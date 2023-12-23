package Control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import static java.nio.file.StandardCopyOption.*;

import Entity.Movie;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/createMovie")
@MultipartConfig(maxFileSize = -1, maxRequestSize = -1)
public class ManagerCreateMovie extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher reqDispatcher;
        HttpSession session = req.getSession();
        System.out.println("/createMovie start");
        String name = req.getParameter("movName");

        // Get Server context
        ServletContext context = req.getServletContext();
        // Get the server folder path
        String serverFolderPath = context.getRealPath("/");

        String imgfilename = "";
        for (Part part : req.getParts()) {
            if (part.getName().equals("img")) {
                System.out.println(part.getName());
                imgfilename = part.getSubmittedFileName();
                if (imgfilename == "") {
                    // no file to upload then break lor
                    System.out.println("No image is provided... a default image will be shown instead");
                    imgfilename = "default.png";
                } else {

                    System.out.println("Image is ->" + imgfilename + "<-");
                    serverFolderPath = serverFolderPath + "Resources\\" + imgfilename;
                    System.out.println("Writing to server: " + serverFolderPath);
                    part.write(serverFolderPath); // saves to server (temp)

                    // To copy from serverFolderPath to localFolder... Reason is because part can
                    // only write once... then it self destruct
                    // if you try to write part twice... it wont be able to write anything the
                    // second time you write the part
                    Path sourcePath = Paths.get(serverFolderPath);
                    Path destinationPath = Paths.get((String) session.getAttribute("ProjectFolder")
                            + "src\\main\\webapp\\Resources\\" + imgfilename);

                    try {
                        Files.copy(sourcePath, destinationPath);
                        System.out.println("Image file copied successfully.");
                    } catch (IOException e) {
                        System.out.println("Error occurred while copying image file: " + e.getMessage());
                    }
                }
            }
        }

        String desc = req.getParameter("desc");
        // System.out.println("name: " + name + "\nimg: " + "Resources/" + fileName +
        // "\ndesc:" + desc);
        String genre = req.getParameter("genre");
        new Movie(name, imgfilename, desc, genre);

        reqDispatcher = req.getRequestDispatcher("managerPage.jsp");
        reqDispatcher.forward(req, res);
    }
}