package by.htp.xml.parser.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.htp.xml.model.XmlElement;
import by.htp.xml.model.Family;
import by.htp.xml.model.Father;
import by.htp.xml.model.Mother;
import by.htp.xml.model.Parent;

public class SimpleSaxHandler extends DefaultHandler {
	private String text;
	private StringBuilder builder;
	private Family family;
	private List<Family> families;
	private Parent parent;
	
	

	@Override
	public void characters(char[] buf, int start, int length) throws SAXException {
	
	
		//System.out.println(biulde.append(buf, start, length).toString().trim());
		text = builder.append(buf, start, length).toString().trim();

	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println(families);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
	//	System.out.println("end element: " + qName);
	//	XmlElement element = XmlElement.valueOf(text.replace("-","_"));
		XmlElement element = XmlElement.valueOf(qName.toUpperCase().replace("-","_"));
		switch(element){
		case FAMILY:
			families.add(family);
			break;
		case MOTHER:
			family.setMother(parent);
			break;
		case FATHER:
			family.setFather(parent);
			break;
		case NAME:
			parent.setName(text);
			break;
		case AGE:
			parent.setAge(Integer.parseInt(text));
			break;
		case MAIDEN_NAME:
			((Mother)parent).setMaideName(text);
			break;
		case SURNAME:
			parent.setSurname(text);
			break;
		case FAMILIES:
			break;
		}
		
	}

	@Override
	public void startDocument() throws SAXException {
		//System.out.println("start document");
		families = new ArrayList<Family>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//System.out.println("start element: " + qName);
		builder = new StringBuilder();
		//text = null;
		if("family".equals(qName)){
			family = new Family();
			family.setName(attributes.getValue("name"));
		}
		if("mother".equals(qName)){
			parent = new Mother();	
		}
		if("father".equals(qName)){
			parent = new Father();	
		}
		if("children".equals(qName)){
			((Mother)parent).setChildren(Integer.parseInt(attributes.getValue("count")));
		}
	}

}
