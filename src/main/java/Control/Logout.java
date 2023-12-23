package Control;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        
        HttpSession session = req.getSession();
        String username = (String)session.getAttribute("sessAccUser");
        System.out.println(String.format("User %s is trying to logout!", username));

        session.setAttribute("sessAccUser", null);

        // change top banner        
        String head = (String)session.getAttribute("isOUT");
        session.setAttribute("topBannerHTML", head);

        RequestDispatcher reqDispatcher = req.getRequestDispatcher("home.jsp");
        reqDispatcher.forward(req, res);
    }

}
