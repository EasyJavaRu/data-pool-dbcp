package ru.easyjava.data.jdbc;


import org.apache.commons.dbcp2.BasicDataSource;

import java.beans.PropertyVetoException;

import static ru.easyjava.data.jdbc.PoolTest.runParallelQueries;

/**
 * Simple example of c3p0 direct instantiation.
 */
public final class SimpleConfig {

    /**
     * Do not construct me.
     */
    private SimpleConfig() {
    }

    /**
     * Entry point.
     *
     * @param args Command line args. Not used.
     * @throws PropertyVetoException when something goes wrong.
     */
    public static void main(final String[] args) throws PropertyVetoException {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://127.0.0.1/test");
        ds.setUsername("test");
        ds.setPassword("test");

        try {
            runParallelQueries(ds);
        } catch (InterruptedException ex) {
            System.out.println("Execution failure: "
                    + ex.getMessage());

        }

    }

}
