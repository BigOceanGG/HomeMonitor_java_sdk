import ocean.Application;
import ocean.entity.ApplicationRes;

public class TestSdkBoot {
    public static void main(String[] args) {
        Application app = new Application("http://127.0.0.1:2500");
        try {
            ApplicationRes res = app.findAppInfo("1");
            System.out.print(res.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
