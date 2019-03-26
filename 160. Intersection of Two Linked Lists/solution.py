# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

####################################
# Hash Table solution
# Time complexity : O(m+n)
# Space complexity : O(m) or O(n) 
####################################
class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        if (headA == None) or (headB == None):
            return None
        dummyA = ListNode(None)
        dummyB = ListNode(None)
        dummyA.next = headA
        dummyB.next = headB
        cur_A = dummyA
        cur_B = dummyB
        dict = {}
        while (cur_A != None):
            dict[cur_A.next] = 1
            cur_A = cur_A.next
        while (cur_B != None):
            if cur_B.next in dict:
                return cur_B.next
            cur_B = cur_B.next
        return None


####################################
# Two Pointers solution
# Time complexity : O(m+n)
# Space complexity : O(1) 
# Explanation:
# 1. Maintain two pointers pApA and pBpB initialized at the head of A and B, respectively. 
#    Then let them both traverse through the lists, one node at a time.
# 2. When pApA reaches the end of a list, then redirect it to the head of B (yes, B, that's
#    right.); similarly when pBpB reaches the end of a list, redirect it the head of A.
# 3. If at any point pApA meets pBpB, then pApA/pBpB is the intersection node.
# 4. If two lists have intersection, then their last nodes must be the same one. So when 
#    pApA/pBpB reaches the end of a list, record the last element of A/B respectively. If
#    the two last elements are not the same one, then the two lists have no intersections.
####################################
class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        if (headA == None) or (headB == None):
            return None
        dummyA = ListNode(None)
        dummyB = ListNode(None)
        dummyA.next = headA
        dummyB.next = headB
        cur_A = dummyA
        cur_B = dummyB
        while (cur_A.next != None) or (cur_B.next != None):
            if cur_A.next == cur_B.next:
                return cur_A.next
            if cur_A.next == None and cur_B.next != None:
                cur_A = dummyB
                cur_B = cur_B.next
            elif cur_B.next == None and cur_A.next != None:
                cur_B = dummyA
                cur_A = cur_A.next
            else:
                cur_A = cur_A.next
                cur_B = cur_B.next
        lastA = cur_A
        lastB = cur_B
        if lastA is not lastB:
            return None
        else:
            return lastA


####################################
# Other's Two Pointers solution
####################################
class Solution:
    # @param two ListNodes
    # @return the intersected ListNode
    def getIntersectionNode(self, headA, headB):
        if headA is None or headB is None:
            return None

        pa = headA # 2 pointers
        pb = headB

        while pa is not pb:
            # if either pointer hits the end, switch head and continue the second traversal, 
            # if not hit the end, just move on to next
            pa = headB if pa is None else pa.next
            pb = headA if pb is None else pb.next

        return pa # only 2 ways to get out of the loop, they meet or the both hit the end=None

# the idea is if you switch head, the possible difference between length would be countered. 
# On the second traversal, they either hit or miss. 
# if they meet, pa or pb would be the node we are looking for, 
# if they didn't meet, they will hit the end at the same iteration, pa == pb == None, return either one of them is the same,None
