package Code.Main;

public class DevKashyap {
    private DevKashyap() {
        super();
    }

    private static boolean[] digit = new boolean[256];
    private static int[] value = new int[256];

    static {
        for (int i = '0'; i <= '9'; i++)
            digit[i] = true;

        for (int i = '0'; i <= '9'; i++)
            value[i] = i - '0';
    }

    public static long getSuminString(String s) {
        long res = 0, temp = 0;
        for (int i = 0; i < s.length(); i++) {
            if (digit[s.charAt(i)])
                temp = temp * 10 + value[s.charAt(i)];
            else {
                res += temp;
                temp = 0;
            }
        }
        return res + temp;
    }

    public static boolean isAnagram(String a, String b) {
        if (a.length() != b.length())
            return false;

        int[] map = new int[256];
        for (int i = 0; i < a.length(); i++) {
            map[a.charAt(i)]++;
            map[b.charAt(i)]--;
        }

        for (int i = 0; i < 256; i++)
            if (map[i] != 0)
                return false;

        return true;
    }

    public static String reverseSum(String a, String b) {
        int temp = '0' + '0';
        StringBuilder sb = new StringBuilder();
        int min = a.length() > b.length() ? b.length() : a.length();
        int carry = 0;
        for (int i = 0; i < min; i++) {
            int sum = a.charAt(i) + b.charAt(i) - temp + carry;
            carry = sum / 10;
            sb.append(sum % 10);
        }

        for (int i = min; i < a.length(); i++) {
            int sum = a.charAt(i) + carry - '0';
            carry = sum / 10;
            sb.append(sum % 10);
        }

        for (int i = min; i < b.length(); i++) {
            int sum = b.charAt(i) + carry - '0';
            carry = sum / 10;
            sb.append(sum % 10);
        }
        if (carry > 0)
            sb.append(carry);

        //        StringBuilder res = new StringBuilder();
        int i = 0;
        while (i < sb.length() && sb.charAt(i) == '0')
            i++;

        return sb.substring(i);

        //        return sb.toString();
    }

    /**
     * This function reverse each word without changing their order.
     * for example the given string is "test the  bud" the returned string is
     * "tset eht  dub"
     *
     * @param s String to be formated
     * @return returns the reversed words string.
     */

    public static String reverseWords(String s) {
        int i = 0, j = 0;
        StringBuilder sb = new StringBuilder();

        while (j < s.length() && s.charAt(j) == ' ') {
            sb.append(' ');
            j++;
        }

        i = j;

        while (j < s.length() && s.charAt(j) != ' ')
            j++;

        while (i < s.length()) {
            for (int k = j - 1; k >= i; k--)
                sb.append(s.charAt(k));

            while (j < s.length() && s.charAt(j) == ' ') {
                j++;
                sb.append(' ');
            }

            i = j;
            while (j < s.length() && s.charAt(j) != ' ')
                j++;
        }

        return sb.toString();
    }
}
