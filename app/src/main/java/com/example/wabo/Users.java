package com.example.wabo;

public class Users {

        private String name;
        private String email;
        private String address;
        private String nric;
        private String password;
        private String phone;
        private String role;

        public Users (String name, String email, String address, String nric, String password, String phone, String role) {
            this.name = name;
            this.email = email;
            this.address = address;
            this.nric = nric;
            this.password = password;
            this.phone = phone;
            this.role = role;

        }

        public Users(){
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNric() {
            return nric;
        }

        public void setNric(String nric) {
            this.nric = nric;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

}
