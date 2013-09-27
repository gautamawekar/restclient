package gawekar.test.restclient;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class JerseyClientToUntrustedServer {
	private static Client CLIENT;

	// Create a trust manager that does not validate certificate chains
	private static final TrustManager[] TRUST_ALL_CERTIFICATES = new TrustManager[] { new X509TrustManager() {
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public void checkClientTrusted(X509Certificate[] certs, String authType) {
		}

		public void checkServerTrusted(X509Certificate[] certs, String authType) {
		}
	} };

	private static HostnameVerifier HOST_NAME_VERIFIER = new HostnameVerifier() {
		public boolean verify(String urlHostName, SSLSession session) {
			return true;
		}
	};

	static {
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(HOST_NAME_VERIFIER);
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, TRUST_ALL_CERTIFICATES, new SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
			CLIENT = ClientBuilder.newBuilder().sslContext(sc).build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Client getClient() {
		return CLIENT;
	}
}
