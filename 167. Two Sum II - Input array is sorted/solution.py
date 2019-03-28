class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        dict = {}
        for i in range(len(numbers)):
            compliment = target - numbers[i]
            if compliment in dict:
                return [dict[compliment]+1, i+1]
            else:
                dict[numbers[i]] = i
        return [] # No solution found


#########################################
# Other's solutions
#########################################
class Solution:
    # two-pointer
    def twoSum1(self, numbers, target):
        l, r = 0, len(numbers)-1
        while l < r:
            s = numbers[l] + numbers[r]
            if s == target:
                return [l+1, r+1]
            elif s < target:
                l += 1
            else:
                r -= 1
     
    # dictionary           
    def twoSum2(self, numbers, target):
        dic = {}
        for i, num in enumerate(numbers):
            if target-num in dic:
                return [dic[target-num]+1, i+1]
            dic[num] = i
     
    # binary search        
    def twoSum(self, numbers, target):
        for i in xrange(len(numbers)):
            l, r = i+1, len(numbers)-1
            tmp = target - numbers[i]
            while l <= r:
                mid = l + (r-l)//2
                if numbers[mid] == tmp:
                    return [i+1, mid+1]
                elif numbers[mid] < tmp:
                    l = mid+1
                else:
                    r = mid-1
                    
