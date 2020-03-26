/*
 * Solution of Brute force
 *
 * Time complexity : O(NlogN) where N is the total number of nodes.
 *                   Collecting all the values costs O(N) time.
 *                   A stable sorting algorithm costs O(NlogN) time.
 *                   Iterating for creating the linked list costs O(N) time.
 * Space complexity : O(N).
 *                    Sorting cost O(N) space (depends on the algorithm you choose).
 *                    Creating a new linked list costs O(N) space.
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        
        // Brute force
        List<Integer> values = new ArrayList<>();
        for (ListNode list: lists) {
            while (list != null) {
                values.add(list.val);
                list = list.next;
            }
        }
        
        Collections.sort(values);
        
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        for (int value: values) {
            ListNode node = new ListNode(value);
            head.next = node;
            head = node;
        }
        head.next = null;
        return dummy.next;
    }
}


/*
 * Other's solution of Priority Queue
 *
 * Time complexity : O(Nlogk) where k is the number of linked lists.
 *                   The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue. But finding the node
 *                   with the smallest value just costs O(1) time.
 *                   There are N nodes in the final linked list.
 * Space complexity : O(1). In-place method which cost O(1) space. And the priority queue (often
 *                    implemented with heaps) costs O(k) space (it's far less than N in most situations).
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if ((lists == null) || (lists.length == 0)) return null;
        if (lists.length == 1) return lists[0];
        
        // PriorityQueue
        PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val == o2.val) return 0;
                else return 1;
            }
        });
        for (ListNode list: lists) {
            if (list != null)
                pq.offer(list);
        }
        
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (!pq.isEmpty()) {
            head.next = pq.poll();
            head = head.next;
            if (head.next != null)
                pq.offer(head.next);
        }
        head.next = null;
        return dummy.next;
    }
}


/*
 * Other's solution of Merge Sort with Divide And Conquer
 *
 * This approach walks alongside the one above but is improved a lot. We don't need to traverse most nodes many times repeatedly
 *   -  Pair up k lists and merge each pair.
 *   -  After the first pairing, \text{k}k lists are merged into k/2k/2 lists with average 2N/k length, then k/4, k/8 and so on.
 *   -  Repeat this procedure until we get the final sorted linked list.
 * Thus, we'll traverse almost NN nodes per pairing and merging, and repeat this procedure about log2(k) times.
 *
 * Time complexity : O(Nlogk) where k is the number of linked lists.
 *                   We can merge two sorted linked list in O(n) time where n is the total number of nodes in two lists.
 *                   Sum up the merge process and we can get: O(Nlogk)
 * Space complexity : O(1).
 *                    We can merge two sorted linked lists in O(1) space.
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode h = new ListNode(0);
        ListNode ans=h;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                h.next = l1;
                h = h.next;
                l1 = l1.next;
            } else {
                h.next = l2;
                h = h.next;
                l2 = l2.next;
            }
        }
        if(l1==null){
            h.next=l2;
        }
        if(l2==null){
            h.next=l1;
        } 
        return ans.next;
    }
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){
            return null;
        }
        int interval = 1;
        while(interval<lists.length){
            System.out.println(lists.length);
            for (int i = 0; i + interval< lists.length; i=i+interval*2) {
                lists[i]=mergeTwoLists(lists[i],lists[i+interval]);            
            }
            interval*=2;
        }
    
        return lists[0];
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
