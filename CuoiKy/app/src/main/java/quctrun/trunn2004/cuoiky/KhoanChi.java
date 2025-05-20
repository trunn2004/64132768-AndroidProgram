package quctrun.trunn2004.cuoiky;

public class KhoanChi {
    private long soTien;
    private String ghiChu;
    private String thoiGian;

    public KhoanChi() {}

    public KhoanChi(long soTien, String ghiChu, String thoiGian) {
        this.soTien = soTien;
        this.ghiChu = ghiChu;
        this.thoiGian = thoiGian;
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
}


