package com.n3twork.mispacientes.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import com.n3twork.mispacientes.Clases.Horarios;
import com.n3twork.mispacientes.Clases.Pacientes;

import java.util.ArrayList;

/**
 * Created by N3TWORK on 30/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "pacientes.db";
    public static final String TABLA_PACIENTES = "pacientes";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_DESCRIPCION = "descripcion";

    public static final String TABLA_HORARIOS = "horarios";
    public static final String COLUMNA_ID = "_id";
    public static final String COLUMNA_HORA = "hora";
    public static final String COLUMNA_NOMBRE = "nombre";
    public static final String COLUMNA_ID_NOMBRE = "id_nombre";

    public String pac = "CREATE TABLE " + TABLA_PACIENTES + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOMBRE + " TEXT NOT NULL, " +
            COLUMN_TELEFONO + " TEXT, " +
            COLUMN_DESCRIPCION + " TEXT " +
            ");";

    public String hora = "CREATE TABLE " + TABLA_HORARIOS + "(" +
            COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMNA_HORA + " TEXT NOT NULL, " +
            COLUMNA_NOMBRE + " TEXT, " +
            COLUMNA_ID_NOMBRE + " INTEGER, " +
            "FOREIGN KEY("+ COLUMNA_ID_NOMBRE + ") REFERENCES " + TABLA_PACIENTES + "(" + COLUMN_ID + ")" +
            ");";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(pac);
        db.execSQL(hora);

        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(0,'8:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(1,'8:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(2,'9:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(3,'9:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(4,'10:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(5,'10:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(6,'11:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(7,'11:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(8,'12:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(9,'12:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(10,'13:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(11,'13:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(12,'14:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(13,'14:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(14,'15:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(15,'15:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(16,'16:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(17,'16:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(18,'17:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(19,'17:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(20,'18:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(21,'18:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(22,'19:00','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(23,'19:30','',100 )" );
        db.execSQL("INSERT INTO " + TABLA_HORARIOS +"(" + COLUMNA_ID + ", " + COLUMNA_HORA + ", " + COLUMNA_NOMBRE + ", " + COLUMNA_ID_NOMBRE +
                ") VALUES(24,'20:00','',100 )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PACIENTES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_HORARIOS);

        db.execSQL(pac);
        db.execSQL(hora);

    }

    public void addPaciente(Pacientes pacientes){

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRE, pacientes.get_nombre());
        values.put(COLUMN_TELEFONO, pacientes.get_telefono());
        values.put(COLUMN_DESCRIPCION, pacientes.get_descripcion());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLA_PACIENTES, null, values);
        db.close();
    }

    /**
     * Metodo que devuelve todos los pacientes
     * @return
     */

    public Cursor obtenerTodosPacientes(){

        String[] columnas = new String[]{COLUMN_ID, COLUMN_NOMBRE, COLUMN_TELEFONO, COLUMN_DESCRIPCION};
        Cursor cursor = this.getReadableDatabase().query(TABLA_PACIENTES, columnas, null, null,null,null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public Cursor obtenerTodasHoras(){

        String[] columnas = new String[]{COLUMNA_ID, COLUMNA_HORA, COLUMNA_NOMBRE, COLUMNA_ID_NOMBRE};
        Cursor cursor = this.getReadableDatabase().query(TABLA_HORARIOS, columnas, null, null,null,null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor obtenerPacientesDialogo(){

        String[] columnas = new String[]{COLUMN_ID, COLUMN_NOMBRE};
        Cursor cursor = this.getReadableDatabase().query(TABLA_PACIENTES, columnas, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    /**
     * Metodo publico que cierra la base de datos
     */
    public void cerrar(){
        this.close();
    }



}
