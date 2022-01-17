package com.example.wabo;

public class Users {

        private String name;
        private String email;
        private String address;
        private String nric;
        private String password;
        private String phone;
        private String role;
        private String heirsname;
        private String heirsic;
        private String heirsadd;

        public Users (String name, String email, String address, String nric, String password, String phone, String role, String heirsname, String heirsic, String heirsadd) {
            this.name = name;
            this.email = email;
            this.address = address;
            this.nric = nric;
            this.password = password;
            this.phone = phone;
            this.role = role;
            this.heirsname = heirsname;
            this.heirsic = heirsic;
            this.heirsadd= heirsadd;

        }

        public Users(String s, String toString, String string){
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

    public String getHeirsname() {
        return heirsname;
    }

    public void setHeirsname(String heirsname) {
        this.heirsname = heirsname;
    }

    public String getHeirsic() {
        return heirsic;
    }

    public void setHeirsic(String heirsic) {
        this.heirsic = heirsic;
    }

    public String getHeirsadd() {
        return heirsadd;
    }

    public void setHeirsadd(String heirsadd) {
        this.heirsadd = heirsadd;
    }
}
