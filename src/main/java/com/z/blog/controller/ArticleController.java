package com.z.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.z.blog.model.entity.Article;
import com.z.blog.model.entity.Label;
import com.z.blog.model.form.ArticleForm;
import com.z.blog.model.vo.ResultVo;
import com.z.blog.service.IArticleLabelService;
import com.z.blog.service.IArticleService;
import com.z.blog.service.ILabelService;
import com.z.blog.utils.JwtUtil;
import com.z.blog.utils.SysUtiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/article")
@Transactional(rollbackFor = Exception.class)
public class ArticleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${uploudfilepath}")
    private String path;

    @Autowired
    IArticleService iArticleService;
    @Autowired
    ILabelService iLabelService;
    @Autowired
    IArticleLabelService iArticleLabelService;


    /**
     * 发布文章
     * @return ResultVo
     */
    @PostMapping("/publish")
    public ResultVo publish(@Valid @RequestBody ArticleForm articleForm) {
        System.out.println(articleForm);
        Article article = new Article();
        article.setArticleName(articleForm.getArticleName());
        article.setArticleContent(articleForm.getArticleContent());
        article.setGood(0);
        article.setWatches(0);
        article.setPicture(articleForm.getPictureAddr());
        article.setAdmire(articleForm.getAdmireAddr());
        article.setUserId(articleForm.getUserId());
        if (iArticleService.isExist(article)) {
            return ResultVo.error("文章已经存在");
        }

        Boolean aBoolean = iArticleService.publishArticle(article);
        if (aBoolean) {
            QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
            articleQueryWrapper.eq("article_name",articleForm.getArticleName());
            Article one = iArticleService.getOne(articleQueryWrapper);
            List<Label> labelsId = iLabelService.getLabelsId(articleForm.getLabels());
            for (Label l: labelsId) {
                iArticleLabelService.addArticleLabel(l.getId(), one.getId());
            }
        } else {
            return ResultVo.error("发布文章出错，请重试");
        }


        return ResultVo.ok();
    }

    /**
     * 上传文件
     * @param file 文件
     * @return 返回结果
     */
    @RequestMapping("/upload")
    public ResultVo uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && !SysUtiles.isImage(fileName)){
            return ResultVo.error("请传入正确的图片格式(限JPG、PNG)");
        }
        fileName = UUID.randomUUID()+"_"+fileName;


        try {
            file.transferTo(new File(path + fileName));
            logger.info("上传成功");
        } catch (IOException e) {
            logger.info("上传异常");
            return ResultVo.error("上传失败，请重新上传图片");
        }
        return ResultVo.ok(fileName);
    }

    @GetMapping("/deleteImg")
    public ResultVo deleteImg(@RequestParam("imgUrl") String imgUrl) {
        File file = new File(path + imgUrl);
        if (!file.isDirectory()) {
            file.delete();
        }
        return ResultVo.ok("删除成功");
    }

    /**
     * 获取所有的文章根据创建时间降序
     * @return ResultVo
     */
    @GetMapping("/getAllArticle")
    public ResultVo getAllArticle() {
        List<Article> allArticle = iArticleService.getAllArticle();
        return ResultVo.ok(allArticle);
    }

    /**
     * 归档
     * @return ResultVo
     */
    @GetMapping("/archive")
    public ResultVo Archive() {
        // 获取token中的信息
        Map<String, Object> tokenInfo = JwtUtil.getTokenInfo();

        String userId = tokenInfo.get("userId").toString();
        return ResultVo.ok(iArticleService.getArticleByUserId(userId));
    }

    /**
     * 通过标签获取文章列表
     * @return ResultVo
     */
    @GetMapping("/getArticleByLabel")
    public ResultVo getArticleByLabel(String label) {
        if (StringUtils.isBlank(label)) {
            return ResultVo.error("标签不能为空");
        }
        return ResultVo.ok(iArticleService.getArticleByLabel(label));
    }

    /**
     * 根据文章编号查询文章
     * @param articleId
     * @return 文章详情
     */
    @GetMapping("/getArticleInfo")
    public ResultVo getArticleInfo(String articleId) {
        return ResultVo.ok(iArticleService.getArticleInfo(articleId));
    }

}
