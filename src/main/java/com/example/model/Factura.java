package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private static List<Factura> all = new ArrayList<>();
    static {
        List<String[]> taula = Accesdb.lligTaula("Factura");
        for (String[] reg : taula) {
            all.add(new Factura(Integer.parseInt(reg[0]), reg[1], Integer.parseInt(reg[2]), Cuenta.getCents(reg[3])));
        }
    }
    Integer num_fra;
    String nif;
    Integer num_habitacion;
    Integer importe; // centimos

    public Factura(Integer num_fra, String nif, Integer num_habitacion, Integer importe) {
        this.num_fra = num_fra;
        this.nif = nif;
        this.num_habitacion = num_habitacion;
        this.importe = importe;
    }

    public void delete(String num){
        for (int index = 0; index < all.size(); index++) {
             if (Integer.toString(all.get(index).getNum_fra()).equals(num)){ 

                String query=String.format(Accesdb.deleteFact,all.get(index).getNum_fra());
                Accesdb.modifica(query);
                all.remove(index);
        }
    }
    } 


    public Integer getNum_fra() {
        return num_fra;
    }

    public void setNum_fra(Integer num_fra) {
        this.num_fra = num_fra;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Integer getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(Integer num_habitacion) {
        this.num_habitacion = num_habitacion;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
    }

    public String getImporteString() {
        return Cuenta.getMoneyString(this.getImporte());
    }

    public static List<String> getNumByNif(String nif) {
        List<String> list = new ArrayList<>();
        for (Factura factura : all) {
            if (factura.getNif().equals(nif))
                list.add(Integer.toString(factura.getNum_fra()));
        }
        return list;
    }

    public static Factura getFactByNum(String num) {
        for (Factura factura : all) {
            if (Integer.toString(factura.getNum_fra()).equals(num))
                return factura;
        }
        return null;
    }
}
