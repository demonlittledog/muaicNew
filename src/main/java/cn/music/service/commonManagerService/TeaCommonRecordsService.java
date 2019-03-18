package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.TeaCommonRecordsDao;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/9.
 */
public class TeaCommonRecordsService {
    private TeaCommonRecordsDao teaCommonRecordsDao = new TeaCommonRecordsDao();
    //得到老师打卡记录
    public List<Map<String,Object>> getAllTeaCommonRecords(int currentPageNo, int pageSize, String da1, String da2, String commonClassName, String teaManName){
        return teaCommonRecordsDao.getAllTeaCommonRecords(currentPageNo,pageSize,da1,da2,commonClassName,teaManName);
    }

    //得到老师打卡记录总数
    public int getTeaCommonRecordsCount(String da1,String da2,String commonClassName,String teaManName){
        return teaCommonRecordsDao.getTeaCommonRecordsCount(da1,da2,commonClassName,teaManName);
    }

    //老师打卡
    public int addTeaCommonRecords(int teaManId,int commonClassId){
        return teaCommonRecordsDao.addTeaCommonRecords(teaManId,commonClassId);
    }
}
