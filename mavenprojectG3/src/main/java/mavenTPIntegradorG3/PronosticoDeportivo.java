/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maventpintegradorg3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Grupo 3
 */

public class PronosticoDeportivo {
    
    ListaEquipos equipos = new ListaEquipos();
    ListaPartidos partidos = new ListaPartidos();
    ListaPronosticos pronosticos = new ListaPronosticos();
    ListaParticipantes participantes = new ListaParticipantes();
    ArrayList<Participante> ParticipantesOrdenados = new ArrayList<>();
    
    
    public void play() {
        
        equipos.cargarDesdeBaseDatos();

        
        partidos.cargarDesdeBaseDatos(equipos);

        
        pronosticos.cargarDesdeBaseDatos(partidos);
    
        participantes.cargarDesdeBaseDatos(pronosticos);

        equipos.listarEquipos();

           
        participantes.listarNombres();
           
        partidos.listarEquiposSimple();
           
     
        PronosticosParticipantes();
           
        partidos.listarConGoles();
           

        
       ListarFichas();
       
       listarOrdenado();
       
    }

public void ListarFichas(){

List<Participante> ordenada = participantes.getListaByPuntaje();
    
        System.out.println("\n");
        System.out.println("                           Aciertos por Apostador                      ");
        System.out.println("             \n");
        System.out.println("-----------------------------------------------------------------------");
        
        System.out.println("");
        
        for (Participante p : ordenada) {
        System.out.print("\n");
        //System.out.print("│"+"N° Par: "+p.getIdParticipante()+cl2(8,String.valueOf(p.getIdParticipante())  ) +"│ NOMBRE: "+p.getNombre()+cl2(32,p.getNombre())+  "│ PUNTAJE: "+ p.getPuntaje()+ cl2(25,Integer.toString(p.getPuntaje()))  +"│"+"\n");
        System.out.print(  " Apostador: (" + p.getIdParticipante()+") "+p.getNombre() +"\n");  //"│ PUNTAJE: "+ p.getPuntaje()+ cl2(25,Integer.toString(p.getPuntaje()))  +"│"+"\n");
        System.out.print("--------------------------------------------------------------------------------------------\n");
        System.out.print("          Equipo 1           Goles           Equipo 2                    Apostó     Puntos \n");
        System.out.print("-----------------------------------------------------------------------------------------------\n");
          


              for (Pronostico pronostico : p.getPronosticos()) {
                
                String equipo = pronostico.getEquipo().getNombre();
                char resultado_pronosticado = pronostico.getResultado();
                String equipo1 = pronostico.getPartido().getEquipo1().getNombre();
                int golesEq1 = pronostico.getPartido().getGolesEquipo1();
                String equipo2 = pronostico.getPartido().getEquipo2().getNombre();
                int golesEq2 = pronostico.getPartido().getGolesEquipo2();
                String acierto = null;
                
                System.out.print(" " + equipo1 + MavenTpIntegradorG3.GenCar(25," ",equipo1)+
                "  "+String.valueOf(golesEq1)+MavenTpIntegradorG3.GenCar(2," ", String.valueOf(golesEq1))+
                        "  "+String.valueOf(golesEq2)+ MavenTpIntegradorG3.GenCar(2," ",String.valueOf(golesEq2)) +" " +
                " "+equipo+MavenTpIntegradorG3.GenCar(25," ",equipo)+equipo2+MavenTpIntegradorG3.GenCar(25," ",equipo2)+
                " "+ String.valueOf(resultado_pronosticado)+"  "+


                        
                  String.valueOf(Calp(equipo1,equipo2,equipo,resultado_pronosticado,golesEq1,golesEq2) ) +
            MavenTpIntegradorG3.GenCar(2, " ", String.valueOf(Calp(equipo1,equipo2,equipo,resultado_pronosticado,golesEq1,golesEq2) ) ) +

                        " "+"\n");
            }
            System.out.print("-----------------------------------------------------------------------------------------------\n");
            System.out.print("│                                                                 Total Puntos : "+ p.getPuntaje()+" \n");
            System.out.print("-----------------------------------------------------------------------------------------------\n");
            
        pausar(100);
        }   
  
    }


