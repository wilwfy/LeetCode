class Solution:
    def intToRoman(self, num: int) -> str:
        num_char = str(num)
        dec_pos = len(num_char)
        roman = ''
        dict1 = {4: 'M', 3: 'C', 2: 'X', 1: 'I'}
        dict2 = {4: '',  3: 'D', 2: 'L', 1: 'V'}
        #for i, c in reversed(list(enumerate(num_char))):
        for c in num_char:
            if int(c) == 0:
                roman = roman
            elif int(c) <= 3:
                roman = roman + dict1[dec_pos]*int(c)
            elif int(c) == 4:
                roman = roman + dict1[dec_pos] + dict2[dec_pos]
            elif int(c) < 9:
                roman = roman + dict2[dec_pos] + dict1[dec_pos]*(int(c) - 5)
            elif int(c) == 9:
                roman = roman + dict1[dec_pos] + dict1[dec_pos+1]
            dec_pos -= 1
        return roman
