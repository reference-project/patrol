package com.slk.patrol.framework.util;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZXingUtil {

    private static final String CHARSET = "utf-8";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int LOGO_WIDTH = 60;
    // LOGO高度
    private static final int LOGO_HEIGHT = 60;


    public static void main(String[] args) {
        String context = "http://192.168.1.110/index.html/33/34/42";
        System.out.println(generateZXing(context));
       /*String code = "789654123";

        int unicode = Integer.valueOf(getUnicode(code.charAt(0)))+code.length();
        String c = String.valueOf((char) unicode);
        for (int i = 1; i < code.length(); i++) {
            int i1 = Integer.valueOf(getUnicode(code.charAt(i))) + Integer.valueOf(getUnicode(code.charAt(i - 1)));
            c += String.valueOf((char) i1);

        }*/

     /*   System.out.println(escape("@"));
        System.out.println();*/

    }




    public static String getUnicode(char c){
        String returnUniCode=null;
        returnUniCode=String.valueOf((int)c);
        return returnUniCode;
    }


    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }


    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }





    /**
     *
     * 生成二维码
     *
     * Create by song-xl on 2018-04-23 10:17:11
     */
    public static String generateZXing(String context){
        String logoPath = "https://slk-save.oss-cn-beijing.aliyuncs.com/patrol/logo/klwy.png";
        BufferedImage image = createImage(context, logoPath, true);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image,"jpg",os);
        } catch (IOException e) {
            System.out.println("ZXing------生成二维码失败");
        }
        String url = FileUtil.setFileInputStream("QRcode", new ByteArrayInputStream(os.toByteArray()));
        System.out.println(url);
        return url;
    }



    /**
     *
     * 生成二维码(内嵌LOGO)
     * 二维码文件名随机，文件名可能会有重复
     * Create by song-xl on 2018-04-23 10:16:17
     */
    private static BufferedImage createImage(String content, String logoPath, boolean needCompress){
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,hints);
        } catch (WriterException e) {
            System.out.println("ZXing------生成二维码(内嵌LOGO)失败");
        }
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        // 插入图片
        insertImage(image, logoPath, needCompress);
        return image;
    }



    /**
     *
     * 插入LOGO
     * Create by song-xl on 2018-04-23 10:16:44
     */
    private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) {
        Image src = null;
        try {
            URL url= new URL(logoPath);
            InputStream is = url.openConnection().getInputStream();
            src = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("ZXing------LOGO插入失败");
        }
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > LOGO_WIDTH) {
                width = LOGO_WIDTH;
            }
            if (height > LOGO_HEIGHT) {
                height = LOGO_HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }



}
