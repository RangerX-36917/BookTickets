public class Seat {
    private String seatNum = "";
    private boolean booked = false;

    public Seat (char s1, String s2) {
        seatNum = s1 + s2;
        booked = false;
    }
    public String getSeatNum() {
        return seatNum;
    }
    public void setSeatNum(String s) {
        seatNum = s;
    }
    public boolean getIfBooked() {
        return booked;
    }
    public void setIfBooked(boolean b) {
        booked = b;
    }
}