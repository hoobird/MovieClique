package Entity;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Report {

    static ArrayList<Report> reportList = new ArrayList<Report>();
    static int count = 0;

    private int reportID;
    private String dateOfCreation; // date of creation for report... use getDateOfCreation() to get string
    private int activeCustomers;
    private int totalSessions;
    private int totalHalls;
    private int totalMovies;
    private double bookingRevenue;
    private double boxOfficeGross;
    private double bookingProfit;
    private int totalFnBProducts;
    private double FnBRevenue;
    private double FnBRawCost;
    private double FnBProfits;
    private double CinemaProfits;

    public Report() throws IOException {
        // Check for the latest added ID since static cannot be serialized or
        // deserialized
        if (!reportList.isEmpty()) {
            for (Report rp : reportList) {
                if (rp.getReportID() > count) {
                    count = rp.getReportID();
                }
            }
        }
        this.reportID = ++count;
        LocalDateTime LDT = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateOfCreation = LDT.format(myFormatObj);
        this.activeCustomers = getActiveCustCount();
        this.totalSessions = getTotalSessions();
        this.totalHalls = getTotalHalls();
        this.totalMovies = getTotalMovies();
        this.bookingRevenue = getTotalBookingRevenue();
        this.boxOfficeGross = getBoxOfficeGross();
        this.bookingProfit = getBookingProfits();
        this.totalFnBProducts = getTotalFnBProducts();
        this.FnBRevenue = getTotalFnBRevenue();
        this.FnBRawCost = getFnBRawCost();
        this.FnBProfits = getFnBProfits();
        this.CinemaProfits = getTotalCinemaProfits();
        reportList.add(0, this);
        // Save to file after arraylist is added to
        Report.writeReportFile();
    }

    public String getDateOfCreation() { // use me to get version of report date creation date and time
        return dateOfCreation;
    }

    public int getActiveCustCount() {
        int count = 0;
        for (Account acc : Account.getAccountList()) {
            if (acc.getType() == 0 && acc.getActive() == true) {
                count += 1;
            }
        }
        return count;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public int getTotalSessions() {
        return Session.getSessionList().size();
    }

    public int getTotalHalls() {
        return Hall.getHallList().size();
    }

    public int getTotalMovies() {
        return Movie.getMovieList().size();
    }

    public double getTotalBookingRevenue() {
        double sum = 0;
        for (Booking bkings : Booking.getAllBookings()) {
            sum += bkings.getTotal();
        }
        return sum;
    }

    public double getBoxOfficeGross() {
        // share of the total revenue that goes to box office (40%)
        return getTotalBookingRevenue() / 100 * 40;
    }

    public double getBookingProfits() {
        return getTotalBookingRevenue() / 100 * 60;
    }

    public int getTotalFnBProducts() {
        return FnB.getFnbList().size();
    }

    public double getTotalFnBRevenue() {
        double sum = 0;
        for (Transaction trans : Transaction.getAllTransactions()) {
            sum += trans.getTotal();
        }
        return sum;
    }

    public double getFnBRawCost() {
        // about 30% of the price is raw material cost
        return getTotalFnBRevenue() / 100 * 30;
    }

    public double getFnBProfits() {
        return getTotalFnBRevenue() / 100 * 70;
    }

    public double getTotalCinemaProfits() {
        return getBookingProfits() + getFnBProfits();
    }

    @Override
    public String toString() {
        return "Report ID: " + reportID +
                "\nReport Creation Date: " + getDateOfCreation() +
                "\nNo of Active Customers: " + activeCustomers +
                "\n\nNo of Sessions:" + totalSessions +
                "\nNo of Halls: " + totalHalls +
                "\nNo of Movies=" + totalMovies +
                "\nTotal Booking Revenue: " + bookingRevenue +
                "\nTotal Box Office Gross:" + boxOfficeGross +
                "\nBooking Profits=" + bookingProfit +
                "\n\nTotal FnB Products:" + totalFnBProducts +
                "\nFnB Revenue: " + FnBRevenue +
                "\nFnB Raw Cost: " + FnBRawCost +
                "\nFnB Profits: " + FnBProfits +
                "\n\nGross Cinema Profits: " + CinemaProfits;
    }

    public static ArrayList<Report> getReportList() {
        return reportList;
    }

    public static void setReportList(ArrayList<Report> reportList) {
        Report.reportList = reportList;
    }

    public static void delete(int id) throws IOException {
        Report report = getReport(id);
        reportList.remove(report);
        Report.writeReportFile();
    }

    public static Report getReport(int id) {
        for (Report report : reportList) {
            if (report.getReportID() == id) {
                return report;
            }
        }
        return null;
    }

    public static void readReportFile() throws StreamReadException, DatabindException, IOException {
        File file = new File("reportList.json");
        System.out.println("reportFile found!");
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                .allowIfBaseType("Entity.Report")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        Report.setReportList(objectMapper.readValue(file, new TypeReference<ArrayList<Report>>() {
        }));
    }

    public static void writeReportFile() throws IOException {
        File file = new File("reportList.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder()
                .allowIfSubType("java.util.ArrayList")
                /* .allowIfBaseType("Entity.Report") */
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, reportList);
        // System.out.println(file.getAbsolutePath());
    }
}
