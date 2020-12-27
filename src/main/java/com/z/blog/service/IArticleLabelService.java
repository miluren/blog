package com.z.blog.service;

import com.z.blog.model.entity.ArticleLabel;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
public interface IArticleLabelService extends IService<ArticleLabel> {

    /**
     * 添加 文章对应的标签号
     * @param labelId 标签号
     * @param articleId 文章编号
     * @return
     */
    Integer addArticleLabel(Integer labelId, Integer articleId);

}
