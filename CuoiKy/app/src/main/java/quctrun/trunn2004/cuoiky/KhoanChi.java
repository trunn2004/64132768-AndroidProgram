package quctrun.trunn2004.cuoiky;

public class KhoanChi {
    private long soTien;
    private String ghiChu;
    private String thoiGian;
    private String category;


    public KhoanChi() {}

    public KhoanChi(long soTien, String ghiChu, String thoiGian, String category) {
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
