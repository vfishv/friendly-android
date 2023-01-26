package com.folderv.friendlyid;

import java.math.BigInteger;
//import java.util.function.Function;

/**
 * Basing on snippet published by drmalex07
 * <p>
 * https://gist.github.com/drmalex07/9008c611ffde6cb2ef3a2db8668bc251
 */
class BigIntegerPairing {

	private static final BigInteger HALF = BigInteger.ONE.shiftLeft(64); // 2^64
	private static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);

	/*
	private static Function<BigInteger, BigInteger> toUnsigned = value
			-> value.signum() < 0 ? value.add(HALF) : value;
	private static Function<BigInteger, BigInteger> toSigned =
			value -> MAX_LONG.compareTo(value) < 0 ? value.subtract(HALF) : value;
	*/

	private static BigInteger toUnsigned(BigInteger value) {
		return value.signum() < 0 ? value.add(HALF) : value;
	}

	private static BigInteger toSigned(BigInteger value) {
		return MAX_LONG.compareTo(value) < 0 ? value.subtract(HALF) : value;
	}

	static BigInteger pair(BigInteger hi, BigInteger lo) {
		BigInteger unsignedLo = toUnsigned(lo);
		BigInteger unsignedHi = toUnsigned(hi);
		return unsignedLo.add(unsignedHi.multiply(HALF));
	}

	static BigInteger[] unpair(BigInteger value) {
		BigInteger[] parts = value.divideAndRemainder(HALF);
		BigInteger signedHi = toSigned(parts[0]);
		BigInteger signedLo = toSigned(parts[1]);
		return new BigInteger[]{signedHi, signedLo};
	}

	/*
	public static BigInteger convertToBigInteger(UUID id)
	{
		BigInteger lo = BigInteger.valueOf(id.getLeastSignificantBits());
		BigInteger hi = BigInteger.valueOf(id.getMostSignificantBits());

		// If any of lo/hi parts is negative interpret as unsigned

		if (hi.signum() < 0)
			hi = hi.add(HALF);

		if (lo.signum() < 0)
			lo = lo.add(HALF);

		return lo.add(hi.multiply(HALF));
	}

	public static UUID convertFromBigInteger(BigInteger x)
	{
		BigInteger[] parts = x.divideAndRemainder(HALF);
		BigInteger hi = parts[0];
		BigInteger lo = parts[1];

		if (MAX_LONG.compareTo(lo) < 0)
			lo = lo.subtract(HALF);

		if (MAX_LONG.compareTo(hi) < 0)
			hi = hi.subtract(HALF);

		return new UUID(hi.longValueExact(), lo.longValueExact());
	}
	*/
}
