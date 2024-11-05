package demo;

/**
 * @Author : just do it
 * @Date : 2024/11/5 13:57
 * @ApiNote :
 */

public class ToBase62 {
    private static final String CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public ToBase62() {
    }

    public static String encodeToBase62(long num) {
        StringBuffer sb = new StringBuffer();

        do {
            int i = (int)(num % 62L);
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(i));
            num /= 62L;
        } while(num > 0L);

        String value = sb.reverse().toString();
        return value;
    }

    public static void main(String[] args) {
        System.out.println(encodeToBase62(1000000000000000000L));
    }
}
