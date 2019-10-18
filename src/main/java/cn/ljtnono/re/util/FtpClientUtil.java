package cn.ljtnono.re.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * @author ljt
 * @date 2019/10/15
 * @version 1.0
 */
public class FtpClientUtil {


    /**
     * 禁止实例化
     */
    private FtpClientUtil(){}

    /** 配置文件路径 */
    public static final String FTP_CONFIG_PATH = "/ftpconfig.properties";

    /** 日志记录器 */
    private static Logger logger = LoggerFactory.getLogger(FtpClientUtil.class);

    /** ftp服务器ip地址 */
    public static String FTP_SERVER_ADDR = "";

    /** ftp服务器端口 */
    public static int FTP_SERVER_PORT = 0;

    /** ftp服务器用户名*/
    public static String FTP_SERVER_USER = "";

    /** ftp服务器密码*/
    public static String FTP_SERVER_PASSWORD = "";

    /** ftp服务器基础目录*/
    public static String FTP_SERVER_DIR_BASE = "";


    static {
        //TODO 读取properties文件，并且将这些值都设置好
        Properties properties = new Properties();
        try {
            properties.load(FtpClientUtil.class.getResourceAsStream(FTP_CONFIG_PATH));
            FTP_SERVER_ADDR = properties.getProperty("ftp.server.addr");
            FTP_SERVER_PORT = Integer.parseInt(properties.getProperty("ftp.server.port"));
            FTP_SERVER_USER = properties.getProperty("ftp.server.user");
            FTP_SERVER_PASSWORD = properties.getProperty("ftp.server.password");
            FTP_SERVER_DIR_BASE = properties.getProperty("ftp.server.dir.base");
        } catch (IOException e) {
            logger.info("ftp配置文件路径错误");
        }
    }

    public static boolean uploadFile(String ftpFileName) {
        FTPClient ftpClient = new FTPClient();
        InputStream in;
        try {
            in = new BufferedInputStream(new FileInputStream("C:\\Users\\GEEK\\Desktop\\tf.doc"));
            ftpClient.connect(FTP_SERVER_ADDR, FTP_SERVER_PORT);
            boolean login = ftpClient.login(FTP_SERVER_USER, FTP_SERVER_PASSWORD);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
            boolean b = ftpClient.storeFile(ftpFileName, in);
            return b;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    private static void getConnection() {

    }

    public static void main(String[] args) {
        System.out.println(uploadFile("tc.doc"));
    }
}