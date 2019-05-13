package com.hk.sys.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hk.sys.entity.SysFight;

public interface SysFightDao {
	
	/*查询所有信息并分页显示*/
	List<SysFight> dofindbyadd(
			@Param("fromAdd")String fromAdd,
			@Param("toAdd")String toAdd,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/*查询所有记录条数*/
	int getRowCount(@Param("fromAdd")String fromAdd,
					@Param("toAdd")String toAdd);
	/*添加记录*/
	int insertfight(SysFight sysFight);
	//修改状态
	int updateStatus(@Param("fightId")String fightId, @Param("status")Integer status);
	//验证航班编号是否重复
	int findByFightId(@Param("fightId")String fightId);
	//查询要删除航班信息
	List<SysFight> findByIds(@Param("ids")Integer... ids);
	//批量删除
	int deleteByIds(@Param("ids")Integer... ids);
	//取出要更新的数据
	SysFight updateFind(Integer id);
	//更新信息
	int updateFight(SysFight sysFight);
}
