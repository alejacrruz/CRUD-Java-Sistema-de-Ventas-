package modelo.dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.EmpleadoDTO;


public class EmpleadoDAO implements Contrato<EmpleadoDTO> {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public EmpleadoDTO validar(String user, String dni) {//Metodo para enviar recoger los datos desde la bd y enviarlos
        EmpleadoDTO em = new EmpleadoDTO();
        String sql = "SELECT * FROM empleado WHERE User=? AND Dni=?";
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                em.setId(rs.getInt("IdEmpleado"));
                em.setUser(rs.getString("User"));
                em.setDni(rs.getString("Dni"));
                em.setNom(rs.getString("Nombres"));
            }
        } catch (SQLException ex) {
        }

        return em;
    }

    //Operaciones CRUD
    @Override
    public List read() {
        String sql = "SELECT * FROM empleado";
        List<EmpleadoDTO> lista = new ArrayList<>();//creamos una lista de tipo Empleado
        try {
            con = cn.conexion();//crea la conexion
            ps = con.prepareStatement(sql);//recibe la consulta
            rs = ps.executeQuery();//Ejecuta la consulta
            while (rs.next()) {//ciclo para obtener todos los datos
                EmpleadoDTO em = new EmpleadoDTO();
                em.setId(rs.getInt(1));
                em.setDni(rs.getString(2));
                em.setNom(rs.getString(3));
                em.setTel(rs.getString(4));
                em.setEstado(rs.getString(5));
                em.setUser(rs.getString(6));
                lista.add(em);//agregamos a la list todos los datos obtenidos de la bd 
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
            }
        }
        return lista;
    }

    @Override
    public int create(EmpleadoDTO em) {
        String sql = "INSERT INTO empleado(Dni, Nombres, Telefono, Estado, User)VALUES(?, ?, ?, ?, ?) ";
        try {
            con = cn.conexion();//crea la conexion
            ps = con.prepareStatement(sql);//recibe la consulta
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.executeUpdate();//Ejecuta la consulta
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
            } catch (SQLException ex) {
            }
        }
        return r;
    }

    @Override
    public EmpleadoDTO listarId(int id) {
        EmpleadoDTO emp = new EmpleadoDTO();
        String sql = "SELECT * FROM empleado WHERE IdEmpleado=" + id;
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                emp.setDni(rs.getString(2));
                emp.setNom(rs.getString(3));
                emp.setTel(rs.getString(4));
                emp.setEstado(rs.getString(5));
                emp.setUser(rs.getString(6));
            }

        } catch (Exception e) {

        }
        return emp;
    }

    @Override
    public int update(EmpleadoDTO em) {
        String sql = "UPDATE empleado SET Dni = ?, Nombres = ?, Telefono = ?, Estado = ?, User = ?"
                + " WHERE IdEmpleado = ?";
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getDni());
            ps.setString(2, em.getNom());
            ps.setString(3, em.getTel());
            ps.setString(4, em.getEstado());
            ps.setString(5, em.getUser());
            ps.setInt(6, em.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }

        return r;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM empleado WHERE IdEmpleado=" + id;
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
