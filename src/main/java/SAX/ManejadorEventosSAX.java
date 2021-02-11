package SAX;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ManejadorEventosSAX extends DefaultHandler {

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Comienza el parser SAX del documento XML");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("  Inicio de un elemento: " + qName);
        if (localName.toString() !=null){
            for (int i=0; i < attributes.getLength(); i++)
            System.out.println("                             Con el "+attributes.getQName(i)+": "+attributes.getValue(i));

        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!String.valueOf(ch,start,length).isEmpty()) {
            System.out.print("                          "+String.valueOf(ch,start,length)+"\n");

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("  FIN del elemento "+ qName);
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Finaliza el parser SAX del documento XML");

    }
}
