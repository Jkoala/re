package cn.ljtnono.re.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * 单例模式FtpClientUtil，文件服务器交互工具
 * @author ljt
 * @date 2019/10/15
 * @version 1.0
 */
public class FtpClientUtil {

    /** 配置文件路径 */
    public static final String FTP_CONFIG_PATH = "/web.properties";

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


    public boolean uploadFile() {

        return false;
    }


    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            // 连接FTP服务器
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.setBufferSize(1024 * 1024 * 10);
            ftp.enterLocalPassiveMode();
            ftp.setControlEncoding("UTF-8");
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ignored) {
                }
            }
        }
        return result;
    }
    public static void main(String[] args) throws FileNotFoundException {
        uploadFile(FTP_SERVER_ADDR, FTP_SERVER_PORT,FTP_SERVER_USER, FTP_SERVER_PASSWORD, "/home/ftpadmin/root_element/", "/images","mm_facetoface_collect_qrcode_1572110198965.png", new BufferedInputStream(new FileInputStream("C:\\Users\\ljt\\Desktop\\mm_facetoface_collect_qrcode_1572110198965.png")));
    }
}
