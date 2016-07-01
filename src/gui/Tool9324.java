/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static gui.LayDuLieuBVTT.getDuongDung;
import static gui.LayDuLieuBVTT.getHamLuong;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import xyz.btq.textlib.*;

/**
 *
 * @author soft1
 */
public class Tool9324 {

    public static String maBH = "";
    public static String iNgay = "";
    public static boolean baoGomTienBNtuTra = false;
    public static double canTrenBHYT = 172500;
    public static double tiletraituyentheohangbenhvien = 60;
    public static String listDVKTNgoaiDanhMuc = "-1";
    public static final String the100 = "CC,TE,CK,CB,KC,BT,HN,DT,DK,XD,TS,QN,CA,CY";
    public static final String the95 = "HT,TC,CN";
    public static final String the80 = "DN,HX,CH,NN,TK,HC,XK,TB,NO,CT,XB,TN,CS,XN,MS,HD,TQ,TA,TY,HG,LS,HS,SV,GB,GD";
    public static ArrayList<String> dmdc_thuoc = null;
    public static final String[] tagname_bang1 = {"bang1_9324", "ma_lk", "stt", "ma_bn", "ho_ten", "ngay_sinh", "gioi_tinh", "dia_chi", "ma_the", "ma_dkbd", "gt_the_tu",
        "gt_the_den", "ten_benh", "ma_benh", "ma_benhkhac", "ma_lydo_vvien", "ma_noi_chuyen", "ma_tai_nan", "ngay_vao", "ngay_ra", "so_ngay_dtri",
        "ket_qua_dtri", "tinh_trang_rv", "ngay_ttoan", "muc_huong", "t_thuoc", "t_vtyt", "t_tongchi", "t_bntt", "t_bhtt", "t_nguonkhac",
        "t_ngoaids", "nam_qt", "thang_qt", "ma_loai_kcb", "ma_khoa", "ma_cskcb", "ma_khuvuc", "ma_pttt_qt", "can_nang"};
    public static final String[] tagname_bang2 = {"bang2_9324", "ma_lk", "stt", "ma_thuoc", "ma_nhom", "ten_thuoc", "don_vi_tinh", "ham_luong", "duong_dung", "lieu_dung", "so_dang_ky",
        "so_luong", "don_gia", "tyle_tt", "thanh_tien", "ma_khoa", "ma_bac_si", "ma_benh", "ngay_yl", "ma_pttt"};
    public static final String[] tagname_bang3 = {"bang3_9324", "ma_lk", "stt", "ma_dich_vu", "ma_vat_tu", "ma_nhom", "ten_dich_vu", "don_vi_tinh", "so_luong", "don_gia", "tyle_tt",
        "thanh_tien", "ma_khoa", "ma_bac_si", "ma_benh", "ngay_yl", "ngay_kq", "ma_pttt"};

