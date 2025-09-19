import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface PlantService extends Remote {
    Map<String, String> getPlantRecommendation(String flower) throws RemoteException;
}