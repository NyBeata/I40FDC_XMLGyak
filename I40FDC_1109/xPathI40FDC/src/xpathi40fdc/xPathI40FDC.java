package xpathi40fdc;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import org.xml.sax.SAXException;

/*	1c) feladat lekerdezesek
 * 
 *	1) /class/student
 *	2) //student[@id='02']
 *	3) //student
 *	4) /class/student[2]
 *	5) /class/student[last()]
 *	6) /class/student[last()-1]
 *	7) /class/student[position()<3]
 *	8) /class/*
 *	9) //student[@*]
 *	10) //*
 *	11) 
 *	12)
 */

public class xPathI40FDC {

	public static void main(String[] args) {
		
		try {
	         File inputFile = new File("studentI40FDC.xml");
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder;

	         dBuilder = dbFactory.newDocumentBuilder();

	         Document doc = dBuilder.parse(inputFile);
	         doc.getDocumentElement().normalize();

	         XPath xPath =  XPathFactory.newInstance().newXPath();

	         String expression = "/class/student";	        
	         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
	            doc, XPathConstants.NODESET);

	         for (int i = 0; i < nodeList.getLength(); i++) {
	            Node nNode = nodeList.item(i);
	            System.out.println("\nAktualis elem :" + nNode.getNodeName());
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               System.out.println("Student id : " + eElement.getAttribute("id"));
	               System.out.println("Keresztnev : " 
	                  + eElement
	                  .getElementsByTagName("keresztnev")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Vezeteknev : " 
	                  + eElement
	                  .getElementsByTagName("vezeteknev")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Becenev : " 
	                  + eElement
	                  .getElementsByTagName("becenev")
	                  .item(0)
	                  .getTextContent());
	               System.out.println("Kor : " 
	                  + eElement
	                  .getElementsByTagName("kor")
	                  .item(0)
	                  .getTextContent());
	            }
	         }
	      } catch (ParserConfigurationException e) {
	         e.printStackTrace();
	      } catch (SAXException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (XPathExpressionException e) {
	         e.printStackTrace();
	      }
		
	}

}
