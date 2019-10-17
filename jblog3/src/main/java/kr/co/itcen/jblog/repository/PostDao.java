package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.PostVo;

@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;

	public PostVo view(String id, Long categoryNo) {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("id", id);
		value.put("categoryNo", categoryNo);
		PostVo vo = sqlSession.selectOne("post.view", value);
		return vo;
	}

	public List<PostVo> getList(String id, Long categoryNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("categoryNo", categoryNo);
		List<PostVo> list = sqlSession.selectList("post.getList", map);
		return list;
	}

	public int getListCount(Long categoryNo) {
		int totalListCount = sqlSession.selectOne("post.getListCount", categoryNo);
		return totalListCount;
	}

	public Boolean insert(PostVo vo) {
		int count = sqlSession.insert("post.insert", vo);
		Boolean result = (count == 1);
		
		return result;
	}

}
