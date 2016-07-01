/**
 * Feb 10, 2016
 */
package org.byt.object;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

/**
 * @author admin
 *
 */
/**
 * Đối tượng Bang1 chứa thong tin bảng 1 và danh sách các row ở bảng 2,3 ứng với bảng 1
 * */
public class Bang1{
	public String malk;
	public String name;
	public String disease;
	public List<Node> danhsachbang2;
	public List<Node> danhsachbang3;
	public Bang1() {
		malk = "";
		name = "";
		disease = "";
		danhsachbang2 = new ArrayList<Node>();
		danhsachbang3 = new ArrayList<Node>();
	}
}
