import java.util.ArrayList;

public interface BaseManager {
    public void setCentralManager(CentralManagerEY CentralManager);
    public void getManager(BaseManager manager);

    BaseManager getManager(String managerName);

    public void getMasterList(ArrayList list);
    public void setManagers();

    ArrayList getMasterList(String arrayName);
}
