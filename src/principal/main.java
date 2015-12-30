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
        ParserTSP.getParserTSP().cargarCiudades();
        for (int i = 0; i < 10; i++) {
            //System.out.println("Ejecucion " + (i+1));
            //Busqueda local
            ParserTSP.getParserTSP().generarRutaAleatoria();
            //System.out.println("Greedy:");
           Ruta result = ParserTSP.getParserTSP().greedy();
            System.out.println("Greedy " + result.calcularSumaDistancias());
            ParserTSP.getParserTSP().setMaxRuns(100000);

            //Busqueda en una poblacion
            //Poblacion.getPoblacion().buscarRutaEnPoblacion();
            //System.out.println("RUTA MAS CORTA");
            //System.out.println(Poblacion.getPoblacion().obtenerPrimeraRuta().imprimirCiudades());
            //System.out.println("BP " + Poblacion.getPoblacion().obtenerPrimeraRuta().getSumaDistancias());
            //Poblacion.getPoblacion().setNumeroIteraciones(1000000);
        }
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
