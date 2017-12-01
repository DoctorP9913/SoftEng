/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymsym;

import gymsym.leaves.Equipment;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DoctorP
 */
public class Rooms {
    
    private static String dbURL = "jdbc:derby://localhost:1527/GymSymDB;user=doctorp;password=201727";
    private static String tableName = "ROOMS";
    private static Connection conn = null;
    private static Statement stmt = null;
    private int room_name;
    private int capacity;
    private Rooms room1;
    Equipment[] room_eq; 
    
    Rooms(int room_name, int capacity, Equipment[] room_eq){
        this.room_name = room_name;
        this.capacity = capacity;
        this.room_eq = room_eq;
        insertRoom(room_name,capacity,room_eq);
    }
    
    public String createRoom(int room_name, int capacity,Equipment[] room_eq){
        
        try{
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select ROOM_NAME from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            while(results.next()){
                if(room_name == (results.getInt("ROOM_NAME"))){
                    return "Room already in database";
                }
            }
            room1 = null;
            room1 = new Rooms(room_name,capacity,room_eq);
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept){
            return sqlExcept.getMessage();
        }
        return "Room added";
    }
  
    private static void insertRoom(int room_name,int capacity,Equipment[] room_eq)
    {
        String equipString = "";
        
        for(int i = 0; i < room_eq.length;i++){
            
            equipString += " ";
            equipString += room_eq[i];
            
        }
        
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" + room_name + ",'" + capacity + "','" + equipString + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            System.out.println(sqlExcept);
        }
    }
    public int getRoom_name() {
        return room_name;
    }

    public void setRoom_name(int room_name) {
        this.room_name = room_name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Equipment[] getRoom_eq() {
        return room_eq;
    }

    public void setRoom_eq(Equipment[] room_eq) {
        this.room_eq = room_eq;
    }
    
}
