
/**
 * Represents a flight. A Flight object is represented by the flight's origin,destination,departure time, 
 * flight duration, no of passengers,if it is full and the price.
 */
public class Flight
{
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;
    public static final int MAX_CAPACITY = 250;
    public static final int DEFAULT_VALUE = 0;

    /**
     * Constructor for a Flight object.
     * If the number of passengers exceeds the maximum capacity the number of passengers will be set to the maximum capacity.
     * If the number of passengers is negative the number of passengers will be set to zero.
     * If the flight duration is negative the flight duration will be set to zero.
     * If the price is negative the price will be set to zero.
     * 
     * @param dest The city the flight lands at.
     * @param origin The city the flight leaves from.
     * @param depHour the departure hour (should be between 0-23).
     * @param depMinute The departure minute (should be between 0-59).
     * @param durTimeMinutes The duration time in minutes(should not be negative).
     * @param noOfPass The number of passengers (should be between 0-maximum capacity).
     * @param price The price (should not be negative).
     */
    public Flight​(java.lang.String origin,
    java.lang.String dest,
    int depHour,
    int depMinute,
    int durTimeMinutes,
    int noOfPass,
    int price){
        if(noOfPass > MAX_CAPACITY){ //if the number of passengers is greater than the max capacity, _noOfPassengers is the max capacity.
            _noOfPassengers = MAX_CAPACITY;
        }else if(noOfPass < DEFAULT_VALUE){ //if the number of passengers is less than the minimum, _noOfPassengers is the minimum.
            _noOfPassengers = DEFAULT_VALUE;
        }else{
            _noOfPassengers = noOfPass;
        }

        if(_noOfPassengers == MAX_CAPACITY){ //if the number of passengers is the maximum, the flight is full.
            _isFull = true;
        }else{
            _isFull = false;
        }

        if(durTimeMinutes < DEFAULT_VALUE){ //if the flight duration is less than the minimum, _flightDuration is the minimum.
            _flightDuration = DEFAULT_VALUE;
        }else{
            _flightDuration = durTimeMinutes;
        }

        if(price < DEFAULT_VALUE){ //if the price is less than the minimum, _price is the minimum.
            price = DEFAULT_VALUE;
        }else{
            _price = price;
        }

        _departure = new Time1(depHour, depMinute); 
        _origin = origin;
        _destination = dest;
    }

    /**
     * Copy constructor for a Flight object. Construct a Flight object with the same attributes as another Flight object.
     * @param other The Flight object from which to construct the new Flight.
     */
    public Flight​(Flight other){
        _origin = other._origin;
        _destination = other._destination;
        _departure = new Time1(other._departure);
        _flightDuration = other._flightDuration;
        _noOfPassengers = other._noOfPassengers;
        _isFull = other._isFull;
        _price = other._price;
    }

    /**
     * Returns the flight origin.
     * @return The flight origin.
     */
    public String getOrigin(){
        return(_origin);
    }

    /**
     * Returns the flight destination.
     * @return the flight destination.
     */
    public String getDestination(){
        return(_destination);
    }

    /**
     * Returns the flight departure time.
     * @return A copy of the flight departure time.
     */
    public Time1 getDeparture(){
        return(new Time1(_departure));
    }

    /**
     * Returns the flight duration time in minutes.
     * @return The flight duration.
     */
    public int getFlightDuration(){
        return(_flightDuration);
    }

    /**
     * Returns the number of passengers on the flight.
     * @return The number of passengers.
     */
    public int getNoOfPassengers(){
        return(_noOfPassengers);
    }

    /**
     * Returns whether the flight is full or not.
     * @return True if the flight is full.
     */
    public boolean getIsFull(){
        return(_isFull);
    }

    /**
     * Returns the price of the flight.
     * @return The price.
     */
    public int getPrice(){
        return(_price);
    }

    /**
     * Changes the flight's destination.
     * @param dest The flight's new destination.
     */
    public void setDestination(String dest){
        _destination = dest;
    }

    /**
     * Changes the flight's origin.
     * @param origin The flight's new origin.
     */
    public void setOrigin(String origin){
        _origin = origin;
    }

