package modelo.dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.dto.VentaDTO;


public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r; 
    public String generarSerie() {
        String numserie = "";
        String sql = "SELECT max(NumeroSerie) FROM ventas";
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                numserie = rs.getString(1);
            }
        } catch (Exception x) {
            System.out.println("Error Generar Serie " + x.getMessage());
        }
        return numserie;
    }
    public String idVentas(){
        String idventas="";
        String sql = "SELECT MAX(IdVentas) FROM ventas";
        try{
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                idventas = rs.getString(1);
            }
        }catch(Exception e){
            System.out.println("Error idVentas "+e.getMessage());
        }
        return idventas;
    }
    public  int guardarVenta(VentaDTO ve){
        String sql = "INSERT INTO ventas(IdCliente, IdEmpleado, NumeroSerie, FechaVentas, Monto, Estado)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
        try{
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getIdCliente());
            ps.setInt(2, ve.getIdEmpleado());
            ps.setString(3, ve.getNumSerie());
            ps.setString(4, ve.getFecha());
            ps.setDouble(5, ve.getPrecio());
            ps.setString(6, ve.getEstado());
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error guardarventa "+e.getMessage());
        }
        return r;
    }
    public int guardarDetalleVentas(VentaDTO ve){
        String sql = "INSERT INTO detalle_ventas (IdVentas, IdProducto, Cantidad, PrecioVenta) VALUES(?, ?, ?, ?)";
        try{
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getId());
            ps.setInt(2, ve.getIdProducto());
            ps.setInt(3, ve.getCantidad());
            ps.setDouble(4, ve.getPrecio()); 
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error DetalleVentas"+e.getMessage());
        }
        return r;
    }
}
