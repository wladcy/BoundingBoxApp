package controlers;

import models.BoundingBoxDataModel;

public class BoundingBoxToCSVControler {
    public String convert(BoundingBoxDataModel model){
        String retval ="";
        retval+=model.getFileName()+";";
        retval+=model.getMinX()+";";
        retval+=model.getMinY()+";";
        retval+=model.getWidth()+";";
        retval+=model.getHeight();
        return retval;
    }
}
