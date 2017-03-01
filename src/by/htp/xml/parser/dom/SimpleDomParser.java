package by.htp.xml.parser.dom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.htp.xml.model.Family;
import by.htp.xml.model.Father;
import by.htp.xml.model.Mother;
import by.htp.xml.model.Parent;

public class SimpleDomParser {
	public static void main(String [] args){
		List<Family> arrayFamilies = new ArrayList<Family>();
		Family familiesXmL = null;
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse("resources/family.xml");
			document.getDocumentElement().normalize();
			Element root = document.getDocumentElement();
			NodeList families = root.getElementsByTagName("family");
			
			for (int i = 0; i < families.getLength(); i++){   
				familiesXmL = new Family();
				familiesXmL.setName(((Element)families.item(i)).getAttribute("name").toString());
			
				Node family = families.item(i);   
				NodeList familyList = family.getChildNodes();    

				for (int j = 0; j < familyList.getLength(); j++){   
					String tag = familyList.item(j).getNodeName();
					Parent oneParent = null;
					switch(tag){
						case "mother":
							oneParent = new Mother();
							notSingleTag(familyList.item(j), oneParent);
							familiesXmL.setMother(oneParent);
							break;
						case "father":
							oneParent = new Father();
							notSingleTag(familyList.item(j), oneParent);
							familiesXmL.setFather(oneParent);
							break;
					}
				}
				arrayFamilies.add(familiesXmL);
			}
		} catch (ParserConfigurationException e) {
				e.printStackTrace();
		} catch (SAXException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		
		
		for(Family family: arrayFamilies){
			System.out.println(family);
		} 
			
	}
	

	public static void singleTag (Node node, Parent parent){
		String tag = node.getNodeName();
		switch(tag){
		case "name":
			parent.setName(node.getTextContent().trim());
			break;
		case "surname":
			parent.setSurname(node.getTextContent().trim());
			break;
		case "age":
			parent.setAge(Integer.parseInt(node.getTextContent().trim()));
			break;
		case "maiden-name":
			((Mother)parent).setMaideName(node.getTextContent().trim());
			break;
		case "children":
			((Mother)parent).setChildren(Integer.parseInt(((Element)node).getAttribute("count").toString()));
			break;
		}
	}	
	
	
	public static Node notSingleTag (Node nodeParent, Parent oneParent){
		NodeList parentList = nodeParent.getChildNodes();          
		for (int k = 0; k < parentList.getLength(); k++) {   
			singleTag(parentList.item(k), oneParent);
		}
		return nodeParent;
	}
}
