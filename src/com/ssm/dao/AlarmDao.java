package com.ssm.dao;

import static com.ssm.util.HrmConstants.ALARMTABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import com.ssm.dao.provider.AlarmDynaSqlProvider;
import com.ssm.domain.Alarm;

public interface AlarmDao {

	//动态查询
		@SelectProvider(type=AlarmDynaSqlProvider.class, method="selectWithParam")
		List<Alarm> selectByPage(Map<String, Object> params);
		
		@SelectProvider(type=AlarmDynaSqlProvider.class, method="count")
		Integer count(Map<String, Object> params);
		
		@Select("select * from " + ALARMTABLE + " ")
		List<Alarm> selectAllAlarm();

		/**
		 * 根据id选择定时服务信息
		 * @param id
		 * @return
		 */
		@Select("select * from " + ALARMTABLE + " where ID = #{id}")
		Alarm selectById(Integer id);
		
		/**
		 * 根据id删除定时服务
		 */
		@Delete(" delete from " + ALARMTABLE + " where id = #{id} ")
		void deleteById(Integer id);
		
		/**
		 * 动态插入定时服务
		 */
		@SelectProvider(type=AlarmDynaSqlProvider.class, method="insertAlarm")
		void save(Alarm alarm);
		
		/**
		 * 动态修改定时服务
		 */
		@SelectProvider(type=AlarmDynaSqlProvider.class, method="updateAlarm")
		void update (Alarm alarm);
}
