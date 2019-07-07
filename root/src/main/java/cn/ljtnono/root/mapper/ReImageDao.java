package cn.ljtnono.root.mapper;

import cn.ljtnono.root.entity.ReImage;
import cn.ljtnono.root.entity.ReImageExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReImageDao {
    long countByExample(ReImageExample example);

    int deleteByExample(ReImageExample example);

    int deleteByPrimaryKey(String id);

    int insert(ReImage record);

    int insertSelective(ReImage record);

    List<ReImage> selectByExample(ReImageExample example);

    ReImage selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ReImage record, @Param("example") ReImageExample example);

    int updateByExample(@Param("record") ReImage record, @Param("example") ReImageExample example);

    int updateByPrimaryKeySelective(ReImage record);

    int updateByPrimaryKey(ReImage record);
}