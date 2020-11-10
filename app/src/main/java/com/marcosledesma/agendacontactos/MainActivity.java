package com.marcosledesma.agendacontactos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marcosledesma.agendacontactos.adapters.AdapterContactos;
import com.marcosledesma.agendacontactos.adapters.AdapterDirecciones;
import com.marcosledesma.agendacontactos.adapters.AdapterTelefonos;
import com.marcosledesma.agendacontactos.configuraciones.Configuraciones;
import com.marcosledesma.agendacontactos.modelos.Contacto;
import com.marcosledesma.agendacontactos.modelos.Direccion;
import com.marcosledesma.agendacontactos.modelos.Telefono;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int ADD_CONTACTO = 1;
    private final int EDIT_CONTACTO = 2;
    // Modelo de datos
    private ArrayList<Contacto> listaContactos;
    // Contenedor - Adapter asociado
    private ListView contenedorContactos;
    private AdapterContactos adapterContactos;
    // Fila o elemento que se repite
    private int filaContacto;

    // Almacenamiento Persistente
    private SharedPreferences spLista;
    private Gson parser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Inicializar elementos
        spLista = getSharedPreferences(Configuraciones.SP_DATOS, MODE_PRIVATE);
        parser = new Gson();

        listaContactos = new ArrayList<>();
        cargaContactos();

        contenedorContactos = findViewById(R.id.contenedorContactos);
        filaContacto = R.layout.fila_contacto;
        adapterContactos = new AdapterContactos(this, filaContacto, listaContactos);

        // contenedorContactos.setAdapter(adapterContactos);
        // Montaremos el adapter en onResume (para recuperar listaContactos si hemos girado la pantalla)

        contenedorContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("POS", position);
                Contacto contacto = listaContactos.get(position);
                bundle.putParcelable("CONTACTO", contacto);
                Intent intent = new Intent(MainActivity.this, EditContactoActivity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_CONTACTO);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lanzamos Activity NewContacto
                startActivityForResult(new Intent(MainActivity.this, NewContactoActivity.class), ADD_CONTACTO);
            }
        });
    }
    /**
     * Mirará si en las SharedPreferences si está el ArrayList y si está lo insertará en listaContactos
     */
    private void cargaContactos() {
        String tempString = spLista.getString(Configuraciones.D_LISTA, null);
        if(tempString != null){
            // Si existe la lista de String lo convertimos a ArrayList (gson)
            ArrayList<Contacto> temp = parser.fromJson(tempString, new TypeToken< ArrayList<Contacto> >(){}.getType());
            // Ahora cogemos los elementos de temp y los insertamos en listaContactos
            listaContactos.addAll(temp);
        }
    }

    /**
     * Utilizaremos guardaLista para guardar los cambios efectuados en EDIT_CONTACTO en las SharedPreferences
     */
    private void guardaLista(){
        String tempString = parser.toJson(listaContactos);
        SharedPreferences.Editor editor = spLista.edit();
        editor.putString(Configuraciones.D_LISTA, tempString);
        editor.apply(); // Abre un hilo para la escritura, así el Main puede seguir con sus procesos
    }

    // Aprovechamos también onActivityResult para guardar los cambios en las SharedPreferences
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CONTACTO && resultCode == RESULT_OK){
            if (data != null && data.getExtras() != null){
                Contacto contacto = data.getExtras().getParcelable("CONTACTO");
                listaContactos.add(contacto);
                adapterContactos.notifyDataSetChanged();
                // Después de añadir un contacto al listaContactos, la guardo también en las SharedPreferences
                guardaLista();
            }
        }
        if (requestCode == EDIT_CONTACTO && resultCode == RESULT_OK){
            if (data != null && data.getExtras() != null){
                int posicion = data.getExtras().getInt("POS");
                Contacto contacto = data.getExtras().getParcelable("CONTACTO");
                if (contacto == null){
                    listaContactos.remove(posicion);
                }else{
                    listaContactos.set(posicion, contacto);
                }
                adapterContactos.notifyDataSetChanged();
                // Después de modificar un contacto al listaContactos, lo guardo también en las SharedPreferences
                guardaLista();
            }
        }
    }

    // No perder listaContactos al girar la pantalla
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Guardo listaContactos antes del destroy
        outState.putParcelableArrayList("LISTA", listaContactos);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Recupero listaCitas
        ArrayList<Contacto> temp = savedInstanceState.getParcelableArrayList("LISTA");
        // Limpio listaCitas
        listaContactos.clear();
        // Lo vuelvo a rellenar
        listaContactos.addAll(temp);
        Log.d("ELEMENTOS", String.valueOf(listaContactos.size()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        contenedorContactos.setAdapter(adapterContactos);
    }
}