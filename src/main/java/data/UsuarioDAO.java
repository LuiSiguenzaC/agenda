package data;

import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CLASE APROBADA
public class UsuarioDAO implements IUsuarioDAO {

    /*
        Clase que implementa la interfaz IUsuarioDAO
        Esta clase debe dotar de funcionalidad cada uno de los métodos declarados dentro de la interfaz
        Debe estar diseñada para interactuar únicamente con la table usuarios
     */
    private Connection conexion = null;
    private static final String SQL_SELECT = "SELECT idUsuarios, nombre, apellido, cel, direccion FROM Usuarios";
    private static final String SQL_SELECT_UNIT = "SELECT * FROM Usuairos WHERE nombre = ? AND apellido = ? AND cel = ? AND direccion =?";
    private static final String SQL_INSERT = "INSERT INTO Usuarios (nombre,apellido,cel,direccion) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Usuarios SET nombre = ?, apellido = ?, cel = ?, direccion = ? WHERE idUsuarios = ?";
    private static final String SQL_DELETE = "DELETE FROM Usuarios WHERE idUsuarios = ?";

    public UsuarioDAO() {

    }

    @Override
    public List<Object> obtenerTodos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        List<Object> lista = new ArrayList<>();

        try {
            conn = Conexion.obtenerConexion();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idUsuarios = rs.getInt("idUsuarios");
                String Nombre = rs.getString("nombre");
                String Apellido = rs.getString("apellido");
                String Cel = rs.getString("cel");//cambie telefono por Cel
                String Direccion = rs.getString("direccion");

                usuario = new Usuario(idUsuarios, Nombre, Apellido, Direccion, Cel);

                lista.add(usuario);
            }

            Conexion.cerrarConexion(rs);
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conn);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return lista;
    } //revisado y todo correcto

    @Override
    public int insertar(Object usuario) { //inserta el objeto a la base de datos
        Connection conexion = null;
        PreparedStatement statement = null;
        int rows = 0;
        Usuario user = (Usuario) usuario;//Le asigné a la variable user el objeto que se pase por parámetro
        try {
            conexion = Conexion.obtenerConexion();
            statement = conexion.prepareStatement(SQL_INSERT);

            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setString(3, user.getCel());
            statement.setString(4, user.getDireccion());

            rows = statement.executeUpdate();

            Conexion.cerrarConexion(statement);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;
    }//revisado y todo correcto

    @Override
    public int actualizar(Object usuario) {
        Connection conexion = null;
        PreparedStatement statement;
        int rows = 0;
        Usuario user = (Usuario) usuario;//Le asigné a la variable user el objeto que se pase por parámetro

        try {
            conexion = Conexion.obtenerConexion();
            statement = conexion.prepareStatement(SQL_UPDATE);

            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellido());
            statement.setString(3, user.getCel());
            statement.setString(4, user.getDireccion());//cambie algunos set por el tipo de dato
            statement.setInt(5, user.getId());

            rows = statement.executeUpdate();

            Conexion.cerrarConexion(statement);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;
    }//revisado y todo correcto

    @Override
    public int borrar(Object usuario) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        int rows = 0;
        Usuario user = (Usuario) usuario;//Le asigné a la variable user el objeto que se pase por parámetro

        try {
            conexion = Conexion.obtenerConexion();

            stmt = conexion.prepareStatement(SQL_DELETE);
            stmt.setInt(1, user.getId());

            rows = stmt.executeUpdate();

            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;
    } //revisado y todo correcto

    @Override
    public Object seleccionar(Object usuario) {

        Connection conexion = null;
        PreparedStatement stmt = null;
        Usuario user = (Usuario) usuario;//El usuario que se recibe por parámetro
        Usuario usuarioDevuelto = null; //el objeto usuario que se creará si los parámetros de la cosulta coinciden
        ResultSet rs = null;

        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_SELECT_UNIT); //para seleccionar una usuario especifico

            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getApellido());
            stmt.setString(3, user.getCel());
            stmt.setString(4, user.getDireccion());

            rs = stmt.executeQuery();

            //obteniendo los resultados de la consulta y almacendandolos en variables para crear el objeto de tipo Usuario
            while (rs.next()) {
                int idUsuario = rs.getInt("idUsuario");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String celular = rs.getString("cel");
                String direccion = rs.getString("direccion");

                //se crea el objeto de tipo usuario con los resultados devueltos
                usuarioDevuelto = new Usuario(idUsuario, nombre, apellido, celular, direccion);
            }

            //se cierran las conexiones abiertas
            Conexion.cerrarConexion(rs);
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return usuarioDevuelto;

    } //método revisado y todo correcto

}
