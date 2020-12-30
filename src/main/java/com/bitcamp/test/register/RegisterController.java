package com.bitcamp.test.register;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {
	SqlSession sqlSession;	
	public SqlSession getSqlSession() {
		return sqlSession;
	}
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	@RequestMapping("/login")
	public String login() {
		return "register/login";
	}
	@RequestMapping(value="/loginOk", method=RequestMethod.POST)
	public ModelAndView loginOk(RegisterVO vo, HttpSession ses) {	
		//                     추상클래스
		RegisterDaoImp dao  = sqlSession.getMapper(RegisterDaoImp.class);
		
		RegisterVO resultVO = dao.login(vo);
		ModelAndView mav = new ModelAndView();
		if(resultVO == null) {//로그인실패
			mav.setViewName("redirect:login");
		}else {
			ses.setAttribute("userid", resultVO.getUserid());
			ses.setAttribute("username", resultVO.getUsername());
			ses.setAttribute("logStatus", "Y");
			mav.setViewName("redirect:/");
		}
		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession s) {
		s.invalidate();		
		return "home";
	}
}




