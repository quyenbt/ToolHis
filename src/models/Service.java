package models;
import java.text.DecimalFormat;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


public class Service {
	private String ma_lk;
    private int stt;
    private String ma_dich_vu;
    private String ma_vat_tu;
    private String ma_nhom;
    private String ten_dich_vu;
    private String don_vi_tinh;
    private double so_luong;
    private int don_gia;
    private int tyle_tt;
    private int thanh_tien;
    private String ma_khoa;
    private String ma_bac_si;
    private String ma_benh;
    private String ngay_yl;
    private String ngay_kq;
    private int ma_pttt;
    
    
    public Service() {    }

    public String get_ma_lk() {return ma_lk;}   
    @XmlElement(name = "ma_lk")
    public void set_ma_lk(String ma_lk) {this.ma_lk = ma_lk;}
    
    public int get_stt() {return stt;}   
    @XmlElement(name = "stt")
    public void set_stt(int stt) {this.stt = stt;}
    
    public String get_ma_dich_vu() {return ma_dich_vu;}   
    @XmlElement(name = "ma_dich_vu")
    public void set_ma_dich_vu(String ma_dich_vu) {this.ma_dich_vu = ma_dich_vu;}
    
    public String get_ma_vat_tu() {return ma_vat_tu;}   
    @XmlElement(name = "ma_vat_tu")
    public void set_ma_vat_tu(String ma_vat_tu) {this.ma_vat_tu = ma_vat_tu;}
    
    public String get_ma_nhom() {return ma_nhom;}   
    @XmlElement(name = "ma_nhom")
    public void set_ma_nhom(String ma_nhom) {this.ma_nhom = ma_nhom;}
    
    public String get_ten_dich_vu() {return ten_dich_vu;}   
    @XmlElement(name = "ten_dich_vu")
    public void set_ten_dich_vu(String ten_dich_vu) {this.ten_dich_vu = ten_dich_vu;}
    
    public String get_don_vi_tinh() {return don_vi_tinh;}   
    @XmlElement(name = "don_vi_tinh")
    public void set_don_vi_tinh(String don_vi_tinh) {this.don_vi_tinh = don_vi_tinh;}
    
    public double get_so_luong() {return so_luong;}   
    @XmlElement(name = "so_luong")
    public void set_so_luong(double so_luong) {this.so_luong = so_luong;}
    
    public int get_don_gia() {return don_gia;}   
    @XmlElement(name = "don_gia")
    public void set_don_gia(int don_gia) {this.don_gia = don_gia;}
    
    public int get_tyle_tt() {return tyle_tt;}   
    @XmlElement(name = "tyle_tt")
    public void set_tyle_tt(int tyle_tt) {this.tyle_tt = tyle_tt;}
    
    public int get_thanh_tien() {return thanh_tien;}   
    @XmlElement(name = "thanh_tien")
    public void set_thanh_tien(int thanh_tien) {this.thanh_tien = thanh_tien;}

    public String get_ma_khoa() {return ma_khoa;}   
    @XmlElement(name = "ma_khoa")
    public void set_ma_khoa(String ma_khoa) {this.ma_khoa = ma_khoa;}
    
    public String get_ma_bac_si() {return ma_bac_si;}   
    @XmlElement(name = "ma_bac_si")
    public void set_ma_bac_si(String ma_bac_si) {this.ma_bac_si = ma_bac_si;}
    
    public String get_ma_benh() {return ma_benh;}   
    @XmlElement(name = "ma_benh")
    public void set_ma_benh(String ma_benh) {this.ma_benh = ma_benh;}
    
    public String get_ngay_yl() {return ngay_yl;}   
    @XmlElement(name = "ngay_yl")
    public void set_ngay_yl(String ngay_yl) {this.ngay_yl = ngay_yl;}
    
    public String get_ngay_kq() {return ngay_kq;}   
    @XmlElement(name = "ngay_kq")
    public void set_ngay_kq(String ngay_kq) {this.ngay_kq = ngay_kq;}
    
    public int get_ma_pttt() {return ma_pttt;}   
    @XmlElement(name = "ma_pttt")
    public void set_ma_pttt(int ma_pttt) {this.ma_pttt = ma_pttt;}
  

}
