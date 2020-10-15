package com.oauth.service;

public interface SignatureVerifier {

	void verify(byte[] content, byte[] sig);

	String algorithm();

}
