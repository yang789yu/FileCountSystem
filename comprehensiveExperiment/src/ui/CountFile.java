package ui;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class CountFile {
    private File folder;
    private long totalSizeOfFiles;
    private long totalLineOfFiles;
    private long totalBlankLineOfFile;
    private long numberOfFiles;
    private String folderDetails;
    private StringBuilder sb=new StringBuilder();

    public CountFile(File folder){
        this.folder=folder;
    }

    public void Count(){
        totalSizeOfFiles=0;
        totalBlankLineOfFile =0;
        totalLineOfFiles=0;
        numberOfFiles=0;
        parse(folder);
        sb.append("Total:");
        sb.append('\n');
        sb.append(String.format("\t\t%,d Java Files\n",getNumberOfFiles()));
        sb.append(String.format("\t\t%,d lines in files\n",getTotalLineOfFiles()));
        sb.append(String.format("\t\t%,d blank lines\n",getTotalBlankLineOfFile()));
        sb.append(String.format("\t\t%,d bytes\n",getTotalSizeOfFiles()));
        this.folderDetails=sb.toString();
    }

    // 使用内部类进行文件夹与文件的排序
    public static class comparator implements Comparator<File>{
        @Override
        public int compare(File file1, File file2) {
            // 首先检查是否为目录，目录排在前面
            if (file1.isDirectory() && file2.isFile()) {
                return -1; // file1排在file2之前
            } else if (file1.isFile() && file2.isDirectory()) {
                return 1; // file1排在file2之后
            }

            // 如果都是目录或都是文件，则按名称升序排序
            return file1.getName().compareTo(file2.getName());
        }
    }
    comparator dfcomparator= new comparator();

    //解析主目录
    public void parse(File folder){
        File[] items=folder.listFiles();
        //设置断点测试
        assert items != null;
        // 文件夹与文件进行排序
        Arrays.sort(items,dfcomparator);
        sb.append('[');
        sb.append(folder.getAbsoluteFile());
        sb.append("] Result:");
//        sb.append(folder.getName());
        sb.append('\n');
        sb.append('\n');
        sb.append("Files detail:");
        sb.append('\n');
        for (File item:items){
            if(item.isDirectory()){
                parseFolder(item,0);
            }
            else if(item.isFile() && item.getName().toLowerCase().endsWith(".java")){
                parseFile(item,0);
            }
        }
    }


    // 解析子目录
    public void parseFolder(File folder,int flag){
        File[] items=folder.listFiles();
        for (int i = 0; i < flag; i++) {
            sb.append('\t');
        }
        flag++;
        sb.append("+");
        sb.append(folder.getName());
        sb.append('\n');
        // 文件夹与文件进行排序
        Arrays.sort(items,dfcomparator);
        for (File item:items){
            if(item.isDirectory()){
                parseFolder(item,flag);
            }
            else if(item.isFile() && item.getName().toLowerCase().endsWith(".java")){
                parseFile(item,flag);
            }
        }
    }

    //解析文件
    public void parseFile(File file,int flag){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            this.numberOfFiles++;
            int lineCount = 0 ;
            int emptyLineCount = 0;
            int byteCount = 0;
            String line;
            while((line = reader.readLine())!=null){
                lineCount++;
                byteCount += line.getBytes().length;
                this.totalLineOfFiles++;
                this.totalSizeOfFiles+=line.getBytes().length;
                if (line.trim().isEmpty()){
                    emptyLineCount++;
                    this.totalBlankLineOfFile++;
                }
            }
            for (int i = 0; i < flag; i++) {
                sb.append('\t');
            }
            sb.append('-');
            sb.append(String.format("%-30s\tTotal: %8d, Blank: %8d,\t %8d Bytes",file.getName(),lineCount,emptyLineCount,byteCount));
            sb.append('\n');
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public CountFile() {

    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }


    public long getTotalSizeOfFiles() {
        return totalSizeOfFiles;
    }

    public long getTotalLineOfFiles() {
        return totalLineOfFiles;
    }

    public long getTotalBlankLineOfFile() {
        return totalBlankLineOfFile;
    }

    public long getNumberOfFiles(){
        return numberOfFiles;
    }

    public String getFolderDetails(){
        return folderDetails;
    }

    public StringBuilder getSb() {
        return sb;
    }

}
