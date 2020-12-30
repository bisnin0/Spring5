package com.bitcamp.test.answer;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AnswerController {
	@Autowired
	SqlSession sqlSession;
	@Autowired
	DataSourceTransactionManager transactionManager;
	
	@RequestMapping("/answerList")
	public ModelAndView allList() {
		AnswerDaoImp dao = sqlSession.getMapper(AnswerDaoImp.class);
		List<AnswerVO> list = dao.allRecord();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("answer/list");
		return mav;
	}
	@RequestMapping("/answerWrite")
	public String write() {
		return "answer/write";
	}
	@RequestMapping(value="/answerWriteOk", method=RequestMethod.POST)
	public ModelAndView writeOk(AnswerVO vo, HttpSession ses) {
		vo.setUserid((String)ses.getAttribute("userid"));
		
		AnswerDaoImp dao = sqlSession.getMapper(AnswerDaoImp.class);
		int result = dao.recordInsert(vo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:answerList");
		return mav;
	}
	@RequestMapping("/answerView")
	public ModelAndView view(int no) {
		AnswerDaoImp dao = sqlSession.getMapper(AnswerDaoImp.class);
		AnswerVO vo = dao.recordSelect(no);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("vo", vo);
		mav.setViewName("answer/view");
		return mav;
	}
	@RequestMapping("/replyWriteForm")
	public String replyWrite(int no, Model model) {
		model.addAttribute("no", no);
		return "answer/replyForm";
	}
	@RequestMapping(value="/replyWriteFormOk", method=RequestMethod.POST)
	public ModelAndView replyWriteOk(AnswerVO vo, HttpSession ses) {
		vo.setUserid((String)ses.getAttribute("userid"));
		
		//TransactionStatus객체를 구하여야 한다.
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = transactionManager.getTransaction(def);
		
		//vo.no -->원글번호
		//vo.subject, vo.content --> 답글
		AnswerDaoImp dao = sqlSession.getMapper(AnswerDaoImp.class);		
		
		//1. 원글의 ref, step, lvl을 선택
		AnswerVO optVo= dao.optionSelect(vo.getNo());
		
		try {
			//원글의 lvl보다 큰 레코드를 업데이트
			dao.lvlUpdate(optVo);
			//답글을 쓰기한다.			
			vo.setRef(optVo.getRef());
			vo.setStep(optVo.getStep()+1);
			vo.setLvl(optVo.getLvl()+1);
			int result = dao.replyInsert(vo);
			
			transactionManager.commit(status);
		}catch(Exception e) {
			transactionManager.rollback(status);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:answerList");
		return mav;
	}
}














