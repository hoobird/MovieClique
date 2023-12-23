package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import Entity.Account;
import Entity.Movie;
import Entity.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/getmvDetails")
public class GetMovieDetails extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();

        if (session.getAttribute("sessAccUser") == null){
            PrintWriter out = res.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Please login first to view movie sessions.');");
            out.println("location='home.jsp';");
            out.println("</script>");
            return;
        }
        

        String movieID = req.getParameter("movieid");
        Movie movie = Movie.getMovie(Integer.parseInt(movieID));

        String movieDetails = ("<table>\n" +
                "<tr>\n" +
                "   <td colspan=\"2\" style=\"font-weight: bold;\">Movie Details</td>\n" +
                "</tr>\n" +
                "<tr>\n" +
                "   <td style=\"width:310px;\"><img src=\"Resources\\" + movie.getImgFile() + "\" alt=\" Movie "
                + movie.getMovieID() + "\"  style = \"max-width: 300px\" >\n" +
                "</td>\n" +
                "   <td><b><u> " + movie.getName() + "</u></b><br>" + movie.getDescription() + "</td>\n" +
                "</tr>\n" +
                "</table>\n" +
                "<br>\n" +
                "<br>\n");

        
        StringBuilder movieSessions = new StringBuilder();
        movieSessions.append("<table>\n");
        for (int day =1 ; day<=7; day++){
            movieSessions.append("<tr>");
            movieSessions.append("<td>"+(Session.getDaysInWeek())[day-1] +"</td>");
            movieSessions.append("<td>");

            for (Session sess : Session.getSessionList()){
                if (sess.getMov().getMovieID() == Integer.parseInt(movieID) && sess.getDay() == day){
                    movieSessions.append("<a href=\"getmvSessSeats?sessionID=" + sess.getSessionID()+"\"><button style=\"background-color: #660000;\">"+Session.getTimeSlots()[sess.getSlot()-1] +"</button></a>");
                }
            }

            movieSessions.append("</td></tr>");
        }
        movieSessions.append("</table>");
        
        session.setAttribute("movieDetailHtml", movieDetails);
        session.setAttribute("movieSessionsHtml", movieSessions);
        RequestDispatcher rd = req.getRequestDispatcher("MovieDetailsPage.jsp");
        rd.forward(req, res);
    }
}
