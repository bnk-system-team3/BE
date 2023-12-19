package ssg.com.houssg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.houssg.dto.CommentDto;

@Mapper
@Repository
public interface CommentDao {

    void saveComment(CommentDto comment);

    void updateComment(CommentDto comment);

    void deleteComment(int cmtId);

    List<CommentDto> getCommentsByBoardId(int boardId);
}
