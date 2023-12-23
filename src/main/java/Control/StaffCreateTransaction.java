package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Entity.Account;
import Entity.FnB;
import Entity.Transaction;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/createTransaction")
public class StaffCreateTransaction extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/createTransaction start");
        String accountUname = req.getParameter("account");
        Account acc = Account.getAccount(accountUname);
        ArrayList<FnB> fnbList = FnB.getFnbList();
        Map<String, String[]> params= req.getParameterMap();
        HashMap<FnB, Integer> orderQty = new HashMap<FnB, Integer>();
        for (Map.Entry<String, String[]> me : params.entrySet()) {
            String pName = me.getKey();
            String[] pVals = me.getValue();
            if (!pName.equals("account") && !pVals[0].equals("") && fnbList.contains(FnB.getFnb(Integer.parseInt(pName)))) {
                orderQty.put(FnB.getFnb(Integer.parseInt(pName)), Integer.parseInt(pVals[0]));
            }
        }
        // Creating a transaction
        new Transaction(acc, orderQty);
        RequestDispatcher reqDispatcher;
        HttpSession session = req.getSession();
        int accType = (Account.getAccount((String)session.getAttribute("sessAccUser"))).getType();
        if (accType == 1) {
            reqDispatcher = req.getRequestDispatcher("staffTransactions.jsp");
            reqDispatcher.forward(req, res);
        } else if (accType == 0) {
            reqDispatcher = req.getRequestDispatcher("customerTransactions.jsp");
            reqDispatcher.forward(req, res);
        }
    }
}