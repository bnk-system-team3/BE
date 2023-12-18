package ssg.com.houssg.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.houssg.dto.BoardDto;

@Mapper
@Repository
public interface BoardDao {

	void saveBoard(BoardDto board);
	
	void updateBoard(BoardDto board);
	
	String findUser(BoardDto board);
}
