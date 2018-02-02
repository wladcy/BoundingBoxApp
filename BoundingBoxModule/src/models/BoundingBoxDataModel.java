package models;

public class BoundingBoxDataModel {
    public static final String FIRST_LINE="file;minX;minY;width;height";
    private String fileName;
    private double minX;
    private double minY;
    private double width;
    private double height;

    private BoundingBoxDataModel(){}

    public BoundingBoxDataModel(String fileName, double minX, double minY, double width, double height){
        this.fileName=fileName;
        this.minX=minX;
        this.minY=minY;
        this.height=height;
        this.width=width;
    }

    public void setMinX(double minX){
        this.minX=minX;
    }

    public double getMinX(){
        return this.minX;
    }

    public void setMinY(double minY){
        this.minY=minY;
    }

    public double getMinY(){
        return this.minY;
    }

    public void setWidth(double width){
        this.width=width;
    }

    public double getWidth(){
        return this.width;
    }

    public void setHeight(double height){
        this.height=height;
    }

    public double getHeight(){
        return this.height;
    }

    public void setFileName(String fileName){
        this.fileName=fileName;
    }

    public String getFileName(){
        return this.fileName;
    }
}
