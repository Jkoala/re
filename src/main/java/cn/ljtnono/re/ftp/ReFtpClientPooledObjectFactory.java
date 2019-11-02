package cn.ljtnono.re.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;

/**
 * ftpClient连接池工厂
 * @author ljt
 * @date 2019/11/2
 * @version 1.0
 */
public class ReFtpClientPooledObjectFactory implements PooledObjectFactory<ReFtpClient> {

    /**
     * Creates an instance that can be served by the pool and wrap it in a
     * {@link PooledObject} to be managed by the pool.
     *
     * @return a {@code PooledObject} wrapping an instance that can be served by the pool
     * @throws Exception if there is a problem creating a new instance,
     *                   this will be propagated to the code requesting an object.
     */
    @Override
    public PooledObject<ReFtpClient> makeObject() throws Exception {
        ReFtpClient reFtpClient = new ReFtpClient();
        return null;
    }

    /**
     * Destroys an instance no longer needed by the pool.
     * <p>
     * It is important for implementations of this method to be aware that there
     * is no guarantee about what state <code>obj</code> will be in and the
     * implementation should be prepared to handle unexpected errors.
     * </p>
     * <p>
     * Also, an implementation must take in to consideration that instances lost
     * to the garbage collector may never be destroyed.
     * </p>
     *
     * @param p a {@code PooledObject} wrapping the instance to be destroyed
     * @throws Exception should be avoided as it may be swallowed by
     *                   the pool implementation.
     * @see #validateObject
     * @see ObjectPool#invalidateObject
     */
    @Override
    public void destroyObject(PooledObject<ReFtpClient> p) throws Exception {
        p.getObject().destroy();
    }

    /**
     * Ensures that the instance is safe to be returned by the pool.
     *
     * @param p a {@code PooledObject} wrapping the instance to be validated
     * @return <code>false</code> if <code>obj</code> is not valid and should
     * be dropped from the pool, <code>true</code> otherwise.
     */
    @Override
    public boolean validateObject(PooledObject<ReFtpClient> p) {
        return false;
    }

    /**
     * Reinitializes an instance to be returned by the pool.
     *
     * @param p a {@code PooledObject} wrapping the instance to be activated
     * @throws Exception if there is a problem activating <code>obj</code>,
     *                   this exception may be swallowed by the pool.
     * @see #destroyObject
     */
    @Override
    public void activateObject(PooledObject<ReFtpClient> p) throws Exception {

    }

    /**
     * Uninitializes an instance to be returned to the idle object pool.
     *
     * @param p a {@code PooledObject} wrapping the instance to be passivated
     * @throws Exception if there is a problem passivating <code>obj</code>,
     *                   this exception may be swallowed by the pool.
     * @see #destroyObject
     */
    @Override
    public void passivateObject(PooledObject<ReFtpClient> p) throws Exception {
        ReFtpClient object = p.getObject();
    }
}
