import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.mongodb.*;
import com.mongodb.client.model.Filters.*;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/query")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class DatabaseServlet extends HttpServlet {
    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String database_name = request.getParameter("database_name");
        request.setAttribute("databasename",database_name);

        String query_type;
        if(database_name.equals("collected_data")){
            query_type = "0";
        }
        else if(database_name.equals("train_composition")) {
            query_type = "1";
        }
        else if(database_name.equals("coach_composition")){
            query_type = "2";
        }
        else{
            query_type = "-1";
        }

        MongoClient mc = new MongoClient("localhost",27017);
        DB db = mc.getDB(database_name);
        Set<String> s = db.getCollectionNames();

        List<DBObject> resultList = new ArrayList<DBObject>();

        for (String temp: s) {
            DBCollection collection = db.getCollection(temp);
            DBCursor cursor = collection.find();
            List<DBObject> collectionList = cursor.toArray();
            resultList.addAll(collectionList);
        }
        request.setAttribute("list", resultList);
        request.setAttribute("size", resultList.size());
        request.setAttribute("querytype", query_type);

        RequestDispatcher reqDispatcher = request.getRequestDispatcher("queryresult.jsp");
        try {
            reqDispatcher.forward(request, response);
        } catch(Exception e){
            e.printStackTrace();
        }

        /*MongoClient mc = new MongoClient("localhost",27017);
        DB db = mc.getDB(database_name);
        Set<String> s = db.getCollectionNames();*/

        /*Object data = (Object)database_name;
        request.setAttribute("database_name", data);
        request.setAttribute("type", (Object)"success");
        request.getRequestDispatcher("/WEB-INF/queryresult.jsp").forward(request, response);*/

        /*PrintWriter out = response.getWriter();

        out.println("<table style='border-width: 1px; border-style: solid; border-collapse: collapse;'>");
        for(String temp: s){ // Iterate over each collection in the database.
            out.println("<tr><th style='border-width: 1px; border-style: solid; padding: 5px; background-color: #DFDFED;'>Collection for coach ");
            out.println(temp);
            out.println("</th></tr>");
            out.println("<tr><td style='border-width: 1px; border-style: solid; padding: 5px;'>");
            DBCollection collection = db.getCollection(temp);
            DBCursor cursor = collection.find(); // Extract all elements of the collection to cursor.
            out.println("<table style='border-width: 1px; border-style: solid; border-collapse: collapse;'><thead>");
            out.println("<tr><th style='border-width: 1px; border-style: solid; padding: 5px; background-color: #DFDFED;'>MongoDB ID</th>");
            out.println("<th style='border-width: 1px; border-style: solid; padding: 5px; background-color: #DFDFED;'>Train ID</th>");
            out.println("<th style='border-width: 1px; border-style: solid; padding: 5px; background-color: #DFDFED;'>Coach ID</th>");
            out.println("<th style='border-width: 1px; border-style: solid; padding: 5px; background-color: #DFDFED;'>Seat ID</th>");
            out.println("<th style='border-width: 1px; border-style: solid; padding: 5px; background-color: #DFDFED;'>Status</th>");
            out.println("<th style='border-width: 1px; border-style: solid; padding: 5px; background-color: #DFDFED;'>Timestamp</th></tr></thead>");

            while(cursor.hasNext()){ // Iterate over the cursor results.
                out.println("<tbody><tr><td style='border-width: 1px; border-style: solid; padding: 5px;'>");
                DBObject o = cursor.next();
                out.println(o.get("_id"));
                out.println("</td><td style='border-width: 1px; border-style: solid; padding: 5px;'>");
                out.println(o.get("train_id"));
                out.println("</td><td style='border-width: 1px; border-style: solid; padding: 5px;'>");
                out.println(o.get("coach_id"));
                out.println("</td><td style='border-width: 1px; border-style: solid; padding: 5px;'>");
                out.println(o.get("seat_id"));
                out.println("</td><td style='border-width: 1px; border-style: solid; padding: 5px;'>");
                out.println(o.get("status"));
                out.println("</td><td style='border-width: 1px; border-style: solid; padding: 5px;'>");
                out.println(o.get("timestamp"));
                out.println("</td></tr></tbody>");

            }
            cursor.close();
            out.println("</table>");
            out.println("</td></tr>");
        }
        out.println("</table>");
        out.println("</div>");/*


        /*try (
                // Step 1: Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://gdpdatabase.cmnhvkgcr2ef.eu-west-2.rds.amazonaws.com:3306/gdpdatabase",
                        "admin", "Purplecarrot94!");   // For MySQL
                // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
                // Step 2: Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();

        ) {
            // Step 3: Execute a SQL SELECT query
            String sqlStr = "SELECT * FROM testTrain";
            out.println("<h3>Thank you for your query.</h3>");
            out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
            //ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
            // Step 4: Process the query result set
            int count = 0;
            while(rset.next()) {
                // Print a paragraph <p>...</p> for each record
                out.println("<p>" + rset.getInt("status") + "</p>");
                count++;
            }
            out.println("<p>==== " + count + " records found =====</p>");
        } catch(Exception ex) {
            out.println("<p>Error: " + ex.getMessage() + "</p>");
            out.println("<p>Check Tomcat console for details.</p>");
            ex.printStackTrace();
        }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)*/

        //out.println("</body></html>");
        //out.close();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");

        MongoClient mc = new MongoClient("localhost",27017);
        DB db = mc.getDB("collected_data");
        DBCollection collection = db.getCollection(request.getParameter("coach_id"));

        int coach_id = Integer.parseInt(request.getParameter("coach_id"));
        int train_id = Integer.parseInt(request.getParameter("train_id"));
        int seat_id = Integer.parseInt(request.getParameter("seat_id"));

        int status;
        String state = request.getParameter("state");
        if(state.equals("free")){
            status = 0;
        } else if(state.equals("occupied")){
            status = 1;
        } else{
            status = 2;
        }

        DBObject query = new QueryBuilder().start().put("seat_id").is(seat_id).get();

        collection.remove(query);

        collection.insert(new BasicDBObject().append("train_id", train_id).append("coach_id", coach_id).append("seat_id", seat_id).append("status", status).append("timestamp", new java.util.Date()));


                /*try (
                    // Step 1: Allocate a database 'Connection' object
                    Connection conn = DriverManager.getConnection(
                            "jdbc:mysql://gdpdatabase.cmnhvkgcr2ef.eu-west-2.rds.amazonaws.com:3306/gdpdatabase",
                            "admin", "Purplecarrot94!");   // For MySQL
                    // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"
                    // Step 2: Allocate a 'Statement' object in the Connection
                    Statement stmt = conn.createStatement();
                ) {
                // Step 3: Execute a SQL SELECT query
                String sqlStr = "INSERT INTO testTrain (seat_id, status) VALUES (1, 0)";
                out.println("<h3>Thank you for your query.</h3>");
                out.println("<p>Your SQL statement is: " + sqlStr + "</p>"); // Echo for debugging
                stmt.executeUpdate(sqlStr);  // Send the query to the server
            } catch(Exception ex) {
                out.println("<p>Error: " + ex.getMessage() + "</p>");
                out.println("<p>Check Tomcat console for details.</p>");
                ex.printStackTrace();
            }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)*/

            out.println("</html>");
            out.close();
    }
}