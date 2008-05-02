package net.jcreate.e3.commons.id;

/**
 * @author 黄云辉
 *
 */
public interface SequenceStorer {
	
	
	/**
	 * 保存序号
	 * @param pSequence 序号
	 * @param pSequenceID 序号ID
	 * @throws StoreSequenceException
	 */
  public void save(long pSequence, final String pSequenceID) throws StoreSequenceException;
  
  /**
   * 
   * @param pSequenceID 序号ID
   * @return
   * @throws StoreSequenceException
   */
  public long load(final String pSequenceID) throws StoreSequenceException;
}
