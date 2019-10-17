package kr.co.itcen.jblog.controller.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.itcen.jblog.dto.JSONResult;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller("CategoryApiController")
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@ResponseBody
	@RequestMapping("/addcategory")
	public JSONResult addCategory(@RequestBody CategoryVo categoryVo, HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		categoryVo.setBlogId(userVo.getId());
		Boolean exist = categoryService.add(categoryVo);
		
		return JSONResult.success(exist);
	}
	
	@ResponseBody
	@RequestMapping("/getlist")
	public List<CategoryVo> getList(HttpSession session) {
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		String id = userVo.getId();
		List<CategoryVo> list = categoryService.getCategoryList(id);
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public JSONResult delete(@RequestBody Map<String, Long> map, HttpSession session) {
		// 접근제어
		UserVo userVo = (UserVo)session.getAttribute("authUser");
		
		Boolean exist = categoryService.delete(map.get("categoryNo"), userVo.getId());
		return JSONResult.success(exist);
	}
}