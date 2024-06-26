# Ticketing System README
## Table of Contents
1. Introduction
2. Features
3. Usage
4. Classes and Functions
5. Database Integration
6. Running the Application
7. Dependencies
8. Conclusion
## Introduction
This is a simple ticketing system application written in Kotlin. The application allows users to manage tickets, events, and orders. It also integrates with a SQLite database to store and retrieve ticket and order information.

## Features
* Add, view, and remove tickets.
* Add, view, and remove events.
* Add, view, and remove orders.
* Store and retrieve ticket and order information from a SQLite database.
## Usage
The application provides a text-based menu for interacting with the system. Users can navigate through the menu to perform various operations on tickets, events, and orders.

## Classes and Functions
### Main Class
The main class initializes the lists for tickets, events, and orders, and provides the main menu loop for user interaction.

### Ticket Class
Represents a ticket with properties such as id, eventName, date, seatNumber, price, eventId, and ticketType.

### EventBase Class
The base class for events with properties such as id, name, date, time, and location.

### ConcertEvent Class
Inherits from EventBase and adds a specific property for concerts: artistName.

### MovieEvent Class
Inherits from EventBase and adds a specific property for movies: genre.

### Order Class
Represents an order with properties such as userId, eventId, ticketList, and totalPrice.

### Information Class
Holds lists of orders, tickets, and events for encapsulating the data.

### Menu Class
Handles user interaction and menu navigation. Provides methods for printing menus, adding, viewing, and removing tickets, events, and orders.

## Database Integration
The application uses SQLite for persistent storage of ticket and order data.

### Creating Tables
* Tickets Table: Contains columns for id, eventName, date, seatNumber, price, and ticketType.
* Orders Table: Contains columns for userId, eventId, ticketList, and price.
### Inserting Data
The application prepares SQL statements for inserting ticket and order data into their respective tables and executes these statements within a try-catch block for error handling.

## Running the Application
1. Ensure you have Kotlin and a suitable IDE (e.g., IntelliJ IDEA) installed.
2. Clone the repository and open the project in your IDE.
3. Run the main function from the Main.kt file.
4. Follow the on-screen menu to interact with the application.
## Dependencies
* Kotlin Standard Library
* SQLite JDBC Driver
## Adding SQLite JDBC Driver
To use the SQLite JDBC driver, add the following dependency to your build.gradle file:
```
dependencies {
implementation "org.xerial:sqlite-jdbc:3.36.0.3"
}
```
## Conclusion
This ticketing system demonstrates basic CRUD operations and database integration in a Kotlin application. The structure is modular and can be extended with additional features as needed.
