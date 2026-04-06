/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distributedcsvdataprocessingsystem;

/**
 *
 * @author yemi-co
 */

// import necessary java packages
import java.io.*;
import java.net.*;
import java.sql.*;

public class ServerApp {

    // Database connection URL specifying MySQL database location and database name
    static final String DB_URL = "jdbc:mysql://localhost:3306/healthDB";

    // Database username used to connect to MySQL
    static final String DB_USER = "root";

    // Database password used to authenticate the connection
    static final String DB_PASSWORD = "LOQ23!wel"; // change to your MySQL password

    // Main method where the program execution begins
    public static void main(String[] args) throws Exception {

        Connection conn;
        // Print message indicating the server has started
        try ( // Create a ServerSocket that listens for client connections on port 5001
                ServerSocket serverSocket = new ServerSocket(5001)) {
            // Print message indicating the server has started
            System.out.println("Server started. Waiting for client connection on port 5001...");
            // Print confirmation once a client successfully connects
            try ( // Wait for a client to connect; this line blocks until connection occurs
                    Socket socket = serverSocket.accept()) {
                // Print confirmation once a client successfully connects
                System.out.println("Client connected.");
                // Read text data sent from the client
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // Send processed data back to the client
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                // Establish connection to the MySQL database using JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                // Prepare SQL statement for inserting data into patients table
                String insertSQL = "INSERT INTO patients (patientID, name, age, heartRate) VALUES (?, ?, ?, ?)";
                // Continuously read lines sent from the client until no more data is received
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    // Continuously read lines sent from the client until no more data is received
                    String line;
                    boolean firstLine = true;
                    while ((line = in.readLine()) != null) {
                        
                        // Skip empty lines
                        if (line.trim().isEmpty()) {
                            continue;
                        }
                        
                        // Skip the header row of the CSV file
                        if (firstLine && line.toLowerCase().startsWith("patientid")) {
                            firstLine = false;
                            continue;
                        }
                        
                        firstLine = false;
                        
                        // Insert data into the patients table
                        String[] data = line.split(",");
                        
                        if (data.length == 4) {
                            pstmt.setInt(1, Integer.parseInt(data[0].trim()));
                            pstmt.setString(2, data[1].trim());
                            pstmt.setInt(3, Integer.parseInt(data[2].trim()));
                            pstmt.setInt(4, Integer.parseInt(data[3].trim()));
                            pstmt.executeUpdate();
                        }
                    }   // Execute SQL query to retrieve all records from the patients table
                    try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM patients")) {
                        // Send retrieve/processed data back to the client
                        while (rs.next()) {
                            String record = rs.getInt("patientID") + "," +
                                    rs.getString("name") + "," +
                                    rs.getInt("age") + "," +
                                    rs.getInt("heartRate");
                            out.println(record);
                        }
                        // Close the client socket connection
                         // Close result set and statement objects
                    }
                }
            }
        }

        // Close the database connection
        conn.close();

        // Print message indicating server has completed processing
        System.out.println("Server finished processing.");
    }
}
