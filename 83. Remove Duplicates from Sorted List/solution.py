# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        pre = head
        cur = head
        while (cur != None):
            if cur.val == pre.val:
                pre.next = cur.next
            else:
                pre = cur
            cur = cur.next
        return head
