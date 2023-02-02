package com.flipkart.bean;

public class Admin extends User {

    public void addProfessor() {

    }

    public void assignCourse() {

    }

    public void approveStudent() {

    }

    public void addAdmin() {

    }

    @Override
    public String toString() {
        return "Admin name = " + this.getName() + " -- id = " + this.getAdminId();

    }

    public String getAdminId() {
        return super.getUserId();
    }

    private Admin(AdminBuilder builder) {
        super(builder.adminId, builder.name, builder.password);
    }

    public static class AdminBuilder {
        private String adminId, name, password;

        public AdminBuilder setAdminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        public AdminBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AdminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }

}
