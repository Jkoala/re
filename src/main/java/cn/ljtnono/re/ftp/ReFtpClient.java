package cn.ljtnono.re.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * 封装ftpClient的对象
 * @author ljt
 * @date 2019/11/2
 * @version 1.0
 */
public class ReFtpClient {

    /** ftp服务器相关配置类 */
    private ReFtpClientConfig reFtpClientConfig;

    /** ftp客户端 */
    private FTPClient ftpClient;

    /** 日志记录 */
    private Logger logger = LoggerFactory.getLogger(ReFtpClient.class);

    public ReFtpClient(ReFtpClientConfig reFtpClientConfig) throws IOException {
        if (reFtpClientConfig == null) {
            // 使用默认配置
            this.reFtpClientConfig = new ReFtpClientConfig();
        }
        this.reFtpClientConfig = reFtpClientConfig;
    }

    public ReFtpClient() throws IOException {
        // 使用默认配置
        this.reFtpClientConfig = new ReFtpClientConfig();
    }

    public ReFtpClientConfig getReFtpClientConfig() {
        return reFtpClientConfig;
    }

    public void setReFtpClientConfig(ReFtpClientConfig reFtpClientConfig) {
        this.reFtpClientConfig = reFtpClientConfig;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    /**
     * 初始化FTPClient相关内容
     */
    public void init() throws IOException {
        // 第一次初始化
        if (ftpClient == null) {
            ftpClient = new FTPClient();
//            connect();
        }
    }

    /**
     * 关闭ftp连接
     * @throws IOException 关闭失败时抛出IO异常
     */
    public void disConnect(){
        if (ftpClient != null) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                // TODO 处理异常
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接ftp服务器
     * @return 连接成功返回true，连接失败返回false
     * @throws IOException 出现IOException 抛出
     */
    public boolean connect() throws IOException {
        if (ftpClient != null) {
            ftpClient.connect(reFtpClientConfig.getFtpServerAddr(), reFtpClientConfig.getFtpServerPort());
            ftpClient.login(reFtpClientConfig.getFtpServerUser(), reFtpClientConfig.getFtpServerPassword());
            if (!FTPReply.isPositiveCompletion(ftpClient.getReply())) {
                ftpClient.disconnect();
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isActive() {

        return false;
    }

    /**
     * 关闭连接并销毁FtpClient对象，将ftpClient设置为null
     */
    public void destroy() {
        disConnect();
        ftpClient = null;
    }



    /**
     * 文件上传方法
     * @param filePath 上传的文件路径 例如 /images/abc.png  /abc.doc
     * @param fileName 存储在文件服务器中的文件名 例如 abc.png
     * @param b 上传的文件的字节数组
     * @return 上传成功返回true，上传失败返回false
     */
    public boolean uploadFile(String filePath, String fileName, byte[] b) {

        return false;
    }


    /**
     * 文件上传方法
     * @param filePath 上传的文件路径 例如 /images/abc.png  /abc.doc
     * @param fileName 存储在文件服务器中的文件名 例如 abc.png
     * @param input 上传的文件输入流
     * @return 上传成功返回true，上传失败返回false
     */
    public boolean uploadFile(String filePath, String fileName, InputStream input) {
        try {
            int reply;
            // 连接FTP服务器
            ftpClient.connect(reFtpClientConfig.getFtpServerAddr(), reFtpClientConfig.getFtpServerPort());
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftpClient.login(reFtpClientConfig.getFtpServerUser(), reFtpClientConfig.getFtpServerPassword());
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return false;
            }
            //切换到上传目录
            if (!ftpClient.changeWorkingDirectory(reFtpClientConfig.getFtpServerDirBase() + filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = reFtpClientConfig.getFtpServerDirBase();
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;
                    if (!ftpClient.changeWorkingDirectory(tempPath)) {
                        if (!ftpClient.makeDirectory(tempPath)) {
                            return false;
                        } else {
                            ftpClient.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(1024 * 1024 * 10);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding("UTF-8");
            //上传文件
            if (!ftpClient.storeFile(fileName, input)) {
                return false;
            }
            input.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            disConnect();
        }
        return true;
    }



    public InputStream download() {

        return null;
    }
}
