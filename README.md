# Database Test

A simple Java CRUD application using SQLite and JDBC with an ANSI-colored console interface.

## What It Does

This project implements basic database operations:
- **Create**: Add new records to the database
- **Read**: View all records or search specific ones
- **Update**: Modify existing records
- **Delete**: Remove records from the database

The console interface uses ANSI colors to improve readability (green for success, red for errors, etc.).

## Requirements

- Java JDK 21 or higher
- SQLite JDBC Driver

## Setup in IntelliJ IDEA

### 1. Clone the Repository
```bash
git clone https://github.com/Faiseven/database-test.git
```

### 2. Open Project in IntelliJ
- Open IntelliJ IDEA
- File → Open → Select the `database-test` folder
- Wait for IntelliJ to index the project

### 3. Download SQLite JDBC Driver

**Option A: Manual Download**
1. Download the SQLite JDBC driver from: https://github.com/xerial/sqlite-jdbc/releases
2. Download the `.jar` file (e.g., `sqlite-jdbc-3.45.0.0.jar`)
3. In IntelliJ: File → Project Structure → Libraries → + → Java
4. Select the downloaded `.jar` file

**Option B: Using Maven** (if you add a `pom.xml`)
```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.45.0.0</version>
</dependency>
```

### 4. Run the Application
- Locate the `Main.java` file
- Right-click → Run 'Main.main()'
- The console interface will appear with the menu options

## Usage

When you run the program, you'll see a menu with these options:
1. Create a new record
2. View all records
3. Search for a record
4. Update a record
5. Delete a record
6. Exit

Simply enter the number corresponding to the action you want to perform.

## Technologies Used

- Java 21
- SQLite
- JDBC
- ANSI color codes for console styling

## Author

**Faiseven** - [GitHub](https://github.com/Faiseven)