/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 *
 * @author iker
 */
public class ParserTSP {

    private static ParserTSP vecinos = new ParserTSP();
    private int populationSize;
    private int maxGenerations;
    private int maxRuns;
    private boolean isTSPFileIn;
    private int matrizDistancias[][];
    private int numVecinos;

    private ParserTSP() {
    }

    public static ParserTSP getVecinos() {
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
}
