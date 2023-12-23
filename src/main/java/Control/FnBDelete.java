package Control;

import java.io.IOException;
import java.util.ArrayList;

import Entity.FnB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteFnb")
public class FnBDelete extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/deleteFnB start");
        // Delete code here
        int delFnb = Integer.parseInt(req.getParameter("delFnb"));
        
        ArrayList<FnB> fnbList = FnB.getFnbList();
        for (FnB fnb : fnbList) {
            if (fnb.getFnbID() == delFnb) {
                fnbList.remove(fnb);
                break;
            }
        }
        FnB.writeFnbFile();
        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("fnbInventory.jsp");
        reqDispatcher.forward(req, res);

    }
}