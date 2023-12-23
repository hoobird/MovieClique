package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.Hall;

@WebServlet("/updateHall")
public class ManagerUpdateHall extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/updateHall start");
        HttpSession session = req.getSession();
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("managerHalls.jsp");

        if (req.getParameter("saveHall") != null && !req.getParameter("saveHall").equals("")) {
            // Directed by save button (request to save edit)
            PrintWriter out = res.getWriter();
            ArrayList<Hall> hallList = Hall.getHallList();
            int newHallID = Integer.parseInt(req.getParameter("saveHall"));
            Hall currHall = (Hall) session.getAttribute("toEdit");
            int hallDups = 0;
            for (Hall hall : hallList) {
                // If new hall ID is duplicate
                if (!hallList.isEmpty() && hall.getHallID() == newHallID) {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('The hall you are trying to use already exists!');");
                    out.println("location='managerHalls.jsp';");
                    out.println("</script>");
                    hallDups++;
                    break;
                }
            }
            // If no duplicates found
            if (hallDups == 0) {
                currHall.setHallID(newHallID);
                // Always sort hallList by ID in ascending order when list is updated
                hallList.sort((hall1, hall2) -> Integer.compare(hall1.getHallID(), hall2.getHallID()));
                Hall.writeHallFile();
                session.removeAttribute("toEdit");
                reqDispatcher.forward(req, res);
            }
        } else if (req.getParameter("editHall") != null) {
            // Directed by edit button (request to edit)
            int hallID = Integer.parseInt(req.getParameter("editHall"));
            session.setAttribute("toEdit", Hall.getHall(hallID));
            reqDispatcher.forward(req, res);
        } else {
            session.removeAttribute("toEdit");
            reqDispatcher.forward(req, res);
        }
    }
}
