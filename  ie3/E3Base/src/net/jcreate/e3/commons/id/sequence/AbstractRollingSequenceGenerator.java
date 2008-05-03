package net.jcreate.e3.commons.id.sequence;

import net.jcreate.e3.commons.id.CreateSequnceException;

public abstract class AbstractRollingSequenceGenerator extends DefaultSequenceGenerator{
	
	public long next() throws CreateSequnceException {
		if ( isResetCount() ){
			this.currCount = this.minValue;
			maxCount = this.currCount;
			sequenceStorer.save(maxCount, this.getId());
		}
		return super.next();
	}
	
   abstract protected boolean isResetCount();

}
