
public class Event {

	private Show show;
	private String date;
	private String time;

	public Event(){
		this.setShow(null);
		this.setDate("Default Event Date");
		this.setTime("Default event time");
	}

	public Event(Show show, String date, String time) {
		this.show = show;
		this.date = date;
		this.time = time;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String toString() {
		return show.getName() + date + time;
	}
}
