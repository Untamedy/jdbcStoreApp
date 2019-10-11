/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.servlets;

import com.store.commonInit.Init;
import com.store.entities.Goods;
import com.store.entities.Order;
import com.store.services.GoodsService;
import com.store.services.StoreService;
import java.io.IOException;
import java.util.List;
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
@WebServlet("/getOrder")
public class GetOrderServlet extends HttpServlet{
    
     private StoreService service;
    private Init init;
    
    public GetOrderServlet(){
        this.init = new Init();
        this.service = init.getConnection();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        List<Order> orders = service.getAllOrder();
        request.setAttribute("list", orders);
         RequestDispatcher dispatcher = request.getRequestDispatcher("getOrder.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        Order order = service.getOrder(code);     
        if(null!=order){
            List<Goods> goods = service.getByOrderCode(order.getId());           
            request.setAttribute("list", goods);
         
              RequestDispatcher dispatcher = request.getRequestDispatcher("order.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(GetOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(GetOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
