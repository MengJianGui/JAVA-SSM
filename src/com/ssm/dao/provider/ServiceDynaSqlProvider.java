package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.domain.Service;

import static com.ssm.util.HrmConstants.SERVICETABLE;

public class ServiceDynaSqlProvider {

	/**
	 * 分页动态模糊查询服务规则库
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(SERVICETABLE);
				if(params.get("service") != null) {
					Service service = (Service) params.get("service");
					if(service.getUser() != null && service.getUser().getId() != null && service.getUser().getId() != 0) {
						WHERE(" user_id = #{service.user.id} ");
					}
					if(service.getMoment() != null && !service.getMoment().equals("0")) {
						WHERE(" moment = #{service.moment} ");
					}
					if(service.getLocation() != null && !service.getLocation().equals("0")) {
						WHERE(" location = #{service.location} ");
					}
					if(service.getBehavior() != null && !service.getBehavior().equals("0")) {
						WHERE(" behavior = #{service.behavior} ");
					}
					if(service.getUser_pressure() != null && !service.getUser_pressure().equals("0")) {
						WHERE(" user_pressure = #{service.user_pressure} ");
					}
					if(service.getService() != null && !service.getService().equals("0")) {
						WHERE(" service = #{service.service} ");
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
				FROM(SERVICETABLE);
				if(params.get("service") != null){
					Service service = (Service) params.get("service");
					if(service.getUser() != null && service.getUser().getId() != null && service.getUser().getId() != 0) {
						WHERE(" user_id = #{service.user.id} ");
					}
					if(service.getMoment() != null && !service.getMoment().equals("0")) {
						WHERE(" moment = #{service.moment} ");
					}
					if(service.getLocation() != null && !service.getLocation().equals("0")) {
						WHERE(" location = #{service.location} ");
					}
					if(service.getBehavior() != null && !service.getBehavior().equals("0")) {
						WHERE(" behavior = #{service.behavior} ");
					}
					if(service.getUser_pressure() != null && !service.getUser_pressure().equals("0")) {
						WHERE(" user_pressure = #{service.user_pressure} ");
					}
					if(service.getService() != null && !service.getService().equals("0")) {
						WHERE(" service = #{service.service} ");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * 动态插入服务规则
	 */
	// 动态插入
		public String insertService(Service service){
			
			return new SQL(){
				{
					INSERT_INTO(SERVICETABLE);
					if(service.getMoment() != null){
						VALUES(" moment", "#{moment}");
					}
					if(service.getLocation() != null){
						VALUES(" location", "#{location}");
					}
					if(service.getUser() != null){
						VALUES("user_id", "#{user.id}");
					}
					if(service.getBehavior() != null){
						VALUES("behavior", "#{behavior}");
					}
					if(service.getState_duration() != null){
						VALUES("state_duration", "#{state_duration}");
					}
					if(service.getUser_pressure() != null){
						VALUES("user_pressure", "#{user_pressure}");
					}
					if(service.getService() != null){
						VALUES("service", "#{service}");
					}
				}
			}.toString();
		}
	
	/**
	 * 动态更新服务规则库
	 */
	public String updateService(Service service){
		
		return new SQL(){
			{
				UPDATE(SERVICETABLE);
				if(service.getUser() != null){
					SET(" user_id = #{user.id}");
				}
				if(service.getMoment() != null){
					SET(" moment = #{moment}");
				}
				if(service.getLocation() != null){
					SET(" location = #{location}");
				}
				if(service.getBehavior() != null){
					SET(" behavior = #{behavior}");
				}
				if(service.getState_duration() != null){
					SET(" state_duration = #{state_duration}");
				}
				if(service.getUser_pressure() != null){
					SET(" user_pressure = #{user_pressure}");
				}
				if(service.getService() != null){
					SET(" service = #{service}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
