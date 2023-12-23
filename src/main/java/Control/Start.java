package Control;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Entity.Account;
import Entity.Booking;
import Entity.CustProfile;
import Entity.FnB;
import Entity.Hall;
import Entity.Movie;
import Entity.Profile;
import Entity.Report;
import Entity.Session;
import Entity.Transaction;

@WebServlet("/start")
public class Start extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        System.out.println("start is working");
        HttpSession session = req.getSession();
        // Not logged in header
        String head = "<div class=\"logo\"> <a href=\"\"><img src=\"Resources/logo.png\" alt=\"MovieClique Logo\"> </div><nav><ul><li><a href=\"home.jsp\">Home</a></li><li><a href=\"NowShowing.jsp\">Now Showing</a></li><li><a href=\"#\">Food & Beverage</a></li></ul></nav> <div class=\"profile\"><a href=\"home.jsp\">Login/Register</a></div>";
        session.setAttribute("isOUT", head);
        // Logged in header
        String head1 = "<div class=\"logo\"> <img src=\"Resources/logo.png\" alt=\"MovieClique Logo\"> </div><nav><ul><li><a href=\"home.jsp\">Home</a></li><li><a href=\"NowShowing.jsp\">Now Showing</a></li><li><a href=\"customerTransactions.jsp\">Food & Beverage</a></li></ul></nav> <div class=\"profile\"><div class=\"profile\"><a href=\"#\">Your Profile</a><div class=\"dropdown\"> <a href=\"profile.jsp\">View Profile</a> <a href=\"getMyBookings\">My Bookings</a> <a href=\"logout\">Logout</a> </div></div></div>";
        session.setAttribute("isIN", head1);

        session.setAttribute("topBannerHTML", head);
        session.setAttribute("sessAccUser", null);

        // Check if file exists and read them. If not, generate samples for some
        File accountFile = new File("accountList.json");
        File hallFile = new File("hallList.json");
        File movieFile = new File("movieList.json");
        File sessionFile = new File("sessionList.json");
        File bookingFile = new File("bookingList.json");
        File fnbFile = new File("fnbList.json");
        File transactionFile = new File("transactionList.json");
        File reportFile = new File("reportList.json");

        if (!accountFile.exists()) {
            System.out.println("Creating sample accounts...");
            createTestAccountProfile();
        } else {
            Account.readAccountFile();
        }
        if (!hallFile.exists()) {
            System.out.println("Creating sample halls...");
            // initialise method
        } else {
            Hall.readHallFile();
        }
        if (!movieFile.exists()) {
            System.out.println("Creating sample movies...");
            Movie.initialise();
        } else {
            Movie.readMovieFile();
        }
        if (!sessionFile.exists()) {
            System.out.println("Creating sample sessions...");
            // initialise method
        } else {
            Session.readSessionFile();
        }
        if (!bookingFile.exists()) {
            System.out.println("Creating sample bookings...");
            // initialise method
        } else {
            Booking.readBookingFile();
        }
        if (!fnbFile.exists()) {
            System.out.println("Creating sample fnb items...");
            // initialise method
        } else {
            FnB.readFnbFile();
        }
        if (!transactionFile.exists()) {
            System.out.println("Creating sample transaction items...");
            // initialise method
        } else {
            Transaction.readTransactionFile();
        }
        if (!reportFile.exists()) {
            System.out.println("Creating sample report items...");
            // initialise method
        } else {
            Report.readReportFile();
        }

        //////////////////////////////////////////////////////////////////
        // This part gets the server folder!!!!//////////////////////////
        // Get Server context
        ServletContext context = req.getServletContext();
        // Get the server folder path
        String serverFolderPath = context.getRealPath("/");
        System.out.println("ServerFolderPath: " + serverFolderPath);
        //////////////////////////////////////////////////////////////////
        // This part below is required to update local folder because server folder bye
        ////////////////////////////////////////////////////////////////// bye after u
        ////////////////////////////////////////////////////////////////// rebuild or
        ////////////////////////////////////////////////////////////////// shut down
        ////////////////////////////////////////////////////////////////// server
        // NEED TO CHANGE THIS AS PER COMPUTER
        session.setAttribute("ProjectFolder", "C:\\Users\\huber\\Desktop\\MovieClique\\MovieClique\\");

        RequestDispatcher reqDispatcher = req.getRequestDispatcher("home.jsp");
        reqDispatcher.forward(req, res);
    }

    private void createTestAccountProfile() throws StreamWriteException, DatabindException, IOException {
        String[] FIRST_NAMES = { "Ethan", "Chloe", "Daniel", "Sophia", "Benjamin", "Emily", "Lucas", "Olivia", "Samuel",
                "Ava", "Ryan", "Isabella", "Noah", "Mia", "Matthew", "Grace", "Nathan", "Lily", "Gabriel", "Emma",
                "Aaron", "Hannah", "David", "Amelia", "Joshua", "Victoria", "Isaac", "Natalie", "Caleb", "Sarah" };
        String[] LAST_NAMES = { "Tan", "Lim", "Lee", "Ng", "Tay", "Goh", "Ong", "Koh", "Chua", "Teo", "Low", "Wong",
                "Yeo", "Neo", "Toh", "Seow", "Ang", "Seah", "Loh", "Poh" };
        String[] GENDERS = { "Male", "Female" };
        String[] GENRES = { "Action", "Adventure", "Comedy", "Crime", "Drama", "Fantasy", "Historical", "Horror",
                "Romance" };
        Random random = new Random();

        // Making 10 Admin accounts
        for (int i = 0; i < 10; i++) {
            String fn;
            String ln;
            do {
                fn = getRandomValue(FIRST_NAMES);
                ln = getRandomValue(LAST_NAMES);
            } while (Account.getAccount(fn + "-" + ln) != null);
            Account account = new Account("admin" + i, "admin" + i, 3);
            Profile profile = new Profile(
                    fn,
                    ln,
                    getRandomValue(GENDERS),
                    random.nextInt(80) + 18,
                    getRandomPhoneNo(),
                    getRandomEmail(fn, ln));
            account.setUserProfile(profile);
            if (i == 0) {
                System.out.println(account.toString());
            }
        }

        // Making 15 Manager accounts
        for (int i = 0; i < 15; i++) {
            String fn;
            String ln;
            do {
                fn = getRandomValue(FIRST_NAMES);
                ln = getRandomValue(LAST_NAMES);
            } while (Account.getAccount(fn + "-" + ln) != null);
            Account account = new Account("man" + i, "man" + i, 2);
            Profile profile = new Profile(
                    fn,
                    ln,
                    getRandomValue(GENDERS),
                    random.nextInt(80) + 18,
                    getRandomPhoneNo(),
                    getRandomEmail(fn, ln));
            account.setUserProfile(profile);
            if (i == 0) {
                System.out.println(account.toString());
            }
        }

        // Making 20 Staff accounts
        for (int i = 0; i < 20; i++) {
            String fn;
            String ln;
            do {
                fn = getRandomValue(FIRST_NAMES);
                ln = getRandomValue(LAST_NAMES);
            } while (Account.getAccount(fn + "-" + ln) != null);
            Account account = new Account("staff" + i, "staff" + i, 1);
            Profile profile = new Profile(
                    fn,
                    ln,
                    getRandomValue(GENDERS),
                    random.nextInt(80) + 18,
                    getRandomPhoneNo(),
                    getRandomEmail(fn, ln));
            account.setUserProfile(profile);
            if (i == 0) {
                System.out.println(account.toString());
            }
        }

        // MAKING 100 Customer Accounts

        for (int i = 0; i < 100; i++) {
            String fn;
            String ln;
            do {
                fn = getRandomValue(FIRST_NAMES);
                ln = getRandomValue(LAST_NAMES);
            } while (Account.getAccount(fn + "-" + ln) != null);
            Account account = new Account(fn + "-" + ln, "password", 0);
            CustProfile custProfile = new CustProfile(
                    fn,
                    ln,
                    getRandomValue(GENDERS),
                    random.nextInt(80) + 18,
                    getRandomPhoneNo(),
                    getRandomEmail(fn, ln),
                    getRandomValue(GENRES));
            account.setUserProfile(custProfile);
            if (i == 0) {
                System.out.println(account.toString());
            }
        }

    }

    private String getRandomEmail(String fn, String ln) {
        String[] EMAIL_SUFFIXES = { "@gmail.com", "@yahoo.com", "@hotmail.com", "@outlook.com", "@example.com" };

        return fn + "-" + ln + getRandomValue(EMAIL_SUFFIXES);
    }

    private String getRandomValue(String[] values) {
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }

    private static String getRandomPhoneNo() {
        String[] PHONE_PREFIXES = { "8", "9" };
        Random random = new Random();
        String fullNo = PHONE_PREFIXES[random.nextInt(2)] + (random.nextInt(1000000, 9999999));
        return fullNo;
    }
}
