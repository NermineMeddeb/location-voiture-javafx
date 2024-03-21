package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SingletonConnection;

public class LoginService {
	
	final static String ADMIN = "admin";
	final static String EMPLOYEE = "employee";
	
	public static int loginAdmin(String email,String pwd) {
		return login(email, pwd, ADMIN);
	}
	
	public static int loginEmployee(String email,String pwd) {
		return login(email, pwd, EMPLOYEE);
	}
	
	public static int login(String email,String pwd, String type) {
		
        String req = "SELECT * FROM `employee` WHERE `email`=? AND `pwd`=? AND `type`=?";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, email);
            ps.setString(2, pwd);
            ps.setString(3, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            } else {
                return 0; 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
	}

	public static int loginClient(String email,String pwd) {
        String req = "SELECT * FROM `client` WHERE `email`=? AND `pwd`=?";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, email);
            ps.setString(2, pwd);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return 1;
            } else {
                return 0; 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }

	}

    }

