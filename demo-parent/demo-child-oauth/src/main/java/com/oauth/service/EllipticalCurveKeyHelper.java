package com.oauth.service;


import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;

final class EllipticCurveKeyHelper {

	static ECPublicKey createPublicKey(final BigInteger x, final BigInteger y, final String curve) {
		ECNamedCurveParameterSpec curveParameterSpec;
		if ((curveParameterSpec = ECNamedCurveTable.getParameterSpec(curve)) == null) {
			throw new IllegalArgumentException("Unsupported named curve: " + curve);
		}

		ECParameterSpec parameterSpec = new ECNamedCurveSpec(
				curveParameterSpec.getName(),
				curveParameterSpec.getCurve(),
				curveParameterSpec.getG(),
				curveParameterSpec.getN());
		ECPublicKeySpec publicKeySpec = new ECPublicKeySpec(new ECPoint(x, y), parameterSpec);

		try {
			return (ECPublicKey) KeyFactory.getInstance("EC").generatePublic(publicKeySpec);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}