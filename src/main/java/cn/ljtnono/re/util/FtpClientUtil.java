package cn.ljtnono.re.util;

import cn.ljtnono.re.ftp.ReFtpClientObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;

/**
 * 单例模式FtpClientUtil，文件服务器交互工具
 * @author ljt
 * @date 2019/10/15
 * @version 1.0
 */
public class FtpClientUtil {

    @Autowired
    private ReFtpClientObjectPool reFtpClientObjectPool;

    /** 实例 */
    private static FtpClientUtil instance = null;

    /** 日志记录器 */
    private Logger logger = LoggerFactory.getLogger(FtpClientUtil.class);

    /** 单例模式 */
    private FtpClientUtil(){}

    /**
     * 单例模式获取实例
     * @return 返回工具类实例
     */
    public static FtpClientUtil getInstance() {
        if (instance == null) {
            synchronized (FtpClientUtil.class) {
                if (instance == null) {
                    return new FtpClientUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 文件上传方法
     * @param filePath
     * @param filename 存储在文件服务器中的文件名
     * @param input
     * @return 上传成功返回true，上传失败返回false
     */
    public boolean uploadFile(String filePath, String filename, InputStream input) throws Exception {
        return reFtpClientObjectPool.borrowObject().uploadFile(filePath, filename, input);
    }

    public boolean uploadFile(String filePath, String filename, byte b) {
        return false;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(FtpClientUtil.getInstance().uploadFile( "/re/images/", "20180801234443645.png", new BufferedInputStream(new FileInputStream("C:\\Users\\ljt\\Desktop\\20180801234443645.png"))));
    }
}
