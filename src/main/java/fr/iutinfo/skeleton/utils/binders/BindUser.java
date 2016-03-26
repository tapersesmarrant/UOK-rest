package fr.iutinfo.skeleton.utils.binders;

import fr.iutinfo.skeleton.res.model.User;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import java.lang.annotation.*;

@BindingAnnotation(BindUser.UserBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface BindUser {
    public static class UserBinderFactory implements BinderFactory {
        public Binder build(Annotation annotation) {
            return new Binder<BindUser, User>() {
                public void bind(SQLStatement q, BindUser bind, User user) {
                    q.bind("name", user.getName());
                    q.bind("alias", user.getAlias());
                    q.bind("id", user.getId());
                    q.bind("email", user.getEmail());
                    q.bind("hash", user.getPasswdHash());
                    q.bind("salt", user.getSalt());
                    q.bind("telNumber", user.getTelNumber());
                    q.bind("isPro", user.isPro());
                    q.bind("location", user.getLocation());
                    q.bind("isAcceptingGlobal", user.isAcceptingGlobal());
                }
            };
        }
    }
}
