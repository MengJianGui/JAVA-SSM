package com.ssm.dao.provider;

import static com.ssm.util.HrmConstants.ALARMTABLE;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.domain.Alarm;

public class AlarmDynaSqlProvider {

	/**
	 * 分页动态查询
	 * @param params
	 * @return
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(ALARMTABLE);
				if(params.get("alarm") != null) {
					Alarm alarm = (Alarm) params.get("alarm");
					if(alarm.getAlarm_service() != null && !alarm.getAlarm_service().equals("0")) {
						WHERE(" alarm_service = #{alarm.alarm_service} ");
					}
				}
			}
		}.toString();
		if(params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	/**
	 * 动态查询总量
	 */
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(ALARMTABLE);
				if(params.get("alarm") != null) {
					Alarm alarm = (Alarm) params.get("alarm");
					if(alarm.getAlarm_service() != null && !alarm.getAlarm_service().equals("0")) {
						WHERE(" alarm_service = #{alarm.alarm_service} ");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * 动态插入
	 */
	public String insertAlarm(Alarm alarm) {
		return new SQL() {
			{
				INSERT_INTO(ALARMTABLE);
				if(alarm.getAlarm_service() != null && !alarm.getAlarm_service().equals("0")) {
					VALUES("alarm_service", "#{alarm_service}");
				}
				if(alarm.getAlarm_time() != null) {
					VALUES("alarm_time", "#{alarm_time}");
				}
			}
		}.toString();
	}
	
	/**
	 * 动态更新
	 */
	public String updateAlarm(Alarm alarm) {
		return new SQL() {
			{
				UPDATE(ALARMTABLE);
				if(alarm.getAlarm_service() != null && !alarm.getAlarm_service().equals("0")) {
					SET(" alarm_service = #{alarm_service}");
				}
				if(alarm.getAlarm_time() != null) {
					SET(" alarm_time = #{alarm_time} ");
				}
				
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}