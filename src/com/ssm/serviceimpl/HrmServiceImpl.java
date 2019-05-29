package com.ssm.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssm.domain.Alarm;
import com.ssm.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.dao.AlarmDao;
import com.ssm.dao.DocumentDao;
import com.ssm.dao.NoticeDao;
import com.ssm.dao.ResourceDao;
import com.ssm.dao.ServiceDao;
import com.ssm.dao.TutorDao;
import com.ssm.dao.UserDao;
import com.ssm.domain.Notice;
import com.ssm.domain.Resource;
import com.ssm.domain.Tutor;
import com.ssm.domain.User;
import com.ssm.service.HrmService;
import com.ssm.util.PageModel;

 
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService{

	/**
	 * 自动注入持久层Dao对象
	 * */
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private TutorDao tutorDao;
	
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private ServiceDao serviceDao;
	@Autowired
	private AlarmDao alarmDao;
	@Autowired
	private ResourceDao resourceDao;
	
	/*****************用户服务接口实现*************************************/
	/**
	 * HrmServiceImpl接口login方法实现
	 *  @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User login(String name, String eduid) {
//		System.out.println("HrmServiceImpl login -- >>");
		return userDao.selectByNameAndEduid(name, eduid);
	}

	/**
	 * HrmService接口findUser方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<User> findUser(User user,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("user", user);
		
		int recordCount = userDao.count(params);
	    pageModel.setRecordCount(recordCount);
	    
	    if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
	    List<User> users = userDao.selectByPage(params);
	    return users;
	}
	/**
	 * HrmService接口removeUserById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeUserById(Integer id) {
		userDao.deleteById(id);
		
	}
	/**
	 * HrmService接口findUserById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public User findUserById(Integer id) {
		
		return userDao.selectById(id);
	}
	
	/**
	 * HrmService接口addUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addUser(User user) {
		userDao.save(user);
		
	}
	
	/**
	 * HrmService接口modifyUser方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyUser(User user) {
		userDao.update(user);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<User> findAllUser() {
		
		return userDao.selectAllUser();
	}
	
	/*****************导师服务接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Tutor> findAllTutor() {
		
		return tutorDao.selectAllTutor();
	}
	
	/**
	 * HrmServiceImpl接口findTutor方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Tutor> findTutor(Tutor tutor,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("tutor", tutor);
		int recordCount = tutorDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Tutor> tutors = tutorDao.selectByPage(params);
		 
		return tutors;
	}
	
	/**
	 * HrmServiceImpl接口removeTutorById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeTutorById(Integer id) {
		tutorDao.deleteById(id);
		
	}

	/**
	 * HrmServiceImpl接口addTutor方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addTutor(Tutor tutor) {
		tutorDao.save(tutor);
		
	}
	
	/**
	 * HrmServiceImpl接口findTutorById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Tutor findTutorById(Integer id) {
		
		return tutorDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口modifyTutor方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyTutor(Tutor tutor) {
		tutorDao.update(tutor);	
	}
	
	/*****************公告服务接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Notice> findNotice(Notice notice, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("notice", notice);
		int recordCount = noticeDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Notice> notices = noticeDao.selectByPage(params);
		 
		return notices;
	}

	/**
	 * HrmService接口findNoticeById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Notice findNoticeById(Integer id) {
		
		return noticeDao.selectById(id);
	}

	/**
	 * HrmService接口removeNoticeById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeNoticeById(Integer id) {
		noticeDao.deleteById(id);
		
	}
	
	/**
	 * HrmService接口addNotice方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addNotice(Notice notice) {
		noticeDao.save(notice);
		
	}
	
	/**
	 * HrmService接口modifyNotice方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyNotice(Notice notice) {
		noticeDao.update(notice);	
	}
	
	/*****************上传文件服务接口实现*************************************/
	/**
	 * HrmService接口findDocument方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Document> findDocument(Document document, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("document", document);
		int recordCount = documentDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Document> documents = documentDao.selectByPage(params);
		 
		return documents;
	}

	/**
	 * HrmService接口addDocument方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addDocument(Document document) {
		documentDao.save(document);
		
	}
	/**
	 * HrmService接口removeDocumentById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeDocumentById(Integer id) {
		documentDao.deleteById(id);
		
	}
	/**
	 * HrmService接口modifyDocument方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyDocument(Document document) {
		documentDao.update(document);
		
	}
	/**
	 * HrmService接口findDocumentById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Document findDocumentById(Integer id) {
		
		return documentDao.selectById(id);
	}

	/*****************服务规则库功能接口实现*************************************/
	@Override
	public List<com.ssm.domain.Service> findService(com.ssm.domain.Service service, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("service", service);
		int recordCount = serviceDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<com.ssm.domain.Service> services = serviceDao.selectByPage(params);
		 
		return services;
	}

	@Override
	public void addService(com.ssm.domain.Service service) {
		serviceDao.save(service);
		
	}

	@Override
	public com.ssm.domain.Service findServiceById(Integer id) {
		return serviceDao.selectById(id);
	}

	@Override
	public void removeServiceById(Integer id) {
		serviceDao.deleteById(id);
		
	}

	@Override
	public void modifyService(com.ssm.domain.Service service) {
		serviceDao.update(service);
		
	}

	/*****************定时服务功能接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Alarm> findAllAlarm() {
		
		return alarmDao.selectAllAlarm();
	}
	
	/**
	 * HrmServiceImpl接口findAlarm方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public List<Alarm> findAlarm(Alarm alarm,PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("alarm", alarm);
		int recordCount = alarmDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Alarm> alarms = alarmDao.selectByPage(params);
		 
		return alarms;
	}
	
	/**
	 * HrmServiceImpl接口removeAlarmById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeAlarmById(Integer id) {
		alarmDao.deleteById(id);
		
	}

	/**
	 * HrmServiceImpl接口addAlarm方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addAlarm(Alarm alarm) {
		alarmDao.save(alarm);
		
	}
	
	/**
	 * HrmServiceImpl接口findAlarmById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public Alarm findAlarmById(Integer id) {
		
		return alarmDao.selectById(id);
	}
	
	/**
	 * HrmServiceImpl接口modifyAlarm方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyAlarm(Alarm alarm) {
		alarmDao.update(alarm);	
	}
	
	/*****************设备预约服务接口实现*************************************/
	@Transactional(readOnly=true)
	@Override
	public List<Resource> findResource(Resource resource, PageModel pageModel) {
		/** 当前需要分页的总数据条数  */
		Map<String,Object> params = new HashMap<>();
		params.put("resource", resource);
		int recordCount = resourceDao.count(params);
		pageModel.setRecordCount(recordCount);
		
		if(recordCount > 0){
	        /** 开始分页查询数据：查询第几页的数据 */
		    params.put("pageModel", pageModel);
	    }
		
		List<Resource> resources = resourceDao.selectByPage(params);
		 
		return resources;
	}

	/**
	 * HrmService接口findResourceById方法实现
	 * @see { HrmService }
	 * */
	@Transactional(readOnly=true)
	@Override
	public Resource findResourceById(Integer id) {
		
		return resourceDao.selectById(id);
	}

	/**
	 * HrmService接口removeResourceById方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void removeResourceById(Integer id) {
		resourceDao.deleteById(id);
		
	}
	
	/**
	 * HrmService接口addResource方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void addResource(Resource resource) {
		resourceDao.save(resource);
		
	}
	
	/**
	 * HrmService接口modifyResource方法实现
	 * @see { HrmService }
	 * */
	@Override
	public void modifyResource(Resource resource) {
		resourceDao.update(resource);	
	}
	
}
