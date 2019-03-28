class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        n = len(nums)
        if (n == 0) or (not nums):
            return None
        if n == 1:
            return nums[0]
        
        dict = {}
        for num in nums:
            if num not in dict:
                dict[num] = 1
            else:
                dict[num] += 1
                if dict[num] > (n//2):
                    return num
        return None


########################################
# Official solution by using Hash based on collections
# Time complexity : O(n)
# Space complexity : O(n)
########################################
class Solution:
    def majorityElement(self, nums):
        counts = collections.Counter(nums)
        return max(counts.keys(), key=counts.get)


########################################
# Another official solution by using
# Boyer-Moore Voting Algorithm
# Time complexity : O(n)
# Space complexity : O(1)
########################################
class Solution:
    def majorityElement(self, nums):
        count = 0
        candidate = None

        for num in nums:
            if count == 0:
                candidate = num
            count += (1 if num == candidate else -1)

        return candidate
