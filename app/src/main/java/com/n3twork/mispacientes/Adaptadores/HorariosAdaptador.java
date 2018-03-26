package com.n3twork.mispacientes.Adaptadores;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.n3twork.mispacientes.Clases.Horarios;
import com.n3twork.mispacientes.R;

/**
 * Created by N3TWORK on 29/10/2017.
 */

public class HorariosAdaptador extends SimpleCursorAdapter{

    Cursor cursor;
    /**
     * Constructor con un sólo parámetro
     *
     * @param context
     */

    public HorariosAdaptador(Context context, Cursor cursor, String[] from, int[] to, int flags){
        super(context, R.layout.list_horarios_item_row, cursor, from, to, flags);
        this.cursor = cursor;
    }

}
