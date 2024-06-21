class Order(
    private var userId: String,
    val eventId: String,
    val ticketList: List<Ticket>,
    var totalPrice: Double = 0.0,
) {
    override fun toString(): String {
        return "Order(userId='$userId', eventId='$eventId', tickets=$ticketList, totalPrice = $totalPrice)"
    }

    init {
        calculateTotalPrice()
    }

    fun getUserId(): String {           ///////// encapsulation used here
        return this.userId
    }

    fun setUserId(userId: String) {
        this.userId = userId
    }

    fun matchesUserId(targetUserId: String): Boolean {
        return userId == targetUserId
    }

    // error handling
    private fun calculateTotalPrice() {
        try {
            require(ticketList.isNotEmpty()) { "Ticket list cannot be empty." }

            totalPrice = ticketList.sumByDouble { it.price }
        } catch (e: IllegalArgumentException) {
            println("Error calculating total price: ${e.message}")
            totalPrice = 0.0 // Set total price to 0 in case of error
        }
    }

    fun displayOrderDetails() {
        println("User ID: $userId")
        println("Event ID: $eventId")
        println("Total Price: $totalPrice")
        println("Tickets:")
        ticketList.forEachIndexed { index, ticket ->
            println("Ticket ${index + 1}:")
            ticket.displayDetails()
        }
    }
}
