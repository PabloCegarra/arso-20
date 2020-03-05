package monederos;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ServicioMonedero {

	Monedero crearMonedero(Set<String> propietarios);
	
	Monedero getMonedero(String idMonedero);
	
	Collection<Monedero> getMonederos();
	
	boolean removeMonedero(String idMonedero);
	
	Actividad organizarActividad(String idMonedero, String nombre, String organizador, LocalDate fecha, double coste, Set<String> participantes);
	
	Collection<Actividad> getActividades(String idMonedero);
	
	Actividad getActividadByNombre(String idMonedero, String nombre);
	
	List<Deuda> getDeudasPropietarios(String idMonedero, String propietario1, String propietario2);
	
	void cancelarDeudas(String idMonedero, String propietario1, String propietario2);
	
}
