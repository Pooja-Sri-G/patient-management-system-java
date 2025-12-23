# Patient Management System

## Description
The Patient Management System is a Java desktop application developed using Swing for the graphical user interface and JDBC for database connectivity.
It allows users to add, view, and delete patient records stored in an Oracle database.
This project was developed as part of academic learning to demonstrate core concepts of Java Swing, JDBC, prepared statements, and event-driven programming.

## Features
- Auto-generated Patient ID using Oracle sequence
- Add new patient records
- View all stored patient details
- Delete patient records using Patient ID
- GUI-based interaction using Java Swing

---

## Technologies Used
- Java
- Java Swing
- JDBC
- Oracle Database
- SQL

---

## Database Configuration
This application uses an Oracle Database.

### Required Database Objects
- Table: `patients`
- Sequence: `seq`

### Sample SQL Setup
```sql
CREATE TABLE patients (
    pid NUMBER PRIMARY KEY,
    name VARCHAR2(50),
    age NUMBER,
    email VARCHAR2(50),
    phone NUMBER
);

CREATE SEQUENCE seq
START WITH 1
INCREMENT BY 1;
```
## Application Configuration

```java
Connection con = DriverManager.getConnection(
    "jdbc:oracle:thin:@localhost:1521:xe",
    "SYSTEM",
    "your_password"
);
```

## How to Run
1. Install Java (JDK 8 or above)
2. Install and configure Oracle Database
3. Create the required table and sequence
4. Add the Oracle JDBC driver to the project
5. Compile and run the Forms.java file

## Scope and Limitations
- Designed as a single-user desktop application
- Basic input validation
- Intended for academic and learning purposes

## Author
Pooja Sri G
