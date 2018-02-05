package controlers;

import models.BoundingBoxDataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class BoundingBoxBufferControler {
    private static BoundingBoxBufferControler instance = null;
    private HashMap<String, ArrayList<BoundingBoxDataModel>> map;
    private BoundingBoxBufferControler(){}

    public static BoundingBoxBufferControler getInstance(){
        if(instance==null){
            instance = new BoundingBoxBufferControler();
        }
        return instance;
    }

    public boolean isInBuffer(String fileName){
        return map.containsKey(fileName);
    }
}
