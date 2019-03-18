package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.CommonClassInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/11.
 */
public class CommonClassInfoDao extends BaseDao{
    //查询打卡班级是否冻结
    public int getCommonStatus(String commonClassName){
        int count=-1;
        String sql = "select commonStatus from commonClassInfo where commonClassName=?";
        super.rs = super.executeSql(sql,commonClassName);
        try {
            if(rs.next()){
                count = rs.getInt("commonStatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return count;
    }

    //得到普通班级信息
    public List<Map<String,Object>> getAllCommonClassInfo(int currentPageNo, int pageSize, String da1, String da2, String commonClassName, String teaManName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "select cci.commonClassId,cci.commonClassName,ct.classTypeName,tmi.teaManName,cci.commonCost,cci.commonSum,cci.commonClassDate,cci.commonWeek,cci.commonTime,cci.commonSpan,cci.studentNum,cci.commonStatus from teaManInfo tmi INNER JOIN commonClassInfo cci on cci.teaManId=tmi.teaManId INNER JOIN classType ct on ct.classTypeId=cci.classTypeId where cci.commonClassDate>='"+da1+"' and cci.commonClassDate<='"+da2+"' and `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%' limit ?,?";
        super.rs = super.executeSql(sql, (currentPageNo-1)*pageSize,pageSize);
        try {
            while (rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("commonClassId",rs.getInt(1));
                map.put("commonClassName",rs.getString(2));
                map.put("classTypeName",rs.getString(3));
                map.put("teaManName",rs.getString(4));
                map.put("commonCost",rs.getBigDecimal(5));
                map.put("commonSum",rs.getInt(6));
                map.put("commonClassDate",rs.getDate(7));
                map.put("commonWeek",rs.getInt(8));
                map.put("commonTime",rs.getTime(9));
                map.put("commonSpan",rs.getInt(10));
                map.put("studentNum",rs.getInt(11));
                map.put("commonStatus",rs.getInt(12));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //得到普通班级信息数量
    public int getAllCommonClassInfoCount(String da1, String da2, String commonClassName, String teaManName){
        int count = 0;
        String sql = "select count(1) from teaManInfo tmi INNER JOIN commonClassInfo cci on cci.teaManId=tmi.teaManId INNER JOIN classType ct on ct.classTypeId=cci.classTypeId where cci.commonClassDate>='"+da1+"' and cci.commonClassDate<='"+da2+"' and `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName` LIKE '%"+teaManName+"%'";
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

    //增加普通班级信息
    public int addCommonClassInfo(CommonClassInfo commonClassInfo){
        String sql = "insert into commonClassInfo values(null,?,?,?,?,?,?,?,?,?,?,?)";
        return super.executeUpdate(sql,commonClassInfo.getCommonClassName(),commonClassInfo.getClassTypeId(),commonClassInfo.getTeaManId(),commonClassInfo.getCommonCost(),commonClassInfo.getCommonSum(),commonClassInfo.getCommonClassDate(),commonClassInfo.getCommonWeek(),commonClassInfo.getCommonTime(),commonClassInfo.getCommonSpan(),commonClassInfo.getStudentNum(),commonClassInfo.getCommonStatus());
    }

    //查询班级是否存在
    public String getCommonClassNameByCommonClassName(String commonClassName){
        String sql = "select commonClassName from commonClassInfo where commonClassName=?";
        super.rs = super.executeSql(sql,commonClassName);
        String ccn = null;
        try {
            if(rs.next()){
                ccn = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return ccn;
    }

    //修改班级打卡状态
    public int updateCommonClassStatus(CommonClassInfo commonClassInfo){
        String sql = "update commonClassInfo set commonStatus=? where commonClassId=?";
        return super.executeUpdate(sql,commonClassInfo.getCommonStatus(),commonClassInfo.getCommonClassId());
    }

    //查询班级信息通过id
    public CommonClassInfo getCommonClassInfoById(int id){
        CommonClassInfo commonClassInfo = null;
        String sql="SELECT * from commonclassinfo where commonClassId=?";
        super.rs = executeSql(sql,id);
        try {
            if(rs.next()){
                commonClassInfo = new CommonClassInfo();
                commonClassInfo.setCommonClassId(rs.getInt(1));
                commonClassInfo.setCommonClassName(rs.getString(2));
                commonClassInfo.setClassTypeId(rs.getInt(3));
                commonClassInfo.setTeaManId(rs.getInt(4));
                commonClassInfo.setCommonCost(rs.getBigDecimal(5));
                commonClassInfo.setCommonSum(rs.getInt(6));
                commonClassInfo.setCommonClassDate(rs.getDate(7));
                commonClassInfo.setCommonWeek(rs.getInt(8));
                commonClassInfo.setCommonTime(rs.getTime(9));
                commonClassInfo.setCommonSpan(rs.getInt(10));
                commonClassInfo.setStudentNum(rs.getInt(11));
                commonClassInfo.setCommonStatus(rs.getInt(12));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return commonClassInfo;
    }

    //修改班级信息
    public int updateCommonClassInfo(CommonClassInfo commonClassInfo){
        String sql = "update commonClassInfo set commonClassName=?,classTypeId=?,teaManId=?,commonCost=?,commonSum=?,commonClassDate=?,commonWeek=?,commonTime=?,commonSpan=? where commonClassId=?";
        return super.executeUpdate(sql,commonClassInfo.getCommonClassName(),commonClassInfo.getClassTypeId(),commonClassInfo.getTeaManId(),commonClassInfo.getCommonCost(),commonClassInfo.getCommonSum(),commonClassInfo.getCommonClassDate(),commonClassInfo.getCommonWeek(),commonClassInfo.getCommonTime(),commonClassInfo.getCommonSpan(),commonClassInfo.getCommonClassId());
    }

    //修改班级人数
    public int updateCommonClassStudentNum(int sum,int commonClassId){
        String sql = "update commonClassInfo set studentNum=? where commonClassId=?";
        return super.executeUpdate(sql,sum,commonClassId);
    }

    //查询班级人数
    public int getStudentNum(int commonClassId){
        int count = 0;
        String sql = "select studentNum from commonClassInfo where commonClassId=?";
        super.rs = super.executeSql(sql,commonClassId);
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


    //得到缴费金额
    public CommonClassInfo getMoney(int commonClassId){
        CommonClassInfo commonClassInfo = null;
        String sql = "select commonCost,commonSum from commonClassInfo where commonClassId=?";
        super.rs = super.executeSql(sql,commonClassId);
        try {
            if(rs.next()){
                commonClassInfo = new CommonClassInfo();
                commonClassInfo.setCommonCost(rs.getBigDecimal(1));
                commonClassInfo.setCommonSum(rs.getInt(2));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return commonClassInfo;
    }

    //得到自己班级信息
    public List<Map<String,Object>> getMyCommonClassInfo(String teaManName,int currentPageNo, int pageSize,String commonClassName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "select cci.commonClassId,cci.commonClassName,ct.classTypeName,cci.commonCost,cci.commonSum,cci.commonClassDate,cci.commonWeek,cci.commonTime,cci.commonSpan,cci.studentNum from teaManInfo tmi INNER JOIN commonClassInfo cci on cci.teaManId=tmi.teaManId INNER JOIN classType ct on ct.classTypeId=cci.classTypeId where `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName`=? and cci.commonStatus=1 limit ?,?";
        super.rs = super.executeSql(sql, teaManName,(currentPageNo-1)*pageSize,pageSize);
        try {
            while (rs.next()){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("commonClassId",rs.getInt(1));
                map.put("commonClassName",rs.getString(2));
                map.put("classTypeName",rs.getString(3));
                map.put("commonCost",rs.getBigDecimal(4));
                map.put("commonSum",rs.getInt(5));
                map.put("commonClassDate",rs.getDate(6));
                map.put("commonWeek",rs.getInt(7));
                map.put("commonTime",rs.getTime(8));
                map.put("commonSpan",rs.getInt(9));
                map.put("studentNum",rs.getInt(10));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //得到自己班级信息数
    public int getMyCommonClassInfoCount(String teaManName,String commonClassName){
        int count = 0;
        String sql = "select count(1) from teaManInfo tmi INNER JOIN commonClassInfo cci on cci.teaManId=tmi.teaManId INNER JOIN classType ct on ct.classTypeId=cci.classTypeId where `commonClassName` LIKE '%"+commonClassName+"%' AND `teaManName`=? and cci.commonStatus=1";
        super.rs = super.executeSql(sql,teaManName);
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


    //得到自己的班级
    public List<CommonClassInfo> getMyCommonClass(String teaManName){
        List<CommonClassInfo> list = new ArrayList<CommonClassInfo>();
        String sql = "select cci.commonClassId,cci.commonClassName from teaManInfo tmi INNER JOIN commonClassInfo cci on cci.teaManId=tmi.teaManId where teaManName=?";
        CommonClassInfo commonClassInfo = null;
        super.rs = super.executeSql(sql,teaManName);
        try {
            while (rs.next()){
                commonClassInfo = new CommonClassInfo();
                commonClassInfo.setCommonClassId(rs.getInt(1));
                commonClassInfo.setCommonClassName(rs.getString(2));
                list.add(commonClassInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }
}
