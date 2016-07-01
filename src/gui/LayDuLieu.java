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
public class LayDuLieu {
    public static ArrayList<String> dmdc_thuoc=null;
    public static void main(String[] args) throws Exception {
        boolean kq = false;
        //kq = getHosoV2("2016-04-20", "2016-04-21", "33031", "hungyen");        
        //getHoso("2016-05-25", "2016-05-25", "22041", "quangninh2");
        dieuChinhTienBang1();
        //getHosoV3("2016-04-20", "2016-04-21", "33031", "hungyen");
//        String s="<so_dang_ky>VD-16980-12</so_dang_ky>";
//        System.out.println(s.substring(12,s.lastIndexOf("<")));
//        System.out.println(getHamLuong("VN-17922-14"));
//        System.out.println(getDuongDung("VD-21061-14"));
        
    }
    public static void dieuChinhTienBang1(){
            PrintWriter fos = null;
        try {
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Hieu chinh file b1.xml");        
            fos = new PrintWriter(new File("b1fix.xml"));
            Scanner sc;
            sc = new Scanner(new File("b1.xml"), "UTF-8");
            String line;
            String muchuong="";
            String tongchi="";
            String bhtt="";
            String bntt="";
            
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                fos.println(line);
                if (line.indexOf("<row>")>=0){
                    for (int i=1;i<=24;i++){
                        line = sc.nextLine();
                        fos.println(line);
                        muchuong=line.substring(10,line.indexOf("</"));
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
                    tongchi=line.substring(10,line.indexOf("</"));
                    fos.println(line);
                    //BN thanh toan
                    line = sc.nextLine();
                    bntt=line.substring(10,line.indexOf("</"));
                    fos.println(line);
                    //Bao Hiem thanh toan
                    line = sc.nextLine();
                    bhtt=line.substring(10,line.indexOf("</"));
                    fos.println(line);
                }
                
                
            }
            fos.close();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Done hieu chinh bang 1.xml");        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fos.close();
        }
    }
    public static String getHamLuong(String sdk){
        for (int i=0; i<dmdc_thuoc.size(); i++){
            if (dmdc_thuoc.get(i).startsWith(sdk)) return dmdc_thuoc.get(i).split("#")[2];
        }
        return "0";
    }
    public static String getDuongDung(String sdk){
        for (int i=0; i<dmdc_thuoc.size(); i++){
            if (dmdc_thuoc.get(i).startsWith(sdk)) return dmdc_thuoc.get(i).split("#")[1];
        }
        return "1.01";
    }
    public static ArrayList<String> getDMDC() throws FileNotFoundException{
        Scanner s = new Scanner(new File("DMDC_THUOC.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNextLine()){
            list.add(s.nextLine());            
        }
        s.close();
        return list;
    }
    public static boolean getHoso(String ngaybd, String ngaykt, String maBH, String tentinh){
        try {
            dmdc_thuoc= getDMDC();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getHosoV5(ngaybd,  ngaykt,  maBH,  tentinh);
    }
    
    public static boolean getHosoV1(String ngaybd, String ngaykt, String maBH, String tentinh) {
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

            sUrl = "https://" + tentinh + "2.vnpthis.vn/web_his/xuatxml_bang1?tungay=" + ngaybd + "&denngay=" + ngaykt + "&dvtt=" + maBH;
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
            sUrl = "https://" + tentinh + "2.vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
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
            sUrl = "https://" + tentinh + "2.vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
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

    public static boolean getHosoV2(String ngaybd, String ngaykt, String maBH, String tentinh) {
        String sUrl = "";
        String ngaybd_b2 = "";
        Patients patients = null;
        Medicines medicines = null;
        Services services = null;

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
            PrintWriter fos = new PrintWriter(new File("b1.xml"));
            Scanner sc;
            sc = new Scanner(in, "UTF-8");
            String line;
            List<String> lst = new ArrayList<String>();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                fos.println(line);
                if (line.indexOf("<ma_lk>") > 0) {
                    String malk = line.trim();
                    lst.add(malk);
                }
            }
            fos.close();
            in.close();
            // Dong lenh kiem tra dung luong bang 1
//            for (int i = 0; i < lst.size(); i++) {
//                System.out.println(lst.get(i));
//            }

            //------------------Bang 2-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            fos = new PrintWriter(new File("b2.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang2_BYT>");
            sc = new Scanner(in, "UTF-8");
            String malk = "";
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {
                    malk = line.trim();
                    if (lst.contains(malk)) {
                        fos.println("<row>");
                        fos.println(line.trim());
                        line = sc.nextLine();
                        while (!line.trim().equals("</row>")) {                            
                            fos.println(line);
                            line = sc.nextLine();
                        }
                        fos.println(line);
                    }
                }
            }
            fos.println("</Bang2_BYT>");
            fos.close();
            in.close();
            //-----------------bang 3-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            
            fos = new PrintWriter(new File("b3.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang3_BYT>");
            sc = new Scanner(in, "UTF-8");
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {
                    malk = line.trim();
                    if (lst.contains(malk)) {
                        fos.println("<row>");
                        fos.println(line.trim());
                        line = sc.nextLine();
                        while (line.indexOf("</row>")<0) {   
                            //FIX Ti le thanh toan 100
                            if (line.indexOf("<tyle_tt>")>=0){line="<tyle_tt>100</tyle_tt>";}
                            //END FIX
                            fos.println(line);
                            line = sc.nextLine();
                        }
                        fos.println(line);
                    }
                }
            }
            fos.println("</Bang3_BYT>");
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
    public static boolean getHosoV3(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean printPhuPhi=true;
        String sUrl = "";
        String ngaybd_b2 = "";
        Patients patients = null;
        Medicines medicines = null;
        Services services = null;

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
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b1.xml");
            PrintWriter fos = new PrintWriter(new File("b1.xml"));
            Scanner sc;
            sc = new Scanner(in, "UTF-8");
            String line;
            List<String> lst = new ArrayList<String>();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                fos.println(line);
                if (line.indexOf("<ma_lk>") > 0) {
                    String malk = line.trim();
                    lst.add(malk);
                }
            }
            fos.close();
            in.close();
            // Dong lenh kiem tra dung luong bang 1
//            for (int i = 0; i < lst.size(); i++) {
//                System.out.println(lst.get(i));
//            }

            //------------------Bang 2-----
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b2.xml");
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            fos = new PrintWriter(new File("b2.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang2_BYT>");
            sc = new Scanner(in, "UTF-8");
            String malk = "";
            String  field[]= new String[19];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {
                    malk = line.trim();
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<19;i++){
                            field[i]= sc.nextLine();
                        }                 
                        
                        fos.println("<row>");                        
                        String sdk="";
                        //F9: SDK; F2: Ma thuoc; F6: Ham luong; F7 Duong dung
                        // Null dang: <so_dang_ky></so_dang_ky>
                        
                        field[9]=field[9].trim();
                        field[2]=field[2].trim();
                        field[6]=field[6].trim();
                        field[7]=field[7].trim();
                        sdk=field[9].substring(12,field[9].lastIndexOf("<"));
                        //Gan lai SDK duong dung ham luong                        
                        if (sdk.length()>0){
                            if (field[2].indexOf("<ma_thuoc></ma_thuoc")>=0) field[2]="<ma_thuoc>" + sdk +"</ma_thuoc>";
                            if (field[6].indexOf("<ham_luong></ham_luong")>=0) field[6]="<ham_luong>" + getHamLuong(sdk) +"</ham_luong>";
                            if (field[7].indexOf("<duong_dung></duong_dung")>=0)field[7]="<duong_dung>" + getDuongDung(sdk) +"</duong_dung>";
                        }else{
                            field[9]="<so_dang_ky>FIXNULL</so_dang_ky>";
                        }
                        for (int i=0;i<19;i++){
                            fos.println(field[i]);
                        }
                        //Doc ky hieu het row
                        sc.nextLine();
                        fos.println("</row>");
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                } 
            }
            fos.println("</Bang2_BYT>");
            fos.close();
            in.close();
            
            //-----------------bang 3-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            
            fos = new PrintWriter(new File("b3.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang3_BYT>");
            sc = new Scanner(in, "UTF-8");
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {
                    malk = line.trim();
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<17;i++){
                            field[i]= sc.nextLine();
                        }
                        sc.nextLine();//Read </row> next
                        field[9]="<tyle_tt>100</tyle_tt>";
                        String sRow="";
                        sRow="<row>";                        
                        for (int i=0;i<17;i++){
                            sRow+=field[i]+"\r\n";
                        }
                        sRow+="</row>";                            
                        if (!printPhuPhi){
                            if (field[2].indexOf("214")>0) sRow="";                        
                        }
                        fos.print(sRow);
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                }                
            }
            fos.println("</Bang3_BYT>");
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
    public static boolean getHosoV4(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean printPhuPhi=false;
        
        String sUrl = "";
        String ngaybd_b2 = "";
        Patients patients = null;
        Medicines medicines = null;
        Services services = null;

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
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b1.xml");
            PrintWriter fos = new PrintWriter(new File("b1.xml"));
            Scanner sc;
            sc = new Scanner(in, "UTF-8");
            String line;
            List<String> lst = new ArrayList<String>();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                fos.println(line);
                if (line.indexOf("<ma_lk>") > 0) {
                    String malk = line.trim();
                    lst.add(malk);
                }
            }
            fos.close();
            in.close();
            // Dong lenh kiem tra dung luong bang 1
//            for (int i = 0; i < lst.size(); i++) {
//                System.out.println(lst.get(i));
//            }

