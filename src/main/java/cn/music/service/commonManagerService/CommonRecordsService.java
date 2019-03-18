package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.CommonRecordsDao;
import cn.music.entity.CommonRecords;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/7.
 */
public class CommonRecordsService {
    private CommonRecordsDao commonRecordsDao = new CommonRecordsDao();
    //得到学生打卡记录
    public List<Map<String,Object>> getAllCommonRecords(int currentPageNo, int pageSize, String da1, String da2, String commonClassName, String studentName){
        return commonRecordsDao.getAllCommonRecords(currentPageNo,pageSize,da1,da2,commonClassName,studentName);
    }

    //得到学生打卡记录总数
    public int getCommonRecordsCount(String da1,String da2,String commonClassName,String studentName){
        return commonRecordsDao.getCommonRecordsCount(da1,da2,commonClassName,studentName);
    }

    //查询学生班级是否对应
    public List<Map<String,Object>> getParentsPhoneAndCommonClassName(String parentsPhone,String commonClassName){
        return commonRecordsDao.getParentsPhoneAndCommonClassName(parentsPhone,commonClassName);
    }

    //增加打卡记录
    public int addCommonRecords(CommonRecords commonRecords){
        return commonRecordsDao.addCommonRecords(commonRecords);
    }
}
