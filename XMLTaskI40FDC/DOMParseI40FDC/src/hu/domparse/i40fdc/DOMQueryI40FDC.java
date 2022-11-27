package hu.domparse.i40fdc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryI40FDC {
	
	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLi40fdc.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = factory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Második helyezettek nyereményei: \n");
			Competition(doc);
			System.out.println("----------------------------");
			
			System.out.println("\nShar pei tenyesztok: \n");
			SharPeiBreeder(doc);
			System.out.println("----------------------------");
			
			System.out.println("\n30 ev feletti gazdik: \n");
			OlderThanThirty(doc);
			
		} catch(ParserConfigurationException | IOException | SAXException ex){
			System.out.println("Some error happened:\n"+ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private static String NormalizeText(String text) {
		text=text.replaceAll("\\n", ", ");
		text=text.replaceAll("\\s+", " ");
		return text;
	}
	
	//masodik helyen vegzettek nyeremenyei
	private static void Competition(Document doc) {
		NodeList tenyesztok = doc.getElementsByTagName("reszvetel");
		for(int i = 0; i < tenyesztok.getLength(); i++) {
			Element elem =(Element)tenyesztok.item(i);
			NodeList childNodes = elem.getChildNodes();
			for(int j =0;j<childNodes.getLength();j++) {
				Node childNode = childNodes.item(j);
				if(childNode.getNodeName().equals("helyezes")) {
					if(Integer.parseInt(childNode.getTextContent())==2) {
						PrintElement(elem);
					}
				}
			}
		}
	}
	
    //shar pei tenyeszto
	private static void SharPeiBreeder(Document doc) {
		NodeList tenyesztok = doc.getElementsByTagName("tenyeszto");
		for(int i = 0; i < tenyesztok.getLength(); i++) {
			Element elem =(Element)tenyesztok.item(i);
			NodeList childNodes = elem.getChildNodes();
			for(int j =0;j<childNodes.getLength();j++) {
				Node childNode = childNodes.item(j);
				if(childNode.getNodeName().equals("fajta")) {
					if(childNode.getTextContent().equals("Shar Pei")) {
						PrintElement(elem);
					}
				}
			}
		}
	}
	
	//30 ev feletti gazdik
	private static void OlderThanThirty(Document doc) {
		NodeList gazdik = doc.getElementsByTagName("gazdi");
		for(int i = 0; i < gazdik.getLength(); i++) {
			Element elem =(Element)gazdik.item(i);
			NodeList childNodes = elem.getChildNodes();
			for(int j =0;j<childNodes.getLength();j++) {
				Node childNode = childNodes.item(j);
				if(childNode.getNodeName().equals("kor")) {
					if(Integer.parseInt(childNode.getTextContent())>=30) {
						PrintElement(elem);
					}
				}
			}
		}
	}
	
	//adatok kiirasa konzolra
	private static void PrintElement(Element elem) {
		String tid = elem.getAttribute("tid");
		String gid = elem.getAttribute("gid");
		
		if(tid != "") {
			System.out.println("ID: "+ tid);
		} else if(gid != "") {
			System.out.println("ID: "+ gid);
		}
		
		String nodeContent="";
		NodeList childNodes = elem.getChildNodes();
		for(int j =0;j<childNodes.getLength() ; j++) {
			if(childNodes.item(j).getTextContent().trim()!="") {
				nodeContent = NormalizeText(childNodes.item(j).getTextContent().trim());
				System.out.println(childNodes.item(j).getNodeName()+": "+nodeContent);
			}
		}
	}
	
}
