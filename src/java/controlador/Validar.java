package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.dto.EmpleadoDTO;
import modelo.dao.EmpleadoDAO;


public class Validar extends HttpServlet {

    EmpleadoDAO edao = new EmpleadoDAO();
    EmpleadoDTO em = new EmpleadoDTO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion.equalsIgnoreCase("Ingresar")) {//si le da al boton ingresar recoge el user y el pass
            String user = request.getParameter("txtuser");
            String pass = request.getParameter("txtpass");
            em = edao.validar(user, pass);//llama al metodo que valida con la bd
            if (em.getUser() != null) {//verifica que el usuario exista
                request.setAttribute("usuario", em);//crea una variable de tipo em para poder acceder a los datos desde cualquier jsp
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);//redirecciona a la pagina principal si la condicion se cumple
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);//de lo contrario, vuelve al index
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
