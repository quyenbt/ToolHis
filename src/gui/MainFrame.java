package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.xml.bind.JAXBContext;

import models.Medicine;
import models.Medicines;
import models.Patient;
import models.Patients;
import models.Service;
import models.Services;
import service.XMLService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame {

	private JPanel patientPane,medicinePane;
	private JTable table;
	private JTabbedPane tabs = new JTabbedPane();;
	private JFrame f = new JFrame();	
	private Object[][] data;
	private String code_lk; 

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {			
					
		/*---Tab Table 1-------------------------------------------------------------------------------------*/
		final JTable patientsTable = new JTable(new Object[0][],getTable1Columns());
		JScrollPane table1Pane = new JScrollPane(patientsTable);	
		table1Pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table1Pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		patientPane = new JPanel();
		JButton btnLoadXML1 = new JButton("Tải file XML Bảng 1");	
		btnLoadXML1.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
			 DefaultTableModel model1 = new DefaultTableModel(getTable1Data(),getTable1Columns());
			 patientsTable.setModel(model1); }
			 });	
		patientPane.setLayout(new BorderLayout());
		patientPane.add(table1Pane,BorderLayout.CENTER);
		patientPane.add(btnLoadXML1,BorderLayout.NORTH);		
		tabs.addTab("BẢNG 1", patientPane);	
		/*--Tab Table 2------------------------------------------------------------------------------------------*/
		final JTable medicinesTable = new JTable(new Object[0][],getTable2Columns());		
		JScrollPane table2Pane = new JScrollPane(medicinesTable);		
		table2Pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table2Pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel medicinePane = new JPanel();			
		JButton btnLoadXML2 = new JButton("Tải file XML Bảng 2");		
		btnLoadXML2.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
			 DefaultTableModel model2 = new DefaultTableModel(getTable2Data(),getTable2Columns());
			 medicinesTable.setModel(model2); }
			 });			
		medicinePane.setLayout(new BorderLayout());
		medicinePane.add(table2Pane,BorderLayout.CENTER);			
		medicinePane.add(btnLoadXML2,BorderLayout.NORTH);		
		tabs.addTab("BẢNG 2", medicinePane);		
	/*------Tab Table 3-------------------------------------------------------------------------------------------*/	
		final JTable servicesTable = new JTable(new Object[0][],getTable3Columns());
		JScrollPane table3Pane = new JScrollPane(servicesTable);		
		table3Pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table3Pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel servicePane = new JPanel();			
		JButton btnLoadXML3 = new JButton("Tải file XML Bảng 3");		
		btnLoadXML3.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
			 DefaultTableModel model3 = new DefaultTableModel(getTable3Data(),getTable3Columns());
			 servicesTable.setModel(model3); }
			 });			
		servicePane.setLayout(new BorderLayout());
		servicePane.add(table3Pane,BorderLayout.CENTER);			
		servicePane.add(btnLoadXML3,BorderLayout.NORTH);		
		tabs.addTab("BẢNG 3", servicePane);		
	/*-----Tab Tools-------------------------------------------------------------------------------------------------------*/
	final JTable patientsTableByCode = new JTable(new Object[0][],getTable3Columns());
		JScrollPane table1PaneByCode = new JScrollPane(patientsTableByCode);		
		table3Pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table3Pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		JPanel patientsPaneByCode = new JPanel();			
		JButton btnLoadXML4 = new JButton("Tải file XML Bảng 2");		
		btnLoadXML4.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent arg0) {
			 DefaultTableModel model4 = new DefaultTableModel(getTable3DataByCode("401/2016"),getTable3Columns());
			 patientsTableByCode.setModel(model4); 
			 System.out.println(getTable3DataByCode("401/2016"));
			 }
			 });			
		patientsPaneByCode.setLayout(new BorderLayout());
		patientsPaneByCode.add(table1PaneByCode,BorderLayout.CENTER);			
		patientsPaneByCode.add(btnLoadXML4,BorderLayout.NORTH);		
		tabs.addTab("BẢNG 4", patientsPaneByCode);
	/*-----Tab Tools-------------------------------------------------------------------------------------------------------*/
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(tabs);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);		
	
	}

	  
	  private Object[][] getTable1Data() {

		  
		  XMLService xmlService = new XMLService();
			Patients patients = xmlService.getPatients();
			Object[][] data = new Object[patients.getPatientsList().size()][];

			for (int i = 0; i < data.length; i++) {

				Patient patient = patients.getPatientsList().get(i);
				data[i] = new Object[] { patient.getma_lk(), patient.getstt(),
						patient.getma_bn(), patient.getho_ten(),patient.getngay_sinh(),patient.getgioi_tinh(),
						patient.getdia_chi(),patient.getma_the(),patient.getma_dkbd(),patient.getgt_the_tu(),
						patient.getgt_the_den(),patient.get_ma_benh(),patient.get_ma_benhkhac(),patient.get_ten_benh(),
						patient.get_ma_lydo_vvien(),patient.get_ma_noi_chuyen(),patient.get_ma_tai_nan(),patient.get_ngay_vao(),
						patient.get_ngay_ra(),patient.get_so_ngay_dtri(),patient.get_ket_qua_dtri(),patient.get_tinh_trang_rv(),
						patient.get_ngay_ttoan() ,patient.get_muc_huong(),patient.get_t_tongchi(),
						patient.get_t_bntt(),patient.get_t_bhtt(),patient.get_t_nguonkhac(),patient.get_t_ngoaids(),
						patient.get_nam_qt() ,patient.get_thang_qt(),patient.get_ma_loaikcb() ,patient.get_ma_cskcb(),
						patient.get_ma_khuvuc(),patient.get_ma_pttt_qt(),patient.get_t_thuoc(),
						patient.get_t_vtyt(),patient.get_can_nang(),patient.get_ma_khoa()};
			}

			return data;
		}
	  private Object[][] getTable1DataByCode(String code_lk) {

		  XMLService xmlService = new XMLService();
			Patients patients = xmlService.getPatients();
			int MaxRow = patients.getPatientsList().size();
			int j=0;
			for (int i=0;i<MaxRow;i++)
			{Patient patient = patients.getPatientsList().get(i);
			String tmp = patient.getma_lk();
			if (tmp.equals(code_lk)) {	
				j++;
			}
			}
			Object[][] data = new Object[j][];			
			int k=0;
			for (int i = 0; i < MaxRow; i++) {				
				Patient patient = patients.getPatientsList().get(i);
				String tmp = patient.getma_lk();				
				if (tmp.equals(code_lk)) {				
					data[k] = new Object[] { patient.getma_lk(), patient.getstt(),
							patient.getma_bn(), patient.getho_ten(),patient.getngay_sinh(),patient.getgioi_tinh(),
							patient.getdia_chi(),patient.getma_the(),patient.getma_dkbd(),patient.getgt_the_tu(),
							patient.getgt_the_den(),patient.get_ma_benh(),patient.get_ma_benhkhac(),patient.get_ten_benh(),
							patient.get_ma_lydo_vvien(),patient.get_ma_noi_chuyen(),patient.get_ma_tai_nan(),patient.get_ngay_vao(),
							patient.get_ngay_ra(),patient.get_so_ngay_dtri(),patient.get_ket_qua_dtri(),patient.get_tinh_trang_rv(),
							patient.get_ngay_ttoan() ,patient.get_muc_huong(),patient.get_t_tongchi(),
							patient.get_t_bntt(),patient.get_t_bhtt(),patient.get_t_nguonkhac(),patient.get_t_ngoaids(),
							patient.get_nam_qt() ,patient.get_thang_qt(),patient.get_ma_loaikcb() ,patient.get_ma_cskcb(),
							patient.get_ma_khuvuc(),patient.get_ma_pttt_qt(),patient.get_t_thuoc(),
							patient.get_t_vtyt(),patient.get_can_nang(),patient.get_ma_khoa()};				
					k++;
				};					
			}			
			return data;			
		}
	  private String[] getTable1Columns() {
          return new String[] { "Mã LK", "STT",
        		  "Mã BN","Họ tên","Ngày sinh","Giới tính",
        		  "Địa chỉ","Mã thẻ","Mã ĐKBĐ","Giá trị thẻ từ",
        		  "Giá trị thẻ đến","Mã bệnh","Mã bệnh khác","Tên bệnh",
        		  "Mã Ldo Vv","Mã nơi chuyển","Mã tai nạn","Ngày vào",
        		  "Ngày ra","Số ngày đtrị","Kết quả đtrị","T.trạng ra viện",
        		  "Ngày t.toán","Mức hưởng","Tổng chi",
        		  "Tiền BNTT","Tiền BHTT","Tiền khác","Tiền ngoài DS",
        		  "Năm qtoán","Tháng qtoán","Mã loại KCB","Mã CSKCB",
        		  "Mã KV","Mã PTTTQT", "Tiền thuốc",
        		  "Tiền VTYT","Cân nặng","Mã khoa"};
	  	}
	  
	  private Object[][] getTable2Data() {

		  DecimalFormat df = new DecimalFormat("#.00");
		  XMLService xmlService = new XMLService();

			Medicines medicines = xmlService.getMedicines();

			Object[][] data = new Object[medicines.getMedicinesList().size()][];

			for (int i = 0; i < data.length; i++) {
				Medicine medicine = medicines.getMedicinesList().get(i);
				data[i] = new Object[] { medicine.get_ma_lk(), medicine.get_stt(),medicine.get_ma_thuoc(),medicine.get_ma_nhom(),
						medicine.get_ten_thuoc(),medicine.get_don_vi_tinh(),medicine.get_ham_luong(),medicine.get_duong_dung(),
						medicine.get_so_dang_ky(),df.format(medicine.get_so_luong()),medicine.get_don_gia(),medicine.get_tyle_tt(),
						medicine.get_thanh_tien(),medicine.get_ma_khoa(),medicine.get_ma_bac_si(),medicine.get_ma_benh(),
						medicine.get_ngay_yl(),medicine.get_lieu_dung(),medicine.get_ma_pttt()};
			}

			return data;
		}
	  private Object[][] getTable2DataByCode(String code_lk) {

		  XMLService xmlService = new XMLService();
		  Medicines medicines = xmlService.getMedicines();
			int MaxRow = medicines.getMedicinesList().size();
			int j=0;
			for (int i=0;i<MaxRow;i++)
			{Medicine medicine = medicines.getMedicinesList().get(i);
			String tmp = medicine.get_ma_lk();
			if (tmp.equals(code_lk)) {j++;}
			}			
			Object[][] data = new Object[j][];			
			int k=0;
			for (int i = 0; i < MaxRow; i++) {				
				Medicine medicine = medicines.getMedicinesList().get(i);
				String tmp = medicine.get_ma_lk();				
				if (tmp.equals(code_lk)) {			
					data[k] = new Object[] { medicine.get_ma_lk(), medicine.get_stt(),medicine.get_ma_thuoc(),medicine.get_ma_nhom(),
							medicine.get_ten_thuoc(),medicine.get_don_vi_tinh(),medicine.get_ham_luong(),medicine.get_duong_dung(),
							medicine.get_so_dang_ky(),medicine.get_so_luong(),medicine.get_don_gia(),medicine.get_tyle_tt(),
							medicine.get_thanh_tien(),medicine.get_ma_khoa(),medicine.get_ma_bac_si(),medicine.get_ma_benh(),
							medicine.get_ngay_yl(),medicine.get_lieu_dung(),medicine.get_ma_pttt()};				
					k++;
				};					
			}			
			return data;			
		}
	  private String[] getTable2Columns() {
        return new String[] { "Mã LK", "STT","Mã thuốc","Mã nhóm","Tên thuốc","ĐVT","Hàm lượng","Đường dùng","SĐK","SL",
        		"Đơn giá","Tỷ lệ TT","Thành tiền","Mã khoa","Mã BS","Mã bệnh","Ngày YL","Liều dùng","Mã PTTT"};
	  	}
	  
	  private Object[][] getTable3Data() {

		  
		  XMLService xmlService = new XMLService();

			Services services = xmlService.getServices();

			Object[][] data = new Object[services.getServicesList().size()][];

			for (int i = 0; i < data.length; i++) {

				Service service = services.getServicesList().get(i);
				data[i] = new Object[] { service.get_ma_lk(), service.get_stt(),service.get_ma_dich_vu(),
						service.get_ma_vat_tu(),service.get_ma_nhom(),service.get_ten_dich_vu(),service.get_don_vi_tinh(),
						service.get_so_luong(),service.get_don_gia(),service.get_tyle_tt(),service.get_thanh_tien(),
						service.get_ma_khoa(),service.get_ma_bac_si(),service.get_ma_benh(),service.get_ngay_yl(),
						service.get_ngay_kq(),service.get_ma_pttt()};
			}

			return data;
		}
	  private Object[][] getTable3DataByCode(String code_lk) {

		  XMLService xmlService = new XMLService();
		  Services services = xmlService.getServices();
			int MaxRow = services.getServicesList().size();
			int j=0;
			for (int i=0;i<MaxRow;i++)
			{Service service = services.getServicesList().get(i);
			String tmp = service.get_ma_lk();
			if (tmp.equals(code_lk)) {j++;}
			}			
			Object[][] data = new Object[j][];			
			int k=0;
			for (int i = 0; i < MaxRow; i++) {				
				Service service = services.getServicesList().get(i);
				String tmp = service.get_ma_lk();				
				if (tmp.equals(code_lk)) {			
					data[k] = new Object[] { service.get_ma_lk(), service.get_stt(),service.get_ma_dich_vu(),
							service.get_ma_vat_tu(),service.get_ma_nhom(),service.get_ten_dich_vu(),service.get_don_vi_tinh(),
							service.get_so_luong(),service.get_don_gia(),service.get_tyle_tt(),service.get_thanh_tien(),
							service.get_ma_khoa(),service.get_ma_bac_si(),service.get_ma_benh(),service.get_ngay_yl(),
							service.get_ngay_kq(),service.get_ma_pttt()};				
					k++;
				};					
			}			
			return data;			
		} 
	 
	  private String[] getTable3Columns() {
        return new String[] { "Mã LK", "STT","Mã DV","Mã VT","Mã nhóm","Tên dịch vụ","ĐVT","SL","Đơn giá","Tỷ lệ TT",
        		"Thành tiền","Mã khoa","Mã BS","Mã bệnh","Ngày y lệnh","Ngày trả KQ","Mã PTTT"};
	  	}
	

}
