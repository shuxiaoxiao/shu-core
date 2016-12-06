package com.shupro.core.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shupro.core.utils.page.PageBean;

public interface BaseService<T> {
//	void setBaseMapper();
	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	int deleteById(Serializable id);
	
	/**
	 * 通过主键id数组 删除(底层是mybatis的foreach)
	 * @param idArr
	 * @return
	 */
	int deleteById(Serializable[] ids);
	
	/**
	 * 批量删除,多个主键值用","隔开(底层是循环执行单条语句)
	 * @param ids
	 * @return
	 */
	int deleteById(String ids);
	
	/**
	 * 增加
	 * @param record	对象
	 * @return
	 */
	public int insert(T record);

//	int insertSelective(T record);
	
	/**
	 * 动态更新
	 * @param record	对象
	 * @return
	 */
	public int updateSelective(T record);

	/**
	 * 更新
	 * @param record	对象
	 * @return
	 */
	public int update(T record);
	
	/**
	 * 根据主键id查询单条记录
	 * @param id	主键id
	 * @return
	 */
	public T selectById(Serializable id);

	/**
	 * 条件查询所有,也是分页查询(分页拦截的就是此语句) <br>
	 * 如果条件查询和分页查询不同，则重新写个条件查询
	 * @param map
	 * @return
	 */
	public List<T> selectAllByCondition(Map<String,Object> map);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public PageBean<T> select2PageBean(Map<String, Object> map);
	
}