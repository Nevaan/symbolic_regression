package treeRepresentation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import regression.Parameters;
import treeElement.function.Add;
import treeElement.function.Divide;
import treeElement.function.Multiply;
import treeElement.function.Substract;
import treeElement.terminal.Constant;
import treeElement.terminal.Variable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class TreeNode implements Iterable<TreeNode> {

	@XmlAttribute
	protected int id;
	@XmlElement
	protected String type;
	@XmlElement
	protected TreeNode parent;
	@XmlElement
	public List<TreeNode> children;
	@XmlElement
	protected int childAmount;

	public boolean isRoot() {
		return parent == null;
	}

	public boolean isLeaf() {
		return children.size() == 0;
	}

	private List<TreeNode> elementsIndex;

	public TreeNode() {
		this.id = Parameters.IDENTIFIER++;
		this.children = new LinkedList<TreeNode>();
		this.elementsIndex = new LinkedList<TreeNode>();
		this.elementsIndex.add(this);
	}

	public TreeNode addChild(TreeNode child) {
		TreeNode childNode = child;
		childNode.parent = this;
		this.children.add(childNode);
		this.registerChildForSearch(childNode);
		return childNode;
	}

	public int getLevel() {
		if (this.isRoot())
			return 0;
		else
			return parent.getLevel() + 1;
	}

	public int getChildAmount() {
		return childAmount;
	}

	public void setChildAmount(int childAmount) {
		this.childAmount = childAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getValue(double xValue) {
		return 0;
	}

	private void registerChildForSearch(TreeNode node) {
		elementsIndex.add(node);
		if (parent != null)
			parent.registerChildForSearch(node);
	}

	public TreeNode findTreeNode(Comparable<Integer> cmp) {
		for (TreeNode element : this.elementsIndex) {
			int elementId = element.id;
			if (cmp.compareTo(elementId) == 0)
				return element;
		}

		return null;
	}

	@Override
	public String toString() {
		return new String(type + " " + id);
	}

	@Override
	public Iterator<TreeNode> iterator() {
		TreeNodeIter iter = new TreeNodeIter(this);
		return iter;
	}

	private TreeNode selectChildType(String type) {
		switch (type) {
		case "+":
			return new Add();
		case "/":
			return new Divide();
		case "*":
			return new Multiply();
		case "-":
			return new Substract();
		case "x":
			return new Variable();
		default:
			return new Constant();
		}
	}

}