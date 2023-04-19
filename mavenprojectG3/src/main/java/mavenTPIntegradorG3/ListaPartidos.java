/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maventpintegradorg3;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Grupo 3
 */
public class ListaPartidos {

    private List<Partido> partidos;
    private String nombreArchivo;

    public ListaPartidos() {
        this.partidos = new ArrayList<>();
        this.nombreArchivo = "partidos.csv";
    }

    public ListaPartidos(List<Partido> partidos, String nombreArchivo) {
        this.partidos = partidos;
        this.nombreArchivo = nombreArchivo;
    }

    public void addPartido(Partido p) {
        this.partidos.add(p);
    }

    public Partido getPartidoById(int idPartido) {
        Partido p = null;
        for (Partido partido : this.getPartidos()) {
            if (partido.getIdPartido() == idPartido) {
                p = partido;
                break;
            }
        }
        return p;
    }

    void cargarDesdeBaseDatos(ListaEquipos equipos) {

        
        Connection conn = null;
        try {
            // Establecer una conexión
            conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
            // Crear el obj comando
            Statement stmt = conn.createStatement();
            String SQL = "SELECT idPartido, idEquipo1, idEquipo2, golesEquipo1,"
                    + " golesEquipo2    FROM partidos";

            ResultSet rs = stmt.executeQuery(SQL);

            //recorremos el rs
            while (rs.next()) {

                // construír el objeto con datos leídos
                Equipo equipo1 = equipos.getEquipoById(rs.getInt("idEquipo1"));
                Equipo equipo2 = equipos.getEquipoById(rs.getInt("idEquipo2"));
                Partido partido = new Partido(
                        rs.getInt("idPartido"),
                        equipo1,
                        equipo2,
                        rs.getInt("golesEquipo1"),
                        rs.getInt("golesEquipo2"));

                
                this.addPartido(partido);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // conn close failed.
                System.out.println(e.getMessage());
            }
        }
    }
/// Aqui cargaba antes del archivo csv
 ///        Fin carga csv 
    
    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    String listar() {
        String lista = "";
        for (Partido partido : partidos) {
            lista += "\n" + partido;
        }
        return lista;
    }
/////////////////////////////////////////////////////
////////////////////////////////////////////////////

    public void listarEquiposSimple() {
        System.out.println("\n");
        
        System.out.println("                              Partidos                           ");
        System.out.println(" ----------------------------------------------------------------------------");
        
        System.out.println("  Nro.        Equipo 1                Vs                  Equipo 2           ");
        System.out.println("-----------------------------------------------------------------------------");
        


        int n = partidos.size();
        for (int x = 0; x < n; x++) {
            System.out.print("  " + partidos.get(x).getIdPartido() + "   "
                    + "  " + partidos.get(x).getEquipo1().getNombre() + cl(28, partidos.get(x).getEquipo1().getNombre())
                    + "  " + "  Vs.  " + "  "
                    + cl(28, partidos.get(x).getEquipo2().getNombre()) + partidos.get(x).getEquipo2().getNombre() + "  " + "\n");//

        }
        
    }

/////////////////////////////////////////////////////
/////////////////////////////////////////////////////    
    public void listarConGoles() {

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("                                                                           ");
        System.out.println("                              Resultados                                   ");
        System.out.println("                                                                           ");
      
        System.out.println("--------------------------------------------------------------------");
        System.out.println(" Nro.         Equipo 1               Goles             Equipo 2         ");
        System.out.println("--------------------------------------------------------------------");
        int t = partidos.size();
        for (int x = 0; x < t; x++) {
            System.out.print("  " + partidos.get(x).getIdPartido() + "   "
                    + "  " + partidos.get(x).getEquipo1().getNombre() + MavenTpIntegradorG3.GenCar(28, " ",partidos.get(x).getEquipo1().getNombre())
                    + "  " + partidos.get(x).getGolesEquipo1() + "   "
                    + partidos.get(x).getGolesEquipo2() + "   "
                    + MavenTpIntegradorG3.GenCar(4, " ",partidos.get(x).getEquipo2().getNombre()) + partidos.get(x).getEquipo2().getNombre() + "\n");//
            
        }
        
        System.out.println("   \n");
        System.out.println("   \n");
        
        
    
    }
//////////////////////////////////////////////////////
/////////////////////////////////////////////////////

    private String cl(int e, String l) {//cl

        String sal = "";
        int t = l.length();

        if (t < e) {//if

            t = e - t;
            for (int x = 0; x < t; x++) {//for
                sal += " ";
            }//for
        } else {//else
            return sal;
        }//else 
        return sal;
    }//cl

///////////////////////////////////////////////////// 
/////////////////////////////////////////////////////  
    @Override
    public String toString() {
        return "ListaPartidos{" + "partidos=" + partidos + ", nombreArchivo=" + nombreArchivo + '}';
    }

}
