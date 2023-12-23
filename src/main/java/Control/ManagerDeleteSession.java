package Control;

import java.io.IOException;
import java.util.ArrayList;

import Entity.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteSession")
public class ManagerDeleteSession extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/deleteSession start");
        // Delete code here
        int delSess = Integer.parseInt(req.getParameter("delSess"));
        ArrayList<Session> sessList = Session.getSessionList();
        for (Session sess : sessList) {
            if (sess.getSessionID() == delSess) {
                sessList.remove(sess);
                break;
            }
        }
        Session.writeSessionFile();
        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("managerSessions.jsp");
        reqDispatcher.forward(req, res);
    }
}