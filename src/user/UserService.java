package user;

import main.ConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    private UserService() {}
    private static UserService instance = new UserService();
    public static UserService getInstance() {return instance;}

    private ConnectionPool cp = ConnectionPool.getInstance();
    private UserDAO dao = UserDAO.getInstance();


    // 유저 목록 조회(SELECT)
    // conn 객체 대여
    public ArrayList<UserDTO> getUserList() {
        Connection conn = cp.getConnection();

        ArrayList<UserDTO> result = new ArrayList<>();

        try {
            result = dao.getUserList(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { // 반납
            cp.releaseConnection(conn);
        }
        return result;
    }

    // 유저 추가(INSERT)
    public int signUp(UserDTO member) {
        Connection conn = cp.getConnection();

        int result = 0;

        try {
            result = dao.signUp(conn, member);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }


}
