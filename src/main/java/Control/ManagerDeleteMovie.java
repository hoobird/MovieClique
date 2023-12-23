package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.Movie;
import Entity.Session;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteMovie")
public class ManagerDeleteMovie extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/deleteMovie start");
        // Delete code here
        int delMovie = Integer.parseInt(req.getParameter("delMovie"));
        // Prevent movie from being deleted if a session is using it
        int inSession = 0;
        ArrayList<Session> sessList = Session.getSessionList();
        for (Session sess : sessList) {
            if (sess.getMov().getMovieID() == delMovie) {
                inSession++;
                break;
            }
        }
        if (inSession == 0) {
            ArrayList<Movie> movieList = Movie.getMovieList();
            for (Movie movie : movieList) {
                if (movie.getMovieID() == delMovie) {
                    movieList.remove(movie);
                    break;
                }
            }
            Movie.writeMovieFile();
            RequestDispatcher reqDispatcher;
            reqDispatcher = req.getRequestDispatcher("managerMovies.jsp");
            reqDispatcher.forward(req, res);
        } else {
            PrintWriter out = res.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('The movie you are trying to delete exists in a session!');");
            out.println("location='managerMovies.jsp';");
            out.println("</script>");
        }
    }
}