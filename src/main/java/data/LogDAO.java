package data;

import java.sql.*;

public class LogDAO {
    
    private static final String SQL_SELECT = "SELECT fecha FROM Log WHERE idLog = ?";
    private static final String SQL_UPDATE = "UPDATE Log SET fecha= ? WHERE idLog=?";
    private static final String SQL_INSERT = "INSERT INTO Log (idLog, fecha) VALUES (?,?)";
    
    
    public LogDAO(){}
    
    public String obtenerFecha(int idLog){ //método para obtener la última fecha de conexión del usuario
        Connection conexion = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String fecha = null;
        
        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_SELECT);
            stmt.setInt(1, idLog);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                fecha = rs.getString("fecha");
            }
            
            Conexion.cerrarConexion(rs);
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return fecha;
    }
    
    public int actualizarFecha(String fecha, int idLog){ //método para actualizar la fecha del usuario
        Connection conexion = null;
        PreparedStatement stmt = null;
        int filas = 0;
        
        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_UPDATE);
            stmt.setString(1, fecha);
            stmt.setInt(2, idLog);
            filas = stmt.executeUpdate();
            
            
            //Se cierran las conexiones
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        return filas;
                
    }
    
    public int nuevaFecha(String fecha, int idLog){
        Connection conexion = null;
        PreparedStatement stmt = null;
        int filas = 0;
        
        try {
            conexion = Conexion.obtenerConexion();
            stmt = conexion.prepareStatement(SQL_INSERT);
            stmt.setInt(1,idLog);
            stmt.setString(2, fecha);
            filas = stmt.executeUpdate();
            
            //cierre de conexiones
            Conexion.cerrarConexion(stmt);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return filas;
    }
    
}
