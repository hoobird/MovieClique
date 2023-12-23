package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Session {
    static ArrayList<Session> sessionList = new ArrayList<>();
    static int count = 0;

    public static String[] daysInWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday" };

    public static String[] getDaysInWeek() {
        return daysInWeek;
    }

    public static void setDaysInWeek(String[] daysInWeek) {
        Session.daysInWeek = daysInWeek;
    }

    public static String[] timeSlots = { "0800 - 1000", "1100 - 1300", "1400 - 1600", "1700 - 1900", "2000 - 2200" };

    public static String[] getTimeSlots() {
        return timeSlots;
    }

    public static void setTimeSlots(String[] timeSlots) {
        Session.timeSlots = timeSlots;
    }

    int sessionID;
    // Contains a hall obj, movie obj, create seats using movie object date/time
    // with substrings
    Hall hall;
    Movie mov;
    // We assume that every hall contains 5 rows of 10 seats
    Seat seatList[] = new Seat[50];
    // Days of the week mon to sun as 1-7
    int day = 0;

    // Slot 1-5 with timings displayed on jsp side
    int slot = 0;

    // For jackson
    public Session() {
        super();
    }

    public Session(Hall hall, Movie mov, int day, int slot)
            throws StreamWriteException, DatabindException, IOException {
        // Check for the latest added ID since static cannot be serialized or
        // deserialized
        if (!sessionList.isEmpty()) {
            for (Session sess : sessionList) {
                if (sess.getSessionID() > count) {
                    count = sess.getSessionID();
                }
            }
        }
        this.sessionID = ++count;
        this.hall = hall;
        this.mov = mov;
        // Create array of Seat objects using seatCount from Hall object
        int seats = this.hall.getSeatCount();
        for (int i = 0; i < seats; i++) { // seats 1 to 50
            seatList[i] = new Seat(i + 1);
        }
        this.day = day;
        this.slot = slot;
        sessionList.add(this);
        // Try saving sessionList everytime it is added to
        writeSessionFile();
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Session.count = count;
    }

    public static ArrayList<Session> getSessionList() {
        Collections.sort(sessionList, Session.SessionComparator);
        return sessionList;
    }

    public static void setSessionList(ArrayList<Session> sessionList) {
        Session.sessionList = sessionList;
    }

    public static Session getSession(int sessionID) {
        for (Session sess : sessionList) {
            if (sess.getSessionID() == sessionID) {
                return sess;
            }
        }
        return null;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Movie getMov() {
        return mov;
    }

    public void setMov(Movie mov) {
        this.mov = mov;
    }

    public Seat[] getSeatList() {
        return seatList;
    }

    public void setSeatList(Seat[] seatList) {
        this.seatList = seatList;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "MovieSession [hall=" + hall + ", mov=" + mov + ", seatList=" + Arrays.toString(seatList) + ", slot="
                + slot + "]";
    }

    public static Comparator<Session> SessionComparator = new Comparator<Session>() {
        public int compare(Session session1, Session session2) {
            // Compare by day
            int result = Integer.compare(session1.getDay(), session2.getDay());
            if (result != 0) {
                return result; // If day is different, return the result
            } else {
                // If day is the same, compare by slot
                return Integer.compare(session1.getSlot(), session2.getSlot());
            }
        }
    };

    public static void readSessionFile() throws StreamReadException, DatabindException, IOException {
        File file = new File("sessionList.json");
        System.out.println("sessionFile found!");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                // .allowIfSubType("java.util.Arrays")
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.Hall")
                .allowIfBaseType("Entity.Seat")
                .allowIfBaseType("[LEntity.Seat;")
                .allowIfBaseType("Entity.Movie")
                .allowIfBaseType("Entity.Session")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        Session.setSessionList(objectMapper.readValue(file, new TypeReference<ArrayList<Session>>() {
        }));
    }

    public static void writeSessionFile() throws StreamWriteException, DatabindException, IOException {
        File file = new File("sessionList.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                /* .allowIfBaseType("Entity.Session") */
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, sessionList);
        // System.out.println(file.getAbsolutePath());
    }
}