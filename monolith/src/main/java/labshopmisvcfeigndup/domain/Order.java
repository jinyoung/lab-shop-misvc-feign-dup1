package labshopmisvcfeigndup.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import labshopmisvcfeigndup.MonolithApplication;
import labshopmisvcfeigndup.domain.OrderPlaced;
import labshopmisvcfeigndup.domain.OrderReturned;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productId;

    private Integer qty;

    private String customerId;

    private Double amount;

    @Embedded
    @AttributeOverride(
        name = "amount",
        column = @Column(name = "paymentAmount", nullable = true)
    )
    private Payment payment;

    @PostPersist
    public void onPostPersist() {
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        labshopmisvcfeigndup.external.Inventory inventory = new labshopmisvcfeigndup.external.Inventory();
        // mappings goes here
        MonolithApplication.applicationContext
            .getBean(labshopmisvcfeigndup.external.InventoryService.class)
            .decreaseInventory(inventory);

        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        labshopmisvcfeigndup.external.Inventory inventory = new labshopmisvcfeigndup.external.Inventory();
        // mappings goes here
        MonolithApplication.applicationContext
            .getBean(labshopmisvcfeigndup.external.InventoryService.class)
            .addStock(inventory);

        OrderReturned orderReturned = new OrderReturned(this);
        orderReturned.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static OrderRepository repository() {
        OrderRepository orderRepository = MonolithApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
