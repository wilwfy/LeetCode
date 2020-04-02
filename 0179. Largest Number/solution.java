/*
 * My solution by referring to official solution
 */
class Solution {
    public String largestNumber(int[] nums) {
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            str[i] = String.valueOf(nums[i]);
            
        // Ascending order: (a + b).compareTo(b + a)
        // Descending order: (b + a).compareTo(a + b)
        Arrays.sort(str, (a, b) -> ((b + a).compareTo(a + b)));
        
        // In case of input like [0, 0], the expecte output is "0" not "00"
        if (str[0].equals("0"))
            return "0";
        
        String res = new String();
        for (String s: str)
            res += s;
        return res;
    }
}


/*
 * Official solution with Sorting via Custom Comparator
 *
 * Intuition:
 * To construct the largest number, we want to ensure that the most significant digits are occupied by the largest digits.
 *
 * While it might be tempting to simply sort the numbers in descending order, this causes problems for sets of numbers with the same
 * leading digit. For example, sorting the problem example in descending order would produce the number 9534303, while the correct
 * answer can be achieved by transposing the 3 and the 30. Therefore, for each pairwise comparison during the sort, we compare the
 * numbers achieved by concatenating the pair in both orders.
 *
 * Once the array is sorted, the most "significant" number will be at the front. There is a minor edge case that comes up when the
 * array consists of only zeroes, so if the most significant number is 0, we can simply return 0. Otherwise, we build a string
 * out of the sorted array and return it.
 *
 * Time complexity : O(nlgn)
 *                   Although we are doing extra work in our comparator, it is only by a constant factor. Therefore, the overall runtime
 *                   is dominated by the complexity of sort, which is O(nlgn) in Python and Java.
 * Space complexity : O(n)
 *                    Here, we allocate O(n) additional space to store the copy of nums. Although we could do that work in place (if we
 *                    decide that it is okay to modify nums), we must allocate O(n) space for the final return string. Therefore, the
 *                    overall memory footprint is linear in the length of nums.
 */
class Solution {
    private class LargerNumberComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String order1 = a + b;
            String order2 = b + a;
           return order2.compareTo(order1);
        }
    }

    public String largestNumber(int[] nums) {
        // Get input integers as strings.
        String[] asStrs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            asStrs[i] = String.valueOf(nums[i]);
        }

        // Sort strings according to custom comparator.
        Arrays.sort(asStrs, new LargerNumberComparator());

        // If, after being sorted, the largest number is `0`, the entire number
        // is zero.
        if (asStrs[0].equals("0")) {
            return "0";
        }

        // Build largest number from sorted array.
        String largestNumberStr = new String();
        for (String numAsStr : asStrs) {
            largestNumberStr += numAsStr;
        }

        return largestNumberStr;
    }
}
