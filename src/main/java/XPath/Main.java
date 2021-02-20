package XPath;

import dom_Alumnos.ControlData;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

/*
 *  @author Zemedu
 */
public class Main {

    //instancia de la clase Scanner para la introdución por teclado
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)  throws IOException{

        int opcion = -1;
        do {
            //"C:\\Users\\I B M\\Documents\\zemedu\\Dom_01"
            File[] listarFicheros = LectorFichero.listarFicheros(".",".xml");
            int posicion = 0;
            while(opcion == -1) {
                for (int i = 0; i < listarFicheros.length; i++) {
                    System.out.println(i + " ->  " + Arrays.asList(listarFicheros).get(i));
                }

                System.out.println("Dime cual de estos ficheros quieres ver");
                opcion = ControlData.lerInt(sc);
                if (opcion < listarFicheros.length && opcion > 0) {
                    for (int i = 0; i < listarFicheros.length; i++) {
                        if (opcion == i){
                            System.out.println("Fichero: " + Arrays.asList(listarFicheros).get(i));
                            posicion = i;
                        }
                    }
                } else {
                    opcion = -1;
                    System.out.println("Error al indicar el fichero");
                }
            }
            opcion = Menu.menu(sc);
                //opcion = Menu.menu(sc);
            File fichero = Arrays.asList(listarFicheros).get(posicion);
            if (opcion == 1){
                //Mostrar el contenido del fichero
                leerXML(fichero);
            }
            if (opcion == 2 || opcion == 3){
                try {
                    ConsultasXPath.cunsultas(fichero,sc);
                } catch (ParserConfigurationException e) {
                    System.out.println("Error 2:");
                    e.printStackTrace();
                } catch (SAXException e) {
                    System.out.println("Error 3:");
                    e.printStackTrace();
                } catch (XPathException e) {
                    System.out.println("Error 4:");
                    e.printStackTrace();
                }
            }

        }while(opcion!=4);

    }

    static void leerXML(File fichero){

        Source sourceDOM = new StreamSource(fichero);
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            Result consola = new StreamResult(System.out);
            transformer.transform(sourceDOM, consola);

        } catch (TransformerException e) {
            System.out.println("Error número 2 al sacar el XML (mostrarXML) -> "+e.toString());
        }
    }
}
