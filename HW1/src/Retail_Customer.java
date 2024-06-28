public class Retail_Customer extends Customer{
    @Override
    public void print_customer(){
        super.print_customer();
        System.out.println("----------------------------");
        print_orders();
    }

}
