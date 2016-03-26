package fr.iutinfo.skeleton.res.model;

import com.google.common.base.Charsets;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import fr.iutinfo.skeleton.utils.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.security.SecureRandom;

public class User implements Principal {
    final static Logger logger = LoggerFactory.getLogger(User.class);

    private String name;
    private String alias;
    private int id = 0;
    private String email;
    private String password;
    private String passwdHash;
    private String salt;
    private String telNumber;
    private boolean isPro;
    private String location;
    private boolean isAcceptingGlobal =true;


    private static User anonymous = new User(-1, "Anonymous", "anonym");

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, String alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
    }

    public User() {
    }

    public void updateFrom(User user){
        logger.info("Lokking for change of this user (id {}) from {}",id,user);

        if (StrUtils.newValue(name,user.name)){
            logger.info("Editing of name from {} to {}",name,user.name);
            setName(user.name);
        }
        if (StrUtils.newValue(alias, user.alias)) {
            logger.info("Editing of alias from {} to {}", alias, user.alias);
            setAlias(user.alias);
        }
        if (StrUtils.newValue(email, user.email)) {
            logger.info("Editing of email from {} to {}", email, user.email);
            setEmail(user.email);
        }
        if (StrUtils.newValue(telNumber,user.telNumber)){
            logger.info("Editing of telNumber from {} to {}",telNumber,user.telNumber);
            setTelNumber(user.telNumber);
        }
        if (StrUtils.newValue(location,user.location)){
            logger.info("Editing of location from {} to {}",location,user.location);
            setTelNumber(user.location);
        }
        if (user.password != null){
            logger.info("Editing of password to {}",user.password);
            setPassword(user.password);
        }
        if (isPro != user.isPro){
            logger.info("Editing of isPro from {} to {}",isPro,user.isPro);
            setPro(user.isPro);
        }
        if (isAcceptingGlobal != user.isAcceptingGlobal){
            logger.info("Editing of isAcceptingGlobal from {} to {}",isAcceptingGlobal,user.isAcceptingGlobal);
            setAcceptingGlobal(user.isAcceptingGlobal);
        }

        logger.info("Edditing is done for id {}",id);


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setPassword(String password) {
        passwdHash = buildHash(password, getSalt());
        this.password = password;
    }

    public String getPassword () {
        return this.password;
    }

    private String buildHash(String password, String s) {
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putString(password + s, Charsets.UTF_8);
        return hasher.hash().toString();
    }

    public boolean isGoodPassword(String password) {
        String hash = buildHash(password, getSalt());
        return hash.equals(getPasswdHash());
    }

    public String getPasswdHash() {
        return passwdHash;
    }

    public void setPasswdHash(String passwdHash) {
        this.passwdHash = passwdHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                isPro == user.isPro &&
                isAcceptingGlobal == user.isAcceptingGlobal &&
                Objects.equal(name, user.name) &&
                Objects.equal(alias, user.alias) &&
                Objects.equal(email, user.email) &&
                Objects.equal(password, user.password) &&
                Objects.equal(passwdHash, user.passwdHash) &&
                Objects.equal(salt, user.salt) &&
                Objects.equal(telNumber, user.telNumber) &&
                Objects.equal(location, user.location);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, alias, id, email, password, passwdHash, salt, telNumber, isPro, location, isAcceptingGlobal);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("alias", alias)
                .add("id", id)
                .add("email", email)
                .add("password", password)
                .add("passwdHash", passwdHash)
                .add("salt", salt)
                .add("telNumber", telNumber)
                .add("isPro", isPro)
                .add("location", location)
                .add("isAcceptingGlobal", isAcceptingGlobal)
                .omitNullValues()
                .toString();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSalt() {
        if (salt == null) {
            salt = generateSalt();
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        Hasher hasher = Hashing.md5().newHasher();
        hasher.putLong(random.nextLong());
        return hasher.hash().toString();
    }

    public void resetPasswordHash() {
        if (password != null && ! password.isEmpty()) {
            setPassword(getPassword());
        }
    }

    public boolean isInUserGroup(){
        return ! (id == anonymous.getId());
    }

    public static User getAnonymousUser() {
        return anonymous ;
    }

    public static boolean isAnonymous(User currentUser) {
        return currentUser.getId() == getAnonymousUser().getId();
    }

    /**
     * @return the telNumber
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * @param telNumber the telNumber to set
     */
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }


    public boolean isPro() {
        return isPro;
    }

    public void setPro(boolean pro) {
        isPro = pro;
    }

    public boolean isAcceptingGlobal() {
        return isAcceptingGlobal;
    }

    public void setAcceptingGlobal(boolean acceptingGlobal) {
        this.isAcceptingGlobal = acceptingGlobal;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
