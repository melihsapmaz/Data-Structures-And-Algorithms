import java.io.File;
import java.util.Scanner;

//This class is to test the project
public class Main {
    public static void main(String[] args) {

        //Create arrays to store orders, retail customers, corporate customers and operators
        Order[] orders = new Order[100];
        Retail_Customer[] retail_customers = new Retail_Customer[100];
        Corporate_Customer[] corporate_customers = new Corporate_Customer[100];
        Operator[] operators = new Operator[100];
        String fileName = "src/content.txt";
        //Read a file named "content.txt" line by line and store the content in an array

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int orderCount = 0;
            int retailCustomerCount = 0;
            int corporateCustomerCount = 0;
            int operatorCount = 0;
            //Read the file line by line
            while(scanner.hasNextLine()) {
                boolean isValidLine = true;
                String line = scanner.nextLine();
                try {
                    //Check if the line is not empty and contains at least one semicolon
                    if(isNotEmpty(line) && isSemicolonExists(line)) {
                        //Split the line by semicolon and store the substrings in an array
                        String[] lineContent = splitLine(line);
                        //Check the first substring of the line and create an object according to the type of the line
                        switch(identifyLineType(line)) {
                            case "order" -> {
                                if(lineContent.length == 5) {
                                    Order order = new Order();
                                    int j = -1;
                                    //Check the substrings of the line and set the properties of the order object
                                    for(String s : lineContent) {
                                        j++;
                                        if(isNotEmpty(s)) {
                                            if(j == 0) {
                                                order.setProduct_name(s);
                                            } else if(j == 1) {
                                                //Check if the count is a number, and it is not 0
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                if(Integer.parseInt(s) == 0) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                order.setCount(Integer.parseInt(s));
                                            } else if(j == 2) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                order.setTotal_price(Integer.parseInt(s));
                                            } else if(j == 3) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                order.setStatus(Integer.parseInt(s));
                                            } else if(j == 4) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                order.setCustomer_ID(Integer.parseInt(s));
                                            }
                                        } else {
                                            isValidLine = false;
                                            break;
                                        }
                                    }
                                    if(isValidLine) {
                                        orders[orderCount] = order;
                                        orderCount++;
                                    }
                                }
                            }
                            case "retail_customer" -> {
                                if(lineContent.length == 6) {
                                    Retail_Customer retail_customer = new Retail_Customer();
                                    int j = 0;
                                    for(String s : lineContent) {
                                        if(isNotEmpty(s)) {
                                            if(j == 0) {
                                                retail_customer.setName(s);
                                            } else if(j == 1) {
                                                retail_customer.setSurname(s);
                                            } else if(j == 2) {
                                                retail_customer.setAddress(s);
                                            } else if(j == 3) {
                                                retail_customer.setPhone(s);
                                            } else if(j == 4) {
                                                if(!isNumber(s) && Integer.parseInt(s) != 0) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                retail_customer.setID(Integer.parseInt(s));
                                            } else if(j == 5) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                retail_customer.setOperator_ID(Integer.parseInt(s));
                                            }
                                            j++;
                                        } else {
                                            isValidLine = false;
                                            break;
                                        }
                                    }
                                    if(isValidLine) {
                                        //There cannot be more than 1 retail customer with the same ID
                                        boolean isRetailCustomerExists = false;
                                        for(Retail_Customer retail : retail_customers) {
                                            if(retail != null && retail.getID() == retail_customer.getID()) {
                                                isRetailCustomerExists = true;
                                                break;
                                            }
                                        }
                                        //An operator and a retail customer cannot have the same ID
                                        for(Operator operator : operators) {
                                            if(operator != null && operator.getID() == retail_customer.getID()) {
                                                isRetailCustomerExists = true;
                                                break;
                                            }
                                        }
                                        if(!isRetailCustomerExists) {
                                            retail_customers[retailCustomerCount] = retail_customer;
                                            retail_customers[retailCustomerCount].define_orders(getOrdersByCustomerID(orders, retail_customer.getID()));
                                            retailCustomerCount++;
                                        }

                                    }
                                }
                            }
                            case "corporate_customer" -> {
                                if(lineContent.length == 7) {
                                    Corporate_Customer corporate_customer = new Corporate_Customer();
                                    int j = 0;
                                    for(String s : lineContent) {
                                        if(isNotEmpty(s)) {
                                            if(j == 0) {
                                                corporate_customer.setName(s);
                                            } else if(j == 1) {
                                                corporate_customer.setSurname(s);
                                            } else if(j == 2) {
                                                corporate_customer.setAddress(s);
                                            } else if(j == 3) {
                                                corporate_customer.setPhone(s);
                                            } else if(j == 4) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                corporate_customer.setID(Integer.parseInt(s));
                                            } else if(j == 5) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                corporate_customer.setOperator_ID(Integer.parseInt(s));
                                            } else if(j == 6) {
                                                corporate_customer.setCompany_name(s);
                                            }
                                            j++;
                                        } else {
                                            isValidLine = false;
                                            break;
                                        }
                                    }
                                    if(isValidLine) {
                                        //There cannot be more than 1 corporate customer with the same ID
                                        boolean isCorporateCustomerExists = false;
                                        for(Corporate_Customer corp : corporate_customers) {
                                            if(corp != null && corp.getID() == corporate_customer.getID()) {
                                                isCorporateCustomerExists = true;
                                                break;
                                            }
                                        }
                                        //An operator and a corporate customer cannot have the same ID
                                        for(Operator operator : operators) {
                                            if(operator != null && operator.getID() == corporate_customer.getID()) {
                                                isCorporateCustomerExists = true;
                                                break;
                                            }
                                        }
                                        if(!isCorporateCustomerExists) {
                                            corporate_customers[corporateCustomerCount] = corporate_customer;
                                            corporate_customers[corporateCustomerCount].define_orders(getOrdersByCustomerID(orders, corporate_customer.getID()));
                                            corporateCustomerCount++;
                                        }
                                    }
                                }
                            }
                            case "operator" -> {
                                if(lineContent.length == 6) {
                                    Operator operator = new Operator();
                                    int j = 0;
                                    for(String s : lineContent) {
                                        if(isNotEmpty(s)) {
                                            if(j == 0) {
                                                operator.setName(s);
                                            } else if(j == 1) {
                                                operator.setSurname(s);
                                            } else if(j == 2) {
                                                operator.setAddress(s);
                                            } else if(j == 3) {
                                                operator.setPhone(s);
                                            } else if(j == 4) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                operator.setID(Integer.parseInt(s));
                                            } else if(j == 5) {
                                                if(!isNumber(s)) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                if(Integer.parseInt(s) == 0) {
                                                    isValidLine = false;
                                                    break;
                                                }
                                                operator.setWage(Integer.parseInt(s));
                                            }
                                            j++;
                                        } else {
                                            isValidLine = false;
                                            break;
                                        }
                                    }
                                    if(isValidLine) {
                                        //There cannot be more than 1 operator with the same ID
                                        boolean isOperatorExists = false;
                                        for(Operator op : operators) {
                                            if(op != null && op.getID() == operator.getID()) {
                                                isOperatorExists = true;
                                                break;
                                            }
                                        }
                                        //An operator and a retail customer cannot have the same ID
                                        for(Retail_Customer retail : retail_customers) {
                                            if(retail != null && retail.getID() == operator.getID()) {
                                                isOperatorExists = true;
                                                break;
                                            }
                                        }
                                        //An operator and a corporate customer cannot have the same ID
                                        for(Corporate_Customer corp : corporate_customers) {
                                            if(corp != null && corp.getID() == operator.getID()) {
                                                isOperatorExists = true;
                                                break;
                                            }
                                        }
                                        if(!isOperatorExists) {
                                            operators[operatorCount] = operator;
                                            Customer[] customers = new Customer[200];
                                            System.arraycopy(retail_customers, 0, customers, 0, retailCustomerCount);
                                            System.arraycopy(corporate_customers, 0, customers, retailCustomerCount, corporateCustomerCount);
                                            operators[operatorCount].define_customers(getCustomersByOperatorID(customers, operator.getID()));
                                            operatorCount++;
                                        }
                                    }
                                }
                            }
                            default -> throw new IllegalArgumentException();
                        }
                    }
                } catch(IllegalArgumentException e) {
                    //If the line is not valid, simply skip it
                }
            }
            scanner.close();
        } catch(Exception e) {
            //If the file is not found, print an error message
            e.printStackTrace();
        }

        //Take input from the user, it has to be a number and if it matches with an operator ID, print the operator's customers, if it matches with a customer ID, print the customer's orders
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your ID...");
        String input = scanner.nextLine();
        boolean flag = false;

        try {
            if(isNumber(input)) {
                int number = Integer.parseInt(input);
                for(Operator operator : operators) {
                    if(operator != null && operator.getID() == number) {
                        System.out.println("*** Operator Screen ***");
                        operator.print_operator();
                        flag = true;
                    }
                }
                for(Retail_Customer retail_customer : retail_customers) {
                    if(retail_customer != null && retail_customer.getID() == number) {
                        System.out.println("*** Customer Screen ***");
                        retail_customer.print_customer();
                        flag = true;
                    }
                }
                for(Corporate_Customer corporate_customer : corporate_customers) {
                    if(corporate_customer != null && corporate_customer.getID() == number) {
                        System.out.println("*** Customer Screen ***");
                        corporate_customer.print_customer();
                        flag = true;
                    }
                }
                if(!flag) {
                    System.out.println("--------------------------------------------------------------------------------");
                    System.out.println("No operator/customer was found with the given ID." + input + " Please try again.");
                    System.out.println("--------------------------------------------------------------------------------");
                }
            }
            else{
                throw new NumberFormatException("Invalid input.");
            }
        } catch(NumberFormatException e) {
            e.printStackTrace();
        }
    }

    //method to check given string is empty or not
    public static boolean isNotEmpty(String str) {
        return !str.isEmpty();
    }


    //method to identify the first substring of a given string delimited by a semicolon
    public static String identifyLineType(String str) {
        if(str.split(";").length > 0) {
            return str.split(";")[0];
        }
        return "";
    }

    //method to check the given string has at least one semicolon
    public static boolean isSemicolonExists(String str) {
        return str.contains(";");
    }

    //method to substring the given string and return the substrings in an array, do not include first substring, delimiter is ";"
    public static String[] splitLine(String str) {
        return str.substring(str.indexOf(";") + 1).split(";");
    }

    //method to check the given string is a number, if it is a number it has to be a positive number, it has to be less than max value of integer, it should be convertible to integer
    public static boolean isNumber(String str) {
        //Check if the string is a number
        if(str.matches("^[+]?\\d+$")) {
            int number = Integer.parseInt(str);
            return number >= 0 && number < Integer.MAX_VALUE;
        }
        return false;
    }

    //method to take Order array and return the orders which have the given customer ID
    public static Order[] getOrdersByCustomerID(Order[] orders, int customerID) {
        Order[] result = new Order[100];
        int i = 0;
        for(Order order : orders) {
            if(order != null && order.getCustomer_ID() == customerID) {
                result[i] = order;
                i++;
            }
        }
        return result;
    }

    //method to take Retail_Customer array and return the retail customers who have the given operator ID
    public static Customer[] getCustomersByOperatorID(Customer[] customers, int operatorID) {
        Customer[] result = new Customer[100];
        int i = 0;
        for(Customer customer : customers) {
            if(customer != null && customer.getOperator_ID() == operatorID) {
                result[i] = customer;
                i++;
            }
        }
        return result;
    }
}