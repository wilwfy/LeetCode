class Solution:
    def romanToInt(self, s: str) -> int:
        pre = ""
        sum = 0
        for c in s:
            if c == 'M':
                if pre == 'C':
                    sum -= 100 # substract 100 counted by previous 'C'
                    sum += 900
                else:
                    sum += 1000
            elif c == 'D':
                if pre == 'C':
                    sum -= 100 # substract 100 counted by previous 'C'
                    sum += 400
                else:
                    sum += 500
            elif c == 'C':
                if pre == 'X':
                    sum -= 10 # substract 10 counted by previous 'X'
                    sum += 90
                else:
                    sum += 100
            elif c == 'L':
                if pre == 'X':
                    sum -= 10 # substract 10 counted by previous 'X'
                    sum += 40
                else:
                    sum += 50
            elif c == 'X':
                if pre == 'I':
                    sum -= 1 # substract 1 counted by previous 'I'
                    sum += 9
                else:
                    sum += 10
            elif c == 'V':
                if pre == 'I':
                    sum -= 1 # substract 1 counted by previous 'I'
                    sum += 4
                else:
                    sum += 5
            elif c == 'I':
                sum += 1
            else:
                print("Error: Illegal Roman number!")
                return False
            pre = c
        return sum


######################
# Hash Table Solution
######################
class Solution:
    def romanToInt(self, s: str) -> int:
        dict = {'I': 1, 'V': 5, 'X': 10, 'L': 50, 'C': 100, 'D': 500, 'M': 1000}
        pre = ""
        sum = 0
        for c in s:
            try:
                sum += dict[c]
            except KeyError:
                print("Error: Illegal Roman number!")
            else:
                if (pre == 'I') and ((c == 'V') or (c == 'X')):
                    # subtract previous 'I' and decrease current 'V' or 'X'
                    sum -= 2
                if (pre == 'X') and ((c == 'L') or (c == 'C')):
                    # subtract previous 'X' and decrease current 'L' or 'C'
                    sum -= 20
                if (pre == 'C') and ((c == 'D') or (c == 'M')):
                    # subtract previous 'C' and decrease current 'D' or 'M'
                    sum -= 200
            pre = c
        return sum
