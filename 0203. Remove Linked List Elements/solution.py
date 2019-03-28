# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def removeElements(self, head: ListNode, val: int) -> ListNode:
        dummy = ListNode(None)
        dummy.next = head
        pre = dummy
        cur = head
        while (cur):
            if cur.val == val:
                pre.next = cur.next
                if cur == head:
                    head = cur.next
                cur = cur.next
                continue
            cur = cur.next
            pre = pre.next
        return head
