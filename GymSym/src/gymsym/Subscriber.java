package gymsym;

import gymsym.leaves.Physic;
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
public class Subscriber extends User{
    
    private static String dbURL = "jdbc:derby://localhost:1527/GymSymDB;user=doctorp;password=201727";
    private static String tableName = "SUBSCRIBERS";
    private static Connection conn = null;
    private static Statement stmt = null;
    private int SBID;
    private int subbed_months;
    private String sub_name;
    private Physic type;
    
    Subscriber(String name,String surname,String phone,String address,String username,String sub_name,Physic type,int subbed_months){
        super(name,surname,phone,address,username);
        this.sub_name = sub_name;
        this.type = type;
        this.subbed_months = subbed_months;
        insertSubscriber(createSBID(),sub_name,type,subbed_months);
    }
    
    public static int createSBID(){
        Random r1 = new Random();
        int q1 = r1.nextInt(9998) + 1;
        try{
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select SBID from " + tableName);
            ResultSetMetaData rsmd = results.getMetaData();
            while(results.next()){
                if(q1==results.getInt("SBID")){
                    int a = createSBID();
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

    private static void insertSubscriber(int SBID, String sub_name, Physic type, int subbed_months)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute("insert into " + tableName + " values (" + SBID + ",'" + sub_name + "','" + type.getDescription() + "','" + subbed_months + "')");
            stmt.close();
        }
        catch (SQLException sqlExcept)
        {
            System.out.println(sqlExcept);
        }
    }
    
    public int getSBID() {
        return SBID;
    }

    public void setSBID(int SBID) {
        this.SBID = SBID;
    }

    public int getSubbed_months() {
        return subbed_months;
    }

    public void setSubbed_months(int subbed_months) {
        this.subbed_months = subbed_months;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public Physic getType() {
        return type;
    }

    public void setType(Physic type) {
        this.type = type;
    }
    
}
