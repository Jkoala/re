package cn.ljtnono.re.ftp;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * ftpClient连接池工厂
 * @author ljt
 * @date 2019/11/2
 * @version 1.0
 */
public class ReFtpClientPooledObjectFactory extends BasePooledObjectFactory<ReFtpClient> {

    private static Logger logger = LoggerFactory.getLogger(ReFtpClientPooledObjectFactory.class);


    @Override
    public ReFtpClient create() throws Exception {
        // 创建ReFtpClient对象
        ReFtpClient reFtpClient = new ReFtpClient();
        // 初始化
        reFtpClient.init();
        return reFtpClient;
    }

    @Override
    public PooledObject<ReFtpClient> wrap(ReFtpClient obj) {
        // 包装ReFtpClient对象
        return new DefaultPooledObject<ReFtpClient>(obj);
    }


    @Override
    public void destroyObject(PooledObject<ReFtpClient> p) throws Exception {
        p.getObject().destroy();
        super.destroyObject(p);
    }


    @Override
    public boolean validateObject(PooledObject<ReFtpClient> p) {
        ReFtpClient reFtpClient = p.getObject();
        // 验证对象
        return reFtpClient.isActive();
    }


    @Override
    public void activateObject(PooledObject<ReFtpClient> p) throws Exception {
        super.activateObject(p);
    }


    @Override
    public void passivateObject(PooledObject<ReFtpClient> p) throws Exception {
        super.passivateObject(p);
    }
}
