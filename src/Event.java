/**
 * Event.java - A class to represent an event, which is a Show with a date and
 * time.
 * 
 * @author Taylor Norton
 *
 */
public class Event {

	private Show show;
	private String date;
	private String time;

	/**
	 * Default constructor.
	 */
	public Event() {
		this.setShow(null);
		this.setDate("Default Event Date");
		this.setTime("Default event time");

	}

	/**
	 * Parameterized constructor.
	 * 
	 * @param show - A variable of type Show.
	 * @param date - A variable of type String.
	 * @param time - A variable of type String.
	 */
	public Event(Show show, String date, String time) {
		this.show = show;
		this.date = date;
		this.time = time;
	}

	/**
	 * Returns this Event's Show.
	 * 
	 * @return Show - This Event's Show.
	 */
	public Show getShow() {
		return show;
	}

	/**
	 * Sets this Event's Show to a given Show.
	 * 
	 * @param show - A variable of type Show.
	 */
	public void setShow(Show show) {
		this.show = show;
	}

	/**
	 * Returns this Event's date.
	 * 
	 * @return String - This Event's date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets this Event's date to a given date.
	 * 
	 * @param date - A variable of type String.
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns this Event's time.
	 * 
	 * @return String - This Event's time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets this Event's time to a given time.
	 * 
	 * @param time - A variable of type String.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Returns the String representation of an Event.
	 * 
	 * @return String - The String representation of an Event.
	 */
	public String toString() {
		return show.getName() + " - " + date + " - " + time;
	}
}
