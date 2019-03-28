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
