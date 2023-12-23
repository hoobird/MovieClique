package Entity;

public class Seat {

    int seatID;
    boolean booked;

    // For Jackson
    public Seat() {
        super();
    }
    
    public Seat(int no) {
        this.seatID = no;
        booked = false;
    }

    public int getSeatID() {
        return seatID;
    }

    public void setSeatID(int seatNo) {
        this.seatID = seatNo;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Seat [seatNo=" + seatID + ", booked=" + booked + "]";
    }
}
