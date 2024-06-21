abstract class EventBase(       // abstract class used here
    val eventId: String,
    val eventName: String,
    val date: String,
    val time: String,
    val location: String
) {
    abstract fun getEventDetails(): String
    abstract fun getAdditionalDetails(): String

    override fun toString(): String {
        return "EventBase(id='$eventId', name='$eventName', date='$date', time='$time', venue='$location')"
    }
}

class ConcertEvent(
    eventId: String,
    eventName: String,
    date: String,
    time: String,
    location: String,
    val artist: String
) : EventBase(eventId, eventName, date, time, location) {     //////// inheritance used /////////
    override fun getEventDetails(): String {  //polymorphism
        return "Concert: $eventName\nDate: $date\nTime: $time\nLocation: $location\nArtist: $artist"
    }
    override fun getAdditionalDetails(): String {
        return "Type: Concert\nArtist: $artist"
    }



    override fun toString(): String {
        return "ConcertEvent(id='$eventId', name='$eventName', date='$date', time='$time', venue='$location', artist='$artist')"
    }
}

class MovieEvent(
    eventId: String,
    eventName: String,
    date: String,
    time: String,
    location: String,
    val genre: String
) : EventBase(eventId, eventName, date, time, location) {
    override fun getEventDetails(): String {    ///////////////// polymorphism ////////////////
        return "Movie: $eventName\nDate: $date\nTime: $time\nLocation: $location\nGenre: $genre"
    }

    override fun getAdditionalDetails(): String {
        return "Type: Movie\nGenre: $genre"
    }

    override fun toString(): String {
        return "MovieEvent(id='$eventId', name='$eventName', date='$date', time='$time', location='$location', genre='$genre')"
    }
}
