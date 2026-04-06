Explanation of Output:
1. Application Started Successfully

The Distributed Data Client GUI launched correctly.

A window titled “Distributed Data Client” appears
It contains:
Upload CSV button
Empty JTable (ID, Name, Age, HeartRate)

This confirms:
✔ GUI is working
✔ Swing components initialized properly

2. CSV File Selection

When the Upload CSV button is clicked:

A file chooser window opens

User selects:

health_data.csv

This confirms:
✔ File selection functionality works
✔ JFileChooser is correctly implemented

3. Data Sent to Server

After selecting the file:

Client reads the CSV file line by line

Sends the data to the server using:

Socket (localhost:5001)

This confirms:
✔ Client-server communication is working
✔ Socket connection is successful

4. Server Processing

On the server side:

Server receives CSV data

Clears old records from database:

DELETE FROM patients;
Inserts new records into MySQL table

Retrieves all records using:

SELECT * FROM patients;
Sends processed data back to client

This confirms:
✔ Database connection (JDBC) is working
✔ Data insertion is successful
✔ No duplicate records (fix applied correctly)

5. Data Display in JTable

The client receives processed data and displays it:

ID	Name	Age	HeartRate
101	John	45	82
102	Alice	50	90
103	Bob	39	75

This confirms:
✔ JTable is correctly updated
✔ Data parsing and display logic works

6. Success Message

A confirmation popup appears:

CSV uploaded successfully and data loaded into table.

This confirms:
✔ End-to-end process completed successfully
✔ No runtime errors

Outcome: 

<img width="745" height="495" alt="image" src="https://github.com/user-attachments/assets/a34c33b4-df55-436d-80a7-a55972bcafd9" />

<img width="643" height="441" alt="image" src="https://github.com/user-attachments/assets/99a3a03b-a621-42b7-b695-46dba164c669" />

<img width="746" height="496" alt="image" src="https://github.com/user-attachments/assets/0e274b73-5c0e-452c-94a6-e9b200620e2d" />

<img width="747" height="495" alt="image" src="https://github.com/user-attachments/assets/e742eb40-8785-4f94-8596-ba6656e05553" />




