package com.ssm.service;

import java.util.List;

import com.ssm.domain.Alarm;
import com.ssm.domain.Document;

import com.ssm.domain.Notice;
import com.ssm.domain.Resource;
import com.ssm.domain.Service;
import com.ssm.domain.Tutor;
import com.ssm.domain.User;
import com.ssm.util.PageModel;

public interface HrmService {


	/**
	 * 学生登录
	 * @param  loginname
	 * @param  password
	 * @return User对象
	 * */
	User login(String name,String eduid);
	
	/***********************学生信息的CRUD************************/
	
	/**
	 * 根据id查询学生
	 * @param id
	 * @return 学生对象
	 * */
	User findUserById(Integer id);
	
	/**
	 * 获得所有学生
	 * @return User对象的List集合
	 * */
	List<User> findUser(User user,PageModel pageModel);
	
	/**
	 * 根据id删除学生
	 * @param id
	 * */
	void removeUserById(Integer id);
	
	/**
	 * 修改学生
	 * @param User 学生对象
	 * */
	void modifyUser(User user);
	
	/**
	 * 添加学生
	 * @param User 学生对象
	 * */
	void addUser(User user);
	
	/**
	 * 获得所有的学生，不用于分页查询，只是得到所有的信息。
	 */
	List<User> findAllUser();
	
	/**************************导师信息的CRUD****************************/
	/**
	 * 获得所有导师，分页查询
	 * @return Tutor对象的List集合
	 * */
	List<Tutor> findTutor(Tutor tutor, PageModel pageModel);
	
	/**
	 * 获得所有导师
	 * @return Tutor对象的List集合
	 * */
	List<Tutor> findAllTutor();
	
	/**
	 * 根据id删除导师
	 * @param id
	 * */
	public void removeTutorById(Integer id);
	
	/**
	 * 添加导师
	 * @param tutor 导师对象
	 * */
	void addTutor(Tutor tutor);
	
	/**
	 * 根据id查询导师
	 * @param id
	 * @return 导师对象
	 * */
	Tutor findTutorById(Integer id);
	
	/**
	 * 修改导师
	 * @param tutor 导师对象
	 * */
	void modifyTutor(Tutor tutor);
	
	/**************************公告信息的CRUD****************************/
	/**
	 * 获得所有公告
	 */
	List<Notice> findNotice(Notice notice, PageModel pageModel);
	
	/**
	 * 根据id查询公告
	 */
	Notice findNoticeById(Integer id);
	
	/**
	 * 根据id删除公告
	 */
	public void removeNoticeById(Integer id);
	
	/**
	 * 添加公告
	 */
	void addNotice(Notice notice);
	
	/**
	 * 修改公告
	 */
	void modifyNotice(Notice notice);
	
	/**************************添加服务规则库，修改服务功能****************************/
	/**
	 * 获得所有服务规则
	 * @return Service对象的List集合
	 * */
	List<Service> findService(Service service, PageModel pageModel);
	
	/**
	 * 添加服务规则
	 * @param Service对象
	 * */
	void addService(Service service);
	
	/**
	 * 根据id查询某一服务
	 * @param id
	 * @return 服务规则对象
	 * */
	Service findServiceById(Integer id);
	
	/**
	 * 根据id删除服务规则
	 * @param id
	 * */
	public void removeServiceById(Integer id);
	
	/**
	 * 修改某一服务规则
	 * @param Service对象
	 * */
	void modifyService(Service service);
	
	/**************************上传文档****************************/
	/**
	 * 获得所有文档
	 * @return Document对象的List集合
	 * */
	List<Document> findDocument(Document document,PageModel pageModel);
	
	/**
	 * 添加文档
	 * @param Document 文件对象
	 * */
	void addDocument(Document document);
	
	/**
	 * 根据id查询文档
	 * @param id
	 * @return 文档对象
	 * */
	Document findDocumentById(Integer id);
	
	/**
	 * 根据id删除文档
	 * @param id
	 * */
	public void removeDocumentById(Integer id);
	
	/**
	 * 修改文档
	 * @param Document 公告对象
	 * */
	void modifyDocument(Document document);
	
	/**************************定时服务信息的CRUD****************************/
	/**
	 * 获得所有定时服务信息，分页查询
	 * @return Alarm对象的List集合
	 * */
	List<Alarm> findAlarm(Alarm alarm, PageModel pageModel);
	
	/**
	 * 获得所有定时服务信息
	 * @return Alarm对象的List集合
	 * */
	List<Alarm> findAllAlarm();
	
	/**
	 * 根据id删除定时服务信息
	 * @param id
	 * */
	public void removeAlarmById(Integer id);
	
	/**
	 * 添加定时服务信息
	 * @param alarm 定时服务信息对象
	 * */
	void addAlarm(Alarm alarm);
	
	/**
	 * 根据id查询定时服务信息
	 * @param id
	 * @return 定时服务信息对象
	 * */
	Alarm findAlarmById(Integer id);
	
	/**
	 * 修改定时服务信息
	 * @param tutor 导师对象
	 * */
	void modifyAlarm(Alarm alarm);
	
	/**************************设备预约信息的CRUD****************************/
	/**
	 * 获得所有设备预约信息
	 */
	List<Resource> findResource(Resource resource, PageModel pageModel);
	
	/**
	 * 根据id查询设备预约信息
	 */
	Resource findResourceById(Integer id);
	
	/**
	 * 根据id删除设备预约信息
	 */
	public void removeResourceById(Integer id);
	
	/**
	 * 添加设备预约信息
	 */
	void addResource(Resource resource);
	
	/**
	 * 修改设备预约信息
	 */
	void modifyResource(Resource resource);
}
