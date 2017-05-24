import java.util.*;
import java.util.GregorianCalendar;

public class Passenger {
	public ArrayList<Order> orders = new ArrayList<Order>();
	Flight flight = new Flight("", "", "", "", "", "", 0, 0, 0);
	Date creatDate = new Date();
	//Passenger passenger = new Passenger(0,"","","");
//	Order order = new Order(passenger,"",flight,creatDate);
	
	int passengerID = 0;
	String realName = " ";
	String identityID = " ";
	String password = " ";
	boolean ifSignIn = false;
	String seat;
	
	public Passenger(int passengerID,String realName, String identityID,String password){
		this.passengerID = passengerID;
		this.realName = realName;
		this.identityID = identityID;
		this.password = password;
		ifSignIn = false;
	}

	public int getPassengerID() {
		return passengerID;
	}
	
	public String getIdentityID() {
		return identityID;
	}
	public String getRealName() {
		return realName;
	}
	public String getPassword() {
		return password;
	}
	public boolean getIfSignIn(){
		return ifSignIn;
	}
	public void setIfSignIn(boolean b) {
	ifSignIn = b;
	}
	public void copy(Passenger p) {
		passengerID = p.getPassengerID();
		realName = p.getRealName();
		identityID = p.getIdentityID();
		password = p.getPassword();
		ifSignIn = p.getIfSignIn();
	}
	
	public static void signUp(ArrayList<Passenger> passengers) {
		Scanner in = new Scanner(System.in);
		System.out.println("please input your identityID:");
		String identityID = in.next();

		System.out.println("please input your realname:");
		String realName = in.next();

		System.out.println("Please input your password:");
		String password = in.next();

		System.out.println("Please check your password:");
		String passwordCheck = in.next();

		while (!password.equals(passwordCheck)) {
			System.out.println("Sorry, the two passwords are not identical.\nPlease check your password again:\n");
			passwordCheck = in.next();
		}
		
		int passengerID = (int)(Math.random()*90000 + 100000);
		//confirm passengerID is unique
		for(Passenger p : passengers) {
			if (passengerID == p.getPassengerID() )
				passengerID = (int)(Math.random()*90000 + 100000);
			}

		Passenger p1 = new Passenger(passengerID, realName, identityID, password);
        passengers.add(p1);

		System.out.printf("Congratulations, %s!\nYou have signed up as an passenger! ",realName,passengerID);
		System.out.printf("Your passengerID is %d.\n",passengerID);
            	
    }

	public static void signIn(ArrayList<Passenger> passengers,ArrayList<Flight> flights, ArrayList<Order> orders) {
	    Scanner in = new Scanner(System.in);
        System.out.print("Passenger ID:");
        int s1 = in.nextInt();
        boolean success = false;
        for (Passenger a : passengers) {
			System.out.println(a.getPassengerID());
            if (a.getPassengerID() == s1) {
                System.out.print("password:");
                String s2 = in.next();
                if (a.getPassword().equals(s2)) {
                    System.out.printf("Welcome back, %s!\n",a.getRealName());
                    a.setIfSignIn(true);
                    success = true;
					
					
					
                    action(flights, a, orders);
                }else success = false;    
            }
        }
        if (!success)
            System.out.println("Sorry, wrong password or Passenger ID, please check!");           
    }
    
		
	//signin confirm
	public static boolean signInConfirm(String input,ArrayList<Passenger> passengers) {
		Scanner in = new Scanner(System.in);
		boolean success = false;
		for (Passenger p : passengers) {
			if(input.equals(p.identityID)) {
				System.out.printf("password:");
				String password = in.next();
				if(password.equals(p.password)) {
					 System.out.printf("Welcome back, %s!",input);
					 p.setIfSignIn(true); 
					 success = true; 
				}
            }
        
			if(input.equals(p.realName)){
				System.out.println("password:");
				String password = in.next();
				if(password.equals(p.password)){
					 System.out.printf("Welcome back, %s!",input);
					 p.setIfSignIn(true);
					 success = true; 
				}
			}
	
        }
		return success;
    }
			
	public static void queryOrder(Passenger passenger ) {

			int passengerID = passenger.getPassengerID();
		
			System.out.println("Your orders are as follows:");
			System.out.printf("%-15s%-10s%-10s%-7s%-18s\n","PassengerID-","FlightID","Seat","CreateDate","Status");
            int count = 0;
			for(Order o: passenger.orders) {										
						System.out.printf("%-15s%-10s%-10s%-30s%-18s\n",passenger.passengerID,o.getFlight().getFlightID(),o.getSeat(),o.getDate(),o.getStatus());
						count++;				
				}
				if (count == 0) System.out.println("You haven't booked any ticket yet.");
					
	
		}
		
 
       
