package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
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

    public String getSaldoString(){
        return getMoneyString(this.getSaldo());
    }

    public List<String> getClientAccounts(String nif){
        List<String> accounts = new ArrayList<>();
        for (Cuenta acc : Cuenta.all) {
            if(acc.getNif().equals(nif))
            accounts.add(acc.getNif());
        }
        return accounts;
    }

}
