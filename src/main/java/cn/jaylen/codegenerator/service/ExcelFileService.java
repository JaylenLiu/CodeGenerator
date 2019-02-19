package cn.jaylen.codegenerator.service;

import cn.jaylen.codegenerator.common.Message;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ljl
 * @create 2018-07-23 10:36
 * @desc 文件处理
 **/
public interface ExcelFileService {
    Message uploadExcel(MultipartFile file, Long proId) throws Exception;
}
