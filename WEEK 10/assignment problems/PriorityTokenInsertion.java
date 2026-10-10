public class PriorityTokenInsertion {

    static class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    static class TokenList {

        private Node head;
        private int size;

        // O(1): only the head changes
        void addFirst(int token) {
            Node node = new Node(token);
            node.next = head;
            head = node;
            size++;
        }

        // O(n): walks to the last node (O(1) if a tail reference were kept)
        void addLast(int token) {
            Node node = new Node(token);
            if (head == null) {
                head = node;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = node;
            }
            size++;
        }

        // O(n): walks to the node just before the index
        void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                System.out.println("Invalid index: " + index);
                return;
            }
            if (index == 0) {
                addFirst(token);
                return;
            }
            Node previous = head;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.next;
            }
            Node node = new Node(token);
            node.next = previous.next; // set the new node's next first
            previous.next = node;      // then link the previous node to it
            size++;
        }

        void print() {
            StringBuilder sb = new StringBuilder();
            for (Node current = head; current != null; current = current.next) {
                sb.append(current.data).append(" -> ");
            }
            System.out.println(sb + "null");
        }
    }

    public static void main(String[] args) {
        TokenList list = new TokenList();
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.print();
    }

    /*
     * Complexity (n nodes):
     *   addFirst : O(1)
     *   addLast  : O(n) with only a head reference (O(1) if a tail reference is kept)
     *   insertAt : O(n) - must walk to the node before the index
     */
}
