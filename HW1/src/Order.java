public class Order {
    private String product_name;
    private int count;
    private int total_price;
    private int status; //0:Initialized, 1:Processing, 2:Completed, 3:Cancelled
    private int customer_ID;

    public void print_order(){
        String status_str = switch(status) {
            case 0 -> "Initialized";
            case 1 -> "Processing";
            case 2 -> "Completed";
            case 3 -> "Cancelled";
            default -> "";
        };
        System.out.println("Product Name: " + this.product_name + " - Count: " + this.count + " - Total Price: " + this.total_price + " - Status: " + status_str + ".");
    }

    public void setProduct_name(String s) {
        product_name = s;
    }

    public void setCount(int parseInt) {
        count = parseInt;
    }

    public void setTotal_price(int parseInt) {
        total_price = parseInt;
    }

    public void setStatus(int parseInt) {
        status = parseInt;
    }

    public void setCustomer_ID(int parseInt) {
        customer_ID = parseInt;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }
}
