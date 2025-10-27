package mvc_jdbc_test.entity;

import java.util.Date;

public class Order {
    private String order_id;
    private String order_customer;
    private String order_product_id;
    private String oder_quantity;
    private String order_address;
    private String order_date;
    private String product_name;

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public Date shippingDate;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_customer() {
        return order_customer;
    }

    public void setOrder_customer(String order_customer) {
        this.order_customer = order_customer;
    }

    public String getOder_quantity() {
        return oder_quantity;
    }

    public void setOder_quantity(String oder_quantity) {
        this.oder_quantity = oder_quantity;
    }

    public String getOrder_product_id() {
        return order_product_id;
    }

    public void setOrder_product_id(String order_product_id) {
        this.order_product_id = order_product_id;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}