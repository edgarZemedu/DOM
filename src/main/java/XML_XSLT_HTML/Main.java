package XML_XSLT_HTML;


import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 *  @author Zemedu
 */
public class Main {

    public static void main(String[] args)  {

        String hojaEstilo = "personasXSLT.xslt";
        String ficheroXML = "personas.xml";
        File paginaHTML = new File ("miSuperWeb.html");

        //Crear un flujo hacia el File de la página Web que quiero crear
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(paginaHTML);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Crear una fuente de datos a partir de la hoja de estilos
        Source estilos = new StreamSource(hojaEstilo);

        //Crear una fuente de datos a partir del fichero XML
        Source datos = new StreamSource(ficheroXML);

        /*Ya tenemos el streamde los ficheros origen (el fichero XML y el XSL)y del fichero destino (la página Web)*/
        //Crear el resultado a partir de flujo hacia el fichero HTML
        Result resultado = new StreamResult(fileOutputStream);

        //Ejecutar la transformación
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer(estilos);
            transformer.transform(datos,resultado);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        //Cerrar el stream
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
