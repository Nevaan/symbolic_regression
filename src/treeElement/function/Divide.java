package treeElement.function;

import treeRepresentation.TreeNode;

public class Divide extends Function{

	public Divide(String data) {
		super(data);
	}

	public double getValue(double xValue){
		return ((TreeNode) this.children.get(0)).getValue(xValue) / ((TreeNode)this.children.get(1)).getValue(xValue);
	}

}
