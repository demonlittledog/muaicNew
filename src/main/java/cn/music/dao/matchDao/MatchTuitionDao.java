package cn.music.dao.matchDao;

import cn.music.dao.baseDao.BaseDao;

import java.math.BigDecimal;
import java.sql.SQLException;

public class MatchTuitionDao extends BaseDao {
    //根据学生id班级id查询集训学费表
    public boolean getMatchTuition(int studentId,int matchClassId){
        boolean flag= false;
        String sql = "select matchTuiId from matchTuiTion where studentId=? and matchClassId=?";
        Object [] params = {studentId,matchClassId};
        rs= super.executeSql(sql, params);
        try {
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return flag;
    }
    //如果有信息 update
    public int  updateTuition(BigDecimal matchCost,int studentId,int matchClassId){
        String sql = "update matchTuiTion set matchSurplus=matchSurplus-? where studentId=? and matchClassId=?";
        Object [] params ={matchCost,studentId,matchClassId};
        return super.executeUpdate(sql, params);
    }

    //如果没有信息 则 insert
    public int insertTuition(int studentId,int matchClassId,BigDecimal matchCost){
        matchCost = matchCost.multiply(new BigDecimal(-1));
        String sql = "insert into matchTuition value(null,?,?,?,?)";
        Object [] params ={studentId,matchClassId,matchCost,0};
        return  super.executeUpdate(sql, params);
    }

    //根据班级id查询学费
    public BigDecimal getMatchCost(int matchClassId){
        String sql = "select matchCost from matchClassinfo where matchClassId=?";
        Object[] params = {matchClassId };
        rs = super.executeSql(sql, params);
        try {
            if(rs.next()) {
                return rs.getBigDecimal("matchCost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return null;
    }
}
