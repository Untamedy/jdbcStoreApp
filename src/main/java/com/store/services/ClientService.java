package com.store.services;


import com.store.entities.Client;
import com.store.entities.Goods;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author YBolshakova
 */
public class ClientService {
    
    private static final Logger LOGGER = Logger.getLogger(ClientService.class.getName());

    private final Connection connection;

    String addClisent = "INSERT into mydb.clients (name,phoneNum) values (?,?) ";

   
        
    public ClientService(Connection connection) {
        this.connection = connection;
    }

    public void addClient(String name, String phoneNumber)  {
        Client client = isExists(phoneNumber);
        if (null == client) {
            try {
                connection.setAutoCommit(false);
                PreparedStatement statement = connection.prepareStatement(addClisent);
                statement.setObject(1, name);
                statement.setObject(2, phoneNumber);
                statement.execute();
                connection.commit();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    LOGGER.warning(ex.getMessage());
                }
                LOGGER.warning(ex.getMessage());
            }
        }else{
            LOGGER.log(Level.INFO, "Client is alredy exists-> {0}", client.toString());
        }
    }    
    

    public Client isExists(String phoneNumber) {
        Client client = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from mydb.clients where phoneNum=" +"'"+phoneNumber+"'");
                
            if (resultSet.next()){
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPersonalNumber(resultSet.getString("phoneNum"));
            }

        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return client;
    }
    
    public Client getById(int id){
        Client client = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select* from mydb.clients where id=" + id);
            if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPersonalNumber(resultSet.getString("phoneNum"));
            }

        } catch (SQLException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return client;
        
        
    }
    
    private Client createClient(ResultSet resultSet) throws SQLException{
        Client client = null;
        if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setPersonalNumber(resultSet.getString("phoneNum"));
            }
        return client;
    }

     public List<Client> getAll()  {
         List<Client> clients = new ArrayList<>();
        try {            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from mydb.clients");
            while(resultSet.next()){
                Client  client = createClient(resultSet);
                clients.add(client);
            }           
           
        } catch (SQLException ex) {
            Logger.getLogger(ClientService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return clients;
    }

}