            //------------------Bang 2-----
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b2.xml");
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            fos = new PrintWriter(new File("b2.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang2_BYT>");
            sc = new Scanner(in, "UTF-8");
            String malk = "";
            String malk_banghimoi = "";
            List<String> dsThuoc=new ArrayList<String>();
            String  field[]= new String[19];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {                    
                    malk = line.trim();
                    if (!malk.equals(malk_banghimoi)){
                        malk_banghimoi=malk;
                       // dsThuoc.clear();
                    }
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<19;i++){
                            field[i]= sc.nextLine();
                        }                 
                        String sRow="";
                        sRow="<row>\r\n";
                        
                        String sdk="";
                        //F9: SDK; F2: Ma thuoc; F6: Ham luong; F7 Duong dung
                        // Null dang: <so_dang_ky></so_dang_ky>
                        
                        field[9]=field[9].trim();
                        field[2]=field[2].trim();
                        field[6]=field[6].trim();
                        field[7]=field[7].trim();
                        sdk=field[9].substring(12,field[9].lastIndexOf("<"));
                        //Gan lai SDK duong dung ham luong                        
                        if (sdk.length()>0){
                            if (field[2].indexOf("<ma_thuoc></ma_thuoc")>=0) field[2]="<ma_thuoc>" + sdk +"</ma_thuoc>";
                            if (field[6].indexOf("<ham_luong></ham_luong")>=0) field[6]="<ham_luong>" + getHamLuong(sdk) +"</ham_luong>";
                            if (field[7].indexOf("<duong_dung></duong_dung")>=0)field[7]="<duong_dung>" + getDuongDung(sdk) +"</duong_dung>";
                        }else{
                            field[9]="<so_dang_ky>0</so_dang_ky>";
                        }
                        for (int i=0;i<19;i++){
                            sRow+=field[i]+"\r\n";
                        }
                        sc.nextLine();
                        sRow+="</row>";
                        String temp="";
                        temp=field[0].trim()+field[9].trim()+field[10].trim()+field[11].trim()+field[17].trim().substring(9,17);
                        if (dsThuoc.contains(temp)){
                            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "----" +field[17].trim().substring(9,17));
                            //Gan sRow=""
                            sRow="";
                        }else{
                            dsThuoc.add(temp);
                        }
                        
