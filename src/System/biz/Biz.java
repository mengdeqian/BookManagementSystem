package System.biz;

import java.io.Serializable;
import java.util.Map;

/**
 * ����ҵ����ĸ��ӿ�
 * @author mdq
 * @data 2019��9��5�� ����8:09:27
 *
 */
public interface Biz<T> extends Serializable {
	/** ͨ�õ���ӷ��� */
	public boolean add(T t);
	/** ͨ�õ�ɾ������ */
	public boolean del(T t);
	
	public T update(T t);
	
	public T fingById(String id);
	
	public Map<String, T> findAll();
	
}
