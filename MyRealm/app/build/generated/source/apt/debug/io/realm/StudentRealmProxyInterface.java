package io.realm;


public interface StudentRealmProxyInterface {
    public String realmGet$name();
    public void realmSet$name(String value);
    public long realmGet$id();
    public void realmSet$id(long value);
    public int realmGet$age();
    public void realmSet$age(int value);
    public String realmGet$gender();
    public void realmSet$gender(String value);
    public RealmList<com.ybyb.zzq.myrealm.data.Subject> realmGet$subjects();
    public void realmSet$subjects(RealmList<com.ybyb.zzq.myrealm.data.Subject> value);
    public double realmGet$totalScore();
    public void realmSet$totalScore(double value);
    public double realmGet$averageScore();
    public void realmSet$averageScore(double value);
    public int realmGet$orderScore();
    public void realmSet$orderScore(int value);
}
