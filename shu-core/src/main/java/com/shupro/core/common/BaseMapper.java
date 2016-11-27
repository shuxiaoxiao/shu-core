package com.shupro.core.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 需要使用的id为Serializable
 * @author shu
 *
 * @param <T>
 */
public interface BaseMapper<T> {
	
	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	int deleteById(Serializable id);

	/**
	 * 新增
	 * @param record
	 * @return
	 */
	int insert(T record);

//	int insertSelective(T record);
	
	/**
	 * 动态修改
	 * @param record
	 * @return
	 */
	int updateSelective(T record);
	
	/**
	 * 修改
	 * @param record
	 * @return
	 */
	int update(T record);

	/**
	 * 通过主键id 单条查询
	 * @param id
	 * @return
	 */
	T selectById(Serializable id);
	
	/**
	 * 分页查询所有(条件查询也能使用这个,不过为了解决翻译列表时的麻烦)
	 * @param map
	 * @return
	 */
	List<T> selectAll4Page(Map<String,Object> map);
	
	/**
	 * 条件查询所有,也是分页查询(分页拦截的就是此语句) <br>
	 * 如果条件查询和分页查询不同，则重新写个条件查询
	 * @param map
	 * @return
	 */
	List<T> selectAllByCondition(Map<String,Object> map);
}