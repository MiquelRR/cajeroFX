package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private static Client loggedClient;
    
    private static List<Client> all= new ArrayList<>();
    static{
        List<String[]> taula=Accesdb.lligTaula("Cliente");
        for (String[] reg : taula) {
            all.add(new Client(reg[0], reg[1], reg[2], reg[3],reg[4]));
        }
    }
    
    public static Client valideClient(String nif, String pwd){
        for (Client client : all) {
            //el orden importa
            if(client.getNif().equals(nif) && client.getClave().equals(pwd)) return client;
        }
        return null;
    }

    public Client(String nif, String clave, String nombre, String apellidos, String movil) {
        this.nif = nif;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.movil = movil;
    }
    private String nif;
    private String clave;
    private String nombre;
    private String apellidos;
    private String movil;

    public String getNif() {
        return nif;
    }
    public void setNif(String nif) {
        this.nif = nif;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getMovil() {
        return movil;
    }
    public void setMovil(String movil) {
        this.movil = movil;
    }
    public static Client getLoggedClient() {
        return loggedClient;
    }
    public static void setLoggedClient(String nif){
        for (Client client : all) {
            if(client.getNif().equals(nif))
            setLoggedClient(client);
        }
    }
    public static void setLoggedClient(Client loggedClient) {
        Client.loggedClient = loggedClient;
    }
    public static List<Client> getAll() {
        return all;
    }
    public static void setAll(List<Client> all) {
        Client.all = all;
    }
    
    
}
