package ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadTxtFile {
    private String filePath;
    public ReadTxtFile(String filePath){
        this.filePath=filePath;
    }
    private final StringBuilder builder=new StringBuilder();

    public ReadTxtFile() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void ReadFile(){
        File file=new File(filePath);
        try (BufferedReader br=new BufferedReader(new FileReader(file))) {
            String line;
            while ((line=br.readLine())!=null){
                System.out.println(line);
                builder.append(line);
                builder.append('\n');
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getResult(){
        return builder.toString();
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
