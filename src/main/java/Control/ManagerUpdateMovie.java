package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Entity.Movie;

@WebServlet("/updateMovie")
public class ManagerUpdateMovie extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/updateMovie start");
        HttpSession session = req.getSession();
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("managerMovies.jsp");
        String saveMovieID = req.getParameter("saveMovieID");
        if (saveMovieID != null && !saveMovieID.equals("")) {
            // Directed by save button (request to save edit)
            String newMovieName = req.getParameter("saveMovieName");
            String newImgFile = req.getParameter("saveMovieImgFile");
            String newMovieDescription = req.getParameter("saveMovieDescription");
            String newMovieGenre = req.getParameter("saveMovieGenre");
            Movie currMovie = (Movie) session.getAttribute("toEdit");
            // Making sure updates are actual changes to values
            if (newMovieName != null && !newMovieName.equals("") && !currMovie.getName().equals(newMovieName)) {
                currMovie.setName(newMovieName);
            }
            if (newImgFile != null && !newImgFile.equals("") && !currMovie.getImgFile().equals(newImgFile)) {
                currMovie.setImgFile(newImgFile);
            }
            if (newMovieDescription != null && !newMovieDescription.equals("")
                    && !currMovie.getDescription().equals(newMovieDescription)) {
                currMovie.setDescription(newMovieDescription);
            }
            if (newMovieGenre != null && !newMovieGenre.equals("") && !currMovie.getGenre().equals(newMovieGenre)) {
                currMovie.setGenre(newMovieGenre);
            }
            Movie.writeMovieFile();
            session.removeAttribute("toEdit");
            reqDispatcher.forward(req, res);
        } else if (req.getParameter("editMovie") != null) {
            // Directed by edit button (request to edit)
            String movieName = req.getParameter("editMovie");
            session.setAttribute("toEdit", Movie.getMovie(movieName));
            reqDispatcher.forward(req, res);
        } else {
            session.removeAttribute("toEdit");
            reqDispatcher.forward(req, res);
        }
    }
}
