import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.mongodb.*;
import com.mongodb.client.model.Filters.*;
import java.util.Set;
import java.util.ArrayList;
import java.util.Map;

import com.google.gson.*;

@WebServlet("/api/composition/coach")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class CoachQuery extends HttpServlet {
    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MongoClient mc = new MongoClient("localhost", 27017);
            DB db = mc.getDB("coach_composition");
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.size() == 1 && parameterMap.containsKey("coach_id")) {
                DBCollection collection = db.getCollection(parameterMap.get("coach_id")[0]);
                DBCursor cursor = collection.find();
                ArrayList<DBObject> list = new ArrayList<DBObject>();
                Gson gson = new Gson();
                while (cursor.hasNext()) {
                    list.add(cursor.next());
                }
                response.setContentType("application/json");
                gson.toJson(list, response.getWriter());
                mc.close();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int table, window, priority;
        try {
            MongoClient mc = new MongoClient("localhost", 27017);
            DB db = mc.getDB("coach_composition");

            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.size() < 6 && parameterMap.containsKey("coach_id") && parameterMap.containsKey("seat_id")) {
                if(!parameterMap.containsKey("table")){
                    table = 0;
                }
                else{
                    table = Integer.parseInt(parameterMap.get("table")[0]);
                }
                if(!parameterMap.containsKey("window")){
                    window = 0;
                }
                else{
                    window = Integer.parseInt(parameterMap.get("window")[0]);
                }
                if(!parameterMap.containsKey("priority")){
                    priority = 0;
                }
                else {
                    priority = Integer.parseInt(parameterMap.get("priority")[0]);
                }

                DBCollection collection = db.getCollection(parameterMap.get("coach_id")[0]);

                DBObject query = QueryBuilder.start().put("seat_id").is(Integer.parseInt(parameterMap.get("seat_id")[0])).get();
                collection.remove(query);
                collection.insert(new BasicDBObject().append("coach_id", Integer.parseInt(parameterMap.get("coach_id")[0])).append("seat_id", Integer.parseInt(parameterMap.get("seat_id")[0])).append("table", table).append("window", window).append("priority", priority).append("timestamp", new java.util.Date()));

                DBCursor cursor = collection.find();
                ArrayList<DBObject> list = new ArrayList<DBObject>();
                Gson gson = new Gson();
                while (cursor.hasNext()) {
                    list.add(cursor.next());
                }

                response.setContentType("application/json");
                gson.toJson(list, response.getWriter());
                mc.close();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MongoClient mc = new MongoClient("localhost", 27017);
            DB db = mc.getDB("coach_composition");
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.size() == 1 && parameterMap.containsKey("coach_id")) {
                db.getCollection(parameterMap.get("coach_id")[0]).drop();
                mc.close();
                return;
            }
            else if(parameterMap.size() == 2 && parameterMap.containsKey("coach_id") && parameterMap.containsKey("seat_id")){
                DBCollection collection = db.getCollection(parameterMap.get("coach_id")[0]);
                DBObject query = QueryBuilder.start().put("seat_id").is(parameterMap.get("seat_id")[0]).get();
                collection.remove(query);
                mc.close();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}