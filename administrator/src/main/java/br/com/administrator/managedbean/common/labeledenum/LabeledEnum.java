package br.com.administrator.managedbean.common.labeledenum;

public final class LabeledEnum<T> {

	private T value;
	private String label;

	public LabeledEnum() {
	}
	
	public LabeledEnum(T value, String label) {
		this.value = value;
		this.label = label;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
