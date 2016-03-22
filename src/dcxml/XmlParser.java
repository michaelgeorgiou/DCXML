package dcxml;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParser {

	public XmlParser() 
	{
		// TODO Auto-generated constructor stub
	}
	
	private void parse()
	{
		String iban="";
		try
		{
			String xml_data = new String(Files.readAllBytes(Paths.get("C:\\DC12XML\\CorrectSampleFiles\\CorrectSampleFiles\\AUTOPAY_20160307_2.txt")));
			//System.out.println(xml_data);
			
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource data_source = new InputSource();
			data_source.setCharacterStream(new StringReader(xml_data));
		
			Document doc = db.parse(data_source);
			NodeList nodes = doc.getElementsByTagName("DbtrAcct");

			for (int i = 0; i < nodes.getLength(); i++) 
			{
					Element element = (Element) nodes.item(i);
		       
					NodeList service_name = element.getElementsByTagName("IBAN");
					Element line = (Element) service_name.item(0);
					iban = getCharacterDataFromElement(line);
					System.out.println("IBAN["+iban+"]");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String getCharacterDataFromElement(Element e) 
	{
	    Node child = e.getFirstChild();
	    if (child instanceof CharacterData) {
	      CharacterData cd = (CharacterData) child;
	      return cd.getData();
	    }
	    return "";
	}

	public static void main(String[] args) 
	{
		XmlParser xml = new XmlParser();
		xml.parse();
	}

}
