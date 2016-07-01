/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xyz.btq.textlib.ConvertTCVN;
import xyz.btq.textlib.ParseXmlEasy;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author soft1
 */
public class Tool917 {

    public static void main(String ar[]) throws Exception {
        XML917ToCheckout("917mh.xml");
//        String x="PERTQUNIX0NISV9USUVUX0RWS1Q+CjxDSElfVElFVF9EVktUPgo8TUFfTEs+MzMwMS8yMDE2PC9NQV9MSz4KPFNUVD4xPC9TVFQ+CjxNQV9ESUNIX1ZVPjAyLjAxNTMuMDAwNDwvTUFfRElDSF9WVT4KPE1BX1ZBVF9UVT48L01BX1ZBVF9UVT4KPE1BX05IT00+MjwvTUFfTkhPTT4KPFRFTl9ESUNIX1ZVPlNpw6p1IMOibSBEb3BwbGVyIHh1ecOqbiBz4buNPC9URU5fRElDSF9WVT4KPERPTl9WSV9USU5IPmzhuqduPC9ET05fVklfVElOSD4KPFNPX0xVT05HPjEuMDA8L1NPX0xVT05HPgo8RE9OX0dJQT4xNzEwMDA8L0RPTl9HSUE+CjxUWUxFX1RUPjEwMDwvVFlMRV9UVD4KPFRIQU5IX1RJRU4+MTcxMDAwPC9USEFOSF9USUVOPgo8TUFfS0hPQT5LMDE8L01BX0tIT0E+CjxNQV9CQUNfU0k+MTQ3OTU8L01BX0JBQ19TST4KPE1BX0JFTkg+RjQxPC9NQV9CRU5IPgo8TkdBWV9ZTD4yMDE2MDYyNzA5MDQ8L05HQVlfWUw+CjxNQV9QVFRUPjA8L01BX1BUVFQ+CjxOR0FZX0tRPjwvTkdBWV9LUT4KPC9DSElfVElFVF9EVktUPgo8Q0hJX1RJRVRfRFZLVD4KPE1BX0xLPjMzMDEvMjAxNjwvTUFfTEs+CjxTVFQ+MjwvU1RUPgo8TUFfRElDSF9WVT4yMS4wMDM3LjE3Nzc8L01BX0RJQ0hfVlU+CjxNQV9WQVRfVFU+PC9NQV9WQVRfVFU+CjxNQV9OSE9NPjM8L01BX05IT00+CjxURU5fRElDSF9WVT5HaGkgxJFp4buHbiBuw6NvIMSR4buTIHZpIHTDrW5oPC9URU5fRElDSF9WVT4KPERPTl9WSV9USU5IPmzhuqduPC9ET05fVklfVElOSD4KPFNPX0xVT05HPjEuMDA8L1NPX0xVT05HPgo8RE9OX0dJQT42MDAwMDwvRE9OX0dJQT4KPFRZTEVfVFQ+MTAwPC9UWUxFX1RUPgo8VEhBTkhfVElFTj42MDAwMDwvVEhBTkhfVElFTj4KPE1BX0tIT0E+SzAxPC9NQV9LSE9BPgo8TUFfQkFDX1NJPjE0Nzk1PC9NQV9CQUNfU0k+CjxNQV9CRU5IPkY0MTwvTUFfQkVOSD4KPE5HQVlfWUw+MjAxNjA2MjcwOTA0PC9OR0FZX1lMPgo8TUFfUFRUVD4wPC9NQV9QVFRUPgo8TkdBWV9LUT48L05HQVlfS1E+CjwvQ0hJX1RJRVRfRFZLVD4KPENISV9USUVUX0RWS1Q+CjxNQV9MSz4zMzAxLzIwMTY8L01BX0xLPgo8U1RUPjM8L1NUVD4KPE1BX0RJQ0hfVlU+MDYuMDAwMS4xODA5PC9NQV9ESUNIX1ZVPgo8TUFfVkFUX1RVPjwvTUFfVkFUX1RVPgo8TUFfTkhPTT4zPC9NQV9OSE9NPgo8VEVOX0RJQ0hfVlU+VGhhbmcgxJHDoW5oIGdpw6EgdHLhuqdtIGPhuqNtIEJlY2sgKEJESSk8L1RFTl9ESUNIX1ZVPgo8RE9OX1ZJX1RJTkg+bOG6p248L0RPTl9WSV9USU5IPgo8U09fTFVPTkc+MS4wMDwvU09fTFVPTkc+CjxET05fR0lBPjEwMDAwPC9ET05fR0lBPgo8VFlMRV9UVD4xMDA8L1RZTEVfVFQ+CjxUSEFOSF9USUVOPjEwMDAwPC9USEFOSF9USUVOPgo8TUFfS0hPQT5LMDE8L01BX0tIT0E+CjxNQV9CQUNfU0k+MTQ3OTU8L01BX0JBQ19TST4KPE1BX0JFTkg+RjQxPC9NQV9CRU5IPgo8TkdBWV9ZTD4yMDE2MDYyNzA5MDQ8L05HQVlfWUw+CjxNQV9QVFRUPjA8L01BX1BUVFQ+CjxOR0FZX0tRPjwvTkdBWV9LUT4KPC9DSElfVElFVF9EVktUPgo8Q0hJX1RJRVRfRFZLVD4KPE1BX0xLPjMzMDEvMjAxNjwvTUFfTEs+CjxTVFQ+NDwvU1RUPgo8TUFfRElDSF9WVT4wNi4wMDA5LjE4MDk8L01BX0RJQ0hfVlU+CjxNQV9WQVRfVFU+PC9NQV9WQVRfVFU+CjxNQV9OSE9NPjM8L01BX05IT00+CjxURU5fRElDSF9WVT5UaGFuZyDEkcOhbmggZ2nDoSBsbyDDonUgLSB6dW5nPC9URU5fRElDSF9WVT4KPERPTl9WSV9USU5IPmzhuqduPC9ET05fVklfVElOSD4KPFNPX0xVT05HPjEuMDA8L1NPX0xVT05HPgo8RE9OX0dJQT4xMDAwMDwvRE9OX0dJQT4KPFRZTEVfVFQ+MTAwPC9UWUxFX1RUPgo8VEhBTkhfVElFTj4xMDAwMDwvVEhBTkhfVElFTj4KPE1BX0tIT0E+SzAxPC9NQV9LSE9BPgo8TUFfQkFDX1NJPjE0Nzk1PC9NQV9CQUNfU0k+CjxNQV9CRU5IPkY0MTwvTUFfQkVOSD4KPE5HQVlfWUw+MjAxNjA2MjcwOTA0PC9OR0FZX1lMPgo8TUFfUFRUVD4wPC9NQV9QVFRUPgo8TkdBWV9LUT48L05HQVlfS1E+CjwvQ0hJX1RJRVRfRFZLVD4KPENISV9USUVUX0RWS1Q+CjxNQV9MSz4zMzAxLzIwMTY8L01BX0xLPgo8U1RUPjU8L1NUVD4KPE1BX0RJQ0hfVlU+MDYuMDA0MC4xNzk5PC9NQV9ESUNIX1ZVPgo8TUFfVkFUX1RVPjwvTUFfVkFUX1RVPgo8TUFfTkhPTT4zPC9NQV9OSE9NPgo8VEVOX0RJQ0hfVlU+xJBvIGzGsHUgaHV54bq/dCBuw6NvPC9URU5fRElDSF9WVT4KPERPTl9WSV9USU5IPmzhuqduPC9ET05fVklfVElOSD4KPFNPX0xVT05HPjEuMDA8L1NPX0xVT05HPgo8RE9OX0dJQT4zMTAwMDwvRE9OX0dJQT4KPFRZTEVfVFQ+MTAwPC9UWUxFX1RUPgo8VEhBTkhfVElFTj4zMTAwMDwvVEhBTkhfVElFTj4KPE1BX0tIT0E+SzAxPC9NQV9LSE9BPgo8TUFfQkFDX1NJPjE0Nzk1PC9NQV9CQUNfU0k+CjxNQV9CRU5IPkY0MTwvTUFfQkVOSD4KPE5HQVlfWUw+MjAxNjA2MjcwOTA0PC9OR0FZX1lMPgo8TUFfUFRUVD4wPC9NQV9QVFRUPgo8TkdBWV9LUT48L05HQVlfS1E+CjwvQ0hJX1RJRVRfRFZLVD4KPENISV9USUVUX0RWS1Q+CjxNQV9MSz4zMzAxLzIwMTY8L01BX0xLPgo8U1RUPjY8L1NUVD4KPE1BX0RJQ0hfVlU+MDwvTUFfRElDSF9WVT4KPE1BX1ZBVF9UVT48L01BX1ZBVF9UVT4KPE1BX05IT00+MTM8L01BX05IT00+CjxURU5fRElDSF9WVT5LaMOhbSBi4buHbmg8L1RFTl9ESUNIX1ZVPgo8RE9OX1ZJX1RJTkg+bOG6p248L0RPTl9WSV9USU5IPgo8U09fTFVPTkc+MTwvU09fTFVPTkc+CjxET05fR0lBPjEwMDAwPC9ET05fR0lBPgo8VFlMRV9UVD44MDwvVFlMRV9UVD4KPFRIQU5IX1RJRU4+MTAwMDA8L1RIQU5IX1RJRU4+CjxNQV9LSE9BPkswMTwvTUFfS0hPQT4KPE1BX0JBQ19TST4xNDc4ODwvTUFfQkFDX1NJPgo8TUFfQkVOSD5GNDE8L01BX0JFTkg+CjxOR0FZX1lMPjIwMTYwNjI3MTAwMjwvTkdBWV9ZTD4KPE1BX1BUVFQ+MDwvTUFfUFRUVD4KPE5HQVlfS1E+PC9OR0FZX0tRPgo8L0NISV9USUVUX0RWS1Q+CjwvRFNBQ0hfQ0hJX1RJRVRfRFZLVD4K";
//        System.out.println(decode(x));
//        System.out.println(ConvertTCVN.decodeBase64(x));
////        System.out.println(ConvertTCVN.encodeBase64("<ahjfhds???>"));
//        System.out.println(encode(x));
    }

