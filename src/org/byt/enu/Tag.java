/**
 * Feb 10, 2016
 */
package org.byt.enu;

/**
 * @author admin
 *
 */
public enum Tag {
	MaLienKet("ma_lk","Mã liên kết"),
	TongHop("tonghop","Tổng hợp"),
	ChiTietThuoc("ctthuoc","Chi tiết thuốc"),
	ChiTietDichVu("CTDV","Chi tiết dịch vụ"),
	BangChiTietThuoc("Bang_CTTHUOC","Bảng chi tiết thuốc"),
	BangChiTietDichVu("Bang_CTDV","Bảng chi tiết dịch vụ"),
	CheckOut("CHECKOUT","CHECKOUT"),
	ThongTinBenhNhan("THONGTINBENHNHAN","Thông tin bệnh nhân"),
	ThongTinChiTiet("THONGTINCHITIET","Thông tin chi tiết"),
	TenThuoc("ten_thuoc","Tên thuốc"),
	TenDichVu("ten_dich_vu","Tên dịch vụ"),
	HoTen("ho_ten","Họ tên"), 
	TenBenh("ten_benh","Tên bệnh"),
	DonViTinh("don_vi_tinh","Đơn vị tính"),
	SoLuong("so_luong","Số lượng"),
	MaThuoc("ma_thuoc","Mã thuốc"),
	HamLuong("ham_luong","Hàm lượng"),
	SoDangKy("so_dang_ky","Số đăng ký"),
	DuongDung("duong_dung", "Đường dùng"),
	MaBenh("ma_benh", "Mã bệnh"),
	MaDichVu("ma_dich_vu", "Mã dịch vụ"),
       
	NgayKetQua("ngay_kq","Ngày kết quả");
	private String code;
	private String text;
	private Tag(String code, String text){
		this.code = code;
		this.text = text;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
