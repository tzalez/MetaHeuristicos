package principal;

import clases.ParserTSP;
import clases.Poblacion;
import clases.Ruta;
import java.util.LinkedList;

public class main {

    public static void main(String[] args) throws Exception {
        //cargar ciudades
        ParserTSP.getParserTSP().cargarDatos("./src/ficheros/dantzig42.tsp", 42);
        int distancias[][] = ParserTSP.getParserTSP().getMatrizDistancias();
        ParserTSP.getParserTSP().cargarCiudades();
        double fitnesMedioLocal = 0;
        double fitnesMedioBP = 0;
        Ruta mejorLocal = new Ruta();
        Ruta mejorBP = new Ruta();
        System.out.println("Búsqueda Local:");
        System.out.println();

        for (int i = 0; i < 10; i++) {
            //System.out.println("Ejecucion " + (i+1));
            //Busqueda local
            ParserTSP.getParserTSP().generarRutaAleatoria();
            Ruta result = ParserTSP.getParserTSP().greedy();
            System.out.println("Solucion " + i + ": " + result.calcularSumaDistancias());
            fitnesMedioLocal += result.getSumaDistancias();
            ParserTSP.getParserTSP().setMaxRuns(1000000);
        }
        System.out.println("Mejor Solucion:" + "\n" + mejorLocal.imprimirCiudades() + "\n" + 
                "Fitness:" + mejorLocal.calcularSumaDistancias());
        System.out.println("Fitness medio:" + fitnesMedioLocal / 10);
        System.out.println();
        System.out.println("Búsqueda Poblacional:");
        System.out.println();

        for (int i = 0; i < 10; i++) {
            //Busqueda en una poblacion
            Poblacion.getPoblacion().buscarRutaEnPoblacion();
            //System.out.println("RUTA MAS CORTA");
            //System.out.println(Poblacion.getPoblacion().obtenerPrimeraRuta().imprimirCiudades());
            System.out.println("Solucion " + i + ": " + Poblacion.getPoblacion().obtenerPrimeraRuta().getSumaDistancias());
            fitnesMedioBP += Poblacion.getPoblacion().obtenerPrimeraRuta().getSumaDistancias();
            Poblacion.getPoblacion().setNumeroIteraciones(100000);
        }
        System.out.println("Mejor Solucion:");
        System.out.println("Fitness medio:" + fitnesMedioBP / 10);
    }
}
