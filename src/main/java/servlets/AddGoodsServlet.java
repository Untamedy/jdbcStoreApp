/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.commonInit.Init;
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
@WebServlet("/addGoods")
public class AddGoodsServlet extends HttpServlet{
    private StoreService service;
    private Init init;
    
    public AddGoodsServlet(){
        this.init = new Init();
        this.service = init.getConnection();
    }
    
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("addGoods.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int articul =Integer.valueOf(request.getParameter("articul"));
        service.addGoods(name, articul);
        RequestDispatcher dispatcher=request.getRequestDispatcher("addresult.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }
    
}
