class Solution:
    def merge(self, nums1: 'List[int]', m: 'int', nums2: 'List[int]', n: 'int') -> 'None':
        """
        Do not return anything, modify nums1 in-place instead.
        """
        for i in range(len(nums2)):
            nums1[m+i] = nums2[i]

        for i in range(m, m+n):
            for j in range(i, 0, -1):
                if j == 0:
                    break
                if nums1[j] <= nums1[j - 1]:
                    tmp = nums1[j-1]
                    nums1[j-1] = nums1[j]
                    nums1[j] = tmp
                else:
                    break
                    
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
