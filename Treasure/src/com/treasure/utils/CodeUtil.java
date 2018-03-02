package com.treasure.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


public class CodeUtil {
	
	private static final String CHARSET = "utf-8";  
    private static final String FORMAT_NAME = "JPG";  
    // 二维码尺寸  
    private static final int QRCODE_SIZE = 300;  
    // LOGO宽度  
    private static final int WIDTH = 60;  
    // LOGO高度  
    private static final int HEIGHT = 60;  
  
    private static List<String> fileName=new ArrayList<>();
    private static BufferedImage createImage(String content, String imgPath,  
            boolean needCompress) throws Exception {  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);  
        hints.put(EncodeHintType.MARGIN, 1);  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
                BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);  
        int width = bitMatrix.getWidth();  
        int height = bitMatrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height,  
                BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000  
                        : 0xFFFFFFFF);  
            }  
        }  
        if (imgPath == null || "".equals(imgPath)) {  
            return image;  
        }  
        // 插入图片  
        CodeUtil.insertImage(image, imgPath, needCompress);  
        return image;  
    }  
  
    /** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param imgPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */  
    private static void insertImage(BufferedImage source, String imgPath,  
            boolean needCompress) throws Exception {  
        File file = new File(imgPath);  
        if (!file.exists()) {  
            System.err.println(""+imgPath+"   该文件不存在！");  
            return;  
        }  
        Image src = ImageIO.read(new File(imgPath));  
        int width = src.getWidth(null);  
        int height = src.getHeight(null);  
        if (needCompress) { // 压缩LOGO  
            if (width > WIDTH) {  
                width = WIDTH;  
            }
            if (height > HEIGHT) {  
                height = HEIGHT;  
            }  
            Image image = src.getScaledInstance(width, height,  
                    Image.SCALE_SMOOTH);  
            BufferedImage tag = new BufferedImage(width, height,  
                    BufferedImage.TYPE_INT_RGB);  
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
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param destPath 
     *            存放目录 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String imgPath, String destPath,  
            boolean needCompress) throws Exception {  
        BufferedImage image = CodeUtil.createImage(content, imgPath,  
                needCompress);  
        mkdirs(destPath);
        String file = new Random().nextInt(99999999)+".jpg";  
        
        fileName.add(file);
       
        //System.out.println(fileName.get(0).contains("144"));
        ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));  
    }  
  
    /** 
     * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常) 
     * @author lanyuan 
     * Email: mmm333zzz520@163.com 
     * @date 2013-12-11 上午10:16:36 
     * @param destPath 存放目录 
     */  
    public static void mkdirs(String destPath) {  
        File file =new File(destPath);      
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)  
        if (!file.exists() && !file.isDirectory()) {  
            file.mkdirs();  
        }  
    }  
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static void encode(String content, String imgPath, String destPath)  
            throws Exception {  
    	CodeUtil.encode(content, imgPath, destPath, false);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String destPath,  
            boolean needCompress) throws Exception {  
    	CodeUtil.encode(content, null, destPath, needCompress);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param destPath 
     *            存储地址 
     * @throws Exception 
     */  
    public static void encode(String content, String destPath) throws Exception {  
    	CodeUtil.encode(content, null, destPath, false);  
    }  
  
    /** 
     * 生成二维码(内嵌LOGO) 
     *  
     * @param content 
     *            内容 
     * @param imgPath 
     *            LOGO地址 
     * @param output 
     *            输出流 
     * @param needCompress 
     *            是否压缩LOGO 
     * @throws Exception 
     */  
    public static void encode(String content, String imgPath,  
            OutputStream output, boolean needCompress) throws Exception {  
        BufferedImage image = CodeUtil.createImage(content, imgPath,  
                needCompress);  
        ImageIO.write(image, FORMAT_NAME, output);  
    }  
  
    /** 
     * 生成二维码 
     *  
     * @param content 
     *            内容 
     * @param output 
     *            输出流 
     * @throws Exception 
     */  
    public static void encode(String content, OutputStream output)  
            throws Exception {  
    	CodeUtil.encode(content, null, output, false);  
    }  
  
    /** 
     * 解析二维码 
     *  
     * @param file 
     *            二维码图片 
     * @return 
     * @throws Exception 
     *//*  
    public static String decode(File file) throws Exception {  
        BufferedImage image;  
        image = ImageIO.read(file);  
        if (image == null) {  
            return null;  
        }  
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(  
                image);  
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
        Result result;  
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();  
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);  
        result = new MultiFormatReader().decode(bitmap, hints);  
        String resultStr = result.getText();  
        return resultStr;  
    } */ 
  
    /** 
     * 解析二维码 
     *  
     * @param path 
     *            二维码图片地址 
     * @return 
     * @throws Exception 
     */  
    /*public static String decode(String path) throws Exception {  
        return CodeUtil.decode(new File(path));  
    } */ 
  
    public static void main(String[] args) throws Exception {  
        String text = "http://www.baidu.com";
        //CodeUtil.encode(text, "http://www.wuasset.com:1088/member//147bfb96d64c44ac82b8276e3fd15a86.jpg", "E:/temps/", true);  
        CodeUtil.encode(text+"zcm=144","imgs/"+144);
    }  
    
    public static String getFileName(){
		return fileName.get(0);    	
    }
	 /*private static final int BLACK = 0xFF000000;
	   private static final int WHITE = 0xFFFFFFFF;
	 
	   public static BufferedImage toBufferedImage(BitMatrix matrix) {
	     int width = matrix.getWidth();
	     int height = matrix.getHeight();
	     BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	     for (int x = 0; x < width; x++) {
	       for (int y = 0; y < height; y++) {
	         image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
	       }
	     }
	     return image;
	   }
	   
	   *//** 
	    * 创建QR二维码图片 
	    *//*  
	   private BufferedImage createQRCodeBitmap() {  
	       // 用于设置QR二维码参数  
	       Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();  
	       // 设置QR二维码的纠错级别——这里选择最高H级别  
	       qrParam.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
	       // 设置编码方式  
	       qrParam.put(EncodeHintType.CHARACTER_SET, "UTF-8");  
	     
	       // 设定二维码里面的内容，这里我采用我微博的地址  
	       String content = "sinaweibo://userinfo?uid=2568190010";  
	     
	       // 生成QR二维码数据——这里只是得到一个由true和false组成的数组  
	       // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数  
	       try {  
	           BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  
	                   BarcodeFormat.QR_CODE, 400, 400, qrParam);  
	     
	           // 开始利用二维码数据创建Bitmap图片，分别设为黑白两色  
	           int w = bitMatrix.getWidth();  
	           int h = bitMatrix.getHeight();  
	           int[] data = new int[w * h];  
	     
	           for (int y = 0; y < h; y++) {  
	               for (int x = 0; x < w; x++) {  
	                   if (bitMatrix.get(x, y))  
	                       data[y * w + x] = 0xff000000;// 黑色  
	                   else  
	                       data[y * w + x] = -1;// -1 相当于0xffffffff 白色  
	               }  
	           }  
	     
	           BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		  	   return image;
	           
	           // 创建一张bitmap图片，采用最高的效果显示  
		  	 //Bit bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);  
	           // 将上面的二维码颜色数组传入，生成图片颜色  
	           //bitmap.setPixels(data, 0, w, 0, 0, w, h);  
	          // return bitmap;  
	       } catch (Exception e) {  
	           e.printStackTrace();  
	       }  
	       return null;  
	   }  
	   
	   
	   *//** 
	     * 生成二维码(QRCode)图片 
	     * @param content 二维码图片的内容
	     * @param imgPath 生成二维码图片完整的路径
	     * @param ccbpath  二维码图片中间的logo路径
	     *//*  
	    public static int createQRCode(String content, String imgPath,String ccbPath) {  
	        try {  
	        	QRCode qrcodeHandler = new QRCode();  
	        
	            //设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
	            qrcodeHandler.setQrcodeErrorCorrect('M');  
	            //N代表数字,A代表字符a-Z,B代表其他字符
	            qrcodeHandler.setQrcodeEncodeMode('B'); 
	            // 设置设置二维码版本，取值范围1-40，值越大尺寸越大，可存储的信息越大  
	            qrcodeHandler.setQrcodeVersion(8); 
	  
	            byte[] contentBytes = content.getBytes("gb2312");  
	            BufferedImage bufImg = new BufferedImage(149, 149, BufferedImage.TYPE_INT_RGB);  
	            Graphics2D gs = bufImg.createGraphics();  
	  
	            gs.setBackground(Color.WHITE);  
	            gs.clearRect(0, 0, 149, 149);  
	  
	            // 设定图像颜色 > BLACK  
	            gs.setColor(Color.blue);  
	  
	            // 设置偏移量 不设置可能导致解析出错  
	            int pixoff = 2;  
	            // 输出内容 > 二维码  
	            if (contentBytes.length > 0 && contentBytes.length <150) {  
	                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
	                for (int i = 0; i < codeOut.length; i++) {  
	                    for (int j = 0; j < codeOut.length; j++) {  
	                        if (codeOut[j][i]) {  
	                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
	                        }  
	                    }  
	                }  
	            } else {  
	                System.err.println("QRCode content bytes length = "  
	                        + contentBytes.length + " not in [ 0,125]. ");  
	                return -1;
	            }  
	            Image img = ImageIO.read(new File(ccbPath));//实例化一个Image对象。
	            gs.drawImage(img, 50, 50, null);
	            gs.dispose();  
	            bufImg.flush();  
	  
	            // 生成二维码QRCode图片  
	            File imgFile = new File(imgPath);  
	            ImageIO.write(bufImg, "png", imgFile);  
	  
	        } catch (Exception e) 
	        {  
	            e.printStackTrace();  
	            return -100;
	        }  
	        
	        return 0;
	    } */ 
}
