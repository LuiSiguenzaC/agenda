package data;

import domain.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//CLASE APROBADA
public class LoginDAO implements IUsuarioDAO {

    /*
        Implementa la interfaz IUsuarioDAO
        diseñada para interactuar únicamente con la tabla login
     */
    private Connection conexion = null;
    private static final String SQL_SELECT = "SELECT * FROM Login ";
    private static final String SQL_SELECT_UNIT = "SELECT * FROM Login WHERE usuario = ? AND password = ?";
    private static final String SQL_INSERT = "INSERT INTO Login (usuario, password) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE Login SET usuario = ?, password = ? WHERE idLogin = ?";
    private static final String SQL_DELETE = "DELETE FROM Login WHERE idLogin = ?";

    public LoginDAO() {
    }

    public LoginDAO(Connection conexion) {

    }

    @Override
    public List<Object> obtenerTodos() {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> lista = new ArrayList<>();

        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery(SQL_SELECT);

            while (rs.next()) {
                int idLogin = rs.getInt("idLogin");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");

                Login login = new Login(idLogin, usuario, password);
                lista.add(login);
            }

            Conexion.cerrarConexion(rs);
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return lista;
    }//revisado y todo correcto

    @Override
    public int insertar(Object login) {
        Connection conexion = null;
        PreparedStatement statement = null;
        int rows = 0;
        Login log = (Login) login;
        try {
            conexion = Conexion.obtenerConexion();
            statement = conexion.prepareStatement(SQL_INSERT);

            statement.setString(1, log.getUsuario());
            statement.setString(2, log.getPassword());

            rows = statement.executeUpdate();

            Conexion.cerrarConexion(statement);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;
    }//revisado y todo correcto

    @Override
    public int actualizar(Object login) {
        Connection conexion = null;
        PreparedStatement statement;
        Login log = (Login) login;
        int rows = 0;
        try {
            conexion = Conexion.obtenerConexion();
            statement = conexion.prepareStatement(SQL_UPDATE);

            statement.setString(1, log.getUsuario());
            statement.setString(2, log.getPassword());
            statement.setInt(3, log.getId());

            rows = statement.executeUpdate();

            //Conexion.cerrarConexion(statement);
            //Conexion.cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;
    }//revisado y todo correcto

    @Override
    public int borrar(Object login) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        int rows = 0;
        Login log = (Login) login;
        try {
            conexion = Conexion.obtenerConexion();

            stmt = conexion.prepareStatement(SQL_DELETE);
            stmt.setInt(1, log.getId());

            rows = stmt.executeUpdate();

            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;

    }//revisado y todo correcto

    @Override
    public Object seleccionar(Object login) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        Login log = (Login) login; //el objeto que se recibe por parámetro
        Login logDevuelto = null; //el objeto que será devuelto
        ResultSet rs = null;

        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_SELECT_UNIT); //para seleccionar una Login especifico

            stmt.setString(1, log.getUsuario());
            stmt.setString(2, log.getPassword());

            rs = stmt.executeQuery();

            //obteniendo los resultados de la consulta y almacendandolos en variables para crear el objeto de tipo Usuario
            while (rs.next()) {
                int idLogin = rs.getInt("idLogin");
                String usuario = rs.getString("usuario");
                String password = rs.getString("password");

                //Se crea el objeto
                logDevuelto = new Login(idLogin, usuario, password);
            }

            //se cierran las conexiones abiertas
            Conexion.cerrarConexion(rs);
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return logDevuelto; //se retorna el objeto
    }//revisado y todo correcto
    
    public Object obtenerDatos(String usuario){
        
        Connection conexion = null;
        PreparedStatement stmt= null;
        Login usuarioDevuelto = null;
        ResultSet rs = null;
        
        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement("SELECT * FROM Login WHERE usuario = ?");
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();
            while(rs.next()){
                int id = rs.getInt("idLogin");
                String usr = rs.getString("usuario");
                String pass = rs.getString("password");
                
                usuarioDevuelto = new Login(id,usr,pass);
            }
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }
        return usuarioDevuelto;
    }
}
