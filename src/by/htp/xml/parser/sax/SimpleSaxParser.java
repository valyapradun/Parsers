package by.htp.xml.parser.sax;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLReaderFactory;

public class SimpleSaxParser {
	public static void main(String [] args){
		try {
			SimpleSaxHandler handler = new SimpleSaxHandler();
			org.xml.sax.XMLReader reader = XMLReaderFactory.createXMLReader();
			reader.setContentHandler(handler);
			reader.parse("resources/family.xml");
			
		} catch (SAXException e) {	
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		
		}
		}
}
