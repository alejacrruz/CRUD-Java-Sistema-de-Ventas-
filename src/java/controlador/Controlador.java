package controlador;

import config.GenerarSerie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dao.ClienteDAO;
import modelo.dto.EmpleadoDTO;
import modelo.dao.EmpleadoDAO;
import modelo.dao.ProductoDAO;
import modelo.dao.VentaDAO;
import modelo.dto.ClienteDTO;
import modelo.dto.ProductoDTO;
import modelo.dto.VentaDTO;


public class Controlador extends HttpServlet {

    EmpleadoDTO em = new EmpleadoDTO();
    EmpleadoDAO edao = new EmpleadoDAO();
    ProductoDTO pro = new ProductoDTO();
    ProductoDAO pdao = new ProductoDAO();
    ClienteDTO cli = new ClienteDTO();
    ClienteDAO cdao = new ClienteDAO();
    int ide;

    VentaDTO ven = new VentaDTO();
    List<VentaDTO> listav = new ArrayList<>();
    int item;
    int cod;
    String des;
    double precio;
    int cant;
    double subtotal;
    double total;

    String numserie;
    VentaDAO vdao = new VentaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.read();//guarda todos los datos recogidos en una lista
                    request.setAttribute("empleados", lista);//envia la informacion con el nombre empleados mediante lista
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");//obtiene los datos que se registran en el formulario
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTelefono");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUsuario");
                    em.setDni(dni);//guardamos los datos obtenidos en el objeto de empleado
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.create(em);//enviamos el objeto al metodo de agregar(create)
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));//captura el id de la fila seleccionada 
                    EmpleadoDTO e = edao.listarId(ide);//obtenemos los datos del id que se ha selecconado
                    request.setAttribute("empleado", e);//enviamos al formulario con el nombre empleado de la variable e
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");//obtiene los datos que se registran en el formulario
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTelefono");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUsuario");
                    em.setDni(dni1);//guardamos los datos obtenidos en el objeto de empleado
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.update(em);//llamamos el método para actualizar
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));//capturamos el id de la fila 
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            switch (accion) {
                case "Listar":
                    List lista = cdao.read();//guarda todos los datos recogidos en una lista
                    request.setAttribute("clientes", lista);//envia la informacion con el nombre clientes mediante lista
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");//obtiene los datos que se registran en el formulario
                    String nom = request.getParameter("txtNombres");
                    String dir = request.getParameter("txtDireccion");
                    String est = request.getParameter("txtEstado");
                    cli.setDni(dni);//guardamos los datos obtenidos en el objeto de cliente
                    cli.setNom(nom);
                    cli.setDir(dir);
                    cli.setEstado(est);
                    cdao.create(cli);//enviamos el objeto al metodo de agregar(create)
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));//captura el id de la fila seleccionada 
                    ClienteDTO c = cdao.listarId(ide);//obtenemos los datos del id que se ha selecconado
                    request.setAttribute("cliente", c);//enviamos al formulario con el nombre empleado de la variable e
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");//obtiene los datos que se registran en el formulario
                    String nom1 = request.getParameter("txtNombres");
                    String dir1 = request.getParameter("txtDireccion");
                    String est1 = request.getParameter("txtEstado");
                    cli.setDni(dni1);//guardamos los datos obtenidos en el objeto de cliente
                    cli.setNom(nom1);
                    cli.setDir(dir1);
                    cli.setEstado(est1);
                    cli.setId(ide);
                    cdao.update(cli);//llamamos el método para actualizar
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));//capturamos el id de la fila 
                    cdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List lista = pdao.read();//guarda todos los datos recogidos en una lista
                    request.setAttribute("productos", lista);//envia la informacion con el nombre productos mediante lista
                    break;
                case "Agregar":
                    String nom = request.getParameter("txtNombrePro");//obtiene los datos que se registran en el formulario
                    double pre = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock = Integer.parseInt(request.getParameter("txtStock"));
                    String estado = request.getParameter("txtEstadoPro");
                    pro.setNom(nom);//guardamos los datos obtenidos en el objeto de producto
                    pro.setPre(pre);
                    pro.setStock(stock);
                    pro.setEstado(estado);
                    pdao.create(pro);//enviamos el objeto al metodo de agregar(create)
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;

                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    ProductoDTO p = pdao.listarId(ide);//obtenemos los datos del id que se ha selecconado
                    request.setAttribute("producto", p);//enviamos al formulario con el nombre producto de la variable e
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String nom1 = request.getParameter("txtNombrePro");//obtiene los datos que se registran en el formulario
                    double pre1 = Double.parseDouble(request.getParameter("txtPrecio"));
                    int stock1 = Integer.parseInt(request.getParameter("txtStock"));
                    String estado1 = request.getParameter("txtEstadoPro");
                    pro.setNom(nom1);//guardamos los datos obtenidos en el objeto de producto
                    pro.setPre(pre1);
                    pro.setStock(stock1);
                    pro.setEstado(estado1);
                    pro.setId(ide);
                    pdao.update(pro);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));//capturamos el id de la fila seleccionada
                    pdao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");//obtiene el parametro guardado en el formulario
                    cli.setDni(dni);
                    cli = cdao.buscar(dni);//realiza la busqueda                    
                    request.setAttribute("c", cli);//envia la informacion
                    request.setAttribute("nserie", numserie);
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));//obtiene el paramaetro guardado en ese input del formulario
                    pro.setId(id);
                    pro = pdao.listarId(id);//realiza la busqueda
                    request.setAttribute("p", pro);//envia la info
                    request.setAttribute("lista", listav);//enviamos la info de la lista
                    request.setAttribute("total", total);
                    request.setAttribute("c", cli);//envia la informacion 
                    request.setAttribute("nserie", numserie);

                    break;
                case "Agregar":
                    total = 0.0;
                    item = item + 1;
                    cod = pro.getId();
                    des = request.getParameter("nombresproducto");//obtenemos los datos enviamos desde el formulario
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    ven = new VentaDTO();
                    ven.setItem(item);//enviamos los datos a sus respectivos sets
                    ven.setIdProducto(cod);
                    ven.setDescripcionP(des);
                    ven.setPrecio(precio);
                    ven.setCantidad(cant);
                    ven.setSubTotal(subtotal);
                    listav.add(ven);//añadimos el objeto a la lista
                    for (int i = 0; i < listav.size(); i++) {
                        total = total + listav.get(i).getSubTotal();
                    }
                    request.setAttribute("c", cli);//envia la informacion 
                    request.setAttribute("total", total);
                    request.setAttribute("lista", listav);//enviamos la info
                    request.setAttribute("nserie", numserie);
                    break;
                case "GenerarVenta":
                    //Actualizar Stock
                    for (int i = 0; i < listav.size(); i++) {
                        ProductoDTO pr = new ProductoDTO();
                        int cant = listav.get(i).getCantidad();
                        int idpro = listav.get(i).getIdProducto();
                        ProductoDAO prdao = new ProductoDAO();
                        pr=prdao.buscar(idpro);
                        int sac = pr.getStock()-cant;
                        prdao.actualizarStock(idpro, sac);
                    }
                    //Guardar venta
                    ven.setIdCliente(cli.getId());
                    ven.setIdEmpleado(1);
                    ven.setNumSerie(numserie);
                    ven.setFecha("2019-08-09");
                    ven.setPrecio(total);
                    ven.setEstado("1");
                    vdao.guardarVenta(ven);
                    //Guardar detalle venta
                    int idVentas = Integer.parseInt(vdao.idVentas());
                    for (int i = 0; i < listav.size(); i++) {
                        ven = new VentaDTO();
                        ven.setId(idVentas);
                        ven.setIdProducto(listav.get(i).getIdProducto());
                        ven.setCantidad(listav.get(i).getCantidad());
                        ven.setPrecio(listav.get(i).getPrecio());
                        vdao.guardarDetalleVentas(ven);
                    }
                    listav = new ArrayList<>();
                    request.getRequestDispatcher("Controlador?menu=NuevaVenta&accion=default").forward(request, response);
                    break;
                                   
                default:
                    numserie = vdao.generarSerie();
                    if (numserie == null) {
                        numserie = "00000001";
                        request.setAttribute("nserie", numserie);
                    } else {
                        int acum = Integer.parseInt(numserie);
                        GenerarSerie gs = new GenerarSerie();
                        numserie = gs.numeroSerie(acum);
                        request.setAttribute("nserie", numserie);
                    }
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
