package com.ssm.dao.provider;

import static com.ssm.util.HrmConstants.USERTABLE;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import com.ssm.domain.User;

/**
 * 用户动态SQL语句提供类
 */
public class UserDynaSqlProvider {

	/**
	 * 分页动态查询
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(USERTABLE);
				if(params.get("user") != null) {
					User user = (User)params.get("user");
					//匹配姓名，学号，导师；
					if(user.getName() != null && !user.getName().equals("")) {
						WHERE(" name LIKE CONCAT('%', #{user.name}, '%')");
					}
					if(user.getEduid() != null && !user.getEduid().equals("")) {
						WHERE(" eduid LIKE CONCAT('%', #{user.eduid}, '%')");
					}
					if(user.getTutor() != null && user.getTutor().getId() != null && user.getTutor().getId() != 0) {
						WHERE(" TUTOR_ID = #{user.tutor.id} ");
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
				FROM(USERTABLE);
				if(params.get("user") != null) {
					User user = (User) params.get("user");
					if(user.getName() != null && !user.getName().equals("")) {
						WHERE(" name LIKE CONCAT ('%', #{user.name}, '%') ");
					}
					if(user.getEduid() != null && !user.getEduid().equals("")) {
						WHERE(" eduid LIKE CONCAT('%', #{user.eduid}, '%')");
					}
					if(user.getTutor() != null && user.getTutor().getId() != null && user.getTutor().getId() != 0) {
						WHERE(" tutor_id = #{user.tutor.id} ");
					}
				}
			}
		}.toString();
	}
	
	// 动态插入
	public String insertUser(User user){
		
		return new SQL(){
			{
				INSERT_INTO(USERTABLE);
				if(user.getName() != null){
					VALUES("name", "#{name}");
				}
				if(user.getEduid() != null){
					VALUES("eduid", "#{eduid}");
				}
				if(user.getTutor() != null){
					VALUES("tutor_id", "#{tutor.id}");
				}
				if(user.getGrade() != null){
					VALUES("grade", "#{grade}");
				}
				if(user.getSex() != null){
					VALUES("sex", "#{sex}");
				}
				if(user.getPhone() != null){
					VALUES("phone", "#{phone}");
				}
				if(user.getWechat() != null){
					VALUES("wechat", "#{wechat}");
				}
				if(user.getQq() != null){
					VALUES("qq", "#{qq}");
				}
				if(user.getEmail()!= null){
					VALUES("email", "#{email}");
				}
				if(user.getHomeaddress() != null){
					VALUES("homeaddress", "#{homeaddress}");
				}
				if(user.getField() != null){
					VALUES("field", "#{field}");
				}
				if(user.getAddress() != null){
					VALUES("address", "#{address}");
				}
				if(user.getBirthday() != null){
					VALUES("birthday", "#{birthday}");
				}
				if(user.getCompany() != null){
					VALUES("company", "#{company}");
				}
				if(user.getRemark()!= null){
					VALUES("remark", "#{remark}");
				}
			}
		}.toString();
	}
	// 动态更新
	public String updateUser(User user){
		
		return new SQL(){
			{
				UPDATE(USERTABLE);
				if(user.getName() != null){
					SET(" name = #{name}");
				}
				if(user.getEduid() != null){
					SET(" eduid = #{eduid}");
				}
				if(user.getTutor() != null){
					SET(" tutor_id = #{tutor.id}");
				}
				if(user.getGrade() != null){
					SET(" grade = #{grade}");
				}
				if(user.getSex() != null){
					SET(" sex = #{sex}");
				}
				if(user.getPhone() != null){
					SET(" phone = #{phone}");
				}
				if(user.getWechat() != null){
					SET(" wechat = #{wechat}");
				}
				if(user.getQq() != null){
					SET(" qq = #{qq}");
				}
				if(user.getEmail()!= null){
					SET(" email = #{email}");
				}
				if(user.getHomeaddress() != null){
					SET(" homeaddress = #{homeaddress}");
				}
				if(user.getField() != null){
					SET(" field = #{field}");
				}
				if(user.getAddress() != null){
					SET(" address = #{address}");
				}
				if(user.getBirthday() != null){
					SET(" birthday = #{birthday}");
				}
				if(user.getCompany() != null){
					SET(" company = #{company}");
				}
				if(user.getRemark()!= null){
					SET(" remark = #{remark}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
	
}
