package com.ssm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.ssm.dao.provider.ResourceDynaSqlProvider;
import com.ssm.domain.Resource;
import static com.ssm.util.HrmConstants.RESOURCETABLE;

public interface ResourceDao {

	/**
	 * 分页动态查询表格信息
	 */
	@SelectProvider(type=ResourceDynaSqlProvider.class, method="selectWithParam")
	@Results({
		@Result(id=true, column="id", property="id"),
		@Result(column="user_id", property="user",
				one=@One(select="com.ssm.dao.UserDao.selectById",
				fetchType = FetchType.EAGER)),
	})
	List<Resource> selectByPage(Map<String, Object> params);
	
	/**
	 * 查询总数
	 */
	@SelectProvider(type=ResourceDynaSqlProvider.class, method="count")
	Integer count(Map<String, Object> params);
	
	/**
	 * 根据id进行查询
	 */
	@Select("select * from " + RESOURCETABLE + " WHERE id = #{id} ")
	Resource selectById(int id);
	
	/**
	 * 根据id删除信息
	 */
	@Delete("delete from " + RESOURCETABLE + " WHERE id = #{id} ")
	void deleteById(Integer id);
	
	/**
	 * 动态插入
	 */
	@SelectProvider(type=ResourceDynaSqlProvider.class, method="insertResource")
	void save(Resource resource);
	
	/**
	 * 动态修改
	 */
	@SelectProvider(type=ResourceDynaSqlProvider.class, method="updateResource")
	void update(Resource resource);
	
}
