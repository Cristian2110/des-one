package pe.com.claro.restclient;

import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.claro.restclient.entity.AuthenticationMode;
import pe.com.claro.restclient.entity.AuthenticationToken;
import pe.com.claro.restclient.exception.ErrorResponse;

/**
 * The Class RestClient.
 */
public final class RestClient {

	/** The Constant LOG. */
	private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);

	/** The uri. */
	private String baseURI;

	/** The token. */
	private AuthenticationToken token;

	/** The password. */
	private String password;

	/** The connection timeout. */
	private int connectionTimeout;

	/** The headersStringMap. */
	private MultivaluedMap<String, Object> headers;

	/**
	 * Gets the.
	 *
	 * @param <T>
	 *            the generic type
	 * @param restPath
	 *            the rest path
	 * @param expectedResponse
	 *            the expected response
	 * @param queryParams
	 *            the query params
	 * @return the t
	 */
	public <T> T get(String restPath, Class<T> expectedResponse, Map<String, String> queryParams) {
		return call(HttpMethod.GET, restPath, expectedResponse, null, queryParams);
	}

	public <T> T getR(String restPath, Class<T> expectedResponse, Map<String, ArrayList<String>> queryParams) {
		return callR(HttpMethod.GET, restPath, expectedResponse, null, queryParams);
	}
	
	public <T> T getO(String restPath, Class<T> expectedResponse, Map<String, Object> queryParams) {
		return callO(HttpMethod.GET, restPath, expectedResponse, null, queryParams);
	}

	/**
	 * Post.
	 *
	 * @param restPath
	 *            the rest path
	 * @param payload
	 *            the payload
	 * @param queryParams
	 *            the query params
	 * @return the response
	 */
	public Response post(String restPath, Object payload, Map<String, String> queryParams) {
		LOG.debug("POST: {}", restPath);
		return call(HttpMethod.POST, restPath, Response.class, payload, queryParams);
	}

	/**
	 * Put.
	 *
	 * @param restPath
	 *            the rest path
	 * @param payload
	 *            the payload
	 * @param queryParams
	 *            the query params
	 * @return the response
	 */
	public Response put(String restPath, Object payload, Map<String, String> queryParams) {
		LOG.debug("PUT: {}", restPath);
		return call(HttpMethod.PUT, restPath, Response.class, payload, queryParams);
	}

	/**
	 * Delete.
	 *
	 * @param restPath
	 *            the rest path
	 * @param queryParams
	 *            the query params
	 * @return the response
	 */
	public Response delete(String restPath, Map<String, String> queryParams) {
		LOG.debug("DELETE: {}", restPath);
		return call(HttpMethod.DELETE, restPath, Response.class, null, queryParams);
	}

	/**
	 * Gets the.
	 *
	 * @param <T>
	 *            the generic type
	 * @param methodName
	 *            the method name
	 * @param restPath
	 *            the rest path
	 * @param expectedResponse
	 *            the clazz
	 * @param payload
	 *            the payload
	 * @param queryParams
	 *            the query params
	 * @return the t
	 */
	public <T> T call(String methodName, String restPath, Class<T> expectedResponse, Object payload,
			Map<String, String> queryParams) {
		WebTarget webTarget = createWebTarget(restPath, queryParams);

		Response result = webTarget.request().headers(headers).method(
				methodName,
				Entity.entity(payload, MediaType.APPLICATION_JSON),
				Response.class);

		if (expectedResponse.getName().equals(Response.class.getName())) {
			return (T) result;
		}

		if (result != null && result.hasEntity() && isStatusCodeOK(result, restPath)) {
			return (T) result.readEntity(expectedResponse);
		}

		return null;
	}

	public <T> T callR(String methodName, String restPath, Class<T> expectedResponse, Object payload,
			Map<String, ArrayList<String>> queryParams) {
		WebTarget webTarget = createWebTargetR(restPath, queryParams);

		Response result = webTarget.request().headers(headers).method(
				methodName,
				Entity.entity(payload, MediaType.APPLICATION_JSON),
				Response.class);

		if (expectedResponse.getName().equals(Response.class.getName())) {
			return (T) result;
		}

		if (result != null && result.hasEntity() && isStatusCodeOK(result, restPath)) {
			return (T) result.readEntity(expectedResponse);
		}

		return null;
	}
	
	public <T> T callO(String methodName, String restPath, Class<T> expectedResponse, Object payload,
			Map<String, Object> queryParams) {
		WebTarget webTarget = createWebTargetO(restPath, queryParams);

		Response result = webTarget.request().headers(headers).method(
				methodName,
				Entity.entity(payload, MediaType.APPLICATION_JSON),
				Response.class);

		if (expectedResponse.getName().equals(Response.class.getName())) {
			return (T) result;
		}

		if (result != null && result.hasEntity() && isStatusCodeOK(result, restPath)) {
			return (T) result.readEntity(expectedResponse);
		}

		return null;
	}

	/**
	 * Checks if is status code ok.
	 *
	 * @param response
	 *            the response
	 * @param uri
	 *            the uri
	 * @return true, if is status code ok
	 */
	private boolean isStatusCodeOK(Response response, String uri) {
		if (response.getStatus() == Status.OK.getStatusCode()
				|| response.getStatus() == Status.CREATED.getStatusCode()) {
			return true;
		} else if (response.getStatus() == Status.UNAUTHORIZED.getStatusCode()) {
			LOG.error(
					"UNAUTHORIZED: Your credentials are wrong. Please check your username/password or the secret key");
		} else if (response.getStatus() == Status.CONFLICT.getStatusCode()
				|| response.getStatus() == Status.NOT_FOUND.getStatusCode()
				|| response.getStatus() == Status.FORBIDDEN.getStatusCode()
				|| response.getStatus() == Status.BAD_REQUEST.getStatusCode()) {
			ErrorResponse errorResponse = response.readEntity(ErrorResponse.class);
			LOG.error("{} - {} on ressource {}", errorResponse.getException(), errorResponse.getMessage()+ " :::: "+
					errorResponse.getRessource());
		} else {
			LOG.error("Unsupported status code: " + response);
		}
		LOG.error(response.toString());

		return false;
	}

	/**
	 * Creates the web target.
	 *
	 * @param restPath
	 *            the rest path
	 * @param queryParams
	 *            the query params
	 * @return the web target
	 */
	private WebTarget createWebTarget(String restPath, Map<String, String> queryParams) {
		WebTarget webTarget;
		try {
			URI u = new URI(this.baseURI + restPath);
			Client client = createrRestClient();

			webTarget = client.target(u);
			if (queryParams != null && !queryParams.isEmpty()) {
				for (Map.Entry<String, String> entry : queryParams.entrySet()) {
					if (entry.getKey() != null && entry.getValue() != null) {
						LOG.debug("PARAM: {} = {}", entry.getKey(), entry.getValue());
						webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Error", e);
			return null;
		}

		return webTarget;
	}

	private WebTarget createWebTargetR(String restPath, Map<String, ArrayList<String>> queryParams) {
		WebTarget webTarget;
		try {
			URI u = new URI(this.baseURI + restPath);
			Client client = createrRestClient();

			webTarget = client.target(u);
			if (queryParams != null && !queryParams.isEmpty()) {
				for (Map.Entry<String, ArrayList<String>> entry : queryParams.entrySet()) {
					if (entry.getKey() != null && entry.getValue() != null) {

						LOG.debug("PARAM: {} = {}", entry.getKey(), entry.getValue());
						for(String valores:entry.getValue()){

							webTarget = webTarget.queryParam(entry.getKey(), valores);
						}

					}
				}
			}

		} catch (Exception e) {
			LOG.error("Error", e);
			return null;
		}

		return webTarget;
	}
	
	private WebTarget createWebTargetO(String restPath, Map<String, Object> queryParams) {
		WebTarget webTarget;
		try {
			URI u = new URI(this.baseURI + restPath);
			Client client = createrRestClient();

			webTarget = client.target(u);
			if (queryParams != null && !queryParams.isEmpty()) {
				for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
					if (entry.getKey() != null && entry.getValue() != null) {
						LOG.debug("PARAM: {} = {}", entry.getKey(), entry.getValue());
						
						if (entry.getValue() instanceof String) {
							webTarget = webTarget.queryParam(entry.getKey(), entry.getValue());
						} else if (entry.getValue() instanceof List<?>) {
							for(String valores : (List<String>) entry.getValue()){
								webTarget = webTarget.queryParam(entry.getKey(), valores);
							}
						} else {
							// Tipo de valor desconocido
						}
						
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Error", e);
			return null;
		}

		return webTarget;
	}

	/**
	 * The Constructor.
	 *
	 * @param builder
	 *            the builder
	 */
	RestClient(RestClientBuilder builder) {
		this.baseURI = builder.baseURI;
		this.connectionTimeout = builder.timeConnectionTimeout;
		this.setHeaders(builder.headersStringMap);
		this.token = builder.token;
	}

	/**
	 * Creater rest client.
	 *
	 * @return the client
	 * @throws KeyManagementException
	 *             the key management exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	private Client createrRestClient() throws KeyManagementException, NoSuchAlgorithmException {
		ClientConfig clientConfig = new ClientConfig();

		// Set connection timeout
		if (this.connectionTimeout != 0) {
			clientConfig.property(ClientProperties.CONNECT_TIMEOUT, this.connectionTimeout);
			clientConfig.property(ClientProperties.READ_TIMEOUT, this.connectionTimeout);
		}
		// Set Logging Filter
		clientConfig.register(new LoggingFilter());

		Client client = null;
		if (this.baseURI.startsWith("https")) {
			client = createSLLClient(clientConfig);
		} else {
			client = ClientBuilder.newClient(clientConfig);
		}

		return client;
	}

	/**
	 * Creates the sll client.
	 *
	 * @param clientConfig
	 *            the client config
	 * @return the client config
	 * @throws KeyManagementException
	 *             the key management exception
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	private Client createSLLClient(ClientConfig clientConfig)
			throws KeyManagementException, NoSuchAlgorithmException {
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
				System.out.println("checkClientTrusted");
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
				System.out.println("checkServerTrusted");
			}
		} };

		SSLContext sc = SSLContext.getInstance("TLS");
		sc.init(null, trustAllCerts, new SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		ClientBuilder.newClient(clientConfig);

		Client client = ClientBuilder.newBuilder()
				.sslContext(sc)
				.hostnameVerifier(new HostnameVerifier() {
					public boolean verify(String s, SSLSession sslSession) {
						return true;
					}
				})
				.withConfig(clientConfig).build();

		return client;
	}

	/**
	 * The Class Builder.
	 */
	public static class RestClientBuilder {

		/** The uri. */
		private String baseURI;

		/** The connection timeout. */
		private int timeConnectionTimeout;

		/** The headersStringMap. */
		private MultivaluedMap<String, Object> headersStringMap;

		/** The token. */
		private AuthenticationToken token;

		/**
		 * The Constructor.
		 *
		 * @param baseUri
		 *            the base uri
		 */
		public RestClientBuilder(String baseUri) {
			this.headersStringMap = new MultivaluedHashMap<String, Object>();
			this.baseURI = baseUri;
		}

		/**
		 * Connection timeout.
		 *
		 * @param connectionTimeout
		 *            the connection timeout
		 * @return the builder
		 */
		public RestClientBuilder connectionTimeout(int connectionTimeout) {
			this.timeConnectionTimeout = connectionTimeout;
			return this;
		}

		/**
		 * Authentication token.
		 *
		 * @param token
		 *            the token
		 * @return the rest client builder
		 */
		public RestClientBuilder authenticationToken(AuthenticationToken token) {
			if (token.getAuthMode() == AuthenticationMode.SHARED_SECRET_KEY) {
				headersStringMap.add(HttpHeaders.AUTHORIZATION, token.getSharedSecretKey());
			} else if (token.getAuthMode() == AuthenticationMode.BASIC_AUTH) {
				String base64 = Base64.encodeAsString(token.getUsername() + ":" + token.getPassword());
				headersStringMap.add(HttpHeaders.AUTHORIZATION, "Basic " + base64);
			}

			this.token = token;
			return this;
		}

		/**
		 * Headers.
		 *
		 * @param headers
		 *            the headersStringMap
		 * @return the rest client builder
		 */
		public RestClientBuilder headers(MultivaluedMap<String, Object> headers) {
			this.headersStringMap = headers;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the rest client resource
		 */
        public RestClient build() {
			return new RestClient(this);
		}

	}

	/**
	 * Gets the uri.
	 *
	 * @return the uri
	 */
	public String getUri() {
		return baseURI;
	}

	/**
	 * Sets the uri.
	 *
	 * @param uri
	 *            the new uri
	 */
	public void setUri(String uri) {
		this.baseURI = uri;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the connection timeout.
	 *
	 * @return the connection timeout
	 */
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * Sets the connection timeout.
	 *
	 * @param connectionTimeout
	 *            the new connection timeout
	 */
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public AuthenticationToken getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the token
	 */
	public void setToken(AuthenticationToken token) {
		this.token = token;
	}

	/**
	 * Gets the headersStringMap.
	 *
	 * @return the headersStringMap
	 */
	public MultivaluedMap<String, Object> getHeaders() {
		return headers;
	}

	/**
	 * Sets the headersStringMap.
	 *
	 * @param headers
	 *            the headersStringMap
	 */
	public void setHeaders(MultivaluedMap<String, Object> headers) {
		this.headers = headers;
	}

}
