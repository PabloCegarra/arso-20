package arso20.laboratorio.xml;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class ManejadorErrores extends DefaultHandler {
	
	private List<SAXParseException> errores = new LinkedList<>();
	
	public void error(SAXParseException e) throws SAXException {
		errores.add(e);
		// throw e; -> para detener la validacion
	}
	
	public List<SAXParseException> getErrores() {
		return Collections.unmodifiableList(errores);
	}
}

public class Validacion {

	public static void main(String[] args) throws Exception {

		final String ficheroEsquema = "xml/acta.xsd";
		final String nombreFichero = "xml/acta-errores.xml";
		
		SchemaFactory factoriaSchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		// Construye el esquema
		Schema esquema = factoriaSchema.newSchema(new File(ficheroEsquema));

		// Solicita al esquema la construccion de un validador
		Validator validador = esquema.newValidator();

		// Registra el manejador de eventos de error
		ManejadorErrores manejador = new ManejadorErrores();
		validador.setErrorHandler(manejador);
			
		// Solicita la validacion del fichero XML
		validador.validate(new StreamSource(nombreFichero));
		
		// Comprueba si se han producido errores
		if (! manejador.getErrores().isEmpty()) {
			System.out.println("El documento tiene errores de validación:");
			for (SAXParseException error : manejador.getErrores())
				System.out.println(error.getMessage());
		}
		else System.out.println("El documento es válido");

		
		System.out.println("fin.");

	}
}
