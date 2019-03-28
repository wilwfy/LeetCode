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


####################
# Other's solution 1
####################
class Solution:
    def deleteDuplicates(self, head):
        dummy = pre = ListNode(0)
        dummy.next = head
        while head and head.next:
            if head.val == head.next.val:
                while head and head.next and head.val == head.next.val:
                    head = head.next
                head = head.next
                pre.next = head
            else:
                pre = pre.next
                head = head.next
        return dummy.next


####################
# Other's solution 2
####################
class Solution:
    def deleteDuplicates(self, head):
        """
        :type head: ListNode
        :rtype: ListNode
        """
        
        dummy = ListNode(0);  # construct a dummy node
        dummy.next = head 

        pre = dummy           # set up pre and cur pointers
        cur = head
        while cur:
            if cur.next and cur.val == cur.next.val:
                # loop until cur point to the last duplicates
                while cur and cur.next and cur.val == cur.next.val:
                    cur = cur.next
                pre.next = cur.next  # propose the next for pre
                                     # this will be verified by next line
            else:
                pre = pre.next 
            cur = cur.next
        return dummy.next
