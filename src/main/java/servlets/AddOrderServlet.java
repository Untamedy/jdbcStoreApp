/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.commonInit.Init;
import com.store.entities.Client;
import com.store.entities.Goods;
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
@WebServlet("/addOrder")
public class AddOrderServlet extends HttpServlet{
    private StoreService service;
    private Init init;
    
    public AddOrderServlet(){
        this.init = new Init();
        this.service = init.getConnection();
    }
       
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        List<Client> clients = service.getAllClient();
        List<Goods> goods = service.getAllGoods();
        
        request.setAttribute("clients", clients);
        request.setAttribute("goods", goods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addOrder.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("client");
        String  goods = request.getParameter("goods");
       // service.addOrder(name, goods);
        RequestDispatcher dispatcher=request.getRequestDispatcher("addresult.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
}
