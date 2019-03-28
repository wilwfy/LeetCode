class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        dict = {}
        for num in nums:
            if num not in dict:
                dict[num] = 1
            else:
                return True
        return False
