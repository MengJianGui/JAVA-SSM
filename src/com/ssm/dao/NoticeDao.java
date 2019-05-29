package com.ssm.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;

import com.ssm.dao.provider.NoticeDynaSqlProvider;
import com.ssm.domain.Notice;
import static com.ssm.util.HrmConstants.NOTICETABLE;

public interface NoticeDao {

	
	/**
	 * 动态分页查询用户信息
	 * @param params
	 * @return
	 */
	@SelectProvider(type=NoticeDynaSqlProvider.class, method="selectWithParam")
	@Results({
		@Result(id=true, column="id", property="id"),
		@Result(column="create_date", property="createDate", javaType=Date.class),
		@Result(column="user_id", property="user",
				one=@One(select="com.ssm.dao.UserDao.selectById",
				fetchType = FetchType.EAGER)),
	})
	List<Notice> selectByPage(Map<String, Object> params);
	
	/**
	 * 查询公告总数
	 * @param params
	 * @return
	 */
	@SelectProvider(type=NoticeDynaSqlProvider.class, method="count")
	Integer count(Map<String, Object> params);
	
	/**
	 * 根据公告id查询公告
	 * @param id
	 * @return
	 */
	@Select("select * from " + NOTICETABLE + " WHERE id = #{id} ")
	Notice selectById(int id);
	
	/**
	 * 根据id来删除公告
	 * @param id
	 */
	@Delete("delete from " + NOTICETABLE + " WHERE id = #{id} ")
	void deleteById(Integer id);
	
	/**
	 * 动态插入公告
	 */
	@SelectProvider(type=NoticeDynaSqlProvider.class, method="insertNotice")
	void save(Notice notice);
	
	/**
	 * 动态修改公告
	 */
	@SelectProvider(type=NoticeDynaSqlProvider.class, method="updateNotice")
	void update(Notice notice);
}
