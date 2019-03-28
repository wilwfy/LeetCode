class Solution(object):
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        for i, val in enumerate(nums):
            if target <= val:
                return i

        return len(nums)
