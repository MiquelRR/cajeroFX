package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    LogToFile log = new LogToFile("cuenta");
    private static List<Cuenta> all = new ArrayList<>();
    static {
        List<String[]> taula = Accesdb.lligTaula("Cuenta");
        for (String[] reg : taula) {
            all.add(new Cuenta(reg[0], reg[1], getCents(reg[2])));
        }

    }
    private String num_cta;
    private String nif;
    // el saldo lo vamos a trabajar en centimos
    private Integer saldo;

    public Cuenta(String num_cta, String nif, Integer saldo) {
        this.num_cta = num_cta;
        this.nif = nif;
        this.saldo = saldo;
    }

    public String getNum_cta() {
        return num_cta;
    }

    public void setNum_cta(String num_cta) {
        this.num_cta = num_cta;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String afterTakeOut(Integer cents){
        this.saldo-=cents;
        String sd=getMoneyString(this.saldo);
        String sd2=sd.substring(0,sd.length()-2);
        log.log(String.format(Accesdb.newAmount,sd2,this.nif));
        Accesdb.modifica(String.format(Accesdb.newAmount,sd2,this.nif));
        return getMoneyString(this.saldo);
    }

    public static int getCents(String moneyString) {

        String[] trunk = moneyString.split("\\.");
        String stringCents = trunk.length > 1 ? trunk[1].substring(0, Math.min(trunk[1].length(), 2)) : "0";
        int euros = Integer.parseInt(trunk[0]);
        int cents = Integer.parseInt(stringCents);

        int totcents = euros * 100 + cents;

        return totcents;
    }
    public static String getMoneyString(Integer cents){
        String cant=Integer.toString(cents);
        int digits=cant.length();
        if (digits>=2) {
            cant=cant.substring(digits-2, digits);
        } else {
            cant="0"+cant;
        }
        String str= (cents/100)+"."+cant+" €";
        return str;
    }
    public static Cuenta getCuentaByNum(String num_cta){
        for (Cuenta cuenta : all) {
            if(cuenta.getNum_cta().equals(num_cta)) return cuenta;
        }
        return null;
    }

    public String getSaldoString(){
        return getMoneyString(this.getSaldo());
    }

    public static List<String> getClientAccounts(String nif){
        List<String> accounts = new ArrayList<>();
        for (Cuenta acc : Cuenta.all) {
            if(acc.getNif().equals(nif))
            accounts.add(acc.getNum_cta());
        }
        return accounts;
    }

}
