package data;

import java.sql.*;
import java.util.List;

public interface IUsuarioDAO {
    
    /*
        Requerimientos de la interfaz IUsuarioDAO
        - debe contener una lista de métodos abstractos que servirán de modelo para las clases que implementarán esta interfaz
    */
    
    List<Object> obtenerTodos() throws SQLException; //método para obtener todos los registros de una tabla
    
    int insertar(Object objeto)throws SQLException; //método para agregar un nuevo registro
    
    int actualizar(Object objeto)throws SQLException; //método para actualizar un registro de la tabla
    
    int borrar(Object objeto)throws SQLException; //método para eliminar un registro de la tabla
    
    Object seleccionar(Object objeto) throws SQLException; //
    
}
