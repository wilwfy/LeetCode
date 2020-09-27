/**
 * My solution of One Pass
 *
 * Time: O(N)
 * Space: O(1)
 */
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (n == 0) return true;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) continue;
            int prev = (i == 0) ? 0 : flowerbed[i - 1];
            int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
            if (prev == 0 && next == 0) {
                flowerbed[i] = 1;
                n--;
                if (n == 0) return true;
            }
        }
        return false;
    }
}


/**
 * Official solution of One Pass
 *
 * Time: O(N)
 * Space: O(1)
 */
public class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int i = 0, count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1;
                count++;
            }
             if(count>=n)
                return true;
            i++;
        }
        return false;
    }
}
