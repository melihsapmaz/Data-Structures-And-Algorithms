public class Customer extends Person{
    private Order[] orders;
    private int operator_ID;

    Customer(){
        orders = new Order[100];
    }

    public void print_customer(){
        System.out.println("Name & Surname: " + this.getName() + " " + this.getSurname());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Phone: " + this.getPhone());
        System.out.println("ID: " + this.getID());
        System.out.println("Operator ID: " + this.getOperator_ID());
    }
    public void print_orders(){
        for(int i = 0; i < orders.length; i++) {
            if(orders[i] == null){
                break;
            }
            System.out.print("Order #" + (i+1) + " => ");
            orders[i].print_order();
        }
    }
    public void define_orders(Order[] orders){
        this.orders = orders;
    }

    public void setOperator_ID(int parseInt) {
        operator_ID = parseInt;
    }

    public int getOperator_ID() {
        return operator_ID;
    }
}
