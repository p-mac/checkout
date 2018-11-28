package org.test.itv.checkout.server;

import org.test.itv.checkout.config.RouteConfig;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.dto.EntityList;
import org.test.itv.checkout.dto.TaskError;
import org.test.itv.checkout.dto.UpdateTask;
import org.test.itv.checkout.service.UpdateOperation;
import org.test.itv.checkout.util.ClassLoadUtil;
import spark.Request;
import spark.Response;

/**
 * Created on 27/11/18.
 */
public class PutRoutingHandler<E extends Entity> extends RoutingHander<E> {

    public PutRoutingHandler() {
        super();
    }

    public PutRoutingHandler(RouteConfig config) {
        super(config);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            E entity = getEntity(request);
            UpdateTask<E> task = new UpdateTask<E>(entityClass, EntityList.put(entity), getParams(request));
            UpdateOperation<E> operation = ClassLoadUtil.getObject(config.getServiceClassName());
            operation.process(task);
            return task.getEntities();
        } catch (Exception e) {
            logger.error("ERROR while Updating the ".concat(entityClass.getSimpleName()), e);
            response.status(500);
            return new TaskError(entityClass.getSimpleName(), "An Issue occurred while updating the requested Entities");
        }
    }
}
