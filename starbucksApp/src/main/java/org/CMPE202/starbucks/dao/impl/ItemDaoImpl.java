package org.CMPE202.starbucks.dao.impl;

import org.CMPE202.starbucks.dao.IItemDao;
import org.CMPE202.starbucks.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ItemDaoImpl implements IItemDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Item> viewItems() {
        String sql ="SELECT "+
                "itemId,"+
                "itemName,"+
                "itemPrice,"+
                "isAvailbale"+
                " FROM ITEM";

           List<Item> items = new ArrayList<Item>();

            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
            for (Map row : rows) {
                Item item = new Item();
                item.setItemId((String)(row.get("itemId")));
                item.setItemName((String)row.get("itemName"));
                item.setItemPrice((Double) row.get("itemPrice"));
                item.setAvailable((boolean) row.get("isAvailbale"));
                items.add(item);
            }

      /*  List<Item> items  = getJdbcTemplate().query(sql,
                new BeanPropertyRowMapper(Item.class));*/
            return items;

    }
}
