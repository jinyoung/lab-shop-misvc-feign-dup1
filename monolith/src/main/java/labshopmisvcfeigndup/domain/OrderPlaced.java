package labshopmisvcfeigndup.domain;

import java.util.*;
import labshopmisvcfeigndup.domain.*;
import labshopmisvcfeigndup.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String productId;
    private Integer qty;
    private String customerId;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
    // keep

}
