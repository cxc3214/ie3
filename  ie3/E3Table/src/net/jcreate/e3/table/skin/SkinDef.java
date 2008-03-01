package net.jcreate.e3.table.skin;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class SkinDef {
	private String name;
	private String skinHome;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSkinHome() {
		return skinHome;
	}
	public void setSkinHome(String skinHome) {
		this.skinHome = skinHome;
	}
	
	public String toString(){
		return ReflectionToStringBuilder.reflectionToString(this);
	}
	public boolean equals(Object obj) {
		if ( obj instanceof SkinDef == false ){
			return false;
		}
		 return org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals(this.name, ((SkinDef)obj).name);
	}
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this.name);
	}
	
	

}
