package kw.test.tree;

public class TreeNode {
	private Node root;
	public TreeNode() {
		root = new Node();
		root.setData(0);
	}
	public void insert(Node value) {
		//�ҳ����һ��
		if(value.getData()>root.getData()) {
			if(root.getRight()==null) {
				root.setRight(value);
			}else {
				this.insert(value);
			}
		}
	}
}
