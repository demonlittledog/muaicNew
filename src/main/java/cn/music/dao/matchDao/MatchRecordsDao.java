package cn.music.dao.matchDao;

import cn.music.dao.baseDao.BaseDao;

import java.sql.SQLException;
import java.util.*;

public class MatchRecordsDao extends BaseDao {
    //根据classID 获取MatchRecords 的信息 今日签到
    public List<Map<String,Object>> getRecordsNow(int matchClassId,int currentPageNo, int pageSize, String studentName){
        String sql ="SELECT s.studentName,s.parentsPhone,mi.matchClassName,tmi.teaManName,mi.matchCost,mi.matchSpan,m.matchCardTime,m.matchStatus FROM matchrecords m inner join studentinfo s on s.studentId=m.studentId  INNER JOIN matchclassinfo mi ON mi.`matchClassId`=m.`matchClassId` INNER JOIN teaManInfo tmi on mi.teaManId=tmi.teaManId  WHERE TO_DAYS(matchCardTime) = TO_DAYS(NOW()) AND m.matchClassId=? AND `studentName` LIKE '%"+studentName+"%' limit ?,?";
        Object [] params = {matchClassId,(currentPageNo-1)*pageSize,pageSize};
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        super.rs= super.executeSql(sql, params);
        try {
            while (rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("studentName",rs.getString(1));
                map.put("parentsPhone",rs.getString(2));
                map.put("matchClassName",rs.getString(3));
                map.put("teaManName",rs.getString(4));
                map.put("matchCost",rs.getBigDecimal(5));
                map.put("matchSpan",rs.getInt(6));
                map.put("matchCardTime",rs.getTimestamp(7));
                map.put("matchStatus",rs.getInt(8));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    //根据classID 获取MatchRecords 的信息 今日签到数量
    public int getRecordsNowCount(int matchClassId,String studentName){
        int count = 0;
        String sql = "SELECT count(1) FROM matchrecords m inner join studentinfo s on s.studentId=m.studentId  INNER JOIN matchclassinfo mi ON mi.`matchClassId`=m.`matchClassId` INNER JOIN teaManInfo tmi on mi.teaManId=tmi.teaManId  WHERE TO_DAYS(matchCardTime) = TO_DAYS(NOW()) AND m.matchClassId=? AND `studentName` LIKE '%"+studentName+"%'";
        super.rs = super.executeSql(sql,matchClassId);
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

    //打卡签到
    public int addMatchRecords(int studentId, int matchClassId, Date date, int matchStatus){
        String sql="INSERT INTO matchrecords VALUES(null,?,?,?,?)";
        Object[]params={studentId,matchClassId,date,matchStatus};
        return super.executeUpdate(sql, params);
    }

    //查看历史签到
    public List<Map<String,Object>> getHistoryMatchRecords(int currentPageNo,int pageSize,String da1,String da2,String matchClassName,String studentName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "SELECT s.`studentName`,s.`parentsPhone`,mci.`matchClassName`,tmi.`teaManName`,mci.`matchCost`,mci.`matchSpan`,mc.`matchCardTime`,mc.`matchStatus` FROM studentinfo  s INNER JOIN matchrecords mc ON mc.`studentId`=s.`studentId` INNER JOIN matchclassinfo mci ON mci.`matchClassId`=mc.`matchClassId` INNER JOIN teamaninfo tmi ON tmi.`teaManId`=mci.`teaManId` where mc.matchCardTime>='"+da1+"' and mc.matchCardTime<='"+da2+"' and `matchClassName` LIKE '%"+matchClassName+"%' AND `studentName` LIKE '%"+studentName+"%' limit ?,?";
        super.rs = super.executeSql(sql, (currentPageNo-1)*pageSize,pageSize);
        try {
            while (rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("studentName",rs.getString(1));
                map.put("parentsPhone",rs.getString(2));
                map.put("matchClassName",rs.getString(3));
                map.put("teaManName",rs.getString(4));
                map.put("matchCost",rs.getBigDecimal(5));
                map.put("matchSpan",rs.getInt(6));
                map.put("matchCardTime",rs.getTimestamp(7));
                map.put("matchStatus",rs.getInt(8));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }


    //查看历史签到数量
    public int getHistoryMatchRecordsCount(String da1,String da2,String matchClassName,String studentName){
        int count = 0;
        String sql = "SELECT count(1) FROM studentinfo  s INNER JOIN matchrecords mc ON mc.`studentId`=s.`studentId` INNER JOIN matchclassinfo mci ON mci.`matchClassId`=mc.`matchClassId` INNER JOIN teamaninfo tmi ON tmi.`teaManId`=mci.`teaManId` where mc.matchCardTime>='"+da1+"' and mc.matchCardTime<='"+da2+"' and `matchClassName` LIKE '%"+matchClassName+"%' AND `studentName` LIKE '%"+studentName+"%'";
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

}
