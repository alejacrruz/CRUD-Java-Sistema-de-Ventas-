
package config;


import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {//se crea la conexion con la bd
    private Connection con;
    private String url="jdbc:mysql://localhost:3306/bd_ventas";
    private String user="root";
    private String pass="";
    public Connection conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url, user, pass);
        }catch(Exception e){
            
        } 
        return con;
    }
}
