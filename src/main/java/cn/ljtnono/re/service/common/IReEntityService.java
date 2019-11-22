package cn.ljtnono.re.service.common;

import cn.ljtnono.re.pojo.JsonResult;

import java.io.Serializable;

/**
 * service父类
 * @author ljt
 * @date 2019/11/22
 * @version 1.0
 */
public interface IReEntityService <T> {

    JsonResult save(T entity);

    JsonResult listAll();

    JsonResult getById(Serializable id);

    JsonResult updateById(Serializable id);

    JsonResult deleteById(Serializable id);
}
