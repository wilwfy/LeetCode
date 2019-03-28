class MyLinkedList:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.head = None
        self.size = 0

    def get(self, index: int) -> int:
        """
        Get the value of the index-th node in the linked list. If the index is invalid, return -1.
        """
        if (self.size == 0) or (index < 0) or (index > self.size -1):
            return -1
        cur = self.head
        while cur:
            if index == 0:
                return cur.val
            cur = cur.next
            index -= 1
        return -1
        

    def addAtHead(self, val: int) -> None:
        """
        Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
        """
        cur = ListNode(val)
        if self.size != 0:
            cur.next = self.head
        self.head = cur
        self.size += 1
        

    def addAtTail(self, val: int) -> None:
        """
        Append a node of value val to the last element of the linked list.
        """
        cur = self.head
        while cur.next:
            cur = cur.next
        cur.next = ListNode(val)
        self.size += 1
        

    def addAtIndex(self, index: int, val: int) -> None:
        """
        Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
        """
        #if index == 0:
        #    cur = ListNode(val)
        #    if self.head:
        #        cur.next = self.head.next
        #        self.head = cur
        #    else:
        #        self.head = cur
        if (index >= 0) and (index <= self.size):
            dummy = ListNode(None)
            dummy.next = self.head
            pre = dummy
            cur = self.head               
            while cur:
                if index == 0:
                    pre.next = ListNode(val)
                    pre.next.next = cur
                    break
                cur = cur.next
                pre = pre.next
                index -= 1
            if (cur == None) and (index == 0):
                if cur is self.head:
                    self.head = ListNode(val)
                else:
                    pre.next = ListNode(val)
            self.size += 1
        

    def deleteAtIndex(self, index: int) -> None:
        """
        Delete the index-th node in the linked list, if the index is valid.
        """
        if (index >= 0) and (index <= self.size - 1):
            dummy = ListNode(None)
            dummy.next = self.head
            pre = dummy
            cur = self.head
            while cur:
                if index == 0:
                    pre.next = cur.next
                    if cur is self.head:
                        self.head = None
                    break
                cur = cur.next
                pre = pre.next
                index -= 1
            self.size -= 1


# Your MyLinkedList object will be instantiated and called as such:
# obj = MyLinkedList()
# param_1 = obj.get(index)
# obj.addAtHead(val)
# obj.addAtTail(val)
# obj.addAtIndex(index,val)
# obj.deleteAtIndex(index)
