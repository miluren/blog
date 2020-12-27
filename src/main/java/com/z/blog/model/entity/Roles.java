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
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;


}
