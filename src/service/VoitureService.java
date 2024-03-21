package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.SingletonConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Voiture;

public class VoitureService {
	
	 
	 
    public static ObservableList<Voiture> getAll() {
    	ObservableList<Voiture> voitures=FXCollections.observableArrayList();
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT * FROM `vehicule`"; 
        PreparedStatement p;
        try {
            p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	Voiture voiture=  new Voiture(rs.getInt("id"),rs.getString("marque"), rs.getString("modele"), rs.getString("type_carburant"), rs.getBoolean("disponibilite"));
            	voitures.add(voiture); 
            	}
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return voitures;
        }
    public static ObservableList<Voiture> getAllVoitureDispo() {
    	ObservableList<Voiture> voitures=FXCollections.observableArrayList();
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT * FROM `vehicule` WHERE `disponibilite` = 1"; 
        PreparedStatement p;
        try {
            p = con.prepareStatement(sql);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	Voiture voiture=  new Voiture(rs.getInt("id"),rs.getString("marque"), rs.getString("modele"), rs.getString("type_carburant"), rs.getBoolean("disponibilite"));
            	voitures.add(voiture); 
            	}
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return voitures;
        }
    
    public static ObservableList<Voiture> getAllByNom(String modele) {
    	ObservableList<Voiture> voitures=FXCollections.observableArrayList();
        Connection con = SingletonConnection.getCon();
        	String sql = "SELECT * FROM `vehicule` WHERE `modele` LIKE CONCAT('%', ?, '%')"; 
        PreparedStatement p;
        try {
            p = con.prepareStatement(sql);
            p.setString(1, modele);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
            	Voiture voiture=  new Voiture(rs.getInt("id"),rs.getString("marque"), rs.getString("modele"), rs.getString("type_carburant"), rs.getBoolean("disponibilite"));
            	voitures.add(voiture); 
            	}
           } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return voitures;
        }

    
    public static int voitureTotal() {
        
        Connection con = SingletonConnection.getCon();
        String sql = "SELECT COUNT(id) AS TOTAL_COUNT FROM `voiture`"; 
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

    
    public static int add(Voiture v) {
        String req = "INSERT INTO `vehicule`(`marque`, `modele`, `disponibilite`, `type_carburant`) VALUES (?,?,?,?);";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setString(1, v.getMarque());
            ps.setString(2, v.getModele());
            ps.setBoolean(3, v.isDisponibilite());
            ps.setString(4, v.getType_carburant());
            

            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int delete(int id) {
        String req = "DELETE FROM `vehicule` WHERE id=?;";
        try {
            PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

 
    public static int update(int id,String Newmarque,String Newmodele,boolean Newdisponibilite,String Newtype_carburant) {
        String req = "UPDATE vehicule SET marque=?, modele=?, disponibilite=?, type_carburant=? WHERE id=?";
        try (PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req)) {
            ps.setString(1, Newmarque);
            ps.setString(2,Newmodele);
            ps.setBoolean(3, Newdisponibilite);
            ps.setString(4, Newtype_carburant);
            ps.setInt(5, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static int resserveVoiture(int id) {
        String req = "UPDATE vehicule SET disponibilite=? WHERE id=?";
        try (PreparedStatement ps = SingletonConnection.getCon().prepareStatement(req)) {
            ps.setBoolean(1, false);
            ps.setInt(2,id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
   
    


}
