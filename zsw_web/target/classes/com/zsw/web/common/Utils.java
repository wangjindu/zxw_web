package com.zsw.web.common;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.*;
import java.util.*;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class Utils {

    private static final Encoder encoder = Base64.getEncoder();

    /**
     * 将图片转换成base64进制,未压缩
     *
     *
     */
    public String getImageBinary(String filePath) {
        return getString(filePath);
    }

    private static String getString(String filePath) {
        File f = new File(filePath);
        BufferedImage bi;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bi = ImageIO.read(f);
            ImageIO.write(bi, "png", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();
        String result = encoder.encodeToString(bytes).trim();
        if(result == null) {
            System.out.println("获取图片为空，图片转为base64进制失败");
        }
        return result;
    }

    /**
     * 将图片转换成base64进制,压缩后10
     *
     * @return
     */
    public String resizeImageTo10K(String filepath) throws IOException {
        String base64Img = imageToBase64(filepath);
        try {
            BufferedImage src = base64String2BufferedImage(base64Img);
            BufferedImage output = Thumbnails.of(src).size(src.getWidth()/3, src.getHeight()/3).asBufferedImage();
            String base64 = outputToBase64(output);
            if (base64.length() - base64.length() / 8 * 2 > 30000) {
                output = Thumbnails.of(output).scale(1/(base64.length()/30000)).asBufferedImage();
                base64 = outputToBase64(output);
            }
            return base64;
        } catch (Exception e) {
            return base64Img;
        }
    }

    /**
     * 将图片转换成base64进制,压缩后50
     *
     * @return
     */
    public String resizeImageTo50K(String filepath) throws IOException {
        String base64Img = imageToBase64(filepath);
        try {
            BufferedImage src = base64String2BufferedImage(base64Img);
            BufferedImage output = Thumbnails.of(src).size(src.getWidth()/3, src.getHeight()/3).asBufferedImage();
            String base64 = outputToBase64(output);
            if (base64.length() - base64.length() / 8 * 2 > 50000) {
                output = Thumbnails.of(output).scale(1/(base64.length()/50000)).asBufferedImage();
                base64 = outputToBase64(output);
            }
            return base64;
        } catch (Exception e) {
            return base64Img;
        }
    }

    /**
     * 将图片转换成base64进制,压缩后8
     *
     * @return
     */
    public String resizeImageTo8K(String filepath) throws IOException {
        String base64Img = imageToBase64(filepath);
        try {
            BufferedImage src = base64String2BufferedImage(base64Img);
            BufferedImage output = Thumbnails.of(src).size(src.getWidth()/3, src.getHeight()/3).asBufferedImage();
            String base64 = outputToBase64(output);
            if (base64.length() - base64.length() / 8 * 2 > 30000) {
                output = Thumbnails.of(output).scale(1/(base64.length()/30000)).asBufferedImage();
                base64 = outputToBase64(output);
            }
            return base64;
        } catch (Exception e) {
            return base64Img;
        }
    }
    public static BufferedImage base64String2BufferedImage(String base64string) throws IOException {
        BufferedImage image = null;

            InputStream stream = BaseToInputStream(base64string);
            image = ImageIO.read(stream);

        return image;
    }
    private static InputStream BaseToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return stream;
    }
    public static String imageToBase64(String filePath) throws IOException {
        return getString(filePath);
    }
    public static String outputToBase64(BufferedImage bufferedImage) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", baos);
        return new String(encoder.encodeToString(baos.toByteArray()).trim());
    }

    /**
     * 缩放图片(压缩图片质量，改变图片尺寸)
     * 若原图宽度小于新宽度，则宽度不变！
     * @param originalFile 原图片路径地址
     * @param resizedFile 压缩后输出路径地址
     * @param maxWidth 最大宽度
     * @param maxHeight 最大高度
     * @param newWidth 新的宽度
     * @param quality 图片质量参数 0.7f 相当于70%质量
     */
    private static final int maxWidth = 1200;
    private static final int maxHeight = 2500;
    private static final float quality = 0.8f;
    public static void imageResize(File originalFile, File resizedFile) throws IOException {

        if (quality > 1) {
            throw new IllegalArgumentException(
                    "图片质量需设置在0.1-1范围");
        }

        ImageIcon ii = new ImageIcon(originalFile.getCanonicalPath());
        Image i = ii.getImage();
        Image resizedImage = null;

        int iWidth = i.getWidth(null);
        int iHeight = i.getHeight(null);

        int newWidth = maxWidth;
        if(iWidth < maxWidth){
            newWidth = iWidth;
        }


        if (iWidth >= iHeight) {
            resizedImage = i.getScaledInstance(newWidth, (newWidth * iHeight)
                    / iWidth, Image.SCALE_SMOOTH);
        }



        int newHeight = maxHeight;
        if(iHeight < maxHeight){
            newHeight = iHeight;
        }

        if(resizedImage==null && iHeight >= iWidth){
            resizedImage = i.getScaledInstance((newHeight * iWidth) / iHeight,
                    newHeight, Image.SCALE_SMOOTH);
        }

        //此代码确保加载图像中的所有像素
        assert resizedImage != null;
        Image temp = new ImageIcon(resizedImage).getImage();

        //创建缓冲图像
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);

        //将图像复制到缓冲图像
        Graphics g = bufferedImage.createGraphics();

        //清除背景并绘制图像。
        g.setColor(Color.white);
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));
        g.drawImage(temp, 0, 0, null);
        g.dispose();


        float softenFactor = 0.05f;
        float[] softenArray = { 0, softenFactor, 0, softenFactor,
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };
        Kernel kernel = new Kernel(3, 3, softenArray);
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);

        //将jpeg写入文件
        FileOutputStream out = new FileOutputStream(resizedFile);

        //将图像编码为jpeg数据流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

        JPEGEncodeParam param = encoder
                .getDefaultJPEGEncodeParam(bufferedImage);

        param.setQuality(quality, true);

        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
    }

    public Map<String, String> requestToMap(HttpServletRequest request) {
        @SuppressWarnings("rawtypes")
        Enumeration e = request.getParameterNames();
        Object object = null;
        String paramKey = "";
        Map<String, String> maps = new HashMap<String, String>();
        while (e.hasMoreElements()) {
            object = (Object) e.nextElement();
            paramKey = object.toString();

            maps.put(object.toString(), request.getParameter(paramKey));
        }
        return maps;
    }

    public String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 是否是手机
     *
     * @param value
     */
    @SuppressWarnings("static-access")
    public  boolean isMobile(String value) {
        Pattern p = null;// 正则表达式
        Matcher m = null;// 操作符表达式
        boolean b = false;
        p = p.compile("^(0|86|17951)?(13[0-9]|15[012356789]|17[0-9]|18[0-9]|19[0-9]|14[57])[0-9]{8}$");
        m = p.matcher(value);
        b = m.matches();
        return !b;
    }
}
