package com.diana;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    private static final DecimalFormat df = new DecimalFormat("0.00");// para limitar el precio final a solo 2 decimales
     //#################################################################
    //funcion para sacar la temperatura maxima y minima de un arraylist
    public static void tempAltaBaja(ArrayList listaTemperaturas) {
        try {
            //ordenamos el arraylist
            Collections.sort(listaTemperaturas);
            //sacamos la temparatura minima (la primera posicion)
            String valMin = (String) listaTemperaturas.get(0);
            //sacamos la temparatura maxima (ultima posicion)
            String valMax = (String) listaTemperaturas.get(listaTemperaturas.size() - 1);
            System.out.println("Temperatura mas alta y mas baja:  " + valMax + "  " + valMin);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //#################################################################
    //funcion que calcula la temperatura media de un arraylist de valores(temperaturas)
    public static double tempMedia(ArrayList listaTemperaturas) {
        double media = 0;
        double sum = 0;
        try {
            for (int i = 0; i < listaTemperaturas.size(); i++)
                sum += Double.valueOf((String) listaTemperaturas.get(i));
            media = sum / listaTemperaturas.size();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Double.parseDouble(df.format(media));
    }

    //#################################################################
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        try {
            //declaramos las variables
            double tempIntermedia = 0;
            double mayorDiferencia = 0;
            ArrayList<String> todasTempMax = new ArrayList<String>();
            ArrayList<String> todasTempMin = new ArrayList<String>();
            ArrayList<String> todasDiferencias = new ArrayList<String>();


            //pedimos el archivo a leer
            System.out.println("Introduzca el nombre de archivo");
            String rutaRelativa = lector.nextLine();
            String pwd = System.getProperty("user.dir");//ruta directorio actual de trabajo
            String rutaAbsoluta = pwd + File.separator + "tenerife" + File.separator + rutaRelativa;

            File ficheroALeer = new File(rutaAbsoluta);
            Scanner sc = new Scanner(ficheroALeer);

            do {//vamos a leer el documento linea por linea
                String linea = sc.nextLine();
                String parte[] = linea.split(" ");//dividimos cada linea en 3 partes separadas por espacios
                //fecha = parte[0]; tempMax = parte[1]; tempMin = parte[2]
                double diferencia = Double.valueOf(parte[1]) - Double.valueOf(parte[2]);
                todasTempMax.add(parte[1]);//almacenamos todas las teperaturas maximas en un arraylist
                todasTempMin.add(parte[2]);//almacenamos todas las teperaturas minimas en un arraylist
                todasDiferencias.add(String.valueOf(diferencia));
            }
            while (sc.hasNextLine());
            sc.close();

            //monstramos por pantalla la informacion
            System.out.println("MAXIMA");
            tempAltaBaja(todasTempMax);
            System.out.println("--------------------------");
            System.out.println("MINIMA");
            tempAltaBaja(todasTempMin);
            System.out.println("--------------------------");
            System.out.println("Temperatura maxima media: " + tempMedia(todasTempMax));
            System.out.println("--------------------------");
            System.out.println("Temperatura minima media: " + tempMedia(todasTempMin));
            System.out.println("--------------------------");
            tempIntermedia = (tempMedia(todasTempMax) + tempMedia(todasTempMin)) / 2;
            System.out.println("Temperatura intermedia: " + df.format(tempIntermedia));
            System.out.println("--------------------------");
            Collections.sort(todasDiferencias, Collections.reverseOrder());
            mayorDiferencia = Double.parseDouble(todasDiferencias.get(0));
            System.out.println("Mayor diferencia de temperatura en un mismo dia: " + df.format(mayorDiferencia));


        } catch (Exception e) {  //manejamos excepciones
            e.printStackTrace();
        } finally {
            lector.close();  //cerramos el scanner
        }
    }
}
