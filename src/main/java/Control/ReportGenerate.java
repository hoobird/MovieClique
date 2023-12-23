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

@WebServlet("/generateReportServlet")
public class ReportGenerate extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/generateReportServlet start");
        new Report();

        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("managerReports.jsp");
        reqDispatcher.forward(req, res);
    }

}