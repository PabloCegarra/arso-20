package monederos;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Monedero {

	private String id;
	private Set<String> propietarios;
	private Map<String, Actividad> actividades;
	private List<Deuda> deudas;
		
	public Monedero(String... propietarios) {
		
		if (propietarios.length <= 1)
			throw new IllegalArgumentException("Al menos debe haber dos propietarios");
		
		this.id = UUID.randomUUID().toString();
		this.propietarios = new HashSet<>();
		this.actividades = new HashMap<>();
		this.deudas = new LinkedList<>();
		Collections.addAll(this.propietarios, propietarios);
	}
	
	public String getId() {
		return id;
	}
	
	public Set<String> getPropietarios() {
		return Collections.unmodifiableSet(propietarios);
	}
	
	public Collection<Actividad> getActividades() {
		return Collections.unmodifiableCollection(actividades.values());
	}
	
	public List<Deuda> getDeudas() {
		return Collections.unmodifiableList(deudas);
	}
	
	public Actividad addActividad(String nombre, LocalDate fecha, String organizador, double coste, String... participantes) {
				
		if (nombre == null || nombre.equals(""))
			throw new IllegalArgumentException("El nombre no debe ser nulo ni la cadena vacía");
		
		if (this.actividades.containsKey(nombre))
			throw new IllegalArgumentException("Ya existe una actividad con el mismo nombre");
		
		// Se acepta cualquier fecha, incluso pasada
		if (fecha == null)
			throw new IllegalArgumentException("La fecha no debe ser nula");

		if (! this.propietarios.contains(organizador))
			throw new IllegalArgumentException("El organizador debe ser un propietario");

		if (coste <= 0)
			throw new IllegalArgumentException("El coste debe ser un valor positivo");
		
		if (! this.propietarios.containsAll(Arrays.asList(participantes)))
			throw new IllegalArgumentException("Los participantes deben ser propietarios");

		
		Actividad actividad = new Actividad(nombre, fecha, organizador, coste, participantes);
		actividades.put(nombre, actividad);
	
		double cantidad = coste / participantes.length;
		for(int i = 0; i < participantes.length; i++) {
			if (! participantes[i].equals(organizador)) {
				Deuda d = new Deuda(this.id, nombre, participantes[i], organizador, cantidad);
				deudas.add(d);
			}
		}
	
		
		return actividad;
	}
		
	public Actividad getActividadByNombre(String nombre) {
		
		if (nombre == null || nombre.equals(""))
			throw new IllegalArgumentException("El nombre no debe ser nulo ni la cadena vacía");
		
		if (! this.actividades.containsKey(nombre))
			throw new IllegalArgumentException("No existe una actividad con el nombre especificado");
		
		
		return this.actividades.get(nombre);
	}
	
	public List<Deuda> getDeudasPropietarios(String propietario1, String propietario2) {
		
		if (propietario1 == null || propietario1.equals(""))
			throw new IllegalArgumentException("propietario1 no debe ser nulo ni la cadena vacía");
		
		if (propietario2 == null || propietario2.equals(""))
			throw new IllegalArgumentException("propietario2 no debe ser nulo ni la cadena vacía");
		
		if (propietario1.equals(propietario2))
			throw new IllegalArgumentException("propietario1 y propietario2 deben ser diferentes");

		
		LinkedList<Deuda> resultado = new LinkedList<>();
		
		Iterator<Deuda> it = deudas.iterator();
		while ( it.hasNext() ) {
			Deuda deuda = it.next();
			if ( deuda.getAcreedor().equals(propietario2) && deuda.getDeudor().equals(propietario1) ) {
				resultado.add(deuda);
			} else if ( deuda.getAcreedor().equals(propietario1) && deuda.getDeudor().equals(propietario2) ) {
				resultado.add(deuda);
			}
		}
		
		return resultado;
	}
	
	public void cancelarDeudas(String propietario1, String propietario2) {
		
		if (propietario1 == null || propietario1.equals(""))
			throw new IllegalArgumentException("propietario1 no debe ser nulo ni la cadena vacía");
		
		if (propietario2 == null || propietario2.equals(""))
			throw new IllegalArgumentException("propietario2 no debe ser nulo ni la cadena vacía");
		
		if (propietario1.equals(propietario2))
			throw new IllegalArgumentException("propietario1 y propietario2 deben ser diferentes");

		
		this.deudas.removeAll(getDeudasPropietarios(propietario1, propietario2));
		
	}

	@Override
	public String toString() {
		return "Monedero [id=" + id + ", propietarios=" + propietarios + ", actividades=" + actividades.values() + ", deudas="
				+ deudas + "]";
	}

	
	
}
