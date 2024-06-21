class Menu {
    fun printingFirstMenu() {
        print("1. Ticket\n" +
                "2. Event\n" +
                "3. Order\n"+
                "0. Finish\n"+
                "Choose one of them according to your wish: ")
    }

    fun ticketFunction() {
        println("1. Add Ticket\n" +
                "2. Remove Ticket\n" +
                "3. Print Tickets\n" +
                "0. Back\n")
    }

    fun eventFunction() {
        println("1. Add Event\n" +
                "2. Remove Event\n" +
                "3. Print Event\n" +
                "0. Back\n")
    }

    fun orderFunction() {
        println("1. Add Order\n" +
                "2. Remove Order\n" +
                "3. Print Order\n" +
                "0. Back\n")
    }

    fun chooseOption(x: Int, y: Int): Int {
        var option: Int? = null
        while (option == null) {
            print("Enter valid number (between $x and $y): ")
            val prompt = readln()
            try {
                option = prompt.toInt()
            } catch (e: NumberFormatException) {
                println("Invalid input. Please Enter valid integer.")
            }
        }
        return option
    }

    fun exitMessage() {
        println("Program stopped. Thanks for using it.")
    }

    fun inputTicketDetails(): Ticket {
        println("Enter ticket details:")

        println("Enter event ID:")
        val eventId = readLine().orEmpty()

        println("Enter event name:")
        val eventName = readLine().orEmpty()

        println("Enter date:")
        val date = readLine().orEmpty()

        println("Enter seat number:")
        val seatNumber = readLine().orEmpty()

        println("Enter price:")
        val price = readLine()?.toDoubleOrNull() ?: 0.0

        println("Enter ticket ID (optional):")
        val ticketId = readLine()


        val ticketType = Ticket.TicketType.STANDARD

        return Ticket(
            eventId = eventId,
            eventName = eventName,
            date = date,
            seatNumber = seatNumber,
            price = price,
            ticketId = ticketId,
            ticketType = ticketType
        )
    }

    fun removeTicket(ticketList: ArrayList<Ticket>): Boolean {
        print("Enter the ID of the ticket you want to remove: ")
        val ticketId = readLine().toString()
        val ticketToRemove = ticketList.find { it.eventId == ticketId }
        if (ticketToRemove != null) {
            ticketList.remove(ticketToRemove)
            println("Ticket removed successfully!")
            return true
        } else {
            println("Ticket with ID $ticketId not found.")
            return false
        }
    }

    fun printAllTickets(ticketList: ArrayList<Ticket>) {
        if (ticketList.isNotEmpty()) {
            println("All Tickets:")
            ticketList.forEachIndexed { index, ticket ->
                println("Ticket ${index + 1}:")
                println("Event ID: ${ticket.eventId}")
                println("Event Name: ${ticket.eventName}")
                println("Date: ${ticket.date}")
                println("Seat Number: ${ticket.seatNumber}")
                println("Price: ${ticket.price}")
                println()
            }
        } else {
            println("No tickets found.")
        }
    }

    fun addNewEvent(eventList: ArrayList<EventBase>) {
        println("Enter event details:")
        print("Event ID: ")
        val eventId = readLine().toString()
        print("Event Name: ")
        val eventName = readLine().toString()
        print("Date: ")
        val date = readLine().toString()
        print("Time: ")
        val time = readLine().toString()
        print("Location: ")
        val location = readLine().toString()
        print("Event Type (Concert/Movie): ")
        val eventType = readLine().toString()
        println("concert or movie")
        val newEvent = when (eventType.lowercase()) {
            "concert" -> {
                print("Artist: ")
                val artist = readLine().toString()
                ConcertEvent(eventId, eventName, date, time, location, artist)
            }
            "movie" -> {
                print("Genre: ")
                val genre = readLine().toString()
                MovieEvent(eventId, eventName, date, time, location, genre)
            }
            else -> {
                println("Invalid event type.")
                return
            }
        }
        eventList.add(newEvent)
        println("Event added successfully!")
    }

    fun removeEvent(eventList: ArrayList<EventBase>) {
        println("Enter the ID of the event you want to remove:")
        val eventId = readLine().toString()
        val eventToRemove = eventList.find { it.eventId == eventId }
        if (eventToRemove != null) {
            eventList.remove(eventToRemove)
            println("Event removed successfully!")
        } else {
            println("Event with ID $eventId not found.")
        }
    }

    fun printAllEvents(eventList: ArrayList<EventBase>) {
        if (eventList.isNotEmpty()) {
            println("All Events:")
            eventList.forEachIndexed { index, event ->
                println("Event ${index + 1}:")
                println("Event ID: ${event.eventId}")
                println("Event Name: ${event.eventName}")
                println("Date: ${event.date}")
                println("Time: ${event.time}")
                println("Location: ${event.location}")
                println(event.getAdditionalDetails())     //////// polymorphism
//                if (event is ConcertEvent) {
//                    println("Type: Concert")
//                    println("Artist: ${event.artist}")
//                } else if (event is MovieEvent) {
//                    println("Type: Movie")
//                    println("Genre: ${event.genre}")
//                }
                println()
            }
        } else {
            println("No events found.")
        }
    }

    fun addNewOrder(ticketList: ArrayList<Ticket>,orderList:ArrayList<Order>) {
        println("Enter order details:")
        print("User ID: ")
        val userId = readLine().toString()
        print("Event ID: ")
        val eventId = readLine().toString()

        val selectedTickets = mutableListOf<Ticket>()
        while (true) {
            println("Enter ticket ID to add (press Enter to finish):")
            val ticketId = readLine()?.toString()?.trim()
            if (ticketId.isNullOrEmpty()) {
                break
            }
            val ticket = ticketList.find { it.ticketId == ticketId }
            if (ticket != null) {
                selectedTickets.add(ticket)
            } else {
                println("Ticket with ID $ticketId not found.")
            }
        }

        val totalPrice = selectedTickets.sumByDouble { it.price }

        val newOrder = Order(userId, eventId, selectedTickets, totalPrice)

        orderList.add(newOrder)

        println("Order added successfully!")
    }

//    fun removeOrder(orderList:ArrayList<Order>) {
//        println("Enter the ID of the order you want to remove:")
//        val userId = readLine().toString()
//        val orderToRemove = orderList.find { it.userId == userId }
//        if (orderToRemove != null) {
//            orderList.remove(orderToRemove)
//            println("Order removed successfully!")
//        } else {
//            println("Order with ID $userId not found.")
//        }
//    }

    fun removeOrder(orderList: MutableList<Order>) {
        println("Enter the ID of the order you want to remove:")
        val userId = readLine().toString()
        val iterator = orderList.iterator()
        while (iterator.hasNext()) {
            val order = iterator.next()
            if (order.matchesUserId(userId)) {
                iterator.remove()
                println("Order removed successfully!")
            }
        }
    }

    fun printAllOrders(orderList: ArrayList<Order>) {
        if (orderList.isNotEmpty()) {
            println("All Orders:")
            orderList.forEachIndexed { index, order ->
                println("Order ${index + 1}:")
                order.displayOrderDetails()
                println()
            }
        } else {
            println("No orders found.")
        }
    }
}
