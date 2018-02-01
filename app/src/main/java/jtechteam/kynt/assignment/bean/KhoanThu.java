package jtechteam.kynt.assignment.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class KhoanThu implements Parcelable{
    private int stt;
    private String tenKhoanThu, ghiChu;
    private int soTien;
    private String thoiGian;
    private boolean noiBat;

    public KhoanThu(int stt, String tenKhoanThu, String ghiChu, int soTien, String thoiGian) {
        this.stt = stt;
        this.tenKhoanThu = tenKhoanThu;
        this.ghiChu = ghiChu;
        this.soTien = soTien;
        this.thoiGian = thoiGian;
    }

    public boolean isNoiBat() {
        return noiBat;
    }

    public void setNoiBat(boolean noiBat) {
        this.noiBat = noiBat;
    }

    public int getStt() {
        return stt;
    }

    public String getTenKhoanThu() {
        return tenKhoanThu;
    }

    public int getSoTien() {
        return soTien;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private KhoanThu(Parcel in) {
        stt = in.readInt();
        tenKhoanThu = in.readString();
        ghiChu = in.readString();
        soTien = in.readInt();
        thoiGian = in.readString();
    }

    public static final Creator<KhoanThu> CREATOR = new Creator<KhoanThu>() {
        @Override
        public KhoanThu createFromParcel(Parcel in) {
            return new KhoanThu(in);
        }

        @Override
        public KhoanThu[] newArray(int size) {
            return new KhoanThu[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(stt);
        parcel.writeString(tenKhoanThu);
        parcel.writeString(ghiChu);
        parcel.writeInt(soTien);
        parcel.writeString(thoiGian);
    }

}