                        if(sRow.length()>0)  fos.println(sRow);
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                } 
            }
            fos.println("</Bang2_BYT>");
            fos.close();
            in.close();
            
            //-----------------bang 3-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao bang 3");
            fos = new PrintWriter(new File("b3.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang3_BYT>");
            sc = new Scanner(in, "UTF-8");
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {
                    malk = line.trim();
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<17;i++){
                            field[i]= sc.nextLine();
                        }
                        sc.nextLine();//Read </row> next
                        field[9]="<tyle_tt>100</tyle_tt>";
                        String sRow="";
                        sRow="<row>";                        
                        for (int i=0;i<17;i++){
                            sRow+=field[i]+"\r\n";
                        }
                        sRow+="</row>";                            
                        if (!printPhuPhi){
                            if (field[2].indexOf("214")>0 && maBH.equals("22041")) sRow="";
                        }
                        fos.print(sRow);
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                }                
            }
            fos.println("</Bang3_BYT>");
            fos.close();
            in.close();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Done");
            ///---Ket thuc
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }
    public static boolean getHosoV5(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean printPhuPhi=false;
        
        String sUrl = "";
        String ngaybd_b2 = "";
        Patients patients = null;
        Medicines medicines = null;
        Services services = null;

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
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b1.xml");
            PrintWriter fos = new PrintWriter(new File("b1.xml"));
            Scanner sc;
            sc = new Scanner(in, "UTF-8");
            String line;
            List<String> lst = new ArrayList<String>();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                fos.println(line);
                if (line.indexOf("<ma_lk>") > 0) {
                    String malk = line.trim();
                    lst.add(malk);
                }
            }
            fos.close();
            in.close();
            // Dong lenh kiem tra dung luong bang 1
