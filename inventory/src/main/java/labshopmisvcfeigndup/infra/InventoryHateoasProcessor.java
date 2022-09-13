package labshopmisvcfeigndup.infra;

import labshopmisvcfeigndup.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class InventoryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Inventory>> {

    @Override
    public EntityModel<Inventory> process(EntityModel<Inventory> model) {
        model.add(
            Link
                .of(
                    model.getRequiredLink("self").getHref() +
                    "/decreaseinventory"
                )
                .withRel("decreaseinventory")
        );
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/addstock")
                .withRel("addstock")
        );

        return model;
    }
}
