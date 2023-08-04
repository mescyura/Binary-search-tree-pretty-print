package com.epam.rd.autocode.bstprettyprint;

public class PrettyPrintableTree implements PrintableTree {
    private TreeNode root;

    @Override
    public void add(int i) {
        if (root == null)
            this.root = new TreeNode(i);
        else
            root.insert(i);
    }

    @Override
    public String prettyPrint() {
        return prettyString();
    }

    private String prettyString() {
        StringBuilder treeSB = new StringBuilder();
        prettyString(root, new StringBuilder("\n"), treeSB);
        treeSB.append("\n");
        return treeSB.substring(1);
    }

    private void prettyString(TreeNode node, StringBuilder lineSB, StringBuilder treeSB) {
        if (node == null)
            return;

        int dataSize = node.getSize();
        int depth = lineSB.length();
        int i = "\n │".indexOf(lineSB.charAt(depth - 1));
        int j = (node.leftChild != null ? 2 : 0) + (node.rightChild != null ? 1 : 0);

        lineSB.append(" ".repeat(dataSize + 1));
        prettyString(node.leftChild, lineSB, treeSB);
        lineSB.setLength(depth - 1);

        treeSB.append(lineSB);
        treeSB.append("\n┌└".charAt(i));
        treeSB.append(node.data);
        treeSB.append(" ┐┘┤".charAt(j));

        lineSB.append("\n│ ".charAt(i));
        lineSB.append(" ".repeat(dataSize));
        lineSB.append(" │ │".charAt(j));
        prettyString(node.rightChild, lineSB, treeSB);

        if (node.leftChild == null && node.rightChild == null) {
            treeSB.delete(treeSB.length() - 1, treeSB.length());
        }
    }


    private static class TreeNode {
        private final int data;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(Integer data) {
            this.data = data;
        }

        private int getSize() {
            return Integer.toString(data).length();
        }

        private void insert(int data) {
            if (data > this.data) {
                if (this.rightChild == null)
                    this.rightChild = new TreeNode(data);
                else
                    this.rightChild.insert(data);
            } else if (data < this.data) {
                if (this.leftChild == null)
                    this.leftChild = new TreeNode(data);
                else
                    this.leftChild.insert(data);
            }
        }
    }
}