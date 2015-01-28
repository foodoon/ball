package guda.ball.biz.helper;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by foodoon on 2015/1/24.
 */
public class ImageUtil {

    public static void resize(File originalFile, File resizedFile,
                              int width) throws IOException {

        int height = width;
        double widthRatio = 0.0;
        double heightRatio = 0.0;
        if(!originalFile.exists()){
            throw new RuntimeException("file not found");
        }
        BufferedImage bi = ImageIO.read(originalFile);
        heightRatio = (double)width / bi.getHeight();
        widthRatio = (double)height / bi.getWidth();
        AffineTransformOp op = new AffineTransformOp(AffineTransform
                .getScaleInstance(widthRatio, heightRatio), null);
        Image item = op.filter(bi, null);
        try {
            ImageIO.write((BufferedImage) item, "png", resizedFile);
        } catch (Exception ex) {
            throw new RuntimeException(" ImageIo.write error in CreatThum.: "
                    + ex.getMessage());
        }
    }

    public static void main(String[] args){
        try {
            ImageUtil.resize(new File("D:\\dev-git\\ball\\webapps\\upload\\20150124\\111.jpg"),new File("D:\\dev-git\\ball\\webapps\\upload\\20150124\\b5b85eb2-38b1-462d-81d0-bda428639972.png"),180);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
