package com.zsw.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

@SpringBootTest
class ZswWebApplicationTests {

    @Test
    void contextLoads() throws IOException {
        Image image = ImageIO.read(new File("E:\\备份\\校区照片\\微信图片_20200426132453.jpg"));
        System.out.println(image.getSource());
    }

    public static void readTxt() {
        List<String[]> list = new LinkedList<String[]>();
        String[] outArray =null;
    try{
    String encoding = "GBK";
    File file = new File("d:"+File.separator+"test.txt");
    String lineinfo="";
    if(file.isFile()&&file.exists()) {
        InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
        BufferedReader bufferReader = new BufferedReader(read);
        while((lineinfo=bufferReader.readLine())!=null) {
            StringTokenizer stk = new StringTokenizer(lineinfo, ",") ;//被读取的文件的字段以","分隔
            String[] strArrty = new String[stk.countTokens()];
            int i=0;
            while(stk.hasMoreTokens()) {
                strArrty[i++]=stk.nextToken();
            }

            list.add(strArrty);
        }
        read.close();
    }
    }catch(Exception e) {
        System.out.println("读取文件内容出错");
         e.printStackTrace();
    }
    for(int i=0;i<list.size();i++) {
        outArray = (String[])list.get(i);
    }
    for(int j=0;j<outArray.length;j++) {
         System.out.print(outArray[j]+" ");
    }
}

    public static void main(String[] args) {
        readTxt();
   }
}


