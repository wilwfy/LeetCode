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
