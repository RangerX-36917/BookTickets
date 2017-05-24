
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

//import Flight.Status;

public class Admin {
    String userName = "";
    String password = "";
    boolean ifSignIn = false;
	enum Status {UNPUBLISHED, AVAILABLE, FULL, TERMINATE};
    public Admin (String name, String password1) {
        userName = name;
        password = password1;
        ifSignIn = false;
    }

    public static void signUp(ArrayList<Admin> array) {
        System.out.println("Please enter your username");
        Scanner in = new Scanner(System.in);
        String s1 = in.next();
        boolean nameUsed = false;
        while (!nameUsed) {
            nameUsed = true;
            for (Admin a : array) {
                if (a.userName.equals(s1))
                {
                    nameUsed = false;
                    break;
                } 

            }
            if (!nameUsed) {
                System.out.printf("This username has been used, please change!\nPlease input your username:\n");
                s1 = in.next();
            }
            
        }
        
        System.out.printf("Please enter your password:\n");
        String s2 = in.next();
        System.out.printf("Please enter your password again:\n");
        String s3 = in.next();
        if (s2.equals(s3)) {          
            Admin a = new Admin(" "," ");
            a.password = s2;
            a.userName = s1;
            array.add(a);
            System.out.println("Congratulations, "+a.userName+"!You have signed up as an administrator!");
        }
        else {
            System.out.println("Sorry, the two passwords are not identical");
        }  
    }

    public static void signIn(ArrayList<Admin> admins,ArrayList<Passenger> passengers,
                             ArrayList<Flight> flights,ArrayList<Order> orders) {
        Scanner in = new Scanner(System.in);
        System.out.print("username:");
        String s1 = in.next();
        boolean success = false;
        for (Admin a : admins) {
            if (a.userName.equals(s1)) {
                System.out.print("password:");
                String s2 = in.next();
                if (a.password.equals(s2)) {
                    System.out.printf("Welcome back, %s!\n",a.userName);
                    a.ifSignIn = true;
                    success = true;
                    Admin.action(a,admins,passengers, flights, orders);//sign in successfully -> admin action
                }else success = false;    
            }
        }
        if (!success)
            System.out.println("Sorry, wrong password or username, please check!");           
    }

    public static void action(Admin administrator,ArrayList<Admin> admins, ArrayList<Passenger> passengers,ArrayList<Flight> flights,ArrayList<Order> orders) {
        Scanner in = new Scanner(System.in);
        int opt = 0;
        boolean commandGet = false;

        while (!commandGet) {
            Project.Timeout();
            showMenu();
            opt = in.nextInt();
            if (opt > 6) 
                System.out.println("no such command!");
            else commandGet = true;

        }
        
        do 
        {
            switch (opt) {
                case 0: continue;
                case 1: Project.Timeout();
                        createFlight(flights);
                        break;
                case 2: Project.Timeout();
                        updateFlight(flights);
                        break;
                case 3: Project.Timeout();
                        deleteFlight(flights,orders);
                        break;
                case 4: Project.Timeout();
                        superQuery(flights, orders);
                        break; 
                case 5: Project.Timeout();
                        userManagement(admins, administrator);
                        break;
                case 6: Project.Timeout();
                        publishFlight(flights);
                        break;
                default: break;
            }
            showMenu();
            opt = in.nextInt();
        }while (opt != 0);
   }
    
