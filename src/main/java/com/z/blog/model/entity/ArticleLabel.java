package com.z.blog.model.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class ArticleLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章编号
     */
    private Integer aId;

    /**
     * 标签编号
     */
    private Integer lId;


}
