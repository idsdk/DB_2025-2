package mvc_jdbc_test.controller.controller;

import jdbc_test.JDBCConnector;
import mvc_jdbc_test.controller.entity.Customer;
import mvc_jdbc_test.controller.entity.Order;
import mvc_jdbc_test.controller.entity.Product;
import mvc_jdbc_test.controller.view.OrdersView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainController {
    public static void main(String[] args) {
        Connection con = JDBCConnector.getConnection();
//        orderListAndView(con);
        mycode(con);
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
}
