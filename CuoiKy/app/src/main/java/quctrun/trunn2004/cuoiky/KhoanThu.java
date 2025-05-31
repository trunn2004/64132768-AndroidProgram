package quctrun.trunn2004.cuoiky;

public class KhoanThu {
    private String id;
    private long soTien;
    private String ghiChu, thoiGian, category;

    public KhoanThu() {}

    public KhoanThu(String id, long soTien, String ghiChu, String thoiGian, String category) {
        this.id = id;
        this.soTien = soTien;
        this.ghiChu = ghiChu;
        this.thoiGian = thoiGian;
        this.category = category;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public long getSoTien() { return soTien; }
    public void setSoTien(long soTien) { this.soTien = soTien; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    public String getThoiGian() { return thoiGian; }
    public void setThoiGian(String thoiGian) { this.thoiGian = thoiGian; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
