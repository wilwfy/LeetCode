#!python3

class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        dict = {}            
        for i in range(len(nums)):
            #print(nums[i])
            complement = target - nums[i]
            if complement in dict:
                return [dict[complement], i]
            else:
                dict[nums[i]] = i
                #print(dict)
        print("No solution found!")
            
