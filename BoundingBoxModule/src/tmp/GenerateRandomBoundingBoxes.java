package tmp;

import models.BoundingBoxDataModel;

import java.util.Random;

public class GenerateRandomBoundingBoxes {

    public BoundingBoxDataModel getNewBoundingBox(String fileName){
        Random r = new Random();
        double minX = r.nextDouble();
        double minY = r.nextDouble();
        double height = r.nextDouble();
        double width = r.nextDouble();
        BoundingBoxDataModel bbdm = new BoundingBoxDataModel(fileName,minX,minY,width,height);
        return bbdm;
    }
}
