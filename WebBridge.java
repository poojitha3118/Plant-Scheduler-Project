import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.rmi.Naming;
import java.util.Map;

public class WebBridge {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0); // HTTP port
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Web server running at http://localhost:8080");
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "<html><body><h2>Plant Care Recommendation</h2>"
                + "<form method='get'>Enter flower name: <input type='text' name='flower'>"
                + "<input type='submit' value='Get Schedule'></form>";

            String query = t.getRequestURI().getQuery();
            if (query != null && query.contains("flower=")) {
                String flower = query.split("flower=")[1];
                try {
                    PlantService service = (PlantService) Naming.lookup("rmi://localhost:5001/PlantService");
                    Map<String, String> data = service.getPlantRecommendation(flower);

                    response += "<h3>Results:</h3><ul>";
                    for (String key : data.keySet()) {
                        response += "<li><b>" + key + ":</b> " + data.get(key) + "</li>";
                    }
                    response += "</ul>";
                } catch (Exception e) {
                    response += "<p>Error: " + e.getMessage() + "</p>";
                }
            }

            response += "</body></html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}