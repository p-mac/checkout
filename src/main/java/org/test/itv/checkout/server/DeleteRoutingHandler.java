package org.test.itv.checkout.server;

import org.test.itv.checkout.config.RouteConfig;
import org.test.itv.checkout.dto.DeleteTask;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.dto.EntityList;
import org.test.itv.checkout.dto.TaskError;
import org.test.itv.checkout.service.DeleteOperation;
import org.test.itv.checkout.util.ClassLoadUtil;
import spark.Request;
import spark.Response;

/**
 * Created on 27/11/18.
 */
public class DeleteRoutingHandler<E extends Entity> extends RoutingHander<E> {

    public DeleteRoutingHandler() {
        super();
    }

    public DeleteRoutingHandler(RouteConfig config) {
        super(config);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            E entity = getEntity(request);
            DeleteTask<E> task = new DeleteTask<E>(entityClass, EntityList.put(entity));
            DeleteOperation<E> operation = ClassLoadUtil.getObject(config.getServiceClassName());
            operation.process(task);
            response.status(200);
            return Void.TYPE;
        } catch (Exception e) {
            logger.error("ERROR while Deleting the ".concat(entityClass.getSimpleName()), e);
            response.status(500);
            return new TaskError(entityClass.getSimpleName(), "An Issue occurred while deleting the requested Entities");
        }
    }
}
