<!DOCTYPE html>
<html>

<head>
    <title>Choose Seats</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <%@ page import="Entity.Session" %>
</head>

<body>
    
    <%@ page import = "java.util.ArrayList" %>
    <%@ page import = "Entity.Account" %>

    <div class="topBanner">
        <!-- This is jsp scripting method -->
        <%= session.getAttribute("topBannerHTML") %>
    </div>



    <div class="body-text">
        <h1>
            <% String[] currSessionInfo = (String[]) session.getAttribute("currSessionInfo");%>
            <%= currSessionInfo[0] + "\n"+currSessionInfo[1] + " (" + currSessionInfo[2] +")"%>
        </h1>
    </div>

    <% int sessionID=Integer.parseInt(String.valueOf(session.getAttribute("currentBookingSessionID"))); %>
    <% Account acc = Account.getAccount((String)session.getAttribute("sessAccUser")); %>

        <div class="login-register-details">
            <img src="Resources/MOVIE.png" alt="Movie Seating Arrangement">

            <form action="BookingConfimationPage.jsp" >
                <div class="row">
                <% for (int i=1; i <=50; i++) { %>
                    <label for = "seats">Seat <%= i %></label>
                    <input type="checkbox" name="seats" value="<%= i %>" <% if
                        ((Session.getSession(sessionID).getSeatList())[i-1].isBooked()==true) { %>disabled<% } %> />
                    <% if (i%10==0) {out.print("</div><div class=\"row\">");} %>
                <% } %>
            </div>

            <div class="row">
                <% if (acc.getType() == 1) {
                    out.println("<label for=\"movie\">Customer Account:</label>" +
                    "<select type=\"text\" name=\"account\" required>");
                    ArrayList<Account> accountList = Account.getAccountList();
                    for(Account account : accountList) {
                        if (account.getType() == 0) {
                            out.println("<option value=\"" + account.getUsername() + "\">" + account.getUsername() + "</option>");
                        }
                    }
                    out.println("</select>");
                } %>
            </div>
            <div class="row">
                <label for="adultQty">Adult Tickets:</label>
                <input type="number" id="adultQty" name="adultQty" value = "0" required />
            </div>
            <div class="row">
                <label for="childQty">Child Tickets:</label>
                <input type="number" id="childQty" name="childQty" value = "0" required />
            </div>
            <div class="row">
                <label for="studentQty">Student Tickets:</label>
                <input type="number" id="studentQty" name="studentQty" value = "0" required />
            </div>
               <div class="row">
                <label for="seniorQty">Senior Tickets:</label>
                <input type="number" id="seniorQty" name="seniorQty" value = "0" required />
              </div>    
            <div class="row">
                <p id="errorText"></p>
                <input type="submit" id="submitBtn"  style="background-color: green;" value="Book Now" disabled />
                </div>
            </form>
            
            <script>
                const checkboxes = document.querySelectorAll('input[type="checkbox"]');
                const adultQtyInput = document.getElementById('adultQty');
                const childQtyInput = document.getElementById('childQty');
                const studentQtyInput = document.getElementById('studentQty');
                const seniorQtyInput = document.getElementById('seniorQty');
                const submitBtn = document.getElementById('submitBtn');
                const errorText = document.getElementById('errorText');
            
                checkboxes.forEach(checkbox => {
                    checkbox.addEventListener('change', updateCheckboxCount);
                });
            
                adultQtyInput.addEventListener('input', updateCheckboxCount);
                childQtyInput.addEventListener('input', updateCheckboxCount);
                studentQtyInput.addEventListener('input', updateCheckboxCount);
                seniorQtyInput.addEventListener('input', updateCheckboxCount);
            
                function updateCheckboxCount() {
                    const checkedCount = document.querySelectorAll('input[type="checkbox"]:checked').length;
                    console.log(`Checked count: ${checkedCount}`);
                    validateForm(checkedCount);
                }
            
                function validateForm(checkedCount) {
                    const adultQty = parseInt(adultQtyInput.value) || 0;
                    const childQty = parseInt(childQtyInput.value) || 0;
                    const studentQty = parseInt(studentQtyInput.value) || 0;
                    const seniorQty = parseInt(seniorQtyInput.value) || 0;
                    const totalQty = adultQty + childQty + studentQty + seniorQty;
            
                    if (checkedCount === undefined) {
                        checkedCount = document.querySelectorAll('input[type="checkbox"]:checked').length;
                    }
            
                    if (checkedCount !== totalQty) {
                        submitBtn.disabled = true;
                        const remaining = totalQty - checkedCount;
                        errorText.textContent = `Assign ${-remaining} more seat(s) to enable booking.`;
                    }else if(checkedCount === 0 && totalQty === 0 ){
                        submitBtn.disabled = true;
                        const remaining = totalQty - checkedCount;
                        errorText.textContent = `Please select at least 1 seat.`;
                    } 
                    else {
                        submitBtn.disabled = false;
                        errorText.textContent = '';
                    }
                }
            </script>
            

        </div>
</body>
<footer>

</footer>

</html>