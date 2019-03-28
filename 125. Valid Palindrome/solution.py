class Solution:
    def isPalindrome(self, s: str) -> bool:
        length = len(s)
        if length <= 1:
            return True
        i = 0
        j = length - 1
        while (i <= j):
            ascii_1 = ord(s[i])
            ascii_2 = ord(s[j])
            print(s[i], ascii_1)
            print(s[j], ascii_2)
            # In Python, the Ascii Code values are like:
            # 'A' - 65, 'Z' - 90, 'a' - 97, 'z' - 122
            # '0' - 48, '9' - 57
            if (ascii_1 < 48) or ((57 < ascii_1) and (ascii_1 < 65)) or ((90 < ascii_1) and (ascii_1 < 97)) or (ascii_1 > 122):
                i += 1
                continue
            if (ascii_2 < 48) or ((57 < ascii_2) and (ascii_2 < 65)) or ((90 < ascii_2) and (ascii_2 < 97)) or (ascii_2 > 122):
                j -= 1
                continue
            if (ascii_1 != ascii_2):
                if (ascii_1 > 57) and (ascii_2 > 57) and (abs(ascii_1 - ascii_2) == 32):
                    # Same alphabet in lower and upper cases respectively
                    i += 1
                    j -= 1
                    continue
                return False
            else:
                i += 1
                j -= 1
        return True
