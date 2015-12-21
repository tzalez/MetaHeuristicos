package clases;

import java.util.HashMap;

public class Ciudad {

    private int idCuidad;
    private final HashMap<Integer, Integer> distanciasDestino;

    public Ciudad(int idCiudad) {
        this.idCuidad = idCiudad;
        distanciasDestino = new HashMap();
    }

    public int getIdCuidad() {
        return idCuidad;
    }

    public void setIdCuidad(int idCuidad) {
        this.idCuidad = idCuidad;
    }

    public void agregarDestino(int idDestino, int distDestino) {
        distanciasDestino.put(idDestino, distDestino);
    }

    public int devolverDistDestino(int idDestino) {

        return distanciasDestino.get(idDestino);
    }
}
