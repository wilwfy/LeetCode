class Solution:
    def merge(self, nums1: 'List[int]', m: 'int', nums2: 'List[int]', n: 'int') -> 'None':
        """
        Do not return anything, modify nums1 in-place instead.
        """
        index2 = 0
        for index1 in range(m+n):
            if (index2 < n) and (nums2[index2] <= nums1[index1]):
                nums1.insert(index1, nums2[index2])
                nums1.pop()  # Remove rightmost element
                index2 += 1
        # Copy remaining elements of nums2 to the tail of nums1
        if index2 < n:
            nums1[m+index2:m+n] = nums2[index2:n]

                    
def main():
    solution = Solution()

    num_list1 = [1,2,3,0,0,0]
    m = 3
    num_list2 = [2,5,6]
    n = 3
    solution.merge(num_list1, m, num_list2, n)

    num_list1 = [2,0]
    m = 1
    num_list2 = [1]
    n = 1
    solution.merge(num_list1, m, num_list2, n)


if __name__ == '__main__':
    main()
