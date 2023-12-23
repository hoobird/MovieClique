package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap; // import the HashMap class

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {
    // purpose of this class is to easy retrival of booking information from
    // respective classes
    // and allow us to keep track of how much revenue is the cinema earning from
    // MOVIES BOOKING

    static ArrayList<Booking> allBookings = new ArrayList<Booking>();
    static HashMap<String, Float> pricing = new HashMap<String, Float>() {
        {
            put("Adult", 15f);
            put("Child", 5f);
            put("Student", 10f);
            put("Senior", 10f);
        }
    };
    static int counter = 0;
    private boolean loyaltyUsed = false; // use 10 pts get 10% off at a time.
    private int bookingID; // CANNOT CHANGE
    private Account account; // account cannot change
    private Session sessionBooked; // from session itself we can get the MOVIE and HALL
    private Seat[] seatsBooked;   // get 
    private float total; // cannot set because total is calculated
    private int[] ticketsOfEachType = new int[4]; // to keep track how many ticket of which ticket category (ONLY NEED
                                                  // TO KEEP TRACK HERE FOR PAYMENT PURPOSES)
    // ticket of each means for eg [1,3,4,5]

    // For jackson
    public Booking() {
        super();
    }

    public Booking(Account account, Session sessionBooked, Seat[] seatsBooked, int[] ticketsOfEachType) throws StreamWriteException, DatabindException, IOException {
        this.account = account;
        // Check for the latest added ID since static cannot be serialized or deserialized
        if (!allBookings.isEmpty()) {
            for (Booking book : allBookings) {
                if (book.getBookingID() > counter) {
                    counter = book.getBookingID();
                }
            }
        }
        this.bookingID = ++counter;
        this.sessionBooked = sessionBooked;
        this.seatsBooked = seatsBooked;
        for (Seat seat : this.seatsBooked) { // must set the seat to booked
            seat.setBooked(true);
        }
        this.ticketsOfEachType = ticketsOfEachType;
        this.total = getTotal();
        CustProfile currProf = ((CustProfile)this.account.userProfile);
        currProf.setLoyaltyPoints(currProf.getLoyaltyPoints()+seatsBooked.length);
        allBookings.add(this);
        // Since a new booking adds to customer loyalty points...
        Account.writeAccountFile();
        // Try saving bookingList everytime it is added to
        writeBookingFile();
    }

    public boolean isLoyaltyUsed() {
        return loyaltyUsed;
    }

    public void setLoyaltyUsed(boolean loyaltyUsed) {
        this.loyaltyUsed = loyaltyUsed;
    }

    public static HashMap<String, Float> getPricing() {
        return pricing;
    }

    public static void setPricing(float[] newPricing) {
        // give new pricing in a list to update current pricing....
        // order is ADULT, CHILD, STUDENT, SENIOR
        pricing.put("Adult", newPricing[0]);
        pricing.put("Child", newPricing[1]);
        pricing.put("Student", newPricing[2]);
        pricing.put("Senior", newPricing[3]);
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Booking.counter = counter;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Session getSessionBooked() {
        return sessionBooked;
    }

    public void setSessionBooked(Session sessionBooked) {
        this.sessionBooked = sessionBooked;
    }

    public Hall getHall() {
        return sessionBooked.getHall();
    }

    public Movie getMovie() {
        return sessionBooked.getMov();
    }

    public Seat[] getSeatsBooked() {
        return seatsBooked;
    }

    public void setSeatsBooked(Seat[] newSeats) {
        this.seatsBooked = newSeats;
    }

    public int[] getSeatsIDBooked() {
        ArrayList<Integer> seats = new ArrayList<Integer>();
        for (Seat seat : getSeatsBooked()) {
            seats.add(seat.getSeatID());
        }
        return seats.stream().mapToInt(Integer::intValue).toArray();
    }

    public float getTotal() {
        float total = 0;
        total += ticketsOfEachType[0] * pricing.get("Adult");
        total += ticketsOfEachType[1] * pricing.get("Child");
        total += ticketsOfEachType[2] * pricing.get("Student");
        total += ticketsOfEachType[3] * pricing.get("Senior");
        if (loyaltyUsed) {
            total *= 0.9;
        }
        return total;
    }

    public int[] getTicketsOfEachType() {
        return ticketsOfEachType;
    }

    public static ArrayList<Booking> getAllBookings() {
        return allBookings;
    }

    public static void setAllBookings(ArrayList<Booking> allBookings) {
        Booking.allBookings = allBookings;
    }

    public static void setPricing(HashMap<String, Float> pricing) {
        Booking.pricing = pricing;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setTicketsOfEachType(int[] ticketsOfEachType) {
        this.ticketsOfEachType = ticketsOfEachType;
    }

    public static Booking getBooking(int bookingID) {
        for (Booking book : allBookings) {
            if (book.getBookingID() == bookingID) {
                return book;
            }
        }
        return null;
    }
    
    public static void readBookingFile() throws StreamReadException, DatabindException, IOException {
        File file = new File("bookingList.json");
        System.out.println("sessionFile found!");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.Account")
                .allowIfBaseType("Entity.Profile")
                .allowIfSubType("Entity.Profile")
                .allowIfBaseType("Entity.Hall")
                .allowIfBaseType("Entity.Seat")
                .allowIfBaseType("[LEntity.Seat;")
                .allowIfBaseType("Entity.Movie")
                .allowIfBaseType("Entity.Session")
                .allowIfBaseType("Entity.Booking")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        Booking.setAllBookings(objectMapper.readValue(file, new TypeReference<ArrayList<Booking>>() {
        }));
    }

    public static void writeBookingFile() throws StreamWriteException, DatabindException, IOException {
        File file = new File("bookingList.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                /*.allowIfBaseType("Entity.Booking")*/
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, allBookings);
        //System.out.println(file.getAbsolutePath());
    }

    @Override
    public String toString() {
        return ("Booking ID: " + getBookingID() +
                "\nHall Number:" + getHall().getHallID() +
                "\nMovie:" + getMovie().getName() +
                "\nSeat(s) Booked:" + Arrays.toString(getSeatsIDBooked()) + // this one prints out the list of seat id
                                                                            // booked
                "\n\tAdult Ticket(s): " + ticketsOfEachType[0] + " x $" + pricing.get("Adult") +
                "\n\tChild Ticket(s): " + ticketsOfEachType[1] + " x $" + pricing.get("Child") +
                "\n\tStudent Ticket(s): " + ticketsOfEachType[2] + " x $" + pricing.get("Student") +
                "\n\tSenior Ticket(s): " + ticketsOfEachType[3] + " x $" + pricing.get("Senior") +
                "\nTotal Amount Paid =" + total+
                "\nLoyalty Used: " + (loyaltyUsed?"Yes":"No"));
    }
}
