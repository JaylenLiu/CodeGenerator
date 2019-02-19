package cn.jaylen.codegenerator.service;

import cn.jaylen.codegenerator.base.BaseService;
import cn.jaylen.codegenerator.entity.AgileComponent;

import java.util.List;

public interface AgileComponentService extends BaseService<AgileComponent> {
    List<AgileComponent> selectAll();
    int deleteByIDs(Long[] ids);
    AgileComponent selectByEntityName(Long entityId, String fieldName);
}
