package com.linyk3.myblog.dao;

import org.apache.ibatis.annotations.*;

import com.linyk3.myblog.model.Comment;

import java.util.List;


@Mapper
public interface CommentDao {
    String TABLE_NAEM = " comment ";
    String INSERT_FIELDS = " article_id, content, created_date, user_id, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into",TABLE_NAEM,"(",INSERT_FIELDS,") values (#{articleId}," +
            "#{content},#{createdDate},#{userId},#{status})"})
    int insertComment(Comment comment);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where id=#{id}"})
    Comment seletById(int id);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"order by id desc limit #{offset},#{limit}"})
    List<Comment> selectLatestComments(@Param("offset") int offset, @Param("limit") int limit);

    @Select({"select",SELECT_FIELDS,"from",TABLE_NAEM,"where article_id = #{articleId} " +
            "order by created_date desc"})
    List<Comment> selectCommentsByArticleId(@Param("articleId") int articleId);

    @Select({"select count(id) from",TABLE_NAEM,"where article_id = #{articleId}"})
    int getCommentCountByArticleId(@Param("articleId") int articleId);

    @Update({"update",TABLE_NAEM,"set status = #{status} where id = #{commendId}"})
    void updateStatus(@Param("commendId") int commendId, @Param("status") int status);

    @Delete({"delete from",TABLE_NAEM,"where id=#{id}"})
    void deleteById(int id);
}
