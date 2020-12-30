package com.bitcamp.test.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	SqlSession sqlSession;

	public SqlSession getSqlSession() {
		return sqlSession;
	}
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@RequestMapping("/boardList")
	public ModelAndView boardAllRecord() {
		
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		//String sql = sqlSession.getConfiguration().getMappedStatement("boardAllRecord").getBoundSql(searchWord).getSql();
		
		List<BoardVO> list = dao.boardAllRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("board/list");
		return mav;
	}
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board/boardWrite";
	}
	@RequestMapping(value="/boardWriteOk", method=RequestMethod.POST)
	public ModelAndView boardWriteOk(BoardVO vo, HttpServletRequest r, HttpSession s) {
		vo.setIp(r.getRemoteAddr());
		vo.setUserid((String)s.getAttribute("userid"));
		
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		int result = dao.boardInsert(vo);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("board/result");
		}
		return mav;
	}
	@RequestMapping(value = "/community/imageUpload", method = RequestMethod.POST)
	public void communityImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {
		System.out.println(111111);
	    OutputStream out = null;
	    PrintWriter printWriter = null;
	    response.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    System.out.println(222222);
	    try{
	 
	        String fileName = upload.getOriginalFilename();
	        System.out.println("fileName->"+fileName);
	        byte[] bytes = upload.getBytes();
	        String uploadPath = request.getSession().getServletContext().getRealPath("/upload");//저장경로
	 
	        out = new FileOutputStream(new File(uploadPath, fileName));
	        out.write(bytes);
	        String callback = request.getParameter("CKEditorFuncNum");
	 
	        printWriter = response.getWriter();
	        String fileUrl = "upload/" + fileName;//url경로
	        System.out.println("fileUrl->"+fileUrl);
	        
	        printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("
	                + callback
	                + ",'"
	                + fileUrl
	                + "','이미지를 업로드 하였습니다.'"
	                + ")</script>");
	        printWriter.flush();
	 
	    }catch(IOException e){
	        e.printStackTrace();
	    } finally {
	        try {
	            if (out != null) {
	                out.close();
	            }
	            if (printWriter != null) {
	                printWriter.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    return;
	}

	@RequestMapping("/boardView")
	public ModelAndView boardView(int no) {
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		
		dao.hitCount(no);
		BoardVO vo = dao.boardSelect(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("board/view");
		return mav;
	}
	@RequestMapping("/boardEdit")
	public ModelAndView boardEdit(int no) {
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		
		BoardVO vo = dao.boardSelect(no);
		ModelAndView mav = new ModelAndView();		
		mav.setViewName("board/edit");
		mav.addObject("vo", vo);
		
		return mav;
	}
	@RequestMapping(value="/editOk", method=RequestMethod.POST)
	public ModelAndView boardEditOk(BoardVO vo, HttpSession s) {
		vo.setUserid((String)s.getAttribute("userid"));
		
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		
		int result = dao.boardUpdate(vo);
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {//업데이트
			mav.addObject("no", vo.getNo());
			mav.setViewName("redirect:boardView");
		}else {
			mav.setViewName("board/result");
		}
		return mav;
	}
	@RequestMapping("/boardDel")
	public ModelAndView boardDel(int no, HttpSession ses) {
		BoardDaoImp dao = sqlSession.getMapper(BoardDaoImp.class);
		
		int result = dao.boardDelete(no, (String)ses.getAttribute("userid"));
		
		ModelAndView mav = new ModelAndView();
		if(result>0) {//삭제
			mav.setViewName("redirect:boardList");
		}else {
			mav.setViewName("board/result");
		}
		return mav;
	}
	
}






