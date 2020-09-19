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


/**
 * Other's solution with Sort
 */
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> res = new ArrayList<>();
        for (int digit = 1; digit < 9; ++digit) {
            int next = digit;
			int n = next;
            while (n <= high && next < 10) {
                if (n >= low) {
                    res.add(n);
                }
				next++;
                int n1 = n * 10 + next;
				// check overflow
                if (n1 > n)
                    n = n1;
                else // Integer overflow.
                    break;
            }
        }
        Collections.sort(res);
        return res;        
    }
}


/**
 * Other's solution with Substring
 */
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList();
        String s = "123456789";
        for(int l = 2; l <= s.length(); l++) {
            for(int j = 0; j <= s.length() - l; j++) {
                int num = Integer.parseInt(s.substring(j, j + l));
                if(num > high) return result;
                if(num >= low) result.add(num);
            }
        }
        
        return result;
    }
}
