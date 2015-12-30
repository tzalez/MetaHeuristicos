package clases;

import java.util.LinkedList;

public class Ruta {

    private LinkedList<Ciudad> ruta;
    private int sumaDistancias;

    public int getSumaDistancias() {
        return this.sumaDistancias;
    }

    public Ruta() {
        ruta = new LinkedList();
        this.sumaDistancias = 999999;
    }

    public void agregarCiudad(Ciudad Ciudad) {
        if (!ruta.contains(Ciudad)) {
            ruta.addLast(Ciudad);
        }
    }

    public LinkedList<Ciudad> getRuta() {
        return ruta;
    }

    public void setRuta(LinkedList<Ciudad> ruta) {
        this.ruta = ruta;
    }

    public void setSumaDistancias(int sumaDistancias) {
        this.sumaDistancias = sumaDistancias;
    }

    public void swapPobla() {
        int r1 = (int) (Math.random() * 41);
        int r2;
        do {
            r2 = (int) (Math.random() * 42);
        } while (r1 == r2);
        Ciudad aux1 = ruta.get(r1);
        Ciudad aux2 = ruta.get(r2);
        ruta.set(r1, aux2);
        ruta.set(r2, aux1);
    }

    public LinkedList<Ciudad> swapLocal(int id1, int id2) {
        LinkedList<Ciudad> res = new LinkedList(ruta);
        Ciudad aux1 = res.get(id1);
        Ciudad aux2 = res.get(id2);
        res.set(id1, aux2);
        res.set(id2, aux1);
        return res;
    }

    public int calcularSumaDistancias() {
        sumaDistancias = 0;
        int i;
        for (i = 0; i < ruta.size(); i++) {
            if (i == ruta.size() - 1) {
                sumaDistancias += ruta.get(i).devolverDistDestino(ruta.get(0).getIdCuidad());
            } else {
                sumaDistancias += ruta.get(i).devolverDistDestino(ruta.get(i + 1).getIdCuidad());
            }
        }
        return sumaDistancias;
    }

    public int numeroCiudades() {
        return ruta.size();
    }

    public boolean comparar(LinkedList<Ciudad> ru) {
        boolean iguales = true;
        int i;
        for (i = 0; i < ru.size(); i++) {
            if (ru.get(i).getIdCuidad() != this.getRuta().get(i).getIdCuidad()) {
                iguales = false;
                break;
            }
        }
        return iguales;
    }

    public void eliminarDesde(int n) {
        int i;
        for (i = n; i < this.ruta.size(); i++) {
            this.ruta.remove(i);
        }
    }

    public String imprimirCiudades() {
        String ciudades = "";
        for (Ciudad c : ruta) {
            ciudades = ciudades + c.getIdCuidad() + " - ";
        }
        return ciudades;
    }

    public boolean contieneCiudad(int idCiudad) {
        boolean contiene = false;
        for (Ciudad ci : this.ruta) {
            if (ci.getIdCuidad() == idCiudad) {
                contiene = true;
                break;
            }
        }
        return contiene;
    }
}
