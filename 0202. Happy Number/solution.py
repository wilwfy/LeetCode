class Solution:
    def isHappy(self, n: int) -> bool:
        if n <= 0:
            return False
        else:
            # Use Hash table to check if the sum is being repeated
            dict = {}
            sum = 0
            while (sum not in dict):
                dict[sum] = 1
                sum = 0
                while (n) > 0:
                    sum += (n % 10)**2
                    n = n//10
                n = sum
                #print(sum)
                #print(dict)
            if sum == 1:
                return True
            else:
                return False
