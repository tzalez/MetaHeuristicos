package clases;

import java.util.LinkedList;

public class Poblacion {

    private static Poblacion MAEPoblacion = new Poblacion();
    private LinkedList<Ruta> poblacion = new LinkedList();
    private final int poblacionSize = 100;
    private final double ratioMutacion = 0.1;
    private final int candidatosSize = 50;
    private int numeroIteraciones = 1000000;

    private Poblacion() {

    }

    public static Poblacion getPoblacion() {
        return MAEPoblacion;

    }

    public void setNumeroIteraciones(int maxRuns) {
        this.numeroIteraciones = maxRuns;
    }

    public Ruta obtenerPrimeraRuta() {
        return poblacion.get(0);
    }

    public void eliminarDescartados() {
        int z, i;
        z = poblacion.size();
        //for (int i = poblacionSize-1; i < poblacion.size()-1; i++) {//Esto no se puede poner por que en cada iteracion el tamaño de poblacion.size() cambia
        for (i = poblacionSize; i < z; i++) {
            poblacion.removeLast();
        }
    }

    public void ordenar() {
        poblacion = new LinkedList(quicksort(poblacion, 0, poblacion.size() - 1));

    }

    private LinkedList<Ruta> quicksort(LinkedList<Ruta> lR, int izq, int der) {
        LinkedList<Ruta> result = new LinkedList();
        Ruta pivote = lR.get(izq);
        int i = izq;
        int j = der;
        int k;
        Ruta aux;

        while (i < j) {
            while (lR.get(i).getSumaDistancias() <= pivote.getSumaDistancias() && i < j) {
                i++;
            }
            while (lR.get(j).getSumaDistancias() > pivote.getSumaDistancias()) {
                j--;
            }
            if (i < j) {
                aux = lR.get(i);
                lR.set(i, lR.get(j));
                lR.set(j, aux);
            }
        }
        lR.set(izq, lR.get(j));
        lR.set(j, pivote);
        if (izq < j - 1) {
            quicksort(lR, izq, j - 1);
        }
        if (j + 1 < der) {
            quicksort(lR, j + 1, der);
        }
        for (k = 0; k < lR.size(); k++) {
            result.add(lR.get(k));
        }
        return result;
    }

    public void generarPoblacionInicial() {
        poblacion = new LinkedList();
        int i;
        Ruta r;
        for (i = 0; i < poblacionSize; i++) {
            r = ParserTSP.getParserTSP().generarRutaAleatoriaP();
            if (!poblacion.contains(r)) {
                r.calcularSumaDistancias();
                numeroIteraciones--;
                poblacion.addLast(r);

            } else {
                i--;
            }
        }
    }

    public void mutar(Ruta hijo1, Ruta hijo2) {
        double mutar = (Math.random() * 1);
        if (mutar < ratioMutacion) {
            hijo1.swapPobla();
            hijo2.swapPobla();
        }
    }

    public LinkedList<Ruta> cruzarRutas(LinkedList<Ruta> lR) {
        LinkedList<Ruta> nuevasRutas = new LinkedList();
        int j;
        Ruta[] hijitos = new Ruta[2];
        Ruta r1;
        Ruta r2;
        for (j = 0; j <= candidatosSize / 2; j++) {
            r1 = lR.get((int) (Math.random() * candidatosSize));
            r2 = lR.get((int) (Math.random() * candidatosSize));
            hijitos = cruceSimple(r1, r2);
            nuevasRutas.addLast(hijitos[0]);
            nuevasRutas.addLast(hijitos[1]);
        }
        return nuevasRutas;
    }

    public Ruta[] cruceSimple(Ruta rP1, Ruta rP2) {
        Ruta[] result = new Ruta[2];
        int point = (int) (Math.random() * rP1.numeroCiudades());
        Ruta cp1 = new Ruta();
        Ruta cp2 = new Ruta();
        int i, j;
        for (i = 0; i <= point; i++) {
            cp1.agregarCiudad(rP1.getRuta().get(i));
            cp2.agregarCiudad(rP2.getRuta().get(i));

        }
        for (j = 0; j < rP2.getRuta().size(); j++) {
            if (!cp1.contieneCiudad(rP2.getRuta().get(j).getIdCuidad())) {
                cp1.agregarCiudad(rP2.getRuta().get(j));
            }
            if (!cp2.contieneCiudad(rP1.getRuta().get(j).getIdCuidad())) {
                cp2.agregarCiudad(rP1.getRuta().get(j));
            }
        }
        mutar(cp1, cp2);
        cp1.calcularSumaDistancias();
        cp2.calcularSumaDistancias();
        numeroIteraciones -= 2;
        result[0] = cp1;
        result[1] = cp2;
        return result;
    }

    public LinkedList<Ruta> obtenerCandidatosCruze() {
        LinkedList<Ruta> candidatos = new LinkedList();
        int i;
        for (i = 0; i < candidatosSize; i++) {
            int indiceRuta = (int) (Math.random() * candidatosSize);
            candidatos.add(i, poblacion.get(indiceRuta));
        }
        return candidatos;
    }

    public void buscarRutaEnPoblacion() {
        generarPoblacionInicial();
        ordenar();
        while (numeroIteraciones > 0) {
            poblacion.addAll(cruzarRutas(obtenerCandidatosCruze()));
            ordenar();
            eliminarDescartados();
        }
    }
}
