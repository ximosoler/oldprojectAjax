/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.oldproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author a073225297j
 */
public class Sesiones extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Gson oGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

            String op = "";
            op = request.getParameter("op");

            String login = "";
            login = request.getParameter("login");

            String pass = "";
            pass = request.getParameter("pass");

            if (op.equalsIgnoreCase("login")) {
                if (pass.equalsIgnoreCase("ausias")) {

                    HttpSession oSession = request.getSession();

                    Usuario oUsuario = new Usuario();
                    oUsuario.setLogin(login);
                    oUsuario.setPass(pass);

                    oSession.setAttribute("activeUser", oUsuario);

                    out.print(oGson.toJson(oUsuario));
                } else {
                    out.print(oGson.toJson("Datos de login o password incorrectos"));
                }
            }

            if (op.equalsIgnoreCase("logout")) {
                HttpSession oSession = request.getSession();
                oSession.invalidate();
                out.print(oGson.toJson("Bye"));
            }

            if (op.equalsIgnoreCase("calcular")) {
                HttpSession oSession = request.getSession();
                Usuario oUsuario = (Usuario) oSession.getAttribute("activeUser");
                if (oUsuario == null) {
                    out.print(oGson.toJson("No active session currently"));
                } else {
                    int op1 = Integer.parseInt(request.getParameter("op1"));
                    int op2 = Integer.parseInt(request.getParameter("op2"));
                    String operacion = request.getParameter("operacion");
                    int resultado = 0;

                    switch (operacion) {
                        case "add":
                            resultado = op1 + op2;
                            break;
                        case "minus":
                            resultado = op1 - op2;
                            break;
                        case "mult":
                            resultado = op1 * op2;
                            break;
                        case "div":
                            resultado = op1 / op2;
                            break;
                    }

                    out.print(oGson.toJson(resultado));
                }
            }

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
     *
     * }
     * }
     *
     *
     * /**
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
