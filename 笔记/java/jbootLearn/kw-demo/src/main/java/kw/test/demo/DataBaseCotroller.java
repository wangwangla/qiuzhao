package kw.test.demo;

import java.util.Arrays;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

@RequestMapping("/db")
public class DataBaseCotroller extends JbootController{
	public void dbtest(){
        List<Record> records = Db.find("select * from user");
        renderText(Arrays.toString(records.toArray()));
    }
}
