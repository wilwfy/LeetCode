class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        if not needle:
            return 0
        if len(haystack) < len(needle):
            return -1
        i = 0
        j = 0
        end = len(needle) - 1
        valid_remain = len(haystack) - len(needle)
        # If the left length of haystack is not enough for a whole new search,
        # then get out of the while() loop
        while (i <= valid_remain):
            for k in range(i, len(haystack)):
                if haystack[k] == needle[j]:
                    in_chk = 1
                    if j == end:
                        return k - j
                    j += 1
                else:
                    break
            # Every time failed to find needle, need restart the
            # search from the index i = k - j + 1
            i = k - j + 1
            #print("i = ", i)
            j = 0
        return -1