    public static void Test() {
//        checkFileCheckout("d:\\xml\\201605191053_GD4221090408003_CheckOut.XML");
        listDVKTNgoaiDanhMuc="214";
    }
    public static void main(String[] args) {
        try {
//            xuly3Bang("b1.xml", "b2.xml", "b3.xml");
//            bang1To79a("b1.xml", maBH, maBH, baoGomTienBNtuTra);
//              b2ToExcel("b2.xml", "b2.xls");
//            System.out.println(tagname_bang2[16]);
//            File dir= new File (".");
//            System.out.println(dir.list()[1]);
//            ParseXmlEasy pxe= new ParseXmlEasy("    <Bang_CTDV>HAKHA</Bang_CTDV> ");
//            System.out.println(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
            
            if (args.length==0){
            b1ToExcel("b1.xml", "b1.xls");
            readAllXmlB2ToOneExcel("", "b2all.xls");
            readAllXmlB3ToOneExcel("", "b3all.xls");
            }
            xuly3xls();
        } catch (Exception ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void XML917ToCheckout(String filepath){
        ParseXmlEasy pxe= new ParseXmlEasy();
        pxe.setContentXML(filepath);
    
    }
    public static String getValueStringXML(String strXML) {
        return strXML.substring(strXML.indexOf('>') + 1, strXML.lastIndexOf('<'));
    }

    public static String getNgayThang(String value) {
        return value.substring(6, 8) + "/" + value.substring(4, 6) + "/" + value.substring(0, 4);
    }

    public static boolean downloadAll(String ngaybd, String ngaykt, String maBH, String tentinh) {
        String sUrl = "";
        String ngaybd_b2 = "";
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(simple.parse(ngaybd));
            c.add(Calendar.DATE, -60);
            ngaybd_b2 = simple.format(c.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean ret = false;
        try {
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang1?tungay=" + ngaybd + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            URL url = new URL(sUrl);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(new File("b1.xml"));
            int length = -1;
            byte[] buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();

            //------------------Bang 2-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            fos = new FileOutputStream(new File("b2.xml"));
            length = -1;
            buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            //-----------------bang 3-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            fos = new FileOutputStream(new File("b3.xml"));
            length = -1;
            buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();

            ///---Ket thuc
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }

    public static boolean downloadB1(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean ret = false;
        String sUrl = "";
        try {
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang1?tungay=" + ngaybd + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            URL url = new URL(sUrl);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(new File(maBH+ngaybd+ngaykt+"_B1.xml"));
            int length = -1;
            byte[] buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }

    public static boolean downloadB2(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean ret = false;
        String sUrl = "";
        try {
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            URL url = new URL(sUrl);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(new File(maBH+ngaybd+ngaykt+"_B2.xml"));
            int length = -1;
            byte[] buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }

    public static boolean downloadB3(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean ret = false;
        String sUrl = "";
        try {
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            URL url = new URL(sUrl);
            InputStream in = url.openStream();
            FileOutputStream fos = new FileOutputStream(new File(maBH+ngaybd+ngaykt+"_B3.xml"));
            int length = -1;
            byte[] buffer = new byte[1024];// buffer for portion of data from connection
            while ((length = in.read(buffer)) > -1) {
                fos.write(buffer, 0, length);
            }
            fos.close();
            in.close();
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }

    public static void xuly3Bang(String pathB1, String pathB2, String pathB3, boolean baoGomTienTuTra) {
        baoGomTienBNtuTra = baoGomTienTuTra;
        xuly3Bang(pathB1, pathB2, pathB3);
    }

    public static void bang1To79a(String pathB1, String pathB2, String pathB3, boolean baoGomTienTuTra) throws Exception {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sample sheet");

        Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "Bat dau tao file b1.xml");
        File f1 = new File(pathB1);

        PrintWriter fos1 = new PrintWriter(new File("processed_" + f1.getName()));
        Scanner sc;
        sc = new Scanner(new File(pathB1), "UTF-8");
        String line;
        List<String> lst = new ArrayList<String>();
        List<String> lstNgoaiTru = new ArrayList<String>();
        List<String> lstNoiTru = new ArrayList<String>();
        List<HoSoKCB> lstB1 = new ArrayList<HoSoKCB>();
        while (sc.hasNextLine()) {
            line = sc.nextLine().trim();
            if (line.indexOf("<ma_lk>") >= 0) {
                String malk = line.trim();
                lst.add(malk);
                HoSoKCB hs = new HoSoKCB();
                hs.sRow1 = new String[40];
                hs.sRow1[1] = malk;
                for (int i = 2; i <= 39; i++) {
                    hs.sRow1[i] = sc.nextLine().trim();
                }
                lstB1.add(hs);
                //read </row>
                line = sc.nextLine().trim();
            }
        }
        //--Ket thuc doc du lieu
        //--Ghi du lieu
        PrintWriter fos_ngoai = new PrintWriter(new File("ngoaitru_b1.xml"));
        PrintWriter fos_noi = new PrintWriter(new File("noitru_b1.xml"));
        fos1.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        fos1.println("<Bang1_BYT>");
        fos_ngoai.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        fos_ngoai.println("<Bang1_BYT>");
        fos_noi.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
        fos_noi.println("<Bang1_BYT>");
        int iSL_ngoaitru = 0;
        int iSL_noitru = 0;
        for (int i = 0; i < lstB1.size(); i++) {
            fos1.println("<row>");
            double dMucHuong = 100;
            boolean traituyen = false;
            //dMucHuong=lstB1.get(i).sRow1[24];
            //System.out.println(lstB1.get(i).sRow1[24].substring(lstB1.get(i).sRow1[24].indexOf('>')+1,lstB1.get(i).sRow1[24].lastIndexOf('<')));
            //24 muc huong
            //15 dung tuyen
            dMucHuong = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[24]));
            traituyen = getValueStringXML(lstB1.get(i).sRow1[15]).equals("3");
            //36 maBH
            maBH = getValueStringXML(lstB1.get(i).sRow1[36]);
            if (traituyen) {
                String sMaThe = "";
                String sDoiTuong = "";
                sMaThe = getValueStringXML(lstB1.get(i).sRow1[8]);
                sDoiTuong = sMaThe.substring(0, 2).toUpperCase();
                if (dMucHuong == tiletraituyentheohangbenhvien) {
                    if (the100.indexOf(sDoiTuong) >= 0) {
                        dMucHuong = tiletraituyentheohangbenhvien;
                    }
                    if (the95.indexOf(sDoiTuong) >= 0) {
                        dMucHuong = tiletraituyentheohangbenhvien * 0.95;
                    }
                    if (the80.indexOf(sDoiTuong) >= 0) {
                        dMucHuong = tiletraituyentheohangbenhvien * 0.8;
                    }
                }

                System.out.println("Sửa mức bảo hiểm " + getValueStringXML(lstB1.get(i).sRow1[1]) + getValueStringXML(lstB1.get(i).sRow1[4]));
                lstB1.get(i).sRow1[24] = "<muc_huong>" + dMucHuong + "</muc_huong>";
                //System.out.println(sDoiTuong);
            }
            if (!baoGomTienBNtuTra) {
                //27 tong chi
                //28 benh nhan
                //29 bao hiem
                double tienbaohiem = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[29]));
                double tongchiOrigin = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[27]));
                double tienbenhnhanOrigin = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[28]));

                double tongchi = tongchiOrigin;
                tongchi = Math.round(tienbaohiem / dMucHuong * 100);
                double tienbenhnhan = tongchi - tienbaohiem;
                boolean daSua = (Math.abs(tienbenhnhanOrigin - tienbenhnhan) > 10);
                //---------------------------------------
                if (tongchiOrigin < canTrenBHYT) {
                    tongchi = tienbaohiem;
                    tienbenhnhan = 0;
                    daSua = true;
                }
                //Gan gia tri moi
                if (daSua) {
                    System.out.println("Sua thanh toan --" + getValueStringXML(lstB1.get(i).sRow1[1]) + getValueStringXML(lstB1.get(i).sRow1[4]) + " " + Math.abs(tienbenhnhanOrigin - tienbenhnhan));
                    lstB1.get(i).sRow1[27] = "<t_tongchi>" + (long) tongchi + "</t_tongchi>";
                    lstB1.get(i).sRow1[28] = "<t_bntt>" + (long) tienbenhnhan + "</t_bntt>";
                }
            }
            for (int j = 1; j <= 39; j++) {
                fos1.println(lstB1.get(i).sRow1[j]);
            }
            fos1.println("</row>");
            //Ngoai tru
            if (lstB1.get(i).sRow1[34].indexOf("1") >= 0) {
                iSL_ngoaitru++;
                fos_ngoai.println("<row>");
                Row row = sheet.createRow(i);
                //lay cot 0--9 thanh cot 0 9 cua 79a Cot0 = A2
                for (int j = 2; j <= 11; j++) {
                    row.createCell(j - 2).setCellValue(getValueStringXML(lstB1.get(i).sRow1[j]).toLowerCase());
                }
                row.createCell(0).setCellValue(getValueStringXML(lstB1.get(i).sRow1[1]).toLowerCase());
                row.createCell(8).setCellValue(getNgayThang(getValueStringXML(lstB1.get(i).sRow1[10]).toLowerCase()));
                row.createCell(9).setCellValue(getNgayThang(getValueStringXML(lstB1.get(i).sRow1[11]).toLowerCase()));
                //10-13 Cot 10 = A13 Cot 13=A16
                for (int j = 13; j <= 16; j++) {
                    row.createCell(j - 3).setCellValue(getValueStringXML(lstB1.get(i).sRow1[j]).toLowerCase());
                }
                //14 - Cot 14=A18 Cot 18 A22
                for (int j = 18; j <= 22; j++) {
                    row.createCell(j - 4).setCellValue(getValueStringXML(lstB1.get(i).sRow1[j]).toLowerCase());
                }

                row.createCell(14).setCellValue(getNgayThang(getValueStringXML(lstB1.get(i).sRow1[18]).toLowerCase()));
                row.createCell(15).setCellValue(getNgayThang(getValueStringXML(lstB1.get(i).sRow1[19]).toLowerCase()));
                //Tong chi Cot 19
                row.createCell(19).setCellValue(getValueStringXML(lstB1.get(i).sRow1[27]).toLowerCase());
                //Cot 20 XN
                row.createCell(20).setCellValue("TienXN");
                //Cot 21 CDHA
                row.createCell(21).setCellValue("CDHA");
                //Cot 22 Thuoc
                row.createCell(22).setCellValue(getValueStringXML(lstB1.get(i).sRow1[25]).toLowerCase());
                for (int j = 23; j <= 28; j++) {
                    row.createCell(j).setCellValue("0");
                }
                //Cot 29 Kham
                row.createCell(29).setCellValue("10000");
                //Cot 30 van chuyen
                row.createCell(29).setCellValue("0");
                //31 BN tra
                row.createCell(31).setCellValue(getValueStringXML(lstB1.get(i).sRow1[28]).toLowerCase());
                //32 Bao hiem
                row.createCell(32).setCellValue(getValueStringXML(lstB1.get(i).sRow1[29]).toLowerCase());

                for (int j = 1; j <= 39; j++) {
                    fos_ngoai.println(lstB1.get(i).sRow1[j]);
                    //row.createCell(j).setCellValue(getValueStringXML(lstB1.get(i).sRow1[j]));
                }
                fos_ngoai.println("</row>");
                lstNgoaiTru.add(lstB1.get(i).sRow1[1]);
            } else //Noi tru
            {
                iSL_noitru++;
                lstNoiTru.add(lstB1.get(i).sRow1[1]);
//                    System.out.println("----------> "+lstB1.get(i).sRow1[1]);
                fos_noi.println("<row>");
                for (int j = 1; j <= 39; j++) {
                    fos_noi.println(lstB1.get(i).sRow1[j]);
                }
                fos_noi.println("</row>");
            }
        }
        fos1.println("</Bang1_BYT>");
        fos1.close();
        fos_ngoai.println("</Bang1_BYT>");
        fos_ngoai.close();
        fos_noi.println("</Bang1_BYT>");
        fos_noi.close();
        sc.close();
        Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "SO LUONG HO SO NGOAI TRU " + iSL_ngoaitru);
        Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "SO LUONG HO SO NOI TRU " + iSL_noitru);
        /**
         * Doc file B3 chia tien XN va CDHA
         *
         */
        String malk = "";
        String field[] = new String[17];
        sc = new Scanner(new File("b3.xml"), "UTF-8");
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.indexOf("<ma_lk>") >= 0) {
                malk = line.trim();
                if (lstNgoaiTru.contains(malk)) {
                    field[0] = malk;
                    for (int i = 1; i < 17; i++) {
                        field[i] = sc.nextLine();
                    }
                    sc.nextLine();//Read </row> next
                    int findIndex = -1;
                    for (int kk = 0; kk < lstB1.size(); kk++) {
                        if (lstB1.get(kk).sRow1[1].equals(malk)) {
                            findIndex = kk;
                        }
                    }

                    //Xet nghiem
                    if (getValueStringXML(field[4]).equals("1")) {
                        lstB1.get(findIndex).tienXN += Double.parseDouble(getValueStringXML(field[10]));
                    }
                    if (getValueStringXML(field[4]).equals("2")) {
                        lstB1.get(findIndex).tienCDHA += Double.parseDouble(getValueStringXML(field[10]));
                    }
                    if (getValueStringXML(field[4]).equals("3")) {
                        lstB1.get(findIndex).tienCDHA += Double.parseDouble(getValueStringXML(field[10]));
                    }
                    //Khong tinh tien phu phi
                    if (getValueStringXML(field[2]).equals("214")) {
                        lstB1.get(findIndex).tienCDHA -= Double.parseDouble(getValueStringXML(field[10]));
                    }
                }
            }
        }
        sc.close();
        //View ket qua tinh toan tien
        for (int k = 0; k < lstB1.size(); k++) {
            if (lstNgoaiTru.contains(lstB1.get(k).sRow1[1])) {
//Khong can nhan ti le
//                       lstB1.get(k).tienXN= lstB1.get(k).tienXN* Integer.parseInt(getValueStringXML(lstB1.get(k).sRow1[24]))/100.0;
//                       lstB1.get(k).tienCDHA= lstB1.get(k).tienCDHA*Integer.parseInt(getValueStringXML(lstB1.get(k).sRow1[24]))/100.0;
                lstB1.get(k).tienXN = Math.round(lstB1.get(k).tienXN);
                lstB1.get(k).tienCDHA = Math.round(lstB1.get(k).tienCDHA);
//                    System.out.print("Benh nhan "+lstB1.get(k).sRow1[1]);
//                    System.out.print("Tien XN "+lstB1.get(k).tienXN);
//                     System.out.println(" --- Tien CDHA "+lstB1.get(k).tienCDHA);
                int findz = -1;
                for (int z = 0; z <= sheet.getLastRowNum(); z++) {
                    if (sheet.getRow(z).getCell(0).toString().equals(getValueStringXML(lstB1.get(k).sRow1[1]))) {
                        findz = z;
                    }
                }
                //System.out.println(findz);
                if (findz != -1) {
                    sheet.getRow(findz).getCell(20).setCellValue(lstB1.get(k).tienXN);
                    sheet.getRow(findz).getCell(21).setCellValue(lstB1.get(k).tienCDHA);
                }
            }
        }

        ////--------------Ghi XLS
        try {
            FileOutputStream out
                    = new FileOutputStream(new File("D:\\File79a_5.xls"));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void xuly3Bang(String pathB1, String pathB2, String pathB3) {
        PrintWriter fos1 = null;
        PrintWriter fos2 = null;
        PrintWriter fos3 = null;
        String dirPath;
        try {
            LayDuLieuBVTT.dmdc_thuoc = LayDuLieuBVTT.getDMDC();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "Bat dau tao file b1.xml");
            File f1 = new File(pathB1);
            dirPath = f1.getPath();
            fos1 = new PrintWriter(new File("processed_" + f1.getName()));
            Scanner sc;
            sc = new Scanner(new File(pathB1), "UTF-8");
            String line;
            List<String> lst = new ArrayList<String>();
            List<String> lstNgoaiTru = new ArrayList<String>();
            List<String> lstNoiTru = new ArrayList<String>();
            List<HoSoKCB> lstB1 = new ArrayList<HoSoKCB>();
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (line.indexOf("<ma_lk>") >= 0) {
                    String malk = line.trim();
                    lst.add(malk);
                    HoSoKCB hs = new HoSoKCB();
                    hs.sRow1 = new String[40];
                    hs.sRow1[1] = malk;
                    for (int i = 2; i <= 39; i++) {
                        hs.sRow1[i] = sc.nextLine().trim();
                    }
                    lstB1.add(hs);
                    //read </row>
                    line = sc.nextLine().trim();
                }
            }
            //--Ket thuc doc du lieu
            //--Ghi du lieu
            PrintWriter fos_ngoai = new PrintWriter(new File("ngoaitru_b1.xml"));
            PrintWriter fos_noi = new PrintWriter(new File("noitru_b1.xml"));
            fos1.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos1.println("<Bang1_BYT>");
            fos_ngoai.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos_ngoai.println("<Bang1_BYT>");
            fos_noi.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos_noi.println("<Bang1_BYT>");
            for (int i = 0; i < lstB1.size(); i++) {
                fos1.println("<row>");
                double dMucHuong = 100;
                boolean traituyen = false;
                //dMucHuong=lstB1.get(i).sRow1[24];
                //System.out.println(lstB1.get(i).sRow1[24].substring(lstB1.get(i).sRow1[24].indexOf('>')+1,lstB1.get(i).sRow1[24].lastIndexOf('<')));
                //24 muc huong
                //15 dung tuyen
                dMucHuong = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[24]));
                traituyen = getValueStringXML(lstB1.get(i).sRow1[15]).equals("3");
                //36 maBH
                maBH = getValueStringXML(lstB1.get(i).sRow1[36]);
                if (traituyen) {
                    String sMaThe = "";
                    String sDoiTuong = "";
                    sMaThe = getValueStringXML(lstB1.get(i).sRow1[8]);
                    sDoiTuong = sMaThe.substring(0, 2).toUpperCase();
                    if (dMucHuong == tiletraituyentheohangbenhvien) {
                        if (the100.indexOf(sDoiTuong) >= 0) {
                            dMucHuong = tiletraituyentheohangbenhvien;
                        }
                        if (the95.indexOf(sDoiTuong) >= 0) {
                            dMucHuong = tiletraituyentheohangbenhvien * 0.95;
                        }
                        if (the80.indexOf(sDoiTuong) >= 0) {
                            dMucHuong = tiletraituyentheohangbenhvien * 0.8;
                        }
                    }

                    System.out.println("Sửa mức bảo hiểm " + getValueStringXML(lstB1.get(i).sRow1[1]) + getValueStringXML(lstB1.get(i).sRow1[4]));
                    lstB1.get(i).sRow1[24] = "<muc_huong>" + dMucHuong + "</muc_huong>";
                    //System.out.println(sDoiTuong);
                }
                if (!baoGomTienBNtuTra) {
                    //27 tong chi
                    //28 benh nhan
                    //29 bao hiem
                    double tienbaohiem = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[29]));
                    double tongchiOrigin = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[27]));
                    double tienbenhnhanOrigin = Double.parseDouble(getValueStringXML(lstB1.get(i).sRow1[28]));

                    double tongchi = tongchiOrigin;
                    tongchi = Math.round(tienbaohiem / dMucHuong * 100);
                    double tienbenhnhan = tongchi - tienbaohiem;
                    boolean daSua = (Math.abs(tienbenhnhanOrigin - tienbenhnhan) > 10);
                    //---------------------------------------
                    if (tongchiOrigin < canTrenBHYT) {
                        tongchi = tienbaohiem;
                        tienbenhnhan = 0;
                        daSua = true;
                    }
                    //Gan gia tri moi
                    if (daSua) {
                        System.out.println("Sua thanh toan --" + getValueStringXML(lstB1.get(i).sRow1[1]) + getValueStringXML(lstB1.get(i).sRow1[4]) + " " + Math.abs(tienbenhnhanOrigin - tienbenhnhan));
                        lstB1.get(i).sRow1[27] = "<t_tongchi>" + (long) tongchi + "</t_tongchi>";
                        lstB1.get(i).sRow1[28] = "<t_bntt>" + (long) tienbenhnhan + "</t_bntt>";
                    }
                }
                for (int j = 1; j <= 39; j++) {
                    fos1.println(lstB1.get(i).sRow1[j]);
                }
                fos1.println("</row>");
                //Ngoai tru
                if (lstB1.get(i).sRow1[34].indexOf("1") >= 0) {
                    fos_ngoai.println("<row>");
                    for (int j = 1; j <= 39; j++) {
                        fos_ngoai.println(lstB1.get(i).sRow1[j]);
                    }
                    fos_ngoai.println("</row>");
                } else //Noi tru
                {
                    lstNoiTru.add(lstB1.get(i).sRow1[1]);
//                    System.out.println("----------> "+lstB1.get(i).sRow1[1]);
                    fos_noi.println("<row>");
                    for (int j = 1; j <= 39; j++) {
                        fos_noi.println(lstB1.get(i).sRow1[j]);
                    }
                    fos_noi.println("</row>");
                }
            }
            fos1.println("</Bang1_BYT>");
            fos1.close();
            fos_ngoai.println("</Bang1_BYT>");
            fos_ngoai.close();
            fos_noi.println("</Bang1_BYT>");
            fos_noi.close();
            sc.close();
            Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "COUNT LIST NOI TRU" + lstNoiTru.size());
            //----------------------Xu ly bang 2---------------------
            boolean ghiFileb2 = true;
            if (ghiFileb2) {
                Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "Bat dau tao file b2.xml");
                fos2 = new PrintWriter(new File("ngoaitru_b2.xml"));
                fos2.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos2.println("<Bang2_BYT>");
                fos_noi = new PrintWriter(new File("noitru_b2.xml"));
                fos_noi.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos_noi.println("<Bang2_BYT>");
                sc = new Scanner(new File(pathB2), "UTF-8");
                String malk = "";
                String malk_banghimoi = "";
                List<String> dsThuoc = new ArrayList<String>();
                String field[] = new String[19];
                while (sc.hasNextLine()) {
                    line = sc.nextLine().trim();
                    if (line.indexOf("<ma_lk>") >= 0) {
                        malk = line.trim();
                        if (!malk.equals(malk_banghimoi)) {
                            malk_banghimoi = malk;
                            // dsThuoc.clear();
                        }
                        if (lst.contains(malk)) {
                            field[0] = malk;
                            for (int i = 1; i < 19; i++) {
                                field[i] = sc.nextLine();
                            }
                            String sRow = "";
                            sRow = "<row>\r\n";

                            String sdk = "";
                            //F9: SDK; F2: Ma thuoc; F6: Ham luong; F7 Duong dung
                            // Null dang: <so_dang_ky></so_dang_ky>

                            field[9] = field[9].trim();
                            field[2] = field[2].trim();
                            field[6] = field[6].trim();
                            field[7] = field[7].trim();
                            sdk = field[9].substring(12, field[9].lastIndexOf("<"));
                            //Gan lai SDK duong dung ham luong                        
                            if (sdk.length() > 0) {
                                if (field[2].indexOf("<ma_thuoc></ma_thuoc") >= 0) {
                                    field[2] = "<ma_thuoc>" + sdk + "</ma_thuoc>";
                                }
                                if (field[6].indexOf("<ham_luong></ham_luong") >= 0) {
                                    field[6] = "<ham_luong>" + getHamLuong(sdk) + "</ham_luong>";
                                }
                                if (field[7].indexOf("<duong_dung></duong_dung") >= 0) {
                                    field[7] = "<duong_dung>" + getDuongDung(sdk) + "</duong_dung>";
                                }
                            } else {
                                field[9] = "<so_dang_ky>0</so_dang_ky>";
                            }
                            for (int i = 0; i < 19; i++) {
                                sRow += field[i] + "\r\n";
                            }
                            sc.nextLine();
                            sRow += "</row>";

                            String temp = "";
                            temp = field[0].trim() + field[9].trim() + field[10].trim() + field[11].trim() + field[17].trim().substring(9, 17);
                            if (dsThuoc.contains(temp)) {
                                Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "----" + field[17].trim().substring(9, 17));
                                //Gan sRow=""
                                sRow = "";
                            } else {
                                dsThuoc.add(temp);
                            }
                            if (sRow.length() > 0) {
                                fos2.println(sRow);
                                if (sRow.lastIndexOf("<ma_khoa>K01</ma_khoa>") > 0) {
                                    sRow = "";
                                }
                                if (sRow.lastIndexOf("<so_luong>0</so_luong>") > 0) {
                                    sRow = "";
                                }
                                if (lstNoiTru.contains(malk)) {
                                    System.out.println("CO DU LIEU");
                                    if (sRow.length() > 0) {
                                        fos_noi.println(sRow);
                                    }
                                }
                            }
                            Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, ".");
                        }
                    }
                }
                fos2.println("</Bang2_BYT>");
                fos2.close();
                fos_noi.println("</Bang2_BYT>");
                fos_noi.close();
                sc.close();
            }
            //----------------------Ket thuc xu ly bang 2------------
            //----------------------Xu ly bang 3---------------------
            boolean ghiFileb3 = true;
            if (ghiFileb3) {
                Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, "Bat dau tao bang 3");
                String malk = "";
                String field[] = new String[19];
                fos3 = new PrintWriter(new File("ngoaitru_b3.xml"));
                fos3.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos3.println("<Bang3_BYT>");
                fos_noi = new PrintWriter(new File("noitru_b3.xml"));
                fos_noi.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos_noi.println("<Bang3_BYT>");
                sc = new Scanner(new File(pathB3), "UTF-8");
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    if (line.indexOf("<ma_lk>") >= 0) {
                        malk = line.trim();
                        if (lst.contains(malk)) {
                            field[0] = malk;
                            for (int i = 1; i < 17; i++) {
                                field[i] = sc.nextLine();
                            }
                            sc.nextLine();//Read </row> next
                            field[9] = "<tyle_tt>100</tyle_tt>";
                            String sRow = "";
                            sRow = "<row>\r\n";
                            for (int i = 0; i < 17; i++) {
                                sRow += field[i] + "\r\n";
                            }
                            sRow += "</row>";
                            boolean printPhuPhi = false;
                            if (!printPhuPhi) {
                                if (field[2].indexOf("214") >= 0 && maBH.equals("22041")) {
                                    sRow = "";
                                }
                            }
                            System.out.println("LIST noi tru count:" + lstNoiTru.size());
                            //Them noi tru
                            if (lstNoiTru.contains(malk)) {
                                System.out.println("Co du lieu noi tru");
                                //Loai bo K01                                
                                if (field[11].indexOf("K01") >= 0) {
                                    sRow = "";
                                }
                                if (sRow.length() > 0) {
                                    fos_noi.println(sRow);
                                }
                            }
                            //--ghi du lieu bang B3
                            if (sRow.length() > 0) {
                                fos3.println(sRow);
                            }
                            Logger.getLogger(Tool9324.class.getName()).log(Level.INFO, ".");
                        }
                    }
                }
                fos3.println("</Bang3_BYT>");
                fos3.close();
                fos_noi.println("</Bang3_BYT>");
                fos_noi.close();
                sc.close();
            }
            //----------------------Ket thuc xu ly bang 3------------
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }

    public static void b1ToExcel(String filexml, String filexls) {
        try {
            boolean xoaBnTrung = true;
            ArrayList<String> lst_key_bang1 = new ArrayList<String>();
            lst_key_bang1.clear();
            String line;
            File workDir= new File (".");
            File fb1[]=workDir.listFiles();
            File fileB1=null;
            for (int i=0; i<fb1.length;i++){
                if (fb1[i].getName().toLowerCase().endsWith(filexml.toLowerCase())) {
                    fileB1=fb1[i];break;
                }
            }
            Scanner sc = new Scanner(fileB1, "UTF-8");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Bang 1");
            ParseXmlEasy pxe = new ParseXmlEasy();
            String malk = "";
            String malk_banghimoi = "";

            String field[] = new String[50];
            int sl = 0;
            Row header = null;
            header = sheet.createRow(0);
            for (int i = 0; i <= 39; i++) {
                header.createCell(i).setCellValue(tagname_bang1[i]);
            }
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (line.indexOf("<ma_lk>") >= 0) {
                    sl++;
                    malk = line.trim();
                    field[1] = malk;
                    Row row = sheet.createRow(sl);
//                    row.createCell(0).setCellValue(sl);                    
                    pxe.setContentXML(field[1]);
                    row.createCell(1).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                    for (int i = 2; i <= 39; i++) {
                        field[i] = sc.nextLine().trim();
                        if (!field[i].endsWith(">")) {
                            field[i]+=" "+sc.nextLine().trim();
                            System.out.println("FIX lỗi xuống dòng: "+field[i]);
                        }
                        pxe.setContentXML(field[i]);
                        row.createCell(i).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                    }
                    line = field[1] + field[8] + field[13] + field[18] + field[19] + field[35];
                    if (lst_key_bang1.contains(line)) {
                        row.createCell(0).setCellValue("del");
                        if (xoaBnTrung) {
                            sl--;
                        }
                    } else {
                        if (line.indexOf("K01") > 0) {
                            row.createCell(0).setCellValue("NGO" + field[1]+field[13]);
                        } else {
                            row.createCell(0).setCellValue("NOI" + field[1]+field[13]);
                        }
                        lst_key_bang1.add(line);
                    }
                    sc.nextLine();  //read ket thuc row
                    //---- Ket thuc xu ly thong thuong. Bat dau xu ly nang cao
                    //---- Xu ly tien ---
                    double dMucHuong = 100;
                    boolean traituyen = false;
                    //dMucHuong=lstB1.get(i).sRow1[24];
                    //System.out.println(lstB1.get(i).sRow1[24].substring(lstB1.get(i).sRow1[24].indexOf('>')+1,lstB1.get(i).sRow1[24].lastIndexOf('<')));
                    //24 muc huong
                    //15 dung tuyen
                    dMucHuong = Double.parseDouble(row.getCell(24).toString());
                    traituyen = row.getCell(15).toString().equals("3");
                    //36 maBH
                    maBH = row.getCell(36).toString();
                    if (traituyen) {
                        String sMaThe = "";
                        String sDoiTuong = "";
                        sMaThe = row.getCell(8).toString();
                        sDoiTuong = sMaThe.substring(0, 2).toUpperCase();
                        if (dMucHuong == tiletraituyentheohangbenhvien) {
                            if (the100.indexOf(sDoiTuong) >= 0) {
                                dMucHuong = tiletraituyentheohangbenhvien;
                            }
                            if (the95.indexOf(sDoiTuong) >= 0) {
                                dMucHuong = tiletraituyentheohangbenhvien * 0.95;
                            }
                            if (the80.indexOf(sDoiTuong) >= 0) {
                                dMucHuong = tiletraituyentheohangbenhvien * 0.8;
                            }
                        }
                        row.getCell(24).setCellValue(dMucHuong);
                        //System.out.println(sDoiTuong);
                    }// END IF Sua Trai Tuyen
//                    ---------------------
                    //Sua tien dong chi tra
                    boolean suaDongChiTra = true;
                    if (suaDongChiTra) {
                    //27 tong chi
                        //28 benh nhan
                        //29 bao hiem
                        double tienbaohiem = Double.parseDouble(row.getCell(29).toString());
                        double tongchiOrigin = Double.parseDouble(row.getCell(27).toString());
                        double tienbenhnhanOrigin = Double.parseDouble(row.getCell(28).toString());

                        double tongchi = tongchiOrigin;
                        tongchi = Math.round(tienbaohiem / dMucHuong * 100);
                        double tienbenhnhan = tongchi - tienbaohiem;
                        boolean daSua = (Math.abs(tienbenhnhanOrigin - tienbenhnhan) > 10);
                        //---------------------------------------
                        if (tongchiOrigin < canTrenBHYT) {
                            tongchi = tienbaohiem;
                            tienbenhnhan = 0;
                            daSua = true;
                        }
                        //Gan gia tri moi
                        if (daSua) {
//                        System.out.println("Sua thanh toan --" + getValueStringXML(lstB1.get(i).sRow1[1]) + getValueStringXML(lstB1.get(i).sRow1[4]) + " " + Math.abs(tienbenhnhanOrigin - tienbenhnhan));
//                        lstB1.get(i).sRow1[27] = "<t_tongchi>" + (long) tongchi + "</t_tongchi>";
//                        lstB1.get(i).sRow1[28] = "<t_bntt>" + (long) tienbenhnhan + "</t_bntt>";
                            row.getCell(27).setCellValue((long) tongchi);
                            row.getCell(28).setCellValue((long) tienbenhnhan);
                        }
                    }//ket thuc  IF dong chi tra
                }
            }
            sc.close();
            //Ghi excel
            try {
                FileOutputStream out
                        = new FileOutputStream(new File(filexls));
                workbook.write(out);
                out.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //-------------------- Doc Bang du lieu to excel ------
    public static void b2ToExcel(String filexml, String filexls) {
        try {
            boolean xoaThuocTrung = true;
            ArrayList<String> lst_key_bang2 = new ArrayList<String>();
            lst_key_bang2.clear();
            String line;
            Scanner sc = new Scanner(new File(filexml), "UTF-8");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Bang 2");
            ParseXmlEasy pxe = new ParseXmlEasy();
            String malk = "";
            String malk_banghimoi = "";
            List<String> dsThuoc = new ArrayList<String>();
            String field[] = new String[50];
            int sl = 0;
            Row header = null;
            header = sheet.createRow(0);
            for (int i = 0; i <= 19; i++) {
                header.createCell(i).setCellValue(tagname_bang2[i]);
            }
            while (sc.hasNextLine()) {
                line = sc.nextLine().trim();
                if (line.indexOf("<ma_lk>") >= 0) {
                    sl++;
                    malk = line.trim();
                    field[1] = malk;
                    Row row = sheet.createRow(sl);
//                    row.createCell(0).setCellValue(sl);                    
                    pxe.setContentXML(field[1]);
                    row.createCell(1).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                    for (int i = 2; i <= 19; i++) {
                        field[i] = sc.nextLine().trim();
                        pxe.setContentXML(field[i]);
                        row.createCell(i).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                    }
                    line = field[1] + field[10] + field[11] + field[15] + field[18];
                    //row.createCell(0).setCellValue(line);
                    if (lst_key_bang2.contains(line)) {
                        row.createCell(0).setCellValue("del");
                        if (xoaThuocTrung) {
                            sl--;
                        }
                    } else {
                        row.createCell(0).setCellValue(line);
                        lst_key_bang2.add(line);
                    }
                    sc.nextLine();  //read ket thuc row
                }
            }
            sc.close();
            //Ghi excel
            try {
                FileOutputStream out
                        = new FileOutputStream(new File(filexls));
                workbook.write(out);
                out.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //-------------------- Doc Bang du lieu to excel ------
    public static void readAllXmlB2ToOneExcel(String filexml, String filexls) {
        try {
            boolean xoaThuocTrung = true;
            ArrayList<String> lst_key_bang2 = new ArrayList<String>();
            lst_key_bang2.clear();
            String line;
            Scanner sc;//= new Scanner(new File(filexml), "UTF-8");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Bang 2");
            ParseXmlEasy pxe = new ParseXmlEasy();
            String malk = "";
            String malk_banghimoi = "";
            List<String> dsThuoc = new ArrayList<String>();
            String field[] = new String[50];
            int sl = 0;
            Row header = null;
            header = sheet.createRow(0);
            for (int i = 0; i <= 19; i++) {
                header.createCell(i).setCellValue(tagname_bang2[i]);
            }

            File filedir = new File(".");
            File[] listfile = filedir.listFiles();
            for (int k = 0; k < listfile.length; k++) {
                if (listfile[k].getName().toLowerCase().endsWith("b2.xml")) {
                    sc = new Scanner(listfile[k], "UTF-8");
                    while (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                        if (line.indexOf("<ma_lk>") >= 0) {
                            sl++;
                            malk = line.trim();
                            field[1] = malk;
                            Row row = sheet.createRow(sl);
                            //                    row.createCell(0).setCellValue(sl);                    
                            pxe.setContentXML(field[1]);
                            row.createCell(1).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                            for (int i = 2; i <= 19; i++) {
                                field[i] = sc.nextLine().trim();
                                //Fix loi ky tu la xuong dong                                
                                if (!field[i].endsWith(">")) {
                                    field[i]+=" "+sc.nextLine().trim();
                                    System.out.println("FIX lỗi xuống dòng: "+field[i]);
                                }
                                
                                pxe.setContentXML(field[i]);
                                row.createCell(i).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                            }
                            line = field[1] + field[10] + field[11] + field[15] + field[17]+ field[18];
                            //row.createCell(0).setCellValue(line);
                            if (lst_key_bang2.contains(line)) {
                                row.createCell(0).setCellValue("del");
                                if (xoaThuocTrung) {
                                    sl--;
                                }
                            } else {
                                //row.createCell(0).setCellValue(line);
                                if (line.indexOf("K01") > 0) {
                                    row.createCell(0).setCellValue("NGO" + field[1]+field[17]);
                                } else {
                                    row.createCell(0).setCellValue("NOI" + field[1]+field[17]);
                                }
                                lst_key_bang2.add(line);
                                //---- Ket thuc xu ly thong thuong. Xu ly nang cao tai day
                                //---- FIX Tam thoi ma thuoc Ham luong duong dung sdk
                                if (row.getCell(3).toString().length() == 0) {
                                    row.getCell(3).setCellValue("MATHUOC");
                                }
                                if (row.getCell(7).toString().length() == 0) {
                                    row.getCell(7).setCellValue("0");
                                }
                                if (row.getCell(8).toString().length() == 0) {
                                    row.getCell(8).setCellValue("1.01");
                                }
                                if (row.getCell(10).toString().length() == 0) {
                                    //SDK NULL dan bang MADUNGCHUNG hoac bang SDK
                                    if (row.getCell(3).toString().equalsIgnoreCase("MATHUOC"))
                                        row.getCell(10).setCellValue("SDK");
                                    else row.getCell(10).setCellValue(row.getCell(3).toString());
                                }
                                if (row.getCell(3).toString().equalsIgnoreCase("MATHUOC"))
                                    if (row.getCell(10).toString().equalsIgnoreCase("SDK"))
                                        System.out.println("-->Chưa map DM:" +field[5]+field[6]+field[7]);
                                    else row.getCell(3).setCellValue(row.getCell(10).toString()); 
                                        
                                //---- END FIX tam thoi
                            }
                            sc.nextLine();  //read ket thuc row
                        }
                    }
                    sc.close();
                }//Ket thuc IF endwith b2.xml                
            }
            //Ghi excel
            try {
                FileOutputStream out
                        = new FileOutputStream(new File(filexls));
                workbook.write(out);
                out.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //-------------------- Doc Bang du lieu 3 to excel ------
    public static void readAllXmlB3ToOneExcel(String filexml, String filexls) {
        try {
            boolean xoaDvktTrung = true;
            ArrayList<String> lst_key_bang3 = new ArrayList<String>();
            lst_key_bang3.clear();
            String line;
            Scanner sc;//= new Scanner(new File(filexml), "UTF-8");
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Bang 3");
            ParseXmlEasy pxe = new ParseXmlEasy();
            String malk = "";
            String malk_banghimoi = "";
            List<String> dsThuoc = new ArrayList<String>();
            String field[] = new String[50];
            int sl = 0;
            Row header = null;
            header = sheet.createRow(0);
            for (int i = 0; i <= 17; i++) {
                header.createCell(i).setCellValue(tagname_bang3[i]);
            }

            File filedir = new File(".");
            File[] listfile = filedir.listFiles();
            for (int k = 0; k < listfile.length; k++) {
                if (listfile[k].getName().toLowerCase().endsWith("b3.xml")) {
                    sc = new Scanner(listfile[k], "UTF-8");
                    while (sc.hasNextLine()) {
                        line = sc.nextLine().trim();
                        if (line.indexOf("<ma_lk>") >= 0) {
                            sl++;
                            System.out.print(" " + sl);
                            malk = line.trim();
                            field[1] = malk;
                            Row row = sheet.createRow(sl);
                            pxe.setContentXML(field[1]);
                            row.createCell(1).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                            for (int i = 2; i <= 17; i++) {
                                field[i] = sc.nextLine().trim();
                                //Fix loi ky tu la xuong dong
                                if (!field[i].endsWith(">")) {
                                    field[i]+=" "+sc.nextLine().trim();
                                    System.out.println("FIX lỗi xuống dòng: "+field[i]);
                                }
                                pxe.setContentXML(field[i]);
                                row.createCell(i).setCellValue(pxe.getElementByTag(pxe.getTagName(pxe.getContentXML())));
                            }
                            //debug
//                            if (field[6].indexOf("Kh")>0){
////                                System.out.print(row.getCell(1).toString());
//                                System.out.println("--"+sl);
//                            }
                            //--end debug
                            line = field[1] + field[3] + field[12] + field[14] + field[15];
//                            System.out.println(line);
                            //line=malk_madv_makhoa_mabenh_ngayylenh
                            //row.createCell(0).setCellValue(line);
                            if (lst_key_bang3.contains(line)) {
//                                System.out.println("Da ton tai "+line);
                                row.createCell(0).setCellValue("del");
                                if (xoaDvktTrung) {
                                    sl--;
                                }
                            } else {
//                                row.createCell(0).setCellValue(line);
                                if (line.indexOf("K01") > 0) {
                                    row.createCell(0).setCellValue("NGO" + field[1]+field[14]);
                                } else {
                                    row.createCell(0).setCellValue("NOI" + field[1]+field[14]);
                                }
                                lst_key_bang3.add(line);
                                //-------------------------------------------------------------------------
                                //---- Ket thuc xu ly nguyen ban. XU LY nang cao tai day
                                //---- xu ly sua ti le thanh toan
                                row.getCell(10).setCellValue("100");

                                //---- Xu ly bo sung ma dich vu
                                if (row.getCell(3).toString().length() == 0) {  //Null MaDV
                                    row.getCell(3).setCellValue("0");
                                }
                                //---- xu ly loai bo phu phi 214
                                if (row.getCell(3).toString().length() > 0 && listDVKTNgoaiDanhMuc.indexOf(row.getCell(3).toString()) >= 0) {
                                    sl--;
                                }
                            }
                            sc.nextLine();  //read ket thuc row
                        }
                    }
                    sc.close();
                }//Ket thuc IF endwith b3.xml                
            }
            //Ghi excel
            try {
                FileOutputStream out
                        = new FileOutputStream(new File(filexls));
                workbook.write(out);
                out.close();
                System.out.println("Excel written successfully..");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //------Xu ly 3 file excel
    public static void xuly3xls() {
        try {
            FileInputStream f1 = new FileInputStream("b1.xls");
            HSSFWorkbook w1 = new HSSFWorkbook(f1);
            HSSFSheet sheet1 = w1.getSheet("Bang 1");
            FileInputStream f2 = new FileInputStream("b2all.xls");
            HSSFWorkbook w2 = new HSSFWorkbook(f2);
            HSSFSheet sheet2 = w2.getSheet("Bang 2");
            FileInputStream f3 = new FileInputStream("b3all.xls");
            HSSFWorkbook w3 = new HSSFWorkbook(f3);
            HSSFSheet sheet3 = w3.getSheet("Bang 3");
            Row r1, r2, r3;
            int find2 = -1, find3 = -1;
            double b1_tientong=0;
            double b1_tienthuoc=0;
            double b1_tienvattu=0;
            double b1_tiendv=0;
            double b2_tienthuoc=0;
            double b3_tiendv=0;
            String hosokcbviettel = "";
            boolean checktien=false;
            for (int i1 = 1; i1 <= sheet1.getLastRowNum(); i1++) {
//                Row r= sheet1.getRow(i1);
//                System.out.println(r.getCell(4));
                r1 = sheet1.getRow(i1);
                hosokcbviettel = "";
                hosokcbviettel += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n";
                hosokcbviettel += "<CHECKOUT>\r\n";
                hosokcbviettel += "<THONGTINBENHNHAN/>\r\n";
                hosokcbviettel += "<THONGTINCHITIET>\r\n";
                hosokcbviettel += "<tonghop>\r\n";
                for (int k = 1; k <= 39; k++) {
                    hosokcbviettel += "\t<" + tagname_bang1[k] + ">" + r1.getCell(k).toString() + "</" + tagname_bang1[k] + ">\r\n";
                }
                b1_tientong=Double.parseDouble(r1.getCell(27).toString());
                b1_tienthuoc=Double.parseDouble(r1.getCell(25).toString());
                b1_tienvattu=Double.parseDouble(r1.getCell(26).toString());
                b1_tiendv= b1_tientong-b1_tienthuoc -b1_tienvattu;
                hosokcbviettel += "</tonghop>\r\n";
//                System.out.println(hosokcbviettel);
//                hosokcbviettel+="<Bang_CTTHUOC>";
                String chitietthuoc = "";
                b2_tienthuoc=0;
                for (int i2 = 0; i2 <= sheet2.getLastRowNum(); i2++) {
                    r2 = sheet2.getRow(i2);
                    String temp = "";
                    if (r2.getCell(1).toString().equals(r1.getCell(1).toString()) && r2.getCell(0).toString().equals(r1.getCell(0).toString())) {
                        for (int k = 1; k <= 19; k++) {
                            temp += "\t<" + tagname_bang2[k] + ">" + r2.getCell(k).toString() + "</" + tagname_bang2[k] + ">\r\n";
                        }
                        chitietthuoc += "<ctthuoc>\r\n" + temp + "</ctthuoc>\r\n";
                        b2_tienthuoc+= Double.parseDouble(r2.getCell(14).toString());
                    }                    
                }
                if (chitietthuoc.length() > 0) {//BN co su dung thuoc
                    hosokcbviettel += "<Bang_CTTHUOC>\r\n" + chitietthuoc + "</Bang_CTTHUOC>\r\n";
                } else {//BN KHONG su dung thuoc
                    hosokcbviettel += "<Bang_CTTHUOC/>\r\n";
                }
                //--- Tao ho so dich vu ky thuat
                String chitietdichvu = "";
                b3_tiendv=0;
                for (int i3 = 0; i3 <= sheet3.getLastRowNum(); i3++) {
                    r3 = sheet3.getRow(i3);
                    String temp = "";
                    if (r3.getCell(1).toString().equals(r1.getCell(1).toString()) && r3.getCell(0).toString().equals(r1.getCell(0).toString())) {
                        for (int k = 1; k <= 17; k++) {
                            temp += "\t<" + tagname_bang3[k] + ">" + r3.getCell(k).toString() + "</" + tagname_bang3[k] + ">\r\n";
                        }
                        chitietdichvu += "<CTDV>\r\n" + temp + "</CTDV>\r\n";
                        b3_tiendv+=Double.parseDouble(r3.getCell(11).toString());
                    }
                }
                if (chitietdichvu.length() > 0) {//BN co su dung thuoc
                    hosokcbviettel += "<Bang_CTDV>\r\n" + chitietdichvu + "</Bang_CTDV>\r\n";
                } else {//BN KHONG su dung thuoc
                    hosokcbviettel += "<Bang_CTDV/>\r\n";
                }
                //----dong goi du lieu ho so KCB
                hosokcbviettel += "</THONGTINCHITIET>\r\n";
                hosokcbviettel += "</CHECKOUT>";

                //System.out.println(hosokcbviettel);
                //Check tien ho so
                checktien=(b1_tientong==b2_tienthuoc+b3_tiendv);
                if (b1_tienthuoc+b1_tienvattu==b2_tienthuoc) System.out.print("----- Bảng 2 Tiền thuốc ĐÚNG");
                if (b1_tiendv==b3_tiendv) System.out.print("----- Bảng 3 Tiền dịch vụ ĐÚNG");
                if (checktien) System.out.println("----HỒ SƠ CHÍNH XÁC----" +r1.getCell(4).toString());
                //-------GHi ho so vao file
                ghiDulieuStringXMLToFile(hosokcbviettel,checktien);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ghiDulieuStringXMLToFile(String valuexml,boolean success){
        String ngayvao = "";
        String mathe = "";
        ParseXmlEasy pxe = new ParseXmlEasy(valuexml);
        ngayvao = pxe.getElementByTag("ngay_vao");
        mathe = pxe.getElementByTag("ma_the");
        String tenfile = ngayvao + "_" + mathe + "_CheckOut.XML";        
        if (!success) tenfile="ERR_"+tenfile;
        File fout = new File("D:\\xml\\" + tenfile);
        try {
            PrintWriter out = new PrintWriter(fout, "UTF-8");
            out.print(valuexml);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public static void ghiDulieuStringXMLToFile(String valuexml){
        ghiDulieuStringXMLToFile(valuexml,true);
    }
    public static void checkFileCheckout(String filename){
        Scanner sc;
        try {
            sc = new Scanner (new File (filename),"UTF-8");        
        String s="";String line="";
        while (sc.hasNext()){
            line=sc.next();
            s+=line;
        }
           ParseXmlEasy pxe= new ParseXmlEasy(s);
           String ctt= pxe.getElementByTag("Bang_CTTHUOC");
            System.out.println(ctt);
            pxe.setContentXML("<root>"+ctt+"</root>");
            String tient[]= pxe.getElementsByTag("thanh_tien");
            for (int i=0; i<tient.length; i++){
                System.out.println(tient[i]);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void checkValueCheckout(String valuexml){
        String ngayvao = "";
        String mathe = "";
        ParseXmlEasy pxe = new ParseXmlEasy(valuexml);
        ngayvao = pxe.getElementByTag("ngay_vao");
        mathe = pxe.getElementByTag("ma_the");
        String tenfile = ngayvao + "_" + mathe + "_CheckOut.XML";        
        File fout = new File("D:\\xml\\" + tenfile);
        try {
            PrintWriter out = new PrintWriter(fout, "UTF-8");
            out.print(valuexml);
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Tool9324.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String deleteXML(String strEnd){
        String kq="Thanh cong";
        try{
        File f= new File (".");
        if (strEnd.equalsIgnoreCase(".xml")) f=new File ("D:\\xml");
        File fi[]= f.listFiles();
        for (int i=0; i<fi.length; i++){
            if (fi[i].getName().toLowerCase().endsWith(strEnd.toLowerCase())){
                if (fi[i].delete()) System.out.println("--Delete "+fi[i].getName());
            }
        }
        }catch(Exception e){    		
    		e.printStackTrace();  
                kq=e.getMessage();
    	}
        return kq;
    }
}
