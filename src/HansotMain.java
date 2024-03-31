import menu.MenuDAO;
import menu.MenuDTO;
import menu.MenuService;
import user.UserDTO;
import user.UserService;

import java.util.ArrayList;
import java.util.Scanner;

public class HansotMain {

    public static void main(String[] args) throws InterruptedException {


        try {
            Class.forName("main.ConnectionFactory");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Scanner scan = new Scanner(System.in);
        MenuService menuService = MenuService.getInstance();
        UserService userService = UserService.getInstance();

        while (true) {
            System.out.println("========== 한솥 ==========");
            System.out.println("========== 공지사항 ==========");
            System.out.println("========== 이달의 메뉴 ==========");
            System.out.println("1. 메뉴 목록 | 2. 회원정보 | 3. 종료");
            int command = Integer.parseInt(scan.nextLine());

            if (command == 1) {
                ArrayList<MenuDTO> menuList = menuService.getMenuList();

                // 전체 메뉴 조회
                System.out.println("1. 전체 메뉴 | 2. 메뉴 추가 | 3. 메뉴 수정 | 4. 메뉴 삭제");
                System.out.println(">>> ");

                int menuSel = Integer.parseInt(scan.nextLine());

                if (menuSel == 1) {
                    for (int i = 0; i < menuList.size(); i++) {
                        System.out.println(menuList.get(i));
                    }

                } else if (menuSel == 2) {
                    // 메뉴 추가
                    System.out.println("추가할 메뉴명");
                    System.out.println(">>> ");
                    String name = scan.nextLine();

                    System.out.println("가격");
                    System.out.println(">>> ");
                    int price = Integer.parseInt(scan.nextLine());

                    System.out.println("카테고리");
                    System.out.println(">>> ");
                    String category = scan.nextLine();

                    MenuDTO menu = new MenuDTO(0, name, price, category);
                    int result = menuService.addMenu(menu);

                    for (int i = 0; i < menuList.size(); i++) {
                        System.out.println(menuList.size());
                    }

                } else if (menuSel == 3) {
                    // 메뉴 수정
                    // 수정할 메뉴명을 입력해서 for문으로 저장된 List index 와 비교해
                    // 실제 존재하면 수정, 존재하지 않으면 에러 메세지 출력

                    int result = 0;
                    while (result == 0) {
                        System.out.println("변경할 메뉴 번호를 입력하세요.");
                        System.out.println(">>> ");
                        int number = Integer.parseInt(scan.nextLine());

                        for (int i = 0; i < menuList.size(); i++) {
                            if (number == menuList.get(i).getMenuId()) {

                                System.out.println(menuList.get(i));
                                // 조회된 존재하는 menu 정보

                                System.out.println("새로운 메뉴 이름");
                                System.out.println(">>> ");
                                String name = scan.nextLine();

                                System.out.println("새로운 가격");
                                System.out.println(">>> ");
                                int price = Integer.parseInt(scan.nextLine());

                                System.out.println("새로운 카테고리명");
                                System.out.println(">>> ");
                                String category = scan.nextLine();

                                MenuDTO menu = new MenuDTO(number, name, price, category);
                                System.out.println("반영된 data 정보");

                                result = menuService.changeMenu(menu);
                                break;

                            } else {
                                System.out.println("존재하지 않는 정보입니다. 다시 입력해주세요");
                                break;
                            }
                        }
                    }

                } else if (menuSel == 4) {
                    // 메뉴 삭제
                    System.out.println("삭제할 메뉴 이름");
                    System.out.println(">>> ");
                    String deleteMenuName = scan.nextLine();

                    for (int i = 0; i < menuList.size(); i++) {
                        if (menuList.get(i).getMenuName().equals(deleteMenuName)) {
                            MenuDTO menuDTO = new MenuDTO(0, deleteMenuName, 0, "");
                            menuService.deleteMenu(menuDTO);
                            System.out.println("메뉴 삭제 완료");
                        }
                    }
                }

            } else if (command == 2) {
                ArrayList<UserDTO> userList = userService.getUserList();

                // TODO 회원정보
                System.out.println("1. 회원 Data 관리 | 2. 가계부");
                System.out.println(">>> ");
                int select = Integer.parseInt(scan.nextLine());

                if (select == 1) {
                    System.out.println("1. 조회 | 2. 추가 | 3. 수정 | 4. 삭제");
                    System.out.println(">>> ");

                    int userSel = Integer.parseInt(scan.nextLine());

                    if (userSel == 1) {
                        for (int i = 0; i < userList.size(); i++) {
                            System.out.println(userList.get(i));
                        }
                    } else if (userSel == 2) {
                        // TODO 중복 가입 방지 처리하기
                        System.out.println("이름");
                        System.out.println(">>> ");
                        String name = scan.nextLine();

                        boolean isDuple = false;
                        for (int i = 0; i < userList.size(); i++) {

                            if (userList.get(i).getUserName().equals(name)) {
                                System.out.println("이미 존재하는 사용자입니다.");
                                isDuple = true;
                                break;
                            }
                        }

                        if (!isDuple) {
                            UserDTO user = new UserDTO(0, name, 0);
                            int result = userService.signUp(user);

                            if (result == 0) {
                                System.out.println("DB 저장 실패");
                            } else {
                                System.out.println("DB 저장 성공!");
                            }
                        }
                    }
                }

            } else if (command == 3) {
                System.out.println("시스템 종료");
                break;
            }
        }
    } // main 끝

}
