package com.example.app.models;

import java.util.ArrayList;

public class Menu {

    private int codigoMenu;
    private String nombreMenu;
    private double precioUnitario;

    public Menu(int codigoMenu, String nombreMenu, double precioUnitario) {
        this.codigoMenu = codigoMenu;
        this.nombreMenu = nombreMenu;
        this.precioUnitario = precioUnitario;
    }

    public int getCodigoMenu() {
        return codigoMenu;
    }

    public void setCodigoMenu(int codigoMenu) {
        this.codigoMenu = codigoMenu;
    }

    public String getNombreMenu() {
        return nombreMenu;
    }

    public void setNombreMenu(String nombreMenu) {
        this.nombreMenu = nombreMenu;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
