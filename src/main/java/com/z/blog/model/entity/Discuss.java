package com.z.blog.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Discuss implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "discuss_id", type = IdType.AUTO)
    private Integer discussId;

    /**
     * 评论论内容
     */
    private String discussContent;

    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 文章编号
     */
    private Integer aid;


}
