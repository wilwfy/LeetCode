# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        if head is None:
            return True
        val = []
        cur = head
        while cur:
            val.append(cur.val)
            cur = cur.next
        for i in range(len(val)//2 + 1):
            if val[i] != val[-1-i]:
                return False
        return True
        
################################################################
# Other's Solution 1: Reversed first half == Second half?
# Phase 1: Reverse the first half while finding the middle.
# Phase 2: Compare the reversed first half with the second half.
################################################################
class Solution:
    def isPalindrome(self, head):
        rev = None
        slow = fast = head
        while fast and fast.next:
            fast = fast.next.next
            rev, rev.next, slow = slow, rev, slow.next
        if fast:
           slow = slow.next
        while rev and rev.val == slow.val:
            slow = slow.next
            rev = rev.next
        return not rev
        
################################################################
# Other's Solution 2: Play Nice
# Same as the above, but while comparing the two halves, restore
# the list to its original state by reversing the first half back.
################################################################
class Solution:
    def isPalindrome(self, head):
        rev = None
        fast = head
        while fast and fast.next:
            fast = fast.next.next
            rev, rev.next, head = head, rev, head.next
        tail = head.next if fast else head
        isPali = True
        while rev:
            isPali = isPali and rev.val == tail.val
            head, head.next, rev = rev, head, rev.next
            tail = tail.next
        return isPali
