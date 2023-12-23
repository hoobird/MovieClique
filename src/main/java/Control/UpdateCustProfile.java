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
// import java.io.PrintWriter;
import Entity.CustProfile;

@WebServlet("/updateAccount")
public class UpdateCustProfile extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/updateAccount start");

        String pword = req.getParameter("pword");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String gender = req.getParameter("gender");
        int age = Integer.valueOf(req.getParameter("age"));
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String genre = req.getParameter("genre");
        HttpSession session = req.getSession();
        
        Account acc = Account.getAccount((String)session.getAttribute("sessAccUser"));
        CustProfile custProf = (CustProfile)acc.getUserProfile();
        
        acc.setPassword(pword);
        custProf.setFirstName(fname);
        custProf.setLastName(lname);
        custProf.setGender(gender);
        custProf.setAge(age);
        custProf.setPhoneNo(phone);
        custProf.setEmail(email);
        custProf.setGenre(genre);
        System.out.println("Cust Account / Profile Updated! ");
        System.out.println(acc.toString());
        System.out.println(custProf.toString());
        

        // PrintWriter out = res.getWriter();
        // out.println("Username: " + i + "\nPassword: " + j);
        // out.println("Username: " + i + "\nPassword: " + j); 
        Account.writeAccountFile();
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("home.jsp");
        reqDispatcher.forward(req, res);
    }
}
