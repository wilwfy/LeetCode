# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        if (l1 == None) and (l2 == None):
            return None
        elif l1 == None:
            return l2
        elif l2 == None:
            return l1
        
        dummy_node = ListNode(0)
        current_node = dummy_node
        cnt = 0
        while ((l1 != None) and (l2 != None)):
            if l1.val <= l2.val:
                current_node.next = l1
                l1 = l1.next
            else:
                current_node.next = l2
                l2 = l2.next
            current_node = current_node.next
            #print(current_node.val)
        if l1 != None:
            current_node.next = l1
        else:
            current_node.next = l2
        return dummy_node.next


####################
# Other's solution
####################
class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        dummy = cur = ListNode(0)
        while l1 and l2:
            if l1.val < l2.val:
                cur.next = l1
                l1 = l1.next
            else:
                cur.next = l2
                l2 = l2.next
            cur = cur.next
        cur.next = l1 or l2
        return dummy.next
