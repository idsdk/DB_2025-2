package mvc_jdbc_test.controller;

import jdbc_test.JDBCConnector;
import mvc_jdbc_test.entity.Customer;
import mvc_jdbc_test.entity.Order;
import mvc_jdbc_test.entity.Product;
import mvc_jdbc_test.view.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {
    public static void main(String[] args) {
        Connection con = JDBCConnector.getConnection();
        Scanner sc = new Scanner(System.in);
//        mycode(con);
//        inputCustomerAndView(con);

        while (true) {
            System.out.println("\n======== 고객 관리 =========");
            System.out.println("1. 고객 정보 등록");
            System.out.println("2. 고객 정보 수정");
            System.out.println("3. 고객 정보 삭제");
            System.out.println("4. 종료");
            System.out.print("메뉴 선택: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    inputCustomerAndView(con);
                    break;
                case "2":
                    updateCustomerInfo(con);
                    break;
                case "3":
                    deleteCustomerInfo(con);
                case "4":
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public static void orderListAndView(Connection con){
        ArrayList<Order> orderList=new ArrayList<Order>();
        String sql="select 주문번호,고객이름,고객아이디,배송지,수량, 주문일자, 제품명 from 고객, 주문, 제품 where 주문.주문고객 = 고객.고객아이디 and 주문.주문제품 = 제품.제품번호";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Order order = null;
            Product product = null;
            while (rs.next()) {
                order = new Order();
                order.setOrder_id(rs.getString("주문번호"));
                order.setOrder_address(rs.getString("고객이름"));
                order.setOrder_id(rs.getString("고객아이디"));
                order.setOrder_address(rs.getString("배송지"));
                order.setOder_quantity(rs.getString("수량"));
                order.setShippingDate(rs.getDate("주문일자"));
                order.setProduct_name(rs.getString("제품명"));
                orderList.add(order);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        OrdersView.printHead();
        for(Order order : orderList){
            OrdersView.printOrders(order);
        }
    }


    public static void mycode(Connection con)
    {ArrayList<Customer> customerList = new ArrayList<Customer>();
        try {
            String sql = "select 고객이름,고객아이디,배송지,수량, 주문일자, 제품명 from 고객, 주문, 제품 where 주문.주문고객 = 고객.고객아이디 and 주문.주문제품 = 제품.제품번호";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                String name = rs.getString("고객이름");
                String id = rs.getString("고객아이디");
                String address = rs.getString("배송지");
                int quantity = rs.getInt("수량");
                String orderDate = rs.getString("주문일자");
                String productName = rs.getString("제품명");

                System.out.println("고객이름: " + name + ", 고객아이디: " + id + ", 배송지: " + address
                        + ", 수량: " + quantity + ", 주문일자: " + orderDate + ", 제품명: " + productName);

            }

        } catch (SQLException e) {
            System.out.println("Statement or SQL Error");
        }
    }

    public static void inputCustomerAndView(Connection con) {
        Scanner sc = new Scanner(System.in);
        InputCustomerInfoView inputCustomer = new InputCustomerInfoView();
        while (true){
            Customer customer = inputCustomer.inputCustomerInfo();
            CustomerView customerView = new CustomerView();
            customerView.printHead();
            customerView.printCustomer(customer);
            customerView.printFooter();

            String sql = "insert into 고객 values(?,?,?,?,?,?)";
            try {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, customer.getCustomer_id());
                pstmt.setString(2, customer.getCustomer_name());
                pstmt.setInt(3, customer.getAge());
                pstmt.setString(4, customer.getLevel());
                pstmt.setString(5, customer.getJob());
                pstmt.setInt(6, customer.getReward());
                pstmt.executeUpdate();
                pstmt.close();
            } catch (SQLException e) {
                System.out.println("Statement or SQL Error");
            }
            System.out.print("프로그램 종료를 원하면 e를 입력: ");

            String input = sc.nextLine();

            if (input.equals("e")) {
                break;
            }
        }
        System.out.println("프로그램이 종료 되었습니다. !!!");
    }

    public static void updateCustomerInfo(Connection con) {
        Scanner sc = new Scanner(System.in);
        UpdateCustomerView updateView = new UpdateCustomerView();

        while (true){
            Customer updatedCustomer = updateView.updateCustomerInput(con);

            if (updatedCustomer == null) return;

            String sql = "update 고객 set 고객이름=?, 나이=?, 등급?, 직업=?, 적립금=? where 고객아이디=?";
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, updatedCustomer.getCustomer_name());
                ps.setInt(2, updatedCustomer.getAge());
                ps.setString(3, updatedCustomer.getLevel());
                ps.setString(4, updatedCustomer.getJob());
                ps.setInt(5, updatedCustomer.getReward());
                ps.setString(6, updatedCustomer.getCustomer_id());

                int result = ps.executeUpdate();
                if (result > 0) {
                    System.out.println("고객정보 수정 완료");
                } else {
                    System.out.println("수정 실패: 해당 고객을 찾을 수 없습니다.");
                }
                ps.close();

            } catch (SQLException e) {
                System.out.println("SQL 오류 발생: " +  e.getMessage());
            }
        }

    }

    public static void deleteCustomerInfo(Connection con) {
        DeleteCustomerView deleteView = new DeleteCustomerView();
        String deleteTargetId = deleteView.deleteCustomerInput(con);

        if (deleteTargetId == null) return;

        String sql = "delete from 고객 where 고객아이디 = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, deleteTargetId);
            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("고객정보가 삭제되었습니다.");
            } else {
                System.out.println("삭제 실패: 고객을 찾을 수 없습니다.");
            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("SQL 오류: " + e.getMessage());
        }
    }
}