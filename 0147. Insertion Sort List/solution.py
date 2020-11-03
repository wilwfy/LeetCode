"""
Other's solution without extra space
(Converted from Java solution)
Time: O(n^2)
Space: O(1)
"""
class Solution:
    def insertionSortList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head
        
        dummy = ListNode(head.val);
        dummy.next = head
        prev = dummy
        cur = head
        while (cur is not None):
            if cur.val >= prev.val:
                prev = cur
                cur = cur.next
            else:
                prev.next = cur.next
                start = dummy
                while (start.next.val <= cur.val):
                    start = start.next
                cur.next = start.next
                start.next = cur
                cur = prev.next
        return dummy.next

# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
