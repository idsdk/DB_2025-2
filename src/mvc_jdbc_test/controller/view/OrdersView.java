package mvc_jdbc_test.controller.view;

import mvc_jdbc_test.controller.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersView {
    public static void printHead() {
        System.out.println("============== 주문 목록 화면 =============");
        System.out.println("고객명\t 고객아이디 \t 제품명\t 수량\t 주문일자\t 배송지");
        System.out.println();
    }

    public static void printOrders(Order order) {
        Date shippingDate = order.getShippingDate();
        SimpleDateFormat dateFomat = new SimpleDateFormat("yyyy-MM-dd");

        String orderDate = dateFomat.format(shippingDate);
        System.out.printf("%s\t%s\t%s\t%d\t%s\t%s\n", order.getOrder_customer(), order.getOrder_id(), order.getOrder_product_id(), order.getOder_quantity(), orderDate, order.getOrder_address());
    }
}
