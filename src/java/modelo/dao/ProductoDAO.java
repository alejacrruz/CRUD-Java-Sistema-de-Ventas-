package modelo.dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.ProductoDTO;


public class ProductoDAO implements Contrato<ProductoDTO> {

    Conexion cn = new Conexion();
    Connection con;
    ResultSet rs;
    int r;

    
    public ProductoDTO buscar(int id){
        ProductoDTO pro = new ProductoDTO();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM producto WHERE IdProducto="+id;
        try{
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                pro.setId(rs.getInt(1));
                pro.setNom(rs.getString(2));
                pro.setPre(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
            }
        }catch(Exception e){
            System.out.println("Error Buscar Producto"+e.getMessage());
        } 
        return pro;
    }
    public int actualizarStock(int id, int stock){
        PreparedStatement ps = null;
        String sql = "UPDATE producto SET Stock=? WHERE IdProducto=?";
        try{
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error actualizar Stock "+e.getMessage());
        }
        return r;
    }
  //Operaciones CRUD

    @Override
    public int create(ProductoDTO pro) {
        String sql = "INSERT INTO producto(Nombres, Precio, Stock, Estado)VALUES(?, ?, ?, ?)";
        PreparedStatement ps = null;
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setDouble(2, pro.getPre());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return r;
    }

    @Override
    public List read() {
        PreparedStatement ps = null;
        String sql = "SELECT * FROM producto";
        List<ProductoDTO> lista = new ArrayList<>();//creamos una lista de tipo Empleado
        try {
            con = cn.conexion();//crea la conexion
            ps = con.prepareStatement(sql);//recibe la consulta
            rs = ps.executeQuery();//Ejecuta la consulta
            while (rs.next()) {//ciclo para obtener todos los datos
                ProductoDTO pro = new ProductoDTO();
                pro.setId(rs.getInt(1));
                pro.setNom(rs.getString(2));
                pro.setPre(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
                lista.add(pro);//agregamos a la list todos los datos obtenidos de la bd 
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
            } catch (Exception ex) {
            }
        }
        return lista;
    }

    @Override
    public ProductoDTO listarId(int id) {
        PreparedStatement ps = null;
        ProductoDTO pro = new ProductoDTO();
        String sql = "SELECT * FROM producto WHERE IdProducto=" + id;
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pro.setId(id);
                pro.setNom(rs.getString(2));
                pro.setPre(rs.getDouble(3));
                pro.setStock(rs.getInt(4));
                pro.setEstado(rs.getString(5));
            }

        } catch (Exception e) {
        }
        return pro;
    }

    @Override
    public int update(ProductoDTO pro) {
        PreparedStatement ps = null;
        String sql = "UPDATE producto SET Nombres = ?, Precio = ?, Stock = ?, Estado = ?"
                + " WHERE IdProducto = ?";
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNom());
            ps.setDouble(2, pro.getPre());
            ps.setInt(3, pro.getStock());
            ps.setString(4, pro.getEstado());
            ps.setInt(5, pro.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    @Override
    public void delete(int id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM producto WHERE IdProducto=" + id;
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
