package files;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CmnFiles {
    private ArrayList<File> images;
    private ArrayList<File> dictionaries;
    private ArrayList<String> extension;
    private String path;
    private File dictionary;
    private int id;

    private CmnFiles(){}
    public CmnFiles(String path) throws IOException{
        this.path=path;
        this.id=0;
        this.images=new ArrayList<>();
        fillExtension();
        loadImages(this.path);
    }

    public CmnFiles(File dictionary) throws IOException{
        this.path=dictionary.getAbsolutePath();
        this.dictionary=dictionary;
        this.id=0;
        this.images=new ArrayList<>();
        fillExtension();
        loadImages(this.path);
    }

    public File getNextFile(String sign){
        File retval;
        if("+".equals(sign)){
            id++;
        }else if("-".equals(sign)){
            id--;
        }
        if(id==images.size()){
            id=0;
            retval=images.get(id);
        }else if(id==-1)
        {
            id=images.size()-1;
            retval=images.get(id);
        }else{
            retval=images.get(id);
        }
        return retval;
    }

    public File getActualImage(){
        return this.images.get(this.id);
    }

    private void loadImages(String path)throws NullPointerException, IOException{
        CSVFiles.create(path);
        if(dictionary!=null){
            for (File fileEntry : dictionary.listFiles()) {
                addFileToCollection(fileEntry);
            }
        }else if(path!=null){
            File f = new File(path);
            for (File fileEntry : f.listFiles()) {
                addFileToCollection(fileEntry);
            }
        }
        if(dictionaries!=null) {
            Iterator<File> iterator = dictionaries.iterator();
            while(iterator.hasNext()) {
                File f = iterator.next();
                String uri = f.getAbsolutePath();
                dictionary=new File(uri);
                iterator.remove();
                loadImages(uri);
            }
        }

    }

    private boolean isImage(String path){
        boolean retval = false;
        String[] ex = path.split("\\.");
        if(extension.contains(ex[ex.length-1].toUpperCase())){
            retval=true;
        }
        return retval;
    }

    private void fillExtension(){
        extension = new ArrayList<>();
        extension.add("PNG");
    }

    private void addFileToCollection(File fileEntry){
        if(dictionaries==null){
            dictionaries=new ArrayList<>();
        }
        if (fileEntry.isDirectory()) {
            dictionaries.add(fileEntry);
        } else if(isImage(fileEntry.getAbsolutePath())){
            images.add(fileEntry);
        }
    }
}
