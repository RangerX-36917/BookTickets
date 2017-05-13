import java.util.*;


public class Passenger {
	private int passengerID = 0;
	private String realName = " ";
	private String identityID = " ";
	private String password = " ";
	private boolean ifSignIn = false;

    public Passenger (int passengerID1, String realName1, String identityID1, String password1) {
        passengerID = passengerID1;
        realName = realName1;
        identityID = identityID1;
        password = password1;
        ifSignIn = false;
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

		System.out.printf("Congratulations, %s!\nYou have signed up as an passenger!",realName);
		System.out.printf("Your passengerID is %d.\n",passengerID);
            
			
    }
		

	public static void signIn(ArrayList<Passenger> passengers) {
		Scanner in = new Scanner(System.in);
		System.out.println("identityID/real name:");
		String input = in.next();
		boolean success = signInConfirm(input,passengers);
			
			while(!success){
				System.out.println("Sorry,wrong password or username, please check!");
				System.out.println("Enter 1 to check the information.\nEnter 2 to sign up as a new passenger.");
				int opt = in.nextInt();
				if (opt != 1 || opt != 2)
					System.out.println("No such command!");
				
				
                switch(opt) {
				case 1:
					System.out.println("identityID/real name:");
					input = in.next();
					success = signInConfirm(input,passengers);
					break;
					
				case 2:
					signUp(passengers);
					break;
					
				default: break;
				}
			}
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
		
		//query Flight
//		public static void queryFlight(){
//			Scanner in = new Scanner(System.in);
//			System.out.println("Welcome to query the flight:");
//			int opt = 0;
//			
//		}
		
	public static void queryOrder(Passenger passenger , ArrayList<Order> orders,ArrayList<Passenger> passengers) {
		if (passenger.getIfSignIn()) {
			int passengerID = passenger.getPassengerID();
			for(Order o: orders) {

				if(passengerID == o.getPassengerID()) {
					System.out.println("Your orders are as follows:");
					System.out.printf("%15s%15s%15s%15s%15s","PassengerID","FlightID","Seat","CreateDate","Status");
             											
//??how to show all the order						
//						System.out.println("%15s%15s%15s%15s%15s",o.passengerID,o.flight,o.seat,o.createDate,status);
				
//					}
//					else
//                      System.out.println("You haven't booked any ticket yet.");
					
				}	
			
		    	else {
			    	System.out.println("You haven't signed in yet.\nPlease sign in or sign up.\n(Enter 1 for sign in, 2 for sign up)");
				    Scanner in = new Scanner(System.in);
				    int opt = in.nextInt();
				    if(opt == 1)
					    signIn(passengers);
				    else
					    signUp(passengers);
		    	}
            } 
        }
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
}
		
		

	
	
	
	
