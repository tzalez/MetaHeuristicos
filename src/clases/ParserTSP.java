
package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 *
 * @author iker
 */
public class ParserTSP {

    private static ParserTSP vecinos = new ParserTSP();
    //private int populationSize;
    private int maxGenerations;
    private int maxRuns = 100000;
    //private boolean isTSPFileIn;
    private int matrizDistancias[][];
    private int numVecinos;
    private HashMap<Integer, Ciudad> lCiudades = new HashMap();
    private Ruta rutaO = new Ruta();

    private ParserTSP() {
    }

    public static ParserTSP getParserTSP() {
        return vecinos;
    }

    public void cargarDatos(String path, int vecinos) throws Exception {
        this.numVecinos = vecinos;
        this.matrizDistancias = parseFile(path);
    }

    public int[][] getMatrizDistancias() {
        return matrizDistancias;
    }

    public int[][] parseFile(String fileName) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        Boolean nodeCoordSection = false;
        int coordI = 0;
        int coordJ = 0;
        int graph[][] = new int[this.numVecinos][this.numVecinos];
        int distancia;
        line = in.readLine();
        while (!line.equalsIgnoreCase("EOF") && !line.equalsIgnoreCase(" EOF") && !line.equals("")) {
            if (nodeCoordSection) {
                StringTokenizer strTok = new StringTokenizer(line, " \t");
                while (strTok.hasMoreElements()) {
                    distancia = Integer.parseInt(strTok.nextToken());
                    graph[coordI][coordJ] = distancia;
                    //graph[coordJ][coordI] = distancia;
                    if (distancia == 0) {
                        coordI++;
                        coordJ = 0;
                    } else {
                        coordJ++;
                    }
                }
            } else {
                if (line.equalsIgnoreCase("EDGE_WEIGHT_SECTION")) {
                    nodeCoordSection = true;
                }
            }
            line = in.readLine();
        }

        return graph;
    }

    public void cargarCiudades() {
        for (int i = 0; i < matrizDistancias.length; i++) {
            Ciudad temp = new Ciudad(i);
            for (int j = 0; j < matrizDistancias[i].length; j++) {
                if (i < j) {
                    temp.agregarDestino(j, matrizDistancias[j][i]);
                    //System.out.println(matrizDistancias[j][i]);
                } else if (j < i) {
                    temp.agregarDestino(j, matrizDistancias[i][j]);
                    //System.out.println(matrizDistancias[i][j]);
                }
            }
            lCiudades.put(i, temp);

        }

    }

    public void generarRutaAleatoria() {
        rutaO = new Ruta();
        while (rutaO.numeroCiudades() < matrizDistancias.length) {
            int ran = (int) (Math.random() * 42);
            rutaO.agregarCiudad(lCiudades.get(ran));
        }
    }

    public Ruta generarRutaAleatoriaP() {
        Ruta r = new Ruta();
        while (r.numeroCiudades() < matrizDistancias.length) {
            int ran = (int) (Math.random() * 42);
            r.agregarCiudad(lCiudades.get(ran));
        }
        return r;
    }

    public boolean contieneRuta(LinkedList<Ruta> lRutas, Ruta ru) {
        boolean contains = false;
        for (int i = 0; i < lRutas.size(); i++) {
            if (lRutas.get(i).comparar(ru)) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public void setMaxRuns(int maxRuns) {
        this.maxRuns = maxRuns;
    }

    public Ruta greedy() {
        int fCurrent, fAux;
        int iBest = 0;
        int jBest = 0;
        LinkedList<Ruta> rutasEvaluadas = new LinkedList();
        fCurrent = rutaO.calcularSumaDistancias();
        //System.out.println(fCurrent);
        rutasEvaluadas.addLast(rutaO);
        maxRuns--;
        Ruta rutaAux;
        while (maxRuns > 0) {
            for (int i = 0; i < matrizDistancias.length && maxRuns > 0; i++) {
                for (int j = 0; j < matrizDistancias.length && maxRuns > 0; j++) {
                    //System.out.println(maxRuns);
                    if (i != j) {
                        rutaAux = rutaO.swapLocal(i, j);
                        //if(!contieneRuta(rutasEvaluadas,rutaAux)){
                        rutasEvaluadas.addLast(rutaAux);
                        fAux = rutaAux.calcularSumaDistancias();
                        maxRuns--;
                        if (fAux < fCurrent) {
                            iBest = i;
                            jBest = j;
                            fCurrent = fAux;
                                    //System.out.println("fcurrent: "+fCurrent);

                        }
                        //}
                    }
                }
            }

            if (rutaO.comparar(rutaO.swapLocal(iBest, jBest))) {
                break;
            } else {
                rutaO = rutaO.swapLocal(iBest, jBest);
            }
        }
        return rutaO;
    }
}
