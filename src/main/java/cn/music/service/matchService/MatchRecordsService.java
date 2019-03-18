package cn.music.service.matchService;

import cn.music.dao.matchDao.MatchRecordsDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MatchRecordsService {
    private MatchRecordsDao matchRecordsDao = new MatchRecordsDao();
    //根据classID 获取MatchRecords 的信息 今日签到
    public List<Map<String,Object>> getRecordsNow(int matchClassId, int currentPageNo, int pageSize, String studentName){
        return matchRecordsDao.getRecordsNow(matchClassId,currentPageNo,pageSize,studentName);
    }

    //根据classID 获取MatchRecords 的信息 今日签到数量
    public int getRecordsNowCount(int matchClassId,String studentName){
        return matchRecordsDao.getRecordsNowCount(matchClassId,studentName);
    }

    //打卡签到
    public int addMatchRecords(int studentId, int matchClassId, Date date, int matchStatus){
        return matchRecordsDao.addMatchRecords(studentId,matchClassId,date,matchStatus);
    }

    //查看历史签到
    public List<Map<String,Object>> getHistoryMatchRecords(int currentPageNo,int pageSize,String da1,String da2,String matchClassName,String studentName){
        return matchRecordsDao.getHistoryMatchRecords(currentPageNo,pageSize,da1,da2,matchClassName,studentName);
    }

    //查看历史签到数量
    public int getHistoryMatchRecordsCount(String da1,String da2,String matchClassName,String studentName){
        return matchRecordsDao.getHistoryMatchRecordsCount(da1,da2,matchClassName,studentName);
    }
}
