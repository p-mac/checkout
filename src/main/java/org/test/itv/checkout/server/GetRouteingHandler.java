package org.test.itv.checkout.server;

import org.test.itv.checkout.config.RouteConfig;
import org.test.itv.checkout.dto.Entity;
import org.test.itv.checkout.dto.RetrieveTask;
import org.test.itv.checkout.dto.TaskError;
import org.test.itv.checkout.service.RetrieveOperation;
import org.test.itv.checkout.util.ClassLoadUtil;
import spark.Request;
import spark.Response;

/**
 * Created on 27/11/18.
 */
public class GetRouteingHandler<E extends Entity> extends RoutingHander<E> {

    public GetRouteingHandler() {
        super();
    }

    public GetRouteingHandler(RouteConfig config) {
        super(config);
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            RetrieveTask<E> task = new RetrieveTask<E>(entityClass, getParams(request));
            RetrieveOperation<E> operation = ClassLoadUtil.getObject(config.getServiceClassName());
            operation.process(task);
            return task.getEntities();
        } catch (Exception e) {
            logger.error("ERROR while Retrieving the ".concat(entityClass.getSimpleName()), e);
            response.status(500);
            return new TaskError(entityClass.getSimpleName(), "An Issue occurred while retrieving the requested Entities");
        }
    }

}
