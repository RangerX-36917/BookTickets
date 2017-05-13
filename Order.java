import java.util.*;
public class Order {
	Passenger passenger = new Passenger(0,"", "","");
	String seat;
	Flight flight = new Flight("", "", "", "", "", "", 0, 0, 0);
	Date creatDate = new Date();
	enum orderStatus {PAID, UNPAID, CANCEL};
	orderStatus status = orderStatus.UNPAID;

	public Order (Passenger passenger1, String seat, Flight flight1, Date creatDate1)
	{
		;
		seat = seat;
		flight.copy(flight1);
		creatDate = creatDate;
		status = orderStatus.UNPAID;
		passenger.copy(passenger1);
	}
	public void setPassenger(Passenger passenger1){
		passenger = passenger1;
	}
	public int getPassengerID(){
		return passenger.getPassengerID();
	}
	public void setSeat(String seat){
		seat = seat;
	}
	public String getSeat(){
		return seat;
	}
	public void setFlight(Flight flight){
		flight=flight;
	}
	public Flight getFlight(){
		return flight;
	}
	public void setDate(Date creatDate){
		creatDate=creatDate;
	}
	public Date getDate(){
		return creatDate;
	}
	public void setStatus(String status){
		status=status;
	}
	public orderStatus getStatus(){
		return status;
	}
	public void displayOrder(){
		System.out.printf("PassengerID: %d\n",passenger.getPassengerID());
		System.out.printf("Seat: %s\n",seat);
		System.out.printf("FlightInfo: %s\n",flight.getFlightID());
		System.out.printf("CreatDate: %s\n",creatDate);
		System.out.printf("Status: %s\n",status);
	}

}
