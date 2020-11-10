package com.marcosledesma.agendacontactos.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Telefono implements Parcelable {
    private String nombre;
    private String numeroTelefono;

    public Telefono() {
    }

    public Telefono(String nombre, String numeroTelefono) {
        this.nombre = nombre;
        this.numeroTelefono = numeroTelefono;
    }

    protected Telefono(Parcel in) {
        nombre = in.readString();
        numeroTelefono = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(numeroTelefono);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Telefono> CREATOR = new Creator<Telefono>() {
        @Override
        public Telefono createFromParcel(Parcel in) {
            return new Telefono(in);
        }

        @Override
        public Telefono[] newArray(int size) {
            return new Telefono[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
}
