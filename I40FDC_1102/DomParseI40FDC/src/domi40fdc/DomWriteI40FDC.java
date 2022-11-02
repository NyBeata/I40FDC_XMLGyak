package domi40fdc;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DomWriteI40FDC {

	public static void main(String[] args) {
		
		DocumentBuilderFactory  factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder bulder = factory.newDocumentBuilder();
		
		org.w3c.dom.Document doc = builder.newDocument;
		
		org.w3c.dom.Element root = doc.createElementNS("DONi40fdc", "users");
		doc.appendChild(root);
		
		root.appendChild(createUser(doc, "1", "Nyíri", "Beáta", "Logisztikai progamozó"));
		root.appendChild(createUser(doc, "2", "Kiss", "Dávid", "Matematika tanár"));
		root.appendChild(createUser(doc, "3", "Nagy", "János", "Postás"));
		
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("(https://xml.apache.org/xslt) indent-amount", "2");
		
		DOMSource source = new DOMSource(doc);
		File myFile = new File("users2.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
		
		}
	
	private static Node createUser(org.w3c.dom.Document doc, String id, String firstName, String lastName, String profession)
	{
		org.w3c.dom.Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "firstname", firstName));
		user.appendChild(createUserElement(doc, "lastname", lastName));
		user.appendChild(createUserElement(doc, "profession", profession));
		
		return user;
	}
	
	private static Node createUserElement(org.w3c.dom.Document doc, String name, String value)
	{
		org.w3c.dom.Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}

}
