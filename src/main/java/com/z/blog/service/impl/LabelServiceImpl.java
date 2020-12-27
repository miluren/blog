package com.z.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.z.blog.model.entity.Label;
import com.z.blog.mapper.LabelMapper;
import com.z.blog.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Override
    public List<Label> getLabelsId(List<String> labels) {

        QueryWrapper<Label> wrapper = new QueryWrapper();
        for (int i = 0; i < labels.size(); i++) {
            wrapper.eq("name", labels.get(i));
            if (i < labels.size() - 1) {
                wrapper.or();
            }
        }

        List<Label> labels1 = labelMapper.selectList(wrapper);
        System.out.println(labels1);
        return labels1;
    }

    @Override
    public List<Label> getAll() {

        return labelMapper.selectList(null);
    }
}
