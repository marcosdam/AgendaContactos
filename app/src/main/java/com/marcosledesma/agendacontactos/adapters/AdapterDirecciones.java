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
import com.marcosledesma.agendacontactos.modelos.Direccion;

import java.util.ArrayList;

public class AdapterDirecciones extends ArrayAdapter<Direccion> {
    // Atributos del adapter
    private Context context;
    private int resource;
    private ArrayList<Direccion> objects;

    // Constructor con los 3 atributos (context, int, ArrayList<Objeto>)
    public AdapterDirecciones(@NonNull Context context, int resource, @NonNull ArrayList<Direccion> objects) {
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
        Direccion direccion = objects.get(position);

        TextView txtNombre = fila.findViewById(R.id.txtNombreDir);
        TextView txtProvincia = fila.findViewById(R.id.txtProvinciaDir);
        TextView txtCalle = fila.findViewById(R.id.txtCalleDir);
        TextView txtNumero = fila.findViewById(R.id.txtNumeroDir);
        TextView txtCodigoPostal = fila.findViewById(R.id.txtCodigoPostalDir);

        txtNombre.setText(direccion.getNombre());
        txtProvincia.setText(direccion.getProvincia());
        txtCalle.setText(direccion.getCalle());
        txtNumero.setText(direccion.getNumero());
        txtCodigoPostal.setText(direccion.getCodigoPostal());

        return fila;
    }
}
