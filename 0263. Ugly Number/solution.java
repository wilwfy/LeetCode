/**
 * My solution
 *
 * Time: O(logn). If the num is n and n = 2^m.
 * Space: O(1).
 */
class Solution {
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        while (num > 1) {
            if (num % 2 == 0) {
                num /= 2;
                continue;
            }
            if (num % 3 == 0) {
                num /= 3;
                continue;
            }
            if (num % 5 == 0) {
                num /= 5;
                continue;
            }
            return false;
        }
        return true;
    }
}


/**
 * Other's solution
 */
class Solution {
    public boolean isUgly(int num) {
        if(num==1) return true;
        if(num==0) return false;
    	  while(num%2==0) num=num>>1;
    	  while(num%3==0) num=num/3;
    	  while(num%5==0) num=num/5;
        return num==1;
    }
}

    
/**
 * Other's solution
 */
class Solution {
    public boolean isUgly(int num) {
        for (int i=2; i<6 && num>0; i++)
            while (num % i == 0)
                num /= i;
        return num == 1;
    }
}
