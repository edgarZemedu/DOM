
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
            
            Element elemento = documento.createElement("persona");
            documento.getDocumentElement().appendChild(elemento);
            int contadorId = 0;
            while (contadorId != 5) {   
            
            Attr attID=documento.createAttribute("id");
                attID.setValue(String.valueOf(contadorId));
                elemento.setAttributeNode(attID);
            //Crear elemento con texto
            String nombre = "ANA";
            String elementoApellido = "Carlos"; 
            int eleEdad = 23;
            int eleDep = 001;
            double eleSalario = 2100; 
            
            addElementos(documento, elemento, "nombre", nombre);
            addElementos(documento, elemento, "apellido", nombre);
            addElementos(documento, elemento, "edad", String.valueOf(eleEdad));
            addElementos(documento, elemento, "dep", String.valueOf(eleDep));
            addElementos(documento, elemento, "salario", String.valueOf(eleSalario));
            
            
                contadorId++;                      
            }
            //Crear fichero XML a partir del documento DOM
            Source sourceDOM = new DOMSource(documento);
            Result resultado = new StreamResult(new File("personas.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer(); 
            transformer.transform(sourceDOM, resultado);
            
            //Mostrar el contenido del documento DOM por pantalla
            Result consola=new StreamResult(System.out);
            transformer.transform(sourceDOM, consola);

            
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void addElementos(Document documento,Element elementos,String hijo,String valor){
        
            elementos = documento.createElement(hijo);
                elementos.appendChild(elementos);
            Text valorNombre = documento.createTextNode(valor);
            elementos.appendChild (valorNombre);   

    
}