    private static void showMenu() {
        for (int i = 0; i < 50; i++) System.out.printf("_");
        System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s%s%s",
                          "0:Exit\n",
                          "1:creat flight\n",
                          "2:update flight\n",
                          "3:delete flight\n",
                          "4:super query\n",
                          "5:user management\n",
                          "6:publish flights\n");
    }   



    public static void createFlight(ArrayList<Flight> flights) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input FlightID:");
        String flightID = in.next();
        System.out.println("Please input start time:");
        String startTime = in.next();
        System.out.println("Please input arrival time:");
        String arrivalTime = in.next();
        System.out.println("Please input start city:");
        String startCity = in.next();
        System.out.println("Please input arrival city:");
        String arrivalCity = in.next();
        System.out.println("Please input departure date:");
        String departureDate = in.next();
        System.out.println("Please input price:");
        int price = in.nextInt();
        System.out.println("Please input seat capacity:");
        int seatCapacity = in.nextInt();
        int seatNum = 0;
        Flight f = new Flight(flightID, startTime, arrivalTime, startCity, arrivalCity, departureDate, price, seatNum, seatCapacity);
        flights.add(f);
    }

    public static void updateFlight(ArrayList<Flight> flights) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input FlightID:");
        String flightID = in.next();
        boolean success = false;
        boolean canDo = false;
        for (Flight a : flights) {
            if (a.getFlightID().equals(flightID)) {
                success = true;
                Flight.updateFlightStatus(flights);
                int opt = 0;
                switch (a.getFlightStatus()) {
                    case UNPUBLISHED: System.out.printf("\nPlease choose the information you wish to update:\n%s%s%s%s%s%s%s%s",
                                                        "0:Exit",
                                                        "1:FlightID\n",
                                                        "2:start time\n",
                                                        "3:arrival time\n",
                                                        "4:start city\n",
                                                        "5:arrival city\n",
                                                        "6:departure date\n",
                                                        "7:price\n",
                                                        "8:seat capacity\n");
                                      opt = in.nextInt();
                                      switch(opt) {
                                      case 0: break;
                                      case 1: {
                                              System.out.println("Please input the new FlightID:n");
                                              String f = in.next();
                                              a.setFlightID(f);
                                              }
                                             break;
                                      case 2: {
                                              System.out.println("Please input the new start time:");
                                              String f = in.next();
                                              a.setStartTime(f);
                                              }       
                                             break;
                                      case 3: {
                                              System.out.println("Please input the new arrival time:");
                                              String f = in.next();
                                              a.setArrivalTime(f);
                                              }
                                              break;
                                      case 4:  {
                                               System.out.println("Please input the new start city:");
                                               String f = in.next();
                                               a.setStartCity(f);
                                               }  
                                               break;
                                      case 5: {
                                              System.out.println("Please input the new arrival city:");
                                              String f = in.next();
                                              a.setArrivalCity(f);
                                              }
                                              break;
                                      case 6: {
                                              System.out.println("Please input the new departure date");
                                              String f = in.next();
                                              a.setDepartureDate(f);
                                              }
                                              break;
                                      case 7: {
                                              System.out.println("Please input the new price");
                                              int f = in.nextInt();
                                              a.setPrice(f);
                                              }
                                              break;  
                                      case 8: {
                                              System.out.println("Please input the new seat capacity");
                                              int f = in.nextInt();
                                              a.setSeatCapacity(f);
                                              }      
                                              break;                             
                                      default : System.out.println("No such command");
                                                break;
									  }   
                                	  break;
                    case AVAILABLE: System.out.printf(
                                                        "\nPlease choose the information you wish to update:\n%s%s",
                                                        "0:Exit",
                                                        "1:price",
                                                        "2:seat capacity");
									opt = in.nextInt();
                                    int f = 0;
									switch (opt) {
										case 0 : break;
										case 1 : System.out.println("Please input the new price");
												f = in.nextInt();
												a.setPrice(f);
												break;
										case 2: System.out.println("Please input the new seat capacity");
												f = in.nextInt();
												a.setSeatCapacity(f);
												break;
										default : System.out.println("No such command!");
									}
                    case TERMINATE: System.out.println("The flight is terminated, you can't edit its information");
                                                  break;
                    default: break;
                }
            }
        }
        if (!success) System.out.println("No such flight, please check!");
    }

                     
    public static void deleteFlight(ArrayList<Flight> flights, ArrayList<Order> orders) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the FlightID of the flight you wish to delete:");
        String ID = in.next();
        boolean success = false;
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightID().equals(ID)) {         
                //delete flight in passenger's order list
                for (Passenger p : flights.get(i).getPassengers()) {
                    for (Order o : p.orders) {
                        if (o.getFlight().getFlightID().equals(ID)) {
                            p.orders.remove(o);
                        }
                    }
                }
                //delete flight in overall order list
                for (Order o : orders) {
                    if (o.getFlight().getFlightID().equals(ID)) {
                        orders.remove(o);
                    }
                }
                //delete flight in overall flight list
                flights.remove(flights.get(i));
                i--;
            }
        }

    }
