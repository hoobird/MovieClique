package Control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import Entity.FnB;
import Entity.Transaction;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteTransaction")
public class StaffDeleteTransaction extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("/deleteTransaction start");
        // Delete code here
        int delTrans = Integer.parseInt(req.getParameter("delTrans"));
        ArrayList<Transaction> transList = Transaction.getAllTransactions();
        for (Transaction trans : transList) {
            if (trans.getTransactionID() == delTrans) {
                // Revert stock then delete
                for (Map.Entry<FnB, Integer> me : (trans.getOrderQty()).entrySet()) {
                    FnB fnb = me.getKey();
                    int addStock = me.getValue();
                    fnb.setStock(fnb.getStock() + addStock);
                }
                transList.remove(trans);
                break;
            }
        }

        Transaction.writeTransactionFile();
        RequestDispatcher reqDispatcher;
        reqDispatcher = req.getRequestDispatcher("staffTransactions.jsp");
        reqDispatcher.forward(req, res);
    }
}