package com.example.demo.mapper;



import com.example.demo.bean.TbItem;
import com.example.demo.vo.TbItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;
@Mapper
public interface TbItemMapper {
    /**
     * 根据商品id查询商品信息
     * @param itemId 商品id
     * @return
     */
    TbItem findTbItemById(Long itemId);

    /**
     * 获取商品总记录条数
     * @return
     */
    int getTbItemCount();

    /**
     * 分页查询商品信息
     * @param index 当前索引
     * @param limit 每页显示条数
     * @return 页面需要的对象
     */
    List<TbItemVo> getItemByPage(@Param("index") Integer index, @Param("limit") Integer limit);



    List<TbItemVo> findTbItemVoByIds(@Param("ids") List<Long> ids);


}
