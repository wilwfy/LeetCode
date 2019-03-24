class Solution:
    def numberToWords(self, num: int) -> str:
        if num == 0:
            return 'Zero'
        if num > 4294967295:
            return ''
        
        r = 1
        eng_w = []
        eng_num = ''
        dict = {0: '', 1: 'One', 2: 'Two', 3: 'Three', 4: 'Four', 5: 'Five',\
                6: 'Six', 7: 'Seven', 8: 'Eight', 9: 'Nine', 10: 'Ten',\
                11: 'Eleven', 12: 'Twelve', 13: 'Thirteen', 14: 'Fourteen',\
                15: 'Fifteen', 16: 'Sixteen', 17: 'Seventeen', 18: 'Eighteen',\
                19: 'Nineteen', 20: 'Twenty', 30: 'Thirty', 40: 'Forty',\
                50: 'Fifty', 60: 'Sixty', 70: 'Seventy', 80: 'Eighty', 90: 'Ninety',\
                100: 'Hundred', 1000: 'Thousand', 1000000: 'Million', 1000000000: 'Billion'}
        
        # All the words are added in a reverse order
        # But it will be fixed since final output will be generate in a reverse order again
        while (num):   
            num, digit = divmod(num, 1000)
            if digit == 0:
                r = r * 1000
                continue
            
            # Add the decimal range level -- 'Thousand', 'Million', 'Billion'
            if r > 1:
                eng_w.append(dict[r])
                
            hundr, dec = divmod(digit, 100)

            if dec >= 20:
                dec, unit = divmod(dec, 10)
                # Ignore 0 on the decimal unit position
                if unit != 0:
                    eng_w.append(dict[unit])
                eng_w.append(dict[dec*10])
            elif dec > 0:
                # Still ignore 0, only deal with 1 ~ 19
                eng_w.append(dict[dec])

            if hundr != 0:
                eng_w.append(dict[100])
                eng_w.append(dict[hundr])
            
            r = r * 1000
        
        if len(eng_w) > 1:
            for i in range(len(eng_w)-1, 0, -1):
                eng_num += eng_w[i] + ' '
        eng_num += eng_w[0]
        return eng_num
