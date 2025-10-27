package mvc_jdbc_test.view;

import mvc_jdbc_test.entity.Customer;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;
import java.util.stream.Gatherer;

public class UpdateCustomerView {

    public Customer updateCustomerInput(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 고객 아이디를 입력하세요: ");
        String customerId = sc.nextLine();

        String selectSql = "select * from 고객 where 고객아이디 = ?";
        try {
            PreparedStatement ps = con.prepareStatement(selectSql);
            ps.setString(1, customerId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("해당 고객이 존재하지 않습니다.");
                return null;
            }

            Customer customer = new Customer();
            customer.setCustomer_id(customerId);
            customer.setCustomer_name(rs.getString("고객이름"));
            customer.setAge(rs.getInt(("나이")));
            customer.setLevel(rs.getString("등급"));
            customer.setJob(rs.getString("직업"));
            customer.setReward(rs.getInt("적립금"));

            System.out.println("\n======= 기존 고객 정보 =======");
            System.out.printf("고객아이디 : %s\n", customer.getCustomer_id());
            System.out.printf("고객이름 : %s\n", customer.getCustomer_name());
            System.out.printf("고객나이 : %d\n", customer.getAge());
            System.out.printf("등급 : %s\n", customer.getLevel());
            System.out.printf("직업 : %s\n", customer.getJob());
            System.out.printf("적립금 : %d\n", customer.getReward());

            System.out.println("\n======== 수정할 정보를 입력하세요 =========");
            System.out.print("고객이름: ");
            customer.setCustomer_name(sc.nextLine());
            System.out.print("고객나이: ");
            customer.setAge(Integer.parseInt(sc.nextLine()));
            System.out.print("등급: ");
            customer.setLevel(sc.nextLine());
            System.out.print("직업: ");
            customer.setJob(sc.nextLine());
            System.out.print("적립금: ");
            customer.setReward(Integer.parseInt(sc.nextLine()));

            rs.close();
            ps.close();
            return  customer;

        } catch (SQLException e) {
            System.out.println("SQL 오류: " + e.getMessage());
            return null;
        }
    }
}
