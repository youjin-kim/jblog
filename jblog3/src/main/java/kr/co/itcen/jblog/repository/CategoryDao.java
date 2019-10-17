package kr.co.itcen.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.itcen.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;

	public Boolean insert(CategoryVo categoryVo) {
		int count = sqlSession.insert("category.insert", categoryVo);
		Boolean result = (count == 1);
		return result;
	}

	public List<CategoryVo> getList(String id) {
		List<CategoryVo> list = sqlSession.selectList("category.getList", id);
		return list;
	}

	public Boolean delete(Long categoryNo, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categoryNo", categoryNo);
		map.put("id", id);
		int count = sqlSession.delete("category.delete", map);
		Boolean result = (count == 1);
		return result;
	}

}
