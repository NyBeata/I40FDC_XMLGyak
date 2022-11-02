package domi40fdc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomReadI40FDC {

	public static void main(String[] args) {
		
		File xmlFile = new File("usersI40FDC.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
				
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		
		org.w3c.dom.NodeList nList = doc.getElementsByTagName("user");
		
		for(int i=0; i<nList.getLength(); i++) {
			Node nNode = nList.item(i);
			System.out.println("\nCurrent Element: " + nNode.getNodeElement());
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				String uid = elem.getAttribute("id");
				
				Node node1 = elem.getElementByTagName("firsname").item(0);
				String fname = node1.getTextContext();
				
				Node node2 = elem.getElementByTagName("lastname").item(0);
				String lname = node2.getTextContext();
				
				Node node3 = elem.getElementByTagName("profession").item(0);
				String pname = node3.getTextContext();
				
				System.out.println("User id: " + uid);
				System.out.println("Firtname: " + fname);
				System.out.println("Lastname: " + lname);
				System.out.println("Profession: " + pname);
			}
		}
	}

}
