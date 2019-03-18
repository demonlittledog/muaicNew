package cn.music.service.matchService;

import cn.music.dao.matchDao.MatchClassInfoDao;
import cn.music.entity.MatchClassInfo;

import java.util.List;
import java.util.Map;

public class MatchClassInfoService {
    private MatchClassInfoDao matchClassInfoDao = new MatchClassInfoDao();

    //获取班级老师信息
    public List<Map<String,Object>> getAllMatchClassInfo(int currentPageNo, int pageSize, String matchClassName, String teaManName){
        return matchClassInfoDao.getAllMatchClassInfo(currentPageNo,pageSize,matchClassName,teaManName);
    }

    //得到集训班级数量
    public int getMatchClassInfoCount(String matchClassName, String teaManName){
        return matchClassInfoDao.getMatchClassInfoCount(matchClassName,teaManName);
    }

    //修改集训班级打卡状态
    public int updateMatchClassStatus(MatchClassInfo matchClassName){
        return matchClassInfoDao.updateMatchClassStatus(matchClassName);
    }

    //根据班级名字查询是否有该班级
    public String getMatchClassName(String matchClassName){
        return matchClassInfoDao.getMatchClassName(matchClassName);
    }


    //增加集训班级
    public int addMatchClass(MatchClassInfo matchClassInfo) {
        return matchClassInfoDao.addMatchClass(matchClassInfo);
    }

    //根据其他信息修改班级表和老师编号
    public int updateMatchClass(MatchClassInfo matchClassInfo) {
        return matchClassInfoDao.updateMatchClass(matchClassInfo);
    }

    //获取班级及老师的相关信息
    public MatchClassInfo getMatchClassById(int matchClassId){
        return matchClassInfoDao.getMatchClassById(matchClassId);
    }
}
