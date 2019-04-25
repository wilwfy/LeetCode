public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) return 0;
        int res = 0, sign = 1;
        if (n < 0) {
            //System.out.println(n);
            //System.out.println(Integer.MIN_VALUE);
            n = n - Integer.MIN_VALUE;
            //System.out.println(n);
            sign = -1;
        }
        for (int i = 0; i < 32; ++i) {
            res = res*2 + n%2;
            n = n/2;
        }
        return (sign > 0) ? res : res + 1;
    }
}
