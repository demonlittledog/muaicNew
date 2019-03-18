package cn.music.dao.commonManagerDao;

import cn.music.dao.baseDao.BaseDao;
import cn.music.entity.CommonTuition;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/8.
 */
public class MatchTuitionDao extends BaseDao{
    /*//修改剩余学费
    public int updateMatchTuition(CommonTuition commonTuition){
        String sql = "update commonTuition set `commonSurplus`=? where `studentId`=? and `commonClassId`=?";
        return super.executeUpdate(sql,commonTuition.getCommonSurplus(),commonTuition.getStudentId(),commonTuition.getCommonClassId());
    }*/

    //得到学生集训学费信息
    public List<Map<String,Object>> getAllMatchTuition(int currentPageNo, int pageSize, String da1, String da2, String matchClassName, String studentName){
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        String sql = "SELECT si.`studentName`,si.`parentsPhone`,mci.`matchClassName`,tmi.`teaManName`,mci.`matchCost`,mci.`matchSpan`,mtu.`matchSurplus`,mtu.`matchAllCost` FROM studentInfo si INNER JOIN matchTuition mtu ON si.`studentId`=mtu.`studentId` INNER JOIN matchClassInfo mci ON mci.`matchClassId`=mtu.`matchClassId` INNER JOIN `teaManInfo` tmi ON tmi.`teaManId`=mci.`teaManId` WHERE mci.matchStatus=1 and mci.matchClassDate>='"+da1+"' AND mci.matchClassDate<='"+da2+"' AND `matchClassName` LIKE '%"+matchClassName+"%' AND `studentName` LIKE '%"+studentName+"%'  limit ?,?";
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
                map.put("matchSurplus",rs.getBigDecimal(7));
                map.put("matchAllCost",rs.getBigDecimal(8));
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            super.closeAll();
        }
        return list;
    }

    //得到学生集训学费信息数量
    public int getAllMatchTuitionCount(String da1, String da2, String matchClassName, String studentName){
        int count = 0;
        String sql = "SELECT count(1) FROM studentInfo si INNER JOIN matchTuition mtu ON si.`studentId`=mtu.`studentId` INNER JOIN matchClassInfo mci ON mci.`matchClassId`=mtu.`matchClassId` INNER JOIN `teaManInfo` tmi ON tmi.`teaManId`=mci.`teaManId` WHERE mci.matchStatus=1 and mci.matchClassDate>='"+da1+"' AND mci.matchClassDate<='"+da2+"' AND `matchClassName` LIKE '%"+matchClassName+"%' AND `studentName` LIKE '%"+studentName+"%'";
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
