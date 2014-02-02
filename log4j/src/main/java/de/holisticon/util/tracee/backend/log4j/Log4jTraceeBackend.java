package de.holisticon.util.tracee.backend.log4j;

import de.holisticon.util.tracee.MDCLike;
import de.holisticon.util.tracee.MDCLikeTraceeBackend;
import de.holisticon.util.tracee.TraceeLogger;

import java.util.Set;


/**
 * @author Daniel Wegener (Holisticon AG)
 */
final class Log4jTraceeBackend extends MDCLikeTraceeBackend {

    Log4jTraceeBackend(MDCLike mdcAdapter, ThreadLocal<Set<String>> traceeKeys) {
        super(mdcAdapter, traceeKeys);
    }

    @Override
    public final TraceeLogger getLogger(Class<?> clazz) {
        return new Log4JTraceeLogger(clazz);
    }

}
