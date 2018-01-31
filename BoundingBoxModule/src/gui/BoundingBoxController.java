package gui;

import files.CmnFiles;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
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
        //TODO
    }

    @FXML
    public void read(){
        imagePanel.setBackground(null);
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        File defaultDirectory = new File("c:/");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog(new Stage());
        cf = new CmnFiles(selectedDirectory);
        setImage("");
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
        Image i = new Image(cf.getNextFile(sign).toURI().toString());
        BackgroundSize bs = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO,true,true,true,false);
        BackgroundImage bi = new BackgroundImage(i, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, bs);
        Background b = new Background(bi);
        imagePanel.setBackground(b);
    }
}
