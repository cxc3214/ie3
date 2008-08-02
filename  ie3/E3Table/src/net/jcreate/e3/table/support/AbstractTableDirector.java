package net.jcreate.e3.table.support;

import net.jcreate.e3.table.TableContext;
import net.jcreate.e3.table.TableContextSupport;
import net.jcreate.e3.table.TableDirector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractTableDirector  implements TableDirector {

    protected final Log logger = LogFactory.getLog( DefaultTableDirector.class );
    protected boolean showCaption = true;	
    protected boolean showHeader = true;
    protected boolean showTopPanel = true;	
    protected boolean showTopToolbar = true;
    protected boolean showBottomToolbar = true;
    protected boolean showBody = true;	
    protected boolean showBottomPanel = true;
	
	protected final TableContext tableContext;
	
	public AbstractTableDirector(){
		logger.debug("DefaultTableDirector未设置WebContext");
		tableContext = null;
	}
	
	public AbstractTableDirector(TableContext pTableContext){
		tableContext = pTableContext;
	}

	protected void setObjectWebContext(Object pObj){
	      if ( pObj instanceof TableContextSupport ){
       	   TableContextSupport contextSupport = (TableContextSupport)pObj;
       	   if ( contextSupport.getTableContext() == null ){//未设置
       		   contextSupport.setTableContext(this.tableContext);
       	   }
          }
		
	}
	
	   
	public boolean isShowBody() {
		return showBody;
	}
	public void setShowBody(boolean showBody) {
		this.showBody = showBody;
	}
	public boolean isShowBottomPanel() {
		return showBottomPanel;
	}
	public void setShowBottomPanel(boolean showBottomPanel) {
		this.showBottomPanel = showBottomPanel;
	}
	public boolean isShowCaption() {
		return showCaption;
	}
	public void setShowCaption(boolean showCaption) {
		this.showCaption = showCaption;
	}
	public boolean isShowHeader() {
		return showHeader;
	}
	public void setShowHeader(boolean showHeader) {
		this.showHeader = showHeader;
	}
	public boolean isShowTopPanel() {
		return showTopPanel;
	}
	public void setShowTopPanel(boolean showTopPanel) {
		this.showTopPanel = showTopPanel;
	}

	public boolean isShowBottomToolbar() {
		return showBottomToolbar;
	}

	public void setShowBottomToolbar(boolean showBottomToolbar) {
		this.showBottomToolbar = showBottomToolbar;
	}

	public boolean isShowTopToolbar() {
		return showTopToolbar;
	}

	public void setShowTopToolbar(boolean showTopToolbar) {
		this.showTopToolbar = showTopToolbar;
	}
	
}
