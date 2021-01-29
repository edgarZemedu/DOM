package dom_Alumnos;

import org.w3c.dom.Document;

import java.io.File;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
     static Scanner sc = new Scanner(System.in); //instancia de la clase Scanner para la introdución por teclado

    public static void main(String[] args){
        Document documento = null; //para manejar el DOM
        String nombreFichero = ""; //para alamcenar el nombre del fichero xml
        boolean fin = false; //para controlar la salida del programa
        File ficheroObjetos = null; //para almacenar la instancia del fichero serializado de objetos

        do {
            switch (menu()){
                case 1:
                    System.out.println("Introduzca el nombre del fichero  *.dat: ");
                    nombreFichero = ControlData.lerString(sc);
                    ficheroObjetos = ManejoFicheros.crearFS(nombreFichero + ".dat");
                    break;
                case 2:
                    System.out.println("Introduzca el nombre del fichero  *.dat: ");
                    nombreFichero = ControlData.lerString(sc);
                    //if (!Objects.isNull(ficheroObjetos) && ficheroObjetos.exists())
                        ManejoFicheros.leerFS(nombreFichero);
                    //else
                    //    System.out.println("No hay nada con ese nombre asi que, ponte a crear primero el fichero");
                    break;

            }

        }while(!fin);

    }
    /*La clase Menu permite imprimir el menú a partir de los datos de un ArrayList<String>
           y utilizar métodos para control de rango*/
    static byte menu() {
        byte opcion;
        boolean correcta;
        System.out.println("\n\n**********************************************************************");
        /* Solo sale del While cuando se selecciona una opción correcta en rango y tipo*/
        do {
            ArrayList<String> misOpciones = new ArrayList<String>() {
                {
                    add("Crear un fichero secuencial de objetos de tipo Alumno");
                    add("Mostrar los datos de los objetos del fichero Alumno");
                    add("Crear el arbol DOM a partir del fichero de objetos Alumno");
                    add("Mostrar el contenido del árbol DOM en pantalla en formato XML");
                    add("Crear un fichero XML a partir del DOM creado anteriormente ");
                    add("Recorrer el árbol DOM conociendo los tags/etiquetas de los elementos");
                    add("Recorrer el árbol DOM conociendo solo conociendo los niveles");
                    add("Esta vez in conocer nada de la estructura del mismo. Método recursivo");
                    add("Fin");
                }
            };
            for (int i = 1; i <= misOpciones.size(); i++){
                System.out.println(i + ".- " + misOpciones.get(i-1));
            }
            /*La clase ControlData permite hacer un control de tipo leído*/
            opcion = ControlData.lerByte(sc);

            boolean enrango = true;
            if (opcion < 1 || opcion > misOpciones.size()) {
                enrango = false;
                System.out.print("\tERRO: debe introducir un valor dentro do rango de números posibles. "
                        + "\n\t\tVolva a introducir un número: \n");
            }
            correcta = enrango;

        } while (!correcta);

        return opcion;
    }

}
