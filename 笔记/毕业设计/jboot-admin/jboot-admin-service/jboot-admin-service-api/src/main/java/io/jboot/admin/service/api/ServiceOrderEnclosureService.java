package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.service.entity.model.Enclosure;
import io.jboot.admin.service.entity.model.ServiceOrderEnclosure;

import java.util.List;

public interface ServiceOrderEnclosureService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public ServiceOrderEnclosure findById(Object id);


    /**
     * find all model
     *
     * @return all <ServiceOrderEnclosure
     */
    public List<ServiceOrderEnclosure> findAll();


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
    public boolean delete(ServiceOrderEnclosure model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(ServiceOrderEnclosure model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(ServiceOrderEnclosure model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(ServiceOrderEnclosure model);


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


	public Page<ServiceOrderEnclosure> findByCon(String id);


	public void deletePhotoId(String ar);


	public List<Record> findByIDz(String institutionName);
}