package cn.ljtnono.re.ftp;

import cn.ljtnono.re.enumeration.GlobalVariableEnum;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Optional;

/**
 * 封装ftpClient的对象
 * @author ljt
 * @version 1.2
 * @date 2019/11/26
 */
public class ReFtpClient {

    /** ftpClient 相关配置*/
    private ReFtpClientConfig reFtpClientConfig;

    /** ftpClient */
    private FTPClient ftpClient;

    /** 打印日志 */
    private static Logger logger = LoggerFactory.getLogger(ReFtpClient.class);

    public ReFtpClient(ReFtpClientConfig reFtpClientConfig) throws IOException {
        if (reFtpClientConfig == null) {
            this.reFtpClientConfig = new ReFtpClientConfig();
        }
        // 构造完成之后进行初始化
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
     * 初始化FTPClient
     */
    public void init() throws IOException {
        if (ftpClient == null) {
            ftpClient = new FTPClient();
//            connect();
        }
    }

    /**
     * 关闭ftp连接并且退出登陆
     * 先退出登陆后断开连接
     * @throws IOException 关闭失败时抛出IO异常
     */
    public void disConnect() throws IOException {
        if (isActive()) {
            logger.info("退出登陆=====>user = " + reFtpClientConfig.getFtpServerUser() + " password = " + reFtpClientConfig.getFtpServerPassword());
            ftpClient.logout();
            logger.info("断开连接");
            ftpClient.disconnect();
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
            logger.info("连接ftp服务器=====>" + reFtpClientConfig.getFtpServerAddr());
            // 连接FTP服务器
            ftpClient.connect(reFtpClientConfig.getFtpServerAddr(), reFtpClientConfig.getFtpServerPort());
            logger.info("登陆ftp服务器=====>user = " + reFtpClientConfig.getFtpServerUser() + " password = " + reFtpClientConfig.getFtpServerPassword());
            ftpClient.login(reFtpClientConfig.getFtpServerUser(), reFtpClientConfig.getFtpServerPassword());
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                logger.info("连接ftp服务器失败，断开连接");
                ftpClient.disconnect();
                return false;
            }
            return true;
        } else {
            logger.info("ftpClient未初始化");
            return false;
        }
    }

    /**
     * 判断ftpClient是否是活跃的，这里判断的依据是该ftpClient是否连接到服务器
     * @return 如果是，返回true，如果不是返回false
     */
    public boolean isActive() {
        return ftpClient != null && ftpClient.isConnected();
    }

    /**
     * 切换到上传文件的目录下
     * @param filePath 上传文件的路径
     * @throws IOException 当出现IO异常时抛出异常
     */
    private void changeWorkingDirectory(final String filePath) throws IOException {
        logger.info("切换目录=====>" + reFtpClientConfig.getFtpServerDirBase() + filePath);
        if (!ftpClient.changeWorkingDirectory(reFtpClientConfig.getFtpServerDirBase() + filePath)) {
            //如果目录不存在创建目录
            logger.info("目录不存在，进行创建=====>" + reFtpClientConfig.getFtpServerDirBase() + filePath);
            String[] dirs = filePath.split("/");
            String tempPath = reFtpClientConfig.getFtpServerDirBase();
            logger.info("循环创建=====>" + reFtpClientConfig.getFtpServerDirBase() + filePath);
            for (String dir : dirs) {
                if (null == dir || "".equals(dir)) {
                    continue;
                }
                tempPath += "/" + dir;
                if (!ftpClient.changeWorkingDirectory(tempPath)) {
                    if (!ftpClient.makeDirectory(tempPath)) {
                        logger.info("循环创建=====>" + reFtpClientConfig.getFtpServerDirBase() + filePath + "成功");
                        break;
                    } else {
                        ftpClient.changeWorkingDirectory(tempPath);
                    }
                }
            }
        }
    }

    /**
     * 检查上传文件的参数的正确性
     * @param filePath 上传文件的
     * @param fileName 文件名（存储在ftp服务器上的文件名）
     * @param input 文件的输入流
     * @throws RuntimeException 参数为不正确时抛出该异常
     * 校验成功返回true，校验失败抛出异常
     */
    private void validateUploadFileParameters(String filePath, String fileName, final InputStream input) {
        Optional.ofNullable(filePath).orElseThrow(() -> new RuntimeException("filePath is not null"));
        Optional.ofNullable(input).orElseThrow(() -> new RuntimeException("fileName is not null"));
        // TODO 检查文件名是否正确

    }

    /**
     * @param filePath 上传的文件路径 例如 /images/abc/  /re/images/abc
     * @param fileName 上传的完整文件名（包括文件的后缀）
     * @return 资源的完整url访问路径
     */
    private String contractPath(final String filePath, final String fileName) {
        return GlobalVariableEnum.RE_FTP_SAVE_PREFIX.getValue().toString() + filePath + fileName;
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
     * @param multipartFile SpringMVC文件上传组件
     * @return 上传成功返回图片的url，上传失败返回空字符串
     */
    public String uploadFile(final String filePath, final String fileName, final MultipartFile multipartFile) {
        String result;
        return "";
    }

    /**
     * 基础文件上传方法
     *
     * @param filePath 上传的文件路径 例如 /images/  /abc
     * @param fileName 存储在文件服务器中的文件名 例如 abc.png
     * @param input    上传的文件输入流
     * @throws IOException 当出现IO异常时抛出
     * @return 上传成功返回图片的url地址，上传失败返回null
     */
    public String uploadFile(final String filePath, final String fileName, final InputStream input) throws IOException {
        try {
            validateUploadFileParameters(filePath, fileName, input);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        connect();
        //切换到上传目录 这里只允许传到re目录下
        changeWorkingDirectory(filePath);
        ftpClient.setFileType(reFtpClientConfig.getFileType());
        ftpClient.setBufferSize(reFtpClientConfig.getBufferSize());
        if (reFtpClientConfig.getPassiveMode()) {
            ftpClient.enterLocalPassiveMode();
        }
        // 设置编码格式为UTF8
        ftpClient.setControlEncoding(reFtpClientConfig.getControlEncoding());
        try {
            //上传文件
            ftpClient.storeFile(fileName, input);
            disConnect();
        } catch (IOException e) {
            logger.error("上传文件" + fileName + "=====>" + "失败");
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                logger.error("关闭输入流失败");
                e.printStackTrace();
            }
        }
        return contractPath(filePath, fileName);
    }


    // 测试
    public static void main(String[] args) throws IOException {
        ReFtpClient reFtpClient = new ReFtpClient();
        reFtpClient.uploadFile("/", "失败.txt", new FileInputStream("C:\\Users\\GEEK\\Desktop\\失败.txt"));
    }
}
