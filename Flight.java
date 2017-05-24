import java.text.SimpleDateFormat;
import java.util.*;

public class Flight {

	static int unpublishedNum = 0;
	private String flightID;
	private String startTime;
	private String arrivalTime;
	private String startCity;
	private String arrivalCity;
	private String departureDate;
	private int price;
	private int currentPassengers;
	private int seatCapacity;
	
	private ArrayList<Seat> seats = new ArrayList<Seat> ();
	private ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	private ArrayList<Order> orders = new ArrayList<Order> ();

	enum Status {UNPUBLISHED, AVAILABLE, FULL, TERMINATE};
	private Status flightStatus;
	private void initiateSeat(int n) {
		
	}
	public Flight(String FlightID1, String startTime1, String arrivalTime1, String startCity1,String arrivalCity1, String departureDate1, int price1, int currentPassengers1, int seatCapacity1)
	{
		flightID = FlightID1;
		startTime = startTime1;
		arrivalTime = arrivalTime1;
		startCity = startCity1;
		arrivalCity = arrivalCity1;
		departureDate = departureDate1;
		price = price1;
		currentPassengers = currentPassengers1;
		seatCapacity = seatCapacity1; 
		flightStatus = Status.UNPUBLISHED;
		unpublishedNum++;
		//initiateSeat(seatCapacity);
		for (int i=1; i <= seatCapacity1/6; i++) {
			for(int j = 1; j < 7; j++){
			String s = j + "";
			Seat seat = new Seat((char)(65+i), s);
			seats.add(seat);
			}
		}
	}				
	public void copy(Flight f) {
		flightID = f.getFlightID();
		startTime = f.getStartTime();
		arrivalTime = f.getArrivalTime();
		startCity = f.getStartCity();
		arrivalCity = f.getArrivalCity();
		departureDate = f.getDepartureDate();
		price = f.getPrice();
		currentPassengers = f.getCurrentPassengers();
		seatCapacity = f.getSeatCapacity(); 
		flightStatus = f.getFlightStatus();
	}
	public static void updateFlightStatus(ArrayList<Flight> flights) {
		SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long currentTime = date.getTime();
		for (Flight f : flights) {
			if (f.getCurrentPassengers() == f.getSeatCapacity()) 
				f.setFlightStatus(Status.FULL);
			try{	
				String s = f.getStartTime();
				Date startTime = d.parse(s);
				if (startTime.getTime() - currentTime <= 7200000) 
				f.setFlightStatus(Status.TERMINATE);
			}catch(Exception e){}
		}
	}
	public void setFlightID(String flightID){
		this.flightID = flightID;
	}
	public String getFlightID(){
		return flightID;
	}
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}
	public String getStartTime(){
		return startTime;
	}
	public void setArrivalTime(String arrivalTime){
		this.arrivalTime = arrivalTime;
	}
	public String getArrivalTime(){
		return arrivalTime;
	}
	public void setStartCity(String startCity){
		this.startCity = startCity;
	}
	public String getStartCity(){
		return startCity;
	}
	public void setArrivalCity(String arrivalCity){
		this.arrivalCity = arrivalCity;
	}
	public String getArrivalCity(){
		return arrivalCity;
	}
	public void setDepartureDate(String departureDate){
		this.departureDate = departureDate;
	}
	public String getDepartureDate(){
		return departureDate;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public int getPrice(){
		return price;
	}
	public void setCurrentPassengers(int currentPassengers){
		this.currentPassengers = currentPassengers;
	}
	public int getCurrentPassengers(){
		return currentPassengers;
	}
	public void setSeatCapacity(int seatCapacity){
		this.seatCapacity = seatCapacity;
	}
	public int getSeatCapacity(){
		return seatCapacity;
	}
	public void setFlightStatus(Status s){
		flightStatus = s;
	}
	public Status getFlightStatus(){
		return flightStatus;
	}
	public void addPassenger(Passenger passenger){
		passengers.add(passenger);
	}
	public ArrayList<Passenger> getPassengers(){
		return passengers;
	}
	public ArrayList<Order> getOrders() {
		return orders;
	}
	public void showAvalableSeats() {
		int count = 0;
		for (Seat s : seats) {
			count ++;
			if(!s.getIfBooked()) System.out.print(s.getSeatNum()+" ");
			if (count % 6 == 0) System.out.println();
		}
	}
	public ArrayList<Seat> getSeats() {
		return seats;
	}
	public void addOrder(Order o) {
		orders.add(o);
	}
}