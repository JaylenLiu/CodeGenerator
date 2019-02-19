package cn.jaylen.codegenerator.service;

import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.entity.SysKeyname;
import cn.jaylen.codegenerator.entity.SysKeyvalue;

import java.util.List;

/**
 * @author ljl
 * @create 2018-08-08 11:21
 * @desc 键值管理service
 **/
public interface KeyService {
    Message getKeyValuesByKeyname(String keyname);
    Message getKeyvaluesByKeynameId(Long keynameId, String searchKey);

    Message saveKeyname(SysKeyname keyname);
    Message deleteKeyname(Long[] ids);
    Message updateKeyname(SysKeyname keyname);

    Message saveKeyvalue(SysKeyvalue keyvalue);
    Message deleteKeyvalues(Long[] ids);
    Message updateKeyvalue(SysKeyvalue keyvalue);

    Message getKeynameTree();

    List<SysKeyname> getAllKeynames();
}
