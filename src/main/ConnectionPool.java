package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {

    private Vector<Connection> pool = new Vector<>();

    // 싱글톤 적용
    private ConnectionPool() {

        ConnectionFactory cf = ConnectionFactory.getInstance();

        for(int i = 0; i < cf.getMaxConn(); i++) {

            try {
                pool.add(cf.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static ConnectionPool instance = new ConnectionPool(){};
    public static ConnectionPool getInstance(){return instance;}


    // Connection 대여
    public synchronized Connection getConnection() {
        ConnectionFactory cf = ConnectionFactory.getInstance();

        if(pool.size() == 0) {

            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Connection conn = pool.get(0);
        pool.remove(0);
        return conn;
    }


    // Connection 반환
    public synchronized void releaseConnection(Connection conn) {
        ConnectionFactory cf = ConnectionFactory.getInstance();

        pool.add(conn);

        notify();

    }
}