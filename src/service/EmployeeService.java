package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SingletonConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;
import model.Employee;

public class EmployeeService {

    public static int add(Employee e) {
        String req = "INSERT INTO `employee`(`nom`, `prenom`, `poste`, `salaire`, `email`, `telephone`, `type`, `pwd`) VALUES (?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getPoste());
            ps.setInt(4, e.getSalaire());
            ps.setString(5, e.getEmail());
            ps.setString(6, e.getTelephone());
            ps.setString(7, e.getType());
            ps.setString(8, e.getPwd());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int delete(int id) {
        String req = "DELETE FROM `employee` WHERE idemployee=?;";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int update(Employee e, String newNom, String newPrenom, String newPoste, int newSalaire, String newEmail, String newTelephone, String newType, String newPwd) {
        String req = "UPDATE employee SET `nom`=?, `prenom`=?, `poste`=?, `salaire`=?, `email`=?, `telephone`=?, `type`=?, `pwd`=? WHERE `idemployee`=?";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, newNom);
            ps.setString(2, newPrenom);
            ps.setString(3, newPoste);
            ps.setInt(4, newSalaire);
            ps.setString(5, newEmail);
            ps.setString(6, newTelephone);
            ps.setString(7, newType);
            ps.setString(8, newPwd);
            ps.setInt(9, e.getIdemployee());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    public static int updatewithPwd(int id, String newNom, String newPrenom, String newPoste, int newSalaire, String newEmail, String newTelephone, String newType) {
        String req = "UPDATE employee SET `nom`=?, `prenom`=?, `poste`=?, `salaire`=?, `email`=?, `telephone`=?, `type`=? WHERE `idemployee`=?";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, newNom);
            ps.setString(2, newPrenom);
            ps.setString(3, newPoste);
            ps.setInt(4, newSalaire);
            ps.setString(5, newEmail);
            ps.setString(6, newTelephone);
            ps.setString(7, newType);
            ps.setInt(8, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    

    public static ObservableList<Employee> getAll() {
    	ObservableList<Employee> employees=FXCollections.observableArrayList();

        Connection con = SingletonConnection.getCon();
        String sql = "SELECT * FROM `employee`"; 
        PreparedStatement p;
        try {
            p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	Employee employee=  new Employee(rs.getInt("idemployee"),rs.getString("nom"), rs.getString("prenom"), rs.getString("poste"),
            			rs.getInt("salaire"), rs.getString("email"),rs.getString("telephone"),rs.getString("type"),null);
            	employees.add(employee); 
            	}
            
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return employees;
        }
    
    
    public static int EmployeeTotal() {
        
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT COUNT(idemployee) AS TOTAL_COUNT FROM `employee`"; 
        PreparedStatement p;
        int countData = 0;

        try {
            p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                countData = rs.getInt("TOTAL_COUNT");
            }
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countData;

        }
    
    public static int EmployeeTotalPresent() {
        
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT COUNT(idemployee) AS TOTAL_COUNT FROM `employee` WHERE salaire != 0"; 
        PreparedStatement p;
        int countData = 0;
        try {
            p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                countData = rs.getInt("TOTAL_COUNT");
            }
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countData;
        }

    public static int EmployeeTotalInactive() {
        
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT COUNT(idemployee) AS TOTAL_COUNT FROM `employee` WHERE salaire = 0"; 
        PreparedStatement p;
        int countData = 0;
        try {
            p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                countData = rs.getInt("TOTAL_COUNT");
            }
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countData;
        }
    
    
    }

