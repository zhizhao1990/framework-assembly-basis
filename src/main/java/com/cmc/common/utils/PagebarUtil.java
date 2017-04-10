/**
 * 
 */
package com.cmc.common.utils;

/**
 * <p>
 * 分页工具
 * </p>
 * 
 * @author John Lee
 * 
 */
public class PagebarUtil {

	/**
	 * 
	 * @param formName
	 * @param serverUrl
	 * @param url
	 * @param totalPages
	 * @param currentPage
	 * @return
	 */
	public static String getPagebar(String formName, String serverUrl,
			String url, Long totalPages, Long currentPage) {
		StringBuilder sb = new StringBuilder();
		String selectAll = "全选";
		String delete = "删除";

		String firstPage = "首页";
		String prePage = "上一页";
		String nextPage = "下一页";
		String lastPage = "尾页";
		String _currentPage = "当前页";

		sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		sb.append("<tr>");
		sb.append("<td width=\"10%\" height=\"40\">&nbsp;</td>");
		sb.append("<td width=\"15%\">&nbsp;</td>");
		sb.append("<td width=\"7%\" align=\"center\">");
		sb.append("<input type=\"button\" value=\"");
		sb.append(selectAll);
		sb.append("\" style=\"cursor:pointer; border:0px;\" ");
		sb.append("onClick=\"selectAllItem('" + formName + "')\"></td>");

		sb.append("<td width=\"7%\" align=\"center\">");
		sb.append("<input type=\"button\" value=\"");
		sb.append(delete);
		sb.append("\" style=\"cursor:pointer; border:0px;\" onclick=\"document."
				+ formName + ".submit();\"></td>");
		sb.append("<td width=\"3%\" align=\"center\">");
		// 首页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=1\">");
		sb.append("<img src=\"" + serverUrl + "/images/page/first.gif\" alt=\"");
		sb.append(firstPage);
		sb.append("\" title=\"");
		sb.append(firstPage);
		sb.append("\" width=\"21\" height=\"16\" border=\"0\">");
		sb.append("</a></td>");
		sb.append("<td width=\"5%\" align=\"center\">");

		// 上一页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=");
		sb.append((currentPage == 1) ? totalPages : (currentPage - 1));
		sb.append("\">");

		sb.append("<img src=\"" + serverUrl + "/images/page/pre.gif\" alt=\"");
		sb.append(prePage);
		sb.append("\" width=\"41\" height=\"16\" border=\"0\" title=\"");
		sb.append(prePage);
		sb.append("\"></a></td>");
		sb.append("<td width=\"5%\" align=\"center\">");

		// 下一页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=");
		sb.append((currentPage >= totalPages || totalPages == 1) ? 1
				: (currentPage + 1));
		sb.append("\">");

		sb.append("<img src=\"" + serverUrl + "/images/page/next.gif\" alt=\"");
		sb.append(nextPage);
		sb.append("\" width=\"41\" height=\"16\" border=\"0\" title=\"");
		sb.append(nextPage);
		sb.append("\"></a></td>");

		sb.append("<td width=\"3%\" align=\"center\">");

		// 尾页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=");
		sb.append(totalPages);
		sb.append("\">");

		sb.append("<img src=\"" + serverUrl + "/images/page/last.gif\" alt=\"");
		sb.append(lastPage);
		sb.append("\" title=\"");
		sb.append(lastPage);
		sb.append("\" width=\"21\" height=\"16\" border=\"0\"></a></td>");
		sb.append("<td width=\"10%\" align=\"right\" style=\"font-size:12px;\">");
		sb.append(_currentPage);
		sb.append("</td>");
		sb.append("<td width=\"10%\" >");
		sb.append("<form name=\"page\" method=\"get\">");
		sb.append("<select name=\"p\" onChange=\"window.location.href='");
		sb.append(url);
		sb.append("&page='+document.page.p.value;\">");
		for (int k = 0; k < totalPages; k++) {
			sb.append("<option value=\"");
			sb.append(k + 1);
			sb.append("\"");
			if ((k + 1) == currentPage) {
				sb.append(" selected");
			}
			sb.append(">");
			sb.append(k + 1);
			sb.append("页");
			sb.append("</option>");
		}
		sb.append("</select>");
		sb.append("</form>");
		sb.append("</td>");
		sb.append("<td width=\"15%\">&nbsp;</td>");
		sb.append("<td width=\"10%\">&nbsp;</td>");
		sb.append("</tr>");
		sb.append("</table>\r\n\r\n");

		sb.append("<script language=\"javascript\" type=\"text/javascript\">\r\n");
		sb.append("    function selectAllItem(form){\r\n");
		sb.append("        var v = eval(\"document.\"+form);\r\n");
		sb.append("        for (var i=0;i<v.elements.length;i++) {\r\n");
		sb.append("            var temp=v[i];\r\n");
		sb.append("            if(temp.type==\"checkbox\"&&(!temp.disabled)) {\r\n");
		sb.append("                temp.checked = !temp.checked;\r\n");
		sb.append("            }\r\n");
		sb.append("        }\r\n");
		sb.append("     }\r\n");
		sb.append("</script>\r\n");

		return sb.toString();
	}

