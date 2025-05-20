package quctrun.trunn2004.cuoiky;

public class KhoanThu {
    private long soTien;
    private String ghiChu;
    private String thoiGian;
    private String category;


    public KhoanThu() {}

    public KhoanThu(long soTien, String ghiChu, String thoiGian, String category) {
        this.soTien = soTien;
        this.ghiChu = ghiChu;
        this.thoiGian = thoiGian;
        this.category = category;

    }

    public long getSoTien() {
        return soTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getThoiGian() {
        return thoiGian;
    }
    public String getCategory() {
        return category;
    }
}


