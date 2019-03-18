package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.CommonTuition;
import cn.music.service.commonManagerService.CommonTuitionService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/8.
 */
public class CommonTuitionDao extends BaseDao{
    //修改剩余学费
    public int updateCommonTuition(CommonTuition commonTuition){
        String sql = "update commonTuition set `commonSurplus`=? where `studentId`=? and `commonClassId`=?";
        return super.executeUpdate(sql,commonTuition.getCommonSurplus(),commonTuition.getStudentId(),commonTuition.getCommonClassId());
    }

    //得到学生学费信息
    public List<Map<String,Object>> getAllCommonTuition(int currentPageNo, int pageSize, String da1, String da2, String commonClassName, String studentName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "SELECT si.`studentName`,si.`parentsPhone`,ct.`classTypeName`,cci.`commonClassName`,tmi.`teaManName`,cci.`commonCost`,cci.`commonSpan`,ctu.`commonSurplus`,ctu.`commonAllCost` FROM studentInfo si INNER JOIN commonTuition ctu ON si.`studentId`=ctu.`studentId` INNER JOIN commonClassInfo cci ON cci.`commonClassId`=ctu.`commonClassId` INNER JOIN `classType` ct ON ct.`classTypeId`=cci.`classTypeId` INNER JOIN `teaManInfo` tmi ON tmi.`teaManId`=cci.`teaManId` WHERE cci.commonStatus=1 and cci.commonClassDate>='"+da1+"' AND cci.commonClassDate<='"+da2+"' AND `commonClassName` LIKE '%"+commonClassName+"%' AND `studentName` LIKE '%"+studentName+"%'  limit ?,?";
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
                map.put("commonSurplus",rs.getBigDecimal(8));
                map.put("commonAllCost",rs.getBigDecimal(9));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //得到学生学费信息数量
    public int getAllCommonTuitionCount(String da1, String da2, String commonClassName, String studentName){
        int count = 0;
        String sql = "SELECT count(1) FROM studentInfo si INNER JOIN commonTuition ctu ON si.`studentId`=ctu.`studentId` INNER JOIN commonClassInfo cci ON cci.`commonClassId`=ctu.`commonClassId` INNER JOIN `classType` ct ON ct.`classTypeId`=cci.`classTypeId` INNER JOIN `teaManInfo` tmi ON tmi.`teaManId`=cci.`teaManId` WHERE cci.commonStatus=1 and cci.commonClassDate>='"+da1+"' AND cci.commonClassDate<='"+da2+"' AND `commonClassName` LIKE '%"+commonClassName+"%' AND `studentName` LIKE '%"+studentName+"%'";
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

    //给班级添加学生
    public int addStudentToCommonClass(CommonTuition commonTuition){
        String sql="insert into commonTuition values(null,?,?,?,?)";
        return super.executeUpdate(sql,commonTuition.getStudentId(),commonTuition.getCommonClassId(),commonTuition.getCommonSurplus(),commonTuition.getCommonAllCost());
    }

    //得到某班级的学生缴费信息
    public CommonTuition getCommonTuition(int commonClassId,int studentId){
        String sql = "select commonSurplus,commonAllCost from commonTuition where commonClassId=? and studentId=?";
        CommonTuition commonTuition = null;
        super.rs = super.executeSql(sql,commonClassId,studentId);
        try {
            if(rs.next()){
                commonTuition = new CommonTuition();
                commonTuition.setCommonSurplus(rs.getBigDecimal(1));
                commonTuition.setCommonAllCost(rs.getBigDecimal(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return commonTuition;
    }

    //缴费
    public int payMoney(int commonClassId,int studentId,BigDecimal money){
        CommonTuition commonTuition = this.getCommonTuition(commonClassId,studentId);
        BigDecimal commonSurplus = commonTuition.getCommonSurplus();
        BigDecimal commonAllCost = commonTuition.getCommonAllCost();
        commonSurplus = commonSurplus.add(money);
        commonAllCost = commonAllCost.add(money);
        String sql = "update commonTuition set commonSurplus= ?,commonAllCost=? where commonClassId=? and studentId=?";
        return super.executeUpdate(sql,commonSurplus,commonAllCost,commonClassId,studentId);
    }

    //在缴费表删除学生
    public int deleteStudent(int commonClassId ,int studentId){
        String sql="delete from commonTuition where commonClassId=? and studentId=?";
        return super.executeUpdate(sql,commonClassId,studentId);
    }
}
