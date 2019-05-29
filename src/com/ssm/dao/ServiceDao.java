package com.ssm.dao;

import static com.ssm.util.HrmConstants.SERVICETABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.ssm.dao.provider.ServiceDynaSqlProvider;
import com.ssm.domain.Service;

public interface ServiceDao {

	/**
	 * 动态分页查询服务规则库信息，根据查询添加匹配
	 */
	@SelectProvider(type=ServiceDynaSqlProvider.class, method="selectWithParam")
	@Results({
		@Result(id=true, column="id", property="id"),
		@Result(column="user_id", property="user",
				one=@One(select="com.ssm.dao.UserDao.selectById",
				fetchType = FetchType.EAGER)),
	})
	List<Service> selectByPage(Map<String, Object> params);
	
	/**
	 * 查询公告总数 
	 */
	@SelectProvider(type=ServiceDynaSqlProvider.class, method="count")
	Integer count(Map<String, Object> params);
	
	/**
	 * 根据服务规则id来查询某个规则
	 */
	@Select(" select * from " + SERVICETABLE + " WHERE id = #{id} ")
	@Results({
		@Result(id=true, column="id", property="id"),
		@Result(column="user_id", property="user",
				one=@One(select="com.ssm.dao.UserDao.selectById",
				fetchType = FetchType.EAGER)),
	})
	Service selectById(int id);
	
	/**
	 * 根据id来删除服务规则库中的某条规则
	 */
	@Delete(" delete from " + SERVICETABLE + " WHERE id = #{id} ")
	void deleteById(Integer id);
	
	/**
	 * 动态插入某条服务规则
	 */
	@SelectProvider(type=ServiceDynaSqlProvider.class, method="insertService")
	void save(Service service);
	
	/**
	 * 对服务规则库不满意，则可以动态修改规则
	 */
	@SelectProvider(type=ServiceDynaSqlProvider.class, method="updateService")
	void update(Service service);
	
}
