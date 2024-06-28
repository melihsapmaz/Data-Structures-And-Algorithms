public class Person {
    private String name;
    private String surname;
    private String address;
    private String phone;
    private int ID;

    public void setName(String s) {
        name = s;
    }

    public void setSurname(String s) {
        surname = s;
    }

    public void setAddress(String s) {
        address = s;
    }

    public void setPhone(String s) {
        phone = s;
    }

    public void setID(int parseInt) {
        ID = parseInt;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
