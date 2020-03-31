/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.com.bot4u;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author 48606
 */
public class database{
    
    String dbURL = "jdbc:sqlserver://serwer1940269.home.pl:1433";
    String user = "29946824_mssql";
    String pass = "170588Piotr";
    Connection conn = null;
    Statement statement = null;
    
    
    public void database() throws SQLException{
        System.out.println("start");
        conn = DriverManager.getConnection(dbURL, user, pass);
        statement = conn.createStatement();
    }
    
    List getString() throws SQLException{

        List<String> resultList = new ArrayList<>();
        ResultSet resultSet = null;
        String selectSql = "SELECT * from dbo.test";
        resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("nazwa"));
            resultList.add(resultSet.getString("nazwa"));
        }
       
        return resultList;
    }
    
    public void insert(String nazwa) throws SQLException{
        String sql = "INSERT INTO dbo.test (LP, nazwa) VALUES ('1234', '"+ nazwa +"');";
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }
    
    public List<String> znajdzNazwa(int lp) throws SQLException{
        List<String> resultList = new ArrayList<>();
                ResultSet resultSet = null;
        String selectSql = "SELECT * from dbo.test where LP = " + lp;
        resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("nazwa"));
            resultList.add(resultSet.getString("nazwa"));
        }
       
        return resultList;
    }
    
        public void zamowienie(int lp, String invoice, String pn, int count) throws SQLException{
        String sql = "INSERT INTO dbo.cos (lp, invoice, pn, count) VALUES ('"+lp+"','"+invoice+"','"+pn+"','"+count+"');";
            System.out.println(sql);
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
    }
        
        public void dostawa(String invoice, String pn, String serial) throws SQLException{
            
        String sql = "INSERT INTO dbo.serial (invoice, pn, serial) VALUES ('"+invoice+"','"+pn+"','"+serial+"');";
            System.out.println(sql);
        PreparedStatement st = conn.prepareStatement(sql);
        st.executeUpdate();
        }
        
        List pokazdostawa() throws SQLException{

        List<String> resultList = new ArrayList<>();
        ResultSet resultSet = null;
        String selectSql = "SELECT * from dbo.serial";
        resultSet = statement.executeQuery(selectSql);
        while (resultSet.next()) {
            String rekord = resultSet.getString(1) + " " + resultSet.getString(2)+ " " + resultSet.getString(3);
            resultList.add(rekord);
        }
        return resultList;
    }
    
    
}
