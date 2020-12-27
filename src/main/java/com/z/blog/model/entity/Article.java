package com.z.blog.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文章名字
     */
    @NotBlank
    private String articleName;

    /**
     * 文章内容
     */
    @NotBlank
    private String articleContent;

    /**
     * 点赞次数
     */
    private Integer good;

    /**
     * 观看次数
     */
    private Integer watches;

    /**
     * 封面图片
     */
    private String picture;

    /**
     * 赞赏图片
     */
    private String admire;

    /**
     * 用户编号
     */
    @NotNull
    private Integer userId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
