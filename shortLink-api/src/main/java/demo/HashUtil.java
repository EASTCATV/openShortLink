package demo;

/**
 * @Author : just do it
 * @Date : 2024/11/5 13:58
 * @ApiNote :
 */

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HashUtil {
    public HashUtil() {
    }

    public static String sha1(String input) {
        return sha1(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String sha1(byte[] input) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException var3) {
            NoSuchAlgorithmException e = var3;
            throw new RuntimeException(e);
        }

        md.update(input);
        byte[] digest = md.digest();
        return ByteUtil.toHexString(digest);
    }

    public static byte[] sha1AsBytes(String input) {
        return sha1AsBytes(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] sha1AsBytes(byte[] input) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException var3) {
            NoSuchAlgorithmException e = var3;
            throw new RuntimeException(e);
        }

        md.update(input);
        return md.digest();
    }

    public static String sha256(String input) {
        return sha256(input.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] sha256AsBytes(String input) {
        return sha256AsBytes(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String sha256(byte[] input) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException var3) {
            NoSuchAlgorithmException e = var3;
            throw new RuntimeException(e);
        }

        md.update(input);
        byte[] digest = md.digest();
        return ByteUtil.toHexString(digest);
    }

    public static byte[] sha256AsBytes(byte[] input) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException var3) {
            NoSuchAlgorithmException e = var3;
            throw new RuntimeException(e);
        }

        md.update(input);
        return md.digest();
    }

    public static byte[] sha512AsBytes(byte[] input) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException var3) {
            NoSuchAlgorithmException e = var3;
            throw new RuntimeException(e);
        }

        md.update(input);
        return md.digest();
    }

    public static byte[] hmacSha256AsBytes(byte[] data, byte[] key) {
        SecretKey skey = new SecretKeySpec(key, "HmacSHA256");

        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(skey);
        } catch (GeneralSecurityException var5) {
            GeneralSecurityException e = var5;
            throw new RuntimeException(e);
        }

        mac.update(data);
        return mac.doFinal();
    }

    public static String hmacSha256(byte[] data, byte[] key) {
        return ByteUtil.toHexString(hmacSha256AsBytes(data, key));
    }

    public static byte[] hmacSha1(byte[] data, byte[] key) {
        SecretKey skey = new SecretKeySpec(key, "HmacSHA1");

        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA1");
            mac.init(skey);
        } catch (GeneralSecurityException var5) {
            GeneralSecurityException e = var5;
            throw new RuntimeException(e);
        }

        mac.update(data);
        return mac.doFinal();
    }

    public static String hmacSha256(String data, String key) {
        return hmacSha256(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] hmacSha256AsBytes(String data, String key) {
        return hmacSha256AsBytes(data.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
    }

    public static String hmacSha256(byte[] data, String key) {
        return hmacSha256(data, key.getBytes(StandardCharsets.UTF_8));
    }
}
