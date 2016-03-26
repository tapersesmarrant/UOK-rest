package fr.iutinfo.skeleton.utils.binders;

import fr.iutinfo.skeleton.res.model.Event;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

@BindingAnnotation(BindEvent.UserBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindEvent {
    public static class UserBinderFactory implements BinderFactory {
        public Binder build(Annotation annotation) {
            return new Binder<BindEvent, Event>() {
                public void bind(SQLStatement q, BindEvent bind, Event event) {
                    q.bind("id", event.getId());
                    q.bind("owner", event.getOwner());
                    q.bind("location", event.getLocation());
                    q.bind("date", event.getDate());
                    q.bind("name", event.getName());
                    q.bind("limitTime", event.getLimiteTime());
                    q.bind("isLock", event.isLock());
                    q.bind("isRush", event.isRush());
                    q.bind("isTime", event.isTime());
                    q.bind("timeBeforeRush", event.getTimeBeforeRush());
                    q.bind("cost", event.getCost());
                    q.bind("isDone", event.isDone());
                    q.bind("isCanceled", event.isCanceled());
                }
            };
        }
    }
}
