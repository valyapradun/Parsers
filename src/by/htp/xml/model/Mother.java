package by.htp.xml.model;

public class Mother extends Parent{
	  private String maideName;
	  private int children;
	  
	  public Mother(String maideName, int children) {
		super();
		this.maideName = maideName;
		this.children = children;
	}
	  public Mother() {
	  	super();
	  }
	  public String getMaideName() {
	  	return maideName;
	  }
	  public void setMaideName(String maideName) {
	  	this.maideName = maideName;
	  }
	  
	  
	  public int getChildren() {
		return children;
	}
	  public void setChildren(int children) {
		this.children = children;
	}
	
	  @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + children;
		result = prime * result + ((maideName == null) ? 0 : maideName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mother other = (Mother) obj;
		if (children != other.children)
			return false;
		if (maideName == null) {
			if (other.maideName != null)
				return false;
		} else if (!maideName.equals(other.maideName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString() + " maideName = " + maideName + " children = " + children;
	}
	

}
