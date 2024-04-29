public class Node {
    private Card data;
    private Node parent;
    private Node left;
    private Node right;

    public Node(Card data) {
        this.data = data;
    }

    public void removeNode() {
        if (this.parent == null ) {
            //root node
            this.right = null;
            // remove right child
            if (this.left != null) {
                if (this.left.right != null) {
                    // if the left child has a right child,
                    // give root node that right child
                    this.right = this.left.right;
                    this.right.parent = this;
                }
                this.setData(this.left.getData());
                // replace root data with left child data
                if (this.left.left != null) {
                    // if old left child (now a copy) has a child,
                    // then reassign its child to new root
                    this.left = this.left.left;
                    this.left.parent = this;
                } else {
                    // remove the (now copied to root) left child
                    this.left = null;
                }
            }
        } else if (this.left != null) {
            // if not a root node and has a Child,
            // reroute around node

            this.left.parent = this.parent;
            this.parent.left = this.left;
        }
        else {
            // remove left leaf node
            this.parent.left = null;
        }
    }

    public void addNode(Node node) {
        if (node.data.getIsFaceCard()) {
            if (this.right == null) {
                this.right = node;
                node.parent = this;
            } else {
                this.right.addNode(node);
            }
        } else if (this.left == null) {
            this.left = node;
            node.parent = this;
        } else {
            this.left.addNode(node);
        }
    }

    public static Node findNodeByCard(Node root, Card card) {
        if (card == null) {return null;}
        if (root != null) {
            if (root.data.toString().equalsIgnoreCase(card.toString())) {
                return root;
            } else {
                Node foundNode = findNodeByCard(root.left, card);
                if (foundNode == null) {
                    foundNode = findNodeByCard(root.right, card);
                }
                return foundNode;
            }
        } else {
            return null;
        }
    }

    public static int calculateBid(Node root) {
        int sum = 0;
        if (root == null) {return 0;}

        sum += calculateBid(root.left);

        if (root.data.getRank() == Ranks.KING) {
            sum += root.parent.data.getValue();
        } else if (root.data.getRank() != Ranks.QUEEN) {
            sum += root.data.getValue();
        }

        sum += calculateBid(root.right);
        return sum;
    }

    public static String printTree(Node root) {
        StringBuilder stringBuilder = new StringBuilder();

        if (root == null) {return stringBuilder.toString();}
        stringBuilder.append(root.data);

        if (root.right != null){stringBuilder.append(" -- ");}
        stringBuilder.append(printTree(root.right));

        if (root.left != null){stringBuilder.append("\n    |\n");}
        stringBuilder.append(printTree(root.left));

        return stringBuilder.toString();
    }

    public static Card getTopCard(Node root) {
        if (root.left == null) {
            return root.data;
        }
        return (getTopCard(root.left));
    }

    public Node getParent() {
        return parent;
    }

    public Node getLeft() {
        return left;
    }

    public Card getData() {
        return data;
    }

    public void setData(Card data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
