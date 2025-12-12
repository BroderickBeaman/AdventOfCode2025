package aoc2015.dec04;

import framework.AOCParent;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Dec04 extends AOCParent {

    @Override
    public void loadInput() {

    }

    @Override
    public void part1() {

        final String input = "ckczppom";

        int salt = 1;

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            String test = input + salt;
            byte[] digest = md.digest(test.getBytes(StandardCharsets.UTF_8));
            if ("00000".equals((byteToHex(digest[0]) + byteToHex(digest[1]) +byteToHex(digest[2])).substring(0, 5))) {
                System.out.println("Answer: " + salt);
                break;
            }
            salt++;
        }
    }

    @Override
    public void part2() {

        final String input = "ckczppom";

        int salt = 1;

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        while(true) {
            String test = input + salt;
            byte[] digest = md.digest(test.getBytes(StandardCharsets.UTF_8));
            if ("000000".equals((byteToHex(digest[0]) + byteToHex(digest[1]) +byteToHex(digest[2])))) {
                System.out.println("Answer: " + salt);
                break;
            }
            salt++;
        }
    }

    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }
}
