package monederos;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Actividad {

	private final String nombre;
	private final LocalDate fecha;
	private final String organizador;
	private final HashSet<String> participantes;

	public Actividad(String nombre, LocalDate fecha, String organizador, double precio, String... participantes) {
		this.nombre = nombre;
		this.fecha = fecha;
		this.organizador = organizador;
		this.participantes = new HashSet<>();
		Collections.addAll(this.participantes, participantes);
	}
	

	public String getNombre() {
		return nombre;
	}
	
	public String getFecha() {
		return fecha.toString(); // Corregido
	}
	
	public String getOrganizador() {
		return organizador;
	}
	
	public Set<String> getParticipantes() {
		return Collections.unmodifiableSet(participantes);
	}

	@Override
	public String toString() {
		return "Actividad [nombre=" + nombre + ", date=" + fecha + ", organizador=" + organizador + ", participantes="
				+ participantes + "]";
	}
	
	
}
