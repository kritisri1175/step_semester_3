public class ReverseTheQueue {

    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // reverses the list in place and returns the new head; no new nodes are created
    static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node next = current.next; // remember the rest of the list
            current.next = previous;  // turn this link around
            previous = current;       // move both references one step forward
            current = next;
        }
        return previous;
    }

    static Node build(int... values) {
        Node head = null;
        Node tail = null;
        for (int value : values) {
            Node node = new Node(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }
        return head;
    }

    static void print(Node head) {
        StringBuilder sb = new StringBuilder();
        for (Node current = head; current != null; current = current.next) {
            sb.append(current.data).append(" -> ");
        }
        System.out.println(sb + "null");
    }

    public static void main(String[] args) {
        print(reverse(build(1, 2, 3, 4)));
        print(reverse(build(9)));
        print(reverse(build()));
    }

    /*
     * Complexity (n nodes):
     *   Time  : O(n) - each node is visited once.
     *   Space : O(1) - only three references (previous, current, next) are used.
     */
}
