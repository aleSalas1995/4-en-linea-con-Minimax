/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Logica.Controlador;
import Logica.Minimax;
import Logica.Tablero;
import Logica.Validaciones;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alejandro
 */
@WebServlet(name="InformacionMovimientos", urlPatterns = {"/InformacionMovimientos"})
public class InformacionMovimientos extends HttpServlet {
    public Tablero t = Tablero.saberEstado();//Instacia tablero
    Validaciones v = new Validaciones();//Instancia validaciones 
    Minimax m = Minimax.saberEstado();
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InformacionMovimientos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InformacionMovimientos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
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
        //Inicializacion del timer java para ver cuanto dura cada movimiento
        Instant inicio = Instant.now();
        
        //Obtiene los parametros de las pantallas JSP
        String profundidad =request.getParameter("hdnbt");
        if(profundidad!=null){
            m.setMaxProfundidad(Integer.parseInt(profundidad));
        }
        
        
        String empate =request.getParameter("empate"); 
        String gane =request.getParameter("gane");
        String opcion =request.getParameter("bt"); 
        
        //Guardar el nombre del jugador 1
        String nombre =request.getParameter("txtNombre");
        t.setNombre(nombre);
        
        //Valida que si hay empate o hay gane
        if(empate!=null && empate.equals("0")||gane!=null && gane.equals("0")){
            t.limpiar();//Limpia la matriz
            empate="1";//reinicia varaible
            gane="1";//reinicia varaible
            opcion=null;//reinicia varaible
            request.getRequestDispatcher("/PaginaWEB.jsp").forward(request, response);//llama a la pagina principal para reiniciar 
        }
        //Si la opcion es null es porque viene de la pagina principal y llama al jsp jugadas para iniciar a elegir columna
        else if(opcion==null){
            request.getRequestDispatcher("/Jugadas.jsp").forward(request, response);


        }
        else{
            Controlador m = new Controlador();
            if(t.jugador.equals("1")&&t.inserto==null||t.jugador.equals("1")&&t.inserto.equals("0")){
                int x = Integer.parseInt(opcion);//obtiene la columna que elige el usuario
                //Instancia de la clase controlador 
                m.moverFichaUsuario(opcion);//llama a la funcion que se encarga de mover la ficha del usuario

                //Funcion que valida si el tablero esta lleno y llama a pantalla de empate
                if(v.hayGanador(t)==0){
                    request.getRequestDispatcher("/pantallaEmpato.jsp").forward(request, response);
                }
                //Funcion que valida si el jugador 1 gano y le pasa la columna y fila que inserto
                else if(v.hayGanador(t)==1){
                    v.llenarMatriz(v.registro1);
                    request.getRequestDispatcher("/pantallaGano.jsp").forward(request, response);
                }
                else{
                    //request.getRequestDispatcher("/Jugadas.jsp").forward(request, response);
                }
            }
            
            
            //Valida si el jugador 1 inserto
            
            if(t.jugador.equals("2")&&t.inserto==null||t.jugador.equals("2")&&t.inserto.equals("1")){
                //Si inserto y es jugador 2 tons mueve la ficha de la compu
                m.moverFichaCompu();
                Instant fin = Instant.now();//Finaliza contador de tiempo
                System.out.println("Duracion jugada: "+Duration.between(inicio, fin));
                
                
                //Valida si el tablero esta lleno para que haya un empate y llama a empate
                if(v.hayGanador(t)==0){
                    request.getRequestDispatcher("/pantallaEmpato.jsp").forward(request, response);
                }
                //Valida si al insertar la compu esta formo el 4 en linea y le pasa la columna y fila insertada
                //Si se cumple llama a gano
                
                else if(v.hayGanador(t)==2){
                    v.llenarMatriz(v.registro2);
                    request.getRequestDispatcher("/pantallaGano.jsp").forward(request, response);
                }
                else{
                    //Sino se cumple nada llama a jugadas para realizar la siguiente movida
                    request.getRequestDispatcher("/Jugadas.jsp").forward(request, response);
                }
            }
            
            else{
                //Si el usario inserto en una invalida este le devuelve para que vuelva a elegrir columna
                request.getRequestDispatcher("/Jugadas.jsp").forward(request, response);
            }
        }        
        response.setContentType("text/html;charset=UTF-8");
    }
}
