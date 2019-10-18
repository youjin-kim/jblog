package kr.co.itcen.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.exception.BlogDaoException;
import kr.co.itcen.jblog.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(BlogVo vo) throws BlogDaoException {
		int count = sqlSession.insert("blog.insert", vo);
		Boolean result = (count == 1);
		
		return result;
	}

	public BlogVo get(String id) {
		BlogVo vo =sqlSession.selectOne("blog.getInfo", id);
		return vo;
	}

	public Boolean update(BlogVo blogVo) {
		int count = sqlSession.update("blog.update", blogVo);
		Boolean result = (count == 1);
		return result;
	}


}
