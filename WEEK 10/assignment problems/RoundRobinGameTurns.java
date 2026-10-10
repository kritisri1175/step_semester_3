public class RoundRobinGameTurns {

    static class Node {

        String name;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    // circular singly linked list that keeps only a tail reference (head is tail.next)
    static class PlayerCircle {

        private Node tail;

        // O(1): link the new node between the tail and the head
        void addLast(String name) {
            Node node = new Node(name);
            if (tail == null) {
                node.next = node; // a single node points to itself
            } else {
                node.next = tail.next;
                tail.next = node;
            }
            tail = node;
        }

        // O(turns): keep moving with next, the list wraps around by itself
        void printTurns(int turns) {
            if (tail == null) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            Node current = tail.next; // the head
            for (int i = 0; i < turns; i++) {
                if (i > 0) {
                    sb.append(' ');
                }
                sb.append(current.name);
                current = current.next;
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        PlayerCircle circle = new PlayerCircle();
        circle.addLast("Asha");
        circle.addLast("Ravi");
        circle.addLast("Neha");

        circle.printTurns(7);
    }

    /*
     * Complexity:
     *   addLast    : O(1) - the tail reference gives direct access to both ends.
     *   printTurns : O(t) for t turns - one step per turn, no matter how many players there are.
     */
}
