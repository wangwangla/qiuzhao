package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.service.entity.model.InstitutionDetailInfo;

import java.util.List;

public interface InstitutionDetailInfoService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public InstitutionDetailInfo findById(Object id);


    /**
     * find all model
     *
     * @return all <InstitutionDetailInfo
     */
    public List<InstitutionDetailInfo> findAll();


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
    public boolean delete(InstitutionDetailInfo model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(InstitutionDetailInfo model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(InstitutionDetailInfo model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(InstitutionDetailInfo model);


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


	public void refreshCache();


	public Page<InstitutionDetailInfo> findPage(int pageNumber, int pageSize);


	public void delete(String institutionId);


	public Page<InstitutionDetailInfo>  findByIDXX(String id);


	public Record findByWD(String wdName);
 

	public List<Record> findByInstituId(String id);


 
}