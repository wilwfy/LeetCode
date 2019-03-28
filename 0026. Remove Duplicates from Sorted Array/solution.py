class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        length = len(nums)

        if length <= 1:
            return length
        
        val_chk = nums[0]
        cnt = 1
        uniq_end = 1
        while cnt < len(nums):
            if nums[cnt] != val_chk:
                val_chk = nums[cnt]
                nums[uniq_end] = val_chk
                uniq_end += 1
            cnt += 1
        #print (uniq_end)
        return uniq_end ## The value of uniq_end equals to the length we desired
