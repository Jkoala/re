package cn.ljtnono.re.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 封装ftpClient的对象
 *
 * @author ljt
 * @version 1.1
 * @date 2019/11/24
 */
public class ReFtpClient {

    /** ftpClient 相关配置*/
    private ReFtpClientConfig reFtpClientConfig;

    /** ftpClient */
    private FTPClient ftpClient;

    private static Logger logger = LoggerFactory.getLogger(ReFtpClient.class);

    public ReFtpClient(ReFtpClientConfig reFtpClientConfig) throws IOException {
        if (reFtpClientConfig == null) {
            this.reFtpClientConfig = new ReFtpClientConfig();
        }
        init();
    }

    public ReFtpClient() throws IOException {
        this(null);
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
        if (ftpClient == null) {
            ftpClient = new FTPClient();
            connect();
        }
    }

    /**
     * 关闭ftp连接并且退出登陆
     *
     * @throws IOException 关闭失败时抛出IO异常
     */
    public void disConnect() throws IOException {
        if (isActive()) {
            ftpClient.disconnect();
            ftpClient.logout();
        }
    }

    /**
     * 连接ftp服务器并且登陆
     *
     * @return 连接成功返回true，连接失败返回false
     * @throws IOException 出现IOException 抛出
     */
    public boolean connect() throws IOException {
        if (ftpClient != null) {
            // 连接FTP服务器
            ftpClient.connect(reFtpClientConfig.getFtpServerAddr(), reFtpClientConfig.getFtpServerPort());
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftpClient.login(reFtpClientConfig.getFtpServerUser(), reFtpClientConfig.getFtpServerPassword());
            return true;
        }
        return false;
    }

    /**
     * 判断ftpClient是否是活跃的，这里判断的依据是该ftpClient是否连接到服务器
     * @return 如果是，返回true，如果不是返回false
     */
    public boolean isActive() {
        return ftpClient != null && ftpClient.isConnected();
    }

    /**
     * 关闭连接并销毁FtpClient对象，将ftpClient设置为null
     */
    public void destroy() throws IOException {
        disConnect();
        ftpClient = null;
    }


    /**
     * 切换到上传文件的目录下
     * @param filePath 上传文件的路径
     * @return 切换成功返回true，切换失败返回false
     * @throws IOException 当出现IO异常时抛出异常
     */
    private boolean changeWorkingDirectory(final String filePath) throws IOException {
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
                        return true;
                    } else {
                        ftpClient.changeWorkingDirectory(tempPath);
                    }
                }
            }
        }
        return false;
    }


    /**
     * 文件上传方法
     *
     * @param filePath 上传的文件路径 例如 /images/abc.png  /abc.doc
     * @param fileName 存储在文件服务器中的文件名 例如 abc.png
     * @param multipartFile SpringMVC文件上传组件
     * @return 上传成功返回图片的url，上传失败返回空字符串
     */
    public String uploadFile(final String filePath, final String fileName, final MultipartFile multipartFile) {

        return "";
    }


    /**
     * 文件上传方法
     *
     * @param filePath 上传的文件路径 例如 /images/abc.png  /abc.doc
     * @param fileName 存储在文件服务器中的文件名 例如 abc.png
     * @param b        上传的文件的字节数组
     * @return 上传成功返回true，上传失败返回false
     */
    public String uploadFile(final String filePath, final String fileName, final byte[] b) throws IOException {
        return uploadFile(filePath, fileName, new BufferedInputStream(new ByteArrayInputStream(b)));
    }


    /**
     * 文件上传方法
     *
     * @param filePath 上传的文件路径 例如 /images/abc.png  /abc.doc
     * @param fileName 存储在文件服务器中的文件名 例如 abc.png
     * @param input    上传的文件输入流
     * @throws IOException 出现IO异常时抛出
     * @throws RuntimeException 连接失败，参数检验错误时抛出
     * @return 上传成功返回图片的url地址，上传失败抛出异常
     */
    public String uploadFile(final String filePath, final String fileName, final InputStream input) throws IOException {
        // TODO 真正的上传函数，需要检查各种参数的合法性，合理处理异常
        if (!connect()) {
            throw new RuntimeException("ftp服务器连接失败");
        }
        //切换到上传目录
        if (changeWorkingDirectory(filePath)) {
            throw new RuntimeException("ftp服务器连接失败");
        }
        //设置上传文件的类型为二进制类型
        ftpClient.setFileType(reFtpClientConfig.getFileType());
        // 设置上传缓存
        ftpClient.setBufferSize(reFtpClientConfig.getBufferSize());
        // 设置以被动模式上传
        if (reFtpClientConfig.getPassiveMode()) {
            ftpClient.enterLocalPassiveMode();
        }
        // 设置编码格式为UTF8
        ftpClient.setControlEncoding(reFtpClientConfig.getControlEncoding());
        //上传文件
        if (!ftpClient.storeFile(fileName, input)) {
            throw new RuntimeException("上传失败");
        }
        if (input != null) {
            input.close();
        }
        disConnect();
        return "";
    }

    public static void main(String[] args) throws IOException {
        ReFtpClient reFtpClient = new ReFtpClient();
        reFtpClient.uploadFile("/re/", "失败.txt", new FileInputStream("C:\\Users\\GEEK\\Desktop\\失败.txt"));
    }
}