//            for (int i = 0; i < lst.size(); i++) {
//                System.out.println(lst.get(i));
//            }

            //------------------Bang 2-----
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b2.xml");
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            fos = new PrintWriter(new File("b2.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang2_BYT>");
            sc = new Scanner(in, "UTF-8");
            String malk = "";
            String malk_banghimoi = "";
            List<String> dsThuoc=new ArrayList<String>();
            String  field[]= new String[19];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {                    
                    malk = line.trim();
                    if (!malk.equals(malk_banghimoi)){
                        malk_banghimoi=malk;
                       // dsThuoc.clear();
                    }
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<19;i++){
                            field[i]= sc.nextLine();
                        }                 
                        String sRow="";
                        sRow="<row>\r\n";
                        
                        String sdk="";
                        //F9: SDK; F2: Ma thuoc; F6: Ham luong; F7 Duong dung
                        // Null dang: <so_dang_ky></so_dang_ky>
                        
                        field[9]=field[9].trim();
                        field[2]=field[2].trim();
                        field[6]=field[6].trim();
                        field[7]=field[7].trim();
                        sdk=field[9].substring(12,field[9].lastIndexOf("<"));
                        //Gan lai SDK duong dung ham luong                        
                        if (sdk.length()>0){
                            if (field[2].indexOf("<ma_thuoc></ma_thuoc")>=0) field[2]="<ma_thuoc>" + sdk +"</ma_thuoc>";
                            if (field[6].indexOf("<ham_luong></ham_luong")>=0) field[6]="<ham_luong>" + getHamLuong(sdk) +"</ham_luong>";
                            if (field[7].indexOf("<duong_dung></duong_dung")>=0)field[7]="<duong_dung>" + getDuongDung(sdk) +"</duong_dung>";
                        }else{
                            field[9]="<so_dang_ky>0</so_dang_ky>";
                        }
                        for (int i=0;i<19;i++){
                            sRow+=field[i]+"\r\n";
                        }
                        sc.nextLine();
                        sRow+="</row>";
                        String temp="";
                        temp=field[0].trim()+field[9].trim()+field[10].trim()+field[11].trim()+field[17].trim().substring(9,17);
                        if (dsThuoc.contains(temp)){
                            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "----" +field[17].trim().substring(9,17));
                            //Gan sRow=""
                            sRow="";
                        }else{
                            dsThuoc.add(temp);
                        }
                        
                        if(sRow.length()>0)  fos.println(sRow);
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                } 
            }
            fos.println("</Bang2_BYT>");
            fos.close();
            in.close();
            
            //-----------------bang 3-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd_b2 + "&denngay=" + ngaykt + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao bang 3");
            fos = new PrintWriter(new File("b3.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang3_BYT>");
            sc = new Scanner(in, "UTF-8");
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {
                    malk = line.trim();
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<17;i++){
                            field[i]= sc.nextLine();
                        }
                        sc.nextLine();//Read </row> next
                        field[9]="<tyle_tt>100</tyle_tt>";
                        String sRow="";
                        sRow="<row>";                        
                        for (int i=0;i<17;i++){
                            sRow+=field[i]+"\r\n";
                        }
                        sRow+="</row>";                            
                        if (!printPhuPhi){
                            if (field[2].indexOf("214")>0 && maBH.equals("22041")) sRow="";
                        }
                        fos.print(sRow);
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                }                
            }
            fos.println("</Bang3_BYT>");
            fos.close();
            in.close();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Done");
            ///---Ket thuc
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }
    public static boolean getHosoVNgoaiTruTest(String ngaybd, String ngaykt, String maBH, String tentinh) {
        boolean printPhuPhi=false;
        
        String sUrl = "";
        String ngaybd_b2 = "";
        Patients patients = null;
        Medicines medicines = null;
        Services services = null;

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

            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang1?tungay=" + ngaybd + "&denngay=" + ngaybd + "&dvtt=" + maBH;
            URL url = new URL(sUrl);
            InputStream in = url.openStream();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b1.xml");
            PrintWriter fos = new PrintWriter(new File("b1.xml"));
            Scanner sc;
            sc = new Scanner(in, "UTF-8");
            String line;
            List<String> lst = new ArrayList<String>();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                fos.println(line);
                if (line.indexOf("<ma_lk>") > 0) {
                    String malk = line.trim();
                    lst.add(malk);
                }
            }
            fos.close();
            in.close();
            // Dong lenh kiem tra dung luong bang 1
