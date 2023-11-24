import ui.CountFile;
import ui.ReadTxtFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        while (true){
            System.out.println("----------MENU----------");
            System.out.println("1.分析目录中的源程序文件");
            System.out.println("2.查看分析结果");
            System.out.println("0.退出程序");
            System.out.println("------------------------");
            System.out.print("请选择：");
            String choice=sc.nextLine();
            switch (choice){
                case "1":analyzeFile();break;
                case "2"://checkResult();break;
                case "0":return;
                default:
                    System.out.println("输入错误");break;
            }
        }

    }

    public static void analyzeFile(){
        String projectPath=System.getProperty("user.dir");
        System.out.println("当前目录名："+projectPath);
        System.out.println("请输入目录名称：");
        String folderName=sc.nextLine();
        File folder = new File(folderName);
        if(folder.isDirectory()){
            CountFile countfile=new CountFile(folder);
            countfile.Count();
            //outputResult(folder,countfile);
            //System.out.println(countfile.getFolderDetails());
        }
        else {
            System.out.printf("错误，%s不是一个目录名或不存在！\n",folderName);
        }
    }
/*
    public static void outputResult(File folder,CountFile countfile) {
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
                try {
                    FileWriter writer=new FileWriter(folderPath);
                    writer.write(countfile.getFolderDetails());
                    writer.close();
                } catch (IOException e){
                    System.out.printf("内容写入时发生错误：%s\n",e.getMessage());
                }
            } catch (IOException e) {
                System.out.printf("创建文件失败：\n",e.getMessage());
            }
        }
        else {
            System.out.println("该文件已经存在");
        }
    }
*/
    /*
    public static void checkResult(){
        String projectPath=System.getProperty("user.dir");
        String resultFolderName=projectPath+File.separator+"result";
        File resultFile=new File(resultFolderName);
        if (resultFile.isDirectory()&&resultFile.exists()){
            File[] files=resultFile.listFiles((dir,name) -> name .toLowerCase().endsWith(".txt"));
            if(files != null && files.length>0){
                int index=1;
                for (int i = 0; i < files.length; i++) {
                    System.out.println(index+"."+files[i].getName());
                    index++;
                }
                System.out.print("请选择要查看的结果文件（0表示放弃）：");
                int choice=Integer.parseInt(sc.nextLine());
                if(choice==0){
                    System.out.println("结束");
                }else if (choice >= 1 && choice <index){
//                    String filePath=resultFolderName+File.separator+files[choice-1];
//                    System.out.println(filePath);
//                    System.out.println(files[choice-1]);
                    ReadTxtFile readTxtFile=new ReadTxtFile(files[choice-1].toString());
                    readTxtFile.ReadFile();
                }else {
                    System.out.println("输入编号错误");
                }
            }else {
                System.out.println("还没有分析结果！");
            }
        }
        /*

    }
        */

}