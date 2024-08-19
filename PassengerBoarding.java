import java.util.Arrays;

public class PassengerBoarding {
    
    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        // Example 1
        System.out.println("Example 1:");
        int[] array1 = {1, 2, 3, 4, 5};
        int k1 = 2;
        ListNode head1 = arrayToList(array1);
        ListNode optimizedHead1 = optimizeBoarding(head1, k1);
        int[] optimizedArray1 = listToArray(optimizedHead1);
        System.out.println("Optimized Sequence: " + Arrays.toString(optimizedArray1));
        
        // Example 2
        System.out.println("Example 2:");
        int[] array2 = {1, 2, 3, 4, 5};
        int k2 = 3;
        ListNode head2 = arrayToList(array2);
        ListNode optimizedHead2 = optimizeBoarding(head2, k2);
        int[] optimizedArray2 = listToArray(optimizedHead2);
        System.out.println("Optimized Sequence: " + Arrays.toString(optimizedArray2));
    }

    // Function to reverse nodes in chunks of size k
    public static ListNode optimizeBoarding(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode curr = head;

        // Count the number of nodes in the linked list
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        curr = dummy.next;
        while (count >= k) {
            ListNode tail = curr;
            ListNode prev = null;
            ListNode temp = null;
            
            // Reverse k nodes
            for (int i = 0; i < k; i++) {
                temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Connect with the previous part
            pre.next = prev;
            tail.next = curr;
            pre = tail;

            count -= k;
        }

        return dummy.next;
    }

    // Helper method to convert an array to a linked list
    private static ListNode arrayToList(int[] array) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int num : array) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    // Helper method to convert a linked list to an array
    private static int[] listToArray(ListNode head) {
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        
        int[] array = new int[size];
        temp = head;
        int index = 0;
        while (temp != null) {
            array[index++] = temp.val;
            temp = temp.next;
        }
        return array;
    }
}
