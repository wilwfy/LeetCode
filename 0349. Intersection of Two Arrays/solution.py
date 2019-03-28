class Solution:
    def intersection(self, nums1: List[int], nums2: List[int]) -> List[int]:
        ref1 = set(nums1)
        ref2 = set(nums2)
        #return ref1.intersection(ref2)
        a = list(ref2) if len(ref1) > len(ref2) else list(ref1)
        b = ref1 if len(ref1) > len(ref2) else ref2
        common = []
        for num in a:
            if num in b:
                common.append(num)
        return common
        
################################################################
# Official solution by using Two Sets
# Time complexity : O(n+m)
# Space complexity : O(m+n) in the worst case when all elements
#                    in the arrays are different
################################################################
class Solution:
    def set_intersection(self, set1, set2):
        return [x for x in set1 if x in set2]
        
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """  
        set1 = set(nums1)
        set2 = set(nums2)
        
        if len(set1) < len(set2):
            return self.set_intersection(set1, set2)
        else:
            return self.set_intersection(set2, set1)


################################################################
# Official solution by using Built-in
# Set Intersection
# Time complexity : O(n+m) in the average case and O(n×m) in the
#                    worst case when load factor is high enough
# Space complexity : O(m+n) in the worst case when all elements
#                    in the arrays are different
################################################################
class Solution:
    def intersection(self, nums1, nums2):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :rtype: List[int]
        """  
        set1 = set(nums1)
        set2 = set(nums2)
        return list(set2 & set1)
