/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.claro.post.documentosSaldoReclamo.one.resource.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.common.resource.util.ClaroUtil;

@WebFilter(filterName = "HTML5CorsFilter", urlPatterns = { "/api/*" })
public class HTML5CorsFilter implements javax.servlet.Filter {

	private static final Logger LOG = LoggerFactory.getLogger(ClaroUtil.class);

	@Override
		public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
				HttpServletResponse res = (HttpServletResponse) response;
				res.addHeader("Access-Control-Allow-Origin", "*");
				res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
				res.addHeader("Access-Control-Allow-Headers", "Content-Type");
				chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
				LOG.info("init");
	}

	@Override
	public void destroy() {
				LOG.info("destroy");
	}

}