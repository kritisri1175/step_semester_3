import java.util.ArrayList;
import java.util.List;

public class PaymentProcessingSystem {

    interface PaymentMethod {
        String getName();

        boolean processPayment(double amount);
    }

    // the boolean simulates what the payment gateway would answer
    static class CreditCardPayment implements PaymentMethod {

        private final boolean approved;

        CreditCardPayment(boolean approved) {
            this.approved = approved;
        }

        @Override
        public String getName() {
            return "Credit Card";
        }

        @Override
        public boolean processPayment(double amount) {
            return approved;
        }
    }

    static class PayPalPayment implements PaymentMethod {

        private final boolean approved;

        PayPalPayment(boolean approved) {
            this.approved = approved;
        }

        @Override
        public String getName() {
            return "PayPal";
        }

        @Override
        public boolean processPayment(double amount) {
            return approved;
        }
    }

    static class BankTransferPayment implements PaymentMethod {

        private final boolean approved;

        BankTransferPayment(boolean approved) {
            this.approved = approved;
        }

        @Override
        public String getName() {
            return "Bank Transfer";
        }

        @Override
        public boolean processPayment(double amount) {
            return approved;
        }
    }

    static class Product {

        private final String name;
        private final double price;

        Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        String getName() {
            return name;
        }

        double getPrice() {
            return price;
        }
    }

    static class OrderItem {

        private final Product product;
        private final int quantity;

        OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        double getSubtotal() {
            return product.getPrice() * quantity;
        }
    }

    static class Customer {

        private final String name;

        Customer(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        Order createOrder() {
            Order order = new Order(this);
            System.out.println("Order created for Customer " + name + ".");
            return order;
        }
    }

    enum OrderStatus {
        PENDING, PAID;

        String label() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

    static class Order {

        private final Customer customer;
        private final List<OrderItem> items = new ArrayList<>();
        private OrderStatus status = OrderStatus.PENDING;

        Order(Customer customer) {
            this.customer = customer;
        }

        void addItem(Product product, int quantity) {
            items.add(new OrderItem(product, quantity));
        }

        double getTotal() {
            double total = 0;
            for (OrderItem item : items) {
                total += item.getSubtotal();
            }
            return total;
        }

        OrderStatus getStatus() {
            return status;
        }

        // depends only on the PaymentMethod abstraction, never on a concrete type
        boolean pay(PaymentMethod method) {
            if (items.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return false;
            }
            if (status == OrderStatus.PAID) {
                System.out.println("Order for Customer " + customer.getName() + " is already paid.");
                return false;
            }
            System.out.println("Payment initiated via " + method.getName() + " for Order " + customer.getName() + ".");
            boolean success = method.processPayment(getTotal());
            if (success) {
                status = OrderStatus.PAID;
                System.out.println("Payment for Order " + customer.getName() + " successful. Order status: "
                        + status.label() + ".");
            } else {
                System.out.println("Payment for Order " + customer.getName() + " failed. Order status: "
                        + status.label() + ".");
            }
            return success;
        }
    }

    public static void main(String[] args) {
        Product a = new Product("Product A", 25.0);
        Product b = new Product("Product B", 40.0);
        Product c = new Product("Product C", 60.0);

        Customer x = new Customer("X");
        Order orderX = x.createOrder();
        orderX.addItem(a, 2);
        orderX.addItem(b, 1);
        orderX.pay(new CreditCardPayment(true));

        Customer y = new Customer("Y");
        Order orderY = y.createOrder();
        orderY.pay(new CreditCardPayment(true));

        Customer z = new Customer("Z");
        Order orderZ = z.createOrder();
        orderZ.addItem(c, 1);
        orderZ.pay(new PayPalPayment(false));
    }
}
