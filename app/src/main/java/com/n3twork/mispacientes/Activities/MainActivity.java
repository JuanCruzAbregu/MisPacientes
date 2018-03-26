package com.n3twork.mispacientes.Activities;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.n3twork.mispacientes.Adaptadores.DialogPacientesAdaptador;
import com.n3twork.mispacientes.Adaptadores.HorariosAdaptador;
import com.n3twork.mispacientes.Adaptadores.PacientesAdaptador;
import com.n3twork.mispacientes.Clases.Horarios;
import com.n3twork.mispacientes.Clases.Pacientes;
import com.n3twork.mispacientes.DB.DBHelper;
import com.n3twork.mispacientes.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ListView listaHorarios;
    FloatingActionButton fabVerListadoPacientes;
    TextView textViewFecha, textViewName, textViewHours, textViewForeign, textViewDialogPacientes;
    EditText editTextNombre, editTextTelefono, editTextDescripcion;

    HorariosAdaptador cursorAdapter;

    DateFormat dateFormat = DateFormat.getDateInstance();
    Calendar dateTime = Calendar.getInstance();
    SQLiteDatabase db;
    DBHelper dbHelper;

    int sumaPos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFecha = findViewById(R.id.textViewFecha);

        //** Invocación del método para conversión y actualización de fecha
        updateTextLabel();

        dbHelper               = new DBHelper(this);
        editTextNombre         = findViewById(R.id.editTextNombre);
        editTextTelefono       = findViewById(R.id.editTextTelefono);
        editTextDescripcion    = findViewById(R.id.editTextDescripcion);
        textViewHours          = findViewById(R.id.textViewHora);
        textViewName           = findViewById(R.id.textViewNombre);
        listaHorarios          = findViewById(R.id.listaHoras);
        fabVerListadoPacientes = findViewById(R.id.fabVerListado);

        textViewDialogPacientes = findViewById(R.id.textViewDialogPacientes);
        textViewForeign         = findViewById(R.id.textViewForeign);

        recuperarTodosLosHorarios();
        registerForContextMenu(listaHorarios);

        //** Método que se invoca al presionar el FAB
        fabVerListadoPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaPacientesActivity.class);
                startActivity(intent);
            }
        });

        //** Método que se invoca al hacer un Click
        listaHorarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        dialogAsignarPaciente(position);

            }
        });
    }

    /**
     * Método que genera la vista del menú de opciones en la action bar
     *
     * @param menu
     * @return boolean
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /**
     * Método que asigna la lógica a los distintos items del menú de la action bar
     *
     * @param item
     * @return boolean
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.limpiarPantalla:
                dialogConfirmacion("Atención","¿Está seguro que desa borrar todos los turnos?");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            if(v.getId() == R.id.listaHoras){

                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.menu_delete_item, menu);

            }
    }

    /**
     *
     * Método de menú que elimina el turno seleccionado.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){

            case R.id.delete:
                int pos = info.position;
                sumaPos = pos+1;

                try{

                    db = dbHelper.getReadableDatabase();

                    Cursor c = db
                            .rawQuery("SELECT _id, nombre FROM Pacientes", null);
                    if(c.moveToFirst()){

                        do{

                            c.moveToPosition(pos);

                            ContentValues values = new ContentValues();

                            values.put("nombre","");
                            values.put("id_nombre",100);

                            String posString = String.valueOf(pos);

                            String[]args = new String[]{posString};
                            db.update("horarios",values, "_id=?", args);
                            recuperarTodosLosHorarios();

                        }while (c.moveToNext());

                    }

                }catch (Exception e){
                    Log.e("Error", "Error: "+e.getMessage());
                }finally {
                    db.close();
                }

                return  true;

            default:
                return super.onContextItemSelected(item);
        }

    }

    /**
     * Método que genera un cuadro de diálogo para asignar un paciente a una sesión.
     * Se utiliza para cuando no hay un paciente asignado a esa hora.
     *
     */

    public void dialogAsignarPaciente(final int posicion){

        final Dialog dialog  = new Dialog(this);
        dialog.setContentView(R.layout.list_pacientes_dialog);
        ListView listView = dialog.findViewById(R.id.listViewPacientesDialog);
        TextView textViewTitulo = dialog.findViewById(R.id.textViewTitulo);

        try{
            dbHelper = new DBHelper(this);
            Cursor cursor = dbHelper.obtenerPacientesDialogo();

            String[] from = new String[]{"_id", "nombre"};
            int[] to = new int[]{R.id.textViewForeign, R.id.textViewDialogPacientes};

            DialogPacientesAdaptador dialogPacientesAdaptador = new DialogPacientesAdaptador(this, cursor, from, to, 0);
            listView.setAdapter(dialogPacientesAdaptador);

            textViewTitulo.setText("Asignar Paciente");

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                    TextView textViewForeign = view.findViewById(R.id.textViewForeign);

                    String identificador = textViewForeign.getText().toString();

                    db = dbHelper.getReadableDatabase();

                    Cursor c = db
                            .rawQuery("SELECT _id, nombre FROM Pacientes WHERE _id='"+identificador+"'",null);
                    try {

                        if(c.moveToFirst()){

                            do{

                                int ident = c.getInt(0);
                                String name = c.getString(1);

                                ContentValues values = new ContentValues();

                                values.put("nombre",name);
                                values.put("id_nombre",ident);

                                String posString = String.valueOf(posicion);

                                String[]args = new String[]{posString};
                                db.update("horarios",values, "_id=?", args);
                                recuperarTodosLosHorarios();

                            }while (c.moveToNext());

                            dialog.dismiss();
                        }


                    }catch (Exception e){

                        Log.e("Error", "Error: "+e.getMessage());

                    }finally {
                        db.close();
                    }


                }
            });

        }catch (Exception e){
            Log.e("Error","Error: " +e.getMessage());
        }finally {
            dbHelper.cerrar();
        }

        dialog.show();

    }

    /**
     * Este método genera un cuadro de diálogo que valida la intención del usuario
     * de eliminar todos los turnos agendados.
     *
     * @param title
     * @param message
     */

    public void dialogConfirmacion(final String title, final String message){

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(title != null){
            builder.setTitle(title);
            builder.setMessage(message);
        }

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                db = dbHelper.getReadableDatabase();


                Cursor c = db
                        .rawQuery("SELECT _id, nombre FROM Pacientes ",null);
                try {

                    if(c.moveToFirst()){

                        do{

                            int ident = 100;
                            String name = "";

                            ContentValues values = new ContentValues();

                            values.put("nombre",name);
                            values.put("id_nombre",ident);


                            db.update("horarios",values,null, null);
                            recuperarTodosLosHorarios();

                        }while (c.moveToNext());

                        dialog.dismiss();
                    }


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
     * Este método coloca la fecha, ajustada a formato: Nov 1, 2017 (ej),
     * en la barra de texto presente en Top de la App al iniciar la misma.
     */

    private void updateTextLabel() {

        textViewFecha.setText(dateFormat.format(dateTime.getTime()));

    }

    /**
     * Este método muestra los datos en la lista al comenzar la App,
     * La base de datos debe de tener por defecto las horas de trabajo.
     */

    public void recuperarTodosLosHorarios(){

        try{
            Cursor cursor = dbHelper.obtenerTodasHoras();

            String[] from = new String[]{
                    "_id",
                    "hora",
                    "nombre",
                    "id_nombre"
            };

            int[] to = new int[]{
                    R.id.textViewIdent,
                    R.id.textViewHora,
                    R.id.textViewNombre,
                    R.id.textViewIdNombre
            };

            cursorAdapter = new HorariosAdaptador(this, cursor, from, to, 0);
            listaHorarios.setAdapter(cursorAdapter);

        }catch (Exception e){
            Log.e("Error", "Error: " +e.getMessage());
        }finally {
            dbHelper.cerrar();
        }

    }

}