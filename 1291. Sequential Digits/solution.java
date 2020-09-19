/**
 * My solution of 0 ms and beaten 100%
 */
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        
        // find the digit length of the low
        int lowLen = 0, tmp = low;
        while (tmp > 0) {
            lowLen++;
            tmp /= 10;
        }
        
        for (int len = lowLen; ; len++) {
            int num = getStartNum(len);
            int step =  getIncrement(len);
            if (num > high) break;
            for (;;) {
                if (num >= low && num <= high) res.add(num);
                if ((num % 10) == 9) break; // If the last digit is 9, then done with current loop
                num += step;
            }
        }
        return res;
    }
    
    private int getStartNum(int len) {
        int res = 0;
        for (int i = 1; i <= len; i++)
            res = res * 10 + i;
        return res;
    }
    
    private int getIncrement(int len) {
        int res = 0;
        for (int i = 1; i <= len; i++)
            res = res * 10 + 1;
        return res;
    }
}
