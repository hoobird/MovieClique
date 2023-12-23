package Control;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Entity.Account;

@WebServlet("/login")
public class Login extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(String.format("User %s is trying to login!", username));

        Account currAcc = Account.getAccount(username);
        String[] typeList = { "customer", "staff", "manager", "admin" };

        HttpSession session = req.getSession();
        // successful login
        if (currAcc != null && currAcc.getPassword().equals(password)) {

            if (currAcc.getActive() == false) {
                // account is suspended already
                PrintWriter out = res.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Account has been suspended. Please contact Customer Support.');");
                out.println("location='home.jsp';");
                out.println("</script>");
                return;
            }

            int accType = currAcc.getType(); // 0 = customer , 1 = staff , 2 = manager , 3=admin
            session.setAttribute("sessAccUser", username);
            System.out.println(String.format("%s: User %s is has login-ed!", typeList[accType],
                    session.getAttribute("sessAccUser")));
            // change top banner
            String head = (String) session.getAttribute("isIN");
            session.setAttribute("topBannerHTML", head);
            RequestDispatcher reqDispatcher;
            switch (accType) {
                case 0: // User is customer, redirect to cinema home page
                    reqDispatcher = req.getRequestDispatcher("home.jsp");
                    reqDispatcher.forward(req, res);
                    break;
                case 1: // User is staff
                    reqDispatcher = req.getRequestDispatcher("staffPage.jsp");
                    reqDispatcher.forward(req, res);
                    break;
                case 2: // User is manager
                    reqDispatcher = req.getRequestDispatcher("managerPage.jsp");
                    reqDispatcher.forward(req, res);
                    break;
                case 3: // User is admin
                    reqDispatcher = req.getRequestDispatcher("adminPage.jsp");
                    reqDispatcher.forward(req, res);
                    break;

            }

        } else {
            // failed login
            PrintWriter out = res.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Username or Password is wrong!');");
            out.println("location='login.jsp';");
            out.println("</script>");
        }

    }

}
