package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SingletonConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Client;

public class ClientService {

    public static int add(Client c) {
        String req = "INSERT INTO `client`(`nom`, `prenom`, `adresse`, `telephone`, `email`, `pwd`) VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, c.getNom());
            ps.setString(2, c.getPrenom());
            ps.setString(3, c.getAdresse());
            ps.setString(4, c.getTelephone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getPwd());

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int delete(int id) {
        String req = "DELETE FROM `client` WHERE idclient=?;";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int update(Client c, String newNom, String newPrenom, String Newadresse, String newEmail, String newPwd) {
        String req = "UPDATE client SET `nom`=?, `prenom`=?, `adresse`=?, `pwd`=?, `email`=? WHERE `idclient`=?";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, newNom);
            ps.setString(2, newPrenom);
            ps.setString(4, Newadresse);
            ps.setString(3, newEmail);
            ps.setString(5, newPwd);
            ps.setInt(6, c.getIdclient());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
 
    public static int updateWithoutPwd(int id, String newNom, String newPrenom, String Newadresse, String newEmail) {
        String req = "UPDATE client SET `nom`=?, `prenom`=?, `adresse`=?, `email`=? WHERE `idclient`=?";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, newNom);
            ps.setString(2, newPrenom);
            ps.setString(3, Newadresse);
            ps.setString(4, newEmail);
            ps.setInt(5, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }


    public static ObservableList<Client> getAll() {
    	ObservableList<Client> clients=FXCollections.observableArrayList();
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT * FROM `client`"; 
        PreparedStatement p;
        try {
            p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	Client client=  new Client(rs.getInt("idClient"),rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("prenom"),null);
            	clients.add(client); 
            	}
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clients;
        }
    
    public static ObservableList<Client> getAllByNom(String nom) {
    	ObservableList<Client> clients=FXCollections.observableArrayList();
        Connection con = SingletonConnection.getCon();
        	String sql = "SELECT * FROM `client` WHERE `nom` LIKE CONCAT('%', ?, '%')"; 
        PreparedStatement p;
        try {
            p = con.prepareStatement(sql);
            p.setString(1, nom);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	Client client=  new Client(rs.getInt("idClient"),rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getString("telephone"), rs.getString("prenom"),null);
            	clients.add(client); 
            	}
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clients;
        }

    
    public static int ClientTotal() {
        
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT COUNT(idclient) AS TOTAL_COUNT FROM `client`"; 
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

