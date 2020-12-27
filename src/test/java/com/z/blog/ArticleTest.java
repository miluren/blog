package com.z.blog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.z.blog.mapper.ArticleMapper;
import com.z.blog.model.entity.Article;
import com.z.blog.service.IArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章测试类
 * @author Mr zhang
 * @date 2020/12/19
 */
@SpringBootTest
public class ArticleTest {

    @Autowired
    private IArticleService iArticleService;

    @Autowired
    private ArticleMapper articleMapper;

    public static void main(String[] args) {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    @Test
    public void isExist(){
        Article article = new Article();
        article.setArticleName("sss");
        article.setArticleContent("sssaaa");
        Boolean exist = iArticleService.isExist(article);
        System.out.println(exist);
    }

    @Test
    public void getAll() {
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.orderByDesc("create_time");
        System.out.println(articleMapper.selectList(articleQueryWrapper));

    }
}
