/*
 * My solution of Counting Sort of Two Pass
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public void sortColors(int[] nums) {
        int[] cnt = new int[3];
        for (int num: nums)
            cnt[num]++;
            
        int i = 0;
        for (int j = 0; j < 3; j++) {
            while (cnt[j]-- > 0)
                nums[i++] = j;
        }
    }
}


/*
 * Other's solution of One Pass with constant space
 */
class Solution {
    public void sortColors(int[] nums) {            
        int zero = 0, second = nums.length - 1;
        for (int i = zero; i <= second;) {
            if (nums[i] == 0) {
                // swap A[i] and A[zero], then i and zero both ++
                int tmp = nums[zero];
                nums[zero++] = nums[i];
                nums[i++] = tmp;
            } else if (nums[i] == 2) {
                //swap A[i] and A[second] and second--;
                int tmp = nums[second];
                nums[second--] = nums[i];
                nums[i] = tmp;
            } else {
                i++;
            }
        }
    }
}


/*
 * Other's solution of One Pass with constant space
 */
public void sortColors(int[] nums) {
    // 1-pass
    int p1 = 0, p2 = nums.length - 1, index = 0;
    while (index <= p2) {
        if (nums[index] == 0) {
            nums[index] = nums[p1];
            nums[p1] = 0;
            p1++;
        }
        if (nums[index] == 2) {
            nums[index] = nums[p2];
            nums[p2] = 2;
            p2--;
            index--;
        }
        index++;
    }
}
