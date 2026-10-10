public class TrainCoachesBothWays {

    static class Node {

        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    static class Train {

        private Node head;
        private Node tail;

        // O(1): the tail reference gives direct access to the end
        void addLast(String name) {
            Node node = new Node(name);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.prev = tail;
                tail.next = node;
                tail = node;
            }
        }

        // O(n) to find the coach, O(1) to unlink it
        boolean remove(String name) {
            Node current = head;
            while (current != null && !current.name.equals(name)) {
                current = current.next;
            }
            if (current == null) {
                return false;
            }

            // fix both neighbours (and head/tail when the end coach is removed)
            if (current.prev != null) {
                current.prev.next = current.next;
            } else {
                head = current.next;
            }
            if (current.next != null) {
                current.next.prev = current.prev;
            } else {
                tail = current.prev;
            }
            return true;
        }

        void printForward() {
            StringBuilder sb = new StringBuilder("Forward: ");
            for (Node current = head; current != null; current = current.next) {
                sb.append(current.name);
                if (current.next != null) {
                    sb.append(" <-> ");
                }
            }
            System.out.println(sb);
        }

        void printBackward() {
            StringBuilder sb = new StringBuilder("Backward: ");
            for (Node current = tail; current != null; current = current.prev) {
                sb.append(current.name);
                if (current.prev != null) {
                    sb.append(" <-> ");
                }
            }
            System.out.println(sb);
        }
    }

    public static void main(String[] args) {
        Train train = new Train();
        for (String coach : new String[]{"Engine", "A1", "B1", "B2", "Guard"}) {
            train.addLast(coach);
        }

        if (!train.remove("B1")) {
            System.out.println("Coach not found");
        }
        train.printForward();
        train.printBackward();
    }

    /*
     * Complexity (n coaches):
     *   Finding the coach by name : O(n) - walk from the head until the name matches.
     *   Unlinking it once found   : O(1) - only the two neighbours' references change.
     */
}
