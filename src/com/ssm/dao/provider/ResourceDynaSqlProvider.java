package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.domain.Resource;

import static com.ssm.util.HrmConstants.RESOURCETABLE;

public class ResourceDynaSqlProvider {

	/**
	 * 分页动态查询
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(RESOURCETABLE);
				/**
				 *  首先在第一次查询的时候params里面是没有数据的，然后第一次查询的时候存入，
				 * 接着在进行搜索的时候，params里面已经包含了全部resource信息，尽可以进行查询了。
				 */
				if(params.get("resource") != null) {
					Resource resource = (Resource)params.get("resource");
					if(resource.getResourcename() != null && !resource.getResourcename().contentEquals("0")) {
						WHERE(" resourcename = #{resource.resourcename} ");
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
	 * 动态查询总量，用于数据的分页显示；
	 */
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(RESOURCETABLE);
				if(params.get("resource") != null) {
					Resource resource = (Resource)params.get("resource");
					if(resource.getResourcename() != null && !resource.getResourcename().contentEquals("0")) {
						WHERE(" resourcename = #{resource.resourcename} ");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * 动态插入新的设备预约
	 */
	public String insertResource(Resource resource) {
		return new SQL() {
			{
				INSERT_INTO(RESOURCETABLE);
				if(resource.getDatetime() != null) {
					VALUES("datetime", "#{datetime}");
				}
				if(resource.getResourcename() != null) {
					VALUES("resourcename", "#{resourcename}");
				}
				if(resource.getUser() != null) {
					VALUES("user_id", "#{user.id}");
				}
			}
		}.toString();
	}
	
	/**
	 * 对预约设备信息进行动态修改
	 */
	public String updateResource(Resource resource) {
		return new SQL() {
			{
				UPDATE(RESOURCETABLE);
				if(resource.getDatetime() != null) {
					SET(" datetime = #{datetime} ");
				}
				if(resource.getResourcename() != null) {
					SET(" resourcename = #{resourcename} ");
				}
				if(resource.getUser() != null) {
					SET(" user_id = #{user.id} ");
				}
			}
		}.toString();
	}
}
