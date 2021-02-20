package XPath;

import dom_Alumnos.ControlData;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.Scanner;

public class ConsultasXPath {

     static void cunsultas(File fichero, Scanner sc) throws IOException,ParserConfigurationException,SAXException,XPathException{
         String consultaXpath = null;
         boolean correcta = true;

         //Crear una instancia de una factoryde constructores de documentos
         DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();

         //Crear un creador dedocumentoXML
         DocumentBuilder builder = builder= factory.newDocumentBuilder();

         Document documento = builder.parse(fichero);

         XPath xpath= XPathFactory.newInstance().newXPath();
         XPathExpression xpathExpression = null;
         do {
             System.out.println("Introduzca la consulta que dese realizar sobre el fichero XML. " +
                     "\n Introduzca un 0 para finalizar");
             consultaXpath = ControlData.lerString(sc);
             correcta = true;
             /*Creo un objeto XPathExpression que es capaz de almacenar una consulta de búsqueda XPath que se pasa como un String*/
             try {
                 xpathExpression = xpath.compile(consultaXpath);
             } catch (XPathExpressionException e) {
                 System.out.println("La expresión no es correcta, vuelva a introducirla ");
                 correcta = false;
             }
         } while (!correcta && consultaXpath != "0");

         //InputStream lector = new FileInputStream(fichero);
         NodeList consulta = (NodeList) xpathExpression.evaluate(documento,XPathConstants.NODESET);
         if (consulta.getLength()>0){
             System.out.println("El resultado de tu consultada es: ");
             for (int i=0;i<consulta.getLength();i++){
                 Node nodo = consulta.item(i);
                 nodoRecursivo(nodo);
                 if (nodo.getNodeType() == Node.ATTRIBUTE_NODE)
                     System.out.println("");
             }

         }else{
             System.out.println("No hay resultado para esta consulta");
         }


    }

    static void nodoRecursivo(Node nodo){

        switch (nodo.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("<xml version=\"1.0\">");
                Document doc = (Document) nodo;
                nodoRecursivo(doc.getDocumentElement());
                break;

            case Node.ELEMENT_NODE:
                Element elemento = (Element) nodo;
                String nombre = elemento.getNodeName();
                System.out.print("<" + nombre);
                NamedNodeMap atributos = elemento.getAttributes();
                if (atributos.getLength() != 0) {
                    for (int i = 0; i < atributos.getLength(); i++) {
                        nodoRecursivo(atributos.item(i));
                    }
                }

                NodeList hijos = nodo.getChildNodes();

                if (hijos.getLength()>0) {
                    System.out.println(">");
                    if (hijos != null) {

                        for (int i = 0; i < hijos.getLength(); i++) {
                            nodoRecursivo(hijos.item(i));
                        }
                    }

                    System.out.println("");
                    System.out.println("</" + nombre + ">");
                }else{
                    System.out.println("/>");
                }
                break;

            case Node.ATTRIBUTE_NODE:
                Attr atributo = (Attr) nodo;
                System.out.print(" " + atributo.getNodeName()
                        + " = " + atributo.getNodeValue());
                break;

            case Node.TEXT_NODE:
                Text texto = (Text) nodo;
                String elTexto = texto.getTextContent();
                System.out.print(elTexto.strip());
        }
    }
}
