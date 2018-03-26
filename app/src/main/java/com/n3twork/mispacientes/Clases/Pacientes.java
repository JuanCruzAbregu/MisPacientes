package com.n3twork.mispacientes.Clases;

/**
 * Created by N3TWORK on 30/10/2017.
 */

public class Pacientes {

    private int _id;
    private String _nombre;
    private String _telefono;
    private String _descripcion;

    public Pacientes(){

    }

    public Pacientes(String _nombre, String _telefono, String _descripcion) {
        this._nombre = _nombre;
        this._telefono = _telefono;
        this._descripcion = _descripcion;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public String get_telefono() {
        return _telefono;
    }

    public void set_telefono(String _telefono) {
        this._telefono = _telefono;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }
}
