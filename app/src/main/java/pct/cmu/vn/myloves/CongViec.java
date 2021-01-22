package pct.cmu.vn.myloves;

import java.io.Serializable;

public class CongViec implements Serializable {
    String maCV;
    String congViec;
    String diemCong;
    String diemTru;
    String tyleDiem;

    public CongViec() {
    }

    public CongViec(String congViec, String diemCong, String diemTru, String tyleDiem) {
        this.maCV = "";
        this.congViec = congViec;
        this.diemCong = diemCong;
        this.diemTru = diemTru;
        this.tyleDiem = tyleDiem;
    }

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }

    public String getDiemCong() {
        return diemCong;
    }

    public void setDiemCong(String diemCong) {
        this.diemCong = diemCong;
    }

    public String getDiemTru() {
        return diemTru;
    }

    public void setDiemTru(String diemTru) {
        this.diemTru = diemTru;
    }

    public String getTyleDiem() {
        return tyleDiem;
    }

    public void setTyleDiem(String tyleDiem) {
        this.tyleDiem = tyleDiem;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }
}
