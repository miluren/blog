package com.z.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.z.blog.mapper.ArticleLabelMapper;
import com.z.blog.mapper.LabelMapper;
import com.z.blog.model.entity.Article;
import com.z.blog.mapper.ArticleMapper;
import com.z.blog.model.entity.ArticleLabel;
import com.z.blog.model.entity.Label;
import com.z.blog.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private ArticleLabelMapper articleLabelMapper;

    @Override
    public Boolean publishArticle(Article article) {
        int insert = articleMapper.insert(article);
        return insert==1;
    }

    @Override
    public Boolean isExist(Article article) {
        return articleMapper.isExist(article) == 1;
    }

    @Override
    public List<Article> getAllArticle() {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByAsc("create_time");
        return articleMapper.selectList(articleQueryWrapper);
    }

    @Override
    public List<Article> getArticleByUserId(String userId) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("user_id", userId);
        articleQueryWrapper.orderByAsc("create_time");
        return articleMapper.selectList(articleQueryWrapper);
    }

    @Override
    public List<Article> getArticleByLabel(String label) {
        QueryWrapper<Label> labelQueryWrapper = new QueryWrapper<>();
        labelQueryWrapper.eq("name", label);
        // 查询标签编号
        Label l = labelMapper.selectOne(labelQueryWrapper);
        if (l == null) {
            return null;
        }
        QueryWrapper<ArticleLabel> articleLabelQueryWrapper = new QueryWrapper<>();
        articleLabelQueryWrapper.eq("l_id", l.getId());
        // 查询文章编号
        List<ArticleLabel> articleLabels = articleLabelMapper.selectList(articleLabelQueryWrapper);
        if (articleLabels.isEmpty()) {
            return null;
        }

        List<Article> articleList = new ArrayList<>();
        for (ArticleLabel articleLabel : articleLabels) {
            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.eq("id", articleLabel.getAId());
            Article article = articleMapper.selectOne(articleQueryWrapper);
            articleList.add(article);
        }
        return articleList;
    }

    @Override
    public Article getArticleInfo(String articleId) {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("id", articleId);
        Article article = articleMapper.selectOne(articleQueryWrapper);
        if (article == null) {
            return null;
        }
        QueryWrapper<Article> update = new QueryWrapper<>();
        update.eq("id", article.getId());
        article.setWatches(article.getWatches()+1);
        articleMapper.update(article, update);

        return articleMapper.selectOne(articleQueryWrapper);
    }
}
