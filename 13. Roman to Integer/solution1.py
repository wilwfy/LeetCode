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
