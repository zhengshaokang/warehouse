package com.hys.commons.util;

import org.apache.commons.lang.StringUtils;

public class SysRemark {
	public static String startFix = "【";
	  public static String endFix = "】";
	  public static String newLine = "<br/>";

	  private static String a(String paramString)
	  {
	    return startFix + paramString + endFix;
	  }

	  public static String append(String paramString1, String paramString2) {
	    int i = StringUtils.countMatches(paramString1, startFix);
	    String str = new Integer(i + 1).toString() + ": ";
	    if ((null == paramString1) || (paramString1.isEmpty()))
	      paramString1 = "";
	    else {
	      paramString1 = paramString1 + newLine;
	    }

	    return paramString1 + str + a(paramString2);
	  }

	  public static String appendMore(String paramString1, String paramString2) {
	    int i = StringUtils.countMatches(paramString1, startFix);
	    String str1 = new Integer(i + 1).toString() + ": ";
	    if ((null == paramString1) || (paramString1.isEmpty()))
	      paramString1 = "";
	    else {
	      paramString1 = paramString1 + newLine;
	    }

	    DateUtil localMyDate = new DateUtil();
	    String str2 = "于 " + localMyDate.getCurrentDateTimeAsString() + " 操作, ";

	    return paramString1 + str1 + startFix + str2 + paramString2 + endFix;
	  }
}
