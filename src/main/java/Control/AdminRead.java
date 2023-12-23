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

import Entity.Account;
// import java.io.PrintWriter;
import Entity.CustProfile;
import Entity.Profile;

@WebServlet("/adminReadServlet")
public class AdminRead extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/adminReadServlet start");
        HttpSession session = req.getSession();
        String usernameToRead = req.getParameter("userRead");

        Account readAcc = Account.getAccount(usernameToRead);
        if (readAcc == null) {
            // failed to find user
            PrintWriter out = res.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('User does not exist');");
            out.println("location='adminPage.jsp';");
            out.println("</script>");
        } else {

            session.setAttribute("accToBeRead", readAcc);

            RequestDispatcher reqDispatcher;
            reqDispatcher = req.getRequestDispatcher("adminReadPage.jsp");
            reqDispatcher.forward(req, res);
        }
    }
}