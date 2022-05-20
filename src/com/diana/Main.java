package com.diana;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = null;
        try {
            System.out.println("Introduzca el nombre de archivo");
            String rutaRelativa=sc.nextLine();
            String pwd = System.getProperty("user.dir");//ruta directorio actual de trabajo
            String rutaAbsoluta = pwd + File.separator + rutaRelativa;

            File ficheroALeer = new File(rutaAbsoluta);
            sc = new Scanner(ficheroALeer);


        }catch (Exception e) {  //manejamos excepciones
            e.printStackTrace();
        } finally {
            sc.close();  //cerramos el scanner
        }
    }
}
