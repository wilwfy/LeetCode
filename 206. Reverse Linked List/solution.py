# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

#######################
# Iterative solution
#######################
class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None:
            return head
        val = []
        while (head):
            val.append(head.val)
            head = head.next
        new_head = ListNode(val[-1])
        pre = new_head
        for i in range(len(val)-2, -1, -1):
            pre.next = ListNode(val[i])
            pre = pre.next
        pre.next = None
        return new_head


#######################
# Recursive solution
#######################
class Solution:
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None:
            return head
        new_h, cur_nd = self.recursiveReverseList(head)
        cur_nd.next = None
        return new_h
    
    def recursiveReverseList(self, node):
        if node.next == None:
            new_head = cur = ListNode(node.val)
            return new_head, cur
        new_head, cur_node = self.recursiveReverseList(node.next)
        cur_node.next = ListNode(node.val)
        return new_head, cur_node.next
