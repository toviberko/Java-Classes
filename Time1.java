
/**
 * Represents time - hours:minutes. Values cannot be negative.
 *
 * @author Tovy Berkowitz
 */
public class Time1
{
    private int _hour;
    private int _minute;

    private static final int MAX_HOUR = 24;
    private static final int MIN_VALUE = 0;
    private static final int MAX_MINUTE = 60;
    private static final int ONE_DIGIT = 9;

    /**
     * Constructs a Time1 object. Construct a new time instance with the specified hour and minute. hour should be between 0-23, otherwise it should be set to 0. minute should be between 0-59, otherwise it should be set to 0.
     * @param h the hour of the time
     * @param m the minute of the time
     */

    public Time1(int h, int m){
        if(h >= MIN_VALUE && h < MAX_HOUR){ //if the value is valid, put it in _hour
            _hour = h;
        }else{ //else, put the minimum value
            _hour = MIN_VALUE;
        }

        if(m >= MIN_VALUE && m < MAX_MINUTE){ //if the value is valid, put it in _minute
            _minute = m;
        }else{ //else, put the minimum value
            _minute = MIN_VALUE;
        }
    }

    /**
     * Copy constructor for Time1. Construct a time with the same instance variables as another time.
     * @param other The time object from which to construct the new time
     */
    public Time1(Time1 other){
        _hour = other._hour; //put the values of other in the current objcet
        _minute = other._minute;
    }

    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour(){
        return(_hour);
    }

    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute(){
        return(_minute);
    }

    /**
     * Changes the hour of the time. If an illegal number is received hour will be unchanged.
     * @param num The new hour
     */
    public void setHour(int num){
        if(num >= MIN_VALUE && num < MAX_HOUR){ //if the value is valid, set the given value
            _hour = num;
        }
    }

    /**
     * Changes the minute of the time. If an illegal number is received minute will be unchanged.
     * @param num The new minute
     */
    public void setMinute(int num){
        if(num >= MIN_VALUE && num < MAX_MINUTE){ //if the value is valid, set the given value
            _minute = num;
        }
    }

    /**
     * Return a string representation of this time (hh:mm).
     * @return String representation of this time (hh:mm).
     */
    public String toString(){
        String minString;
        String hourString;

        if(_minute <= ONE_DIGIT){ //if the minute has one digit, add a 0 before.
            minString = "0" + _minute;
        }else{
            minString = "" + _minute;
        }

        if(_hour <= ONE_DIGIT){ //if the hour has one digit, add 0 before.
            hourString = "0" + _hour;
        }else{
            hourString = "" + _hour;
        }

        return(hourString + ":" + minString);
    }

    /**
     *     Return the amount of minutes since midnight.
     *     @return Return the amount of minutes since midnight
     */
    public int minFromMidnight(){
        return(_hour*MAX_MINUTE + _minute); //hours converted to minutes plus the minutes.
    }

    /**
     * Check if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return True if the received time is equal to this time
     */
    public boolean equals(Time1 other){
        return(other.getMinute() == _minute && other.getHour() == _hour); //check if the values are equal
    }

    /**    
     * Check if this time is before a received time.
     * @param other The time to check if this point is before
     * @return True if this time is before other time
     */
    public boolean before(Time1 other){
        if(_hour < other.getHour()){ //if the hour is before, the time is before
            return(true);
        }else if(_hour == other.getHour() && _minute < other.getMinute()){ //if the hour is the same and the minute is before, the time is before.
            return(true);
        }else{ //otherwise, the time is not before
            return(false);
        }
    }

    /**
     * Check if this time is after a received time.
     * @param other The time to check if this point is after
     * @return True if this time is after other time
     */
    public boolean after(Time1 other){
        return(other.before(this)); //if other is before this, this is after other.
    }

    /**
     * Calculates the difference (in minutes) between two times. Assumption: this time is after other time.
     * @param other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference(Time1 other){
        return(MAX_MINUTE*(_hour - other.getHour()) + (_minute - other.getMinute())); 
        //the difference between the hours converted to minutes, plus the difference between the minutes.
    }

    /**
     * Copy current object and add requested minutes to new object.
     * @param num The minutes need to add.
     * @return new update Time1 object.
     */
    public Time1 addMinutes(int num){
        int newHour, newMin;    

        newHour = num / MAX_MINUTE + _hour; //the minutes added converted to hours, plus the current hour.
        newMin = num % MAX_MINUTE + _minute; //the minutes that don't make a full hour, plus the current minute. It's between -59 and 119.

        if(newMin >= MAX_MINUTE){ //if the new minute is greater or equal the maximum, there are full hours in it
            newHour += newMin/MAX_MINUTE; //adding the full hours that were in the minutes.
            newMin = newMin - MAX_MINUTE*(newMin/MAX_MINUTE); //the new minute is the minutes minus the full hours that are in the minutes.
        }

        if(newMin < MIN_VALUE){ //if the new minute is below the minimum
            newMin = MAX_MINUTE + newMin; //adding an hour to make the minutes positive (the newMin is greater than -59).
            newHour -= 1; //taking off an hour to add it to the minutes
        }

        if(newHour < MIN_VALUE){ //if the new hour is below the minimum
            newHour = (-newHour/MAX_HOUR + 1)*MAX_HOUR + newHour; //adding the days needed plus the hour
        }
        
        if(newHour >= MAX_HOUR){ //if the new hour is over the maximum
            newHour = newHour % MAX_HOUR; //take the hour after we subtract the full days
        }

        Time1 newTime = new Time1(newHour, newMin);
        return(newTime);
    }
}
