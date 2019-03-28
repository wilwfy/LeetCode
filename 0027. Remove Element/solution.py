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
        
        # Considering the order of elements could be changed,
        # We scan the list from the tail and replace the element
        # which contains desired value with the element at tail.
        # Then we cut off that old tail.
        new_end = length - 1;
        for cnt in range(length-1, -1, -1):
            if nums[cnt] == val:
                nums[cnt] = nums[new_end]
                new_end -= 1

        return new_end + 1
