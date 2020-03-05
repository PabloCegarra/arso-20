package arso20.laboratorio.xml;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;

public class XPathConsultas {

	public static void main(String[] args) throws Exception {
		
		final String documento = "xml/acta.xml";
		
		// 1. Obtener la factoria
		
		XPathFactory factoria = XPathFactory.newInstance();
				 
		// 2. Construir el evaluador XPath
				
		XPath xpath = factoria.newXPath();

		xpath.setNamespaceContext( new EspacioNombres());

		
		// 3. Realizar una consulta
				
		XPathExpression consulta = xpath.compile("//c:nif"); 		

				
		NodeList resultado = (NodeList) consulta.evaluate(
		  new org.xml.sax.InputSource(documento), XPathConstants.NODESET); 
		
		for (int i = 0; i < resultado.getLength(); i++) {
			System.out.println(resultado.item(i).getTextContent());
		}
		
		System.out.println("fin.");

	}
}
