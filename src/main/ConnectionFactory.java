package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private String url;
    private String id;
    private String pw;
    private int maxConn;

    // SingleTon
    private ConnectionFactory() {

        Properties prop = new Properties();

        InputStream input = getClass().getClassLoader()
                .getResourceAsStream("config/hansot-content-db.properties");


        try {
            prop.load(input);

            String driver = prop.getProperty("driver");

            Class.forName(driver);
            System.out.println("드라이버 로드 성공!");

            this.url = prop.getProperty("url");
            this.id = prop.getProperty("id");
            this.pw = prop.getProperty("pw");
            this.maxConn = Integer.parseInt(prop.getProperty("maxConn"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패!");
            System.exit(0);
        }

    }

    private static ConnectionFactory instance = new ConnectionFactory();
    public static ConnectionFactory getInstance() {
        return instance;
    }


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, id, pw);
    }

    public int getMaxConn() {
        return maxConn;
    }
}
