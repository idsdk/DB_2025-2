package mvc_jdbc_test.view;

import java.sql.*;
import java.util.Scanner;

public class DeleteCustomerView {

    public String deleteCustomerInput(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 고객 아이디를 입력하세요: ");
        String customerId = sc.nextLine();

        String selectSql = "select * from 고객 where 고객아이디 = ?";
        try {
            PreparedStatement ps = con.prepareStatement(selectSql);
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("해당 고객이 존재하지 않습니다.");
                return null;
            }

            System.out.println("\n======== 삭제할 고객 정보 ========");
            System.out.printf("고객아이디 : %s\n", rs.getString("고객아이디"));
            System.out.printf("고객이름 : %s\n", rs.getString("고객이름"));
            System.out.printf("등급 : %s\n", rs.getString("등급"));
            System.out.printf("직업 : %s\n", rs.getString("직업"));
            System.out.print("\n정말 삭제하시겠습니까? (y/n): ");
            String confirm = sc.nextLine();

            rs.close();
            ps.close();

            if (confirm.equalsIgnoreCase("y")) {
                return customerId;
            } else {
                System.out.println("삭제가 취소되었습니다.");
                return null;
            }


        } catch (SQLException e) {
            System.out.println("SQL 오류: " + e.getMessage());
            return null;
        }
    }
}
