package io.jboot.admin.service.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.admin.service.entity.model.InstitutionDetailInfo;
import io.jboot.admin.service.entity.model.ServiceOrder;
import io.jboot.db.model.Columns;

import java.util.List;

public interface ServiceOrderService  {

    /**
     * find model by primary key
     *
     * @param id
     * @return
     */
    public ServiceOrder findById(Object id);


    /**
     * find all model
     *
     * @return all <ServiceOrder
     */
    public List<ServiceOrder> findAll();


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
    public boolean delete(ServiceOrder model);


    /**
     * save model to database
     *
     * @param model
     * @return
     */
    public boolean save(ServiceOrder model);


    /**
     * save or update model
     *
     * @param model
     * @return if save or update success
     */
    public boolean saveOrUpdate(ServiceOrder model);


    /**
     * update data model
     *
     * @param model
     * @return
     */
    public boolean update(ServiceOrder model);


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


	public Page<ServiceOrder> findPage(int pageNumber, int pageSize);


	public void refreshCache();


	public Page<ServiceOrder> findByIDXX(String id);


	public List<Record> findByIDN(String id);


	public List<Record> findByIDy(String id);


	public List<Record> findByIDjd(String id);


	public List<Record> findByIDz(String id);


	public List<Record> findByIDxq(String institutionName);
 
	public Page<ServiceOrder> findByWdName(String id, String institutionName);


	public List<Record> findByYearPage(int pageNumber, int pageSize);


	public List<Record> findByJiDuPage(int pageNumber, int pageSize);


	public List<Record> findByMonthPage(int pageNumber, int pageSize);


	public List<Record> findByWeekPage(int pageNumber, int pageSize);
}