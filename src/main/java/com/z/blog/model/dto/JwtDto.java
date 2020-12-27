package com.z.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * token传输数据实体
 * @author Mr zhang
 * @date 2020/12/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class JwtDto {

    private String id;

    private String username;

}
