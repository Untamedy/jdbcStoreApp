/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.servlets;

import com.store.commonInit.Init;
import com.store.services.StoreService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet("/addClient")
public class AddClientServlet extends HttpServlet{
    
    private StoreService service;
    private Init init;
    
    public AddClientServlet(){
        this.init = Init.getInit();
        this.service = init.getService();
    }
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("addClient.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String phoneNumber = request.getParameter("phoneNumber");
        service.addNewClient(name, phoneNumber);
        RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
}
