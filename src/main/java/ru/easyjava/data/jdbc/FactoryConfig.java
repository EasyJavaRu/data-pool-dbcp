package ru.easyjava.data.jdbc;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import java.sql.SQLException;

/**
 * Simple example of c3p0 factory instantiation.
 */
public final class FactoryConfig {

    /**
     * Do not construct me.
     */
    private FactoryConfig() {
    }

    /**
     * Entry point.
     *
     * @param args Command line args. Not used.
     * @throws SQLException when something goes wrong.
     */
    public static void main(final String[] args) throws SQLException {
        DriverManagerConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(
                        "jdbc:postgresql://192.168.75.6/test",
                        "test",
                        "test");
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        PoolingDataSource<PoolableConnection> ds =
                new PoolingDataSource<>(connectionPool);

        try {
            PoolTest.runParallelQueries(ds);
        } catch (InterruptedException ex) {
            System.out.println("Execution failure: "
                    + ex.getMessage());
        }

    }

}
