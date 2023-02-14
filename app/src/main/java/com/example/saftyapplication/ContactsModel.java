package com.example.saftyapplication;

public class ContactsModel {
    private int id;
    private String phoneNo;
    private String name;
    // constructor
    public ContactsModel(int id, String name,String phoneNo){
        this.id = id;
        this.phoneNo = validate(phoneNo);
        this.name = name;
    }

    private String validate(String phoneNo) {
        StringBuilder case1 = new StringBuilder("+256");
        StringBuilder case2 = new StringBuilder("");

        // check if a string has a "+"
        if (phoneNo.charAt(0) != '+') {
            for (int i = 0; i < phoneNo.length(); i++) {
                //remove any spaces or "-"
                if (phoneNo.charAt(i) != '-' && phoneNo.charAt(i) != ' ') {
                    case1.append(phoneNo.charAt(i));
                }

            }
            return case1.toString();

        } else {
            for (int i = 0; i < phoneNo.length(); i++) {
                // remove any spaces or "_"
                if (phoneNo.charAt(i) != '-' || phoneNo.charAt(i) != ' ') {
                    case2.append(phoneNo.charAt(i));
                }
            }
            return case2.toString();
        }
    }
    public String getPhoneNo(){
        return phoneNo;
    }

    public int getId() {
       return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
