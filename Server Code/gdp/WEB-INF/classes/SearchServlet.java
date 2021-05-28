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

@WebServlet("/search")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class SearchServlet extends HttpServlet {
    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search_term = request.getParameter("searchterm");
        request.setAttribute("searchterm", search_term);
        try{
            MongoClient mc = new MongoClient("localhost",27017);
            DB db = mc.getDB("train_composition");
            DBCollection collection = db.getCollection(search_term);
            DBCursor cursor = collection.find();
            ArrayList<DBObject> list = new ArrayList<DBObject>();
            while(cursor.hasNext()){
                list.add(cursor.next());
            }
            request.setAttribute("listsize", list.size());
            request.setAttribute("list", list);
            request.getRequestDispatcher("searchresult.jsp").forward(request, response);
            return;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}