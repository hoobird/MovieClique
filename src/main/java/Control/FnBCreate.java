package Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entity.FnB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/createFnb")
public class FnBCreate extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/createFnb start");
        String fnbName = req.getParameter("fnbName");
        float fnbPrice = Float.parseFloat(req.getParameter("fnbPrice"));
        int fnbStock = Integer.parseInt(req.getParameter("fnbStock"));

        new FnB(fnbName, fnbPrice, fnbStock);
        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("fnbInventory.jsp");
        reqDispatcher.forward(req, res);
    }
}