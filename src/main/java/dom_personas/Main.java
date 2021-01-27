
package dom_personas;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author a18zemedufc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {

        try { 
            //Elementos a instanciar
            DocumentBuilderFactory factoryDocument = DocumentBuilderFactory.newInstance();
            DocumentBuilder builderDocument = factoryDocument.newDocumentBuilder();
            DOMImplementation implementacionDOM = builderDocument.getDOMImplementation();
            
            //Crear nodo raíz de un documento XML
            Document documento = implementacionDOM.createDocument (null, "personas",null);
            documento.setXmlVersion("1.0");
            

            int contadorId = 0;
            /*********************persona id = 0***********************/
                Element elemento1 = documento.createElement("persona");
                documento.getDocumentElement().appendChild(elemento1);

                Attr id1=documento.createAttribute("id");
                id1.setValue(String.valueOf(contadorId++));
                elemento1.setAttributeNode(id1);

                //Crear elemento con texto
                String nombre = "ANA";
                String elementoApellido = "Carlos";
                int eleEdad = 23;
                int eleDep = 40;
                double eleSalario = 2100;

                addElementos(documento, elemento1, "nombre", nombre);
                addElementos(documento, elemento1, "apellido", elementoApellido);
                addElementos(documento, elemento1, "edad", String.valueOf(eleEdad));
                addElementos(documento, elemento1, "dep", String.valueOf(eleDep));
                addElementos(documento, elemento1, "salario", String.valueOf(eleSalario));


            /**********************persona id = 1**********************/
            Element elemento2 = documento.createElement("persona");
            documento.getDocumentElement().appendChild(elemento2);

            Attr id2=documento.createAttribute("id");
            id2.setValue(String.valueOf(contadorId++));
            elemento2.setAttributeNode(id2);

            //Crear elemento con texto
            String nombr = "PAN";
            String apellidos = "Pin";;
            int dep = 45;;
            int edad = 32;
            double salario = 2500.0;

            addElementos(documento, elemento2, "nombre", nombr);
            addElementos(documento, elemento2, "apellido", apellidos);
            addElementos(documento, elemento2, "edad", String.valueOf(edad));
            addElementos(documento, elemento2, "dep", String.valueOf(dep));
            addElementos(documento, elemento2, "salario", String.valueOf(salario));


            /**********************persona id = 2**********************/
            Element elemento3 = documento.createElement("persona");
            documento.getDocumentElement().appendChild(elemento3);

            Attr id3 = documento.createAttribute("id");
            id3.setValue(String.valueOf(contadorId++));
            elemento3.setAttributeNode(id3);

            //Crear elemento con texto
            String nomb = "TOM";
            String apellido = "Rois";
            int depa = 45;;
            int eda = 2;
            double salari = 2200.0;

            addElementos(documento, elemento3, "nombre", nomb);
            addElementos(documento, elemento3, "apellido", apellido);
            addElementos(documento, elemento3, "edad", String.valueOf(eda));
            addElementos(documento, elemento3, "dep", String.valueOf(depa));
            addElementos(documento, elemento3, "salario", String.valueOf(salari));


            /**********************persona id = 3**********************/
            Element elemento4 = documento.createElement("persona");
            documento.getDocumentElement().appendChild(elemento4);

            Attr id4 = documento.createAttribute("id");
            id4.setValue(String.valueOf(contadorId++));
            elemento4.setAttributeNode(id4);

            //Crear elemento con texto
            String nom = "JUAN";
            String apellid = "TIN";
            int depar = 40;;
            int edade = 29;
            double salar = 2000.50;

            addElementos(documento, elemento4, "nombre", nom);
            addElementos(documento, elemento4, "apellido", apellid);
            addElementos(documento, elemento4, "edad", String.valueOf(edade));
            addElementos(documento, elemento4, "dep", String.valueOf(depar));
            addElementos(documento, elemento4, "salario", String.valueOf(salar));

            contadorId = 0;

            //Crear fichero XML a partir del documento DOM
            Source sourceDOM = new DOMSource(documento);
            Result resultado = new StreamResult(new File("personas.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer(); 
            transformer.transform(sourceDOM, resultado);
            
            //Mostrar el contenido del documento DOM por pantalla
            Result consola=new StreamResult(System.out);
            transformer.transform(sourceDOM, consola);

            contadorId = 0;
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void addElementos(Document documento,Element elementos,String hijo,String valor) {

        Element e = documento.createElement(hijo);
        elementos.appendChild(e);
        Text texto = documento.createTextNode(valor);
        e.appendChild(texto);

    }
    
}
