package net.jcreate.e3.tree.swing;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import net.jcreate.e3.tree.BuildTreeException;
import net.jcreate.e3.tree.Node;
import net.jcreate.e3.tree.support.TreeBuilderSupport;

public class SwingTreeBuilder extends TreeBuilderSupport{

	private SwingTree swingTree;
	private TreeCellRenderer cellRenderer;
	/**
	 * 开始构造树
	 * @throws BuildTreeException
	 */
	public void buildTreeStart() throws BuildTreeException{
		this.swingTree = createTree();
		this.cellRenderer = createCellRenderer();
		swingTree.setCellRenderer(cellRenderer);
	    
	    //设置树侦听
		TreeSelectionListener listener=new TreeSelectionListener() {
	      public void valueChanged(TreeSelectionEvent e) {
	    	  valueChanged(e);	        
	      }
	    };

	    swingTree.addTreeSelectionListener(listener);
		
	}
	
	protected SwingTree createTree(){
		return new SwingTree();
	}
	protected TreeCellRenderer createCellRenderer(){
		return null;
	}
	protected SwingTree createTreeModel(){
		return new SwingTree();
	}
	
	
	
	private  //为子节点设置选择属性
	   void valueChanged(TreeSelectionEvent e){
//	    //如果是根节点，则退出
//	    int selRows[]=swingTree.getSelectionRows();
//	    if(selRows==null||selRows.length==0){
//	      return;
//	    }
//	    if ( swingTree.getSelectionPath().getLastPathComponent() instanceof CheckNode == false ){
//	    	return;
//	    }
//	    //获得选择的对象
//	    node = (CheckNode) this.getSelectionPath().getLastPathComponent();
//	    node.onClick();
//	    
//	    this.removeTreeSelectionListener(listener);
//	    this.clearSelection();
//	    DefaultTreeModel defaultModel = (DefaultTreeModel)this.getModel();
//	    defaultModel.nodeChanged(this.getRoot());
//	    this.addTreeSelectionListener(listener);
	  }
	
	public SwingTree getTree(){
		return swingTree; 
	}
	
	public void buildNodeStart(Node pNode, Node pParentNode, int pLevel, int pRow) throws BuildTreeException {
	}

	public void buildRootNodeStart(Node pRootNode, int pLevel, int pRow) throws BuildTreeException {
	}

}
