package web;

import data.LogDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//Esta clase ha sido mapeada en el documento web.xml como una clase oyente
public class AsignacionFecha implements HttpSessionListener{
    
    
    //Este evento será llamado cuando la sesión sea destruída, ya sea directemente por el usuario, o porque
    //ah alcanzado el límite de tiempo de inactividad.
    @Override
    public void sessionDestroyed(HttpSessionEvent evt){
        HttpSession misesion = evt.getSession(); //obtenemos la sesión actualmente activa
        int idUsuario = (Integer) misesion.getAttribute("id"); //obtenemos el id del usuario que acaba de abandonar la sesión
        Date fechaActual = new Date(); //se crea una instancia de la fecha actual
        SimpleDateFormat formateador = new SimpleDateFormat("EEE.dd ' ' HH.mm a"); //formateamos la salida de la fecha
        String fecha = formateador.format(fechaActual);//aplicamos el formato
        LogDAO log = new LogDAO();
        String fechaAsociada = log.obtenerFecha(idUsuario); //revisamos si hay una fecha o no asignada a este usuario
        
        if(fechaAsociada == null){
            log.nuevaFecha(fecha, idUsuario); //si no hay fecha, se le crea una nueva
        }else{
            log.actualizarFecha(fecha, idUsuario);// si ya había fecha, se actualiza la anterior
        }
    }
}
