package domain;

public class Agenda {

    /*
        Clase para construir objetos de tipo Agenda
     */
    private int id;
    private String descripcion;
    private String fecha; //le cambie el nombre a fecha - Jairo
    private int idnota; //agregué este campo para poder borrar una nota en específico

    public Agenda() {
    }

    public Agenda(int idnota) {
        this.idnota = idnota;
    }

    public Agenda(int id, int idnota) {
        this.id = id;
        this.idnota = idnota;
    }

    public Agenda(String descripcion, String fecha, int idnota) {
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idnota = idnota;
    }

    public Agenda(int id, String descripcion, String fecha){
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    public Agenda(int id, String descripcion, String fecha, int idnota){
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.idnota = idnota;
    }
 
    public int getId() {
        return id;
    }

    public String getDesccripcion() {
        return descripcion;
    }

    public String getFecha() {
        return fecha;
    }
    
    public int getIdNota(){
        return this.idnota;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDesccripcion(String desccripcion) {
        this.descripcion = desccripcion;
    }
    
    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public void setIdNota(int idnota){
        this.idnota = idnota;
    }

    @Override
    public String toString() {
        return "Agenda{" + "id=" + id + ", descripcion=" + descripcion + ", fecha=" + fecha + ", idnota=" + idnota + '}';
    }
    
    

    

    
    
    
}
