package com.z.blog.service;

import com.z.blog.model.entity.Label;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
public interface ILabelService extends IService<Label> {

    /**
     * 通过标签获取标签的编号
     * @param labels
     * @return
     */
    List<Label> getLabelsId(List<String> labels);

    /**
     * 获取是所有的标签
     * @return
     */
    List<Label> getAll();
}
