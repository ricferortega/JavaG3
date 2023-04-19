/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package maventpintegradorg3 ;



/**
 *
 * @author Grupo 3 
 */
public class MavenTpIntegradorG3 {

    public static PronosticoDeportivo PRODE;

    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("                   SIMULACION DE PRONOSTICOS DEPORTIVOS.          ");
        System.out.println("                                                                  ");
        
       
        
        PRODE = new PronosticoDeportivo();

        PRODE.play();
    }
    /*////////////////////////////////////////////////////////
    // Se Calcula los espacios en blanco para completar y tabular columnas
     // repite el caracter "car", tantas veces hasta llegar al valor de "param"
    // Menos la cantidad de caracteres de cadena
    // En Síntesis tabula espacios...
    /*////////////////////////////////////////////////////
    
       
    public static String GenCar(int param , String car, String cadena) {

        String carRep = "";
        int longCadena  = cadena.length();

        if (longCadena < param) {
            carRep = car.repeat(param - longCadena);
            
            }
        return carRep;
        }
}
