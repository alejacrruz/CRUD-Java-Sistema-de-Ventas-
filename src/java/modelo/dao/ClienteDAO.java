package modelo.dao;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.ClienteDTO;


public class ClienteDAO implements Contrato<ClienteDTO> {

    PreparedStatement ps;
    Conexion cn = new Conexion();
    Connection con;
    ResultSet rs;
    int r;

    public ClienteDTO buscar(String dni) {
        ClienteDTO c = new ClienteDTO();
        String sql = "SELECT * FROM cliente WHERE DNI=" + dni;
        try {
            con = cn.conexion();//Crea la conexion
            ps = con.prepareStatement(sql);//Recibe la consulta
            rs = ps.executeQuery();//Ejecuta la consulta
            while(rs.next()){
                c.setId(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDir(rs.getString(4));
                c.setEstado(rs.getString(5));
            }
        } catch (Exception e) {

        }
        return c;
    }
//Operaciones CRUD

    @Override
    public int create(ClienteDTO cli) {
        String sql = "INSERT INTO cliente(Dni, Nombres, Direccion, Estado) VALUES (?, ?, ?, ?)";
        try {
            con = cn.conexion();//Crea la conexion
            ps = con.prepareStatement(sql);//Recibe la consulta
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDir());
            ps.setString(4, cli.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;
    }

    @Override
    public List read() {
        String sql = "SELECT * FROM cliente";
        List<ClienteDTO> lista = new ArrayList<>();//creamos una lista de tipo cliente
        try {
            con = cn.conexion();//crea la conexion
            ps = con.prepareStatement(sql);//recibe la consulta
            rs = ps.executeQuery();//Ejecuta la consulta
            while (rs.next()) {//ciclo para obtener todos los datos
                ClienteDTO cli = new ClienteDTO();
                cli.setId(rs.getInt(1));
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEstado(rs.getString(5));

                lista.add(cli);//agregamos a la list todos los datos obtenidos de la bd 
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public ClienteDTO listarId(int id) {
        ClienteDTO cli = new ClienteDTO();
        String sql = "SELECT * FROM cliente WHERE IdCliente=" + id;
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEstado(rs.getString(5));
            }
        } catch (Exception e) {

        }
        return cli;
    }

    @Override
    public int update(ClienteDTO cli) {
        String sql = "UPDATE cliente SET Dni = ?, Nombres = ?, Direccion = ?, Estado = ?"
                + " WHERE IdCliente = ?";
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDir());
            ps.setString(4, cli.getEstado());
            ps.setInt(5, cli.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }

        return r;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cliente WHERE IdCliente=" + id;
        try {
            con = cn.conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
