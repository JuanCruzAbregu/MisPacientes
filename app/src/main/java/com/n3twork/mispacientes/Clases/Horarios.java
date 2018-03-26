package com.n3twork.mispacientes.Clases;

/**
 * Created by N3TWORK on 29/10/2017.
 */

public class Horarios {

    private int _id;
    private String horario;
    private String nombre;
    private int id_nombre;

    public Horarios(){
    }

    public Horarios(String horario, String nombre, int id_nombre){
        this.horario = horario;
        this.nombre = nombre;
        this.id_nombre = id_nombre;

    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getId_nombre() {
        return id_nombre;
    }

    public void setId_nombre(int id_nombre) {
        this.id_nombre = id_nombre;
    }
}
