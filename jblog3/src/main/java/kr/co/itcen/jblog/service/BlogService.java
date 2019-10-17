package kr.co.itcen.jblog.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.itcen.jblog.exception.FileUploadExcetpion;
import kr.co.itcen.jblog.repository.BlogDao;
import kr.co.itcen.jblog.repository.CategoryDao;
import kr.co.itcen.jblog.repository.PostDao;
import kr.co.itcen.jblog.vo.BlogVo;
import kr.co.itcen.jblog.vo.CategoryVo;
import kr.co.itcen.jblog.vo.PostVo;

@Service
public class BlogService {
	private static final String SAVE_PATH = "/uploads";
	private static final String URL_PREFIX = "/images";

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private PostDao postDao;

	@Autowired
	private CategoryDao categoryDao;

	public void insert(BlogVo vo) {
		blogDao.insert(vo);
	}

	public BlogVo get(String id) {
		return blogDao.get(id);
	}

	public Map<? extends String, ? extends Object> getAll(String id, Long categoryNo, Long postNo) {
		BlogVo blogVo = get(id);
		PostVo postVo = view(id, categoryNo);
		List<PostVo> postList = getPostList(id, categoryNo);
		List<CategoryVo> categoryList = getCategoryList(id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("blogVo", blogVo);
		map.put("postVo", postVo);
		map.put("postList", postList);
		map.put("categoryList", categoryList);
		map.put("currentPostNo", postNo);
		map.put("currentCategoryNo", categoryNo);

		return map;
	}

	private PostVo view(String id, Long categoryNo) {
		return postDao.view(id, categoryNo);
	}

	private List<PostVo> getPostList(String id, Long categoryNo) {
		return postDao.getList(id, categoryNo);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return categoryDao.getList(id);
	}

	public int getListCount(Long categoryNo) {
		return postDao.getListCount(categoryNo);
	}

	public void update(BlogVo blogVo) {
		blogDao.update(blogVo);
	}

	public String restore(MultipartFile multipartFile) {
		String logo = "";

		try {
			if (multipartFile == null) {
				return logo;
			}

			String originalFilename = multipartFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf('.')+1);
			String saveFilename = generateSaveFilename(extName);
			long fileSize = multipartFile.getSize();

			System.out.println("######" + originalFilename);
			System.out.println("######" + saveFilename);
			System.out.println("######" + fileSize);

			byte[] fileData = multipartFile.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + saveFilename);
			os.write(fileData);
			os.close();

			logo = URL_PREFIX + "/" + saveFilename;

		} catch (IOException e) {
			throw new FileUploadExcetpion();
		}
		return logo;
	}
	
	private String generateSaveFilename(String extName) {
		String filename = "";
		
		Calendar calendar = Calendar.getInstance();
		filename += calendar.get(Calendar.YEAR);
		filename += calendar.get(Calendar.MONTH);
		filename += calendar.get(Calendar.DATE);
		filename += calendar.get(Calendar.HOUR);
		filename += calendar.get(Calendar.MINUTE);
		filename += calendar.get(Calendar.SECOND);
		filename += calendar.get(Calendar.MILLISECOND);
		filename += ("." + extName);
		
		return filename;
	}

}
