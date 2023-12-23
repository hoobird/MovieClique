package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap; // import the HashMap class
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

import Control.MapFnbKeyDeserializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction {

    static ArrayList<Transaction> allTransactions = new ArrayList<Transaction>();
    static int counter = 0;
    private int transactionID;  // cannot change transact id
    private Account account;   // cannot change account
    private float total;    // cannot set because total is calculated
    @JsonDeserialize(keyUsing = MapFnbKeyDeserializer.class)
    //@JsonRawValue
    HashMap<FnB, Integer> orderQty = new HashMap<FnB, Integer>();

    public Transaction(Account account, HashMap<FnB, Integer> orderQty) throws StreamWriteException, DatabindException, IOException {
        // Check for the latest added ID since static cannot be serialized or deserialized
        if (!allTransactions.isEmpty()) {
            for (Transaction trans : allTransactions) {
                if (trans.getTransactionID() > counter) {
                    counter = trans.getTransactionID();
                }
            }
        }
        this.transactionID = ++counter;
        this.account = account;
        this.orderQty = orderQty;
        this.total = getTotal();   // total points will be dynamic because order can be edited
        allTransactions.add(this);
        // Update Stock on creation
        for (Map.Entry<FnB, Integer> me : (this.orderQty).entrySet()) {
            FnB fnb = me.getKey();
            int remStock = me.getValue();
            fnb.setStock(fnb.getStock() - remStock);
        }
        // Since a new transactions adds to FnB stock...
        FnB.writeFnbFile();
        // Try transactionList everytime it is added to
        writeTransactionFile();
    }

    // For jackson
    public Transaction() {
        super();
    }

    public static ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public static void setAllTransactions(ArrayList<Transaction> allTransactions) {
        Transaction.allTransactions = allTransactions;
    }

    public static int getCounter() {
        return counter;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public Account getAccount() {
        return account;
    }

    public float getTotal() {
        float total = 0;
        for (FnB fnb : orderQty.keySet()) {
            total += fnb.getPrice() * orderQty.get(fnb);
        }
        return total;
    }

    public HashMap<FnB, Integer> getOrderQty() {
        return orderQty;
    }
    
    public boolean checkIfOrdered(FnB item){
        return (orderQty.containsKey(item));
    }

    public boolean editQty(FnB item , int newQty){
        if (checkIfOrdered(item) == false){
            return false;
        }else{
            orderQty.put(item,newQty);
            return true;
        }
    }

    public boolean removeItem(FnB item){
        if (checkIfOrdered(item) == false){
            return false;
        }else{
            orderQty.remove(item);
            return true;
        }
    }

    public static void readTransactionFile() throws StreamReadException, DatabindException, IOException {
        File file = new File("transactionList.json");
        System.out.println("transactionFile found!");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.HashMap")
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.Account")
                .allowIfBaseType("Entity.Profile")
                .allowIfSubType("Entity.Profile")
                .allowIfBaseType("Entity.FnB")
                .allowIfBaseType("Entity.Transaction")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        Transaction.setAllTransactions(objectMapper.readValue(file, new TypeReference<ArrayList<Transaction>>() {
        }));
    }

    public static void writeTransactionFile() throws StreamWriteException, DatabindException, IOException {
        File file = new File("transactionList.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.HashMap")
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.FnB")
                /* .allowIfBaseType("Entity.Transaction") */
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, allTransactions);
        // System.out.println(file.getAbsolutePath());
    }

}
