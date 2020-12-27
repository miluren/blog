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
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 角色编号
     */
    private Integer rid;


}
