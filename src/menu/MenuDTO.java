package menu;

public class MenuDTO {
                               // DB 테이블명 : menu
    private int menuId;        /* 메뉴 고유번호 */
    private String menuName;   /* 메뉴 이름 */
    private int menuPrice;     /* 메뉴 가격 */
    private String category;   /* 카테고리 */
                               // 도시락, 보울, 사이드 메뉴 등

    public MenuDTO() {}

    public MenuDTO(int menuId, String menuName, int menuPrice, String category) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
    }

    @Override
    public String toString() {
        return "No. " + menuId
                + " | 메뉴명 : " + menuName
                + " | 가격 : " + menuPrice
                + " | 카테고리 : " + category;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
