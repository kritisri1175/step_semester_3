public class CancelledOrdersCleanup {

    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // removes every node holding the given code and returns the (possibly new) head
    static Node removeAll(Node head, int code) {
        // first, drop matching nodes from the front by moving the head forward
        while (head != null && head.data == code) {
            head = head.next;
        }

        // then unlink matching nodes in the rest of the list
        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next; // skip it, do not move forward yet
            } else {
                current = current.next;
            }
        }
        return head;
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
        print(removeAll(build(5, 3, 5, 8, 5), 5));
        print(removeAll(build(5, 5, 5), 5));
        print(removeAll(build(), 5));
    }

    /*
     * Complexity (n nodes):
     *   Time  : O(n) - the list is walked once.
     *   Space : O(1) - the nodes are unlinked in place.
     */
}
