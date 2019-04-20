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


/*
 * Official solution
 *
 * Time Complexity: O(log(x)). There are log10(x) digits in x.
 * Space Complexity: O(1).
 */
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}


/*
 * Other's solution 1
 */
class Solution {
    public int reverse(int x) {
        int result = 0;  
        while (x != 0) {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
            x = x / 10;
        }
        return result;
    }
}


/*
 * Other's solution 2
 */
class Solution {
    public int reverse(int x) {
        long rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
            x= x/10;
            if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return (int) rev;
    }
}
