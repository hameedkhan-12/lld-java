interface IDataService{
    String fetchData();
}

class RealDataService implements IDataService {
    public RealDataService(){
        System.out.println("[RealDataService] initialized (simulating remote setup)");
    }

    @Override 
    public String fetchData(){
        return "[RealDataService] Data from server";
    }
}

class DataServiceProxy implements IDataService {
    private RealDataService realService;

    public DataServiceProxy(){
        realService = new RealDataService();
    }

    @Override
    public String fetchData(){
        System.out.println("[DataServiceProxy] Fetching data from server (simulating remote setup)");
        return realService.fetchData();
    }
}

public class RemoteProxy {
    public static void main(String[] args){
        IDataService service = new DataServiceProxy();
        System.out.println(service.fetchData());
    }
}