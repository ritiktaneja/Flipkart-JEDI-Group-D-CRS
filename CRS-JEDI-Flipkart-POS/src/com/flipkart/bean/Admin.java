package com.flipkart.bean;

public class Admin extends User{

    public void addProfessor() {

    }

    public void assignCourse() {

    }
    public void approveStudent() {

    }

    public void addAdmin() {

    }

    private Admin(AdminBuilder builder) {
        super(builder.userId, builder.name, builder.password);
    }
    public class AdminBuilder {
        private String userId, name, password;

        public AdminBuilder setUserId(String userId) {this.userId = userId; return this;}
        public AdminBuilder setName(String name) {this.name = name; return this;}
        public AdminBuilder setPassword(String password) {this.password = password; return this;}

        public Admin build() {
            return new Admin(this);
        }
    }

}
