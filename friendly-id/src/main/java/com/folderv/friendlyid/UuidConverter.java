package com.folderv.friendlyid;

import android.os.Build;

import java.math.BigInteger;
import java.util.UUID;

class UuidConverter {

	static BigInteger toBigInteger(UUID uuid) {
		return BigIntegerPairing.pair(
				BigInteger.valueOf(uuid.getMostSignificantBits()),
				BigInteger.valueOf(uuid.getLeastSignificantBits())
		);
	}

	static UUID toUuid(BigInteger value) {
		BigInteger[] unpaired = BigIntegerPairing.unpair(value);
		return new UUID(unpaired[0].longValue(), unpaired[1].longValue());
	}

}
