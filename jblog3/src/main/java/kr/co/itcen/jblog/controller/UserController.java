package kr.co.itcen.jblog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;

import kr.co.itcen.jblog.service.BlogService;
import kr.co.itcen.jblog.service.CategoryService;
import kr.co.itcen.jblog.service.UserService;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsucess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		
		BlogVo blogVo = new BlogVo();
		blogVo.setBlogId(vo.getId());
		blogVo.setTitle(vo.getName() + "의 블로그 입니다.");
		blogVo.setLogo("spring-logo.png");
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setBlogId(vo.getId());
		categoryVo.setName("기타");
		categoryVo.setExplain("카테고리 설명을 설정하세요.");
		
		userService.join(vo);
		blogService.insert(blogVo);
		categoryService.insert(categoryVo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo();
		vo.setId(id);
		vo.setPassword(password);
		
		// 어플리케이션 어디서든지 spring Container(ApplicationContext)을 가져오는 방법
		ApplicationContext appCtxt = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		userService = appCtxt.getBean(UserService.class);
		
		UserVo authUser = userService.getUser(vo);
		if(authUser == null) {
			return "redirect:/user/login";
		}
		
		//session 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		
		return "main/index";
	}
	
}
