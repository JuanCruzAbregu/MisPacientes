package com.n3twork.mispacientes.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

import com.n3twork.mispacientes.R;

/**
 * Created by N3TWORK on 2/11/2017.
 */

public class DialogPacientesAdaptador extends SimpleCursorAdapter{

    Cursor cursor;


    public DialogPacientesAdaptador(Context context, Cursor cursor, String[] from, int[] to, int flags){
        super(context, R.layout.list_dialog_agregar_pacientes, cursor, from, to, flags);
        this.cursor = cursor;

    }


}
