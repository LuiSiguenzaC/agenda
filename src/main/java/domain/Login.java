package domain;

public class Login {
     /*
        Clase para crear objetos de tipo Login
    */
    private int id;
    private String usuario;
    private String password;
   
    public Login() {
        
    }
    public Login(int id) {
        this.id = id;
    }

    public Login(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Login(int id, String usuario, String password) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
