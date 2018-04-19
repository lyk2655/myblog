package com.linyk3.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.linyk3.myblog.dao.CommentDao;
import com.linyk3.myblog.model.Comment;

import java.util.List;


@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired
    private SensitiveService sensitiveService;

    public void addCommet(Comment comment){
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveService.filter(comment.getContent()));
        commentDao.insertComment(comment);
    }

    public List<Comment> getCommentsByArticleId(int articleId){
        return commentDao.selectCommentsByArticleId(articleId);
    }

    public int getCommentsCount(int articleId){
        return commentDao.getCommentCountByArticleId(articleId);
    }

    public void deleteComment(int commentId){
        commentDao.updateStatus(commentId,1);
    }

    public Comment getCommentById(int commentId){
        return commentDao.seletById(commentId);
    }
}