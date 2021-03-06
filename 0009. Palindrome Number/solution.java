class Solution {
    public boolean isPalindrome(int x) {
        String str = new Integer(x).toString();
        int size = str.length();
        for (int i = 0; i < size/2; i++) {
            if (str.charAt(i) != str.charAt(size - 1 - i))
                return false;
        }
        return true;
    }
}


/*
 * Solution for the follow up without converting Integer to String
 */
class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x == 0) return true;
        List nums = new ArrayList<Integer>();
        while (x > 0) {
            nums.add(x%10);
            x /= 10;
        }
        //System.out.println("size = " + size);
        int size = nums.size();
        for (int i = 0; i < size/2; i++) {
            if (nums.get(i) != nums.get(size - 1 - i))
                return false;
        }
        return true;
    }
}


/*
 * Other's Solution without converting Integer to String
 * by referring to official C++ solution.
 *
 * Time complexity : O(log10(n)). We divided the input by 10 for every iteration.
 * Space complexity : O(1).
 */
class Solution {
    public boolean isPalindrome(int x) {
        if (x<0 || (x!=0 && x%10==0)) return false;
        int revertedNumber = 0;
        while (x>revertedNumber){
        	revertedNumber = revertedNumber*10 + x%10;
        	x = x/10;
        }
        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // For example when the input is 12321, at the end of the while loop we get x = 12, revertedNumber = 123,
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return (x==revertedNumber || x==revertedNumber/10);
    }
