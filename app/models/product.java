package models;

import play.db.jpa.Model;

import javax.persistence.*;


/**
 * Created by PB6N0071 on 9/13/2017.
 */
@Entity
public class product extends Model{
    @Column(columnDefinition = "varchar(30)")
    public String prod_desc;
    public String prod_cate;
    public String beauty_desc;

    @Column(columnDefinition = "varchar(100)")
    public String file_name;

    @Column(columnDefinition = "varchar(100)")
    public String prodName;

    @Column(columnDefinition = "smallint")
    public int price;


}
