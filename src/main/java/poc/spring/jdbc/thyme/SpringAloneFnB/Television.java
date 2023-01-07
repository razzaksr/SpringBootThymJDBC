package poc.spring.jdbc.thyme.SpringAloneFnB;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Television {
    private int stock_id;
    private String stock_name;
    private String stock_brand;
    private String stock_type;
    private double stock_size;
    private int stock_cost;
}
