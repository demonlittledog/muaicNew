package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.CommonRecords;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/7.
 */
public class CommonRecordsDao extends BaseDao{
    //得到学生打卡记录
    public List<Map<String,Object>> getAllCommonRecords(int currentPageNo,int pageSize,String da1,String da2,String commonClassName,String studentName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "select si.`studentName`,si.`parentsPhone`,ct.`classTypeName`,cci.`commonClassName`,tmi.`teaManName`,cci.`commonCost`,cci.`commonSpan`,cr.`commonCardTime`,cr.`commonStatus` from studentInfo si INNER join commonRecords cr on si.`studentId`=cr.`studentId` INNER join commonClassInfo cci ON cci.`commonClassId`=cr.`commonClassId` INNER join `classType` ct on ct.`classTypeId`=cci.`classTypeId` INNER join `teaManInfo` tmi on tmi.`teaManId`=cci.`teaManId` where cr.commonCardTime>='"+da1+"' and cr.commonCardTime<='"+da2+"' and `commonClassName` LIKE '%"+commonClassName+"%' AND `studentName` LIKE '%"+studentName+"%' limit ?,?";
        super.rs = super.executeSql(sql, (currentPageNo-1)*pageSize,pageSize);
        try {
            while (rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("studentName",rs.getString(1));
                map.put("parentsPhone",rs.getString(2));
                map.put("classTypeName",rs.getString(3));
                map.put("commonClassName",rs.getString(4));
                map.put("teaManName",rs.getString(5));
                map.put("commonCost",rs.getBigDecimal(6));
                map.put("commonSpan",rs.getInt(7));
                map.put("commonCardTime",rs.getTimestamp(8));
                map.put("commonStatus",rs.getInt(9));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //得到学生打卡记录总数
    public int getCommonRecordsCount(String da1,String da2,String commonClassName,String studentName){
        int count = 0;
        String sql = "select count(1) from studentInfo si INNER join commonRecords cr on si.`studentId`=cr.`studentId` INNER join commonClassInfo cci ON cci.`commonClassId`=cr.`commonClassId` INNER join `classType` ct on ct.`classTypeId`=cci.`classTypeId` INNER join `teaManInfo` tmi on tmi.`teaManId`=cci.`teaManId` where cr.commonCardTime>='"+da1+"' and cr.commonCardTime<='"+da2+"' and `commonClassName` LIKE '%"+commonClassName+"%' AND `studentName` LIKE '%"+studentName+"%'";
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

    //查询学生班级是否对应
    public List<Map<String,Object>> getParentsPhoneAndCommonClassName(String parentsPhone,String commonClassName){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        String sql = "SELECT st.`studentId`,cci.`commonClassId`,cci.`commonCost`,ct.`commonSurplus` FROM `studentInfo` st INNER JOIN `commonTuition` ct ON st.`studentId`=ct.`studentId` INNER JOIN `commonClassInfo` cci ON cci.`commonClassId`=ct.`commonClassId` WHERE st.`parentsPhone`=? AND cci.`commonClassName`=?";
        super.rs = super.executeSql(sql,parentsPhone,commonClassName);
        try {
            while (rs.next()){
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("studentId",rs.getInt(1));
                map.put("commonClassId",rs.getInt(2));
                map.put("commonCost",rs.getBigDecimal(3));
                map.put("commonSurplus",rs.getBigDecimal(4));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }


    //增加打卡记录
    public int addCommonRecords(CommonRecords commonRecords){
        String sql = "insert into `commonRecords` values (null,?,?,?,?)";
        return super.executeUpdate(sql,commonRecords.getStudentId(),commonRecords.getCommonClassId(),commonRecords.getCommonCardTime(),commonRecords.getCommonStatus());
    }
}
