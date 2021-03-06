package gui;

import controlers.BoundingBoxBufferControler;
import controlers.BoundingBoxToCSVControler;
import files.CSVFiles;
import files.CmnFiles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import models.BoundingBoxDataModel;
import tmp.GenerateRandomBoundingBoxes;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BoundingBoxController implements Initializable {

    @FXML
    private Pane imagePanel;
    @FXML
    private Canvas image;

    private CmnFiles cf;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void showPrevious(){
        if(checkImages()){
            setImage("-");
        }
    }

    @FXML
    public void showNext(){
        if(checkImages()){
            setImage("+");
        }
    }

    @FXML
    public void save(){
        if(checkImages()) {
            GenerateRandomBoundingBoxes grbb = new GenerateRandomBoundingBoxes();
            BoundingBoxToCSVControler bbtcc = new BoundingBoxToCSVControler();
            File f = cf.getActualImage();
            String[] tmp = f.getAbsolutePath().split("\\\\");
            String path = "";
            for (int i = 0; i < tmp.length - 1; i++) {
                path += tmp[i] + "\\\\";
            }
            File director = new File(path+tmp[tmp.length-2]+".CSV");
            ArrayList<BoundingBoxDataModel> bbdm = new ArrayList<>();
            bbdm.add(grbb.getNewBoundingBox(f.getPath()));//TODO trzeba zmienić: gdy zacznie pobierać bounding boxy z gui-> wyłączyć generator
            BoundingBoxBufferControler bbbc = BoundingBoxBufferControler.getInstance();
            bbbc.initializeBuffer(bbdm);
            try {
                CSVFiles.append(director, bbtcc.convert(bbdm));
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd pliku");
                alert.setHeaderText(null);
                alert.setContentText("Wystapił błąd przy zapisie do pliku. Proszę spróbowac ponownie później");
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                String exceptionText = sw.toString();
                Label label = new Label("Wyjatek:");
                TextArea textArea = new TextArea(exceptionText);
                textArea.setEditable(false);
                textArea.setWrapText(true);
                textArea.setMaxWidth(Double.MAX_VALUE);
                textArea.setMaxHeight(Double.MAX_VALUE);
                GridPane.setHgrow(textArea, Priority.ALWAYS);
                GridPane.setVgrow(textArea, Priority.ALWAYS);
                GridPane expContent = new GridPane();
                expContent.setMaxWidth(Double.MAX_VALUE);
                expContent.add(label, 0, 0);
                expContent.add(textArea, 0, 1);
                alert.getDialogPane().setExpandableContent(expContent);
                alert.showAndWait();
            }
        }

    }

    @FXML
    public void read(){
        imagePanel.setBackground(null);
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Wybierz folder");
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());
        try {
            cf = new CmnFiles(selectedDirectory);
            setImage("");
        }catch (IOException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd pliku");
            alert.setHeaderText(null);
            alert.setContentText("Wystapił błąd przy tworzeniu pliku CSV. Proszę spróbowac ponownie później");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();
            Label label = new Label("Wyjatek:");
            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);
            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setHgrow(textArea, Priority.ALWAYS);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label,0,0);
            expContent.add(textArea,0,1);
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
        }

    }

    private boolean checkImages(){
        boolean retval = true;
        if(cf==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd danych");
            alert.setHeaderText(null);
            alert.setContentText("Proszę wczytać katalog ze zdjęciami!");
            alert.showAndWait();
            retval = false;
        }
        return retval;
    }

    private void setImage(String sign){
        File f = cf.getNextFile(sign);
        Image i = new Image(f.toURI().toString());
        BackgroundSize bs = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,false);
        BackgroundImage bi = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bs);
        Background b = new Background(bi);
        imagePanel.setBackground(b);
        BoundingBoxBufferControler bbbc=BoundingBoxBufferControler.getInstance();
        if(bbbc.isInBuffer(f.getPath())){
            ArrayList<BoundingBoxDataModel> list = bbbc.getBoundingBoxes(f.getPath());
            for(BoundingBoxDataModel bbdm : list){
                //TODO teraz sie drukuje na standardowe wyjście, tutaj ma się wczytywac boundingbox na gui
                System.out.println("File name: "+bbdm.getFileName());
                System.out.println("MinX: "+bbdm.getMinX());
                System.out.println("MinY: "+bbdm.getMinY());
                System.out.println("Width: "+bbdm.getWidth());
                System.out.println("Height: "+bbdm.getHeight());
            }
        }
    }
}
