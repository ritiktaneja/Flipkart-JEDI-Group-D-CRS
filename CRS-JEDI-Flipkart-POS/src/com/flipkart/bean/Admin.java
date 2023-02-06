package com.flipkart.bean;

/**
 * Admin Class
 * @author JEDI-D
 */
public class Admin extends User {


    /**
     * Default Constructor
     */
    public void addProfessor() {

    }

    public void assignCourse() {

    }

    public void approveStudent() {

    }

    public void addAdmin() {

    }

    @Override
    /**
     * returns Admin name and ID
     */
    public String toString() {
        return "Admin name = " + this.getName() + ", id = " + this.getAdminId();

    }

    /**
     *
     * @return Admin Id
     */
    public String getAdminId() {
        return super.getUserId();
    }

    private Admin(AdminBuilder builder) {
        super(builder.adminId, builder.name, builder.password);
    }

    /**
     * Admin Builder Class
     */
    public static class AdminBuilder {
        private String adminId, name, password;

        /**
         *Set admin Id function
         * @param adminId
         * @return AdminBuilder
         */
        public AdminBuilder setAdminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        /**
         * Set admin name function
         * @param name
         * @return AdminBuilder
         */
        public AdminBuilder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set Admin Password
         * @param password
         * @return AdminBuilder
         */
        public AdminBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        /**
         * @return Admin
         */
        public Admin build() {
            return new Admin(this);
        }
    }

}
