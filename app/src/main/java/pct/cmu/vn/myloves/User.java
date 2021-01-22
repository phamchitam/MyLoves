package pct.cmu.vn.myloves;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class User implements Serializable {
    String hoTen;
    String namSinh;
    String password;
    String email;


    public User() {
    }

    public User(String hoTen, String namSinh, String email, String password) {
        this.hoTen = hoTen;
        this.namSinh = namSinh;
        this.email = email;
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
