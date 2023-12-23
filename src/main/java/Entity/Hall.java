package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

public class Hall {
    static ArrayList<Hall> hallList = new ArrayList<>();
    // We assume that every hall contains 5 rows of 10 seats
    int seatCount = 50;
    //static int hallCount = 0;
    int hallID;

    /* For if we want id assignment when no id is given
    public Hall() {
        this.hallID = hallCount;
        hallCount++;
    }
    */

    public Hall(int id) throws IOException {
        this.hallID = id;
        //hallCount++;

        hallList.add(this);
        // Always sort hallList by ID in ascending order when list is added to
        hallList.sort((hall1, hall2) -> Integer.compare(hall1.getHallID(), hall2.getHallID()));
		System.out.println("Hall " + this.hallID + " has been added");
		System.out.println("Hall List size: " + hallList.size() );
        // Try saving hallList every time it is added to
        writeHallFile();
    }

    // For jackson
    public Hall() {
        super();
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCounts) {
        this.seatCount = seatCounts;
    }

    /*
    public static int getHallCount() {
        return hallCount;
    }

    public static void setHallCount(int hallCount) {
        Hall.hallCount = hallCount;
    }
    */

    public int getHallID() {
        return hallID;
    }

    public void setHallID(int hallID) {
        this.hallID = hallID;
    }

    public static ArrayList<Hall> getHallList() {
        return hallList;
    }

    public static void setHallList(ArrayList<Hall> hallList) {
        Hall.hallList = hallList;
    }

	public static Hall getHall(int hID) {
		for (Hall hall : hallList){
			if (hall.getHallID() == hID) {
				return hall;
			} 
		}
		return null;
	}
    
    public static void readHallFile() throws StreamReadException, DatabindException, IOException {
        File file = new File("hallList.json");
        System.out.println("hallFile found!");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.Hall")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        Hall.setHallList(objectMapper.readValue(file, new TypeReference<ArrayList<Hall>>() {
        }));
    }

    public static void writeHallFile() throws IOException {
        File file = new File("hallList.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                /*.allowIfBaseType("Entity.Hall")*/
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, hallList);
        //System.out.println(file.getAbsolutePath());
    }

    @Override
    public String toString() {
        return "Hall [seatCount=" + seatCount + ", hallID=" + hallID + "]";
    }
}
