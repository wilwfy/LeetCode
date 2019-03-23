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

    
# Other's Solution
# Make the key in Hash Table computable
class Solution:
    def intToRoman(self, num: int) -> str:
        dic={1:"I", 5:"V", 10:"X", 50:"L", 100:"C", 500:"D", 1000:"M"}
        a = num
        b = 0
        res = ""
        r = 1
	
        while (a):
            a, b = divmod(a,10)
            if b>=1 and b<4:
                res += dic[r] * b
            elif b==4:
                res += dic[5*r] + dic[r]
            elif b==5:
                res += dic[5*r]
            elif 5<b and b<9:
                res += dic[r]*(b-5) + dic[5*r]
            elif b==9:
                res += dic[10*r] + dic[r]
            r = r*10
        
        return res[::-1]
