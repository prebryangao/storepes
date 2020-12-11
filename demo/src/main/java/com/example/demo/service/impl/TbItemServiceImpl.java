package com.example.demo.service.impl;


import com.example.demo.bean.TbItem;
import com.example.demo.mapper.TbItemMapper;
import com.example.demo.service.TbItemService;

import com.example.demo.utils.LayuiTableResult;
import com.example.demo.vo.TbItemVo;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Autowired
    private TbItemMapper tbItemMapper;


    @Override
    public TbItem findTbItemById(Long itemId) {
        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }

    @Override
    public LayuiTableResult getItemByPage(Integer page, Integer limit) {
        LayuiTableResult result = new LayuiTableResult();
        result.setCode(0);
        result.setMsg("");
        int count = tbItemMapper.getTbItemCount();
        result.setCount(count);
        List<TbItemVo> data = tbItemMapper.getItemByPage((page - 1) * limit, limit);
        result.setData(data);
        return result;
    }







    @Override
    public HSSFWorkbook getexportExcel(List<Long> ids) {
        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        //根据id查询商品信息 装到集合对象里面去
        List<TbItemVo> tbItemVoList = tbItemMapper.findTbItemVoByIds(ids);
        //excel表样式对象
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);//上边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THICK);//下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);//左边框
        style.setBorderRight(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);//右边框

        HSSFSheet sheet = workbook.createSheet("商品信息");// 创建工作表(Sheet)
        //sheet他可以设置宽高
        for (int i = 0; i < 6; i++) {
            sheet.setColumnWidth(i, 31 * 256);
        }

        //创建表头
        HSSFRow firstRow = sheet.createRow(0);// 创建行,从0开始
        HSSFCell cell0 = firstRow.createCell(0);
        cell0.setCellValue("商品编号");
        HSSFCell cell1 = firstRow.createCell(1);
        cell1.setCellValue("商品名称");
        HSSFCell cell2 = firstRow.createCell(2);
        cell2.setCellValue("商品类别");
        HSSFCell cell3 = firstRow.createCell(3);
        cell3.setCellValue("创建时间");
        HSSFCell cell4 = firstRow.createCell(4);
        cell4.setCellValue("商品价格");
        HSSFCell cell5 = firstRow.createCell(5);
        cell5.setCellValue("创建数量");
        //设置样式
        cell0.setCellStyle(style);
        cell1.setCellStyle(style);
        cell2.setCellStyle(style);
        cell3.setCellStyle(style);
        cell4.setCellStyle(style);
        cell5.setCellStyle(style);
        //日期对象转化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 总金额
        Long totalMoney = 0L;
        // 总数量
        Integer totalNum = 0;
        //循环遍历 商品集合对象
        for (int i = 0; i < tbItemVoList.size(); i++) {
            TbItemVo itemVo = tbItemVoList.get(i);
            HSSFRow row = sheet.createRow(i + 1);// 根据对象来创建行 从第1行开始
            HSSFCell hssfCell0 = row.createCell(0);
            hssfCell0.setCellValue(itemVo.getId());
            HSSFCell hssfCell1 = row.createCell(1);
            hssfCell1.setCellValue(itemVo.getTitle());
            HSSFCell hssfCell2 = row.createCell(2);

            //设置类别
            hssfCell2.setCellValue(itemVo.getTbItemCatName());

            HSSFCell hssfCell3 = row.createCell(3);
            //设置创建时间
            hssfCell3.setCellValue(format.format(itemVo.getCreated()));
            Long price = itemVo.getPrice();
            //计算出了总金额
            totalMoney += price;
            //设置金额
            HSSFCell hssfCell4 = row.createCell(4);
//            hssfCell4.setCellValue(getCalculationPrice(price));
            HSSFCell hssfCell5 = row.createCell(5);
            Integer num = itemVo.getNum();
            //计算出了总数量
            totalNum += num;
            //设置数量
            hssfCell5.setCellValue(num);
            hssfCell0.setCellStyle(style);
            hssfCell1.setCellStyle(style);
            hssfCell2.setCellStyle(style);
            hssfCell3.setCellStyle(style);
            hssfCell4.setCellStyle(style);
            hssfCell5.setCellStyle(style);
        }
        //设置总金额
        HSSFRow rowLast = sheet.createRow(tbItemVoList.size() + 1);
        HSSFCell secondLastCell = rowLast.createCell(4);
//        secondLastCell.setCellValue("总金额:" + getCalculationPrice(totalMoney));
        secondLastCell.setCellStyle(style);
        //设置总数量
        HSSFCell lastCell = rowLast.createCell(5);
        lastCell.setCellValue("总数量:" + totalNum);
        lastCell.setCellStyle(style);

        return workbook;
    }






}
