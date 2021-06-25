package data;

import domain.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//CLASE APROBADA
public class AgendaDAO implements IUsuarioDAO {

    /*
        Esta clase también debe implementar la interfaz IUsuarioDAO y dotar de funcionalidad cada uno de los métodos
        Esta clase debe estar diseñada para interactuar únicamente con la tabla agenda
     */
    private Connection conexion = null;
    private static final String SQL_SELECT = "SELECT * FROM Agenda";
    private static final String SQL_SELECT_UNIC = "SELECT * FROM Agenda WHERE descripcion = ? AND fecha = ?";
    private static final String SQL_SELECT_REL = "SELECT * FROM Agenda WHERE idAgenda = ?";
    private static final String SQL_INSERT = "INSERT INTO Agenda (idAgenda,descripcion, fecha) VALUES (?,?, ?)";
    private static final String SQL_UPDATE = "UPDATE Agenda SET descripcion = ?, fecha = ? WHERE idNota = ?";
    private static final String SQL_DELETE = "DELETE FROM Agenda WHERE idNota = ?";

    public AgendaDAO() {
    }

    @Override
    public List<Object> obtenerTodos() {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> lista = new ArrayList<>();

        try {
            conexion = this.conexion;
            stmt = conexion.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int idAgenda = rs.getInt("idAgenda");
                String descripcion = rs.getString("descripcion");
                String fecha = rs.getString("fecha");
                int idNota = rs.getInt("idNota");

                Agenda agenda = new Agenda(idAgenda, descripcion, fecha, idNota);

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
    public int insertar(Object agenda) {
        Connection conexion = null;
        PreparedStatement statement = null;
        int rows = 0;
        Agenda agen = (Agenda) agenda; //el objeto que se pasa por parámetro
        try {
            conexion = Conexion.obtenerConexion();
            statement = conexion.prepareStatement(SQL_INSERT);

            statement.setInt(1, agen.getId());
            statement.setString(2, agen.getDesccripcion());
            statement.setString(3, agen.getFecha());

            rows = statement.executeUpdate();

            Conexion.cerrarConexion(statement);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;
    }//arreglado, todo correcto

    @Override
    public int actualizar(Object agenda) {
        Connection conexion = null;
        PreparedStatement statement = null;
        int rows = 0;
        Agenda agen = (Agenda) agenda;
        try {
            conexion = Conexion.obtenerConexion();
            statement = conexion.prepareStatement(SQL_UPDATE);

            statement.setString(1, agen.getDesccripcion());
            statement.setString(2, agen.getFecha());
            statement.setInt(3, agen.getIdNota());

            rows = statement.executeUpdate();

            Conexion.cerrarConexion(statement);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;
    }//revisado y todo correcto

    @Override
    public int borrar(Object agenda) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        int rows = 0;
        Agenda agen = (Agenda) agenda;
        try {
            conexion = Conexion.obtenerConexion();
            //////////////////
            stmt = conexion.prepareStatement(SQL_DELETE);

            stmt.setInt(1, agen.getIdNota());

            rows = stmt.executeUpdate();

            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return rows;

        
    }//revisado y todo correcto

    @Override
    public Object seleccionar(Object agenda) {
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Agenda agen = (Agenda) agenda; //el objeto que se pasa por parámetro
        Agenda agenDevuelta = null; //y el objeto que será devuelto
        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_SELECT_UNIC); //para seleccionar una Agenda especifica

            stmt.setString(1, agen.getDesccripcion());
            stmt.setString(2, agen.getFecha());

            rs = stmt.executeQuery();

            //obteniendo los resultados de la consulta y almacendandolos en variables para crear el objeto de tipo Usuario
            while (rs.next()) {
                
                int idAgenda = rs.getInt("idAgenda");
                String descripcion = rs.getString("descripcion");
                String fecha = rs.getString("fecha");
                int idnota = rs.getInt("idnota");

                //Se crea el objeto
                agenDevuelta = new Agenda(idAgenda, descripcion, fecha,idnota);
            }
            
            //se cierran las conexiones abiertas
            Conexion.cerrarConexion(rs);
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return agenDevuelta; //se retorna el objeto
    }//modificado y todo correcto

    public List<Object> seleccionarRelacion(int relacion){
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Object> lista = new ArrayList<>();
      
        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_SELECT_REL);
            stmt.setInt(1, relacion);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idAgenda = rs.getInt("idAgenda");
                String descripcion = rs.getString("descripcion");
                String fecha = rs.getString("fecha");
                int idnota = rs.getInt("idnota");
                
                Agenda nota = new Agenda(idAgenda,descripcion,fecha,idnota);
                lista.add(nota);
            }
            
            //se cierran las conexiones
            Conexion.cerrarConexion(rs);
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return lista;
        
    }

}
