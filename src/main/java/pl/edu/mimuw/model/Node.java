package pl.edu.mimuw.model;

public class Node {
  private String value;
  private Node left;
  private Node right;

  public Node(String value) {
    this.value = value;
    left = null;
    right = null;
  }

  public void buildLeft(String value) {
    left = new Node(value);
  }

  public void buildRight(String value) {
    right = new Node(value);
  }

}
