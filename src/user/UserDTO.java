package user;

public class UserDTO {
                                 // DB 테이블명 : hansot_user
    private int userId;          /* 사용자 고유번호 */
    private String userName;     /* 사용자명 */
    private int amountPaid;      /* 누적 결제금액 */

    public UserDTO() {
    }

    public UserDTO(int userId, String userName, int amountPaid) {
        this.userId = userId;
        this.userName = userName;
        this.amountPaid = amountPaid;
    }

    @Override
    public String toString() {
        return "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", amountPaid=" + amountPaid +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }
}
