package imageManageTool;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.text.*;

public class Image {
    public File file;
    // public BufferedImage image;
    private String path;
    private String name;
    private String format;
    public long size;
    public long date;
    private String resolution;

    public String color;
    public String camera;
    public String location;

    public Image() {
    }

    public Image(File image) {
        this.size = image.length();
        this.path = image.getName();
        this.date = image.lastModified();

        this.file = image;
        // this.resolution = im.getHeight() + "*" + im.getWidth();

    }

    public String getName() {
        this.name = path.substring(0, path.lastIndexOf("."));
        return name;
    }

    public String getType() {
        this.format = path.substring(path.lastIndexOf("."), path.length());
        return format;
    }

    public String getResolution() {
        BufferedImage images;
        try { // handle with IOexception
            images = ImageIO.read(file);
            this.resolution = images.getHeight() + "*" + images.getWidth();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        return resolution;
    }

}
