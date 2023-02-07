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
        return "Admin name = " + this.getName() + ", id = " + this.getAdminId();

    }

    /**
     * Method to get Admin Id
     * @return Admin Id
     */
    public String getAdminId() {
        return super.getUserId();
    }

    /**
     * Admin builder
     * @param builder
     */
    private Admin(AdminBuilder builder) {
        super(builder.adminId, builder.name, builder.password);
    }

    /**
     * Admin Builder class
     */
    public static class AdminBuilder {
        private String adminId, name, password;

        /**
         * Assigning admin Id to current object
         * @param adminId
         */
        public AdminBuilder setAdminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        /**
         * Assigning admin name to current object
         * @param name
         */
        public AdminBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set password for admin object
         * @param password
         */
        public AdminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }

}
