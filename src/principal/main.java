/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import clases.ParserTSP;
import clases.Poblacion;

/**
 *
 * @author Alvaro
 */
/*Evaluar 1.000.000 de soluciones
 Repetir el proceso unas 10 veces para ajustar los valores
 */
import clases.Ruta;

public class main {

    public static void main(String[] args) throws Exception {
        //cargar ciudades
        ParserTSP.getParserTSP().cargarDatos("./src/ficheros/dantzig42.tsp", 42);
        int distancias[][] = ParserTSP.getParserTSP().getMatrizDistancias();
        //System.out.println(toString(distancias));
        ParserTSP.getParserTSP().cargarCiudades();
        /*
        //Busqueda local
        ParserTSP.getParserTSP().generarRutaAleatoria();
        System.out.println("Greedy:");
        Ruta result=ParserTSP.getParserTSP().greedy();
        System.out.println("La distancia menor es " + result.calcularSumaDistancias());
        */
        //Busqueda en una poblacion
        Poblacion.getPoblacion().buscarRutaEnPoblacion();
        System.out.println(Poblacion.getPoblacion().obtenerPrimeraRuta().getSumaDistancias());
    }

    
    
    
    public static String toString(int[][] M) {
        String separator = "\t";
        StringBuffer result = new StringBuffer();
        // iterate over the first dimension
        for (int i = 0; i < M.length; i++) {
            // iterate over the second dimension
            for (int j = 0; j < M[i].length; j++) {
                result.append(M[i][j]);
                result.append(separator);
            }
            // remove the last separator
            result.setLength(result.length() - separator.length());
            // add a line break.
            result.append("\n");
        }
        return result.toString();
    }

}
