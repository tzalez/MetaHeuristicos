package clases;

import java.util.LinkedList;

public class Ruta {

    private LinkedList<Ciudad> ruta;
    private int sumaDistancias;

    public Ruta() {
        ruta = new LinkedList();
    }

    public void agregarCiudad(Ciudad Ciudad) {
        if (!ruta.contains(Ciudad)) {
            ruta.addLast(Ciudad);
        }
    }

    public void swap() {
        int r1 = (int) (Math.random() * 42);
        int r2;
        do {
            r2 = (int) (Math.random() * 42);
        } while (r1 == r2);
        if (r1 > r2) {
            Ciudad aux1 = ruta.remove(r1);
            Ciudad aux2 = ruta.remove(r2);
            ruta.set(r2, aux1);
            ruta.set(r1, aux2);
        } else {
            Ciudad aux2 = ruta.remove(r2);
            Ciudad aux1 = ruta.remove(r1);
            ruta.set(r1, aux2);
            ruta.set(r2, aux1);

        }
    }
    public void calcularSumaDistancias(){
        sumaDistancias=0;
             
        for (int i = 0; i < ruta.size(); i++) {
            if(i==ruta.size()-1){
                sumaDistancias+=ruta.get(i).devolverDistDestino(ruta.get(0).getIdCuidad());
            }
            else{sumaDistancias+=ruta.get(i).devolverDistDestino(ruta.get(i+1).getIdCuidad());}
        }
}
    public int tamanoLista(){
    return ruta.size();
    }
}
