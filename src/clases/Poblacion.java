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
    //one point entre 0...41
    //mutar ramdom < 0.10 -> > 0.10 no mutar (mutar las dos rutas)(se muta con swap aleatorio)
//IKER QUEDA POR HACER EL MÉTODO DE CRUCE Y PROBAR LO QUE HEMOS HECHO
    private Poblacion() {

    }

    public static Poblacion getPoblacion() {
        return MAEPoblacion;

    }

    public void eliminarDescartados() {
        for (int i = poblacionSize; i < poblacion.size(); i++) {
            poblacion.remove(poblacionSize);
        }
    }

    public void ordenar() {
        poblacion = new LinkedList(quicksort((Ruta[]) poblacion.toArray(), 0, poblacion.size() - 1));
    }

    private LinkedList<Ruta> quicksort(Ruta A[], int izq, int der) {
        LinkedList<Ruta> result = new LinkedList();
        Ruta pivote = A[izq]; // tomamos primer elemento como pivote
        int i = izq; // i realiza la bÃºsqueda de izquierda a derecha
        int j = der; // j realiza la bÃºsqueda de derecha a izquierda
        Ruta aux;

        while (i < j) { // mientras no se crucen las bÃºsquedas
            while (A[i].getSumaDistancias() <= pivote.getSumaDistancias() && i < j) {
                i++; // busca elemento mayor que pivote
            }
            while (A[j].getSumaDistancias() > pivote.getSumaDistancias()) {
                j--; // busca elemento menor que pivote
            }
            if (i < j) { // si no se han cruzado
                aux = A[i]; // los intercambia
                A[i] = A[j];
                A[j] = aux;
            }
        }
        A[izq] = A[j]; // se coloca el pivote en su lugar de forma que tendremos
        A[j] = pivote; // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1) {
            quicksort(A, izq, j - 1); // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            quicksort(A, j + 1, der); // ordenamos subarray derecho
        }
        for (int k = 0; k < A.length; k++) {
            result.addLast(A[k]);
        }
        return result;
    }

    public void generarPoblacionInicial() {
        for (int i = 0; i < poblacionSize; i++) {
            Ruta r = ParserTSP.getParserTSP().generarRutaAleatoriaP();
            if (!poblacion.contains(r)) {
                poblacion.addLast(r);
            } else {
                i--;
            }
        }
    }
    
    public void mutar(Ruta hijo1,Ruta hijo2){
    double mutar = (Math.random() * 1);
    if(mutar<0.1){
    hijo1.swapPobla();
    hijo2.swapPobla();
    }
    }
}
