package sqlMethods;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import constructors.Voters;
import constructors.Candidates;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kobe0
 */
public class SQLMethods {  
    public static String[] GetPositions(){
        String url = "jdbc:mysql://localhost:3306/votingregistration";
        String user = "root";
        String password = "admin123";
        String[] positions = new String[13];
        int counter = 0;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM positions");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("positionName");
                positions[counter] = name;
                counter = counter + 1;
            }

            rs.close();
            stmt.close();
            conn.close();           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return positions;
    }
    public static String[] GetIdType(){
        String url = "jdbc:mysql://localhost:3306/votingregistration";
        String user = "root";
        String password = "admin123";
        String[] idType = new String[6];
        int counter = 0;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM idType");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("typeOfId");
                idType[counter] = name;
                counter = counter + 1;
            }

            rs.close();
            stmt.close();
            conn.close();           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return idType;
    }
    public Voters[] GetVoters(){
        String url = "jdbc:mysql://localhost:3306/votingregistration";
        String user = "root";
        String password = "admin123";
        String table = "voters";
        Voters[] voters = new Voters[100];
        int counter = 0;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT v.id, v.name, v.age, v.street, v.barangay, v.city, v.municipality, v.country, v.zip_code, it.typeOfId, v.idNumber\n" +
"FROM voters v\n" +
"INNER JOIN idType it ON v.idType = it.id;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name  = rs.getString("name");
                int age = rs.getInt("age");
                String street  = rs.getString("street");
                String barangay  = rs.getString("barangay");
                String municipality  = rs.getString("municipality");
                String city  = rs.getString("city");
                String country  = rs.getString("country");
                int zip_code  = rs.getInt("zip_code");
                String idType  = rs.getString("typeOfId");
                String idNumber = rs.getString("idNumber");
                
                Voters voter = new Voters(id, name, age, street, barangay, municipality, city, country, zip_code, idType, idNumber);
                
                voters[counter] = voter;
                
                counter = counter + 1;
            }

            rs.close();
            stmt.close();
            conn.close();           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return voters;
    }
    
    public Candidates[] GetCandidates(){
        String url = "jdbc:mysql://localhost:3306/votingregistration";
        String user = "root";
        String password = "admin123";
        Candidates[] candidates = new Candidates[100];
        int counter = 0;
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("	SELECT \n" +
"	  c.id AS id, \n" +
"	  c.party, \n" +
"	  p.positionName, \n" +
"	  c.votes_received, \n" +
"	  v.name, \n" +
"	  v.age, \n" +
"	  v.street, \n" +
"	  v.barangay, \n" +
"	  v.city, \n" +
"	  v.municipality, \n" +
"	  v.country, \n" +
"	  v.zip_code, \n" +
"	  i.typeOfId, \n" +
"	  v.idNumber \n" +
"	FROM candidates c\n" +
"	JOIN positions p ON c.positionId = p.id\n" +
"	JOIN voters v ON c.voterId = v.id\n" +
"	JOIN idType i ON v.idType = i.id;");

            while (rs.next()) {
                int id = rs.getInt("id");
                String party  = rs.getString("party");
                String positionName = rs.getString("positionName");
                String name  = rs.getString("name");
                int age  = rs.getInt("age");
                String street  = rs.getString("street");
                String barangay  = rs.getString("barangay");
                String city  = rs.getString("city");
                String municipality  = rs.getString("municipality");
                String country  = rs.getString("country");
                int zip_code = rs.getInt("zip_code");
                String typeOfId = rs.getString("typeOfId");
                String idNumber = rs.getString("idNumber");
                Candidates candidateEntry = new Candidates(id, party, positionName, name, age, street, barangay, city, municipality, country, zip_code, typeOfId, idNumber);
                
                candidates[counter] = candidateEntry;
                
                counter = counter + 1;
            }

            rs.close();
            stmt.close();
            conn.close();           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return candidates;
    }
    
    public void insertVoter(String name, int age, String street, String barangay, String city, String municipality, String country, int zipCode, int idType, String idNumber) {
    // Validate input fields
    if (name.isEmpty() || street.isEmpty() || barangay.isEmpty() || city.isEmpty() || municipality.isEmpty() || country.isEmpty() || idNumber.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill out all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    if (age < 18) {
        JOptionPane.showMessageDialog(null, "Voter must be 18 years old or above.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    //check if idNumber is in use
    try {
        String url = "jdbc:mysql://localhost:3306/votingregistration";
        String user = "root";
        String password = "admin123";
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database!");
        PreparedStatement checkIdNumber = conn.prepareStatement("SELECT * FROM voters WHERE idNumber = ?");
        checkIdNumber.setString(1, idNumber);
        ResultSet rs = checkIdNumber.executeQuery();
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "ID number is already in use.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Perform database insertion
    try {
        String url = "jdbc:mysql://localhost:3306/votingregistration";
        String user = "root";
        String password = "admin123";
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connected to database!");
        
        // Insert voter and get generated voter_id
        PreparedStatement ps = conn.prepareStatement("INSERT INTO voters(name, age, street, barangay, city, municipality, country, zip_code, idType, idNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setInt(2, age);
        ps.setString(3, street);
        ps.setString(4, barangay);
        ps.setString(5, city);
        ps.setString(6, municipality);
        ps.setString(7, country);
        ps.setInt(8, zipCode);
        ps.setInt(9, idType);
        ps.setString(10, idNumber);
        ps.executeUpdate();
        
        // Get generated voter_id
        ResultSet rs = ps.getGeneratedKeys();
        int voterId = 0;
        if (rs.next()) {
            voterId = rs.getInt(1);
        }
        
        JOptionPane.showMessageDialog(null, "Your voter's id is " + voterId, "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public void insertCandidate(String party, int positionId, int voterId) {
        // Validate input fields
        if (party.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter candidate name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        

        // Perform database insertion
        try {
            String url = "jdbc:mysql://localhost:3306/votingregistration";
            String user = "root";
            String password = "admin123";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");
                                    
            //Check if candidate is above 40 if running for president or vp
            PreparedStatement chckAge = conn.prepareStatement("SELECT age FROM voters WHERE id = ?");
            chckAge.setInt(1, voterId);
            ResultSet ageResult = chckAge.executeQuery();
            int age = 0;
            if (ageResult.next()) {
                age = ageResult.getInt("age");
            }
            
            System.out.println(age);
            
            if ((positionId == 1 || positionId == 2) && age < 40){
                JOptionPane.showMessageDialog(null, "This position requires you to be atleast 40 years old.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check if candidate with same voter ID already exists
            PreparedStatement checkStmt = conn.prepareStatement("SELECT COUNT(*) FROM candidates WHERE voterId = ?");
            checkStmt.setInt(1, voterId);
            ResultSet checkResult = checkStmt.executeQuery();
            if (checkResult.next() && checkResult.getInt(1) > 0) {
                JOptionPane.showMessageDialog(null, "Candidate with this voter ID is already running for a position.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert candidate into candidates table
            PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO candidates(party, positionId, voterId) VALUES (?, ?, ?)");
            insertStmt.setString(1, party);
            insertStmt.setInt(2, positionId);
            insertStmt.setInt(3, voterId);
            insertStmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Candidate successfully added.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteCandidate(int voterId) {
        try {
            String url = "jdbc:mysql://localhost:3306/votingregistration";
            String user = "root";
            String password = "admin123";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM candidates WHERE voterId=?");
            ps.setInt(1, voterId);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Candidate deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void deleteVoter(int voterId) {
        deleteCandidate(voterId);
        try {
            // Establish database connection
            String url = "jdbc:mysql://localhost:3306/votingregistration";
            String user = "root";
            String password = "admin123";
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to database!");

            // Prepare and execute SQL statement
            PreparedStatement ps = conn.prepareStatement("DELETE FROM voters WHERE id = ?");
            ps.setInt(1, voterId);
            int rowCount = ps.executeUpdate();

            // Check if a row was deleted
            if (rowCount == 0) {
                JOptionPane.showMessageDialog(null, "Voter not found.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Voter with ID " + voterId + " deleted.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            // Close the database connection
            conn.close();
            System.out.println("Disconnected from database.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