    public static void XML917ToCheckout(String filepath) {
        ParseXmlEasy pxe = new ParseXmlEasy();
        String tonghop;
        String dschitietthuoc;
        String dschitietdvkt;
        pxe.setContentXML(filepath);
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nListHS = doc.getElementsByTagName("HOSO");
            for (int i = 0; i < nListHS.getLength(); i++) {
                Node nNode = nListHS.item(i);                                 
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList nDSFileHoSo=eElement.getElementsByTagName("FILEHOSO");
                    //Mot nguoi co N file ho so
                    for (int j = 0; j< nDSFileHoSo.getLength(); j++) {
                        Node item=nDSFileHoSo.item(j);//item la 1 file ho so                        
                        Element ele=(Element) item;
                        NodeList o1=ele.getElementsByTagName("LOAIHOSO"); 
                        NodeList o2=ele.getElementsByTagName("NOIDUNGFILE");
//                        System.out.println(o2.getLength());
                        System.out.println(o1.item(0).getTextContent());
                        System.out.println(o2.item(0).getTextContent());
                    }                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String decode(String value) throws Exception {
        byte[] decodedValue = Base64.getDecoder().decode(value);
        return new String(decodedValue, StandardCharsets.UTF_8);
    }

    public static String encode(String value) throws Exception {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}
