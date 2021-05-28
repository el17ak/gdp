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

@WebServlet("/api/composition/train")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class TrainQuery extends HttpServlet {
    // The doGet() runs once per HTTP GET request to this servlet.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            MongoClient mc = new MongoClient("localhost",27017);
            DB db = mc.getDB("train_composition");
            Map<String, String[]> parameterMap = request.getParameterMap();
            if(parameterMap.size() == 1 && parameterMap.containsKey("train_id")){
                DBCollection collection = db.getCollection(parameterMap.get("train_id")[0]);
                DBCursor cursor = collection.find();
                ArrayList<DBObject> list = new ArrayList<DBObject>();
                Gson gson = new Gson();
                while(cursor.hasNext()){
                    list.add(cursor.next());
                }
                response.setContentType("application/json");
                gson.toJson(list, response.getWriter());
                mc.close();
                return;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try{
            MongoClient mc = new MongoClient("localhost",27017);
            DB db = mc.getDB("train_composition");

            Map<String, String[]> parameterMap = request.getParameterMap();
            if(parameterMap.size() == 3 && parameterMap.containsKey("train_id") && parameterMap.containsKey("coach_id") && parameterMap.containsKey("coach_position")){
                Set<String> s = db.getCollectionNames();
                DBObject query = QueryBuilder.start().put("coach_id").is(Integer.parseInt(parameterMap.get("coach_id")[0])).get();
                for(String temp: s){
                    db.getCollection(temp).remove(query);
                }

                DBCollection collection = db.getCollection(parameterMap.get("train_id")[0]);
                collection.insert(new BasicDBObject().append("train_id", Integer.parseInt(parameterMap.get("train_id")[0])).append("coach_id", Integer.parseInt(parameterMap.get("coach_id")[0])).append("coach_position", Integer.parseInt(parameterMap.get("coach_position")[0])).append("timestamp", new java.util.Date()));

                DBCursor cursor = collection.find();
                ArrayList<DBObject> list = new ArrayList<DBObject>();
                Gson gson = new Gson();
                while(cursor.hasNext()){
                    list.add(cursor.next());
                }

                response.setContentType("application/json");
                gson.toJson(list, response.getWriter());
                mc.close();
                return;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MongoClient mc = new MongoClient("localhost", 27017);
            DB db = mc.getDB("train_composition");
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.size() == 1 && parameterMap.containsKey("train_id")) {
                db.getCollection(parameterMap.get("train_id")[0]).drop();
                mc.close();
                return;
            }
            else if(parameterMap.size() == 2 && parameterMap.containsKey("train_id") && parameterMap.containsKey("coach_id")){
                DBCollection collection = db.getCollection(parameterMap.get("train_id")[0]);
                DBObject query = QueryBuilder.start().put("coach_id").is(parameterMap.get("coach_id")[0]).get();
                collection.remove(query);
                mc.close();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}