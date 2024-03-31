package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    private UserDAO() {}
    private static UserDAO instance = new UserDAO();
    public static UserDAO getInstance() {
        return instance;
    }

    // 사용자 목록 조회(SELECT)
    public ArrayList<UserDTO> getUserList(Connection conn) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append(" SELECT                       ");
        query.append("          u.user_id           ");
        query.append("    ,     u.user_name         ");
        query.append("    ,     u.amount_paid       ");
        query.append(" FROM                         ");
        query.append("          hansot_user u       ");

        PreparedStatement ps = conn.prepareStatement(query.toString());
        ResultSet rs = ps.executeQuery();

        ArrayList<UserDTO> result = new ArrayList<>();

        while (rs.next()) {
            UserDTO user = new UserDTO();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setAmountPaid(rs.getInt("amount_paid"));

            result.add(user);
        }

        rs.close();
        ps.close();

        return result;
    }

    // 사용자 추가(INSERT)
    public int signUp(Connection conn, UserDTO member) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("      INSERT INTO                 ");
        query.append("          hansot_user u    (      ");
        query.append("          u.user_id               ");
        query.append("    ,     u.user_name             ");
        query.append("    ,     u.amount_paid           ");
        query.append("    )     values          (       ");
        query.append("          user_seq.NEXTVAL        "); // PK Sequence
        query.append("    ,        ?                    ");
        query.append("    ,        ?                    ");
        query.append("    )                             ");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        // 미완성 data 채워주기
        int idx = 1;
        ps.setString(idx++, member.getUserName());
        ps.setInt(idx++, member.getAmountPaid());

        int result = ps.executeUpdate();

        ps.close();

        return result;


    }


}
