package fr.iutinfo.skeleton.utils.binders;

import fr.iutinfo.skeleton.res.model.Invit;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

@BindingAnnotation(BindInvit.UserBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindInvit {
    public static class UserBinderFactory implements BinderFactory {
        public Binder build(Annotation annotation) {
            return new Binder<BindInvit, Invit>() {
                public void bind(SQLStatement q, BindInvit bind, Invit invit) {
                    q.bind("event", invit.getEvent());
                    q.bind("user", invit.getUser());
                    q.bind("timestamp", invit.getTimestamp());
                    q.bind("isSecondaryList", invit.isSecondaryList());
                    q.bind("isfired", invit.isFired());
                }
            };
        }
    }
}
