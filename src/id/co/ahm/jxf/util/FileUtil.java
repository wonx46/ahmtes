package id.co.ahm.jxf.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author bayutridewanto
 */
public class FileUtil {

    private final String fullPath;
    private final char pathSeparator, extensionSeparator;

    public FileUtil(String str, char sep, char ext) {
        fullPath = str;
        pathSeparator = sep;
        extensionSeparator = ext;
    }

    /* get file extension */
    public String extension() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        return fullPath.substring(dot + 1);
    }

    /* get filename without extension */
    public String filename() {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(sep + 1, dot);
    }

    /* get path directory */
    public String path() {
        int sep = fullPath.lastIndexOf(pathSeparator);
        return fullPath.substring(0, sep);
    }

    /* get fullname */
    public String fullname() {
        File file = new File(fullPath);
        return file.getName();
    }

    /**
     *
     * @param filePath : file directory
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static InputStream getStreamFromFile(String filePath) throws FileNotFoundException, IOException {
        InputStream input = new FileInputStream(filePath);
        int data = input.read();
        while (data != -1) {
            data = input.read();
        }
        return input;
    }

    /**
     *
     * @param filePath : file directory
     * @return
     * @throws java.io.FileNotFoundException
     */
    public static OutputStream getOutputStreamFromFile(String filePath) throws FileNotFoundException, IOException {
        OutputStream output = new FileOutputStream(filePath);
        return output;
    }

    /**
     * get config-jxf-owo.properties
     * @param propParam
     * @return
     */
    public static String getJxfOwoProperties(String propParam) {
        InputStream input = null;
        Properties prop = new Properties();
        try {
            String pathConfig = System.getProperty("jxconfig");
            input = new FileInputStream(pathConfig);
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return prop.getProperty(propParam);
    }
    
    
    public static byte[] FileToByte(File file) throws IOException {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream(file);
            int read = 0;
            while ((read = ios.read(buffer)) != -1) {
                ous.write(buffer, 0, read);
            }
        } finally {
            try {
                if (ous != null) {
                    ous.close();
                }
            } catch (Exception e) {
            }
            try {
                if (ios != null) {
                    ios.close();
                }
            } catch (Exception e) {
            }  
        }
        return ous.toByteArray();
    }  

}
