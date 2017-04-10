package com.cmc.common.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Utils - 图片处理(支持JDK、GraphicsMagick、ImageMagick)
 * @author Hengtiansoft Team
 * @version 1.0_beta

public final class ImageUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * 处理类型
    private enum Type {

        /** 自动
        auto,

        /** jdk
        jdk,

        /**
         * GraphicsMagick
        graphicsMagick,

        /**
         * ImageMagick
        imageMagick
    }

    /** 处理类型
    private static Type type = Type.auto;

    /** GraphicsMagick程序路径
    private static String graphicsMagickPath;

    /** ImageMagick程序路径
    private static String imageMagickPath;

    /** 背景颜色
    private static final Color BACKGROUND_COLOR = Color.white;

    /** 目标图片品质(取值范围: 0 - 100)
    private static final int DEST_QUALITY = 88;

    static {
        if (graphicsMagickPath == null) {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.indexOf("windows") >= 0) {
                String pathVariable = System.getenv("Path");
                if (pathVariable != null) {
                    String[] paths = pathVariable.split(";");
                    for (String path : paths) {
                        File gmFile = new File(path.trim() + "/gm.exe");
                        File gmdisplayFile = new File(path.trim() + "/gmdisplay.exe");
                        if (gmFile.exists() && gmdisplayFile.exists()) {
                            graphicsMagickPath = path.trim();
                            break;
                        }
                    }
                }
            }
        }

        if (imageMagickPath == null) {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.indexOf("windows") >= 0) {
                String pathVariable = System.getenv("Path");
                if (pathVariable != null) {
                    String[] paths = pathVariable.split(";");
                    for (String path : paths) {
                        File convertFile = new File(path.trim() + "/convert.exe");
                        File compositeFile = new File(path.trim() + "/composite.exe");
                        if (convertFile.exists() && compositeFile.exists()) {
                            imageMagickPath = path.trim();
                            break;
                        }
                    }
                }
            }
        }

        if (type == Type.auto) {
            try {
                IMOperation operation = new IMOperation();
                operation.version();
                IdentifyCmd identifyCmd = new IdentifyCmd(true);
                if (graphicsMagickPath != null) {
                    identifyCmd.setSearchPath(graphicsMagickPath);
                }
                identifyCmd.run(operation);
                type = Type.graphicsMagick;
            } catch (Exception e1) {
                try {
                    IMOperation operation = new IMOperation();
                    operation.version();
                    IdentifyCmd identifyCmd = new IdentifyCmd(false);
                    identifyCmd.run(operation);
                    if (imageMagickPath != null) {
                        identifyCmd.setSearchPath(imageMagickPath);
                    }
                    type = Type.imageMagick;
                } catch (Exception e2) {
                    type = Type.jdk;
                }
            }
        }
    }

    /**
     * 不可实例化
    private ImageUtils() {
    }

    /**
     * 等比例图片缩放
     * @param srcFile 源文件
     * @param destFile 目标文件
     * @param destWidth 目标宽度
     * @param destHeight 目标高度
    public static void zoom(File srcFile, File destFile, int destWidth, int destHeight) {
        Assert.notNull(srcFile);
        Assert.notNull(destFile);
        Assert.state(destWidth > 0);
        Assert.state(destHeight > 0);
        if (type == Type.jdk) {
            Graphics2D graphics2D = null;
            ImageOutputStream imageOutputStream = null;
            ImageWriter imageWriter = null;
            try {
                // BufferedImage srcBufferedImage = ImageIO.read(srcFile);
                // 此方式读取会导致ICC信息不全的图片变红或变黑

                // 新的方式:获取图片后重新绘制并转成BufferedImage
                Image srcImg = Toolkit.getDefaultToolkit().getImage(srcFile.getPath());
                BufferedImage srcBufferedImage = toBufferedImage(srcImg);

                int srcWidth = srcBufferedImage.getWidth();
                int srcHeight = srcBufferedImage.getHeight();
                int width = destWidth;
                int height = destHeight;
                if (srcHeight >= srcWidth) {
                    width = (int) Math.round(((destHeight * 1.0 / srcHeight) * srcWidth));
                } else {
                    height = (int) Math.round(((destWidth * 1.0 / srcWidth) * srcHeight));
                }
                BufferedImage destBufferedImage = new BufferedImage(destWidth, destHeight,
                        BufferedImage.TYPE_INT_RGB);
                graphics2D = destBufferedImage.createGraphics();
                graphics2D.setBackground(BACKGROUND_COLOR);
                graphics2D.clearRect(0, 0, destWidth, destHeight);
                graphics2D.drawImage(
                        srcBufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                        (destWidth / 2) - (width / 2), (destHeight / 2) - (height / 2), null);

                imageOutputStream = ImageIO.createImageOutputStream(destFile);
                imageWriter = ImageIO.getImageWritersByFormatName(
                        FilenameUtils.getExtension(destFile.getName())).next();
                imageWriter.setOutput(imageOutputStream);
                ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
                imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                imageWriteParam.setCompressionQuality((float) (DEST_QUALITY / 100.0));
                imageWriter.write(null, new IIOImage(destBufferedImage, null, null),
                        imageWriteParam);
                imageOutputStream.flush();
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            } finally {
                if (graphics2D != null) {
                    graphics2D.dispose();
                }
                if (imageWriter != null) {
                    imageWriter.dispose();
                }
                if (imageOutputStream != null) {
                    try {
                        imageOutputStream.close();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage(), e);
                    }
                }
            }
        } else {
            IMOperation operation = new IMOperation();
            operation.thumbnail(destWidth, destHeight);
            operation.gravity("center");
            operation.background(toHexEncoding(BACKGROUND_COLOR));
            operation.extent(destWidth, destHeight);
            operation.quality((double) DEST_QUALITY);
            operation.addImage(srcFile.getPath());
            operation.addImage(destFile.getPath());
            if (type == Type.graphicsMagick) {
                ConvertCmd convertCmd = new ConvertCmd(true);
                if (graphicsMagickPath != null) {
                    convertCmd.setSearchPath(graphicsMagickPath);
                }
                try {
                    convertCmd.run(operation);
                } catch (IOException | InterruptedException | IM4JavaException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            } else {
                ConvertCmd convertCmd = new ConvertCmd(false);
                if (imageMagickPath != null) {
                    convertCmd.setSearchPath(imageMagickPath);
                }
                try {
                    convertCmd.run(operation);
                } catch (IOException | InterruptedException | IM4JavaException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * 初始化
    public static void initialize() {
    }

    /**
     * 转换颜色为十六进制代码
     * @param color 颜色
     * @return 十六进制代码
    private static String toHexEncoding(Color color) {
        String R, G, B;
        StringBuffer stringBuffer = new StringBuffer();
        R = Integer.toHexString(color.getRed());
        G = Integer.toHexString(color.getGreen());
        B = Integer.toHexString(color.getBlue());
        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;
        stringBuffer.append("#");
        stringBuffer.append(R);
        stringBuffer.append(G);
        stringBuffer.append(B);
        return stringBuffer.toString();
    }

    /**
     * image对象转化为bufferedImage
     * @param image
     * @return
    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Determining If an Image Has Transparent
        // Pixels
        // boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the
        // screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            /*
             * if (hasAlpha) { transparency = Transparency.BITMASK; }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null),
                    transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            // int type = BufferedImage.TYPE_3BYTE_BGR;//by wang
            /*
             * if (hasAlpha) { type = BufferedImage.TYPE_INT_ARGB; }
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }

    /**
     * 按指定高度 等比例缩放图片
     * @param imageFile
     * @param newPath
     * @param newWidth 新图的宽度
     * @throws IOException
    public static void zoomImageScale(File imageFile, File destFile, int newWidth, int newHeight)
            throws IOException {
        if (!imageFile.canRead())
            return;
        BufferedImage bufferedImage = ImageIO.read(imageFile);
        if (null == bufferedImage)
            return;

        int originalWidth = bufferedImage.getWidth();
        int originalHeight = bufferedImage.getHeight();
        double scale = (double) originalWidth / (double) newWidth; // 缩放的比例
        newHeight = (int) (originalHeight / scale);
        zoomImageUtils(imageFile, destFile, bufferedImage, newWidth, newHeight);
    }

    private static void zoomImageUtils(File imageFile, File destFile, BufferedImage bufferedImage,
            int width, int height) throws IOException {

        String suffix = StringUtils.substringAfterLast(imageFile.getName(), ".");

        // 处理 png 背景变黑的问题
        if (suffix != null
                && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase()
                        .endsWith("gif"))) {
            BufferedImage to = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height,
                    Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();

            ImageIO.write(to, suffix, destFile);
        } else {
            // 高质量压缩，其实对清晰度而言没有太多的帮助
            // BufferedImage tag = new BufferedImage(width, height,
            // BufferedImage.TYPE_INT_RGB);
            // tag.getGraphics().drawImage(bufferedImage, 0, 0, width, height,
            // null);
            //
            // FileOutputStream out = new FileOutputStream(newPath); // 将图片写入
            // newPath
            // JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            // JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            // jep.setQuality(1f, true); //压缩质量, 1 是最高值
            // encoder.encode(tag, jep);
            // out.close();

            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, destFile);
        }
    }

}
             */