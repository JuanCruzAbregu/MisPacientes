package com.n3twork.mispacientes.Adaptadores;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

import com.n3twork.mispacientes.R;

/**
 * Created by N3TWORK on 30/10/2017.
 */

public class PacientesAdaptador extends SimpleCursorAdapter{

    private Cursor cursor;

    /**
     * Constructor con un sólo parámetro
     *
     * @param context
     */

    public PacientesAdaptador(Context context, Cursor cursor, String[] from, int[] to, int flags) {
        super(context, R.layout.list_datos_pacientes_item_row, cursor, from, to, flags);
        this.cursor = cursor;
    }


}
