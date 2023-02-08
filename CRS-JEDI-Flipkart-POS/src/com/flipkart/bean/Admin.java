package com.flipkart.bean;

import java.util.List;

/**
 * admin class
 */
public class Admin extends User {

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

    /**
     * Print list of admins
     * @param header
     * @param adminList
     */
    public static void printAdminList(String header, List<Admin> adminList) {
        String stars = "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
        System.out.println(stars);
        String namePlate = String.format("%45s", header);
        System.out.println(namePlate + "\n");
        String s = String.format("%-35s" + "%-35s", "Name", "ID");
        System.out.println(s+ "\n");

        for(Admin admin: adminList) {
            String stmt = String.format("%-35s" + "%-35s" , admin.getName(), "" + admin.getAdminId());
            System.out.println(stmt);
        }

        System.out.println(stars+"\n");

    }

}
