package files;

import models.BoundingBoxDataModel;

import java.io.*;

public class CSVFiles {
    public static void create(String path)throws IOException{
        String[] tmp = path.split("\\\\");
        File f = new File(path+"\\"+tmp[tmp.length-1]+".CSV");
        if(!f.exists()){
            f.createNewFile();
            append(f, BoundingBoxDataModel.FIRST_LINE);
        }
    }

    public static void append(File file, String text)throws IOException{//TODO zmiana ciała metody i jej wywołań, tak, aby zapisywała kilka bounding boxów do jednego pliku
        boolean append = true;
        boolean autoFlush = true;
        String charset = "UTF-8";
        FileOutputStream fos = new FileOutputStream(file,append);
        OutputStreamWriter osw = new OutputStreamWriter(fos,charset);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw,autoFlush);
        pw.append(text+"\n");
        pw.flush();
        pw.close();
    }

}
