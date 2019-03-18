package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/9.
 */
public class TeaCommonRecordsDao extends BaseDao{
    //得到老师打卡记录
    public List<Map<String,Object>> getAllTeaCommonRecords(int currentPageNo, int pageSize, String da1, String da2, String commonClassName, String teaManName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "select tmi.`teaManName`,tmi.`teaManPhone`,ct.`classTypeName`,cci.`commonClassName`,cci.`commonCost`,cci.`commonSpan`,tcr.`teaCommonCardTime`,tcr.`teaCommonStatus` from `teaManInfo` tmi  INNER join teaCommonRecords tcr on tmi.`teaManId`=tcr.`teaManId` INNER join commonClassInfo cci ON cci.`commonClassId`=tcr.`commonClassId` INNER join `classType` ct on ct.`classTypeId`=cci.`classTypeId` where tcr.teaCommonCardTime>='"+da1+"' and tcr.teaCommonCardTime<='"+da2+"' and `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%' limit ?,?";
        super.rs = super.executeSql(sql, (currentPageNo-1)*pageSize,pageSize);
        try {
            while (rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("teaManName",rs.getString(1));
                map.put("teaManPhone",rs.getString(2));
                map.put("classTypeName",rs.getString(3));
                map.put("commonClassName",rs.getString(4));
                map.put("commonCost",rs.getBigDecimal(5));
                map.put("commonSpan",rs.getInt(6));
                map.put("teaCommonCardTime",rs.getTimestamp(7));
                map.put("teaCommonStatus",rs.getInt(8));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //得到老师打卡记录总数
    public int getTeaCommonRecordsCount(String da1,String da2,String commonClassName,String teaManName){
        int count = 0;
        String sql = "select count(1) from `teaManInfo` tmi  INNER join teaCommonRecords tcr on tmi.`teaManId`=tcr.`teaManId` INNER join commonClassInfo cci ON cci.`commonClassId`=tcr.`commonClassId` INNER join `classType` ct on ct.`classTypeId`=cci.`classTypeId` where tcr.teaCommonCardTime>='"+da1+"' and tcr.teaCommonCardTime<='"+da2+"' and `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%'";
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

    //老师打卡
    public int addTeaCommonRecords(int teaManId,int commonClassId){
        String sql = "insert into `teaCommonRecords` values (null,?,?,now(),1)";
        return super.executeUpdate(sql,teaManId,commonClassId);
    }
}
