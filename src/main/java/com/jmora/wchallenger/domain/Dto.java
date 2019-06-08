package com.jmora.wchallenger.domain;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @category Domain
 * @author joseluismoravilladiego
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3678064294776543194L;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