    public void cargarParaOrdenar() {//cargar para ordenar
        
        Participante parti;
        int x = 0;
        int lon = participantes.getParticipantes().size();
        for (x = 0; x < lon; x++) {
            
            int id = participantes.getParticipantes().get(x).getIdParticipante();
            String nom = participantes.getParticipantes().get(x).getNombre();
            int pun = participantes.getParticipantes().get(x).getPuntaje();
            parti = new Participante(id, nom, null, pun);
            
            ParticipantesOrdenados.add(parti);
            
        }
    }

    public void listarOrdenado() {
 
        List<Participante> ordenada = participantes.getListaByPuntaje();
        
        int x = 0;
        int lon = ordenada.size();
        System.out.println("");
        //System.out.println("-------------------------------------------------------------------");
        System.out.println("                      Ranking de Apuestas Positivas                ");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("             Puntos                 Apostador                      ");
        System.out.println("-------------------------------------------------------------------");

        for (x = 0; x < lon; x++) {//for

        System.out.println ( "           "+ordenada.get(x).getPuntaje() + 
                               "                ( "+  ordenada.get(x).getIdParticipante()+ " ) " +
                                  ordenada.get(x).getNombre() 
                                  );

        }

        
        
    }


    public void PronosticosParticipantes() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("        Apostador          Id.Pron.                            Partido                                       Equipo Referencia  Pronóstico  " );
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        int t = pronosticos.getPronosticos().size();
        
        for (int x = 0; x < t; x++) {
            System.out.print(" ( " + pronosticos.getPronosticos().get(x).getIdParticipante() + " ) " 
                    + MavenTpIntegradorG3.GenCar( 8, " ", " ( " + pronosticos.getPronosticos().get(x).getIdParticipante()+ " ) ")  
                    + participantes.getParticipantes().get(pronosticos.getPronosticos().get(x).getIdParticipante() - 1).getNombre()
                    + MavenTpIntegradorG3.GenCar(20, " ",(participantes.getParticipantes().get(pronosticos.getPronosticos().get(x).getIdParticipante() - 1).getNombre())) + "   "
                    + pronosticos.getPronosticos().get(x).getIdPronostico()        
                    + MavenTpIntegradorG3.GenCar(10, " ",Integer.toString(pronosticos.getPronosticos().get(x).getIdPronostico() ))
                    +"    "
                    +" " + pronosticos.getPronosticos().get(x).getPartido().getIdPartido() + "  "
                    + //nombre del equipo pronosticado        
                      //esta parte es de los equipo1 vs equipo2
                    pronosticos.getPronosticos().get(x).getPartido().getEquipo1().getNombre()
                    + MavenTpIntegradorG3.GenCar(25, " ", pronosticos.getPronosticos().get(x).getPartido().getEquipo1().getNombre())
                    + " Vs    "// + MavenTpIntegradorG3.GenCar(27, " ",pronosticos.getPronosticos().get(x).getPartido().getEquipo2().getNombre())
                    + pronosticos.getPronosticos().get(x).getPartido().getEquipo2().getNombre()
                    + MavenTpIntegradorG3.GenCar(25, " ",pronosticos.getPronosticos().get(x).getPartido().getEquipo2().getNombre())
                    +" " + pronosticos.getPronosticos().get(x).getEquipo().getNombre()
                    + MavenTpIntegradorG3.GenCar(25, " ",pronosticos.getPronosticos().get(x).getEquipo().getNombre())
                    + "  " + pronosticos.getPronosticos().get(x).getResultado() + "  "
                    + " "
                    + "\n");//
            
       
             }
         }



public char Calp(String equi1,String equi2, String apuEqui, char res_pro,int golEqui1,int golEqui2){

    if(golEqui1==golEqui2 && res_pro == 'E')
   {
    return '1';   
     }
 if(golEqui1>golEqui2 && equi1.equals(apuEqui)&&res_pro=='G'||
    golEqui1>golEqui2 && equi2.equals(apuEqui)&&res_pro=='P'||
 
    golEqui1<golEqui2 && equi1.equals(apuEqui)&&res_pro=='P'||
    golEqui1<golEqui2 && equi2.equals(apuEqui)&&res_pro=='G'  )
    {
    return '1'; 
    }
     
 return '-';
 }

public void pausar(long tiempo){
    try {
		
				Thread.sleep(tiempo);
		
		}catch(Exception e) {
			System.out.println(e);
		}
	
}    

}

