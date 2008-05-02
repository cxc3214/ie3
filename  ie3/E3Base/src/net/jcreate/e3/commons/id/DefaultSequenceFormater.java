package net.jcreate.e3.commons.id;

import java.text.DecimalFormat;

public class DefaultSequenceFormater implements SequenceFormater {

	private String pattern;
	
	public String format(long pSequence) throws FormatSequenceExcepiton {
        DecimalFormat  df = new DecimalFormat(pattern);
        return df.format(pSequence);
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}
