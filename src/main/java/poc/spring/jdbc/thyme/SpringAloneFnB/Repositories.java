package poc.spring.jdbc.thyme.SpringAloneFnB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class Repositories {
    @Autowired
    JdbcTemplate template;

    public String insertOne(Television tele){
        String nm=tele.getStock_id()+" has inserted";
        template.update("insert into television(stock_name,stock_type,stock_brand,stock_size,stock_cost) values(?,?,?,?,?)",
                new Object[]{tele.getStock_name(),tele.getStock_type(),tele.getStock_brand(),
                        tele.getStock_size(),tele.getStock_cost()});
        return nm;
    }

    public String updateOne(Television tele){
        String nm=tele.getStock_id()+" has updated";
        template.update("update television set stock_name=?,stock_type=?,stock_brand=?,stock_size=?,stock_cost=? where stock_id=?",
                new Object[]{tele.getStock_name(),tele.getStock_type(),tele.getStock_brand(),
                tele.getStock_size(),tele.getStock_cost(),tele.getStock_id()});
        return nm;
    }

    public String deleteOne(int id){
        String nm=listOne(id).get().getStock_name()+" has deleted";
        template.update("delete from television where stock_id=?",new Object[]{id});
        return nm;
    }

    public Optional<Television> listOne(int id){
        return Optional.of(
                template.queryForObject("select * from television where stock_id=?",
                        new Object[]{id},
                new BeanPropertyRowMapper<Television>(Television.class)));
    }

    public List<Television> listAll(){
        return template.query("select * from television",new TeleMapper());
    }

    class TeleMapper implements RowMapper<Television>{
        @Override
        public Television mapRow(ResultSet rs, int rowNum) throws SQLException {
            Television vision=new Television();
            vision.setStock_id(rs.getInt("stock_id"));
            vision.setStock_brand(rs.getString("stock_brand"));
            vision.setStock_name(rs.getString("stock_name"));
            vision.setStock_type(rs.getString("stock_type"));
            vision.setStock_size(rs.getDouble("stock_size"));
            vision.setStock_cost(rs.getInt("stock_cost"));
            return vision;
        }
    }
}
