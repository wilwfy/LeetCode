class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        pos_nums = []
        for num in nums:
            if num > 0:
                pos_nums.append(num)
        pos_nums.sort()
        if len(pos_nums) == 0:
            return 1
        elif pos_nums[0] > 1:
            return 1
        else:
            for i in range(1, len(pos_nums)):
                if pos_nums[i] - pos_nums[i-1] > 1:
                    return pos_nums[i-1] + 1
            return pos_nums[-1] + 1
                
