package com.z.blog.model.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * 文章表单
 * @author Mr zhang
 * @date 2020/12/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleForm {

    private static final long serialVersionUID = 1L;

    /**
     * 文章名字
     */
    @NotBlank(message = "文章名字不为空")
    @Max(value = 255, message = "标题不能过长")
    private String articleName;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不为空")
    private String articleContent;

    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不为空")
    @Max(value = Integer.MAX_VALUE,message = "用户编号不能过大")
    @Min(value = Integer.MIN_VALUE,message = "用户编号不能过小")
    private Integer userId;

    /**
     * 封面照片地址
     */
    private String pictureAddr;

    /**
     * 赞赏图片地址
     */
    private String admireAddr;

    /**
     * 标签
     */
    @NotEmpty(message = "标签不为空")
    private List<String> labels;

}
