import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "localhost");

            // Start RMI registry on port 5000
            LocateRegistry.createRegistry(5001);

            // Bind our service
            PlantServiceImpl obj = new PlantServiceImpl();
            Naming.rebind("rmi://localhost:5001/PlantService", obj);

            System.out.println("Plant RMI Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

