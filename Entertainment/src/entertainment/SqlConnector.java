/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entertainment;

/**
 *
 * @author majorsalieri
 */

import java.sql.*;


public class SqlConnector {
    
    private final String url = "jdbc:mysql://127.0.0.1:3306/";
    private final String dbName = "Entertainment";
    private final String dbUsername = "root";
    private final String dbPassword = "Omwene11@";
        
    public void createUser(String email, String username, String password) throws SQLException{
        Connection db = DriverManager.getConnection(url+dbName, dbUsername, dbPassword);
        Statement query = (Statement) db.createStatement();
        query.executeUpdate("INSERT INTO users (email, username, password) VALUES "
                + "('"+ email +"', '"+ username +"', '"+ password +"')");
        db.close();
    }
    
//    public void findUser(String username, String password) throws SQLException{
    public void findAllUsers() throws SQLException{    
        Connection db = DriverManager.getConnection(url+dbName, dbUsername, dbPassword);
        Statement query = (Statement) db.createStatement();
        ResultSet data = query.executeQuery("SELECT * FROM users");
        while (data.next()){
            System.out.println(data.getString("email"));
            System.out.println(data.getString("username"));
            System.out.println(data.getString("password"));
            System.out.println("*******");
        }
    }
    
    public boolean findUser(String username, String password) throws SQLException{
        Connection db = DriverManager.getConnection(url+dbName, dbUsername, dbPassword);
        Statement query = (Statement) db.createStatement();
        ResultSet data = query.executeQuery("SELECT * FROM users where username='"+ username +"' and password='"+ password +"'");
        int counter = 0;
        while (data.next()){
            counter ++;
        }
        
        if (counter == 0)
            return false;
        else
            return true;
    }
    
    public boolean buyTicket(int user, String game, int numberOfTickets, int price) throws SQLException{
        Connection db = DriverManager.getConnection(url+dbName, dbUsername, dbPassword);
        Statement query = (Statement) db.createStatement();
        boolean response;
        try{
            for (int x = 0; x < numberOfTickets; x++){
                query.executeUpdate("insert into tickets(user, game, price) VALUES ("+ user +", '"+  game +"', "+ price +") ");
            }
            response = true;
        } catch(Exception ex){
            response = false;
        } finally{
            db.close();
        }
        return response;
    }
}
