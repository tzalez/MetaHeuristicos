/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import clases.ParserTSP;

/**
 *
 * @author Alvaro
 */
/*Evaluar 1.000.000 de soluciones
  Repetir el proceso unas 10 veces para ajustar los valores
*/
public class main{
    
    public static void main(String[] args)throws Exception{
        ParserTSP.getVecinos().cargarDatos("./src/ficheros/dantzig42.tsp", 42);
        int distancias[][]=ParserTSP.getVecinos().getMatrizDistancias();
        System.out.println(toString(distancias));
    }
    
public static String toString(int[][] M) {
    String separator = "\t";
    StringBuffer result = new StringBuffer();
    // iterate over the first dimension
    for (int i = 0; i < M.length; i++){
        System.out.print("fila "+i+" ");
        System.out.println();
        // iterate over the second dimension
        for(int j = 0; j < M[i].length; j++){
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
