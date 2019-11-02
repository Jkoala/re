package cn.ljtnono.re.ftp;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * FtpClient对象池
 * @author ljt
 * @date 2019/11/2
 * @version 1.0
 */
public class ReFtpClientObjectPool extends GenericObjectPool<ReFtpClient> {
    /**
     * Creates a new <code>GenericObjectPool</code> using defaults from
     * {@link GenericObjectPoolConfig}.
     *
     * @param factory The object factory to be used to create object instances
     *                used by this pool
     */
    public ReFtpClientObjectPool(PooledObjectFactory<ReFtpClient> factory) {
        super(factory);
    }

    /**
     * Creates a new <code>GenericObjectPool</code> using a specific
     * configuration.
     *
     * @param factory The object factory to be used to create object instances
     *                used by this pool
     * @param config  The configuration to use for this pool instance. The
     *                configuration is used by value. Subsequent changes to
     *                the configuration object will not be reflected in the
     */
    public ReFtpClientObjectPool(PooledObjectFactory<ReFtpClient> factory, GenericObjectPoolConfig<ReFtpClient> config) {
        super(factory, config);
    }

    /**
     * Creates a new <code>GenericObjectPool</code> that tracks and destroys
     * objects that are checked out, but never returned to the pool.
     *
     * @param factory         The object factory to be used to create object instances
     *                        used by this pool
     * @param config          The base pool configuration to use for this pool instance.
     *                        The configuration is used by value. Subsequent changes to
     *                        the configuration object will not be reflected in the
     *                        pool.
     * @param abandonedConfig Configuration for abandoned object identification
     */
    public ReFtpClientObjectPool(PooledObjectFactory<ReFtpClient> factory, GenericObjectPoolConfig<ReFtpClient> config, AbandonedConfig abandonedConfig) {
        super(factory, config, abandonedConfig);
    }
}
