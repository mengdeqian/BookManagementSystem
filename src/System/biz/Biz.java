package System.biz;

import java.io.Serializable;
import java.util.Map;

/**
 * 所有业务类的父接口
 * @author mdq
 * @data 2019年9月5日 下午8:09:27
 *
 */
public interface Biz<T> extends Serializable {
	/** 通用的添加方法 */
	public boolean add(T t);
	/** 通用的删除方法 */
	public boolean del(T t);
	
	public T update(T t);
	
	public T fingById(String id);
	
	public Map<String, T> findAll();
	
}
