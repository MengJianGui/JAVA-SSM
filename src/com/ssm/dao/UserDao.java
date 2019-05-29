package com.ssm.dao;

import static com.ssm.util.HrmConstants.USERTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.ssm.dao.provider.UserDynaSqlProvider;
import com.ssm.domain.User;

/**
 * UserMapper接口
 * */
public interface UserDao {
	
	/**
	 * 根据登录名和密码查询用户
	 * @param String name
	 * @param String eduid
	 * @return 找到返回User对象，没有找到返回null
	 * */
	@Select("select * from hrm_user where name = #{name} and eduid = #{eduid}")
	User selectByNameAndEduid(@Param("name")String name,
			@Param("eduid") String eduid);
	
	/**
	 * 根据id来查询用户
	 * @param id
	 * @return
	 */
	@Select("select * from hrm_user where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name", property="name"),
		@Result(column="eduid", property="eduid"),
		@Result(column="tutor_id", property="tutor",
				one = @One(select = "com.ssm.dao.TutorDao.selectById",
				fetchType = FetchType.EAGER))
	})
	User selectById(Integer id);
	
	/**
	 * 根据参数动态查询员工
	 */
	@SelectProvider(type=UserDynaSqlProvider.class, method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="name", property="name"),
		@Result(column="eduid", property="eduid"),
		@Result(column="tutor_id", property="tutor",
				one = @One(select = "com.ssm.dao.TutorDao.selectById",
				fetchType = FetchType.EAGER))
	})
	List<User> selectByPage(Map<String, Object> params);
	
	/**
	 * 动态插入成员
	 */
	@SelectProvider(type=UserDynaSqlProvider.class, method = "insertUser")
	void save(User user);
	
	
	/**
	 * 根据id删除用户
	 */
	@Delete(" delete from " + USERTABLE + " WHERE ID = #{id} ")
	void deleteById(Integer id);
	
	
	/**
	 * 根据参数查询用户总数
	 */
	@SelectProvider(type = UserDynaSqlProvider.class, method = "count")
	Integer count(Map<String, Object> params);

	/**
	 * 动态修改成员
	 */
	@SelectProvider(type=UserDynaSqlProvider.class, method="updateUser")
	void update(User user);
	
	@Select("select * from " + USERTABLE + " ")
	List<User> selectAllUser();

}
