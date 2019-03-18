package cn.music.dao.matchDao;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.MatchClassInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchClassInfoDao extends BaseDao {
    //获取班级老师信息
    public List<Map<String,Object>> getAllMatchClassInfo(int currentPageNo, int pageSize,String matchClassName, String teaManName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "SELECT m.matchClassId,m.matchClassName,t.teaManName,m.matchCost,m.matchClassDate,m.matchSpan,m.matchStatus FROM matchclassinfo m INNER JOIN teamaninfo t ON m.teaManId=t.teaManId where `matchClassName` LIKE '%"+matchClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%' limit ?,?";
        super.rs = super.executeSql(sql,(currentPageNo-1)*pageSize,pageSize);
        try {
            while (rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("matchClassId",rs.getInt(1));
                map.put("matchClassName",rs.getString(2));
                map.put("teaManName",rs.getString(3));
                map.put("matchCost",rs.getString(4));
                map.put("matchClassDate",rs.getDate(5));
                map.put("matchSpan",rs.getInt(6));
                map.put("matchStatus",rs.getInt(7));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //得到集训班级数量
    public int getMatchClassInfoCount(String matchClassName, String teaManName){
        int count = 0;
        String sql = "SELECT count(1) FROM matchclassinfo m INNER JOIN teamaninfo t ON m.teaManId=t.teaManId where `matchClassName` LIKE '%"+matchClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%'";
        super.rs = super.executeSql(sql);
        try {
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return count;
    }

    //修改集训班级打卡状态
    public int updateMatchClassStatus(MatchClassInfo matchClassName){
        String sql = "update matchClassInfo set matchStatus=? where matchClassId=?";
        return super.executeUpdate(sql,matchClassName.getMatchStatus(),matchClassName.getMatchClassId());
    }


    //根据班级名字查询是否有该班级
    public String getMatchClassName(String matchClassName){
        String sql ="select matchclassname from matchclassinfo where matchclassname=?";
        Object [] params = {matchClassName};
        rs= super.executeSql(sql, params);
        try {
            while(rs.next()){
                return rs.getString("matchClassName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    //增加集训班级
    public int addMatchClass(MatchClassInfo matchClassInfo) {
        String sql = "insert into matchClassInfo value(null,?,?,?,?,?,?)";
        Object[] obj = {matchClassInfo.getMatchClassName(), matchClassInfo.getTeaManId(), matchClassInfo.getMatchCost(), matchClassInfo.getMatchClassDate(), matchClassInfo.getMatchSpan(),matchClassInfo.getMatchStatus()};
        return super.executeUpdate(sql, obj);
    }

    //根据其他信息修改班级表和老师编号
    public int updateMatchClass(MatchClassInfo matchClassInfo) {
        String sql = "update matchClassInfo set matchClassName=?,teaManId=?,matchCost=?,matchClassDate=?,matchSpan=? where matchClassId=?";
        Object[] params = {matchClassInfo.getMatchClassName(),matchClassInfo.getTeaManId(),matchClassInfo.getMatchCost(),matchClassInfo.getMatchClassDate(),matchClassInfo.getMatchSpan(),matchClassInfo.getMatchClassId()};
        return super.executeUpdate(sql, params);
    }

    //获取班级及老师的相关信息
    public MatchClassInfo getMatchClassById(int matchClassId){
        MatchClassInfo matchClassInfo = null;
        String sql ="select * from matchClassInfo where matchclassId=?";
        Object [] params = {matchClassId};
        rs = super.executeSql(sql, params);
        try {
            if(rs.next()){
                matchClassInfo = new MatchClassInfo();
                matchClassInfo.setMatchClassId(rs.getInt("matchClassId"));
                matchClassInfo.setMatchClassName(rs.getString("matchClassName"));
                matchClassInfo.setTeaManId(rs.getInt("teaManId"));
                matchClassInfo.setMatchCost(rs.getBigDecimal("matchCost"));
                matchClassInfo.setMatchClassDate(rs.getDate("matchClassDate"));
                matchClassInfo.setMatchSpan(rs.getInt("matchSpan"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchClassInfo;

    }
}
