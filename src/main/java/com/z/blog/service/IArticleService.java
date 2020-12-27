package com.z.blog.service;

import com.z.blog.model.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
public interface IArticleService extends IService<Article> {

    /**
     * 发布文章
     * @param article 文章
     * @return 是否发布成功
     */
    Boolean publishArticle(Article article);

    /**
     * 检查文章是否存在
     * @param article 文章
     * @return 是否
     */
    Boolean isExist(Article article);

    /**
     * 查找所有文章，由近到元
     * @return 文章列表
     */
    List<Article> getAllArticle();

    /**
     * 根据用户编号查询文章
     * @param userId 用户编号
     * @return 文章列表
     */
    List<Article> getArticleByUserId(String userId);

    /**
     * 根据标签查询文章
     * @param label 标签
     * @return 文章列表
     */
    List<Article> getArticleByLabel(String label);

    /**
     * 通过文章编号获取文章详情
     * @param articleId 文章编号
     * @return 文章详情
     */
    Article getArticleInfo(String articleId);
}
