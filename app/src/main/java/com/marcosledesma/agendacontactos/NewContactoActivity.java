package com.marcosledesma.agendacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.marcosledesma.agendacontactos.adapters.AdapterDirecciones;
import com.marcosledesma.agendacontactos.adapters.AdapterTelefonos;
import com.marcosledesma.agendacontactos.modelos.Contacto;
import com.marcosledesma.agendacontactos.modelos.Direccion;
import com.marcosledesma.agendacontactos.modelos.Telefono;

import java.text.ParseException;
import java.util.ArrayList;

public class NewContactoActivity extends AppCompatActivity {
    private EditText txtNombre, txtApellidos, txtEmpresa, txtNombreDireccion, txtProvincia, txtCalle,
    txtNumero, txtCodigoPostal, txtNombreTelefono, txtNumeroTelefono;
    private Button btnGuardar;
    // Modelo de datos
    private ArrayList<Telefono> listaTelefonos;
    private ArrayList<Direccion> listaDirecciones;
    // Contenedor - Adapter asociado
    private ListView contenedorTelefonos;
    private AdapterTelefonos adapterTelefonos;
    private ListView contenedorDirecciones;
    private AdapterDirecciones adapterDirecciones;
    // Fila o elemento que se repite
    private int filaTelefono;
    private int filaDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contacto);
        inicializaVariables();

        // Listas de Direcciones y Telefonos del contacto
        listaTelefonos = new ArrayList<>();
        listaDirecciones = new ArrayList<>();

        contenedorTelefonos = findViewById(R.id.contenedorTelefonos);
        filaTelefono = R.layout.fila_telefono;
        adapterTelefonos = new AdapterTelefonos(this, filaTelefono, listaTelefonos);

        contenedorDirecciones = findViewById(R.id.contenedorDirecciones);
        filaDireccion = R.layout.fila_direccion;
        adapterDirecciones = new AdapterDirecciones(this, filaDireccion, listaDirecciones);

        contenedorTelefonos.setAdapter(adapterTelefonos);
        contenedorDirecciones.setAdapter(adapterDirecciones);
        // Montaremos los adapters en onResume (para recuperar listaTelefonos y listaDirecciones si hemos girado la pantalla)

        // Si todos los campos están completos, guardamos el Contacto
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().isEmpty() &&
                        !txtApellidos.getText().toString().isEmpty() &&
                        !txtEmpresa.getText().toString().isEmpty()) {
                    Contacto contacto = new Contacto();
                    contacto.setNombre(txtNombre.getText().toString());
                    contacto.setApellidos(txtApellidos.getText().toString());
                    contacto.setEmpresa(txtEmpresa.getText().toString());
                    //
                    ArrayList<Direccion> direcciones = new ArrayList<Direccion>();
                    Direccion direccion = new Direccion();
                    direccion.setNombre(txtNombreDireccion.getText().toString());
                    direccion.setProvincia(txtProvincia.getText().toString());
                    direccion.setCalle(txtCalle.getText().toString());
                    direccion.setNumero(Integer.parseInt(txtNumero.getText().toString()));
                    direccion.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText().toString()));
                    direcciones.add(direccion);

                    contacto.setDirecciones(direcciones);

                    ArrayList<Telefono> telefonos = new ArrayList<Telefono>();
                    Telefono telefono = new Telefono();
                    telefono.setNombre(txtNombreTelefono.getText().toString());
                    telefono.setNumeroTelefono(txtNumeroTelefono.getText().toString());
                    telefonos.add(telefono);

                    contacto.setTelefonos(telefonos);

                    // Ya lo tengo, me lo llevo al Main en el Intent
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("CONTACTO", contacto);

                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // Así mostramos "Todos los campos son obligatorios" en el idioma correspondiente (R.string.error)
                    Toast.makeText(NewContactoActivity.this, getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void inicializaVariables() {
        txtNombre = findViewById(R.id.txtNombreAddContacto);
        txtApellidos = findViewById(R.id.txtApellidosAddContacto);
        txtEmpresa = findViewById(R.id.txtEmpresaAddContacto);
        // txtDirecciones<>(), txtTelefonos<>()
        btnGuardar = findViewById(R.id.btnGuardarAddContacto);
    }
}