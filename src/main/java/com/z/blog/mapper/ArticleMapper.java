package com.z.blog.mapper;

import com.z.blog.model.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 检查文章是否存在
     * @param article 文章
     * @return 是否
     */
    Integer isExist(Article article);



}
