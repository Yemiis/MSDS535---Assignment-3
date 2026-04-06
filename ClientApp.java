/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distributedcsvdataprocessingsystem;

/**
 *
 * @author yemi-co
 */
// Import packages
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClientApp extends JFrame {

    // JTable component used to display returned data from the server
    JTable table;

    // Table model used to dynamically store and update table data
    DefaultTableModel model;

    // Use constructor method that builds the GUI when the program starts
    public ClientApp() {

        // Set the title of the application window
        setTitle("Distributed Data Client");

        // Set the size of the window (width x height: 600X400)
        setSize(600, 400);

        // Close the application when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the window on the screen
        setLocationRelativeTo(null);

        // Create a button that allows users to upload a CSV file
        JButton uploadBtn = new JButton("Upload CSV");

        // Initialize the table model
        model = new DefaultTableModel();

        // Define table column headers
        model.setColumnIdentifiers(new String[]{"ID", "Name", "Age", "HeartRate"});

        // Create JTable and link it with the table model
        table = new JTable(model);

        // Define action when the upload button is clicked
        uploadBtn.addActionListener(e -> sendFile());

        // Add the button at the top (NORTH) of the window layout
        add(uploadBtn, BorderLayout.NORTH);

        // Add the table with a scroll bar in the center of the window
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Make the GUI visible to the user
        setVisible(true);
    }

    // Method responsible for sending CSV data to the server
    @SuppressWarnings("CallToPrintStackTrace")
    void sendFile() {
        try {

            // Create a file chooser dialog so the user can select a CSV file
            JFileChooser chooser = new JFileChooser();

            // Open the file dialog window
            int result = chooser.showOpenDialog(this);

            // If user cancels file selection, exit method
            if (result != JFileChooser.APPROVE_OPTION) {
                return;
            }

            // Retrieve the selected file from the file chooser
            File file = chooser.getSelectedFile();

            // Clear existing table data before loading new data
            model.setRowCount(0);

            // Read the selected CSV file line by line
            try ( // Create a socket connection to the server running on localhost at port 5001
                    Socket socket = new Socket("localhost", 5001)) {
                PrintWriter out;
                BufferedReader in;
                // Send data to the server through the socket
                try ( // Read the selected CSV file line by line
                        BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
                    // Send data to the server through the socket
                    out = new PrintWriter(socket.getOutputStream(), true);
                    String line;
                    while ((line = fileReader.readLine()) != null) {
                        out.println(line);
                    }   // Indicate that all data has been sent to the server
                    socket.shutdownOutput();
                    // Receive/Read processed records returned by the server
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while ((line = in.readLine()) != null) {
                        String[] data = line.split(",");
                        
                        // Add the received data as a new row in the table (GUI)
                        if (data.length == 4) {
                            model.addRow(data);
                        }
                    }   // Close file reader and socket streams
                }
                in.close();
                out.close();
                // Close the socket connection after communication is complete
            }

            // Display success message to user
            JOptionPane.showMessageDialog(this, "CSV uploaded successfully and data loaded into table.");

        } catch (HeadlessException | IOException e) {
            // Display error message if something goes wrong
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method where the client application starts execution
    public static void main(String[] args) {

        // Create an instance of the ClientApp which launches the GUI
        SwingUtilities.invokeLater(ClientApp::new);
    }
}
