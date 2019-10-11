/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.services;

import com.store.commonInit.ConnectionService;
import com.store.commonInit.InputData;
import com.store.entities.Client;
import com.store.entities.Goods;
import com.store.entities.Order;
import com.store.services.ClientService;
import com.store.services.GoodsService;
import com.store.services.OrderService;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class StoreService {

    private static final Logger LOGGER = Logger.getLogger(InputData.class.getName());

    private ClientService clientService;
    private GoodsService goodsService;
    private OrderService orderService;
    private ConnectionService connectionservice;
    private Connection connection;

    public StoreService() {
    }

    public StoreService(ConnectionService connectionservice) {
        this.connectionservice = connectionservice;
        this.connection = connectionservice.getConnection();
        this.goodsService = new GoodsService(connection);
        this.orderService = new OrderService(connection);
        this.clientService = new ClientService(connection);
    }

    public void addNewClient(String name, String phoneNum) {
       
        clientService.addClient(name, phoneNum);
    }

    public void addGoods(String name, int articul) {
        
        goodsService.addGoods(name, articul);
    }

    public void addOrder(String clPhone, List<Goods> orderList) {
        
        orderService.addOrder(clPhone, orderList);

    }

    public Order getOrder(String code) {
        
        return orderService.getOrder(code);
    }

    public List<Goods> getDataToOrder(int start, int end) throws SQLException {
        List<Goods> sublistFortest = goodsService.getGoodsSublist(start, end);
        return sublistFortest;

    }

    public List<Client> getAllClient()  {      
       List<Client> clients =  clientService.getAll();
       return clients;
        
    }

    public List<Goods> getAllGoods()  {            
       List<Goods> goods =  goodsService.getAll();
       return goods;

    }

    public Goods getGoodsByArticul(int value) {       
               return  goodsService.getByArticul(value);
    }

    public List<Order> getAllOrder() {          
      return  orderService.getAll();
    }
    
    public List<Goods> getByOrderCode(int id){        
        return goodsService.getGoodsByOrdersId(id);
    }
}
