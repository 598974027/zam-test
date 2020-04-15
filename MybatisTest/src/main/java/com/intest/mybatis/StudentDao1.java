package com.intest.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * 持久层
 * 
 * @author AdminTC
 */
public class StudentDao1 {
	/**
	 * 增加学生
	 */
	public void add(Student student) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.insert("namespace001.add", student);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	/**
	 * 根据ID查询学生
	 */
	public Student findById(int id) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			Student student = sqlSession.selectOne("namespace001.findById", id);
			sqlSession.commit();
			return student;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	/**
	 * 查询所有学生
	 */
	public List<Student> findAll() throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			return sqlSession.selectList("namespace001.findAll");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	/**
	 * 更新学生
	 */
	public void update(Student student) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.update("namespace001.update", student);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	/**
	 * 删除学生
	 */
	public void delete(Student student) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			sqlSession.delete("namespace001.delete2", student);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	public static void main(String[] args) throws Exception {
		StudentDao1 dao = new StudentDao1();
		Student student = new Student();
		student.setId(3);
		dao.delete(student);
		
		
//		dao.add(new Student(3,"哈哈",7000D));
//		dao.add(new Student(4,"呵呵",8000D));
//		Student student = dao.findById(3);
//		System.out.println(student.getName());
		
//		Student student2 = dao.findById(3);
//		student2.setName("靓班长");
//		dao.update(student2);
//
//		Student student3 = dao.findById(3);
//		System.out.println(student3.getId() + ":" + student3.getName() + ":" + student3.getSal());

//		List<Student> studentList = dao.findAll();
//		for(Student s : studentList){
//			System.out.println(s.getId()+":"+s.getName()+":"+s.getSal());
//			dao.delete(s);
//		}
	}
}
