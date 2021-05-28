import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.mongodb.*;
import com.mongodb.client.model.Filters.*;
import java.util.Set;
import java.util.ArrayList;

import com.google.gson.*;

@WebServlet("/live")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class LiveServlet extends HttpServlet {

    /* GET HTTP Request to '/live' retrieves seating formation data and sends it to the JSP file to show all seats.
    This servlet does not handle any of the actual collected data showing, this is handled by JSP scripts.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        // First, the requested carriage's ID has to be included in the request.
        String carriage_id = request.getParameter("carriage_id");
        request.setAttribute("carriage_id", carriage_id);
        try{
            MongoClient mc = new MongoClient("localhost",27017);
            DB db = mc.getDB("collected_data");
            DBCollection collection = db.getCollection(carriage_id);
            DBCursor cursor = collection.find();
            ArrayList<DBObject> list = new ArrayList<DBObject>();
            while(cursor.hasNext()) {
                list.add(cursor.next());
            }

            request.setAttribute("seat-list", list);
            RequestDispatcher reqDispatcher = request.getRequestDispatcher("live.jsp");
            reqDispatcher.include(request, response);
            return;
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}