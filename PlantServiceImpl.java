import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PlantServiceImpl extends UnicastRemoteObject implements PlantService {

    protected PlantServiceImpl() throws RemoteException {
        super();
    }

    public Map<String, String> getPlantRecommendation(String flower) throws RemoteException {
        Map<String, String> recommendation = new HashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/plant_scheduler", "root", "root");






            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM plants WHERE name = ?");
            stmt.setString(1, flower);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                recommendation.put("Type", rs.getString("type"));
                recommendation.put("Sunlight", rs.getString("sunlight"));
                recommendation.put("Water Frequency", rs.getString("water_frequency"));
            } else {
                recommendation.put("Error", "No data found for this flower.");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            recommendation.put("Error", e.getMessage());
        }
        return recommendation;
    }
}