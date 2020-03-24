/*
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int num: nums) {
            String str = Integer.toString(num);
            if (str.length()%2 == 0) res++;
        }
        return res;
    }
}


class Solution {
    public int findNumbers(int[] nums) {
        int res = 0;
        for (int num: nums) {
            int cnt = 0;
            while (num > 0) {
                num = num / 10;
                cnt++;
            }
            if (cnt%2 == 0) res++;
        }
        return res;
    }
}


class Solution {
    public int findNumbers(int[] nums) {
        int cnt = 0;
        for (int n : nums)
            cnt += 1 - Integer.toString(n).length() % 2;
        return cnt;
    }
}


/*
 * Other's concise solution with Stream
 */
class Solution {
    public int findNumbers(int[] nums) {
        return Arrays.stream(nums).map(i -> 1 - Integer.toString(i).length() % 2).sum();
    }
}
