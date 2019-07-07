package cn.ljtnono.root.mapper;

import cn.ljtnono.root.entity.ReBlog;
import cn.ljtnono.root.entity.ReBlogExample;
import cn.ljtnono.root.entity.ReBlogWithBLOBs;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReBlogDao {
    long countByExample(ReBlogExample example);

    int deleteByExample(ReBlogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReBlogWithBLOBs record);

    int insertSelective(ReBlogWithBLOBs record);

    List<ReBlogWithBLOBs> selectByExampleWithBLOBs(ReBlogExample example);

    List<ReBlog> selectByExample(ReBlogExample example);

    ReBlogWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReBlogWithBLOBs record, @Param("example") ReBlogExample example);

    int updateByExampleWithBLOBs(@Param("record") ReBlogWithBLOBs record, @Param("example") ReBlogExample example);

    int updateByExample(@Param("record") ReBlog record, @Param("example") ReBlogExample example);

    int updateByPrimaryKeySelective(ReBlogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ReBlogWithBLOBs record);

    int updateByPrimaryKey(ReBlog record);
}