package Control;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Entity.FnB;

@WebServlet("/updateFnb")
public class FnBUpdate extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/updateFnb start");
        HttpSession session = req.getSession();
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("fnbInventory.jsp");
        String saveFnbID = req.getParameter("saveFnbID");

        if (saveFnbID != null && !saveFnbID.equals("")) {
            // Directed by save button (request to save edit)
            String newFnbName = req.getParameter("saveFnbName");
            String newFnbPrice = req.getParameter("saveFnbPrice");
            String newFnbStock = req.getParameter("saveFnbStock");
            FnB currFnb = (FnB) session.getAttribute("toEdit");
            // Making sure updates are actual changes to values
            if ((newFnbName != null && !newFnbName.equals("")) && !currFnb.getName().equals(newFnbName)) {
                currFnb.setName(newFnbName);
            }
            if (newFnbPrice != null && !newFnbPrice.equals("")) {
                float newPrice = Float.parseFloat(newFnbPrice);
                if (currFnb.getPrice() != newPrice) {
                    currFnb.setPrice(newPrice);
                }
            }
            if (newFnbStock != null && !newFnbStock.equals("")) {
                int newStock = Integer.parseInt(newFnbStock);
                if (currFnb.getStock() != newStock) {
                    currFnb.setStock(newStock);
                }
            }
            FnB.writeFnbFile();
            session.removeAttribute("toEdit");
            reqDispatcher.forward(req, res);
        } else if (req.getParameter("editFnb") != null) {
            // Directed by edit button (request to edit)
            int fnbID = Integer.parseInt(req.getParameter("editFnb"));
            session.setAttribute("toEdit", FnB.getFnb(fnbID));
            reqDispatcher.forward(req, res);
        } else {
            session.removeAttribute("toEdit");
            reqDispatcher.forward(req, res);
        }
    }
}
