package com.yunji.dango.secondhouse.service.impl;

import com.yunji.dango.secondhouse.dao.CollectionDao;
import com.yunji.dango.secondhouse.model.Collection;
import com.yunji.dango.secondhouse.service.CollectionService;
import com.yunji.dango.shiro.service.impl.BaseServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectionServiceImpl extends BaseServiceImpl<Collection> implements CollectionService{
    @Autowired
    private CollectionDao collectionDao;

    @Override
    public void setBaseDao(){
        super.setBaseDao(collectionDao);
    }

    @Override
    public List<Collection> findModelByCondition(Map<String, String> map){
        return collectionDao.findModelByCondition(map);
    }

    @Override
    public int insertModel(Collection collection){
        return collectionDao.insertModel(collection);
    }

    @Override
    public int deleteModel(Collection collection){
        return collectionDao.deleteModel(collection);
    }
}
