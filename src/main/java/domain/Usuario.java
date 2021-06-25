package domain;

public class Usuario {
     /*
        Clase para construir objetos de tipo Usuario
     */
    private  int id;
    private  String nombre;
    private  String apellido;
    private  String cel;
    private  String direccion;

    public Usuario() {
    }

    public Usuario(int id) {
        this.id = id;
    }

    public Usuario(String nombre, String apellido,String cel,String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cel = cel;
        this.direccion = direccion;
    }

    public Usuario(int id, String nombre, String apellido,String direccion ,String cel ) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cel = cel;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCel() {
        return cel;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", cel=" + cel + ", direccion=" + direccion + '}';
    }
        
}
