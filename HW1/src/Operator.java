public class Operator extends Person{
    private int wage;
    private Customer[] customers;

    Operator(){
        customers = new Customer[100];
    }

    public void print_operator(){
        System.out.println("----------------------------");
        System.out.println("Name & Surname: " + this.getName() + " " + this.getSurname());
        System.out.println("Address: " + this.getAddress());
        System.out.println("Phone: " + this.getPhone());
        System.out.println("ID: " + this.getID());
        System.out.println("Wage: " + this.wage);
        print_customers();
    }
    public void print_customers(){
        boolean is_empty = true;
        for(int i = 0; i < customers.length; i++) {
            if(customers[i] == null){
                break;
            }
            if(customers[i] instanceof Retail_Customer) {
                is_empty = false;
                System.out.println("----------------------------");
                System.out.println("Customer #" + (i+1) + " ( a retail customer) :");
            }
            else{
                is_empty = false;
                System.out.println("----------------------------");
                System.out.println("Customer #" + (i+1) + " ( a corporate customer) :");
            }
            customers[i].print_customer();
        }
        if(is_empty){
            System.out.println("----------------------------");
            System.out.println("This operator does not have any customer.");
        }
    }
    public void define_customers(Customer[] customers){
        this.customers = customers;
    }

    public void setWage(int parseInt) {
        wage = parseInt;
    }
}
