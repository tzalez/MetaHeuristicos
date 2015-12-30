package principal;

import clases.ParserTSP;
import clases.Poblacion;
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
            /*ParserTSP.getParserTSP().generarRutaAleatoria();
            Ruta result = ParserTSP.getParserTSP().greedy();
            System.out.println("Greedy " + result.calcularSumaDistancias());
            ParserTSP.getParserTSP().setMaxRuns(1000000);*/
                        
            //Busqueda en una poblacion
            Poblacion.getPoblacion().buscarRutaEnPoblacion();
            //System.out.println("RUTA MAS CORTA");
            //System.out.println(Poblacion.getPoblacion().obtenerPrimeraRuta().imprimirCiudades());
            System.out.println("BP " + Poblacion.getPoblacion().obtenerPrimeraRuta().getSumaDistancias());
            Poblacion.getPoblacion().setNumeroIteraciones(1000000);
        }
    }
}
