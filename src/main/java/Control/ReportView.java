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

@WebServlet("/viewReportServlet")
public class ReportView extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println("/viewReportServlet start");
        int reportID = Integer.parseInt(req.getParameter("reportID"));
        String reportDetails = Report.getReport(reportID).toString();
        session.setAttribute("reportDetails", reportDetails);
        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("managerViewReport.jsp");
        reqDispatcher.forward(req, res);
    }

}