	//	query Flight
	public static void queryFlight(ArrayList<Flight> flights,Passenger passenger,ArrayList<Order> orders){
			Scanner in = new Scanner(System.in);
			System.out.println("Welcome to query flight information:");
			System.out.println("1:query by flight ID\n2:query by start city, arrival city and date\n3:exit");
			int opt = in.nextInt();
			switch(opt){
			case 1: queryByID(flights, passenger, orders);
					break;
			case 2: queryByInfo(flights, passenger, orders);
					break;
			case 3: break;
			default:System.out.println("No such command!");	
			}			
	}
	public static void queryFlight(ArrayList<Flight> flights){
			Scanner in = new Scanner(System.in);
			System.out.println("Welcome to query flight information:");
			System.out.println("1:query by flight ID\n2:query by start city, arrival city and date\n3:exit");
			int opt = in.nextInt();
			switch(opt){
			case 1: queryByID(flights);
					break;
			case 2: queryByInfo(flights);
					break;
			case 3: break;
			default:System.out.println("No such command!");	
			}			
	}
	
	
	private static void queryByID(ArrayList<Flight> flights, Passenger passenger, ArrayList<Order> orders){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight ID:");
		String ID = in.next();
		int s = -1;
		boolean success = false;
		int opt = -1;
		System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6s%-6s\n","FlightID","Start Time","Arrival Time","Start City","ArrivalCity","DepartureDate","Price","Seat Capacity");
		for (Flight f: flights){
			if (ID.equals(f.getFlightID()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
				
				System.out.println("1:reserve the flight\n2:exit");
				opt = in.nextInt();
				switch (opt){
				case 1: reserveFlight(flights,passenger,orders);
					    break;
				case 2: break;
				default: System.out.println("No such command!");
				}
			}else{ //implicit search
				s = f.getFlightID().indexOf(ID);			
				if (s!=-1 && f.getFlightStatus() == Flight.Status.AVAILABLE){
					success = true;
					System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
					
					System.out.println("1:reserve the flight\n2:exit");
					opt = in.nextInt();
					switch (opt){
					case 1: reserveFlight(flights,passenger,orders);
						    break;
					case 2: break;
					default: System.out.println("No such command!");
					}


				}
			}
		}
		if (!success) System.out.println("No such flight!");	
	}
	private static void queryByID(ArrayList<Flight> flights){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight ID:");
		String ID = in.next();
		int s = -1;
		boolean success = false;
		int opt = -1;
		System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6s%-6s\n","FlightID","Start Time","Arrival Time","Start City","ArrivalCity","DepartureDate","Price","Seat Capacity");
		for (Flight f: flights){
			if (ID.equals(f.getFlightID()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			}
		}
	}
	
	private static void queryByInfo(ArrayList<Flight> flights, Passenger passenger, ArrayList<Order> orders){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight Infomation:");
		String info = in.nextLine();
		boolean success = false;
		int opt = 0;
		System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6s%-6s\n","FlightID","Start Time","Arrival Time","Start City","ArrivalCity","DepartureDate","Price","Seat Capacity");
		for (Flight f: flights){
			if (info.equals(f.getStartCity()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
				System.out.println("1:reserve the flight\n2:exit");
				opt = in.nextInt();
				switch (opt){
					case 1: reserveFlight(flights,passenger,orders);
						    break;
					case 2: break;
					default: System.out.println("No such command!");
					}
			}
			if (info.equals(f.getArrivalCity()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());

				System.out.println("1:reserve the flight\n2:exit");
				opt = in.nextInt();
				switch (opt){
					case 1: reserveFlight(flights,passenger,orders);
						    break;
					case 2: break;
					default: System.out.println("No such command!");
					}	
			}
		
			if (info.equals(f.getDepartureDate()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());

				System.out.println("1:reserve the flight\n2:exit");
				opt = in.nextInt();
				switch (opt){
					case 1: reserveFlight(flights,passenger,orders);
						    break;
					case 2: break;
					default: System.out.println("No such command!");
					}
	
			}
		}
		
			if (!success) System.out.println("No such flight!");
		
	}
	private static void queryByInfo(ArrayList<Flight> flights){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight Infomation:");
		String info = in.nextLine();
		boolean success = false;
		int opt = 0;
		System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6s%-6s\n","FlightID","Start Time","Arrival Time","Start City","ArrivalCity","DepartureDate","Price","Seat Capacity");
		for (Flight f: flights){
			if (info.equals(f.getStartCity()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			
			}
			if (info.equals(f.getArrivalCity()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());	
			}
		
			if (info.equals(f.getDepartureDate()) && f.getFlightStatus() == Flight.Status.AVAILABLE){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			}
		}
		
			if (!success) System.out.println("No such flight!");
		
	}


	
	private static void showMenu() {
	    for (int i = 0; i < 50; i++) System.out.printf("_");
	   	System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s",
	                 			"0:Exit\n",
	    			  			"1:query the flight\n",
	    			  			"2:reserve the flight\n",
	    			  			"3:unsubscribe the flight\n",
								"4:query my orders");  
	} 	  
	public static void action(ArrayList<Flight> flights, Passenger passenger, ArrayList<Order> orders) {      	  
	    int opt = 0;
		Scanner in = new Scanner(System.in);
		Project.Timeout();
		Project.Timeout();
		showMenu();
		opt = in.nextInt();
		do {
			switch (opt) {
	                case 0: continue;
	      			case 1:	queryFlight(flights,passenger,orders);
	    					break;
	       			case 2: reserveFlight(flights,passenger,orders);
	       					break;
	       			case 3: unsubscribeFlight(flights,passenger,orders);
	       					break;
					case 4: queryOrder(passenger);
							break;
			default : System.out.println("No such command");
		}
		Project.Timeout();    
	    showMenu();
	    opt = in.nextInt();
	    }while (opt != 0);
	}

	public static void reserveFlight(ArrayList<Flight> flights,Passenger passenger,ArrayList<Order> orders){
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to reserve flights!");
		boolean flag = true;
		boolean success = false;
		Flight.updateFlightStatus(flights);
		System.out.printf("%-8s%-25s%-25s%-10s%-10s%-15s%-6s%-6s\n","FlightID","Start Time","Arrival Time","Start City","ArrivalCity","DepartureDate","Price","Seat Capacity");
		//show available flights
		for (Flight f : flights) 
			if(f.getFlightStatus() == Flight.Status.AVAILABLE) System.out.printf("%-8s%-25s%-25s%-10s%-10s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
		//reserve flights
		System.out.println("Please enter the flight ID of the flight you wish to reserve:");
		String ID = in.next();
		int opt = 0;
		for (Flight f : flights){
			if (ID.equals(f.getFlightID())){
				if (f.getFlightStatus() == Flight.Status.AVAILABLE) {
					System.out.println("Please check flight information");
					System.out.printf("%-8s%-25s%-25s%-10s%-10s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
					System.out.println("1: Yes\n2: No");
					opt = in.nextInt();
					switch(opt){
					case 1:
						reserveFlightConfirm(ID,passenger,orders,f);
						success = true;
						break;
					case 2:
						break;
					default:
						System.out.println("No such command!");
						break;
					}
				}else {
					System.out.println("Sorry, the flight has been terminated!");
					success = true;
				}
			}
		
			if (!success) System.out.println("Sorry!No such flight!\n");
		}
		
		
		
	}
	
	//reserve confirm
	public static void reserveFlightConfirm(String ID, Passenger passenger,ArrayList<Order> orders,Flight f){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your password:");
		String password = in.next();
		//check password
		if (password.equals(passenger.getPassword())){
			boolean success = false;
			f.showAvalableSeats();
			System.out.println("Please enter the seat number you wish to reserve():");
			String seatNum = in.next();
			for (Seat s : f.getSeats()) { //reserve seat
				if (seatNum.equals(s.getSeatNum())) {
					success = true;
					s.setIfBooked(true);
					f.setCurrentPassengers(f.getCurrentPassengers() + 1);
					Date createDate = new Date();
					Order o = new Order(passenger,seatNum,f, createDate);//create order
					orders.add(o);
					passenger.orders.add(o);
					f.addOrder(o);
					f.addPassenger(passenger);
					System.out.println("Pay now? (Enter Y for yes, N for no) ");
					String opt = in.next();
					switch (opt) {
						case "Y" : o.setStatus(Order.orderStatus.PAID);	
								   break;	
						case "N" : break;
						default : System.out.println("No such command");
					}
				if (!success) 
					System.out.println("Sorry, seat number is incorrect or it's already booked");
			}
			}
		}else{
			System.out.println("Sorry!Wrong password!");
		}
	}

	
	public static void unsubscribeFlight(ArrayList<Flight> flights,Passenger passenger,ArrayList<Order> orders){
		Scanner in = new Scanner(System.in);
		System.out.println("Welcome to unsubscribe the flight!");
		boolean success = false;
		do{
			System.out.println("Please enter the flight ID of the flight you wish to unsubscribe:");
			String ID = in.next();
			int opt = 0;
			Flight f = new Flight("", "", "", "", "", "", 0, 0, 0);
			for (int i = 0; i < passenger.orders.size(); i++){
				Order o = passenger.orders.get(i);
				f = o.getFlight();
				if (ID.equals(f.getFlightID())){
					System.out.println("Do you want to unsubscribe this flight?");
					
					System.out.printf("%-8s%-25s%-25s%-10s%-10s%-15s\n",
							f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate());
					System.out.println("1: Yes\n2: No");
					opt = in.nextInt();
					switch(opt){
					case 1: unsubscribeFlightConfirm(f,passenger,o);
							success = true;
							i--;
							break;
					case 2: success = true;
							break;
					default: System.out.println("No such command!");
						     break;
					}
				}
			}
			if (!success) {
				System.out.println("Sorry!No such flight!");
				success = true;
			}
				
		//	Project.Timeout();
		
		
		}while(!success);
		
	}
	
	public static void unsubscribeFlightConfirm(Flight f,Passenger passenger,Order order){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter your password:");
		String password = in.next();
		if (password.equals(passenger.getPassword())){
			f.setCurrentPassengers(f.getCurrentPassengers()-1);
			for (Seat s : f.getSeats()) {
				if (order.seat.equals(s.getSeatNum())) 
					s.setIfBooked(false);
			}
			passenger.orders.remove(order);
			order.status = Order.orderStatus.CANCEL;
		}
		else{
			System.out.println("Sorry!Wrong password!");
		}
	
	}
	
}




	



		
		

	
	
	
	
			


