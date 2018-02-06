package files;

import models.BoundingBoxDataModel;

import java.io.*;
import java.util.ArrayList;

public class CSVFiles {
    static void create(String path)throws IOException{
        String[] tmp = path.split("\\\\");
        File f = new File(path+"\\"+tmp[tmp.length-1]+".CSV");
        if(!f.exists()){
            f.createNewFile();
            ArrayList<String> list = new ArrayList<>();
            list.add(BoundingBoxDataModel.FIRST_LINE);
            append(f, list);
        }
    }

    public static void append(File file, ArrayList<String> text)throws IOException{
        boolean append = true;
        boolean autoFlush = true;
        String charset = "UTF-8";
        FileOutputStream fos = new FileOutputStream(file,append);
        OutputStreamWriter osw = new OutputStreamWriter(fos,charset);
        BufferedWriter bw = new BufferedWriter(osw);
        PrintWriter pw = new PrintWriter(bw,autoFlush);
        for(String s:text) {
            pw.append(s + "\n");
        }
        pw.flush();
        pw.close();
    }

    static ArrayList<BoundingBoxDataModel> readCSVFile(String path) throws IOException{
        ArrayList<BoundingBoxDataModel> retval = new ArrayList<>();
        String[] tmp = path.split("\\\\");
        FileReader file = new FileReader(path+"\\"+tmp[tmp.length-1]+".CSV");
        BufferedReader br = new BufferedReader(file);
        String line;
        boolean first = true;
        while((line = br.readLine())!=null){
            if(first){
                first=false;
                continue;
            }

            String[] tmpLine = line.split(";");
            String fileName = tmpLine[0];
            double minX = Double.parseDouble(tmpLine[1]);
            double minY = Double.parseDouble(tmpLine[2]);
            double width = Double.parseDouble(tmpLine[3]);
            double height = Double.parseDouble(tmpLine[4]);
            BoundingBoxDataModel bbdm = new BoundingBoxDataModel(fileName,minX,minY,width,height);
            retval.add(bbdm);
        }
        br.close();
        file.close();
        return retval;
    }

}
