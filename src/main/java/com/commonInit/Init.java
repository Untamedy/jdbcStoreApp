/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.commonInit;

import com.store.services.StoreService;
import com.commonInit.ConnectionService;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Init {

    private ConnectionService connectionService;
    private PropertyReader propertyReader;
    private InputData inputData;
    private StoreService storeService;
    //private  String path = "src\\main\\resources\\prop.properties";
    private String path = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\jdbcFlatApp\\src\\main\\resources\\propmysql.properties";

    public Init() {

    }

    public StoreService getConnection() {
       PropertyReader reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);
        storeService = new StoreService(connectionService);
        inputData = new InputData(connectionService);
        try {
            inputData.populateDB();
        } catch (SQLException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storeService;
    }

}
