package cn.ljtnono.root.mapper;

import cn.ljtnono.root.entity.ReTimeline;
import cn.ljtnono.root.entity.ReTimelineExample;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReTimelineDao {
    long countByExample(ReTimelineExample example);

    int deleteByExample(ReTimelineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ReTimeline record);

    int insertSelective(ReTimeline record);

    List<ReTimeline> selectByExample(ReTimelineExample example);

    ReTimeline selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ReTimeline record, @Param("example") ReTimelineExample example);

    int updateByExample(@Param("record") ReTimeline record, @Param("example") ReTimelineExample example);

    int updateByPrimaryKeySelective(ReTimeline record);

    int updateByPrimaryKey(ReTimeline record);
}