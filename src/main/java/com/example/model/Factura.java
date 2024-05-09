package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private static List<Factura> all= new ArrayList<>();
    static{
        List<String[]> taula = Accesdb.lligTaula("Factura");
        for (String[] reg : taula) {
            all.add(new Factura(Integer.parseInt(reg[0]), reg[1], Integer.parseInt(reg[2]),Cuenta.getCents(reg[3])));
        }
    }
    Integer num_fra;
    String nif;
    Integer num_habitacion;
    Integer importe; //centimos

    public Factura(Integer num_fra, String nif, Integer num_habitacion, Integer importe) {
        this.num_fra = num_fra;
        this.nif = nif;
        this.num_habitacion = num_habitacion;
        this.importe = importe;
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

    public String getImporteString(){
        return Cuenta.getMoneyString(this.getImporte());
    }


    
    
}
