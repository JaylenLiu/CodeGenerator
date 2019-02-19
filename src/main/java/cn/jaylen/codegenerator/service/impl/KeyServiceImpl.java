package cn.jaylen.codegenerator.service.impl;

import ch.qos.logback.classic.Logger;
import cn.jaylen.codegenerator.common.Message;
import cn.jaylen.codegenerator.dao.SysKeynameMapper;
import cn.jaylen.codegenerator.dao.SysKeyvalueMapper;
import cn.jaylen.codegenerator.entity.SysKeyname;
import cn.jaylen.codegenerator.entity.SysKeyvalue;
import cn.jaylen.codegenerator.entity.example.SysKeynameExample;
import cn.jaylen.codegenerator.entity.example.SysKeyvalueExample;
import cn.jaylen.codegenerator.service.KeyService;
import cn.jaylen.codegenerator.util.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author ljl
 * @create 2018-08-08 11:22
 * @desc 键值管理serviceImpl
 **/
@Service
public class KeyServiceImpl implements KeyService {

    @Autowired
    SysKeynameMapper keynameMapper;

    @Autowired
    SysKeyvalueMapper keyvalueMapper;

    private Logger logger = (Logger) LoggerFactory.getLogger(KeyServiceImpl.class);

    @Override
    public Message getKeyValuesByKeyname(String keyname) {
        try{
            return Message.successMessage(keyvalueMapper.getKeyValuesByKeyname(keyname));
        } catch (Exception e) {
            logger.error("获取数据字典信息失败！", e);
            return Message.errorMessage(500, "获取数据字典信息失败！");
        }
    }

    @Override
    public Message getKeyvaluesByKeynameId(Long keynameId, String searchKey) {
        SysKeyvalueExample example = new SysKeyvalueExample();
        SysKeyvalueExample.Criteria criteria= example.createCriteria();
        criteria.andKeynameIdEqualTo(keynameId);
        if (!StringUtils.isNullOrEmpty(searchKey)){
            criteria.andKeyvalueLike("%" + searchKey + "%");
        }
        try{
            return Message.successMessage(keyvalueMapper.selectByExample(example));
        } catch (Exception e) {
            logger.error("获取数据字典信息失败！", e);
            return Message.errorMessage(500, "获取数据字典信息失败！");
        }
    }

    @Override
    public Message saveKeyname(SysKeyname keyname) {
        try{
            keyname.preInsert();
            return Message.successMessage(keynameMapper.insertSelective(keyname));
        } catch (Exception e){
            logger.error("保存键名信息失败！", e);
            return Message.errorMessage(500, "保存键名信息失败！");
        }
    }

    @Override
    public Message deleteKeyname(Long[] ids) {
        SysKeynameExample keynameExample = new SysKeynameExample();
        keynameExample.createCriteria().andIdIn(Arrays.asList(ids));

        SysKeyvalueExample keyvalueExample = new SysKeyvalueExample();
        keyvalueExample.createCriteria().andKeynameIdIn(Arrays.asList(ids));

        try{
            keyvalueMapper.deleteByExample(keyvalueExample);
            keynameMapper.deleteByExample(keynameExample);
            return Message.successMessage(1);
        }catch (Exception e) {
            logger.error("删除键名信息失败！", e);
            return Message.errorMessage(500, "删除键名信息失败！");
        }
    }

    @Override
    public Message updateKeyname(SysKeyname keyname) {
        try{
            return Message.successMessage(keynameMapper.updateByPrimaryKeySelective(keyname));
        }catch (Exception e){
            logger.error("更新键名信息失败！", e);
            return Message.errorMessage(500, "更新键名信息失败！");
        }
    }

    @Override
    public Message saveKeyvalue(SysKeyvalue keyvalue) {
        try{
            keyvalue.preInsert();
            return Message.successMessage(keyvalueMapper.insertSelective(keyvalue));
        } catch (Exception e){
            logger.error("保存键值信息失败！", e);
            return Message.errorMessage(500, "保存键值信息失败！");
        }
    }

    @Override
    public Message deleteKeyvalues(Long[] ids) {
        SysKeyvalueExample keyvalueExample = new SysKeyvalueExample();
        keyvalueExample.createCriteria().andIdIn(Arrays.asList(ids));
        try{
            keyvalueMapper.deleteByExample(keyvalueExample);
            return Message.successMessage(1);
        }catch (Exception e) {
            logger.error("删除键值信息失败！", e);
            return Message.errorMessage(500, "删除键值信息失败！");
        }
    }

    @Override
    public Message updateKeyvalue(SysKeyvalue keyvalue) {
        try{
            return Message.successMessage(keyvalueMapper.updateByPrimaryKeySelective(keyvalue));
        }catch (Exception e){
            logger.error("更新键值信息失败！", e);
            return Message.errorMessage(500, "更新键值信息失败！");
        }
    }

    @Override
    public Message getKeynameTree() {
        SysKeynameExample example = new SysKeynameExample();
        example.createCriteria().andIdIsNotNull();
        try{
            List<SysKeyname> keynameList = keynameMapper.selectByExample(example);
            if (keynameList == null || keynameList.size() == 0) {
                return Message.errorMessage(500, "没有字典信息");
            } else {
                return Message.successMessage(getTreeMap(keynameList, null));
            }
        } catch (Exception e) {
            logger.error("获取字典信息失败！", e);
            return Message.errorMessage(500, "获取字典信息失败！");
        }
    }

    @Override
    public List<SysKeyname> getAllKeynames() {
        SysKeynameExample example = new SysKeynameExample();
        example.createCriteria().andIdIsNotNull();
        return keynameMapper.selectByExample(example);
    }

    private List<Map<String, Object>> getTreeMap(List<SysKeyname> list, Long parentId) {
        List<Map<String, Object>> res = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            for (SysKeyname keyname : list) {
                if ((parentId == null &&  keyname.getParentId() == -1) || keyname.getParentId() == parentId) {
                    Map<String, Object> temp = new HashMap<>();
                    List<Map<String, Object>> sublist = getTreeMap(list, keyname.getId());
                    if (sublist != null && sublist.size() != 0) {
                        temp.put("children", sublist);
                    }
                    temp.put("id", keyname.getId());
                    temp.put("parentId", keyname.getParentId());
                    temp.put("label", keyname.getKeyname());
                    temp.put("keyType", keyname.getKeyType());
                    res.add(temp);
                }
            }
        }
        return res;
    }
}
