package com.hys.mgt.view.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("agent")
public class Agent {
	@Autowired
	  private HttpServletRequest request;

	  public String getAgent()
	  {
	    String userAgent = this.request.getHeader("user-agent");
	    if ((null != userAgent) && (userAgent.indexOf("iPhone") > 0 || userAgent.indexOf("Android") > 0 || userAgent.indexOf("iPad") > 0)) {
	      return "M";
	    }
	    return null;
	  }
}
