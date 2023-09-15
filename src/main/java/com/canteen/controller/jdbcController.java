package com.canteen.controller;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController         //如果要求方法返回的是json格式数据，而不是跳转页面，可以直接在类上标注@RestController
@RequestMapping("/mysql")
public class jdbcController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/selectbusiness")
    public JSONArray selectbusiness(){             //查询所有商家

            String sql="select * from business";

            List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);

            System.out.println(maps);

            JSONArray json = JSONArray.fromObject(maps);

            System.out.println(json);
            return json;

    }
    @PostMapping("/selectbusinessForid")
    public JSONArray selectbusinessForid(@RequestBody JSONObject req){             //根据id查询商家

        int id= Integer.parseInt(req.getString("id"));

        String sql="select * from business where businessid="+"\""+id+"\"";

        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);

        System.out.println(maps);

        JSONArray json = JSONArray.fromObject(maps);

        System.out.println(json);
        return json;

    }

    @PostMapping("/selectbusinessForclass")
    public JSONArray selectbusinessForclass(@RequestBody JSONObject req){             //根据分类名查询商家

        String classname=req.getString("classname");

        String sql="select * from business where  class="+"\""+classname+"\"";

        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);

        System.out.println(maps);

        JSONArray json = JSONArray.fromObject(maps);

        System.out.println(json);
        return json;

    }

    @PostMapping("/selectgoods")                //根据商家id查询菜品
    public JSONArray selectgoods(@RequestBody JSONObject req){

        int id= Integer.parseInt(req.getString("id"));
        System.out.println(id);

        String sql="select * from goods where businessid="+"\""+id+"\"";

        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);

        System.out.println(maps);

        JSONArray json = JSONArray.fromObject(maps);

        System.out.println(json);
        return json;

    }

    @PostMapping("/selectallgoods")                //查询所有菜品，商家，地址
    public JSONArray selectallgoods(){


        String sql="select name,businessName,businessid,address from goods";

        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);

        System.out.println(maps);

        JSONArray json = JSONArray.fromObject(maps);

        System.out.println(json);
        return json;

    }


    @PostMapping("/selectgoodsForclass")            //根据商家内分类查询菜品
    public JSONArray selectgoodsForclass(@RequestBody JSONObject req){

        int id= Integer.parseInt(req.getString("id"));
        String name=req.getString("classname");

        String sql="select * from goods where businessid="+"\""+id+"\""+"and classname="+"\""+name+"\"";

        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);

        System.out.println(maps);

        JSONArray json = JSONArray.fromObject(maps);

        System.out.println(json);
        return json;

    }

    @PostMapping("/selectclass")                  //查询商家的菜品分类名
    public JSONArray selectclass(@RequestBody JSONObject req){

        int id= Integer.parseInt(req.getString("id"));
        System.out.println(id);

        String sql="select * from class where businessid="+"\""+id+"\"";

        List<Map<String,Object>> maps=jdbcTemplate.queryForList(sql);

        System.out.println(maps);

        JSONArray json = JSONArray.fromObject(maps);

        System.out.println(json);
        return json;

    }



    @PostMapping("/insertstudent")              //插入学生名字学生数据
    public String insertstudent(@RequestBody JSONObject req){

        String openid=req.getString("openid");
        String name=req.getString("name");
        System.out.println(openid);
        System.out.println(name);

        String selectsql="select * from student where openid="+"\""+openid+"\"";
        List<Map<String,Object>> maps=jdbcTemplate.queryForList(selectsql);
        if(maps.size()==0) {
            String updatesql = "insert into student (name,openid) values(?,?)";
            jdbcTemplate.update(updatesql,name , openid);
            return "添加成功";
        }
        else {

            return "已有数据";

        }

    }

    @PostMapping("/updatestudent")              //修改学生名字学生数据
    public String updatestudent(@RequestBody JSONObject req){

        String openid=req.getString("openid");
        String name=req.getString("name");
        System.out.println(openid);
        System.out.println(name);

        String selectsql="select * from student where openid="+"\""+openid+"\"";
        List<Map<String,Object>> maps=jdbcTemplate.queryForList(selectsql);
        if(maps.size()==0) {
            String updatesql = "insert into student (name,openid) values(?,?)";
            jdbcTemplate.update(updatesql,name , openid);
            return "添加成功";
        }
        else {

            String setsql="update student set name="+"\""+name+"\""+"where openid="+"\""+openid+"\"";
            jdbcTemplate.update(setsql);
            return "修改成功";
        }

    }

    @PostMapping("/selectstudent")              //查询学生名字学生数据
    public String selectstudent(@RequestBody JSONObject req){

        String openid=req.getString("openid");

        String selectsql="select * from student where openid="+"\""+openid+"\"";
        List<Map<String,Object>> maps=jdbcTemplate.queryForList(selectsql);

        if(maps.size()!=0)
        {
            String name=maps.get(0).get("name").toString();
            return name;
        }
        else
        {
            return "";
        }
        }

}



