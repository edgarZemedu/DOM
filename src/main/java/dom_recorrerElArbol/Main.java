
package dom_recorrerElArbol;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

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
            //1º Crear una nueva instancia de una fábrica de constructores de documentos a partir de la cual fabricamos un constructor de documentos, que procesará el XML.
            DocumentBuilder builderDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //2º Parsear/procesar el fichero *.xmly, como consecuencia, se crea un árbol DOM
            Document documento = builderDocument.parse(new File("personas.xml"));
            //3º Elimina nodos vacíos
            documento.getDocumentElement().normalize();

            System.out.println("Elemento raíz del documento XML: "+documento.getDocumentElement().getNodeName());
            NodeList listaP = documento.getElementsByTagName("persona");
            System.out.println("El nº de elementos de tipo persona en el DOM es "+ listaP.getLength());

            //para conertir en elemento el node
            for (int i=0; i < listaP.getLength(); i++){
                Node nodeP = listaP.item(i);
                if (nodeP.getNodeType() == Node.ELEMENT_NODE){
                    Element e = (Element) nodeP;
                    System.out.println(e.getTagName() + e.getTextContent());
                }
                if (nodeP.hasAttributes()){
                    NamedNodeMap listaAtributos = nodeP.getAttributes();
                    for (int j=0; j < listaAtributos.getLength(); j++){
                        System.out.println(listaAtributos.item(j).getNodeName());
                        System.out.println(listaAtributos.item(j).getNodeValue());
                    }
                }
                if (nodeP.hasChildNodes()){
                    NodeList listaNode = nodeP.getChildNodes();
                    for (int j=0; j < listaNode.getLength(); j++){
                        Node n = listaNode.item(j);
                        System.out.println(n.getNodeName()+": "+n.getTextContent());
                        // Hay otra forma de acceder al texto de una etiqueta teniendo en cuenta de que el texto es también un hijo del elemento:
                        //System.out.println(n.getFirstChild().getNodeValue());
                    }
                }
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
    
}
