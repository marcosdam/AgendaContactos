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
import com.marcosledesma.agendacontactos.modelos.Contacto;

import java.util.ArrayList;
import java.util.List;

public class AdapterContactos extends ArrayAdapter<Contacto> {
    // Atributos del adapter
    private Context context;
    private int resource;
    private ArrayList<Contacto> objects;

    // Constructor con los 3 atributos (context, int, ArrayList<Objeto>)
    public AdapterContactos(@NonNull Context context, int resource, @NonNull ArrayList<Contacto> objects) {
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
        Contacto contacto = objects.get(position);

        TextView txtNombre = fila.findViewById(R.id.txtNombreFila);
        TextView txtApellidos = fila.findViewById(R.id.txtApellidosFila);

        txtNombre.setText(objects.get(position).getNombre());
        txtApellidos.setText(objects.get(position).getApellidos());
        return fila;
    }

}
