package org.jukebox.dao;

import org.jukebox.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.jukebox.connection_db.Connector_db.con;

public class UserDao {

    //Inserts unique user record according to username and password.
    public static boolean createUser(String user_name, String user_password) throws SQLException {
        PreparedStatement ps=con.prepareStatement("select * from userTable where userName=? and userPassword=?");
        ps.setString(1,user_name);
        ps.setString(2,user_password);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            rs.close();
            ps.close();
            return false;
        }
        ps=con.prepareStatement("insert into userTable(userName,userPassword) values(?,?)");
        ps.setString(1,user_name);
        ps.setString(2,user_password);
        ps.executeUpdate();
        rs.close();
        ps.close();
        return true;
    }

    //Returns true if login is successful, otherwise returns false.
    public static boolean login(String user_name, String user_password) throws  SQLException {
        PreparedStatement ps = con.prepareStatement("select * from userTable where userName=? and userPassword=? ");
        ps.setString(1,user_name);
        ps.setString(2, user_password);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            rs.close();
            ps.close();
            return true;
        }
        rs.close();
        ps.close();
        return false;
    }


    //Returns a User (to create playlist we need userId).
    public static User getUser(String user_name, String user_password) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select * from userTable where userName=? and userPassword=? ");
        ps.setString(1,user_name);
        ps.setString(2, user_password);
        ResultSet rs = ps.executeQuery();
        User user=null;
        while (rs.next()){
            int userId=rs.getInt(1);
            String userName = rs.getString(2);
            String userPassword = rs.getString(3);
            user = new User(userId,userName,userPassword);
        }
        rs.close();
        ps.close();
        return user;
    }


}
