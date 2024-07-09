package com.demo.servicelmpl;

import com.demo.javaBean.Fengxiang;
import com.demo.mapper.FengxiangMapper;
import com.demo.serveice.FengxiangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FengxiangServicelmpl implements FengxiangService {

    @Autowired
    private FengxiangMapper fengxiangMapper;
    @Override
    public List<Fengxiang> selectFengxiang() {
        List<Fengxiang> list=fengxiangMapper.selectFengxiang();
        return list;
    }

    @Override
    public void updateFengxiang(Fengxiang fengxiang) {
        fengxiangMapper.updateFengxiang(fengxiang);
    }
    @Override
    public void addFengxiang(Fengxiang fengxiang) {
        fengxiangMapper.addFengxiang(fengxiang);
    }

    @Override
    public void deleteFengxiang(Fengxiang fengxiang) {
        fengxiangMapper.deleteFengxiang(fengxiang);
    }

    @Override
    public List<Fengxiang> searchId(String id) {
        List<Fengxiang> list=fengxiangMapper.searchId(id);
        return list;
    }
}
