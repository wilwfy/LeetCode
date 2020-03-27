/*
 * Other's solution
 *
 * This solution is just a java version derived from this post.  https://www.geeksforgeeks.org/find-next-greater-number-set-digits/
 *
 * At first, lets look at the edge cases -
 * If all digits sorted in descending order, then output is always “Not Possible”. For example, 4321.
 * If all digits are sorted in ascending order, then we need to swap last two digits. For example, 1234.
 * For other cases, we need to process the number from rightmost side (why? because we need to find the smallest of all greater numbers)
 * Now the main algorithm works in following steps -
 *   I) Traverse the given number from rightmost digit, keep traversing till you find a digit which is smaller than the previously
 *      traversed digit. For example, if the input number is “534976”, we stop at 4 because 4 is smaller than next digit 9. If we do
 *      not find such a digit, then output is “Not Possible”.
 *   II) Now search the right side of above found digit ‘d’ for the smallest digit greater than ‘d’. For “534976″, the right side of 4
 *       contains “976”. The smallest digit greater than 4 is 6.
 *   III) Swap the above found two digits, we get 536974 in above example.
 *   IV) Now sort all digits from position next to ‘d’ to the end of number. The number that we get after sorting is the output. For
 *       above example, we sort digits in bold 536974. We get “536479” which is the next greater number for input 534976.
 */
class Solution {
    public int nextGreaterElement(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        
        int i, j, size = digits.length;
        // I) Start from the right most digit and find the first digit that is
        // smaller than the digit next to it.
        for (i = size-1; i > 0; i--) {
            if (digits[i-1] < digits[i]) break;
        }
        // If no such digit is found, its the edge case 1.
        if (i == 0) // The digits is descending, no possible answer
            return -1;
        
        // II) Find the smallest digit on right side of (i-1)'th 
        // digit that is greater than digits[i-1]
        char tmp = digits[i-1];
        for (j = size-1; j >= i; j--) {
            // The digits in index range of i ~ n is descending
            if (digits[j] > tmp) break;
        }
        
        // III) Swap the above found smallest digit with digits[i-1]
        digits[i-1] = digits[j];
        digits[j] = tmp;
        
        // IV) Sort the digits after (i-1) in ascending order.
        // But switching is faster than Arrays.sort() because O(n) < O(nlogn)
        // It works because this part of digits is descending
        j = size-1;
        while (i < j) {
            tmp = digits[i];
            digits[i] = digits[j];
            digits[j] = tmp;
            i++;
            j--;
        }
        
        // The tricky part is the final result could go beyond Integer.MAX_VALUE.
        long number = Long.parseLong(new String(digits));
        return number > Integer.MAX_VALUE ? -1 : (int) number;
    }
}
