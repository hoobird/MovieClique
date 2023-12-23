package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Entity.Booking;

@WebServlet("/getMyBookings")
public class BookingTableServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        ArrayList<Booking> allBookings = Booking.getAllBookings();
        StringBuilder outputTable = new StringBuilder();

        // Generate HTML table
        outputTable.append("<html><head><title>Booking Table</title></head><body>");
        outputTable.append("<table>");
        outputTable.append("<tr>");
        outputTable.append("<th>Booking ID</th>");
        outputTable.append("<th>Hall Number</th>");
        outputTable.append("<th>Movie</th>");
        outputTable.append("<th>Seat(s) Booked</th>");
        outputTable.append("<th>Adult Ticket(s)</th>");
        outputTable.append("<th>Child Ticket(s)</th>");
        outputTable.append("<th>Student Ticket(s)</th>");
        outputTable.append("<th>Senior Ticket(s)</th>");
        outputTable.append("<th>Total Amount Paid</th>");
        outputTable.append("<th>Loyalty Used</th>");
        outputTable.append("</tr>");

        for (Booking booking : allBookings) {
            if (booking.getAccount().getUsername().equals((String) session.getAttribute("sessAccUser"))) {
                outputTable.append("<tr>");
                outputTable.append("<td>" + booking.getBookingID() + "</td>");
                outputTable.append("<td>" + booking.getHall().getHallID() + "</td>");
                outputTable.append("<td>" + booking.getMovie().getName() + "</td>");
                outputTable.append("<td>" + Arrays.toString(booking.getSeatsIDBooked()) + "</td>");
                outputTable.append("<td>" + booking.getTicketsOfEachType()[0] + "</td>");
                outputTable.append("<td>" + booking.getTicketsOfEachType()[1] + "</td>");
                outputTable.append("<td>" + booking.getTicketsOfEachType()[2] + "</td>");
                outputTable.append("<td>" + booking.getTicketsOfEachType()[3] + "</td>");
                outputTable.append("<td>" + booking.getTotal() + "</td>");
                outputTable.append("<td>" + (booking.isLoyaltyUsed() ? "Yes" : "No") + "</td>");
                outputTable.append("</tr>");
            }
        }

        outputTable.append("</table>");
        outputTable.append("</body></html>");

        session.setAttribute("myBookingsTable", outputTable.toString());
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("myBookings.jsp");
        reqDispatcher.forward(req, res);

    }
}
