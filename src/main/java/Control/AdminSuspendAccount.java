package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import Entity.Account;

@WebServlet("/adminSuspendAccount")
public class AdminSuspendAccount extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/adminSuspendAccount start");
        String uname = req.getParameter("userSuspend");

        System.out.println("Customer Account Suspension in progress...");
        System.out.println("Username: " + uname);

        PrintWriter out = res.getWriter();

        if (Account.getAccount(uname) == null) {
            // username dont exist
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username does not exist!');");
            out.println("location='adminSuspendUsernamePage.jsp';");
            out.println("</script>");
        } else if (Account.getAccount(uname).getActive() == false) {
            // username is already suspended
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User is already suspended!');");
            out.println("location='adminSuspendUsernamePage.jsp';");
            out.println("</script>");
        } else {
            Account.getAccount(uname).setActive(false);
            Account.writeAccountFile();
            RequestDispatcher reqDispatcher;
            reqDispatcher = req.getRequestDispatcher("adminPage.jsp");
            reqDispatcher.forward(req, res);
        }

        // PrintWriter out = res.getWriter();
        // out.println("Username: " + i + "\nPassword: " + j);
        // out.println("Username: " + i + "\nPassword: " + j);

    }
}