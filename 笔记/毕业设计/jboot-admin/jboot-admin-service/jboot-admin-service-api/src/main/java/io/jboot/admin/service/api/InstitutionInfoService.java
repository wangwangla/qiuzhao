package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.service.entity.model.InstitutionInfo;
import io.jboot.admin.service.entity.model.StaffInfo;

import java.util.List;

public interface InstitutionInfoService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public InstitutionInfo findById(Object id);


    /**
     * find all model
     *
     * @return all <InstitutionInfo
     */
    public List<InstitutionInfo> findAll();


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
    public boolean delete(InstitutionInfo model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(InstitutionInfo model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(InstitutionInfo model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(InstitutionInfo model);


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


	public Page<InstitutionInfo> findPage(int pageNumber, int pageSize);


	public void refreshCache();


	public Page<InstitutionInfo> findPage(InstitutionInfo info, int pageNumber, int pageSize);


	public Record findByInsititutionName(String institution);
}