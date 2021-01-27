package com.zam.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

/**
 * 持久层
 * 
 * @author AdminTC
 */
public class TSopDao {

	public List<TShopSku> find1(Integer id) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			return sqlSession.selectList("namespace002.getById1", id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	public List<TShopSku> find2(Integer id) throws Exception {
		SqlSession sqlSession = null;
		try {
			sqlSession = MybatisUtil.getSqlSession();
			return sqlSession.selectList("namespace002.getById2", id);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			MybatisUtil.closeSqlSession();
		}
	}

	public static void main(String[] args) throws Exception {
		TSopDao dao = new TSopDao();
//		List<TShopSku> list = dao.find1(1);
//		for (TShopSku t : list) {
//			System.out.println(
//					t.getId() + ":" + t.getSkuName() + ":" + t.getCategoryId() + ":" + (t.getAttributes() == null));
//		}

		List<TShopSku> list = dao.find2(1);
		for (TShopSku t : list) {
			System.out.println(t.getId() + ":" + t.getSkuName() + ":" + t.getCategoryId() + ":"
					+ (t.getAttributes() == null) + "----" + t.getAttributes().get(1).getAttributeName());
		}
	}
}
