package com.oauth.service;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.security.interfaces.ECPublicKey;

import com.oauth.exception.InvalidSignatureException;

/**
 * Verifies signatures using an Elliptic Curve public key.
 *
 * <p>
 * @deprecated See the <a href="https://github.com/spring-projects/spring-security/wiki/OAuth-2.0-Migration-Guide">OAuth 2.0 Migration Guide</a> for Spring Security 5.
 *
 * @author Michael Duergner
 * @since 2.3
 */

public class EllipticCurveVerifier implements SignatureVerifier {
	private final ECPublicKey key;
	private final String algorithm;

	public EllipticCurveVerifier(final BigInteger x, final BigInteger y,
								 final String curve, final String algorithm) {
		this(EllipticCurveKeyHelper.createPublicKey(x, y, curve), algorithm);
	}

	public EllipticCurveVerifier(final ECPublicKey key, final String algorithm) {
		this.key = key;
		this.algorithm = algorithm;
	}

	@Override
	public String algorithm() {
		return this.algorithm;
	}

	@Override
	public void verify(byte[] content, byte[] sig) {
		try {
			Signature signature = Signature.getInstance(this.algorithm);
			signature.initVerify(this.key);
			signature.update(content);

			if (!signature.verify(EllipticCurveSignatureHelper.transcodeSignatureToDER(sig))) {
				throw new InvalidSignatureException("EC Signature did not match content");
			}
		} catch (GeneralSecurityException ex) {
			throw new RuntimeException(ex);
		}
	}
}