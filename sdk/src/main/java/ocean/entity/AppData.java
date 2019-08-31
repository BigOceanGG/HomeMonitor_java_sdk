package ocean.entity;

import java.util.ArrayList;

public class AppData {
    private Integer pageNum;
    private Integer pageSize;
    private Integer size;
    private String orderBy;
    private Integer startRow;
    private Integer endRow;
    private Integer total;
    private Integer pages;
    private ArrayList<AppInfo> list = new ArrayList <AppInfo> ();
}
