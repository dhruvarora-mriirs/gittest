package com.oauth.service;

import java.security.GeneralSecurityException;

final class EllipticCurveSignatureHelper {


	static byte[] transcodeSignatureToJWS(final byte[] derSignature, int outputLength) throws GeneralSecurityException {

		if (derSignature.length < 8 || derSignature[0] != 48) {
			throw new GeneralSecurityException("Invalid ECDSA signature format");
		}

		int offset;
		if (derSignature[1] > 0) {
			offset = 2;
		} else if (derSignature[1] == (byte) 0x81) {
			offset = 3;
		} else {
			throw new GeneralSecurityException("Invalid ECDSA signature format");
		}

		byte rLength = derSignature[offset + 1];

		int i;
		for (i = rLength; (i > 0) && (derSignature[(offset + 2 + rLength) - i] == 0); i--) {
			// do nothing
		}

		byte sLength = derSignature[offset + 2 + rLength + 1];

		int j;
		for (j = sLength; (j > 0) && (derSignature[(offset + 2 + rLength + 2 + sLength) - j] == 0); j--) {
			// do nothing
		}

		int rawLen = Math.max(i, j);
		rawLen = Math.max(rawLen, outputLength / 2);

		if ((derSignature[offset - 1] & 0xff) != derSignature.length - offset
				|| (derSignature[offset - 1] & 0xff) != 2 + rLength + 2 + sLength
				|| derSignature[offset] != 2
				|| derSignature[offset + 2 + rLength] != 2) {
			throw new GeneralSecurityException("Invalid ECDSA signature format");
		}

		final byte[] concatSignature = new byte[2 * rawLen];

		System.arraycopy(derSignature, (offset + 2 + rLength) - i, concatSignature, rawLen - i, i);
		System.arraycopy(derSignature, (offset + 2 + rLength + 2 + sLength) - j, concatSignature, 2 * rawLen - j, j);

		return concatSignature;
	}

	
	static byte[] transcodeSignatureToDER(byte[] jwsSignature) throws GeneralSecurityException {

		// Adapted from org.apache.xml.security.algorithms.implementations.SignatureECDSA

		int rawLen = jwsSignature.length / 2;

		int i;

		for (i = rawLen; (i > 0) && (jwsSignature[rawLen - i] == 0); i--) {
			// do nothing
		}

		int j = i;

		if (jwsSignature[rawLen - i] < 0) {
			j += 1;
		}

		int k;

		for (k = rawLen; (k > 0) && (jwsSignature[2 * rawLen - k] == 0); k--) {
			// do nothing
		}

		int l = k;

		if (jwsSignature[2 * rawLen - k] < 0) {
			l += 1;
		}

		int len = 2 + j + 2 + l;

		if (len > 255) {
			throw new GeneralSecurityException("Invalid ECDSA signature format");
		}

		int offset;

		final byte derSignature[];

		if (len < 128) {
			derSignature = new byte[2 + 2 + j + 2 + l];
			offset = 1;
		} else {
			derSignature = new byte[3 + 2 + j + 2 + l];
			derSignature[1] = (byte) 0x81;
			offset = 2;
		}

		derSignature[0] = 48;
		derSignature[offset++] = (byte) len;
		derSignature[offset++] = 2;
		derSignature[offset++] = (byte) j;

		System.arraycopy(jwsSignature, rawLen - i, derSignature, (offset + j) - i, i);

		offset += j;

		derSignature[offset++] = 2;
		derSignature[offset++] = (byte) l;

		System.arraycopy(jwsSignature, 2 * rawLen - k, derSignature, (offset + l) - k, k);

		return derSignature;
	}
}