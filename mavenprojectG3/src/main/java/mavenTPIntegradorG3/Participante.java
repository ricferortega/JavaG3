/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maventpintegradorg3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * @author Grupo 3
 */
public class Participante implements Comparable<Participante> {

    private int idParticipante;
    private String nombre;
    private List<Pronostico> pronosticos;
    private int puntaje;

    public Participante() {
        this.idParticipante = 0;
        this.nombre = null;
        this.pronosticos = new ArrayList<>();
        this.puntaje = 0;
    }

    public Participante(int Id, String nombre) {
        this.idParticipante = Id;
        this.nombre = nombre;
        this.pronosticos = new ArrayList<>();
        this.puntaje = 0;
    }

    public Participante(int idParticipante, String nombre, List<Pronostico> pronosticos, int puntaje) {
        this.idParticipante = idParticipante;
        this.nombre = nombre;
        this.pronosticos = pronosticos;
        this.puntaje = puntaje;
    }

    void addPronostico(Pronostico p) {
        this.pronosticos.add(p);
    }

    
    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(List<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    public int getPuntaje() {
        //calcula puntaje en base a sus pronósticos
        int puntos = 0;
        for (Pronostico pronostico : pronosticos) {

            //para cada pronóstico, establecer si hay acierto
            Equipo equipo = pronostico.getEquipo();
            Partido partido = pronostico.getPartido();
            char resultado_pronostico = pronostico.getResultado();
            char resultado_real = 'X';

            if (partido.getGolesEquipo1() == partido.getGolesEquipo2()) {
                resultado_real = 'E'; //empate

            } else {

                //uno de los equipos ganó
                if (equipo.equals(partido.getEquipo1())) {

                    //se apostó por el equipo 1
                    if (partido.getGolesEquipo1() > partido.getGolesEquipo2()) {
                        resultado_real = 'G'; //ganó equipo 1
                    } else {
                        resultado_real = 'P'; //perdió equipo 1
                    }
                } else {

                    //se apostó por el equipo 2
                    if (partido.getGolesEquipo2() > partido.getGolesEquipo1()) {
                        resultado_real = 'G'; //ganó equipo 2
                    } else {
                        resultado_real = 'P'; //perdió equipo 2
                    }
                }
            }

            if (resultado_real == resultado_pronostico) {

                //acierto, se suma un punto a l participante
                puntos++;
            }
        }
        return puntos;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Particip id=" + idParticipante + " " + nombre + " pronost=" + pronosticos + " puntaje=" + puntaje + '}';
    }

    @Override
    public int compareTo(Participante t) {
        if (this.getPuntaje() == t.getPuntaje())
           {
            return 0;
           } else {
                   if (this.getPuntaje() > t.getPuntaje())
                   {
                   return 1;
                   } else
                        {
                        return -1;
                        }
           }

//return t.puntaje - this.puntaje;
    }

}
