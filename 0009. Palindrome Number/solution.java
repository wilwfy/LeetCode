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
