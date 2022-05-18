package com.ztool.box.config;

import com.ztool.box.util.AddressUtil;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BasicFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		MDC.put("ip", AddressUtil.getIpAddr((HttpServletRequest) request));
		chain.doFilter(request, response);
	}

}
