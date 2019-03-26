# Definition for singly-linked list.
# class ListNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.next = None

##################################################
# Hash Table Solution
# Time complexity : O(n)
# Space complexity: O(n)
##################################################
class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if (not head) or (head.next == None):
            return False
        dict = {}
        while (head != None):
            if (head.next in dict):
                return True
            else:
                dict[head.next] = 1
            head = head.next
        return False


##################################################
# Solution based on two pointers by
# referring to official's Java solution
# Time complexity : O(n)
# Space complexity: O(1). We only use two nodes (slow and fast) so the space complexity is O(1).
##################################################
class Solution(object):
    def hasCycle(self, head):
        """
        :type head: ListNode
        :rtype: bool
        """
        if (not head) or (head.next == None):
            return False
        slower = head
        faster = head.next
        while (slower != faster):
            if (faster == None) or (faster.next == None):
                return False
            else:
                slower = slower.next
                faster = faster.next.next
        return True
