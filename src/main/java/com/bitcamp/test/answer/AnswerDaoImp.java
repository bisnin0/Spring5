package com.bitcamp.test.answer;

import java.util.List;

public interface AnswerDaoImp {	
	public List<AnswerVO> allRecord();
	public int recordInsert(AnswerVO vo);
	public AnswerVO recordSelect(int no);
	public AnswerVO optionSelect(int no);
	public int lvlUpdate(AnswerVO vo);
	public int replyInsert(AnswerVO vo);
}
