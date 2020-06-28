package pe.com.claro.post.documentosSaldoReclamo.one.resource.util;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.post.documentosSaldoReclamo.one.canonical.request.HeaderRequest;

/**
 * @author everis.
 */

@Provider
@PreMatching
public class HeaderRequestValidation implements ContainerRequestFilter {
		private static final Logger LOG = LoggerFactory.getLogger(HeaderRequestValidation.class);
		@Context
		HttpHeaders httpHeaders;
		@Context
		private Configuration conf;
		private HeaderRequest headerRequest;

	public void filter(ContainerRequestContext ctx) throws IOException {
				headerRequest = new HeaderRequest(httpHeaders, conf);
				if (ctx.getUriInfo().getPath().matches("post.+")) {
						headerRequest.isValid();
						LOG.info("Header Request:" + headerRequest);
						org.apache.log4j.MDC.put("dataAudit", headerRequest.toStringLog());
				}
	}

}
