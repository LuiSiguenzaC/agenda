package data;

import java.sql.*;

public class Conexion {
    
    private static final String JDBC_URL2 = "jdbc:mariadb://u3r5w4ayhxzdrw87.cbetxkdyhwsb.us-east-1.rds.amazonaws.com:3306/wvng6e6dosnx9f39";
    
    private static final String JDBC_USER = "h9m47r1yahu92vxr";
    
    private static final String JDBC_PASSWORD = "dgc2hrwysjsck7s1";
    
    
    public static Connection obtenerConexion() throws SQLException{
        
        return DriverManager.getConnection(JDBC_URL2, JDBC_USER,JDBC_PASSWORD);
    }
    
    //métodos para cerrar las conexiones que se vayan a abrir
    
    public static void cerrarConexion(Connection conexion){
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void cerrarConexion(Statement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void cerrarConexion(PreparedStatement stmt){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    public static void cerrarConexion(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}