public class Corporate_Customer extends Customer{
    private String company_name;

    @Override
    public void print_customer(){
        super.print_customer();
        System.out.println("Company Name: " + this.getCompany_name());
        System.out.println("----------------------------");
        print_orders();
    }

    public void setCompany_name(String s) {
        company_name = s;
    }

    public String getCompany_name() {
        return company_name;
    }
}
