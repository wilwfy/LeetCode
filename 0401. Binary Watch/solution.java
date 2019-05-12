class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                if (Integer.bitCount(h * 64 + m) == num)
                    res.add(String.format("%d:%02d", h, m));
            }
        }
        return res;
    }
}


/*
 * Other's Solution: Backtracking
 */
class Solution {
    
    int[] weight = {8, 4, 2, 1, 32, 16, 8, 4, 2, 1};
    
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        helper(0, 0, 0, num, res);
        return res;
    }
    
    private void helper(int hour, int min, int index, int num, List<String> res) {
        if (hour > 12 || min >= 60 || hour == 12 && min == 0) {
            return;
        }
        if (num == 0) {
            String strMin = String.valueOf(min);
            if (strMin.length() == 1) {
                strMin = "0" + strMin;
            }
            res.add(String.valueOf(hour) + ":" + strMin);
        } else if (index < 10 && num <= 10 - index && num > 0) {
            helper(hour, min, index + 1, num, res);
            if (index < 4) {
                helper(hour + weight[index], min, index + 1, num - 1, res);
            } else {
                helper(hour, min + weight[index], index + 1, num - 1, res);
            }
        }
    }
}


/*
 * Other's Solution: Backtracking and Idea of "Permutation and Combination"
 */
class Solution {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1};
        int[] nums2 = new int[]{32, 16, 8, 4, 2, 1};
        // don't need to compute the condition where i>4 or num - i > 6
        for(int i = 0; i <= 4 && i <= num; i++) {
            if(num - i > 6) continue;
            List<Integer> list1 = generateDigit(nums1, i); // hour
            List<Integer> list2 = generateDigit(nums2, num - i); // minitues
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }
    
    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }
    
    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
        }
    }
}
