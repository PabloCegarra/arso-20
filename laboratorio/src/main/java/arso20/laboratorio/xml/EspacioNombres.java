package arso20.laboratorio.xml;

import java.util.Arrays;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

// Gestiona un solo prefijo
public class EspacioNombres implements NamespaceContext {

	private static final String PREFIJO = "c";
	private static final String URI = "http://www.um.es/acta";

	@Override
	public String getNamespaceURI(String prefix) {
		
		return prefix.equals(PREFIJO)? URI : null;		
	}

	@Override
	public String getPrefix(String namespaceURI) {
		
		return namespaceURI.equals(URI)? PREFIJO : null;		
	}

	@Override
	public Iterator<String> getPrefixes(String namespaceURI) {
		
		return namespaceURI.equals(URI)? Arrays.asList(PREFIJO).iterator() : null;
	}
}
