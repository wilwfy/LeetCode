class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        zero_p = -1
        for i in range(len(nums)):
            if nums[i] == 0:
                if zero_p < 0:
                    zero_p = i
            else:
                if zero_p >= 0:
                    nums[zero_p], nums[i] = nums[i], 0
                    zero_p += 1
