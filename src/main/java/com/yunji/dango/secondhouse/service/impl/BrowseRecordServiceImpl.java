package com.yunji.dango.secondhouse.service.impl;

import com.yunji.dango.secondhouse.dao.BrowseRecordDao;
import com.yunji.dango.secondhouse.model.BrowseRecord;
import com.yunji.dango.secondhouse.service.BrowseRecordService;
import com.yunji.dango.shiro.service.impl.BaseServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrowseRecordServiceImpl extends BaseServiceImpl<BrowseRecord> implements BrowseRecordService{
    @Autowired
    private BrowseRecordDao browseRecordDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(browseRecordDao);
    }

    @Override
    public int insertModel(BrowseRecord browseRecord){
        return browseRecordDao.insertModel(browseRecord);
    }

    @Override
    public List<BrowseRecord> findModelByCondition(Map<String, String> map){
        return browseRecordDao.findModelByCondition(map);
    }

    @Override
    public int updateModel(BrowseRecord browseRecord){
        return browseRecordDao.updateModel(browseRecord);
    }

    @Override
    public BrowseRecord findOneModel(Map<String, String> map){
        return browseRecordDao.findOneModel(map);
    }

    @Override
    public int deleteModel(BrowseRecord browseRecord){
        return browseRecordDao.deleteModel(browseRecord);
    }
}
