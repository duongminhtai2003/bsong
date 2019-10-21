package util;

import javax.servlet.jsp.JspWriter;

public class CodeMessageUtil {
	public static final int ADD_SUCCESS = 1;
	public static final int EDIT_SUCCESS = 2;
	public static final int DELETE_SUCCESS = 3;
	public static final int ERROR = 0;
	
	public static String displayMessage(JspWriter out, String msg) {
		String result = "";
		if(!"".equals(msg)) {
			result = "<h3 style='color:red'>"+msg+"</h3>";
		}
		return result;
	}
}
