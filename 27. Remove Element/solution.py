class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        length = len(nums)
        if length < 1:
            return length
        
        new_end = length - 1;
        for cnt in range(length-1, -1, -1):
            if nums[cnt] == val:
                nums[cnt] = nums[new_end]
                new_end -= 1

        return new_end + 1
