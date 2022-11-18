package hu.domparse.i40fdc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class DOMReadI40FDC {

  public static void main(String[] args) {

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      try {
    	  
    	  // 	TXT fajlba mentes
    	  //PrintStream myconsole = new PrintStream(new File("C:\\Users\\Admin\\OneDrive\\Asztali gép\\Uni\\2022_23_1\\XML\\Github\\XMLTaskI40FDC\\DOMParseI40FDC\\src\\hu\\domparse\\i40fdc\\output.txt"));
          //System.setOut(myconsole);
    	  
          // parse XML file
          DocumentBuilder db = dbf.newDocumentBuilder();

          Document doc = db.parse(new File("XMLi40fdc.xml"));

          System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
          System.out.println("------------------------");

          // ---------------------------- Kutyak ----------------------------
          NodeList listKutya = doc.getElementsByTagName("kutya");

          for (int i = 0; i < listKutya.getLength(); i++) {

              Node node = listKutya.item(i);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // attributumok
                  String kid = element.getAttribute("kid");
                  String k_t = element.getAttribute("k_t");
                  String k_g = element.getAttribute("k_g");

                  // szoveg kiolvasasa
                  String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                  String ev = element.getElementsByTagName("ev").item(0).getTextContent();
                  String honap = element.getElementsByTagName("honap").item(0).getTextContent();
                  String nap = element.getElementsByTagName("nap").item(0).getTextContent();
                  //tobberteku elemekhez lista
                  NodeList felmenok = element.getElementsByTagName("felmeno");
                  
                  //adatok kiiratasa
                  System.out.println("Current Element : " + node.getNodeName());
                  System.out.println("ID : " + kid);
                  System.out.println("Tenyeszto ID : " + k_t);
                  System.out.println("Gazdi ID : " + k_g);
                  System.out.println("Nev : " + nev);
                  System.out.println("Szuletett : " + ev + ". " + honap + " " + nap + ".");
                  for(int j=0; j<felmenok.getLength(); j++) {
                	  System.out.println("Felmeno : " + felmenok.item(j).getTextContent());
                  }
                  System.out.println();
              }
          }
          System.out.println("------------------------\n");
          
          // ---------------------------- Tenyesztok ----------------------------
          NodeList listTenyeszto = doc.getElementsByTagName("tenyeszto");

          for (int i = 0; i < listTenyeszto.getLength(); i++) {

              Node node = listTenyeszto.item(i);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // attributumok
                  String tid = element.getAttribute("tid");

                  // szoveg kiolvasasa
                  String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                  String varos = element.getElementsByTagName("varos").item(0).getTextContent();
                  String utca = element.getElementsByTagName("utca").item(0).getTextContent();
                  String hazszam = element.getElementsByTagName("hazszam").item(0).getTextContent();
                  String fajta = element.getElementsByTagName("fajta").item(0).getTextContent();
                  
                  //adatok kiiratasa
                  System.out.println("Current Element : " + node.getNodeName());
                  System.out.println("ID : " + tid);
                  System.out.println("Nev : " + nev);
                  System.out.println("Telephely : " + varos + ", " + utca + " " + hazszam + ".");
                  System.out.println("Tenyesztett fajta : " + fajta);
                  System.out.println();
              }
          }
          System.out.println("------------------------\n");
          
          // ---------------------------- Gazdik ----------------------------
          NodeList listGazdi = doc.getElementsByTagName("gazdi");

          for (int i = 0; i < listGazdi.getLength(); i++) {

              Node node = listGazdi.item(i);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // attributumok
                  String gid = element.getAttribute("gid");

                  // szoveg kiolvasasa
                  String nev = element.getElementsByTagName("nev").item(0).getTextContent();
                  String varos = element.getElementsByTagName("varos").item(0).getTextContent();
                  String utca = element.getElementsByTagName("utca").item(0).getTextContent();
                  String hazszam = element.getElementsByTagName("hazszam").item(0).getTextContent();
                  String kor = element.getElementsByTagName("kor").item(0).getTextContent();
                  
                  //adatok kiiratasa
                  System.out.println("Current Element : " + node.getNodeName());
                  System.out.println("ID : " + gid);
                  System.out.println("Nev : " + nev);
                  System.out.println("Lakcim : " + varos + ", " + utca + " " + hazszam + ".");
                  System.out.println("Kor : " + kor);
                  System.out.println();
              }
          }
          System.out.println("------------------------\n");
          
          // ---------------------------- Oltasi konyvek ----------------------------
          NodeList listOltasi = doc.getElementsByTagName("oltasi_konyv");

          for (int i = 0; i < listOltasi.getLength(); i++) {

              Node node = listOltasi.item(i);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // attributumok
                  String oid = element.getAttribute("oid");
                  String k_o = element.getAttribute("k_o");

                  // szoveg kiolvasasa
                  String kiallitas = element.getElementsByTagName("kiallitas").item(0).getTextContent();
                  String allatorvos = element.getElementsByTagName("allatorvos").item(0).getTextContent();
                  //tobberteku elemekhez lista
                  NodeList oltasok = element.getElementsByTagName("oltas");
                  
                  //adatok kiiratasa
                  System.out.println("Current Element : " + node.getNodeName());
                  System.out.println("ID : " + oid);
                  System.out.println("Kutya ID : " + k_o);
                  System.out.println("Kiallitas : " + kiallitas);
                  for(int j=0; j<oltasok.getLength(); j++) {
                	  System.out.println("Oltas : " + oltasok.item(j).getTextContent());
                  }
                  System.out.println();
              }
          }
          System.out.println("------------------------\n");
          
          // ---------------------------- Versenyek ----------------------------
          NodeList listVerseny = doc.getElementsByTagName("verseny");

          for (int i = 0; i < listVerseny.getLength(); i++) {

              Node node = listVerseny.item(i);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // attributumok
                  String vid = element.getAttribute("vid");

                  // szoveg kiolvasasa
                  String dij = element.getElementsByTagName("nevezesi_dij").item(0).getTextContent();
                  String helyszin = element.getElementsByTagName("helyszin").item(0).getTextContent();
                  String idopont = element.getElementsByTagName("idopont").item(0).getTextContent();
                  
                  //adatok kiiratasa
                  System.out.println("Current Element : " + node.getNodeName());
                  System.out.println("ID : " + vid);
                  System.out.println("Nevezesi dij : " + dij);
                  System.out.println("Helyszin : " + helyszin);
                  System.out.println("Idopont : " + idopont);
                  System.out.println();
              }
          }
          System.out.println("------------------------\n");
          
          // ---------------------------- Versenyen valo reszvetelek ----------------------------
          NodeList listReszvetel = doc.getElementsByTagName("reszvetel");

          for (int i = 0; i < listReszvetel.getLength(); i++) {

              Node node = listReszvetel.item(i);

              if (node.getNodeType() == Node.ELEMENT_NODE) {

                  Element element = (Element) node;

                  // attributumok
                  String k_v_k = element.getAttribute("k_v_k");
                  String k_v_v = element.getAttribute("k_v_v");

                  // szoveg kiolvasasa
                  String helyezes = element.getElementsByTagName("helyezes").item(0).getTextContent();
                  String nyeremeny = element.getElementsByTagName("nyeremeny").item(0).getTextContent();
                  
                  //adatok kiiratasa
                  System.out.println("Current Element : " + node.getNodeName());
                  System.out.println("Kutya ID : " + k_v_k);
                  System.out.println("Verseny ID : " + k_v_v);
                  System.out.println("Helyezes : " + helyezes);
                  System.out.println("Nyeremeny : " + nyeremeny);
                  System.out.println();
              }
          }
         

      } catch (ParserConfigurationException | SAXException | IOException e) {
          e.printStackTrace();
      } 

  }

}