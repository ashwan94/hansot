package menu;

import main.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MenuService {

    private MenuService() {}
    private static MenuService instance = new MenuService();
    public static MenuService getInstance(){return instance;}

    private ConnectionPool cp = ConnectionPool.getInstance();
    private MenuDAO dao = MenuDAO.getInstance();



    // 메뉴 목록 조회(SELECT)
    public ArrayList<MenuDTO> getMenuList() {
        Connection conn = cp.getConnection();

        ArrayList<MenuDTO> result = new ArrayList<>();

        try {
            result = dao.getMenuList(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }

    // 메뉴 추가(INSERT)
    public int addMenu(MenuDTO menu) {
        Connection conn = cp.getConnection();

        int result = 0;

        try {
            result = dao.addMenu(conn, menu);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }

    // 메뉴 수정(UPDATE)
    public int changeMenu(MenuDTO menu) {
        Connection conn = cp.getConnection();

        int result = 0;

        try {
            result = dao.changeMenu(conn, menu);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            cp.releaseConnection(conn);
        }
        return result;
    }

    public void deleteMenu(MenuDTO menu) {
        Connection conn = cp.getConnection();

        int result = 0;

        try {
            result = dao.deleteMenu(conn, menu);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cp.releaseConnection(conn);
        }

    }
}
