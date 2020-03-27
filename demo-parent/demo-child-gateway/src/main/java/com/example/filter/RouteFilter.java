package com.example.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class RouteFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(RouteFilter.class);
	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx=RequestContext.getCurrentContext();
		HttpServletRequest request=ctx.getRequest();
		log.info("Route Filter"+String.format("%s request to %s", request.getMethod(),request.getRequestURL().toString()));
		System.out.println("Using Route Filter");

		return null;
	}

}