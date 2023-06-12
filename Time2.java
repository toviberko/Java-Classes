
/**
 * Represents time - hours:minutes. Values cannot be negative.
 *
 * @author Tovy Berkowitz
 */
public class Time2
{
    private int _minFromMid;

    private static final int MAX_HOUR = 24;
    private static final int MIN_VALUE = 0;
    private static final int MAX_MINUTE = 60;
    private static final int ONE_DIGIT = 9;
    
    /**
     * Constructs a Time2 object. Construct a new time instance with the specified hour and minute. hour should be between 0-23, otherwise it should be set to 0. minute should be between 0-59, otherwise it should be set to 0.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time2(int h, int m){
        int hour, minute;
        
        if(h >= MIN_VALUE && h < MAX_HOUR){ //if the value is valid, put it in _hour
            hour = h;
        }else{ //else, put the minimum value
            hour = MIN_VALUE;
        }

        if(m >= MIN_VALUE && m < MAX_MINUTE){ //if the value is valid, put it in _minute
            minute = m;
        }else{ //else, put the minimum value
            minute = MIN_VALUE;
        }
        
        _minFromMid = MAX_MINUTE * hour + minute; //converting the hours to minutes
    }

    /**
     * Copy constructor for Time2. Construct a time with the same instance variables as another time.
     * @param other The time object from which to construct the new time
     */
    public Time2(Time2 other){
        _minFromMid = other._minFromMid;
    }

    /**
     *     Returns the hour of the time.
     *     @return The hour of the time
     */
    public int getHour(){
        return(_minFromMid/MAX_MINUTE); //converting minutes into full hours
    }

    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute(){
        return(_minFromMid % MAX_MINUTE); //destracting the full hours, remaining with the excess minutes
    }

    /**
     * Changes the hour of the time. If an illegal number is received hour will be unchanged.
     * @param num The new hour
     */
    public void setHour(int num){
        if(num >= MIN_VALUE && num < MAX_HOUR){
            _minFromMid = num * MAX_MINUTE + getMinute(); //the minutes from midnight are the hour that was set - converted to minutes, added to the minutes from before.
        }
    }

    /**
     * Changes the minute of the time. If an illegal number is received minute will be unchanged.
     * @param num The new minute
     */
    public void setMinute(int num){
        if(num >= MIN_VALUE && num < MAX_MINUTE){
            _minFromMid = num + getHour()* MAX_MINUTE; //the minutes from midnight are the minute that was set, added to the hours from before converted to minutes.
        }
    }

    /**
     * Return a string representation of this time (hh:mm).
     * @return String representation of this time (hh:mm).
     */
    public String toString(){
        int newHour = _minFromMid / MAX_MINUTE; //the full hours
        int newMinute = _minFromMid % MAX_MINUTE; //the excess of the full hours
        String hourString;
        String minString;

        if(newMinute <= ONE_DIGIT){ //if the minute has one digit, add a 0 before.
            minString = "0" + newMinute;
        }else{
            minString = "" + newMinute;
        }

        if(newHour <= ONE_DIGIT){ //if the hour has one digit, add a 0 before.
            hourString = "0" + newHour;
        }else{
            hourString = "" + newHour;
        }
        return(hourString + ":" + minString);
    }

    /**
     * Return the amount of minutes since midnight.
     * @return Return the amount of minutes since midnight
     */
    public int minFromMidnight(){
        return(_minFromMid);
    }

    /**
     * Check if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return True if the received time is equal to this time
     */
    public boolean equals(Time2 other){
        return(other.minFromMidnight() == _minFromMid); //return true if the values are equal
    }

    /**    
     * Check if this time is before a received time.
     * @param other The time to check if this point is before
     * @return True if this time is before other time
     */
    public boolean before(Time2 other){
        return((_minFromMid < other.minFromMidnight())); //if the minutes from midnight are fewer, this time is before. 
    }

    /**
     * Check if this time is after a received time.
     * @param other The time to check if this point is after
     * @return True if this time is after other time
     */
    public boolean after(Time2 other){
        return(other.before(this)); //if other is before this, this is after other.
    }

    /**
     * Calculates the difference (in minutes) between two times. Assumption: this time is after other time.
     * @param other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference(Time2 other){
        return(_minFromMid - other.minFromMidnight()); //decreasing the other minutes from midnight from the current.
    }

    /**
     * Copy current object and add requested minutes to new object.
     * @param num The minutes need to add.
     * @return new update Time2 object.
     */
    public Time2 addMinutes(int num){
        int newHour, newMin;    

        newHour = num / MAX_MINUTE + getHour();//the minutes added are converted to hours, plus the current hour.
        newMin = num % MAX_MINUTE + getMinute(); //the minutes that don't make a full hour, plus the current minute. It's between -59 and 118.

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
        
        Time2 newTime = new Time2(newHour, newMin);
        return(newTime);
    }
}
