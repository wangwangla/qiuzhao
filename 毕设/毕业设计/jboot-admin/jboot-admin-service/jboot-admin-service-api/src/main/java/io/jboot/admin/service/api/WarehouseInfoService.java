package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.admin.service.entity.model.WarehouseInfo;

import java.util.List;

public interface WarehouseInfoService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public WarehouseInfo findById(Object id);


    /**
     * find all model
     *
     * @return all <WarehouseInfo
     */
    public List<WarehouseInfo> findAll();


    /**
     * delete model by primary key
     *
     * @param id
     * @return success
     */
    public boolean deleteById(Object id);


    /**
     * delete model
     *
     * @param model
     * @return
     */
    public boolean delete(WarehouseInfo model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(WarehouseInfo model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(WarehouseInfo model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(WarehouseInfo model);


    public void join(Page<? extends Model> page, String joinOnField);
    public void join(Page<? extends Model> page, String joinOnField, String[] attrs);
    public void join(Page<? extends Model> page, String joinOnField, String joinName);
    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);
    public void join(List<? extends Model> models, String joinOnField);
    public void join(List<? extends Model> models, String joinOnField, String[] attrs);
    public void join(List<? extends Model> models, String joinOnField, String joinName);
    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);
    public void join(Model model, String joinOnField);
    public void join(Model model, String joinOnField, String[] attrs);
    public void join(Model model, String joinOnField, String joinName);
    public void join(Model model, String joinOnField, String joinName, String[] attrs);

    public void keep(Model model, String... attrs);
    public void keep(List<? extends Model> models, String... attrs);


	public Page<WarehouseInfo> findPage(int pageNumber, int pageSize);


	public void refreshCache();

}