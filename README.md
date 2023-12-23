CSIT314 - Software Development Methodologies   
Group: Food-Clique  

Specifications:
- App Name: Movieclique by FoodClique
- Languages : Java with Maven, HTML, CSS, Javascript, JSP
- Java Version: JDK 17
- Apache Maven Version: 3.9.1
- Server : Apache Tomcat 10.1.4

- IDE: Visual Studio Code(VSC)
- Extensions used: 
	+ Extension Pack for Java 
	+ Maven for Java
	+ Community Server Connectors (By Red Hat)


How to setup:
1) Ensure that Java JDK 17 and Apache Maven 3.9.1 is installed and set-up properly.
2) Download VSC extensions stated above.
3) Download and open the project folder in VSC.
4) To ensure that movie poster uploads work, navigate to MovieClique/src/main/java/Control/Start.java.
	+ Kindly edit line 56: session.setAttribute("ProjectFolder", "#########");
	+ Please change the second parameter of this line of code to the location of the project folder
	+ For eg: line 56: session.setAttribute("ProjectFolder", "C:\\Download\\MovieClique\\");
	+ This folder should contain the src folder
	+ Do remember to user 2 backslash instead of 1 because its an escape character.
5) Under Explorer, go to MAVEN tab and right click the webapp.
6) Select Run Maven Commands -> install.
7) After installation and building of the app is done, a target folder should be fully generated in the Project Folder.
8) Under Explorer, go to SERVERS tab, right click Community Server Connector.
9) Select Create New Server --> Yes --> Apache Tomcat 10.1.4 (Internet required)
10) After Server is fully installed, navigate to the target folder, right-click the war file (movieclique.war).
11) Select Run on Server
12) Go back to the SERVERS tab, right click the apache-tomcat-10.1.4 server and start server if not started.
13) To use the application, right click on apache-tomcat-10.1.4 server again, select Server Actions --> Show in Browser --> http://localhost:8080/movieclique/. 
14) Now the Movie Booking System is ready for use.
* If sample accounts are required, check the console on VSC, where 1 account of each type will have their details printed.
In the case that sample files are provided(sampleFiles folder), locate "ServerFolderPath:" and navigate back to tomcat server's bin file (e.g. C:\Users\-\tomcat\apache-tomcat-10.1.8\bin) and place the json files into it.
