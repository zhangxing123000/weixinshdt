package com.weixin.http;

import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

/** 
 * 信任管理器 
 * @author fjing 
 * 
 */  
public class MyX509TrustManager implements X509TrustManager {  
  
    public void checkClientTrusted(X509Certificate[] arg0, String arg1)  
            throws CertificateException {  
  
    }  
  
    public void checkServerTrusted(X509Certificate[] arg0, String arg1)  
            throws CertificateException {  
  
    }  
  
    public X509Certificate[] getAcceptedIssuers1() {  
        return null;  
    }

	@Override
	public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
			throws java.security.cert.CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
			throws java.security.cert.CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}  
  
}  