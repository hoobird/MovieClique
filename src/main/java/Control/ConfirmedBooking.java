package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

import Entity.Account;
import Entity.Profile;
import Entity.CustProfile;
import Entity.Booking;
import Entity.Seat;
import Entity.Session;

@WebServlet("/ConfirmedBookingServlet")
public class ConfirmedBooking extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession sess = req.getSession();
        Account acc = Account.getAccount((String) sess.getAttribute("sessAccUser"));
        if (acc.getType() == 1) {
            acc = Account.getAccount((String) req.getParameter("account"));
        }
        int sessionID = Integer.parseInt(String.valueOf(sess.getAttribute("currentBookingSessionID")));
        Session currentSession = Session.getSession(sessionID);
        String[] seatsChosenStr = (String[]) sess.getAttribute("seatsChosen");
        Seat[] seatsChosen = new Seat[seatsChosenStr.length];
        for (int i = 0; i < seatsChosenStr.length; i++) {
            int seatNumber = Integer.parseInt(seatsChosenStr[i]);
            for(Seat seat : currentSession.getSeatList()){
                if (seatNumber == seat.getSeatID()){
                    seatsChosen[i] = seat;
                }
            } 
        }

        int adultQty = Integer.parseInt(req.getParameter("adultQty"));
        int childQty = Integer.parseInt(req.getParameter("childQty"));
        int studentQty = Integer.parseInt(req.getParameter("studentQty"));
        int seniorQty = Integer.parseInt(req.getParameter("seniorQty"));
        int[] ticketsOfEachType = { adultQty, childQty, studentQty, seniorQty };
        boolean useLP = req.getParameter("useLP") != null;

        Booking thisBooking = new Booking(acc, currentSession, seatsChosen, ticketsOfEachType);
        thisBooking.setLoyaltyUsed(useLP);
        if(useLP){
            ((CustProfile) acc.getUserProfile()).useLoyaltyPoints();
        }
        System.out.println("Booking made.......");
        System.out.println(thisBooking.toString());


        Account loggedIn = Account.getAccount((String) sess.getAttribute("sessAccUser"));
        if (loggedIn.getType() == 0) {
            RequestDispatcher reqDispatcher = req.getRequestDispatcher("home.jsp");
            reqDispatcher.forward(req, res);
        } else if (loggedIn.getType() == 1) {
            RequestDispatcher reqDispatcher = req.getRequestDispatcher("staffBookings.jsp");
            reqDispatcher.forward(req, res);
        }
    }
}