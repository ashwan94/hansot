package menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuDAO {

    private MenuDAO() {}
    private static MenuDAO instance = new MenuDAO();
    public static MenuDAO getInstance(){return instance;}

    // 메뉴목록 조회(SELECT)
    public ArrayList<MenuDTO> getMenuList(Connection conn) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append(" SELECT                     ");
        query.append("          h.menu_id         ");
        query.append("   ,      h.menu_name       ");
        query.append("   ,      h.menu_price      ");
        query.append("   ,      h.category        ");
        query.append(" FROM                       ");
        query.append("          hansot_menu h     ");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        ResultSet rs = ps.executeQuery();

        ArrayList<MenuDTO> result = new ArrayList<>();

        while(rs.next()) {

            MenuDTO menu = new MenuDTO();
            menu.setMenuId(rs.getInt("menu_id"));
            menu.setMenuName(rs.getString("menu_name"));
            menu.setMenuPrice(rs.getInt("menu_price"));
            menu.setCategory(rs.getString("category"));

            result.add(menu);
        }

        rs.close();
        ps.close();

        return result;
    }



    // 메뉴 추가(INSERT)
    public int addMenu(Connection conn, MenuDTO menu) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append("  INSERT INTO                  ");
        query.append("          hansot_menu m   (    ");
        query.append("          m.menu_id            ");
        query.append("    ,     m.menu_name          ");
        query.append("    ,     m.menu_price         ");
        query.append("    ,     m.category           ");
        query.append("    )     VALUES         (     ");
        query.append("          menu_seq.NEXTVAL     ");
        query.append("    ,         ?                ");
        query.append("    ,         ?                ");
        query.append("    ,         ?                ");
        query.append("    )                          ");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;
        ps.setString(idx++, menu.getMenuName());
        ps.setInt(idx++, menu.getMenuPrice());
        ps.setString(idx++, menu.getCategory());

        int result = ps.executeUpdate();

        ps.close();

        return result;
    }


    // 메뉴 수정(UPDATE)
    public int changeMenu(Connection conn, MenuDTO menu) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append(" UPDATE                   ");
        query.append("       hansot_menu m      ");
        query.append(" SET                      ");
        query.append("       m.menu_name = ?  ");
        query.append("   ,    m.menu_price = ?  ");
        query.append("   ,    m.category = ?  ");
        query.append(" WHERE 1=1                ");
        query.append(" AND m.menu_id = ?  ");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;
        ps.setString(idx++, menu.getMenuName());
        ps.setInt(idx++, menu.getMenuPrice());
        ps.setString(idx++, menu.getCategory());
        ps.setInt(idx++, menu.getMenuId());

        int result = ps.executeUpdate();

        return result;
    }

    public int deleteMenu(Connection conn, MenuDTO menu) throws SQLException {

        StringBuffer query = new StringBuffer();
        query.append(" DELETE FROM              ");
        query.append("           hansot_menu    ");
        query.append(" WHERE 1=1                ");
        query.append(" AND menu_name = ?        ");

        PreparedStatement ps = conn.prepareStatement(query.toString());

        int idx = 1;
        ps.setString(idx++, menu.getMenuName());

        int result = ps.executeUpdate();
        ps.close();

        return result;
    }

}
