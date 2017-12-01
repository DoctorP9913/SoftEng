package gymsym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author DoctorP
 */
public abstract class User {
    
    private static String dbURL = "jdbc:derby://localhost:1527/GymSymDB;user=doctorp;password=201727";
    private static String tableName = "USERS";
    private static Connection conn = null;
    private static Statement stmt = null;
    private String name,surname,phone,address;
    private int ACID;
    private int USID; // needs to get random number
    
    User(String name,String surname,String phone,String address,String username){
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
        insertUser(createUSID(),name,surname,phone,address,username);
    }

    public static int createUSID(){
        Random r1 = new Random();
        int q1 = r1.nextInt(9998) + 1;
        try{
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select USID from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            while(results.next()){
                if(q1==results.getInt("USID")){
                    int a = createUSID();
                    return a;
                }
            }
            results.close();
            stmt.close();
            return q1;
        } catch (SQLException sqlExcept){
            System.out.println(sqlExcept);
            return q1;
        }
    }
    
    private static void createConnection() //needs to be placed inside main --
    {
        try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL); 
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException except)
        {
            System.out.println(except);
        }
    }
    
    private static void insertUser(int USID, String name, String surname,String phone,String address,String username)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" + USID + ",'" + name + "','" + surname + "','" + phone + "','" + address + "'," + Account.usernameToACID(username) + ")");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            System.out.println(sqlExcept);
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getACID() {
        return ACID;
    }

    public void setACID(int ACID) {
        this.ACID = ACID;
    }
    
}
