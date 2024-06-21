import java.util.EventListener
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.Statement

fun main() {
    val ticketList: ArrayList<Ticket> = ArrayList()

    val eventList: ArrayList<EventBase> = ArrayList()

    val orderList: ArrayList<Order> = ArrayList()


    ticketList.add(Ticket("1", "Concert Ticket", "2024-06-01", "Stadium", 50.0,"1",Ticket.TicketType.VIP))
    ticketList.add(Ticket("2", "Movie Ticket", "2024-06-02", "Cinema", 40.0,"2",Ticket.TicketType.CHILD))

    eventList.add(ConcertEvent("101", "Concert", "2024-06-01", "19:00", "Stadium", "Artist Name"))
    eventList.add(MovieEvent("102", "Movie", "2024-06-02", "20:00", "Cinema", "Action"))

    val ticketsInOrder: ArrayList<Ticket> = ArrayList()
    ticketsInOrder.add(ticketList[0])
    ticketsInOrder.add(ticketList[1])
    orderList.add(Order("user123", "101", ticketsInOrder))

    val order = Order("user123", "101", ticketsInOrder)
    orderList.add(order)

    var awake: Boolean = true
    var option: Int?
    val menu = Menu()      //////// Object for menu commands ///////// Object is used here

    while (awake) {
        menu.printingFirstMenu()
        option = menu.chooseOption(0, 3)
        if (option == 1) {
            menu.ticketFunction()
            when(menu.chooseOption(0,3)) {
                1 ->{
                    val newTicket = Ticket.createTicketFromUserInput()
                    ticketList.add(newTicket)
                    println("Ticket added successfully!")
                }
                2 -> {
                    menu.printAllTickets(ticketList)
                    menu.removeTicket(ticketList)
                    continue
                }
                3 -> {
                    menu.printAllTickets(ticketList)
                    continue
                }
                0 -> continue
            }
        }
        else if (option == 2) {
            menu.eventFunction()
            when(menu.chooseOption(0,3)) {
                1 ->{ menu.addNewEvent(eventList)
                    continue
                }
                2 -> {
                    menu.printAllEvents(eventList)
                    menu.removeEvent(eventList)
                    continue
                }
                3 -> {
                    menu.printAllEvents(eventList)
                    continue
                }
                0 -> continue
            }
        }
        else if (option == 3) {
            menu.orderFunction()
            when(menu.chooseOption(0,3)) {
                1 ->{ menu.addNewOrder(ticketList,orderList)
                    continue
                }
                2 -> {
                    menu.printAllOrders(orderList)
                    menu.removeOrder(orderList)
                    continue
                }
                3 -> {
                    menu.printAllOrders(orderList)
                    continue
                }
                0 -> continue
            }
        }
        else if (option == 0) {
            menu.exitMessage()
            awake = false
        }
        else {
             if (option !in 0..3) println("Enter valid number (between 0 and 3) !!!")
              continue }
    }

    order.setUserId("newUser123")         ///////////// encapsulation used here

    val information = Information(
        orders = orderList,
        tickets = ticketList,
        events = eventList
    )

    println(information)

    val desktopPath = "C:/Users/USER/Desktop"
    val url = "jdbc:sqlite:$desktopPath/database/new.db"
    var connection: Connection? = null

    try {
        // Load SQLite JDBC driver (not always necessary in recent JVMs)
        Class.forName("org.sqlite.JDBC")

        // Establish connection
        connection = DriverManager.getConnection(url)
        println("Connection to SQLite has been established.")

        val statement: Statement = connection.createStatement()

        // Example: Create a table
        var sql = "CREATE TABLE IF NOT EXISTS tickets (" +
                "id TEXT," +
                "eventName TEXT," +
                "date TEXT," +
                "seatNumber TEXT," +
                "price REAL," +
                "ticketType TEXT);"

        statement.executeUpdate(sql)
        println("Table created successfully.")

        // SQL statement for inserting data
        sql = "INSERT INTO tickets (id, eventName, date, seatNumber, price, ticketType) VALUES (?, ?, ?, ?, ?, ?)"

        // Prepare the statement
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)

        // Iterate through ticketList and insert each Ticket
        for (ticket in ticketList) {
            preparedStatement.setString(1, ticket.eventId)
            preparedStatement.setString(2, ticket.eventName)
            preparedStatement.setString(3, ticket.date)
            preparedStatement.setString(4, ticket.seatNumber)
            preparedStatement.setDouble(5, ticket.price)
            preparedStatement.setString(6, ticket.ticketType.toString()) // Assuming ticketType is an enum

            // Execute the statement
            preparedStatement.executeUpdate()
        }

        println("Data inserted into database.")

    } catch (e: Exception) {
        println("Error connecting to SQLite database: ${e.message}")
    } finally {
        try {
            connection?.close()
        } catch (e: Exception) {
            println("Error closing connection: ${e.message}")
        }
    }

    try {
        // Load SQLite JDBC driver (not always necessary in recent JVMs)
        Class.forName("org.sqlite.JDBC")

        // Establish connection
        connection = DriverManager.getConnection(url)
        println("Connection to SQLite has been established.")

        val statement: Statement = connection.createStatement()

        // Example: Create a table
        var sql = "CREATE TABLE IF NOT EXISTS orders (" +
                "userId TEXT," +
                "eventId TEXT," +
                "ticketList TEXT," +
                "price REAL);"

        statement.executeUpdate(sql)
        println("Table created successfully.")

        // SQL statement for inserting data
        sql = "INSERT INTO orders (userId, eventId, ticketList, price) VALUES (?, ?, ?, ?)"

        // Prepare the statement
        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)

        // Iterate through ticketList and insert each Ticket
        for (ticket in orderList) {
            preparedStatement.setString(1, order.getUserId())
            preparedStatement.setString(2, order.eventId)
            preparedStatement.setString(3, order.ticketList.toString())
            preparedStatement.setDouble(4, order.totalPrice)

            // Execute the statement
            preparedStatement.executeUpdate()
        }

        println("Data inserted into database.")

    } catch (e: Exception) {
        println("Error connecting to SQLite database: ${e.message}")
    } finally {
        try {
            connection?.close()
        } catch (e: Exception) {
            println("Error closing connection: ${e.message}")
        }
    }
}

