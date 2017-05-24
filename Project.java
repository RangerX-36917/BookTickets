import java.util.*;

public class Project {
    public static void main(String[] args) {
        ArrayList<Admin> Admins = new ArrayList<Admin>();
        ArrayList<Passenger> Passengers = new ArrayList<Passenger>();
        ArrayList<Flight> Flights = new ArrayList<Flight>();
        ArrayList<Order> Orders = new ArrayList<Order>();
        
        Flight f1 = new Flight("CZ121", "2017-08-04 12:12:12", "2017-08-05 05:12:12", "Beijing", "Shenzhen", "2017-08-05", 12345, 0, 36);
        Flight f2 = new Flight("CZ122", "2017-05-23 01:12:12", "2017-05-24 09:12:12", "Beijing", "New York", "2017-05-23", 12345, 0, 126);
        Flights.add(f1);
        Flights.add(f2);

        Admin a = new Admin("c", "c");
        Admins.add(a);

        Passenger p = new Passenger(123, "Chris", "1", "c");
        Passengers.add(p);

        Scanner in = new Scanner(System.in);
        int opt = 0;
      
        boolean commandGet = false;
        showMenu();
        while (!commandGet) {
            opt = in.nextInt();
            if (opt > 5) 
                System.out.println("no such command!");
            else commandGet = true;

        }
        
        do 
        {
            switch (opt) {
                case 0: continue;
                case 1: Admin.signUp(Admins);
                        break;
                case 2: Passenger.signUp(Passengers);
                        break;
                case 3: Admin.signIn(Admins,Passengers,Flights,Orders);
                   //     Admin.action(Admins, Passengers, Flights, Orders);
                        break;
                case 4: Passenger.signIn(Passengers, Flights, Orders);
                //        Passenger.action(Passengers, Flights, Orders);
                        break;
                case 5: Passenger.queryFlight(Flights);
                        break;
           
                default: break;
            }
            Timeout();
            showMenu();
            opt = in.nextInt();
        }while (opt !=0);
            
    }
    private static void showMenu() {
        for (int i = 0; i < 50; i++) System.out.printf("_");
        System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s%s",
                          "0:Exit\n",
                          "1:Sign up as Administrators\n",
                          "2:Sign up as Passengers\n",
                          "3:Sign in as Administrators\n",
                          "4:Sign in as Passengers\n", 
                          "5:Query Flight without sign in\n");
                          
    }
    public static void Timeout() {
        try
					{
						Thread.currentThread().sleep(800);//毫秒 
					}
					catch(Exception e){}
    }
}