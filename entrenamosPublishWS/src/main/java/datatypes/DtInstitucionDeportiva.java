package datatypes;

public class DtInstitucionDeportiva {
	private String nombre;
	private String descripcion;
	private String url;

	public DtInstitucionDeportiva(){
		super();
	}
	public DtInstitucionDeportiva(String n, String d, String u) {
		super();
		this.nombre = n;
		this.descripcion = d;
		this.url = u;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "Nombre: " + nombre + "\nDescripcion: " + descripcion + "\nUrl: " + url;
	}
}
