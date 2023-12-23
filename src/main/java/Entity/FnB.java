package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

public class FnB {
    static ArrayList<FnB> fnbList = new ArrayList<>();

    static int count = 0;
    // Will act as "coupon" or some other form of unique ID
    int fnbID;
    String name;
    float price;
    int stock;

    // Constructor for physical purchase
    public FnB(String name, float price, int stock) throws StreamWriteException, DatabindException, IOException {
        // Check for the latest added ID since static cannot be serialized or deserialized
        if (!fnbList.isEmpty()) {
            for (FnB fnb : fnbList) {
                if (fnb.getFnbID() > count) {
                    count = fnb.getFnbID();
                }
            }
        }
        this.fnbID = ++count;
        this.name = name;
        this.price = price;
        this.stock = stock;
        fnbList.add(this);
        // Try saving fnbList every time it is added to
        writeFnbFile();
    }

    //For jackson
    public FnB() {
        super();
    }

    public static ArrayList<FnB> getFnbList() {
        return fnbList;
    }
    public static void setFnbList(ArrayList<FnB> fnbList) {
        FnB.fnbList = fnbList;
    }
    public static int getCount() {
        return count;
    }
    public static void setCount(int count) {
        FnB.count = count;
    }
    public int getFnbID() {
        return fnbID;
    }
    public void setFnbID(int fnbID) {
        this.fnbID = fnbID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public static FnB getFnb(int fnbID) {
        for (FnB fnb : fnbList) {
            if (fnb.getFnbID() == fnbID) {
                return fnb;
            }
        }
        return null;
    }
    
    public static void readFnbFile() throws StreamReadException, DatabindException, IOException {
        File file = new File("fnbList.json");
        System.out.println("fnbFile found!");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.FnB")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        FnB.setFnbList(objectMapper.readValue(file, new TypeReference<ArrayList<FnB>>() {
        }));
    }

    public static void writeFnbFile() throws StreamWriteException, DatabindException, IOException {
        File file = new File("fnbList.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                /*.allowIfBaseType("Entity.FnB")*/
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, fnbList);
        //System.out.println(file.getAbsolutePath());
    }

    @Override
    //@JsonRawValue
    public String toString() {
        /*ObjectMapper objectMapper = new ObjectMapper();
        try {
            String result = "[ \"Entity.FnB\"" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this) + " ]";
            System.out.println(result);
            return (result);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;*/
        return ("Entity.FnB, {" +
        "fnbID : " + fnbID + ", " +
        "name : " + name + ", " +
        "price : " + price + ", " +
        "stock : " + stock + ", " +
        '}');
    }
    
}
