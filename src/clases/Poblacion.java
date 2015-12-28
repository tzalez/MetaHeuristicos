package clases;

import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alvaro
 */
public class Poblacion {

    private static Poblacion MAEPoblacion = new Poblacion();
    private LinkedList<Ruta> poblacion = new LinkedList();
    private final int poblacionSize = 100;
    private final int padresCandidatos = 50;
    private final double ratioMutacion = 0.10;
    private final int candidatosSize = 50;
    private int evaluations;
    private final int numeroIteraciones = 1000000;

    //one point entre 0...41
    //mutar ramdom < 0.10 -> > 0.10 no mutar (mutar las dos rutas)(se muta con swap aleatorio)
    private Poblacion() {

    }

    public static Poblacion getPoblacion() {
        return MAEPoblacion;

    }

    public Ruta obtenerPrimeraRuta() {
        return poblacion.get(0);
    }

    public void eliminarDescartados() {
        //for (int i = poblacionSize-1; i < poblacion.size()-1; i++) {//Esto no se puede poner por que en cada iteracion el tamaño de poblacion.size() cambia
        for (int i = poblacionSize; i < 150; i++) {
            poblacion.removeLast();
        }
    }

    public void ordenar() {
        poblacion = new LinkedList(quicksort(poblacion, 0, poblacion.size() - 1));
    }

    public void imprimirRutas() {
        int i = 1;
        for (Ruta r : poblacion) {
            System.out.println("Ruta " + i);
            System.out.println("suma -->" + r.getSumaDistancias());
            System.out.println(r.imprimirCiudades());
            i++;
        }
    }

    private LinkedList<Ruta> quicksort(LinkedList<Ruta> lR, int izq, int der) {
        LinkedList<Ruta> result = new LinkedList();
        Ruta pivote = lR.get(izq); // tomamos primer elemento como pivote
        int i = izq; // i realiza la bÃºsqueda de izquierda a derecha
        int j = der; // j realiza la bÃºsqueda de derecha a izquierda
        Ruta aux;

        while (i < j) { // mientras no se crucen las bÃºsquedas
            while (lR.get(i).getSumaDistancias() <= pivote.getSumaDistancias() && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (lR.get(j).getSumaDistancias() > pivote.getSumaDistancias()) {
                j--; // busca elemento menor que pivote
            }
            if (i < j) { // si no se han cruzado
                aux = lR.get(i); // los intercambia
                lR.set(i, lR.get(j));
                lR.set(j, aux);
            }
        }
        lR.set(izq, lR.get(j));// se coloca el pivote en su lugar de forma que tendremos
        lR.set(j, pivote); // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1) {
            quicksort(lR, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            quicksort(lR, j + 1, der); // ordenamos subarray derecho
        }
        for (int k = 0; k < lR.size(); k++) {
            result.add(lR.get(k));
        }
        return result;
    }

    public void generarPoblacionInicial() {
        for (int i = 0; i < poblacionSize; i++) {
            Ruta r = ParserTSP.getParserTSP().generarRutaAleatoriaP();
            if (!poblacion.contains(r)) {
                poblacion.addLast(r);
                r.calcularSumaDistancias();
                evaluations++;
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
        //aplicar el metodo 1-point
        LinkedList<Ruta> nuevasRutas = new LinkedList();
        int i = 0;
        Ruta r1;
        Ruta r2;
        while (i < lR.size()) {
            if (evaluations > numeroIteraciones) {
                break;
            }
            //System.out.println(evaluations);
            r1 = lR.get(i);
            r2 = lR.get(i + 1);
            Ruta copyR1 = new Ruta();
            copyR1.setRuta(new LinkedList<Ciudad>(r1.getRuta()));
            Ruta newRuta1 = new Ruta();
            newRuta1.setRuta(new LinkedList<Ciudad>(r1.getRuta()));
            Ruta newRuta2 = new Ruta();
            newRuta2.setRuta(new LinkedList<Ciudad>(r2.getRuta()));
            int point = (int) Math.random() * r1.numeroCiudades();
            newRuta1.eliminarDesde(point);
            for (int j = 0; j < newRuta2.numeroCiudades(); j++) {
                if (!newRuta1.getRuta().contains(newRuta2.getRuta().get(j))) {
                    newRuta1.agregarCiudad(newRuta2.getRuta().get(j));
                }
            }
            newRuta2.eliminarDesde(point);
            for (int k = 0; k < copyR1.numeroCiudades(); k++) {
                if (!newRuta2.getRuta().contains(copyR1.getRuta().get(k))) {
                    newRuta2.agregarCiudad(copyR1.getRuta().get(k));
                }
            }
            mutar(newRuta1, newRuta2);
            newRuta1.calcularSumaDistancias();
            newRuta2.calcularSumaDistancias();
            evaluations = evaluations + 2;
            nuevasRutas.addLast(newRuta1);
            nuevasRutas.addLast(newRuta2);
            i = i + 2;
        }
        return nuevasRutas;
    }

    public LinkedList<Ruta> obtenerCandidatosCruze() {
        LinkedList<Ruta> candidatos = new LinkedList();
        for (int i = 0; i < candidatosSize; i++) {
            int indiceRuta = (int) (Math.random() * poblacion.size());
            candidatos.add(i, poblacion.get(indiceRuta));
        }
        return candidatos;
    }

    public void buscarRutaEnPoblacion() {
        generarPoblacionInicial();
        ordenar();
        while (evaluations < numeroIteraciones) {
            //imprimirRutas();
            poblacion.addAll(cruzarRutas(obtenerCandidatosCruze()));
            //imprimirRutas();
            ordenar();
            eliminarDescartados();
        }
    }

}
