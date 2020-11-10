package com.marcosledesma.agendacontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marcosledesma.agendacontactos.modelos.Contacto;
import com.marcosledesma.agendacontactos.modelos.Direccion;
import com.marcosledesma.agendacontactos.modelos.Telefono;

import java.text.ParseException;
import java.util.ArrayList;

public class EditContactoActivity extends AppCompatActivity {
    private EditText txtNombre, txtApellidos, txtEmpresa, txtNombreDireccion, txtProvincia, txtCalle,
            txtNumero, txtCodigoPostal, txtNombreTelefono, txtNumeroTelefono;
    private Button btnEditar, btnEliminar;
    private Contacto contacto;
    private ArrayList<Direccion> direcciones;
    private ArrayList<Telefono> telefonos;
    private int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contacto);
        contacto = getIntent().getExtras().getParcelable("CONTACTO");
        posicion = getIntent().getExtras().getInt("POS");
        direcciones = new ArrayList<>();
        telefonos = new ArrayList<>();
        inicializaVariables();
        rellenaInformacion();

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombre.getText().toString().isEmpty() &&
                        !txtApellidos.getText().toString().isEmpty() &&
                        !txtEmpresa.getText().toString().isEmpty() &&
                        !txtNombreDireccion.getText().toString().isEmpty() &&
                        !txtProvincia.getText().toString().isEmpty() &&
                        !txtCalle.getText().toString().isEmpty() &&
                        !txtNumero.getText().toString().isEmpty() &&
                        !txtCodigoPostal.getText().toString().isEmpty() &&
                        !txtNombreTelefono.getText().toString().isEmpty() &&
                        !txtNumeroTelefono.getText().toString().isEmpty()) {
                    Contacto contacto = new Contacto();
                    contacto.setNombre(txtNombre.getText().toString());
                    contacto.setApellidos(txtApellidos.getText().toString());
                    contacto.setEmpresa(txtEmpresa.getText().toString());
                    //
                    Direccion direccion = new Direccion();
                    direccion.setNombre(txtNombreDireccion.getText().toString());
                    direccion.setProvincia(txtProvincia.getText().toString());
                    direccion.setCalle(txtCalle.getText().toString());
                    direccion.setNumero(Integer.parseInt(txtNumero.getText().toString()));
                    direccion.setCodigoPostal(Integer.parseInt(txtCodigoPostal.getText().toString()));
                    direcciones.add(direccion);

                    contacto.setDirecciones(direcciones);

                    //
                    Telefono telefono = new Telefono();
                    telefono.setNombre(txtNombreTelefono.getText().toString());
                    telefono.setNumeroTelefono(txtNumeroTelefono.getText().toString());
                    telefonos.add(telefono);

                    contacto.setTelefonos(telefonos);

                    // Ya lo tengo, me lo llevo al Main en el Intent
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("CONTACTO", contacto);
                    bundle.putInt("POS", posicion);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt("POS", posicion);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

    private void rellenaInformacion() {
        txtNombre.setText(contacto.getNombre());
        txtApellidos.setText(contacto.getApellidos());
        txtEmpresa.setText(contacto.getEmpresa());
        // Montar AdapterTelefonos y AdapterDirecciones

        /*txtNombreDireccion.setText(contacto.getDirecciones().get(0).getNombre());
        txtProvincia.setText(contacto.getDirecciones().get(0).getProvincia());
        txtCalle.setText(contacto.getDirecciones().get(0).getCalle());
        txtNumero.setText(contacto.getDirecciones().get(0).getNumero());
        txtCodigoPostal.setText(contacto.getDirecciones().get(0).getCodigoPostal());
        txtNombreTelefono.setText(contacto.getTelefonos().get(0).getNombre());
        txtNumeroTelefono.setText(contacto.getTelefonos().get(0).getNumeroTelefono());*/
    }

    private void inicializaVariables() {
        txtNombre = findViewById(R.id.txtNombreEditContacto);
        txtApellidos = findViewById(R.id.txtApellidosEditContacto);
        txtEmpresa = findViewById(R.id.txtEmpresaEditContacto);
        txtNombreDireccion = findViewById(R.id.txtNombreDireccionEditContacto);
        txtProvincia = findViewById(R.id.txtProvinciaEditContacto);
        txtCalle = findViewById(R.id.txtCalleEditContacto);
        txtNumero = findViewById(R.id.txtNumeroEditContacto);
        txtCodigoPostal = findViewById(R.id.txtCodigoPostalEditContacto);
        txtNombreTelefono = findViewById(R.id.txtNombreTelefonoEditContacto);
        txtNumeroTelefono = findViewById(R.id.txtNumeroTelefonoEditContacto);
        btnEditar = findViewById(R.id.btnEditarEditContacto);
        btnEliminar = findViewById(R.id.btnEliminarEditContacto);
    }
}