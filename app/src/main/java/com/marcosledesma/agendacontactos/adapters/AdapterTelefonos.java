package com.marcosledesma.agendacontactos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.marcosledesma.agendacontactos.R;
import com.marcosledesma.agendacontactos.modelos.Telefono;

import java.util.ArrayList;

public class AdapterTelefonos extends ArrayAdapter<Telefono> {
    // Atributos del adapter
    private Context context;
    private int resource;
    private ArrayList<Telefono> objects;

    // Constructor con los 3 atributos (context, int, ArrayList<Objeto>)
    public AdapterTelefonos(@NonNull Context context, int resource, @NonNull ArrayList<Telefono> objects) {
        super(context, resource, objects);
        // Asigno las variables
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    // Añadir método getView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Eliminar return por defecto
        // Creamos view a partir del xml (LayoutInflater que obtengo del contexto, lo saco de la actividad que me llame)
        View fila = LayoutInflater.from(context).inflate(resource, null);
        Telefono telefono = objects.get(position);

        TextView txtNombre = fila.findViewById(R.id.txtNombreTel);
        TextView txtNumero = fila.findViewById(R.id.txtNumeroTel);

        txtNombre.setText(telefono.getNombre());
        txtNumero.setText(telefono.getNumeroTelefono());
        return fila;
    }
}
