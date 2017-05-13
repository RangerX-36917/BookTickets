
import java.util.*;

//import Flight.Status;

public class Admin {
    String userName = "";
    String password = "";
    boolean ifSignIn = false;
	enum Status {UNPUBLISHED, AVAILABLE, FULL, TERMINATE};

    public static void signUp(ArrayList<Admin> array) {
        System.out.println("please input your username");
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
        
        System.out.printf("Please input your password:");
        String s2 = in.next();
        System.out.printf("Please input your password again:");
        String s3 = in.next();
        if (s2.equals(s3)) {          
            Admin a = new Admin();
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
                    System.out.printf("Welcome back, %s!",a.userName);
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
                case 1: creatFlight(flights);
                        break;
                case 2: updateFlight(flights);
                        break;
                case 3: deleteFlight(flights);
                        break;
                case 4: superQuery(flights, orders);
                        break;
                case 5: userManagement(admins);
                        break;
                case 6: publishFlight(flights);
                        break;
                default: break;
            }
            showMenu();
            opt = in.nextInt();
        }while (opt != 0);
            
        
        

   }
    
    public static void showMenu() {
        for (int i = 0; i < 50; i++) System.out.printf("_");
        System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s%s%s",
                          "0:Exit\n",
                          "1:creat flight\n",
                          "2:update flight\n",
                          "3:delete flight\n",
                          "4:super query\n",
                          "5:user management\n",
                          "6:query flight\n");
    }   



    public static void creatFlight(ArrayList<Flight> flights) {
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
        System.out.println("Please input flight status:");
        String flightStatus = in.next();
        int seatNum = 0;
        Flight f = new Flight(flightID, startTime, arrivalTime, startCity, arrivalCity, departureDate, price, seatNum, seatCapacity);
        flights.add(f);
    }
/*
//unfinished updateFlight
    public static void updateFlight(ArrayList<Flight> flights) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please input FlightID:");
        int flightID = in.next();

        for (Flight a : flights) {
            if (a.getflightID() == flightID) {
	        	System.out.printf(
		    	  "\nPlease choose the information you wish to update:\n%s%s%s%s%s%s%s",
		    	  "0:FlightID\n",
                  "1:start time\n",
                  "2:arrival time\n",
                  "3:start city\n",
                  "4:arrival city\n",
                  "5:departure date\n",
		    	  "6:price\n",
                  "7:seat capacity\n");
                int opt = in.nextInt();
                switch(opt) {
	    	        case 0: System.out.println("Please input the new FlightID:n");
	    	                int f = in.nextInt();
	    	                a.setFlightID(f);
	    	                break;
                    case 1: System.out.println("Please input the new start time:");
                            String f = in.next();
                            a.setStartTime(f);
                            break;
                    case 2: System.out.println("Please input the new arrival time:");
                            String f = in.next();
                            a.setArrivalTime(f);
                            break;
                    case 3: System.out.println("Please input the new start city:");
                            String f = in.next();
                            a.setStartCity(f);
                            break;
                    case 4: System.out.println("Please input the new arrival city:");
                            String f = in.next();
                            a.setArrivalCity(f);
                            break;
                    case 5: System.out.println("Please input the new departure date");
                            String f = in.next();
                            a.setDepartureDate(f);
                            break;
                    case 6: System.out.println("Please input the new price");
                            int f = in.nextInt();
                            a.setPrice(f);
                            break;  
                    case 7: System.out.println("Please input the new seat capacity");
                            int f = in.nextInt();
                            a.setSeatCapacity(f);
                            break;                             
                    default : break;
                }  
            }
        }
    }
//unfinished                        
    public static void deleteFlight(ArrayList<Flight> flights) {

    }
//unfinished                        
    public static void superQuery(ArrayList<Flight> flights, ArrayList<Order> orders) {

    }
//unfinished
    public static void userManagement(ArrayList<Admin> admins) {

    }
    */
    public static void publishFlight(ArrayList<Flight> flights) {
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
                    success = true;
                }
                
            }
        if (success) 
            System.out.println("You have successfully published the flight"+id);
        else System.out.println("Sorry, so such flight or it has already been published!");
        }
    }
                       
}
