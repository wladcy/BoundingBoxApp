package controlers;

import models.BoundingBoxDataModel;

import java.util.ArrayList;

public class BoundingBoxToCSVControler {
    public ArrayList<String> convert(ArrayList<BoundingBoxDataModel> bbdm){
        ArrayList<String> retval =new ArrayList<>();
        for(BoundingBoxDataModel model:bbdm) {
            String text="";
            text += model.getFileName() + ";";
            text += model.getMinX() + ";";
            text += model.getMinY() + ";";
            text += model.getWidth() + ";";
            text += model.getHeight();
            retval.add(text);
        }
        return retval;
    }
}
