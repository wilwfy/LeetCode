class Solution {
    public int reverse(int x) {
        int sign = (x < 0) ? -1 : 1;
        int val = Math.abs(x);
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.MIN_VALUE);
        int res = 0;
        while (val > 0) {
            if ((res > Integer.MAX_VALUE/10) || (res < Integer.MIN_VALUE/10))
                return 0;
            res = res * 10 + (val%10)* sign;
            val = val/10;
        }
        return res;
    }
}
