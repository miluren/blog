package com.z.blog.service.impl;

import com.z.blog.model.entity.ArticleLabel;
import com.z.blog.mapper.ArticleLabelMapper;
import com.z.blog.service.IArticleLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements IArticleLabelService {


    @Autowired
    private ArticleLabelMapper articleLabelMapper;

    @Override
    public Integer addArticleLabel(Integer labelId, Integer articleId) {
        ArticleLabel articleLabel = new ArticleLabel();
        articleLabel.setLId(labelId);
        articleLabel.setAId(articleId);
        return articleLabelMapper.insert(articleLabel);
    }

}
