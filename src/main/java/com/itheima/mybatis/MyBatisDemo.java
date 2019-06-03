package com.itheima.mybatis;

import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MyBatisDemo {
    @Test
    public void testFindUserById() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        InputStream inputStream= Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory=sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession=factory.openSession();
        User user=sqlSession.selectOne("user.findUserById",22);
        System.out.println(user);
        inputStream.close();
        sqlSession.close();
    }
    @Test
    public void testFindUserByName(){
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        InputStream inputStream= null;
        try {
            inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory=sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession=factory.openSession();
        List<User> list = sqlSession.selectList("user.findUserByName", "%张%");
        System.out.println(list);
    }

    @Test
    public void testInsertUser() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory=sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession=factory.openSession(true);
        User user=new User();
        user.setUsername("sasuke");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("上海");
        System.out.println("之前:"+user);
        sqlSession.insert("user.insertUser",user);
        System.out.println("之后:"+user);
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testUpdateUser() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory=sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession=factory.openSession(true);
        User user=new User();
        user.setId("1");;
        user.setAddress("北京");
        user.setSex("1");
        user.setUsername("kakaxi");
        sqlSession.update("user.updateUser",user);
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testdeleteUserById() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
        InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory=sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession=factory.openSession(true);
        sqlSession.delete("user.deleteUserById","31");
        sqlSession.close();
        inputStream.close();
    }

}