//unfinished                        
    private static void superQuery(ArrayList<Flight> flights, ArrayList<Order> orders) {
        Scanner in = new Scanner(System.in);
			System.out.println("Welcome to super query :");
			System.out.printf("%s%s%s%s%s",
                            "0:Exit\n",
                            "1:query by flight ID\n",
                            "2:query by start city, arrival city and date\n",
                            "3:query orders of a flight\n",
                            "4:query orders\n");
			int opt = in.nextInt();
			switch(opt){
                case 0: break;
                case 1: queryByID(flights);
                        break;
                case 2: queryByInfo(flights);
                        break;
                case 3: queryOrdersOfAFlight(flights);
                        break;
     //           case 4: queryOrders(orders); 
     //                   break;
                default:System.out.println("No such command!");	
			}	
    }

    private static void userManagement(ArrayList<Admin> admins, Admin administrator) {
        System.out.printf("1. Create a new administrator\n2. Modify my account\n");
        Scanner in = new Scanner(System.in);
        int opt = in.nextInt();
        switch (opt) {
            case 1: signUp(admins);
                    break;
            case 2: updateInfo(administrator, admins);
                    break;
            default: System.out.println("No such command!");
                     break;
        }
    }
  
    private static void publishFlight(ArrayList<Flight> flights) {
        if (Flight.unpublishedNum == 0) 
            System.out.println("There is no unpublished flights!");
        if (Flight.unpublishedNum != 0) {
            System.out.println("Unpublished flights:");
            System.out.println("FlightID");
            for (Flight a : flights) {
                if (a.getFlightStatus() == Flight.Status.UNPUBLISHED) 
                    System.out.println(a.getFlightID());
            }
            System.out.println("Please enter the FlightID of the flight you wish to publish");
            Scanner in = new Scanner(System.in);
            String id = in.next();
            boolean success = false;
            for (Flight a : flights) {
                if (a.getFlightID().equals(id)) {
                    a.setFlightStatus(Flight.Status.AVAILABLE);
                    Flight.unpublishedNum--;
                    success = true;
                }
                
            }
        if (success) 
            System.out.println("You have successfully published the flight"+id);
        else System.out.println("Sorry, so such flight or it has already been published!");
        }
    }
    private static void updateInfo(Admin a, ArrayList<Admin> admins) {
        Scanner in = new Scanner(System.in);
        System.out.printf("1.Change username\n2.Change password\n");
        int opt = in.nextInt();
        switch (opt) {
            case 1: System.out.println("Please enter new username:");
                    boolean success = true;
                    String s = in.next();
                    for (Admin adm : admins) {
                        if (s.equals(adm.userName)) {
                            System.out.println("Sorry, this username has been used");
                            success = false;
                            break;
                        }
                   
                    }
                    if (success) a.userName = s;
                    break;
            case 2: System.out.println("Please enter your current password:");
                    String p = in.next();
                    if (p.equals(a.password)) {
                        System.out.println("Please enter your new password:");
                        p = in.next();
                        System.out.println("Please enter your new password again:");
                        String p1 = in.next();
                        if (p1.equals(p)) {          
                            a.password = p1;
                            System.out.println("Congratulations, "+a.userName+"!You have changed your password successfully!");
                        }
                        else {
                            System.out.println("Sorry, the two passwords are not identical");
                        }  
                    }
                    break;
            default: System.out.println("No such command!");
                     break;
        }
    }
        
    private static void queryByID(ArrayList<Flight> flights) {
        Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight ID:");
		String ID = in.next();
		int s = -1;
		boolean success = false;
        System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6s%-6s\n","FlightID","Start Time","Arrival Time","Start City","ArrivalCity","DepartureDate","Price","Seat Capacity");
        for (Flight f: flights){
			if (ID.equals(f.getFlightID())){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
            }else{
                s = f.getFlightID().indexOf(ID);
                if(s != -1) {
                    success = true;
                    System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
                }
            }
        }
        if (!success) System.out.println("No such flight");                       
    }


    private static void queryByInfo(ArrayList<Flight> flights) {
        Scanner in = new Scanner(System.in);
		System.out.println("Please enter Flight Infomation:");
		String info = in.nextLine();
		boolean success = false;
		int opt = 0;
        System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6s%-6s\n","FlightID","Start Time","Arrival Time","Start City","ArrivalCity","DepartureDate","Price","Seat Capacity");
		for (Flight f: flights){
			if (info.equals(f.getStartCity())){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			}
			if (info.equals(f.getArrivalCity())){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			}
		
			if (info.equals(f.getDepartureDate())){
				success = true;
				System.out.printf("%-10s%-25s%-25s%-12s%-12s%-15s%-6d%-6d\n",f.getFlightID(),f.getStartTime(),f.getArrivalTime(),f.getStartCity(),f.getArrivalCity(),f.getDepartureDate(),f.getPrice(),f.getSeatCapacity());
			}
		}
		if (!success) System.out.println("No such flight!");
    }

    private static void queryOrdersOfAFlight(ArrayList<Flight> flights) {
        System.out.println("Please enter the FlightID of the flight you wish to query:");
        Scanner in = new Scanner(System.in);
        String ID = in.next();
        String paid = " ";
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Flight f : flights) {
            if (ID.equals(f.getFlightID())) {
                System.out.printf("%-15s%-9s%-9s%-9s\n","Passenger ID", "Seat", "Flight", "Create Date");
                for (Order o : f.getOrders()) {
                    if (o.getStatus() == Order.orderStatus.PAID) paid = "PAID"; else paid = "UNPAID";
                    System.out.printf("%-15s%-9s%-9s%-9s\n", o.getPassengerID(), o.getSeat(), o.getFlight().getFlightID(), d.format(o.getDate()));
                }
            }
        }
    }
    private static void queryOrders(ArrayList<Order> orders) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String paid = " ";
        for (Order o : orders) {
            if (o.getStatus() == Order.orderStatus.PAID) paid = "PAID"; else paid = "UNPAID";
                    System.out.printf("%7s%7s%7s%7s", o.getPassengerID(), o.getSeat(), o.getFlight(), d.format(o.getDate()));
        }
    }

}