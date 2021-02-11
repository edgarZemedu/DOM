package SAX;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class Main {

    //Acceso a ficheros XML con SAX

    public static void main(String[] args){
        try{
            //creando el objeto procesador de tipo XMLReader
            SAXParser saxParser = SAXParserFactory.newDefaultInstance().newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            // Obtener una instancia de eventos de SAX
            ManejadorEventosSAX manejadorEventos = new ManejadorEventosSAX();

            //enlazar el parser con el manejador de eventos
            xmlReader.setContentHandler(manejadorEventos);

            //Crear el lector de ficheros para para el fichero xml
            InputSource lectorSource = new InputSource("personas.xml");

            //enlazar el parser con el fichero
            xmlReader.parse(lectorSource);

        } catch (SAXException | ParserConfigurationException | IOException ex) {
            System.out.println("Error 1: SAXParser");
            ex.printStackTrace();
        }
    }
}
