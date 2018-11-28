package org.test.itv.checkout.dto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created on 27/11/18.
 */
public class EntityList<E extends Entity> extends ArrayList<E> {

    public EntityList() { }

    public EntityList(@NotNull Collection<? extends E> c) {
        super(c);
    }

    public E get() {
        return get((size()-1));
    }

    public static <E extends Entity> EntityList<E> put(E... entities) {
        return  (entities.length > 0) ? new EntityList<E>(Arrays.asList(entities)) : new EntityList<E>();
    }
}
