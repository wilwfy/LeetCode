# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        if not head:
            return head
        pre = cur = head
        dup = False
        while (cur.next != None):
            #print(head.val)
            #print(cur.val)
            if cur.val == cur.next.val:
                dup = True
                cur.next = cur.next.next
            else:
                if dup:
                    # Relocate head to ignore the duplicated elements in head part
                    if pre == head and pre.val == cur.val:
                        head = cur.next
                        pre = cur.next
                    else:
                        pre.next = cur.next
                    dup = False
                    cur = cur.next
                else:
                    pre = cur
                    cur = cur.next
        #print(head.val)
        # The last element is still a duplicated element
        if dup:
            # Relocate head to ignore the duplicated elements in head part
            if pre == head and pre.val == cur.val:
                head = cur.next
                pre = cur.next
            else:
                pre.next = cur.next
        return head
