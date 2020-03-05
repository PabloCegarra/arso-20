package arso20.laboratorio.xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOM {


	public static void main(String[] args) throws Exception {

		final String documento = "xml/acta.xml";
		
		// Construye un analizador DOM
		
		DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factoria.newDocumentBuilder();

		Document doc = builder.parse(documento);

		// ...
		

		System.out.println("Fin.");
	}

}
