# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        """
        :type l1: ListNode
        :type l2: ListNode
        :rtype: ListNode
        """
        carry = 0
        dummy_node = ListNode(0)
        current_node = dummy_node
        a = l1
        b = l2
        while ((a != None) or (b != None)):
            if a is None:
                a = ListNode(0)
            if b is None:
                b = ListNode(0)
            sum = (a.val + b.val + carry) % 10
            current_node.next = ListNode(sum)
            carry = (a.val + b.val + carry) // 10
            a = a.next
            b = b.next
            current_node = current_node.next
        if carry == 1:
            current_node.next = ListNode(1)
        return dummy_node.next

