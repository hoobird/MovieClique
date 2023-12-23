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
// import java.io.PrintWriter;
import Entity.CustProfile;
import Entity.Profile;

@WebServlet("/addAccount")
public class AddAccount extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/addAccount start");
        String uname = req.getParameter("uname");
        String pword = req.getParameter("pword");
        int type = Integer.parseInt(req.getParameter("type"));
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String gender = req.getParameter("gender");
        int age = Integer.valueOf(req.getParameter("age"));
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String genre = req.getParameter("genre");
        System.out.println("Customer Account Creation in progress...");
        System.out.println("Username: " + uname + "\nPassword: " + pword);

        String[] typeList = { "customer", "staff", "manager", "admin" };

        PrintWriter out = res.getWriter();

        if (Account.getAccount(uname) != null) {
            // duplicate username
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username has already been used!');");
            out.println("location='register.jsp';");
            out.println("</script>");
        } else {
            Account acc = new Account(uname, pword, type);
            CustProfile custProf;
            RequestDispatcher reqDispatcher;

            // customer register
            custProf = new CustProfile(fname, lname, gender, age, phone, email, genre);
            acc.setUserProfile(custProf);
            System.out.println("Customer profile created");
            Account.writeAccountFile();
            reqDispatcher = req.getRequestDispatcher("home.jsp");
            reqDispatcher.forward(req, res);

        }

        // PrintWriter out = res.getWriter();
        // out.println("Username: " + i + "\nPassword: " + j);
        // out.println("Username: " + i + "\nPassword: " + j);

    }
}