//            for (int i = 0; i < lst.size(); i++) {
//                System.out.println(lst.get(i));
//            }

            //------------------Bang 2-----
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao file b2.xml");
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang2?tungay=" + ngaybd + "&denngay=" + ngaybd + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            fos = new PrintWriter(new File("b2.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang2_BYT>");
            sc = new Scanner(in, "UTF-8");
            String malk = "";
            String malk_banghimoi = "";
            List<String> dsThuoc=new ArrayList<String>();
            String  field[]= new String[19];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {                    
                    malk = line.trim();
                    if (!malk.equals(malk_banghimoi)){
                        malk_banghimoi=malk;
                        dsThuoc.clear();
                    }
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<19;i++){
                            field[i]= sc.nextLine();
                        }                 
                        String sRow="";
                        sRow="<row>\r\n";
                        
                        String sdk="";
                        //F9: SDK; F2: Ma thuoc; F6: Ham luong; F7 Duong dung
                        // Null dang: <so_dang_ky></so_dang_ky>
                        
                        field[9]=field[9].trim();
                        field[2]=field[2].trim();
                        field[6]=field[6].trim();
                        field[7]=field[7].trim();
                        sdk=field[9].substring(12,field[9].lastIndexOf("<"));
                        //Gan lai SDK duong dung ham luong                        
                        if (sdk.length()>0){
                            if (field[2].indexOf("<ma_thuoc></ma_thuoc")>=0) field[2]="<ma_thuoc>" + sdk +"</ma_thuoc>";
                            if (field[6].indexOf("<ham_luong></ham_luong")>=0) field[6]="<ham_luong>" + getHamLuong(sdk) +"</ham_luong>";
                            if (field[7].indexOf("<duong_dung></duong_dung")>=0)field[7]="<duong_dung>" + getDuongDung(sdk) +"</duong_dung>";
                        }else{
                            field[9]="<so_dang_ky>0</so_dang_ky>";
                        }
                        for (int i=0;i<19;i++){
                            sRow+=field[i]+"\r\n";
                        }
                        sc.nextLine();
                        sRow+="</row>";
                        String temp="";
                        temp=field[0].trim()+field[9].trim()+field[10].trim()+field[11].trim()+field[17].trim().substring(9,17);
                        if (dsThuoc.contains(temp)){
                            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "----" +field[17].trim().substring(9,17));
                            //Gan sRow=""
                            sRow="";
                        }else{
                            dsThuoc.add(temp);
                        }
                        
                        if(sRow.length()>0)  fos.println(sRow);
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                } 
            }
            fos.println("</Bang2_BYT>");
            fos.close();
            in.close();
            
            //-----------------bang 3-----
            sUrl = "https://" + tentinh + ".vnpthis.vn/web_his/xuatxml_bang3?tungay=" + ngaybd + "&denngay=" + ngaybd + "&dvtt=" + maBH;
            url = new URL(sUrl);
            in = url.openStream();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Bat dau tao bang 3");
            fos = new PrintWriter(new File("b3.xml"));
            fos.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            fos.println("<Bang3_BYT>");
            sc = new Scanner(in, "UTF-8");
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.indexOf("<ma_lk>") > 0) {
                    malk = line.trim();
                    if (lst.contains(malk)) {
                        field[0]=malk;
                        for (int i=1;i<17;i++){
                            field[i]= sc.nextLine();
                        }
                        sc.nextLine();//Read </row> next
                        field[9]="<tyle_tt>100</tyle_tt>";
                        String sRow="";
                        sRow="<row>";                        
                        for (int i=0;i<17;i++){
                            sRow+=field[i]+"\r\n";
                        }
                        sRow+="</row>";                            
                        if (!printPhuPhi){
                            if (field[2].indexOf("214")>0 && maBH.equals("22041")) sRow="";
                        }
                        fos.print(sRow);
                        Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, ".");
                    }
                }                
            }
            fos.println("</Bang3_BYT>");
            fos.close();
            in.close();
            Logger.getLogger(LayDuLieu.class.getName()).log(Level.INFO, "Done");
            ///---Ket thuc
            ret = true;
        } catch (Exception ex) {
            ex.printStackTrace();
            ret = false;
        }
        return ret;
    }
}
