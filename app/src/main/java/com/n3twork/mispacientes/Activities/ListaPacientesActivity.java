package com.n3twork.mispacientes.Activities;


import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.n3twork.mispacientes.Adaptadores.PacientesAdaptador;
import com.n3twork.mispacientes.Clases.Pacientes;
import com.n3twork.mispacientes.DB.DBHelper;
import com.n3twork.mispacientes.R;

import java.util.List;


public class ListaPacientesActivity extends AppCompatActivity {

    private ListView listViewDatos;
    FloatingActionButton fabAgendarNuevo;
    DBHelper dbHelper = null;

    String aux_nombre = "";
    String aux_id = "";
    String aux_desc = "";
    String aux_tel = "";
    String[] opciones = new String[]{"Editar", "Borrar"};

    SQLiteDatabase db;

    PacientesAdaptador cursorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        //Habilita el botón para volver a la pantalla anterior
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fabAgendarNuevo = findViewById(R.id.fabAddPaciente);

        fabAgendarNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoPacientes("  Agendar paciente");
            }
        });

        listViewDatos = findViewById(R.id.listViewBaseDatos);
        recuperarTodosPacientes();

    }
    /**
     * Este método recupera todos los pacientes registrados en la tabla
     * Pacientes de la base de datos y los muestra en una lista en la pantalla.
     *
     */

    public void recuperarTodosPacientes(){

        try{

            dbHelper = new DBHelper(this);

            //Devuelve todas las personas con el objeto Cursor.
            Cursor cursor = dbHelper.obtenerTodosPacientes();

            String[]from = new String[]{
                    "_id",
                    "nombre",
                    "telefono",
                    "descripcion"
            };

            int[]to = new int[]{
                    R.id.textViewId,
                    R.id.textViewName,
                    R.id.textViewPhone,
                    R.id.textViewDescription
            };
            cursorAdapter = new PacientesAdaptador(this, cursor, from, to, 0);
            listViewDatos.setAdapter(cursorAdapter);

            listViewDatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                    TextView textoNombre = view.findViewById(R.id.textViewName);
                    TextView textoId     = view.findViewById(R.id.textViewId);
                    TextView textoTel    = view.findViewById(R.id.textViewPhone);
                    TextView textoDesc   = view.findViewById(R.id.textViewDescription);

                    aux_nombre = textoNombre.getText().toString();
                    aux_id     = textoId.getText().toString();
                    aux_tel    = textoTel.getText().toString();
                    aux_desc   = textoDesc.getText().toString();

                    dialogOpcionesPacientes(opciones, aux_nombre, aux_id, aux_tel, aux_desc);

                    return true;
                }
            });

        }catch (Exception e){
            Log.e("Error", "Error: " +e.getMessage());
        }finally {
            dbHelper.cerrar();
        }
    }

    /**
     * Este método genera un cuadro de diálogo que permite agendar nuevos pacientes
     * en la tabla Paciente de la base de datos.
     *
     * @param title
     */

    public void dialogoPacientes(final String title) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_paciente, null);
        builder.setView(viewInflated);

        final TextView textViewTitle = viewInflated.findViewById(R.id.textViewTitle);
        final EditText editTextNombre = (EditText) viewInflated.findViewById(R.id.editTextNombre);
        final EditText editTextTelefono = (EditText) viewInflated.findViewById(R.id.editTextTelefono);
        final EditText editTextDescripcion = (EditText) viewInflated.findViewById(R.id.editTextDescripcion);
        textViewTitle.setText(title);

        builder.setPositiveButton("Agendar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String nombre = editTextNombre.getText().toString().trim();
                if (nombre.length() > 0) {
                    String telefono = editTextTelefono.getText().toString();
                    String descripcion = editTextDescripcion.getText().toString();

                    agregarPaciente(nombre, telefono, descripcion);
                    recuperarTodosPacientes();

                } else {

                    Toast.makeText(getApplicationContext(), "Ingrese el nombre del paciente", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Método que genera un cuadro de diálogo de lista con opciones al realizar un LongClick
     *
     * @param opciones
     * @param aux_nombre
     * @param aux_id
     * @param aux_tel
     * @param aux_desc
     */

    public void dialogOpcionesPacientes(final String[] opciones, final String aux_nombre, final String aux_id, final String aux_tel, final String aux_desc){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {

                if(position == 0){

                    dialogEditar(" Editar", aux_id, aux_nombre, aux_tel, aux_desc);

                }else if(position == 1){

                    dialogConfirmar("Borrar paciente", "¿Desea borrar a "+aux_nombre+" de su lista de pacientes?", aux_nombre);

                }

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Este método genera un cuadro de diálogo para confirmar si el usuario desea borrar
     * de la base de datos al paciente seleccionado.
     *
     * @param title
     * @param message
     * @param aux_nombre
     */

    public void dialogConfirmar(String title, String message, final String aux_nombre){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if(title !=null){
            builder.setTitle(title);
        }

        if(message !=null){
            builder.setMessage(message);
        }

        builder.setCancelable(true);

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    db = dbHelper.getWritableDatabase();
                    db.delete("Pacientes", "nombre='" +aux_nombre+"'", null);
                    recuperarTodosPacientes();

                }catch (Exception e){

                    Log.e("Error", "Error: "+e.getMessage());

                }finally {

                    db.close();

                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Este método agrega pacientes en la tabla Paciente de la base de datos
     * para luego ser mostrado en la pantalla que se genera al pulsar la opcion "Ver Pacientes"
     *
     * @param nombre
     * @param telefono
     * @param descripcion
     */

    public void agregarPaciente(String nombre, String telefono, String descripcion) {

        Pacientes pacientes = new Pacientes(nombre, telefono, descripcion);

        dbHelper.addPaciente(pacientes);
    }

    /**
     * Método que genera un cuadro de diálogo con los datos a editar del campo seleccionado.
     *
     * @param title
     * @param aux_id
     * @param aux_nombre
     * @param aux_tel
     * @param aux_desc
     */

    public void dialogEditar(String title, final String aux_id, final String aux_nombre, String aux_tel, String aux_desc){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_agregar_paciente, null);
        builder.setView(viewInflated);

        final TextView textViewTitle = viewInflated.findViewById(R.id.textViewTitle);
        final EditText editTextNombre = viewInflated.findViewById(R.id.editTextNombre);
        final EditText editTextTelefono = viewInflated.findViewById(R.id.editTextTelefono);
        final EditText editTextDescripcion = viewInflated.findViewById(R.id.editTextDescripcion);

        textViewTitle.setText(title);
        editTextNombre.setText(aux_nombre);
        editTextTelefono.setText(aux_tel);
        editTextDescripcion.setText(aux_desc);

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String nombre = editTextNombre.getText().toString();
                String tel    = editTextTelefono.getText().toString();
                String descip = editTextDescripcion.getText().toString();

                db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put("nombre", nombre);
                values.put("telefono", tel);
                values.put("descripcion", descip);
                db.update("Pacientes", values,"_id='"+aux_id+"'",null);

                recuperarTodosPacientes();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