    /**
     * Changes the flight's departure time.
     * @param departureTime The flight's new departure time.
     */
    public void setDeparture(Time1 departureTime){
        _departure = new Time1(departureTime);
    }

    /**
     * Changes the flight's duration time. 
     * If the parameter is negative the duration time will remain unchanged.
     * @param durTimeMinutes The flight's new duration time.
     */
    public void setFlightDuration(int durTimeMinutes){
        if(durTimeMinutes >= DEFAULT_VALUE){
            _flightDuration = durTimeMinutes;
        }
    }

    /**
     * Changes the number of passengers. 
     * If the parameter is negative or larger than the maximum capacity the number of passengers will remain unchanged.
     * @param noOfPass The new number of passengers.
     */
    public void setNoOfPassengers​(int noOfPass){
        if(noOfPass >= DEFAULT_VALUE && noOfPass <= MAX_CAPACITY){
            _noOfPassengers = noOfPass;
        }

        if(_noOfPassengers == MAX_CAPACITY){ //if the number of passengers is the maximum, the flight is full.
            _isFull = true;
        }else{
            _isFull = false;
        }
    }

    /**
     * Changes the flight price.
     * If the parameter is negative the price will remain unchanged.
     * @param price The new price.
     */
    public void setPrice​(int price){
        if(price >= DEFAULT_VALUE){
            _price = price;
        }
    }

    /**
     * Check if the received flight is equal to this flight.
     * Flights are considered equal if the origin, destination and departure times are the same.
     * @param other The flight to be compared with this flight.
     * @return True if the received flight is equal to this flight.
     */
    public boolean equals​(Flight other){
        return(_origin.equals(other._origin) && _destination.equals(other._destination) && _departure.equals(other._departure));
    }

    /**
     * Returns the arrival time of the flight .
     * @return The arrival time of this flight.
     */
    public Time1 getArrivalTime(){
        return(_departure.addMinutes(_flightDuration)); //The arrival time is the departure time plus the flight duration.
    }

    /**
     * Add passengers to this flight.
     * If the number of passengers exceeds he maximum capacity, no passengers are added and false is returned. 
     * If the flight becomes full, the boolean attribute describing whether the flight if full becomes true.
     * @param num The number of passengers to be added to this flight.
     * @return True if the passengers were added to the flight.
     */
    public boolean addPassengers​(int num){
        if(num + _noOfPassengers < MAX_CAPACITY){ //If the number of passengers after the addtion is less than the maximum, add the passengers and return true.
            _noOfPassengers += num;
            return(true);
        }else if(num + _noOfPassengers == MAX_CAPACITY){ //If the number of passengers after the addition is the maximum, the flight is full.
            _noOfPassengers = MAX_CAPACITY;
            _isFull = true;
            return(true);
        }else{
            return(false);
        }
    }

    /**
     * Check if this flight is cheaper than another flight.
     * @param other The flight whose price is to be compared with this flight's price.
     * @return True if this flight is cheaper than the received flight .
     */
    public boolean isCheaper​(Flight other){
        return(_price < other._price);
    }

    /**
     * Calculate the total price of the flight.
     * @return The total price of the flight.
     */
    public int totalPrice(){
        return(_price * _noOfPassengers);
    }

    /**
     * Check if this flight lands before another flight.
     * Note - the flights may land on different days, the method checks which flight lands first.
     * @param other The flight whose arrival time to be compared with this flight's arrival time.
     * @return True if this flight arrives before the received flight.
     */
    public boolean landsEarlier​(Flight other){
        return(getArrivalTime().before(other.getArrivalTime()));
    }

    /**
     * Return a string representation of this flight (for example: "Flight from London to Paris departs at 09:24.Flight is full.").
     * @return String representation of this flight (for example: "Flight from London to Paris departs at 09:24.Flight is full.").
     */
    public String toString(){
        String isFullString;
        if(_isFull){
            isFullString = "Flight is full.";
        }else{
            isFullString = "Flight is not full.";
        }
        return("Flight from " + _origin + " to " + _destination + " departs at " + _departure.toString() + ". " + isFullString);
    }
}
