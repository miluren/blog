package com.z.blog.controller;

import com.z.blog.model.vo.ResultVo;
import com.z.blog.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr zhang
 * @since 2020-12-16
 */
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private ILabelService iLabelService;

    @RequestMapping("/getAll")
    public ResultVo getAll() {
        return ResultVo.ok(iLabelService.getAll());
    }


}