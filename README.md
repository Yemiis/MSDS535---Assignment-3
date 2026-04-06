# MSDS535---Assignment-3

## Distributed CSV Data Processing System

**Due: March 29, 2025 07:00 PM US Central Time**. Push to GitHub to
submit the assignment.

------------------------------------------------------------------------

## Learning Objectives

By completing this assignment, students will be able to:

-   Implement distributed client-server systems using TCP sockets
-   Build graphical user interfaces using Java Swing
-   Perform file transfer between distributed systems
-   Parse structured CSV datasets
-   Connect Java applications to MySQL using JDBC
-   Store and retrieve data from relational databases
-   Display database records using GUI tables
-   Understand distributed data processing workflows

------------------------------------------------------------------------

## Assignment Overview

This assignment consists of two parts:

| Part | Description | File |
|------|------------|------|
| Part 1 | TCP Server that receives CSV and stores data in MySQL | `ServerApp.java` |
| Part 2 | Java Swing Client that uploads CSV and display records | `ClientApp.java` |

------------------------------------------------------------------------

# System Architecture

    +------------------+         TCP Socket          +----------------------+
    |                  |  CSV File Transfer Request  |                      |
    |   CLIENT (GUI)   | --------------------------> |        SERVER        |
    |  Java Swing App  |                             |   Java Socket Server |
    |                  |                             |                      |
    |  File Chooser    |                             |  Parse CSV File      |
    |  Send CSV File   |                             |  Insert into MySQL   |
    |                  | <-------------------------- |  Retrieve Records    |
    |  Display Table   |   Database Records          |  Send Back to Client |
    |                  |                             |                      |
    +------------------+                             +----------------------+

------------------------------------------------------------------------

# Dataset 

You can use `health_data.csv` for the dataset for this assignment.

    patientID,name,age,heartRate
    101,John,45,82
    102,Alice,50,90
    103,Bob,39,75

------------------------------------------------------------------------

# Part 1: Server Application (TCP + JDBC)

## Problem Description

You will implement a Java TCP server that receives a CSV dataset from
the client and performs database operations.

The server should:

1.  Start a TCP socket server
2.  Receive CSV file data from the client
3.  Parse the CSV records
4.  Insert records into a MySQL database using JDBC
5.  Retrieve all records from the database
6.  Send the retrieved records back to the client

------------------------------------------------------------------------

## Task 1: Database Setup

Create a MySQL database named `healthDB`.

Create a table called `patients` with the following attributes:

-   `patientID` -- Integer
-   `name` -- VARCHAR(50)
-   `age` -- Integer
-   `heartRate` -- Integer

Example SQL:

``` sql
CREATE DATABASE healthDB;

USE healthDB;

CREATE TABLE patients (
    patientID INT,
    name VARCHAR(50),
    age INT,
    heartRate INT
);
```

------------------------------------------------------------------------

## Task 2: Implement TCP Server

Implement `ServerApp.java` that:

-   Starts a server on port `5000`
-   Waits for a client connection
-   Receives CSV data line by line
-   Parses each row using `split(",")`

------------------------------------------------------------------------

## Task 3: Insert Records Using JDBC

Use JDBC to insert each record into the `patients` table.

Required JDBC steps:

-   Load MySQL driver
-   Create database connection
-   Prepare SQL INSERT statement
-   Insert CSV records

------------------------------------------------------------------------

## Task 4: Retrieve Database Records

Retrieve all records using:

``` sql
SELECT * FROM patients;
```

------------------------------------------------------------------------

## Task 5: Send Records Back to Client

Send each database record back to the client via the TCP socket.

------------------------------------------------------------------------

## Evaluation (Part 1)

  | Criteria                         |   Points |
  | -------------------------------- | --------
  | TCP Server Implementation        | 10       |
  | CSV Parsing                      | 10  |
  | MySQL Database Connection        | 10  |
  | Data Insertion using JDBC        | 10  |
  | Retrieving and Sending Records   | 10  |
  | **Total (Part 1)**               | **50** |

------------------------------------------------------------------------

# Part 2: Client Application (Java Swing GUI)

## Problem Description

You will implement a Java Swing GUI client that sends a CSV dataset to
the server and displays the returned records.

The client should provide:

-   File selection
-   File transfer to server
-   Receiving database results
-   Displaying records in a table

------------------------------------------------------------------------

## Task 1: Create GUI Interface

Create a Java Swing interface with:

-   A File Chooser
-   An Upload Button
-   A JTable to display results

------------------------------------------------------------------------

## Task 2: Upload CSV File

When the user clicks Upload:

-   Open `JFileChooser`
-   Select CSV file
-   Send file contents to server via TCP socket

------------------------------------------------------------------------

## Task 3: Receive Records from Server

Receive database records sent by the server.

Each record format:

    patientID,name,age,heartRate

------------------------------------------------------------------------

## Task 4: Display Data in JTable

Insert each received record into the GUI table with columns:

-   Patient ID
-   Name
-   Age
-   Heart Rate

------------------------------------------------------------------------

## Evaluation (Part 2)

 | Criteria                            | Points    |
 | ----------------------------------- | --------  |
 | GUI Design (Swing)                  | 10        |
 | File Selection using JFileChooser   | 10        |
 | TCP File Transfer Implementation    | 10        |
 | Receiving Server Response           | 10        |
 | Displaying Data in JTable           | 10        |
 | **Total (Part 2)**                  | **50**    |

------------------------------------------------------------------------

# System Execution

Steps:

1.  Start MySQL Server
2.  Create the database and table
3.  Run `ServerApp.java`
4.  Run `ClientApp.java`
5.  Upload `health_data.csv`
6.  Verify records appear in the GUI table

------------------------------------------------------------------------

# Sample Output

<img width="745" height="495" alt="image" src="https://github.com/user-attachments/assets/a1446f36-b726-4d46-8043-70cf1418d9d1" />


![output2](output2.png)

![output3](output3.png)

------------------------------------------------------------------------

# Submission Instructions

Push the following files to GitHub:

    ServerApp.java
    ClientApp.java

------------------------------------------------------------------------
