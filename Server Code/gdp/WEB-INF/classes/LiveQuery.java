import com.google.gson.*;
import com.mongodb.*;
import com.mongodb.client.model.Filters.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.*;
import java.util.Map;

import java.io.IOException;
import java.util.ArrayList;

/** Handler servlet for web service queries to live collected data.
 * @author Anna Irma Elizabeth Kennedy, el17ak@leeds.ac.uk
 * @version 1.0
 */
@WebServlet("/api/live")
public class LiveQuery extends HttpServlet {

    /** GET request handler for this web service.
     * @param request the HTTP request sent to the URL.
     * @param response the HTTP response which will be returned to the client.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            MongoClient mc = new MongoClient("localhost",27017);
            DB db = mc.getDB("collected_data");
            String carriage_id = request.getParameter("carriage_id"); // Retrieve parameter from URL.
            DBCollection collection = db.getCollection(carriage_id);
            DBCursor cursor = collection.find(); // Insert all entries of the collection into a cursor.
            ArrayList<DBObject> list = new ArrayList<DBObject>();
            Gson gson = new Gson(); // Google Json serializing API
            while(cursor.hasNext()){
                list.add(cursor.next()); //Store each element of the cursor as a Database Object.
            }
            response.setContentType("application/json");
            gson.toJson(list, response.getWriter()); // Convert list to JSON, write to response.
            return;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            MongoClient mc = new MongoClient("localhost",27017);
            DB db = mc.getDB("collected_data");
            Map<String, String[]> parameterMap = request.getParameterMap();
            if(parameterMap.size() == 4 && parameterMap.containsKey("train_id") && parameterMap.containsKey("coach_id") && parameterMap.containsKey("seat_id") && parameterMap.containsKey("radioStatus")){
                DBCollection collection = db.getCollection(parameterMap.get("coach_id")[0]);
                DBObject query = QueryBuilder.start().put("seat_id").is(Integer.parseInt(parameterMap.get("seat_id")[0])).get();
                collection.remove(query);

                int radio_status;
                if(parameterMap.get("radioStatus")[0].equals("availableStatus")) {
                    radio_status = 0;
                } else if(parameterMap.get("radioStatus")[0].equals("occupiedStatus")) {
                    radio_status = 1;
                } else if(parameterMap.get("radioStatus")[0].equals("reservedStatus")) {
                    radio_status = 2;
                } else{
                    radio_status = 0;
                }

                collection.insert(new BasicDBObject().append("train_id", Integer.parseInt(parameterMap.get("train_id")[0])).append("coach_id", Integer.parseInt(parameterMap.get("coach_id")[0])).append("seat_id", Integer.parseInt(parameterMap.get("seat_id")[0])).append("status", radio_status).append("timestamp", new java.util.Date()));

                mc.close();
                return;
            }
            return;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}