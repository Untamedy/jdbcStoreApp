/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.servlets;

import com.store.commonInit.Init;
import com.store.entities.Client;
import com.store.entities.Goods;
import com.store.services.StoreService;
import java.io.IOException;
import java.util.ArrayList;

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
public class AddOrderServlet extends HttpServlet {

    private StoreService service;
    private Init init;

    public AddOrderServlet() {
        this.init = new Init();
        this.service = init.getConnection();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String[] clientData = request.getParameter("client").split(",");
        String name = clientData[0];
        String[] goodsData = request.getParameterValues("goods");
        List<Goods> goods = new ArrayList<>();
        for (String s : goodsData) {
            Goods g = service.getGoodsByArticul(Integer.valueOf(s));
            goods.add(g);
        }
        service.addOrder(name, goods);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(AddClientServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
