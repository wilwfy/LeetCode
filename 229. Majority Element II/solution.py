class Solution:
    def majorityElement(self, nums: List[int]) -> List[int]:
        if len(nums) == 0:
            return []
        elif len(nums) == 1:
            return nums
        
        counts = collections.Counter(nums)
        major = []
        for key in counts.keys():
            if counts.get(key) > (len(nums)//3):
                major.append(key)
                if len(major) == 2:
                    break
        return major
