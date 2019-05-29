package com.ssm.dao.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.ssm.domain.Notice;

import static com.ssm.util.HrmConstants.NOTICETABLE;

public class NoticeDynaSqlProvider {

	/**
	 * 分页动态查询
	 */
	public String selectWithParam(Map<String, Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(NOTICETABLE);
				if(params.get("notice") != null) {
					Notice notice = (Notice) params.get("notice");
					if(notice.getTitle() != null && !notice.getTitle().equals("")) {
						WHERE(" title LIKE CONCAT ('%', #{notice,title}, ''%) ");
					}
					if(notice.getContent() != null && !notice.getContent().equals("")) {
						WHERE(" content LIKE CONCAT ('%', #{notice.content}, '%') ");
					}
					//如何进行日期的模糊匹配
				} 
			}
		}.toString();
		if(params.get("pageModel") != null) {
			sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
		}
		return sql;
	}
	
	/**
	 * 动态查询公告总数量
	 */
	public String count(Map<String, Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(NOTICETABLE);
				if(params.get("notice") != null) {
					Notice notice = (Notice) params.get("notice");
					if(notice.getTitle() != null && !notice.getTitle().equals("")) {
						WHERE(" title LIKE CONCAT ('%', #{notice,title}, ''%)");
					}
					if(notice.getContent() != null && !notice.getContent().equals("")) {
						WHERE(" content LIKE CONCAT ('%', #{notice.content}, '%')");
					}
				}
			}
		}.toString();
	}
	
	/**
	 * 动态插入新的公告
	 * @param notice
	 * @return
	 */
	public String insertNotice(Notice notice) {
		return new SQL() {
			{
				INSERT_INTO(NOTICETABLE);
				if(notice.getTitle() != null && !notice.getTitle().equals("")){
					VALUES("title", "#{title}");
				}
				if(notice.getContent() != null && !notice.getContent().equals("")){
					VALUES("content", "#{content}");
				}
				if(notice.getUser() != null && notice.getUser().getId() != null){
					VALUES("user_id", "#{user.id}");
				}
			}
		}.toString();
	}
	/**
	 * 动态更新公告
	 */
	public String updateNotice(Notice notice) {
		return new SQL() {
			{
				UPDATE(NOTICETABLE);
				if(notice.getTitle() != null && !notice.getTitle().equals("")){
					SET(" title = #{title}");
				}
				if(notice.getContent() != null && !notice.getContent().equals("")){
					SET(" content = #{content}");
				}
				if(notice.getUser() != null && notice.getUser().getId() != null){
					SET(" user_id = #{user.id}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
