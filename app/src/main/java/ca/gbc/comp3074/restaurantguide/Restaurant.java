package ca.gbc.comp3074.restaurantguide;

public class Restaurant{

    private String name;
    private String street;
    private String city;
    private String country;
    private String postcode;
    private String phone;
    private String tag;
    private String description;

    // set constructor
    public Restaurant(String name, String street, String city, String country, String postcode, String phone, String tag, String description) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
        this.postcode = postcode;
        this.phone = phone;
        this.tag = tag;
        this.description = description;
    }

    // set this constructor for SearchView
    public Restaurant(String name){this.name = name;}

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // get address
    public String getAddress() {
        return getStreet()+", "+getCity()+", "+getCountry()+" "+getPostcode();
    }
}
