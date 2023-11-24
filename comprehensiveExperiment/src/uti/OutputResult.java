package uti;

import ui.CountFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputResult {

    public static final int WRITE_TRUE = 1;
    public static final int WRITE_FALSE = 2;
    public static final int MAKE_FILE_FALSE = 3;

    public static int output(File folder, CountFile countfile) {
        String projectPath=System.getProperty("user.dir");
        String resultFolderName=projectPath+File.separator+"result";
        File resultFolder = new File(resultFolderName);
        if(!resultFolder.exists()){
            resultFolder.mkdir();
        }
        String folderPath=resultFolderName+File.separator+folder.getName()+".txt";
        File txtFile=new File(folderPath);
        if(!txtFile.exists()){
            try {
                txtFile.createNewFile();
//                try {
//                    FileWriter writer=new FileWriter(folderPath);
//                    writer.write(countfile.getFolderDetails());
//                    writer.close();
//                    return 1;
//                } catch (IOException e){
//                    System.out.printf("内容写入时发生错误：%s\n",e.getMessage());
//                    return 2;
//                }
            } catch (IOException e) {
                System.out.printf("创建文件失败：\n",e.getMessage());
                return MAKE_FILE_FALSE;
                //return 3;
            }
        }
        try {
            FileWriter writer=new FileWriter(folderPath);
            writer.write(countfile.getFolderDetails());
            writer.close();
            //return 1;
            return WRITE_TRUE;
        } catch (IOException e){
            System.out.printf("内容写入时发生错误：%s\n",e.getMessage());
            //return 2;
            return WRITE_FALSE;
        }
//        else {
//            System.out.println("该文件已经存在");
//
//            return 4;
//        }
    }
}
