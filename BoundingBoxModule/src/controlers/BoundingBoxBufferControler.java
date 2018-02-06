package controlers;

import models.BoundingBoxDataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class BoundingBoxBufferControler {
    private static BoundingBoxBufferControler instance = null;
    private HashMap<String, ArrayList<BoundingBoxDataModel>> map;
    private BoundingBoxBufferControler(){
        map=new HashMap<>();
    }

    public static BoundingBoxBufferControler getInstance(){
        if(instance==null) {
            instance = new BoundingBoxBufferControler();
        }
        return instance;
    }

    public boolean isInBuffer(String fileName){
        return map.containsKey(fileName);
    }

    public void initializeBuffer(ArrayList<BoundingBoxDataModel> data){
        for(BoundingBoxDataModel bbdm: data){
            addBoungingBox(bbdm);
        }
    }

    public ArrayList<BoundingBoxDataModel> getBoundingBoxes(String fileName){
        return map.get(fileName);
    }

    private void addBoungingBox(BoundingBoxDataModel data){
        if(isInBuffer(data.getFileName())){
            ArrayList<BoundingBoxDataModel> list = map.get(data.getFileName());
            list.add(data);
            map.put(data.getFileName(),list);
        }else {
            ArrayList<BoundingBoxDataModel> list = new ArrayList<>();
            list.add(data);
            map.put(data.getFileName(),list);
        }
    }
}
