package controllers.Backstage;


import models.product;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by PB6N0071 on 9/13/2017.
 */
public class Product extends MainBackstage {

    public static void prodlist(String productCategory, String productCategory_beauty) {
        productCategory_beauty = "A";
        productCategory = "1";
        if (productCategory_beauty == null || productCategory_beauty.trim().isEmpty()) {
            productCategory_beauty = "";
        }
        List<product> prodList = product.find("prod_cate=? and  beauty_desc=?", productCategory, productCategory_beauty).fetch();
        render(prodList, productCategory, productCategory_beauty);
    }

    public static void productupload() {
        render();
    }


    public static void submitPRODUCT(String prodCode, String prodDes, Integer prodPrice, String category, String productCategory_beauty, String prodImage) {
        int width = 363;    //width of the image
        int height = 240;
        BufferedImage image = null;
        File f = null;
        //read image
        try{
            f = new File(prodImage); //image file path
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            image = ImageIO.read(f);
            System.out.println("Reading complete.");
        }catch(IOException e){
            System.out.println("Error: "+e);
        }

        try{
            f = new File("C:\\Users\\pb6n0071\\Downloads\\chesca\\Output.jpg");  //output file path
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            ImageIO.write(image, "jpg", f);
            System.out.println("Writing complete.");
        }catch(IOException e){
            System.out.println("Error: "+e);
        }

        product prod = new product();
        prod.prod_desc = prodDes;
        prod.prod_cate = category;
        prod.beauty_desc = productCategory_beauty;
        prod.price = prodPrice;
        prod.file_name = prodImage;
        prod.save();
        redirect("/productupload");
    }

    private static void save(BufferedImage image, String ext) {
        String fileName = "savingAnImage";
        File file = new File(fileName + "." + ext);
        try {
            ImageIO.write(image, ext, file);  // ignore returned boolean
        } catch(IOException e) {
            System.out.println("Write error for " + file.getPath() +
                    ": " + e.getMessage());
        }
    }

    private static BufferedImage toBufferedImage(Image src) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }


}

