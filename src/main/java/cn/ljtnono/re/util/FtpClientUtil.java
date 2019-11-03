package cn.ljtnono.re.util;

import cn.ljtnono.re.ftp.ReFtpClient;
import cn.ljtnono.re.ftp.ReFtpClientPool;
import cn.ljtnono.re.ftp.ReFtpClientPooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;

/**
 * 单例模式FtpClientUtil，文件服务器交互工具
 * @author ljt
 * @date 2019/10/15
 * @version 1.0
 */
@Component
public class FtpClientUtil {

    @Autowired
    private ReFtpClientPool reFtpClientPool;

    /** 实例 */
    private static FtpClientUtil instance = null;

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
                    instance =  new FtpClientUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 文件上传方法
     *
     * @param filePath 上传的文件路径 例如 /images/abc.png  /abc.doc
     * @param fileName 存储在文件服务器中的文件名 例如 abc.png
     * @param input    上传的文件输入流
     * @return 上传成功返回true，上传失败返回false
     * @see ReFtpClient#uploadFile(String, String, InputStream)
     */
    public boolean uploadFile(String filePath, String fileName, InputStream input) throws Exception {
        ReFtpClient reFtpClient = reFtpClientPool.borrowObject();
//        boolean result = reFtpClient.uploadFile(filePath, fileName, input);
//        reFtpClientPool.returnObject(reFtpClient);
        System.out.println(reFtpClient);
        return false;
    }

//    public boolean uploadFile(String filePath, String filename, byte[] b) {
//        return false;
//    }

    public static void main(String[] args) throws Exception {
        System.out.println(new FtpClientUtil().uploadFile(null, null, null));
    }
}
