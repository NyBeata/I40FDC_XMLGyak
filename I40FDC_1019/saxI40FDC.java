//plusz feladat old meg, hogy az adatok egymas melle (egy sorba keruljenek)!

package SaxI40FDC1019;

inport java.io.File;
import java.io.IOException;

import java.xml.parsers.ParserConfigurationException;
import java.xml.parsers.SAXParser;
import java.xml.parser.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxI40FDC {

	public static void main(String[] args){
		try{
			// Dokumentumolvaso letrehozosa 
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

			// peldanyositja a SAX ertelmezot 
			SAXParser saxParser = saxParserFactory.newSAXParser();

			// sajat esemenykezelo objektum letrehozasa
			SaxHandler handler = new SaxHandler();

			// dokumentum ertelmezesi folyamatanak elinditasa
			// a parse metodus elso parametere a beolvasando XML fajl neve
			//AZ .xml FAJLNAK A GYOKERBEN KELL LENNIE!!
			saxParser.parse(new File("NyB_kurzusfelvetel.xml"), handler);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}

//tartalomkezelo keret letrehozasa + esemeny- es hibakezelo metodus
class SaxHandler extends DefaultHandler {
	private int indent = 0;

	private String formatAttributes(Attributes attributes) {
		int attrLength = attributes.getLength();
		if(attrLength == 0) {
			return "";
		}
	StringBuilder sb = new StringBuilder(", {");
	for (int i = 0; i < attrLength; i++) {
		sb.append(attributes.getLocalName(i) + "=" + attributes.getValue(i));
		if(i < attrLength - 1) {
			sb.append(", ");
		}
	}
	sb.append("}");
	return sb.toString();
}

private void indent() {
	for (int i = 0; i < indent; i++) {
		System.out.print(" ");
	}
}


// esemeny kezelo metodusok letrehozasa, startElement metodus

//elem kezdete es vege
@Override
public void startElement(String uri, String localName, String qName, Attributes attributes) {
	indent++; //behuz mindent
	indent(); //start huzza be
	System.out.println(qName + formatAttributes(attributes) + " start");
}

//endElement metodust ujraimplementaljuk
@Override
public void endElement(String uri, String localName, String qName) {
	indent(); //end huzza be
	indent --; //behuzas csokkentese
	System.out.println(qName + " end");
}

//szovegelem feldolgozasa, characters metodust ujraimplementaljuk
@Override
public void characters(char ch[], int start, int length) {
	String chars = new String(ch, start, length).trim();
	if(!chars.isEmpty()) {
		indent++; //behuz mindent
		indent(); //karakter behuzas
		indent--; //behuzas csokkentese
		System.out.println(chars);
		}
	}
}