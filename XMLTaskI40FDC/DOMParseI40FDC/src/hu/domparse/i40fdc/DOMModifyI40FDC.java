package hu.domparse.i40fdc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyI40FDC {

public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		try {
			//file nev megadasa, parszolas
			File inputFile = new File("XMLi40fdc.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(inputFile);
	        
			//a masodik es a harmadik kutya adatainak mentese
			Node kutya2 = doc.getElementsByTagName("kutya").item(1);
			Node kutya3 = doc.getElementsByTagName("kutya").item(2);
			//gyokerelem mentese
			Node ebtenyesztes = doc.getFirstChild();
	        
			//kutya ID modositasa
			NamedNodeMap attr = kutya2.getAttributes();
			Node nodeAttr = attr.getNamedItem("kid");
			nodeAttr.setTextContent("k7");
	         
			//a masodik kutya nevenek modositasa
			NodeList list = kutya2.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					if ("nev".equals(elem.getNodeName())) {
						if("Anastasia".equals(elem.getTextContent())) {
							elem.setTextContent("Snowglobe");  
						}
					}
				}
			}
	        
			//a harmadik kutya egyik felmenojenek modositasa
			NodeList list1 = kutya3.getChildNodes();
			for (int i = 0; i < list1.getLength(); i++) {
				Node node = list1.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					if ("felmeno".equals(elem.getNodeName())) {
						if("Shira Victory Dolce Vita".equals(elem.getTextContent())) {
							elem.setTextContent("Shira Victory Sunshine");  
						}
					}
				}
			}
	        
			//oltasi konyvek torlese
			NodeList childNodes = ebtenyesztes.getChildNodes();
			for(int i = 0; i < childNodes.getLength(); i++) {
				Node node = childNodes.item(i);
	            
				if("oltasi_konyv".equals(node.getNodeName()))
					ebtenyesztes.removeChild(node);
			}
	        
	        //megjelenites a consolon
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			System.out.println("-----------New File-----------");
			StreamResult consoleResult = new StreamResult(System.out);
			transformer.transform(source, consoleResult);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

}
