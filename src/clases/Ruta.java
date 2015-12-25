package clases;

import java.util.LinkedList;

public class Ruta {

    private LinkedList<Ciudad> ruta;
    private int sumaDistancias;

    public int getSumaDistancias() {
        return this.sumaDistancias;
    }

    public Ruta() {
        ruta = new LinkedList();
        this.sumaDistancias = 0;
    }

    public void agregarCiudad(Ciudad Ciudad) {
        if (!ruta.contains(Ciudad)) {
            ruta.addLast(Ciudad);
        }
    }

    public LinkedList<Ciudad> getRuta() {
        return ruta;
    }

    public void setRuta(LinkedList<Ciudad> ruta) {
        this.ruta = ruta;
    }

    public void setSumaDistancias(int sumaDistancias) {
        this.sumaDistancias = sumaDistancias;
    }

    public void swapPobla() {
        /*for (int i = 0; i < ruta.size(); i++) {
         System.out.print(ruta.get(i).getIdCuidad() + ",");
         }
         System.out.println();*/
        int r1 = (int) (Math.random() * 42);
        int r2;
        do {
            r2 = (int) (Math.random() * 42);
        } while (r1 == r2);
        Ciudad aux1 = ruta.get(r1);
        Ciudad aux2 = ruta.get(r2);
        ruta.set(r1, aux2);
        ruta.set(r2, aux1);
        /*
         System.out.println("SWAP");
         for (int i = 0; i < ruta.size(); i++) {
         System.out.print(ruta.get(i).getIdCuidad() + ",");
         }
         System.out.println();
         */
    }

    public Ruta swapLocal(int id1, int id2) {
        Ruta result = new Ruta();
        result.setRuta(new LinkedList<Ciudad>(this.getRuta()));
        Ciudad aux1 = result.getRuta().get(id1);
        Ciudad aux2 = result.getRuta().get(id2);
        result.getRuta().set(id1, aux2);
        result.getRuta().set(id2, aux1);
        return result;
    }

    public int calcularSumaDistancias() {
        sumaDistancias = 0;
        for (int i = 0; i < ruta.size(); i++) {
            if (i == ruta.size() - 1) {
                sumaDistancias += ruta.get(i).devolverDistDestino(ruta.get(0).getIdCuidad());
            } else {
                sumaDistancias += ruta.get(i).devolverDistDestino(ruta.get(i + 1).getIdCuidad());
                //System.out.println(ruta.get(i).devolverDistDestino(ruta.get(i+1).getIdCuidad()));
            }
        }
        return sumaDistancias;
    }

    public int numeroCiudades() {
        return ruta.size();
    }

    public boolean comparar(Ruta ru) {
        boolean iguales = true;
        for (int i = 0; i < ru.getRuta().size(); i++) {
            if (ru.getRuta().get(i).getIdCuidad() != this.getRuta().get(i).getIdCuidad()) {
                iguales = false;
                break;
            }
        }

        return iguales;
    }

    public void eliminarDesde(int n) {
        for (int i = n; i < this.ruta.size(); i++) {
            this.ruta.remove(i);
        }
    }
    public String imprimirCiudades(){
        String ciudades="";
        for(Ciudad c : ruta){
            ciudades= ciudades + c.getIdCuidad()+ " - ";
        }
        return ciudades;
    }

}
