package by.htp.xml.parser.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.xml.model.Family;
import by.htp.xml.model.Father;
import by.htp.xml.model.Mother;
import by.htp.xml.model.Parent;
import by.htp.xml.model.XmlElement;

public class SimpleStaxParser {
	
	public static void main(String[] args) {
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream("resources/family.xml");
			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			List<Family> families = process(reader);
			for(Family family: families){
				System.out.println(family);
			} 
		} catch (XMLStreamException e){
			e.printStackTrace();
		} catch (FileNotFoundException e){
			e.printStackTrace();
		}
	}

	
	private static List<Family> process(XMLStreamReader reader) throws XMLStreamException {
		Family family = null;
		Parent parent = null;
		List<Family> families = new ArrayList<Family>();
		XmlElement element = null;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
				case XMLStreamConstants.START_ELEMENT:
					element = XmlElement.valueOf(reader.getLocalName().trim().toUpperCase().replace("-","_"));
					switch (element) {
						case FAMILY:
							family = new Family();
						    String name = reader.getAttributeValue(0);  
						//	String name = reader.getAttributeValue(null, "name");
							family.setName(name);
							break;
						case MOTHER:
							parent = new Mother();
							family.setMother(parent);
							break;
						case FATHER:
							parent = new Father();
							family.setFather(parent);
							break;	
						case CHILDREN:
							int count = Integer.parseInt(reader.getAttributeValue(0));
							((Mother)parent).setChildren(count);
							break;
					}
					break;
					
				case XMLStreamConstants.CHARACTERS:
					String text = reader.getText().trim();
					
					if(text.isEmpty()){
						break;
					}
					switch (element) {
						case NAME:
							parent.setName(text);
							break;
						case SURNAME:
							parent.setSurname(text);
							break;
						case AGE:
							parent.setAge(Integer.parseInt(text));
							break;
						case MAIDEN_NAME:
							((Mother)parent).setMaideName(text);
							break;	
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					element = XmlElement.valueOf(reader.getLocalName().trim().toUpperCase().replace("-","_"));
					switch (element) {
					case FAMILY:
						families.add(family);
					}
			}
		}
		return families;
	}
}
