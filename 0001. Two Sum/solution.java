class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> table = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            //if (table.get(complement) != null) {
            if (table.containsKey(complement)) {
                return new int[] {i, table.get(complement)};
            } else {
                table.put(nums[i], i);
            }
        }
        //return new int[] {};
        throw new IllegalArgumentException("No two sum solution");
    }
}
