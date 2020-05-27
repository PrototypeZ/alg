package io.michaelrocks.paranoid;

public class RandomHelper {
    private RandomHelper() {
    }

    public static long next(long var0) {
        short var2 = (short)((int)(var0 & 65535L));
        short var3 = (short)((int)(var0 >>> 16 & 65535L));
        short var4 = (short)(rotl((short)(var2 + var3), 9) + var2);
        short var5 = (short)(var3 ^ var2);
        short var6 = (short)((short)(rotl(var2, 13) ^ var5) ^ var5 << 5);
        var3 = rotl(var5, 10);
        var0 = (long)var4;
        return ((long)var3 | var0 << 16) << 16 | (long)var6;
    }

    private static short rotl(short var0, int var1) {
        return (short)(var0 >>> 32 - var1 | var0 << var1);
    }

    public static long seed(long var0) {
        var0 = (var0 ^ var0 >>> 33) * 7109453100751455733L;
        return (var0 ^ var0 >>> 28) * -3808689974395783757L >>> 32;
    }
}

