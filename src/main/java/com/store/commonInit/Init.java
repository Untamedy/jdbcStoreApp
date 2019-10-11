/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.commonInit;

import com.store.services.StoreService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lenovo
 */
public class Init {

    private static ConnectionService connectionService;
    private PropertyReader propertyReader;
    private InputData inputData;
    private static StoreService storeService;
    // private  String path = "C:\\Users\\YBolshakova\\Documents\\gs-maven\\jdbsStoreApp\\src\\main\\resources\\prop.properties";
    private String path = "C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\jdbcStoreweb\\src\\main\\resources\\propmysql.properties";
    
    private static final Init init = new Init();

    private Init() {
        PropertyReader reader = new PropertyReader(path);
        connectionService = new ConnectionService(reader);
        inputData = new InputData(connectionService);
        try {
            inputData.populateDB();
        } catch (SQLException ex) {
            Logger.getLogger(Init.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Init getInit(){
        return init;
             
    }

    public static StoreService getService() {        
        storeService = new StoreService(connectionService);
        return storeService;
    }

}
