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
public class ListaPronosticos {

    private List<Pronostico> pronosticos;
    private String nombreArchivo;

    public ListaPronosticos() {
        pronosticos = new ArrayList<>();
        nombreArchivo = "pronosticos.csv";
    }

    public ListaPronosticos(List<Pronostico> pronosticos, String nombreArchivo) {
        this.pronosticos = pronosticos;
        this.nombreArchivo = nombreArchivo;
    }

    void cargarDesdeBaseDatos(ListaPartidos partidos) {

        //Carga de database SQLite
        //establecemos el objeto Connection
        Connection conn = null;
        try {
            // Establecer una conexión
            conn = DriverManager.getConnection("jdbc:sqlite:pronosticos.db");
            // Crear el obj comando
            Statement stmt = conn.createStatement();
            String SQL = "SELECT idPronostico, idParticipante, idPartido, idEquipo, resultado   FROM pronosticos";

            // tomamos las filas y se guardan en el obj rs
            ResultSet rs = stmt.executeQuery(SQL);

            //recorremos el rs
            while (rs.next()) {

                // construír el objeto con datos leídos
                int idPronostico = rs.getInt("idPronostico");
                int idParticipante = rs.getInt("idParticipante");
                int idPartido = rs.getInt("idPartido");
                int equipo_nro = rs.getInt("idEquipo");
                String resultado = rs.getString("resultado");

                //obtenemos el partido del pronóstico
                Partido partido = partidos.getPartidoById(idPartido);

                //obtenemos el equipo de apuesta del pronóstico
                Equipo equipo = null;
                if (equipo_nro == 1) {
                    equipo = partido.getEquipo1();
                } else {
                    equipo = partido.getEquipo2();
                }
                
                //formamos el objeto pronóstico
                Pronostico pronostico = new Pronostico(
                        idPronostico,
                        idParticipante,
                        equipo,
                        partido,
                        resultado.charAt(0));
                
                //agregar a la lista de objetos
                this.addPronostico(pronostico);
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

   

    String listar() {
        String lista = "";
        for (Pronostico pronostico : this.getPronosticos()) {
            lista += "\n" + pronostico;
        }
        return lista;
    }
//--------------------------------------------------------

//--------------------------------------------------------    
    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(List<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    void addPronostico(Pronostico p) {
        this.pronosticos.add(p);
    }

    public Pronostico getPronosticoById(int idPronostico) {
        Pronostico pronostico = null;
        for (Pronostico p : this.getPronosticos()) {
            if (p.getIdPronostico() == idPronostico) {
                pronostico = p;
                break;
            }
        }

        return pronostico;
    }

    @Override
    public String toString() {
        return "ListaPronosticos{" + "pronosticos=" + pronosticos + ", nombreArchivo=" + nombreArchivo + '}';
    }

}
