/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projets5t4;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gaspard Lefort-Louet
 */
public class DocteurDAO extends DAO<Doctor> {  ///Hérite de la classe DAO

    public DocteurDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void create(Doctor obj) {   //Création d'un docteur dans la base de donnée

        try {

            String sql = "INSERT INTO docteur (numSécuDocteur, nom, prénom, age, spécialité, mdp, sexe) VALUES (?,?,?,?,?,?,?)"; //Commande SQL
            PreparedStatement statementDoctor = this.connect.prepareStatement(sql); //Ecrit dans la base de donnée à chaque colonne
            statementDoctor.setInt(1, obj.getInsuranceNumber());
            statementDoctor.setString(2, obj.getLastName());
            statementDoctor.setString(3, obj.getName());
            statementDoctor.setInt(4, obj.getBorn());
            statementDoctor.setString(5, obj.getSpeciality());
            statementDoctor.setString(6, obj.getPassWord());
            statementDoctor.setString(7, obj.getSexe());

            int row = statementDoctor.executeUpdate();
            if (row > 0) {
                System.out.println("command executed");
            }

            statementDoctor.close();

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DataBase connection error : docteur existe deja");
        }

    }

    @Override
    public void delete(int numSecu) {  ///Supprime un docteur dans la base de donnée
 
        try {

            Statement stmt = this.connect.createStatement();

            stmt.execute("DELETE FROM docteur WHERE numSécuDocteur =" + numSecu);  //Commande SQL de suppression
            stmt.execute("DELETE FROM rdv WHERE numSécuDocteur =" + numSecu);

            stmt.close();

        } // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DataBase connection error");
        }

    }

    @Override
    public boolean update(Doctor obj, String ant) { 
        return false;

    }

    @Override
    public List<Doctor> find() {  ///Récupère tout les docteurs de la base de donnée

        List<Doctor> DoctorList = new ArrayList<Doctor>();  ///Création d'une arrauList de docteur

        try {
            ResultSet rs = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery(
                    "SELECT * FROM docteur"
            );
            while (rs.next()) {  
                Doctor doc = new Doctor(rs.getString(2), rs.getString(3), rs.getInt(1), rs.getInt(4), rs.getString(6), rs.getString(5), rs.getString(7));
                DoctorList.add(doc);  //Ajoute chaque docteur de la base de donnée à l'arraylist
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return DoctorList;  //Return la liste de docteur
    }

    @Override
    public void delete(String numRdv) {
    }

}
