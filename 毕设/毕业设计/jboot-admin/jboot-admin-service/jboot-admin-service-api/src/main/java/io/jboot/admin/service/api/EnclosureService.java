package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.service.entity.model.Enclosure;

import java.util.List;

public interface EnclosureService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public Enclosure findById(Object id);


    /**
     * find all model
     *
     * @return all <Enclosure
     */
    public List<Enclosure> findAll();


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
    public boolean delete(Enclosure model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(Enclosure model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(Enclosure model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(Enclosure model);


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


	public  Page<Enclosure> findByCon(String id);


	public void deleteBywareId(String id);


	public List<Record> export();
}