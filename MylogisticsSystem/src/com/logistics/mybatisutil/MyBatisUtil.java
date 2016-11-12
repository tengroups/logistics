package com.logistics.mybatisutil;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

	// 配置SqlSessionFactory加载配置文件
	public static SqlSessionFactory getSqlSessionFactory() {
		// MyBatis的配置文件
		String resource = "MyBatis.xml";
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		//getClassLoader()是取得该Class对象的类装载器
		//getResourceAsStream（resource）在类路径里获取资源输入流
		InputStream is = MyBatisUtil.class.getClassLoader().getResourceAsStream(
				resource);
		// 构建并初始化sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		return sessionFactory;
	}

	// 获取SqlSession
	public static SqlSession getsqlSession() {
		// 创建能执行映射文件中sql的sqlSession
		return getSqlSessionFactory().openSession();
	}

	
	/**
	 * 获取SqlSession的重载方法
	 * 
	 * @param isAutoCommit
	 *            true 表示创建的SqlSession对象在执行完SQL之后会自动提交事务 
	 *            false 表示创建的SqlSession对象在执行完SQL之后不会自动提交事务
	 *            ，这时就需要我们手动调用sqlSession.commit()提交事务
	 * @return SqlSession
	 */
	public static SqlSession getsqlSession(boolean isAutoCommit) {
		// 创建能执行映射文件中sql的sqlSession
		return getSqlSessionFactory().openSession(isAutoCommit);
	}
}
