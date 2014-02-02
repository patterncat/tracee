package de.holisticon.util.tracee.errorlogger.json.generator;

import de.holisticon.util.tracee.TraceeBackend;
import de.holisticon.util.tracee.errorlogger.json.beans.values.TraceeContextValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Factory for tracee context specific data (MDB).
 * Created by Tobias Gindler, holisticon AG on 20.12.13.
 */
public final class TraceeCategoryCreator {

    @SuppressWarnings("unused")
    private TraceeCategoryCreator() {

    }


    public static List<TraceeContextValue> createTraceeCategory(TraceeBackend traceeBackend) {

        final List<TraceeContextValue> list = new ArrayList<TraceeContextValue>();

        final Collection<String> keys = traceeBackend.keySet();
        if (keys != null) {
            for (String key : keys) {
                final String value = traceeBackend.get(key);
                list.add(new TraceeContextValue(key, value));
            }
        }
        return list;

    }

}
