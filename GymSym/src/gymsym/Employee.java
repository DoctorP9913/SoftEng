package gymsym;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author DoctorP
 */
public class Employee extends User {
    
    private static String dbURL = "jdbc:derby://localhost:1527/GymSymDB;user=doctorp;password=201727";
    private static String tableName = "EMPLOYEE";
    private static Connection conn = null;
    private static Statement stmt = null;
    private int EMID;
    private String emp_name,position;
    
    Employee(String name,String surname,String phone,String address,String username,String emp_name,String position){
        super(name,surname,phone,address,username);
        this.emp_name = emp_name;
        this.position = position;
        insertEmployee(createEMID(),emp_name,position);
    }
    
    public static int createEMID(){
        Random r1 = new Random();
        int q1 = r1.nextInt(9998) + 1;
        try{
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select EMID from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            while(results.next()){
                if(q1==results.getInt("EMID")){
                    int a = createEMID();
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

    public int getEMID() {
        return EMID;
    }

    public void setEMID(int EMID) {
        this.EMID = EMID;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    private static void insertEmployee(int EMID, String emp_name, String position)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" + EMID + ",'" + emp_name + "','" + position + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            System.out.println(sqlExcept);
        }
    }
    
}
