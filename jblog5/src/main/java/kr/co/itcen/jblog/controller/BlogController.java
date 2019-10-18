package kr.co.itcen.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.security.Auth;
import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.PostService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets)(?!images).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private PostService postService;
	
	@RequestMapping(value= {"", "/{cNo}", "/{cNo}/{pNo}"}, method=RequestMethod.GET)
	public String main(@PathVariable("id") String id,
			@PathVariable Optional<Long> cNo,
			@PathVariable Optional<Long> pNo,
			ModelMap modelMap, Model model) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pNo.isPresent()) {
			postNo = pNo.get();
			categoryNo = cNo.get();
		} else if(cNo.isPresent()) {
			categoryNo = cNo.get();
		}
		
		modelMap.putAll(blogService.getAll(id, categoryNo, postNo));
		modelMap.addAttribute("blogVo",blogService.get(id));
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) {
		model.addAttribute("blogVo", blogService.get(id));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String id,
			@RequestParam(value="logo", required=false) MultipartFile multipartFile,
			@RequestParam(value="title", required=false) String title) {
		String logo = blogService.restore(multipartFile);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(id);
		blogVo.setTitle(title);
		blogVo.setLogo(logo);
		blogService.update(blogVo);
		return "redirect:/{id}";
	}
	
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.GET)
	public String adminCategory(@PathVariable("id") String id, ModelMap model) {
		
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) {
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		model.addAttribute("categoryList", categoryList);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id,
			@ModelAttribute PostVo vo,
			HttpServletRequest request) {
		String category = request.getParameter("category");
		Long categoryNo = Long.parseLong(category);
		vo.setCategoryNo(categoryNo);
		postService.insert(vo);
		return "redirect:/{id}";
	}
	
}
