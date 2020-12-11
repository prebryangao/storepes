package com.example.demo.service;


import com.example.demo.bean.TbItem;

import com.example.demo.utils.LayuiTableResult;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface TbItemService {
    /**
     * 根据商品id查询指定商品信息
     * @param itemId 商品id
     * @return 指定商品id对象
     */
    TbItem findTbItemById(Long itemId);

    /**
     * 分页显示商品
     * @param page 当前页
     * @param limit 每一页显示条数
     * @return layui需要的json数据格式
     */
    LayuiTableResult getItemByPage(Integer page, Integer limit);






    /**
     * 根据id查询数据库得到商品信息 吧商品信息存入到 excel表对象里面去 并且返回这个对象
     * @param ids
     * @return
     */
    HSSFWorkbook getexportExcel(List<Long> ids);

    /**
     * 吧图片存入到nginx里面（多图片上传）。并且把图片地址封装到TaotaoResult里面
     * @param file
     * @return
     */

}
