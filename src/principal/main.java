package principal;

import clases.ParserTSP;
import clases.Poblacion;
import clases.Ruta;

public class main {

    public static void main(String[] args) throws Exception {
        ParserTSP.getParserTSP().cargarDatos("./src/ficheros/dantzig42.tsp", 42);
        ParserTSP.getParserTSP().getMatrizDistancias();
        ParserTSP.getParserTSP().cargarCiudades();
        double fitnesMedioLocal = 0;
        double fitnesMedioBP = 0;
        Ruta mejorLocal = new Ruta();
        Ruta act;
        Ruta mejorBP = new Ruta();
        System.out.println("BÚSQUEDA LOCAL:");
        System.out.println();

        for (int i = 0; i < 10; i++) {
            ParserTSP.getParserTSP().generarRutaAleatoria();
            Ruta result = ParserTSP.getParserTSP().greedy();
            System.out.println("Solucion " + (i + 1) + ": " + result.calcularSumaDistancias());
            fitnesMedioLocal += result.getSumaDistancias();
            if (result.getSumaDistancias() < mejorLocal.getSumaDistancias()) {
                mejorLocal = result;
            }
            ParserTSP.getParserTSP().setMaxRuns(1000000);
        }
        System.out.println();
        System.out.println("Mejor Solucion:" + "\n" + mejorLocal.imprimirCiudades() + "\n"
                + "Fitness de la mejor solución: " + mejorLocal.getSumaDistancias());
        System.out.println();
        System.out.println("Fitness medio: " + fitnesMedioLocal / 10);
        System.out.println();
        System.out.println("BÚQUEDA POBLACIONAL:");
        System.out.println();

        for (int i = 0; i < 10; i++) {
            Poblacion.getPoblacion().buscarRutaEnPoblacion();
            act = Poblacion.getPoblacion().obtenerPrimeraRuta();
            System.out.println("Solucion " + (i + 1) + ": " + act.getSumaDistancias());
            fitnesMedioBP += act.getSumaDistancias();
            if (act.getSumaDistancias() < mejorBP.getSumaDistancias()) {
                mejorBP = act;
            }
            Poblacion.getPoblacion().setNumeroIteraciones(1000000);
        }
        System.out.println();

        System.out.println("Mejor Solucion:" + "\n" + mejorBP.imprimirCiudades() + "\n"
                + "Fitness de la mejor solución: " + mejorBP.getSumaDistancias());
        System.out.println();
        System.out.println("Fitness medio: " + fitnesMedioBP / 10);
    }
}
