package ssg.com.houssg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.houssg.dao.CommentDao;
import ssg.com.houssg.dto.CommentDto;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentDao dao;

    public void saveComment(CommentDto comment) {
        dao.saveComment(comment);
    }

    public void updateComment(CommentDto comment) {
        dao.updateComment(comment);
    }

    public void deleteComment(int cmtId) {
        dao.deleteComment(cmtId);
    }

    public List<CommentDto> getCommentsByBoardId(int boardId) {
        return dao.getCommentsByBoardId(boardId);
    }
}
