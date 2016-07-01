package org.byt;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.byt.enu.Tag;
import org.byt.exception.FilePathException;
import org.byt.exception.MaLienKetException;
import org.byt.exception.NodeException;
import org.byt.object.Bang1;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BHYT {
	public static String duongdanbang1;
	public static String duongdanbang2;
	public static String duongdanbang3;
	public static String duongdanluufile;
	private static int dem = 0;
	// public static String lastMLK = "";
	/**
	 * danh sách bảng 1 với key là mã liên kết
	 */
	public static Map<String, Bang1> danhsachbang1 = new HashMap<String, Bang1>();

	public static void main(String[] args) {
            TaoHoSoKCB();
        }
        public static void TaoHoSoKCB() {
		try {

		duongdanluufile = "D:/xml";

		//get duong dan B1,B2,B3
		File file = new File("D:/temp");
		File[] listfile = file.listFiles();
		for (int i = 0; i < listfile.length; i++) {
			if(listfile[i].getName().endsWith("b1.xml"))
				duongdanbang1 = listfile[i].getPath();
			else if(listfile[i].getName().endsWith("b2.xml"))
				duongdanbang2 = listfile[i].getPath();
			else if(listfile[i].getName().endsWith("b3.xml"))
				duongdanbang3 = listfile[i].getPath();
			else listfile[i].delete();
		}
		//init hashmap
		danhsachbang1 = new HashMap<String, Bang1>();
		System.out.println("Please wait...");
		// Lập danh sách bảng 2 ứng với từng bảng 1
		CreateListTable2();
		// Lập danh sách bảng 3 ứng với từng bảng 1
		CreateListTable3();
		
		// open xml danh sách bảng 1
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(duongdanbang1);
		// Lấy phần tử Bang1_BYT
		Element root = (Element) xmlDoc.getDocumentElement();
		// Danh sách các row ở bảng 1
		NodeList nodeList = root.getChildNodes();
		String malk = "";
		for (int i = 0; i < nodeList.getLength(); i++) {
			// row
			Node row = nodeList.item(i);
			if (row.getNodeType() == row.ELEMENT_NODE) {
				Element bang1 = (Element) row;
				// get ma lien ket
				NodeList malienket = bang1.getElementsByTagName(Tag.MaLienKet.getCode());
				if (malienket == null || malienket.getLength() <= 0) {
					throw new MaLienKetException("Ma lien ket null");
				}
				malk = malienket.item(0).getTextContent();
				// thêm thông tin bảng 1
				try {
					if (!danhsachbang1.containsKey(malk))
						danhsachbang1.put(malk, new Bang1());
					danhsachbang1.get(malk).malk = malk;
					danhsachbang1.get(malk).name = bang1.getElementsByTagName(Tag.HoTen.getCode()).item(0)
							.getTextContent();
					danhsachbang1.get(malk).disease = bang1.getElementsByTagName(Tag.TenBenh.getCode()).item(0)
							.getTextContent();
				} catch (Exception e) {
					throw new Exception();
				}

				List<Node> danhsachbang2 = danhsachbang1.containsKey(malk) ? danhsachbang1.get(malk).danhsachbang2
						: new ArrayList<Node>();
				List<Node> danhsachbang3 = danhsachbang1.containsKey(malk) ? danhsachbang1.get(malk).danhsachbang3
						: new ArrayList<Node>();
				// save
				xmlDoc.renameNode(bang1, null, Tag.TongHop.getCode());
				createXML(malk, bang1, danhsachbang2, danhsachbang3);
			}
		}
		System.out.println("END");
		System.out.println("Tong so : " + dem + " file duoc tao");
		} catch (Exception e) {
			System.out.println("lỗi : " + e.getMessage());
		}

	}

	/**
	 * tạo danh sách bảng 2
	 */
	private static void CreateListTable2()
			throws MaLienKetException, ParserConfigurationException, SAXException, IOException, NodeException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(duongdanbang2);
		Element root = (Element) xmlDoc.getDocumentElement();
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			// row
			Node row = nodeList.item(i);
			if (row.getNodeType() == row.ELEMENT_NODE) {
				Element rowBang = (Element) row;
				//FIXME [‎Sunday, ‎March ‎20, ‎2016] fix đường dùng null
				//get duongdung
				NodeList nodeDuongdung = rowBang.getElementsByTagName(Tag.DuongDung.getCode());
				if (nodeDuongdung == null || nodeDuongdung.getLength() <= 0) {
					throw new NodeException("khong co the " + Tag.DuongDung.getCode());
				}
				if(nodeDuongdung.item(0).getTextContent()=="")
				nodeDuongdung.item(0).setTextContent("N/A");
				//END FIX
				//FIXME [‎Sunday, ‎March ‎20, ‎2016] fix mã thuốc null
				//get mathuoc
				NodeList nodeMaThuoc = rowBang.getElementsByTagName(Tag.MaThuoc.getCode());
				if (nodeMaThuoc == null || nodeMaThuoc.getLength() <= 0) {
					throw new NodeException("khong co the " + Tag.MaThuoc.getCode());
				}
				if(nodeMaThuoc.item(0).getTextContent()=="")
					nodeMaThuoc.item(0).setTextContent("N/A");
				//END FIX
				//FIXME [‎Sunday, ‎March ‎20, ‎2016] fix hàm lượng null
				//get hamluong
				NodeList nodeHamLuong = rowBang.getElementsByTagName(Tag.HamLuong.getCode());
				if (nodeHamLuong == null || nodeHamLuong.getLength() <= 0) {
					throw new NodeException("khong co the " + Tag.HamLuong.getCode());
				}
				if(nodeHamLuong.item(0).getTextContent()=="")
					nodeHamLuong.item(0).setTextContent("N/A");
				//END FIX
				//FIXME [‎Sunday, ‎March ‎20, ‎2016] fix số đăng ký null
				//get sodangky
				NodeList nodeSoDangKy = rowBang.getElementsByTagName(Tag.SoDangKy.getCode());
				if (nodeSoDangKy == null || nodeSoDangKy.getLength() <= 0) {
					throw new NodeException("khong co the " + Tag.SoDangKy.getCode());
				}
				if(nodeSoDangKy.item(0).getTextContent()=="")
					nodeSoDangKy.item(0).setTextContent("N/A");
				//END FIX
				// get ma lien ket
				NodeList malienket = rowBang.getElementsByTagName(Tag.MaLienKet.getCode());
				if (malienket == null || malienket.getLength() <= 0) {
					throw new MaLienKetException("Ma lien ket null");
				}
				String malk = malienket.item(0).getTextContent();
				// đổi tên
				xmlDoc.renameNode(row, null, Tag.ChiTietThuoc.getCode());
				// thêm bảng 2
				if (!danhsachbang1.containsKey(malk))
					danhsachbang1.put(malk, new Bang1());
				danhsachbang1.get(malk).danhsachbang2.add(row);
			}
		}
	}

	/**
	 * tạo danh sách bảng 3
	 * @throws NodeException 
	 */
	private static void CreateListTable3()
			throws MaLienKetException, ParserConfigurationException, SAXException, IOException, NodeException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// factory.setValidating(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(duongdanbang3);
		Element root = (Element) xmlDoc.getDocumentElement();
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			// row
			Node row = nodeList.item(i);
			if (row.getNodeType() == row.ELEMENT_NODE) {
				Element rowBang = (Element) row;
				//FIXME [‎Sunday, ‎March ‎20, ‎2016] fix ngày kết quả lỗi định dạng
				//get ngayketqua
				NodeList nodeNgayKQ = rowBang.getElementsByTagName(Tag.NgayKetQua.getCode());
				if (nodeNgayKQ == null || nodeNgayKQ.getLength() <= 0) {
					throw new NodeException("khong co the " + Tag.NgayKetQua.getCode());
				}
				if(nodeNgayKQ.item(0).getTextContent()=="")
					nodeNgayKQ.item(0).setTextContent("20161230");
				nodeNgayKQ.item(0).setTextContent(nodeNgayKQ.item(0).getTextContent().replace("-", ""));
				//END FIX
				//FIXME [‎Sunday, ‎March ‎20, ‎2016] fix mã bệnh null
				//get mabenh
				NodeList nodeMaBenh = rowBang.getElementsByTagName(Tag.MaBenh.getCode());
				if (nodeMaBenh == null || nodeMaBenh.getLength() <= 0) {
					throw new NodeException("khong co the " + Tag.MaBenh.getCode());
				}
				if(nodeMaBenh.item(0).getTextContent()=="")
					nodeMaBenh.item(0).setTextContent("N/A");
				//END FIX
				//FIXME [‎Sunday, ‎March ‎20, ‎2016] fix mã dịch vụ null
				//get madichvu
				NodeList nodeMaDichVu = rowBang.getElementsByTagName(Tag.MaDichVu.getCode());
				if (nodeMaDichVu == null || nodeMaDichVu.getLength() <= 0) {
					throw new NodeException("khong co the " + Tag.MaDichVu.getCode());
				}
				if(nodeMaDichVu.item(0).getTextContent()=="")
					nodeMaDichVu.item(0).setTextContent("N/A");
				//END FIX
				// Lấy mã liên kết
				NodeList malienket = rowBang.getElementsByTagName(Tag.MaLienKet.getCode());
				if (malienket == null || malienket.getLength() <= 0) {
					throw new MaLienKetException("Ma lien ket null");
				}
				String malk = malienket.item(0).getTextContent();
				// đổi tên
				xmlDoc.renameNode(row, null, Tag.ChiTietDichVu.getCode());
				// thêm bảng 3
				if (!danhsachbang1.containsKey(malk))
					danhsachbang1.put(malk, new Bang1());
				danhsachbang1.get(malk).danhsachbang3.add(row);
			}
		}
	}

	/**
	 * save xml
	 * 
	 * @param tenfile
	 *            tên file save
	 * @param bang1
	 *            Node chứa thông tin tổng hợp của từng người
	 * @param danhsachbang2
	 *            danh sách các Node chứa thông tin thuốc ứng với bảng 1
	 * @param danhsachbang3
	 *            danh sách các Node chứa thông tin dịch vụ ứng với bảng 1
	 * @param LastXml
	 *            true nếu đây là xml cuối cùng trong danh sách
	 * @throws FilePathException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	private static void createXML(final String tenfile, Node bang1, List<Node> danhsachbang2, List<Node> danhsachbang3)
			throws TransformerException, ParserConfigurationException, FilePathException, SAXException, IOException {
//		final Element eBang1 = (Element) bang1;
//		final List<String> danhsachthuoc = new ArrayList<String>();
//		final List<String> danhsachdichvu = new ArrayList<String>();
		// Create doc
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		// root elements
		Document doc = docBuilder.newDocument();
		doc.setXmlStandalone(true);
		Element rootElement = doc.createElement(Tag.CheckOut.getCode());
		doc.appendChild(rootElement);
		// thong tin benh nhan
		Element thogtinbenhnhan = doc.createElement(Tag.ThongTinBenhNhan.getCode());
		rootElement.appendChild(thogtinbenhnhan);
		// thong tin chi tiet
		Element thongtinchitiet = doc.createElement(Tag.ThongTinChiTiet.getCode());
		rootElement.appendChild(thongtinchitiet);
		// thong tin tong hop
		bang1 = doc.importNode(bang1, true);
		thongtinchitiet.appendChild(bang1);
		// Bang_CTTHUOC
		thongtinchitiet.appendChild(doc.createElement(Tag.BangChiTietThuoc.getCode()));
		for (Node node : danhsachbang2) {
			Node importnode = doc.importNode(node, true);
			if (importnode.getNodeType() == Node.ELEMENT_NODE)
				thongtinchitiet.getElementsByTagName(Tag.BangChiTietThuoc.getCode()).item(0).appendChild(importnode);
//			Element e = (Element) node;
//			danhsachthuoc.add(e.getElementsByTagName(Tag.TenThuoc.getCode()).item(0).getTextContent());
		}
		// Bang_CTDV
		thongtinchitiet.appendChild(doc.createElement(Tag.BangChiTietDichVu.getCode()));
		//FIXME [Bao][Sunday, ‎March ‎20, ‎2016] thêm giá khám bệnh, mặc định : Khám Lâm Sàng, Khám Chuyên Khoa
		
		//END FIX
		for (Node node : danhsachbang3) {
			Node importnode = doc.importNode(node, true);
			if (importnode.getNodeType() == Node.ELEMENT_NODE)
				thongtinchitiet.getElementsByTagName(Tag.BangChiTietDichVu.getCode()).item(0).appendChild(importnode);
//			Element e = (Element) node;
			//danhsachdichvu.add(e.getElementsByTagName(Tag.TenDichVu.getCode()).item(0).getTextContent());
		}
		// save xml to file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		File savefile = new  File(duongdanluufile + "/" + tenfile.replace("/", "_") + ".xml");
		if(savefile.exists()){
			System.out.println("WARN : Trung ma lien ket : " + savefile.getPath());
			dem--;
		}
		StreamResult result = new StreamResult(savefile);
		transformer.transform(source, result);
		// println
		dem++;
		System.out.println("File saved to " + duongdanluufile + "/" + tenfile.replace("/", "_") + ".xml");
	}
}
