package labshopmisvcfeigndup.domain;

import java.util.*;
import labshopmisvcfeigndup.domain.*;
import labshopmisvcfeigndup.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderReturned extends AbstractEvent {

    private Long id;

    public OrderReturned(Order aggregate) {
        super(aggregate);
    }

    public OrderReturned() {
        super();
    }
    // keep

}
