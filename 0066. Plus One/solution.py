class Solution:
    def plusOne(self, digits: List[int]) -> List[int]:
        carry = 1
        for i in range(-1, -len(digits)-1, -1):
            a = digits[i]
            digits[i] = (a + carry) % 10
            carry = (a + carry) // 10
        if carry == 1:
            digits.insert(0, 1)
        return digits
