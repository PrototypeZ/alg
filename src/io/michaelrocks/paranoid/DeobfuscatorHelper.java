package io.michaelrocks.paranoid;

public class DeobfuscatorHelper {
    public static final int MAX_CHUNK_LENGTH = 8191;

    private DeobfuscatorHelper() {
    }

    private static long getCharAt(int var0, String[] var1, long var2) {
        var2 = RandomHelper.next(var2);
        return (long)var1[var0 / 8191].charAt(var0 % 8191) << 32 ^ var2;
    }

    public static String getString(long var0, String[] var2) {
        long var3 = RandomHelper.next(RandomHelper.seed(4294967295L & var0));
        long var5 = RandomHelper.next(var3);
        int var7 = (int)(var0 >>> 32 ^ var3 >>> 32 & 65535L ^ var5 >>> 16 & -65536L);
        var0 = getCharAt(var7, var2, var5);
        int var8 = (int)(var0 >>> 32 & 65535L);
        char[] var9 = new char[var8];

        for(int var10 = 0; var10 < var8; ++var10) {
            var0 = getCharAt(var7 + var10 + 1, var2, var0);
            var9[var10] = (char)((char)((int)(var0 >>> 32 & 65535L)));
        }

        return new String(var9);
    }
}

