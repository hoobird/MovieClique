package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getmv")
public class GetMovieList extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        ArrayList<Movie> currentMovieList = Movie.getMovieList();
        StringBuilder movieHtmlElements = new StringBuilder();
        System.out.println("MovieList is generating");
        for (Movie show : currentMovieList) {
            movieHtmlElements.append(
                    String.format(
                            "<div class= \"movie-poster\">"+
                            "<a href=\"getmvDetails?movieid=%d\"><img src=\"%s\" alt=\"Movie %d\"></a>" +
                            "<div class=\"movie-description\"><h3>%s</h3><p>%s</p></div></div>", show.getMovieID(),
                            ("Resources\\"+show.getImgFile()), show.getMovieID(), show.getName(), show.getDescription()));
        }
        PrintWriter out = res.getWriter();
        out.println(movieHtmlElements.toString());


        // req.setAttribute("movies", movieHtmlElements);
        // RequestDispatcher rd = req.getRequestDispatcher("NowShowing.jsp");
        // rd.forward(req, res);
    }
}
