package kr.co.itcen.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;

	public void insert(CategoryVo categoryVo) {
		categoryDao.insert(categoryVo);
	}

	public Boolean add(CategoryVo categoryVo) {
		return categoryDao.insert(categoryVo);
	}

	public Boolean delete(Long categoryNo, String id) {
		return categoryDao.delete(categoryNo, id);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return categoryDao.getList(id);
	}

}
