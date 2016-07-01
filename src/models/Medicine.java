package models;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Medicine {
	private String ma_lk;
	private int stt;
	private String ma_thuoc;
	private String ma_nhom;
	private String ten_thuoc;
	private String don_vi_tinh;
	private String ham_luong;
	private String duong_dung;
	private String so_dang_ky;
	private int so_luong;
	private int don_gia;
	private int tyle_tt;
	private int thanh_tien;
	private String ma_khoa;
	private String ma_bac_si;
	private String ma_benh;
	private String ngay_yl;
	private String lieu_dung;
	private int ma_pttt;
	
	public Medicine() {    }
	
	public String get_ma_lk() {return ma_lk;}   
	@XmlElement(name = "ma_lk")
	public void set_ma_lk(String ma_lk) { this.ma_lk = ma_lk; }
	
	public int get_stt() {return stt;}   
	@XmlElement(name = "stt")
	public void set_stt(int stt) { this.stt = stt; }
	
	public String get_ma_thuoc() {return ma_thuoc;}   
	@XmlElement(name = "ma_thuoc")
	public void set_ma_thuoc(String ma_thuoc) { this.ma_thuoc = ma_thuoc; }
	
	public String get_ma_nhom() {return ma_nhom;}   
	@XmlElement(name = "ma_nhom")
	public void set_ma_nhom(String ma_nhom) { this.ma_nhom = ma_nhom; }
	
	public String get_ten_thuoc() {return ten_thuoc;}   
	@XmlElement(name = "ten_thuoc")
	public void set_ten_thuoc(String ten_thuoc) { this.ten_thuoc = ten_thuoc; }
	
	public String get_don_vi_tinh() {return don_vi_tinh;}   
	@XmlElement(name = "don_vi_tinh")
	public void set_don_vi_tinh(String don_vi_tinh) { this.don_vi_tinh = don_vi_tinh; }
	
	public String get_ham_luong() {return ham_luong;}   
	@XmlElement(name = "ham_luong")
	public void set_ham_luong(String ham_luong) { this.ham_luong = ham_luong; }
	
	public String get_duong_dung() {return duong_dung;}   
	@XmlElement(name = "duong_dung")
	public void set_duong_dung(String duong_dung) { this.duong_dung = duong_dung; }
	
	public String get_so_dang_ky() {return so_dang_ky;}   
	@XmlElement(name = "so_dang_ky")
	public void set_so_dang_ky(String so_dang_ky) { this.so_dang_ky = so_dang_ky; }
	
	public int get_so_luong() {return so_luong;}   
	@XmlElement(name = "so_luong")
	public void set_so_luong(int so_luong) { this.so_luong = so_luong; }
	
	public int get_don_gia() {return don_gia;}   
	@XmlElement(name = "don_gia")
	public void set_don_gia(int don_gia) { this.don_gia = don_gia; }
	
	public int get_tyle_tt() {return tyle_tt;}   
	@XmlElement(name = "tyle_tt")
	public void set_tyle_tt(int tyle_tt) { this.tyle_tt = tyle_tt; }
	
	public int get_thanh_tien() {return thanh_tien;}   
	@XmlElement(name = "thanh_tien")
	public void set_thanh_tien(int thanh_tien) { this.thanh_tien = thanh_tien; }
	
	public String get_ma_khoa() {return ma_khoa;}   
	@XmlElement(name = "ma_khoa")
	public void set_ma_khoa(String ma_khoa) { this.ma_khoa = ma_khoa; }
	
	public String get_ma_bac_si() {return ma_bac_si;}   
	@XmlElement(name = "ma_bac_si")
	public void set_ma_bac_si(String ma_bac_si) { this.ma_bac_si = ma_bac_si; }
	
	public String get_ma_benh() {return ma_benh;}   
	@XmlElement(name = "ma_benh")
	public void set_ma_benh(String ma_benh) { this.ma_benh = ma_benh; }
	
	public String get_ngay_yl() {return ngay_yl;}   
	@XmlElement(name = "ngay_yl")
	public void set_ngay_yl(String ngay_yl) { this.ngay_yl = ngay_yl; }
	
	public String get_lieu_dung() {return lieu_dung;}   
	@XmlElement(name = "lieu_dung")
	public void set_lieu_dung(String lieu_dung) { this.lieu_dung = lieu_dung; }
	
	public int get_ma_pttt() {return ma_pttt;}   
	@XmlElement(name = "ma_pttt")
	public void set_ma_pttt(int ma_pttt) { this.ma_pttt = ma_pttt; }
	
}