	/**
	 * 不含有全选和删除按钮
	 * 
	 * @param serverUrl
	 * @param url
	 * @param totalPages
	 * @param currentPage
	 * @return
	 */
	public static String getPagebar(String serverUrl, String url,
			Long totalPages, Long currentPage) {
		StringBuilder sb = new StringBuilder();

		String firstPage = "首页";
		String prePage = "上一页";
		String nextPage = "下一页";
		String lastPage = "尾页";
		String _currentPage = "当前页";

		sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		sb.append("<tr>");
		sb.append("<td width=\"10%\" height=\"40\">&nbsp;</td>");
		sb.append("<td width=\"15%\">&nbsp;</td>");
		sb.append("<td width=\"7%\">&nbsp;</td>");
		sb.append("<td width=\"3%\" align=\"center\">");
		// 首页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=1\">");
		sb.append("<img src=\"" + serverUrl + "/images/page/first.gif\" alt=\"");
		sb.append(firstPage);
		sb.append("\" title=\"");
		sb.append(firstPage);
		sb.append("\" width=\"21\" height=\"16\" border=\"0\">");
		sb.append("</a></td>");
		sb.append("<td width=\"5%\" align=\"center\">");

		// 上一页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=");
		sb.append((currentPage == 1) ? totalPages : (currentPage - 1));
		sb.append("\">");

		sb.append("<img src=\"" + serverUrl + "/images/page/pre.gif\" alt=\"");
		sb.append(prePage);
		sb.append("\" width=\"41\" height=\"16\" border=\"0\" title=\"");
		sb.append(prePage);
		sb.append("\"></a></td>");
		sb.append("<td width=\"5%\" align=\"center\">");

		// 下一页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=");
		sb.append((currentPage >= totalPages || totalPages == 1) ? 1
				: (currentPage + 1));
		sb.append("\">");

		sb.append("<img src=\"" + serverUrl + "/images/page/next.gif\" alt=\"");
		sb.append(nextPage);
		sb.append("\" width=\"41\" height=\"16\" border=\"0\" title=\"");
		sb.append(nextPage);
		sb.append("\"></a></td>");

		sb.append("<td width=\"3%\" align=\"center\">");

		// 尾页
		sb.append("<a href=\"");
		sb.append(url);
		sb.append("&page=");
		sb.append(totalPages);
		sb.append("\">");

		sb.append("<img src=\"" + serverUrl + "/images/page/last.gif\" alt=\"");
		sb.append(lastPage);
		sb.append("\" title=\"");
		sb.append(lastPage);
		sb.append("\" width=\"21\" height=\"16\" border=\"0\"></a></td>");
		sb.append("<td width=\"10%\" align=\"right\" style=\"font-size:12px;\">");
		sb.append(_currentPage);
		sb.append("</td>");
		sb.append("<td width=\"10%\" >");
		sb.append("<form name=\"page\" method=\"get\">");
		sb.append("<select name=\"p\" onChange=\"window.location.href='");
		sb.append(url);
		sb.append("&page='+document.page.p.value;\">");
		for (int k = 0; k < totalPages; k++) {
			sb.append("<option value=\"");
			sb.append(k + 1);
			sb.append("\"");
			if ((k + 1) == currentPage) {
				sb.append(" selected");
			}
			sb.append(">");
			sb.append(k + 1);
			sb.append("页");
			sb.append("</option>");
		}
		sb.append("</select>");
		sb.append("</form>");
		sb.append("</td>");
		sb.append("<td width=\"15%\">&nbsp;</td>");
		sb.append("<td width=\"10%\">&nbsp;</td>");
		sb.append("<td width=\"7%\">&nbsp;</td>");
		sb.append("</tr>");
		sb.append("</table>\r\n\r\n");

		sb.append("<script language=\"javascript\" type=\"text/javascript\">\r\n");
		sb.append("    function selectAllItem(form, flag){\r\n");
		sb.append("        var v = eval(\"document.\"+form);\r\n");
		sb.append("        for (var i=0;i<v.elements.length;i++) {\r\n");
		sb.append("            var temp=v[i];\r\n");
		sb.append("            if(temp.type==\"checkbox\"&&(!temp.disabled)) {\r\n");
		sb.append("                temp.checked = flag;\r\n");
		sb.append("            }\r\n");
		sb.append("        }\r\n");
		sb.append("     }\r\n");
		sb.append("</script>\r\n");

		return sb.toString();
	}
}
