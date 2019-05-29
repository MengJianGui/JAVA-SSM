package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.domain.Tutor;

import static com.ssm.util.HrmConstants.TUTORTABLE;

public class TutorDynaSqlProvider {

	/**
	 * 分页动态查询
	 * @param params
	 * @return
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(TUTORTABLE);
				if(params.get("tutor") != null) {
					Tutor tutor = (Tutor) params.get("tutor");
					if(tutor.getName() != null && !tutor.getName().equals("")) {
						WHERE(" name LIKE CONCAT ('%', #{tutor.name}, '%') ");
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
				FROM(TUTORTABLE);
				if(params.get("tutor") != null) {
					Tutor tutor = (Tutor) params.get("tutor");
					if(tutor.getName() != null && !tutor.getName().equals("")) {
						WHERE(" name LIKE CONCAT ('%', #{tutor.name}, '%') ");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * 动态插入
	 */
	public String insertTutor(Tutor tutor) {
		return new SQL() {
			{
				INSERT_INTO(TUTORTABLE);
				if(tutor.getName() != null && !tutor.getName().equals("")) {
					VALUES("name", "#{name}");
				}
				if(tutor.getTitle() != null && !tutor.getName().equals("")) {
					VALUES("title", "#{title}");
				}
				if(tutor.getPhone() != null && !tutor.getPhone().equals("")) {
					VALUES("phone", "#{phone}");
				}
				if(tutor.getField() != null && !tutor.getField().equals("")) {
					VALUES("field", "#{field}");
				}
				if(tutor.getEmail() != null && !tutor.getEmail().equals("")) {
					VALUES("email", "#{email}");
				}
			}
		}.toString();
	}
	
	/**
	 * 动态更新
	 */
	public String updateTutor(Tutor tutor) {
		return new SQL() {
			{
				UPDATE(TUTORTABLE);
				if(tutor.getName() != null) {
					SET(" name = #{name} ");
				}
				if(tutor.getTitle() != null) {
					SET(" title = #{title} ");
				}
				if(tutor.getPhone() != null) {
					SET(" phone = #{phone} ");
				}
				if(tutor.getField() != null) {
					SET(" field = #{field} ");
				}
				if(tutor.getEmail() != null) {
					SET(" email = #{email} ");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
