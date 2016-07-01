package models;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Patient {
	private String ma_lk;
    private int stt;
    private String ma_bn;
    private String ho_ten;
    private String ngay_sinh;    
    private int gioi_tinh;
    private String dia_chi;
    private String ma_the;
    private String ma_dkbd;
    private String gt_the_tu;
    private String gt_the_den;
    private String ma_benh;
    private String ma_benhkhac;
    private String ten_benh;
    private int ma_lydo_vvien;
    private String ma_noi_chuyen;
    private int ma_tai_nan;
    private String ngay_vao;
    private String ngay_ra;
    private int so_ngay_dtri;
    private int ket_qua_dtri;
    private int tinh_trang_rv;
    private String ngay_ttoan;
    private int muc_huong;
    private int t_tongchi;
    private int t_bntt;
    private int t_bhtt;
    private String ma_khoa;
    private int t_nguonkhac;
    private int t_ngoaids;
    private int nam_qt;
    private int thang_qt;
    private int ma_loaikcb;
    private String ma_cskcb;
    private String ma_khuvuc;
    private String ma_pttt_qt;
    private int t_thuoc;
    private int t_vtyt;
    private int can_nang;
    
    

    public Patient() {    }

public String getma_lk() {return ma_lk;}   
@XmlElement(name = "ma_lk")
public void setma_lk(String ma_lk) {this.ma_lk = ma_lk;}
  
public int getstt() {return stt;}
@XmlElement(name = "stt")
public void setFacnum(int stt) {this.stt = stt;}

public String getma_bn() {  return ma_bn;  }
@XmlElement(name = "ma_bn")
public void setma_bn(String ma_bn) {  this.ma_bn = ma_bn;  }

public String getho_ten() { return ho_ten;  }
@XmlElement(name = "ho_ten")
public void setho_ten(String ho_ten) { this.ho_ten = ho_ten;  }

public String getngay_sinh() {    return ngay_sinh;  }
@XmlElement(name = "ngay_sinh")
public void setngay_sinh(String ngay_sinh) {this.ngay_sinh = ngay_sinh;  }

public int getgioi_tinh() {    return gioi_tinh;  }
@XmlElement(name = "gioi_tinh")
public void setgioi_tinh(int gioi_tinh) { this.gioi_tinh = gioi_tinh;  }

public String getdia_chi() { return dia_chi;  }
@XmlElement(name = "dia_chi")
public void setdia_chi(String dia_chi) { this.dia_chi = dia_chi;  }

public String getma_the() { return ma_the;  }
@XmlElement(name = "ma_the")
public void setma_the(String ma_the) { this.ma_the = ma_the;  }

public String getma_dkbd() { return ma_dkbd;  }
@XmlElement(name = "ma_dkbd")
public void setma_dkbd(String ma_dkbd) { this.ma_dkbd = ma_dkbd;  }

public String getgt_the_tu() {  return gt_the_tu;  }
@XmlElement(name = "gt_the_tu")
public void setgt_the_tu(String gt_the_tu) {this.gt_the_tu = gt_the_tu;  }

public String getgt_the_den() { return gt_the_den;  }
@XmlElement(name = "gt_the_den")
public void setgt_the_den(String gt_the_den) {this.gt_the_den = gt_the_den;  }

public String get_ma_benh() { return ma_benh;  }
@XmlElement(name = "ma_benh")
public void set_ma_benh(String ma_benh) { this.ma_benh = ma_benh;  }

public String get_ma_benhkhac() { return ma_benhkhac;  }
@XmlElement(name = "ma_benhkhac")
public void set_ma_benhkhac(String ma_benhkhac) { this.ma_benhkhac = ma_benhkhac;  }

public String get_ten_benh() { return ten_benh;  }
@XmlElement(name = "ten_benh")
public void set_ten_benh(String ten_benh) { this.ten_benh = ten_benh;  }

public int get_ma_lydo_vvien() { return ma_lydo_vvien;  }
@XmlElement(name = "ma_lydo_vvien")
public void set_ma_lydo_vvien(int ma_lydo_vvien) { this.ma_lydo_vvien = ma_lydo_vvien;  }

public String get_ma_noi_chuyen() { return ma_noi_chuyen;  }
@XmlElement(name = "ma_noi_chuyen")
public void set_ma_noi_chuyen(String ma_noi_chuyen) { this.ma_noi_chuyen = ma_noi_chuyen;  }

public int get_ma_tai_nan() { return ma_tai_nan;  }
@XmlElement(name = "ma_tai_nan")
public void set_ma_tai_nan(int ma_tai_nan) { this.ma_tai_nan = ma_tai_nan;  }

public String get_ngay_vao() { return ngay_vao;  }
@XmlElement(name = "ngay_vao")
public void set_ngay_vao(String ngay_vao) { this.ngay_vao = ngay_vao;  }

public String get_ngay_ra() { return ngay_ra;  }
@XmlElement(name = "ngay_ra")
public void set_ngay_ra(String ngay_ra) { this.ngay_ra = ngay_ra;  }

public int get_so_ngay_dtri() { return so_ngay_dtri;  }
@XmlElement(name = "so_ngay_dtri")
public void set_so_ngay_dtri(int so_ngay_dtri) { this.so_ngay_dtri = so_ngay_dtri;  }

public int get_ket_qua_dtri() { return ket_qua_dtri;  }
@XmlElement(name = "ket_qua_dtri")
public void set_ket_qua_dtri(int ket_qua_dtri) { this.ket_qua_dtri = ket_qua_dtri;  }

public int get_tinh_trang_rv() { return tinh_trang_rv;  }
@XmlElement(name = "tinh_trang_rv")
public void set_tinh_trang_rv(int tinh_trang_rv) { this.tinh_trang_rv = tinh_trang_rv;  }

public String get_ngay_ttoan() { return ngay_ttoan;  }
@XmlElement(name = "ngay_ttoan")
public void set_ngay_ttoan(String ngay_ttoan) { this.ngay_ttoan = ngay_ttoan;  }

public int get_muc_huong() { return muc_huong;  }
@XmlElement(name = "muc_huong")
public void set_muc_huong(int muc_huong) { this.muc_huong = muc_huong;  }

public int get_t_tongchi() { return t_tongchi;  }
@XmlElement(name = "t_tongchi")
public void set_t_tongchi(int t_tongchi) { this.t_tongchi = t_tongchi;  }

public int get_t_bntt() { return t_bntt;  }
@XmlElement(name = "t_bntt")
public void set_t_bntt(int t_bntt) { this.t_bntt = t_bntt;  }

public int get_t_bhtt() { return t_bhtt;  }
@XmlElement(name = "t_bhtt")
public void set_t_bhtt(int t_bhtt) { this.t_bhtt = t_bhtt;  }

public int get_t_nguonkhac() { return t_nguonkhac;  }
@XmlElement(name = "t_nguonkhac")
public void set_t_nguonkhac(int t_nguonkhac) { this.t_nguonkhac = t_nguonkhac;  }

public int get_t_ngoaids() { return t_ngoaids;  }
@XmlElement(name = "t_ngoaids")
public void set_t_ngoaids(int t_ngoaids) { this.t_ngoaids = t_ngoaids;  }

public int get_nam_qt() { return nam_qt;  }
@XmlElement(name = "nam_qt")
public void set_nam_qt(int nam_qt) { this.nam_qt = nam_qt;  }

public int get_thang_qt() { return thang_qt;  }
@XmlElement(name = "thang_qt")
public void set_thang_qt(int thang_qt) { this.thang_qt = thang_qt;  }

public int get_ma_loaikcb() { return ma_loaikcb;  }
@XmlElement(name = "ma_loaikcb")
public void set_ma_loaikcb(int ma_loaikcb) { this.ma_loaikcb = ma_loaikcb;  }

public String get_ma_cskcb() { return ma_cskcb;  }
@XmlElement(name = "ma_cskcb")
public void set_ma_cskcb(String ma_cskcb) { this.ma_cskcb = ma_cskcb;  }

public String get_ma_khuvuc() { return ma_khuvuc;  }
@XmlElement(name = "ma_khuvuc")
public void set_ma_khuvuc(String ma_khuvuc) { this.ma_khuvuc = ma_khuvuc;  }

public String get_ma_pttt_qt() { return ma_pttt_qt;  }
@XmlElement(name = "ma_pttt_qt")
public void set_ma_pttt_qt(String ma_pttt_qt) { this.ma_pttt_qt = ma_pttt_qt;  }

public int get_t_thuoc() { return t_thuoc;}
@XmlElement(name = "t_thuoc")
public void set_t_thuoc(int t_thuoc) { this.t_thuoc = t_thuoc;  }

public int get_t_vtyt() { return t_vtyt;  }
@XmlElement(name = "t_vtyt")
public void set_t_vtyt(int t_vtyt) { this.t_vtyt = t_vtyt;  }

public int get_can_nang() { return can_nang;  }
@XmlElement(name = "can_nang")
public void set_can_nang(int can_nang) { this.can_nang = can_nang;  }

public String get_ma_khoa() { return ma_khoa;  }
@XmlElement(name = "ma_khoa")
public void set_ma_khoa(String ma_khoa) { this.ma_khoa = ma_khoa;  }
}
