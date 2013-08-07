package jpamock.instance;

import java.util.ArrayList;
import java.util.List;

public class SmartField {

	private SmartField parent;
	private List<SmartField> children;
	private Class<?> clazz;
	private String path;
	private Object value;

	public SmartField(SmartField parent, Class<?> clazz, String path) {
		this.parent = parent;
		this.clazz = clazz;
//		if (parent != null && parent.getPath() != null) {
//			if (path.contains("[")) {
//				this.path = parent.getPath() + path;
//			} else {
//				this.path = parent.getPath() + "." + path;
//			}
//		} else {
//			this.path = path;
//		}
		this.path = path;
	}

	public void addChild(SmartField smartField) {
		if (children == null)
			children = new ArrayList<SmartField>();
		children.add(smartField);
	}

	public List<SmartField> getChildren() {
		return children;
	}

	public void setChildren(List<SmartField> children) {
		this.children = children;
	}

	public SmartField getParent() {
		return parent;
	}

	public void setParent(SmartField parent) {
		this.parent = parent;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
