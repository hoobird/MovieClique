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
import Entity.Profile;

@WebServlet("/adminUpdateAccount")
public class AdminUpdateProfile extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/updateAccount start");
        HttpSession session = req.getSession();
        boolean active = req.getParameter("active").equals("active");
        String pword = req.getParameter("pword");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        int type;
        int loyaltyPoints =0;

        if (req.getParameter("type") == null){
            type = 0;
            loyaltyPoints = Integer.parseInt(req.getParameter("loyaltyPoints"));
        }else{
            type = Integer.valueOf(req.getParameter("type"));
        }
        String gender = req.getParameter("gender");
        int age = Integer.valueOf(req.getParameter("age"));
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String genre = req.getParameter("genre");

        Account acc = (Account) session.getAttribute("accToBeRead");
        // Profile profile = acc.getUserProfile(); typo?

        acc.setActive(active);
        acc.setPassword(pword);
        if (type == 0) {
            CustProfile custProf = (CustProfile) acc.getUserProfile();

            custProf.setFirstName(fname);
            custProf.setLastName(lname);
            custProf.setGender(gender);
            custProf.setAge(age);
            custProf.setPhoneNo(phone);
            custProf.setEmail(email);
            custProf.setGenre(genre);
            custProf.setLoyaltyPoints(loyaltyPoints);
            System.out.println("Cust Account / Profile Updated! ");
            System.out.println(acc.toString());
            System.out.println(custProf.toString());

        } else {
            Profile Prof = acc.getUserProfile();
            Prof.setFirstName(fname);
            Prof.setLastName(lname);
            Prof.setGender(gender);
            Prof.setAge(age);
            Prof.setPhoneNo(phone);
            Prof.setEmail(email);
            System.out.println(type + "Account / Profile Updated! ");
            System.out.println(acc.toString());
            System.out.println(Prof.toString());

        }
        Account.writeAccountFile();
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("adminPage.jsp");
        reqDispatcher.forward(req, res);

    }
}
