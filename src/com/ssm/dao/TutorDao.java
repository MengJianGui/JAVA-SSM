package com.ssm.dao;

import static com.ssm.util.HrmConstants.TUTORTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.TutorDynaSqlProvider;
import com.ssm.domain.Tutor;

public interface TutorDao {

	//动态查询
	@SelectProvider(type=TutorDynaSqlProvider.class, method="selectWithParam")
	List<Tutor> selectByPage(Map<String, Object> params);
	
	@SelectProvider(type=TutorDynaSqlProvider.class, method="count")
	Integer count(Map<String, Object> params);
	
	@Select("select * from " + TUTORTABLE + " ")
	List<Tutor> selectAllTutor();

	/**
	 * 根据id选择导师信息
	 * @param id
	 * @return
	 */
	@Select("select * from " + TUTORTABLE + " where ID = #{id}")
	Tutor selectById(Integer id);
	
	/**
	 * 根据id删除导师
	 */
	@Delete(" delete from " + TUTORTABLE + " where id = #{id} ")
	void deleteById(Integer id);
	
	/**
	 * 动态插入导师
	 */
	@SelectProvider(type=TutorDynaSqlProvider.class, method="insertTutor")
	void save(Tutor tutor);
	
	/**
	 * 动态修改导师
	 */
	@SelectProvider(type=TutorDynaSqlProvider.class, method="updateTutor")
	void update (Tutor tutor);
}
