/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import models.Medicine;

import models.Medicines;
import models.Patients;
import models.Services;

/**
 *
 * @author soft1
 */
public class LayDuLieuBVTT {

    public static ArrayList<String> dmdc_thuoc = null;

    public static void main(String[] args) throws Exception {
        boolean kq = false;
        //kq = getHosoV2("2016-04-20", "2016-04-21", "33031", "hungyen");        
        getHoso("2016-05-01", "2016-05-20", "22041", "quangninh2");
       
        //getHosoV3("2016-04-20", "2016-04-21", "33031", "hungyen");
//        String s="<so_dang_ky>VD-16980-12</so_dang_ky>";
//        System.out.println(s.substring(12,s.lastIndexOf("<")));
//        System.out.println(getHamLuong("VN-17922-14"));
//        System.out.println(getDuongDung("VD-21061-14"));

    }

    public static void dieuChinhTienBang1() {
        PrintWriter fos = null;
        try {
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "Hieu chinh file b1.xml");
            fos = new PrintWriter(new File("b1fix.xml"));
            Scanner sc;
            sc = new Scanner(new File("b1.xml"), "UTF-8");
            String line;
            String muchuong = "";
            String tongchi = "";
            String bhtt = "";
            String bntt = "";

            while (sc.hasNextLine()) {
                line = sc.nextLine();
                fos.println(line);
                if (line.indexOf("<row>") >= 0) {
                    for (int i = 1; i <= 24; i++) {
                        line = sc.nextLine();
                        fos.println(line);
                        muchuong = line.substring(10, line.indexOf("</"));
                        System.out.println(muchuong);
                    }
                    //thuoc
                    line = sc.nextLine();
                    fos.println(line);
                    //vattu
                    line = sc.nextLine();
                    fos.println(line);
                    //tongchi
                    line = sc.nextLine();
                    tongchi = line.substring(10, line.indexOf("</"));
                    fos.println(line);
                    //BN thanh toan
                    line = sc.nextLine();
                    bntt = line.substring(10, line.indexOf("</"));
                    fos.println(line);
                    //Bao Hiem thanh toan
                    line = sc.nextLine();
                    bhtt = line.substring(10, line.indexOf("</"));
                    fos.println(line);
                }

            }
            fos.close();
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "Done hieu chinh bang 1.xml");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fos.close();
        }
    }

    public static String getHamLuong(String sdk) {
        for (int i = 0; i < dmdc_thuoc.size(); i++) {
            if (dmdc_thuoc.get(i).startsWith(sdk)) {
                return dmdc_thuoc.get(i).split("#")[2];
            }
        }
        return "0";
    }

    public static String getDuongDung(String sdk) {
        for (int i = 0; i < dmdc_thuoc.size(); i++) {
            if (dmdc_thuoc.get(i).startsWith(sdk)) {
                return dmdc_thuoc.get(i).split("#")[1];
            }
        }
        return "1.01";
    }

    public static ArrayList<String> getDMDC() throws FileNotFoundException {
        Scanner s = new Scanner(new File("DMDC_THUOC.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

    public static boolean getHoso(String ngaybd, String ngaykt, String maBH, String tentinh) {
        try {
            dmdc_thuoc = getDMDC();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getHosoV5(ngaybd, ngaykt, maBH, tentinh);
    }

    

    public static boolean getHosoV5(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean printPhuPhi = false;
        int iGiam_ngaybd = 50;
        int iTang_ngaykt = 5;
        String sUrl = "";
        String ngaybd_b2 = "";
        String ngaykt_b2 = "";
        //-----------------------
        boolean ghiFileb2 = true;
        boolean ghiFileb3 = true;

        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(simple.parse(ngaybd));
            c.add(Calendar.DATE, -iGiam_ngaybd);
            ngaybd_b2 = simple.format(c.getTime());
            c.setTime(simple.parse(ngaykt));
            c.add(Calendar.DATE, iTang_ngaykt);
            ngaykt_b2 = simple.format(c.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean ret = false;
        try {
            System.setProperty("javax.net.ssl.trustStore", "cacerts");
            System.setProperty("javax.net.ssl.trustStorePassword", "123456");

            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang1?tungay=" + ngaybd + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            URL url = new URL(sUrl);
            InputStream in = url.openStream();
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "Bat dau tao file b1.xml");
            PrintWriter fos = new PrintWriter(new File("b1.xml"));
            Scanner sc;
            sc = new Scanner(in, "UTF-8");
            String line;
            List<String> lst = new ArrayList<String>();
            List<String> lstNgoaiTru = new ArrayList<String>();
            List<String> lstNoiTru = new ArrayList<String>();
            List<HoSoKCB> lstB1 = new ArrayList<HoSoKCB>();
            
            while (sc.hasNextLine()) {                line = sc.nextLine().trim();
                if (line.indexOf("<ma_lk>") >= 0) {
                    String malk = line.trim();
                    lst.add(malk);
                    
                    HoSoKCB hs= new HoSoKCB();
                    hs.sRow1= new String[40];
                    hs.sRow1[1]=malk;
                    for (int i= 2; i<=39; i++ ){
                        hs.sRow1[i]=sc.nextLine().trim();
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
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang1_BYT>");
            fos_ngoai.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos_ngoai.println("<Bang1_BYT>");
            fos_noi.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos_noi.println("<Bang1_BYT>");
            for (int i=0; i<lstB1.size();i++){
                fos.println("<row>");                
                for (int j=1; j<=39; j++){
                    fos.println(lstB1.get(i).sRow1[j]);
                }
                fos.println("</row>");
                //Ngoai tru
                if (lstB1.get(i).sRow1[34].indexOf("1")>=0){
                    fos_ngoai.println("<row>");                
                    for (int j=1; j<=39; j++){
                        fos_ngoai.println(lstB1.get(i).sRow1[j]);
                    }
                    fos_ngoai.println("</row>");
                }else //Noi tru
                {
                    lstNoiTru.add(lstB1.get(i).sRow1[1]);
                    System.out.println("----------> "+lstB1.get(i).sRow1[1]);
                    fos_noi.println("<row>");                
                    for (int j=1; j<=39; j++){
                        fos_noi.println(lstB1.get(i).sRow1[j]);
                    }
                    fos_noi.println("</row>");
                }                
            }
            fos.println("</Bang1_BYT>");
            fos.close();
            fos_ngoai.println("</Bang1_BYT>");
            fos_ngoai.close();
            fos_noi.println("</Bang1_BYT>");
            fos_noi.close();
            in.close();
            
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "COUNT LIST NOI TRU" +lstNoiTru.size() );
            for (int i=0;i<lstNoiTru.size();i++){
                System.out.println(lstNoiTru.get(i));
            }
            
            
            //------------------Bang 2-----
            if (ghiFileb2) {
                Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "Bat dau tao file b2.xml");
                sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt_b2 + "&dvtt=" + maBH;
                url = new URL(sUrl);
                in = url.openStream();
                fos = new PrintWriter(new File("b2.xml"));
                fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos.println("<Bang2_BYT>");
                fos_noi = new PrintWriter(new File("noitru_b2.xml"));
                fos_noi.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos_noi.println("<Bang2_BYT>");
                sc = new Scanner(in, "UTF-8");
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
                                Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "----" + field[17].trim().substring(9, 17));
                                //Gan sRow=""
                                sRow = "";
                            } else {
                                dsThuoc.add(temp);
                            }

                            if (sRow.length() > 0) {
                                fos.println(sRow);
                                if (sRow.lastIndexOf("<ma_khoa>K01</ma_khoa>")>0){
                                    sRow="";
                                }
                                if (sRow.lastIndexOf("<so_luong>0</so_luong>")>0){
                                    sRow="";
                                }
                               
                                if (lstNoiTru.contains(malk)){
                                    System.out.println("CO DU LIEU");
                                    if (sRow.length() > 0) {fos_noi.println(sRow);}
                                }
                            }
                            
                            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, ".");
                        }
                    }
                }
                fos.println("</Bang2_BYT>");
                fos.close();
                fos_noi.println("</Bang2_BYT>");
                fos_noi.close();
                in.close();
            }//Tuy chon ghi gile B2 
            //-----------------bang 3-----

            if (ghiFileb3) {
                sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt_b2 + "&dvtt=" + maBH;
                url = new URL(sUrl);
                in = url.openStream();
                Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "Bat dau tao bang 3");
                String malk = "";               
                String field[] = new String[19];
                fos = new PrintWriter(new File("b3.xml"));
                fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos.println("<Bang3_BYT>");
                fos_noi = new PrintWriter(new File("noitru_b3.xml"));
                fos_noi.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
                fos_noi.println("<Bang3_BYT>");
                sc = new Scanner(in, "UTF-8");
                while (sc.hasNextLine()) {
                    line = sc.nextLine();
                    if (line.indexOf("<ma_lk>") >=0) {
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
                            if (!printPhuPhi) {
                                if (field[2].indexOf("214") >= 0 && maBH.equals("22041")) {
                                    sRow = "";
                                }
                            }
                            System.out.println("LIST noi tru count:" +lstNoiTru.size());
                            //Them noi tru
                            if (lstNoiTru.contains(malk)){
                                System.out.println("Co du lieu noi tru");
                                //Loai bo K01                                
                                if (field[11].indexOf("K01") >= 0 ) {
                                    sRow = "";
                                }
                                if (sRow.length()>0) fos_noi.println(sRow);
                            }
                            //--ghi du lieu bang B3
                            if (sRow.length()>0) fos.println(sRow);
                            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, ".");
                        }
                    }
                }
                fos.println("</Bang3_BYT>");
                fos.close();
                fos_noi.println("</Bang3_BYT>");
                fos_noi.close();
                in.close();
            }//tuy chon ghi file b3
            Logger.getLogger(LayDuLieuBVTT.class.getName()).log(Level.INFO, "Done");
            ///---Ket thuc
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }

    
}

