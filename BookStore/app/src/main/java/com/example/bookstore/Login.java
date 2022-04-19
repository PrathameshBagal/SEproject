package com.example.bookstore;

public class Login {
        int _id;
        String _email;
        String _password;
        public Login(){   }
        public Login(int id, String email, String password){
            this._id = id;
            this._email = email;
            this._password = password;
        }

        public Login(String email, String password){
            this._email = email;
            this._password = password;
        }
        public int getID(){
            return this._id;
        }

        public void setID(int id){
            this._id = id;
        }

        public String getEmail(){
            return this._email;
        }

        public void setEmail(String email){
            this._email = email;
        }

        public String getPassword(){
            return this._password;
        }

        public void setPhoneNumber(String password){
            this._password = password;
        }
}

