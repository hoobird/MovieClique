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

import Entity.Report;

@WebServlet("/deleteReportServlet")
public class ReportDelete extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/deleteReportServlet start");
        int reportID = Integer.parseInt(req.getParameter("reportID"));
        Report.delete(reportID);

        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("managerReports.jsp");
        reqDispatcher.forward(req, res);
    }

}