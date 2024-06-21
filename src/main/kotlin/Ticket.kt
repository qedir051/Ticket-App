class Ticket internal constructor(
    val eventId: String,
    val eventName: String,
    val date: String,
    val seatNumber: String,
    val price: Double,
    val ticketId: String?,
    val ticketType: TicketType
) {
    enum class TicketType {
        STANDARD,
        VIP,
        CHILD,
    }

    override fun toString(): String {
        return "Ticket(id='$eventId', name='$eventName', date='$date', seatNumber='$seatNumber', price=$price, ticketId='$ticketId', type=$ticketType)"
    }

    companion object {                     /////////// Companion used here
        fun createTicketFromUserInput(): Ticket {
            println("Enter event ID:")
            val eventId = readLine().orEmpty()

            println("Enter event name:")
            val eventName = readLine().orEmpty()

            println("Enter date:")
            val date = readLine().orEmpty()

            println("Enter seat number:")
            val seatNumber = readLine().orEmpty()

            println("Enter price:")
            // val price = readLine()?.toDoubleOrNull() ?: 0.0
            val price = readPrice()

            println("Enter ticket ID (optional):")
            val ticketId = readLine()

            val ticketType = TicketType.STANDARD

            return Ticket(eventId, eventName, date, seatNumber, price, ticketId, ticketType)
        }

        private fun readPrice(): Double {
            while (true) {
                val input = readlnOrNull()

                try {
                    val price = input?.toDouble() ?: throw NumberFormatException("Invalid input")
                    if (price <= 0.0) {
                        throw IllegalArgumentException("Price must be greater than 0")
                    }
                    return price
                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a valid number.")
                } catch (e: IllegalArgumentException) {
                    println(e.message)
                }
            }
        }
    }

    // Function to display ticket details
    fun displayDetails() {
        println("Event ID: $eventId")
        println("Event Name: $eventName")
        println("Date: $date")
        println("Seat Number: $seatNumber")
        println("Price: $price")
        println("Ticket Type: $ticketType")
    }
}
