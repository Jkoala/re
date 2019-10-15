package cn.ljtnono.re.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }

    public static boolean uploadFile() {
        return false;
    }



    private static void getConnection() {

    }

}
