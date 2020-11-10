package com.marcosledesma.agendacontactos.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Direccion implements Parcelable {
    private String nombre;
    private String provincia;
    private String calle;
    private int numero;
    private int codigoPostal;

    public Direccion() {
    }

    public Direccion(String nombre, String provincia, String calle, int numero, int codigoPostal) {
        this.nombre = nombre;
        this.provincia = provincia;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
    }

    protected Direccion(Parcel in) {
        nombre = in.readString();
        provincia = in.readString();
        calle = in.readString();
        numero = in.readInt();
        codigoPostal = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(provincia);
        dest.writeString(calle);
        dest.writeInt(numero);
        dest.writeInt(codigoPostal);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Direccion> CREATOR = new Creator<Direccion>() {
        @Override
        public Direccion createFromParcel(Parcel in) {
            return new Direccion(in);
        }

        @Override
        public Direccion[] newArray(int size) {
            return new Direccion[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
