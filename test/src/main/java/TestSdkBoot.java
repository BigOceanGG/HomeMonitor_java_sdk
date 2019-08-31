import ocean.Application;
import ocean.entity.ApplicationRes;

public class TestSdkBoot {
    public static void main(String[] args) {
        Application app = new Application("http://172.16.2.51:2500");
        try {
            ApplicationRes res = app.findAppInfo("1");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
