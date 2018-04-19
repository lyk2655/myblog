package com.linyk3.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linyk3.myblog.dao.ArticleTagDao;
import com.linyk3.myblog.dao.TagDao;
import com.linyk3.myblog.model.ArticleTag;
import com.linyk3.myblog.model.Tag;

import java.util.List;


@Service
public class TagService {
    @Autowired
    private TagDao tagDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    public Tag selectByName(String name){
        return tagDao.selectByName(name);
    }

    public List<Tag> getAllTag(){
        return tagDao.selectAll();
    }

    public List<Tag> getTagByArticleId(int articleId){
        return articleTagDao.selectByArticleId(articleId);
    }

    public int addTag(Tag tag){
        return tagDao.insertTag(tag)>0?tag.getId():0;
    }

    public int addArticleTag(ArticleTag articleTag){
        return articleTagDao.insertArticleTag(articleTag);
    }

    public void updateCount(int tagId,int count){
        tagDao.updateCount(tagId,count);
    }
}
