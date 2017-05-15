import java.util.*;

public class Project {
    public static void main(String[] args) {
        ArrayList<Admin> Admins = new ArrayList<Admin>();
        ArrayList<Passenger> Passengers = new ArrayList<Passenger>();
        ArrayList<Flight> Flights = new ArrayList<Flight>();
        ArrayList<Order> Orders = new ArrayList<Order>();
        

        Scanner in = new Scanner(System.in);
        int opt = 0;
      
        boolean commandGet = false;
        showMenu();
        while (!commandGet) {
            opt = in.nextInt();
            if (opt > 4) 
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
                case 4: Passenger.signIn(Passengers);
                //        Passenger.action(Passengers, Flights, Orders);
                        break;
           
                default: break;
            }
            showMenu();
            opt = in.nextInt();
        }while (opt !=0);
            
    }
    public static void showMenu() {
        for (int i = 0; i < 50; i++) System.out.printf("_");
        System.out.printf("\nWelcome to book ticket!\n%s%s%s%s%s",
                          "0:Exit\n",
                          "1:Sign up as Administrators\n",
                          "2:Sign up as Passengers\n",
                          "3:Sign in as Administrators\n",
                          "4:Sign in as Passengers\n"); 
                          
    }
}
