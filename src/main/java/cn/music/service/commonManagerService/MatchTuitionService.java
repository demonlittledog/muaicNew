package cn.music.service.commonManagerService;

import cn.music.dao.commonManagerDao.MatchTuitionDao;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2018/11/11.
 */
public class MatchTuitionService {
    private MatchTuitionDao matchTuitionDao = new MatchTuitionDao();
    //得到学生集训学费信息
    public List<Map<String,Object>> getAllMatchTuition(int currentPageNo, int pageSize, String da1, String da2, String matchClassName, String studentName){
        return matchTuitionDao.getAllMatchTuition(currentPageNo,pageSize,da1,da2,matchClassName,studentName);
    }

    //得到学生集训学费信息数量
    public int getAllMatchTuitionCount(String da1, String da2, String matchClassName, String studentName){
        return matchTuitionDao.getAllMatchTuitionCount(da1,da2,matchClassName,studentName);
    }
}
