package net.jcreate.e3.tree.swing;

import javax.swing.Icon;

import net.jcreate.e3.tree.support.DefaultNode;

public class SwingTreeNode extends DefaultNode {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 是否被选种
	 */
	private boolean selected = false;
	/**
	 * 是否被禁用
	 */
	private boolean disabled = false;
	
	/**
	 * 普通图标
	 */
	private Icon icon;
	
	/**
	 * 展开时图标
	 */
	private Icon openIcon;
	
	/**
	 * todo:
	 * 1:键盘，右键菜单，单击，双击的监听器
	 */

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Icon getOpenIcon() {
		return openIcon;
	}

	public void setOpenIcon(Icon openIcon) {
		this.openIcon = openIcon;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}


}
