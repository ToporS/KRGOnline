package com.hk.deader.domains;

// Generated 17.10.2012 8:37:53 by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * KeyhistoryId generated by hbm2java
 */
@Embeddable
public class KeyhistoryId implements java.io.Serializable {

	private static final long serialVersionUID = -5930180046284020301L;
	private Date dat;
	private String numkey;

	public KeyhistoryId() {
	}

	public KeyhistoryId(Date dat, String numkey) {
		this.dat = dat;
		this.numkey = numkey;
	}

	@Column(name = "DAT", nullable = false, length = 19)
	public Date getDat() {
		return this.dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}

	@Column(name = "NUMKEY", nullable = false, length = 6)
	public String getNumkey() {
		return this.numkey;
	}

	public void setNumkey(String numkey) {
		this.numkey = numkey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof KeyhistoryId))
			return false;
		KeyhistoryId castOther = (KeyhistoryId) other;

		return ((this.getDat() == castOther.getDat()) || (this.getDat() != null
				&& castOther.getDat() != null && this.getDat().equals(
				castOther.getDat())))
				&& ((this.getNumkey() == castOther.getNumkey()) || (this
						.getNumkey() != null && castOther.getNumkey() != null && this
						.getNumkey().equals(castOther.getNumkey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDat() == null ? 0 : this.getDat().hashCode());
		result = 37 * result
				+ (getNumkey() == null ? 0 : this.getNumkey().hashCode());
		return result;
	}

}
