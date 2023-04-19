/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maventpintegradorg3;

/**
 *@author Grupo 3 
 
 */
public class Equipo {
    private int idEquipo;
    private String Nombre;
    private String Descripcion;

    public Equipo(int idEquipo, String nombre, String descripcion) {
        this.idEquipo = idEquipo;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
    }

    public Equipo() {
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Equipo idEq=" + idEquipo + " " + Nombre;
    }
    
}
