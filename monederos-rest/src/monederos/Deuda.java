package monederos;

public class Deuda {

	private String idMonedero;
	private String nombreActividad;
	private final String deudor;
	private final String acreedor;
	private double cantidad;

	public Deuda(String idMonedero, String nombreActividad, String deudor, String acreedor, double cantidad) {
		this.nombreActividad = nombreActividad;
		this.deudor = deudor;
		this.acreedor = acreedor;
		this.cantidad = cantidad;
	}

	public String getIdMonedero() {
		return idMonedero;
	}
	
	public String getNombreActividad() {
		return nombreActividad;
	}
	
	public String getAcreedor() {
		return acreedor;
	}

	public String getDeudor() {
		return deudor;
	}

	public double getCantidad() {
		return cantidad;
	}

	@Override
	public String toString() {
		return "Deuda [idMonedero=" + idMonedero + ", nombreActividad=" + nombreActividad + ", deudor=" + deudor
				+ ", acreedor=" + acreedor + ", cantidad=" + cantidad + "]";
	}

	
	
}
