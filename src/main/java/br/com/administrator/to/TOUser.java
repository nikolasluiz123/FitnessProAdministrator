package br.com.administrator.to;

import br.com.administrator.managedbean.common.labeledenum.LabeledEnum;
import br.com.administrator.to.common.AbstractAuditableDTO;
import br.com.fitnesspro.shared.communication.enums.general.EnumUserType;

public class TOUser extends AbstractAuditableDTO {

	private static final long serialVersionUID = 1L;

	private String email;
	private String password;
	private LabeledEnum<EnumUserType> type;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LabeledEnum<EnumUserType> getType() {
		return type;
	}

	public void setType(LabeledEnum<EnumUserType> type) {
		this.type = type;
	}

}
