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
import java.util.Arrays;

import Entity.Account;
// import java.io.PrintWriter;
import Entity.CustProfile;
import Entity.Profile;

@WebServlet("/adminSearchAccount")
public class AdminSearchAccount extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/adminSearchAccount start");

        String[] typeList = { "customer", "staff", "manager", "admin" };
        String[] genreList = { "Action", "Adventure", "Comedy", "Crime", "Drama", "Fantasy", "Historical", "Horror",
                "Romance" };

        String[] query = req.getParameterValues("adminQuery");
        String[] Cquery = req.getParameterValues("adminCQuery");
        System.out.println(Arrays.toString(Cquery));

        StringBuilder output = new StringBuilder();
        output.append("<table>");
        output.append(
                "<tr><th>Username</th><th>Password</th><th>Account Type</th><th>Account Status</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Age</th><th>Phone No</th><th>Email</th><th>Genre</th></tr>");
        for (Account acc : Account.getAccountList()) {
            if (query == null) {
                continue;
            } else if (!(Arrays.asList(query).contains("1")) && acc.getType() == 1) {
                continue;
            } else if (!(Arrays.asList(query).contains("2")) && acc.getType() == 2) {
                continue;
            } else if (!(Arrays.asList(query).contains("3")) && acc.getType() == 3) {
                continue;
            } else if (!(Arrays.asList(query).contains("0")) && acc.getType() == 0) {
                continue;
            }
            if (acc.getType() == 0) {
                if (Cquery == null) {
                    continue;
                }

                if (!(Arrays.asList(Cquery).contains(((CustProfile) acc.getUserProfile()).getGenre()))) {
                    continue;
                }

            }

            Profile currProf = acc.getUserProfile();

            output.append("<tr>");
            output.append("<td>" + acc.getUsername() + "</td>");
            output.append("<td>" + acc.getPassword() + "</td>");
            output.append("<td>" + typeList[acc.getType()] + "</td>");
            output.append("<td>" + (acc.getActive() ? "Active" : "Suspended") + "</td>");
            output.append("<td>" + currProf.getFirstName() + "</td>");
            output.append("<td>" + currProf.getLastName() + "</td>");
            output.append("<td>" + currProf.getGender() + "</td>");
            output.append("<td>" + currProf.getAge() + "</td>");
            output.append("<td>" + currProf.getPhoneNo() + "</td>");
            output.append("<td>" + currProf.getEmail() + "</td>");
            output.append(
                    "<td>" + ((currProf instanceof CustProfile) ? ((CustProfile) currProf).getGenre() : "") + "</td>");
            output.append("</tr>");
        }
        output.append("</table>");

        HttpSession session = req.getSession();
        session.setAttribute("searchQueryResult", output.toString());
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("adminSearchQuery.jsp");
        reqDispatcher.forward(req, res);

        // PrintWriter out = res.getWriter();
        // out.println("Username: " + i + "\nPassword: " + j);
        // out.println("Username: " + i + "\nPassword: " + j);

    }
}