# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution(object):
    def detectCycle(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        if (head == None) or (head.next == None):
            return None
        
        dict = {}
        dummy = ListNode(None)
        dummy.next = head
        cur = dummy
        while (cur != None):
            if cur.next in dict:
                return cur.next
            else:
                dict[cur.next] = 1
            cur = cur.next
        return None

    
##################################
# A solution without using extra space based on two pointers
# can solve this problem by using Floyd's algorithm.
# This approach is explained in the video below:
# https://www.youtube.com/watch?time_continue=2&v=zbozWoMgKW0
##################################
