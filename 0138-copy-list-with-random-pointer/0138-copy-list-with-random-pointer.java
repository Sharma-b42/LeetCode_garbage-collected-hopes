/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> copies = new HashMap<>();

        for (Node curr = head; curr != null; curr = curr.next) {
            copies.put(curr, new Node(curr.val));
        }

        for (Node curr = head; curr != null; curr = curr.next) {
            Node copy = copies.get(curr);
            copy.next = copies.get(curr.next);
            copy.random = copies.get(curr.random);
        }

        return copies.get(head);
    }
}