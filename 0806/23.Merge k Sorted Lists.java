
public class MergeKSortedLinkedList {
    // 1. Merge Sort
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            ListNode newHead = null;
            return newHead;
        }
        ListNode head = helper (lists, 0, lists.length - 1);
        return head;
    }
    public ListNode helper (ListNode[] lists, int start, int end) {
        if (start >= end) {
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode l1 = helper(lists, start, mid);
        ListNode l2 = helper(lists, mid + 1, end);
        ListNode head = merge(l1, l2);
        return head;
    }
    public ListNode merge (ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            ListNode newHead = null;
            return newHead;
        }
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            }else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummy.next;
    }





    // 2. Heap

    class MyComparator1 implements Comparator<ListNode>{
        @Override
    public int compare(ListNode o1,ListNode o2) {
        return o1.val - o2.val;
    }
}
    public ListNode mergeKLists2(ListNode[] lists) {
    
        if (lists == null || lists.length == 0) {
            ListNode newHead = null;
            return newHead;
        }

        PriorityQueue<ListNode> heap = new PriorityQueue<>(new MyComparator1());
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                heap.offer(lists[i]);
            }
        }
        while ( !heap.isEmpty()) {
            ListNode node = heap.poll();
            curr.next = node;
            curr = curr.next;
            if (node.next != null) {
                heap.offer(node.next);
            }
        }
        return dummy.next;
    }
}
