# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

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
