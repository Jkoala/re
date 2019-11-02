package cn.ljtnono.re.ftp;

import java.io.Serializable;

/**
 * ftpClient服务器相关配置
 * @author ljt
 * @date 2019/11/2
 * @version 1.0
 */
public class ReFtpClientConfig implements Serializable {

    private static final long serialVersionUID = 7050205319517825883L;
    /** 配置文件路径 */
    private String ftpConfigPath = "/web.properties";

    /** ftp服务器ip地址 */
    private String ftpServerAddr = "139.9.73.191";

    /** ftp服务器端口 */
    private int ftpServerPort = 21;

    /** ftp服务器用户名*/
    private String ftpServerUser = "ftpadmin";

    /** ftp服务器密码*/
    private String ftpServerPassword = "ljtLJT715336";

    /** ftp服务器基础目录*/
    private String ftpServerDirBase = "/home/ftpadmin";

    private ReFtpClientConfig(Builder builder) {
        setFtpConfigPath(builder.ftpConfigPath);
        setFtpServerAddr(builder.ftpServerAddr);
        setFtpServerPort(builder.ftpServerPort);
        setFtpServerUser(builder.ftpServerUser);
        setFtpServerPassword(builder.ftpServerPassword);
        setFtpServerDirBase(builder.ftpServerDirBase);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ReFtpClientConfig copy) {
        Builder builder = new Builder();
        builder.ftpConfigPath = copy.getFtpConfigPath();
        builder.ftpServerAddr = copy.getFtpServerAddr();
        builder.ftpServerPort = copy.getFtpServerPort();
        builder.ftpServerUser = copy.getFtpServerUser();
        builder.ftpServerPassword = copy.getFtpServerPassword();
        builder.ftpServerDirBase = copy.getFtpServerDirBase();
        return builder;
    }

    public String getFtpConfigPath() {
        return ftpConfigPath;
    }

    public void setFtpConfigPath(String ftpConfigPath) {
        this.ftpConfigPath = ftpConfigPath;
    }

    public String getFtpServerAddr() {
        return ftpServerAddr;
    }

    public void setFtpServerAddr(String ftpServerAddr) {
        this.ftpServerAddr = ftpServerAddr;
    }

    public int getFtpServerPort() {
        return ftpServerPort;
    }

    public void setFtpServerPort(int ftpServerPort) {
        this.ftpServerPort = ftpServerPort;
    }

    public String getFtpServerUser() {
        return ftpServerUser;
    }

    public void setFtpServerUser(String ftpServerUser) {
        this.ftpServerUser = ftpServerUser;
    }

    public String getFtpServerPassword() {
        return ftpServerPassword;
    }

    public void setFtpServerPassword(String ftpServerPassword) {
        this.ftpServerPassword = ftpServerPassword;
    }

    public String getFtpServerDirBase() {
        return ftpServerDirBase;
    }

    public void setFtpServerDirBase(String ftpServerDirBase) {
        this.ftpServerDirBase = ftpServerDirBase;
    }

    public ReFtpClientConfig() {

    }

    public static final class Builder {
        private String ftpConfigPath;
        private String ftpServerAddr;
        private int ftpServerPort;
        private String ftpServerUser;
        private String ftpServerPassword;
        private String ftpServerDirBase;

        private Builder() {
        }

        public Builder ftpConfigPath(String val) {
            ftpConfigPath = val;
            return this;
        }

        public Builder ftpServerAddr(String val) {
            ftpServerAddr = val;
            return this;
        }

        public Builder ftpServerPort(int val) {
            ftpServerPort = val;
            return this;
        }

        public Builder ftpServerUser(String val) {
            ftpServerUser = val;
            return this;
        }

        public Builder ftpServerPassword(String val) {
            ftpServerPassword = val;
            return this;
        }

        public Builder ftpServerDirBase(String val) {
            ftpServerDirBase = val;
            return this;
        }

        public ReFtpClientConfig build() {
            return new ReFtpClientConfig(this);
        }
    }
}
