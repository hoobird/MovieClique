package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import Entity.Account;
import java.io.PrintWriter;
import Entity.CustProfile;
import Entity.Profile;

@WebServlet("/readAccount")
public class ReadAccount extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/readAccount start");
        String username = req.getParameter("username");
        
        System.out.println("Finding user: " + username);
        Account currAcc = Account.getAccount(username);

        // if account dont exist
        if (currAcc == null){
            // failed to find user try again.
            PrintWriter out = res.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Failed to find user... please try again');");
            out.println("location='login.jsp';");
            out.println("</script>");
        }else{
            HttpSession session = req.getSession();
            session.setAttribute("readUser", currAcc);
        }

       
    }
}