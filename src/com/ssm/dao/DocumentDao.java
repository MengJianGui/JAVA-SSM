package com.ssm.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import com.ssm.dao.provider.DocumentDynaSqlProvider;
import com.ssm.domain.Document;
import static com.ssm.util.HrmConstants.DOCUMENTTABLE;

/**   
 * @Description: DocumentMapper接口   
 */
public interface DocumentDao {

	// 动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate",javaType=java.util.Date.class),
		@Result(column="user_id",property="user",
			one=@One(select="com.ssm.dao.UserDao.selectById",
		fetchType=FetchType.EAGER))
	})
	List<Document> selectByPage(Map<String, Object> params);
		
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
	Integer count(Map<String, Object> params);
		
	// 动态插入文档
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
	void save(Document document);
	
	@Select("select * from "+DOCUMENTTABLE+" where ID = #{id}")
	Document selectById(int id);
	
	// 根据id删除文档
	@Delete(" delete from "+DOCUMENTTABLE+" where id = #{id} ")
	void deleteById(Integer id);
		
	// 动态修改文档
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
	void update(Document document);
		
}
