package com.zam.mybatis;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

/**
 * 持久层
 * 
 * @author AdminTC
 */
public class StudentDao2 {
	/**
	 * 有条件的查询所有学生
	 */
	public List<Student> findAll(Integer id, String name, Double sal) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("pid", id);
			map.put("pname", name);
			map.put("psal", sal);
			return sqlSession.selectList("namespace002.findAll", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	public static void main(String[] args) throws Exception {
		StudentDao2 dao = new StudentDao2();
		List<Student> studentList = dao.findAll(4, null, null);
		for (Student s : studentList) {
			System.out.println(s.getId() + ":" + s.getName() + ":" + s.getSal());
		}
		
		studentList = dao.findAll(4, "靓班长", null);
		for (Student s : studentList) {
			System.out.println(s.getId() + ":" + s.getName() + ":" + s.getSal());
		}
		
		studentList = dao.findAll(4, null, 8000D);
		for (Student s : studentList) {
			System.out.println(s.getId() + ":" + s.getName() + ":" + s.getSal());
		}
	}
}
