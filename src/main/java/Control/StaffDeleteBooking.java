package Control;

import java.io.IOException;
import java.util.ArrayList;

import Entity.Profile;
import Entity.Seat;
import Entity.Booking;
import Entity.CustProfile;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteBooking")
public class StaffDeleteBooking extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/deleteTransaction start");
        // Delete code here
        int delBook = Integer.parseInt(req.getParameter("delBook"));
        ArrayList<Booking> bookingList = Booking.getAllBookings();
        // Refund loyalty points & unbook seats
        if (Booking.getBooking(delBook) != null) {
            Booking delBooking = Booking.getBooking(delBook);
            Profile refundProfile = delBooking.getAccount().getUserProfile();
            CustProfile refProf = (CustProfile) refundProfile;
            refProf.setLoyaltyPoints(refProf.getLoyaltyPoints() + delBooking.getSeatsBooked().length);
            for (Seat seat : delBooking.getSeatsBooked()) {
                seat.setBooked(false);
            }
            bookingList.remove(delBooking);
        }

        Booking.writeBookingFile();
        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("staffBookings.jsp");
        reqDispatcher.forward(req, res);
    }
}