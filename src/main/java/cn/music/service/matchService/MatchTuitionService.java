package cn.music.service.matchService;

import cn.music.dao.matchDao.MatchTuitionDao;

import java.math.BigDecimal;

public class MatchTuitionService {
    private MatchTuitionDao matchTuitionDao = new MatchTuitionDao();

    //根据学生id班级id查询集训学费表
    public boolean getMatchTuition(int studentId,int matchClassId){
        return matchTuitionDao.getMatchTuition(studentId,matchClassId);
    }

    //如果有信息 update
    public int  updateTuition(BigDecimal matchCost, int studentId, int matchClassId){
        return matchTuitionDao.updateTuition(matchCost,studentId,matchClassId);
    }

    //如果没有信息 则 insert
    public int insertTuition(int studentId,int matchClassId,BigDecimal matchCost){
        return matchTuitionDao.insertTuition(studentId,matchClassId,matchCost);
    }

    //根据班级id查询学费
    public BigDecimal getMatchCost(int matchClassId){
        return matchTuitionDao.getMatchCost(matchClassId);
    }
}
