package net.jcreate.e3.tree.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import net.jcreate.e3.tree.BuildTreeException;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.NodeVisitor;
import net.jcreate.e3.tree.TreeBuilder;
import net.jcreate.e3.tree.TreeDirector;
import net.jcreate.e3.tree.TreeModel;


/**
 * 负责节点遍历,采用先序遍历算法
 * @author new
 * @todo: 添加节点过滤器和排序器
 */
public class DefaultTreeDirector implements TreeDirector {
    private TreeBuilder treeBuilder = null;
    private Comparator comparator = null;
    private NodeVisitor nodeVisitor = new EmptyNodeVisitor();
    /**
     * 节点访问者
     * @param pVisitor
     */
    public void setNodeVisitor(NodeVisitor pVisitor){
    	if ( pVisitor != null ){
    	  nodeVisitor = pVisitor;
    	}
    }
    
   
    //节点被过滤过
    //节点排序过
    //TODO: 测试各种情况
    private static class FilterSortTreeModel implements TreeModel{
    	
        private DefaultTreeModel treeModel = new DefaultTreeModel(); 
        private NodeVisitor nodeVisitor;
        private Comparator comparator;
    	public FilterSortTreeModel(TreeModel pTreeModel, NodeVisitor pNodeVisitor, Comparator pComparator){
    		//1:过滤
    		this.nodeVisitor = pNodeVisitor;
    		this.comparator = pComparator;
    		Iterator rootNodes = pTreeModel.getRootNodes();
    		List tempRootNodes = new ArrayList();
    		while( rootNodes.hasNext() ){
    			Node rootNode = (Node)rootNodes.next();
    			if ( nodeVisitor.visit(rootNode) == false){//已被过滤
    				continue;
    			}
    			tempRootNodes.add(rootNode);
    			filter(rootNode, asList(rootNode.getChildren()) );    			
    		}
    		
//    		//排序节点
    		if ( this.comparator != null ){
    			Collections.sort(tempRootNodes, this.comparator);
    		}
    		for(int i=0; i<tempRootNodes.size(); i++){
    			Node rootNode = (Node)tempRootNodes.get(i);
    			treeModel.addRootNode(rootNode);
        		if ( this.comparator != null ){
        			sort(rootNode, asList(rootNode.getChildren()));
        		}
    			
    		}
    	}
    	 private List asList(Iterator pIterator){
    	    	List result = new ArrayList();
    	    	if ( pIterator == null ){
    	    		return result;
    	    	}
    	    	while( pIterator.hasNext() ){
    	    		Object obj = pIterator.next();
    	    		result.add(obj);
    	    	}
    	    	return result;
    	    }
    	private void sort(Node pParentNode, List sortedChildren){
    		if ( sortedChildren == null ){
    			return;
    		}
    		if ( sortedChildren.isEmpty() ){
    			return;
    		}
			Collections.sort(sortedChildren, this.comparator);
			//先删除
		    for(int i=0; i<sortedChildren.size(); i++){
		    	Node sortedNode = (Node)sortedChildren.get(i);
		    	pParentNode.detachNode(sortedNode);
		    }
		    //重新建设父子关系
		    for(int i=0; i<sortedChildren.size(); i++){
		    	Node sortedNode = (Node)sortedChildren.get(i);
		    	pParentNode.addNode(sortedNode);
		    	sort(sortedNode, asList(sortedNode.getChildren()));
		    }
    	}
    	
    	private void filter(Node pParentNode, List pChildrenNodes){
    		if ( pChildrenNodes == null ){
    			return;
    		}
    		if ( pChildrenNodes.isEmpty() ){
    			return;
    		}
    		for(int i=0; i<pChildrenNodes.size(); i++){
    			Node childNode = (Node)pChildrenNodes.get(i);
    			if ( nodeVisitor.visit(childNode) == false ){//已被过滤
    				pParentNode.detachNode(childNode);
    				continue;
    			}
    			filter(childNode, asList(childNode.getChildren()) );
    			
    		}
    	}
		public Iterator getRootNodes() {
			return treeModel.getRootNodes();
		}
    	
    }
	public void build(TreeModel pTree,  TreeBuilder pTreeBuilder)
			throws BuildTreeException {
		if ( pTreeBuilder == null ){
			throw new BuildTreeException("TreeBuilder为空null");
		}
		this.treeBuilder = pTreeBuilder;
		Iterator rootNodes = new FilterSortTreeModel(pTree, nodeVisitor, this.comparator).getRootNodes();
		if ( rootNodes == null ){
			throw new BuildTreeException("没有根节点点");
		}
		treeBuilder.buildTreeStart();
		int rootRow = 0;
		final int rootLevel = 0;
		while ( rootNodes.hasNext() ){
			Node rootNode = (Node)rootNodes.next();
		pTreeBuilder.buildRootNodeStart(rootNode, rootLevel, rootRow );
		rootRow++;
		  Iterator i = rootNode.getChildren();
		  int level = 1;//第2级
		  int row = 0;
		  while( i.hasNext() ){
			  Node node = (Node)i.next();
			  buildNode(node, rootNode, level, row);
			  row++;
		  }
		  pTreeBuilder.buildRootNodeEnd(rootNode, rootLevel, rootRow);
		}
		treeBuilder.buildTreeEnd();
	}
	private void buildNode(Node pNode, Node pParentNode, int pLevel, int pRow )throws BuildTreeException{
		Iterator i = pNode.getChildren();
		treeBuilder.buildNodeStart(pNode, pParentNode, pLevel, pRow);
		
		int row = 0;
		pLevel++;		
		while( i.hasNext() ){
			Node node = (Node)i.next();
			buildNode(node, pNode, pLevel, row);
			row++;
		}
		treeBuilder.buildNodeEnd(pNode, pParentNode, pLevel, pRow);
	}
	public void setComparator(Comparator pComparator) {
	  this.comparator = pComparator;
	}
 public static void main(String[] args){
	 List a = new ArrayList();
	 String b="a";
	 String c="a";
	 a.add(b);
	 a.add(c);
	 a.remove("a");
	 System.out.println(a.size());
 }
}
