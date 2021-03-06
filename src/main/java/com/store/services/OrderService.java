package com.store.services;

import com.store.entities.Client;
import com.store.entities.Goods;
import com.store.entities.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class OrderService {

    private static final Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    private final Connection connection;
    private ClientService clientService;
    private GoodsService goodsService;

    public OrderService(Connection connection) {
        this.connection = connection;
        this.clientService = new ClientService(connection);
        this.goodsService = new GoodsService(connection);
    }

    private final String addOrder = "insert into mydb.orders (code,client_id) values(?,?)";
    private final String addGoodsToOrder = "insert into mydb.orders_goods (order_id, goods_id) values(?,?)";
    private final String selectOrderByCode = "select * from mydb.orders where code=?";

    public void addOrder(String clPhoneNum, List<Goods> orderList) {
        Client client = clientService.isExists(clPhoneNum);
        ResultSet resultSet = null;
        PreparedStatement statement;
        String code = generateCode();
        Order order = isExists(code);
        if (order == null) {
            if (null != client) {
                try {
                    statement = connection.prepareStatement(addOrder);
                    statement.setString(1, code);
                    statement.setInt(2, client.getId());
                    statement.execute();

                } catch (SQLException ex) {
                    LOGGER.warning(ex.getMessage());
                }
            } else {
                LOGGER.log(Level.INFO, "You should to create client with this phonenumber - {0}", clPhoneNum);
            }
        }

        int orderId;
        try {
            Statement statement1 = connection.createStatement();
            ResultSet resultSet1 = statement1.executeQuery("select * from mydb.orders where code =" + "'" + code + "'");
            if (resultSet1.next()) {
                orderId = resultSet1.getInt("id");
                for(Goods g: orderList){
                    try {
                        addToOrderGoods(orderId, g);
                    } catch (SQLException ex) {
                        Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }                           }
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addToOrderGoods(int ordId, Goods goods) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(addGoodsToOrder);
        Goods g = goodsService.getByArticul(goods.getArticul());
        if (null != g) {
            statement.setInt(1, ordId);
            statement.setInt(2, g.getId());
            statement.execute();
        } else {
            LOGGER.info("You should create goods with articul =" + goods.getArticul());
        }
    }

    public Order getOrder(String code) {
       Order order = new Order(); 
        try {
             PreparedStatement statement = connection.prepareStatement(selectOrderByCode);
            statement.setObject(1, code);
            ResultSet resultSet = statement.executeQuery();          
            if (resultSet.next()) {
                order.setCode(resultSet.getString("code"));
                order.setCustomer(clientService.getById(resultSet.getInt("client_id")));
                order.setGoods(goodsService.getGoodsByOrdersId(resultSet.getInt("id")));
            }            
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return order;

    }

    public Order isExists(String code) {
        Order order = null;

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from mydb.orders where code=" + "'" + code + "'");
            if (resultSet.next()) {
                order = new Order();
                order.setCode(resultSet.getString("code"));
                order.setId(resultSet.getInt("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return order;
    }

    private String generateCode() {
        Random random = new Random();
        int code = random.nextInt();
        String curentCode = "order_" + code;
        return curentCode;
    }

    public List<Order> getAll() {
         List<Order> orders = new ArrayList<>();
        try {           
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from mydb.orders");
            orders = createOrderList(resultSet);      
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
     public Order createOreder(ResultSet resultSet) throws SQLException {
        Order order = new Order();    
        order.setId(resultSet.getInt("id"));
        order.setCode(resultSet.getString("code"));
        order.setCustomer(clientService.getById(resultSet.getInt("client_Id")));
        return order;
    }

    public List<Order> createOrderList(ResultSet resultSet) throws SQLException {
        List<Order> orders = new ArrayList<>();
        while (resultSet.next()) {
            orders.add(createOreder(resultSet));
        }
        return orders;
    }

}
