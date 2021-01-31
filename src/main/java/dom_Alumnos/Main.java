package dom_Alumnos;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.ObjectInputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in); //instancia de la clase Scanner para la introdución por teclado
/*    static ArrayList<Alumno> listAlumnos = new ArrayList<>();
    static Alumno a = null;*/

    public static void main(String[] args){
        Document documento = null; //para manejar el DOM
        String nombreFichero = ""; //para alamcenar el nombre del fichero xml
        boolean fin = false; //para controlar la salida del programa
        File ficheroObjetos = null; //para almacenar la instancia del fichero serializado de objetos
        ArrayList<Alumno> listAlumnos = null;

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
                        ManejoFicheros.leerFS(nombreFichero);
                    break;
                case 3:
                        crearArbolDOM(listAlumnos,documento);
                        System.out.println("Éxito!!!! Se ha creado el arbol DOM *******");
                    break;
                case 4:
                        mostradorXML(listAlumnos,documento);
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
    static Document crearDOM(ArrayList<Alumno> listAlumnos){
        //primero añadimos datos al ArrayList<Alumno>
        listAlumnos = ManejoFicheros.añadirDatos();
        //Elementos a instanciar
        DocumentBuilderFactory factoryDocument = DocumentBuilderFactory.newInstance();
        DocumentBuilder builderDocument = null;
        try {
            builderDocument = factoryDocument.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error numero 1 al crear árbol DOM "+e.toString());
        }
        DOMImplementation implementacionDOM = builderDocument.getDOMImplementation();
        //documento raiz Alumnos
        Document documento = implementacionDOM.createDocument(null, "Alumnos",null);
        //Elemento hijo Alumno y sus atributos(curso)

        for (int i = 0; i < listAlumnos.size(); i++) {
            //añadir elemntos *alumno*
            Element elemento = documento.createElement("Alumno");
            documento.getDocumentElement().appendChild(elemento);
            //añadir el atributo *curso*
            añadirAtributos(documento, elemento, "Alumno", listAlumnos.get(i).getCurso());
            //añadimos los subElementos de *alumno* con su valor
            crearElementos(documento,elemento,"nombre",listAlumnos.get(i).getNombre());
            crearElementos(documento,elemento,"apellidos",listAlumnos.get(i).getApellidos());
            crearElementos(documento,elemento,"edad",(String.valueOf(listAlumnos.get(i).getEdad())));

        }
        return  documento;
    }

    static void crearArbolDOM(ArrayList<Alumno> listAlumnos,Document documento){
        documento = crearDOM(listAlumnos);
        Source sourceDOM = new DOMSource(documento);
        Result resultado = new StreamResult(new File("Alumnos.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(sourceDOM, resultado);
        } catch (TransformerException e) {
            System.out.println("Error número 2 al crear árbol DOM "+e.toString());
        }

    }

    //para crear elementos básicos sin atributos
    static void crearElementos(Document documento,Element elemento,String nombre,String valor){

        Element e = documento.createElement(nombre);
        elemento.appendChild(e);
        Text texto = documento.createTextNode(valor);
        e.appendChild(texto);

    }
    //para añadir los atributos de *Alumno*
    static void añadirAtributos(Document documento,Element elemento,String nombre,String valor){

        Attr atri = documento.createAttribute(nombre);
        atri.setValue(valor);
        elemento.setAttributeNode(atri);

    }
    //mostrar el contenido del árbol DOM
    static void mostradorXML(ArrayList<Alumno> listAlumnos,Document documento) {
        documento = crearDOM(listAlumnos);
        Source sourceDOM = new DOMSource(documento);
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            Result consola = new StreamResult(System.out);
            transformer.transform(sourceDOM, consola);

        } catch (TransformerException e) {
            System.out.println("Error número 2 al sacar el XML "+e.toString());
        }
    }

}




