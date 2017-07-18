package io.realm;


import android.util.JsonReader;
import io.realm.RealmObjectSchema;
import io.realm.internal.ColumnInfo;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

@io.realm.annotations.RealmModule
class DefaultRealmModuleMediator extends RealmProxyMediator {

    private static final Set<Class<? extends RealmModel>> MODEL_CLASSES;
    static {
        Set<Class<? extends RealmModel>> modelClasses = new HashSet<Class<? extends RealmModel>>();
        modelClasses.add(com.ybyb.zzq.myrealm.data.Subject.class);
        modelClasses.add(com.ybyb.zzq.myrealm.data.Student.class);
        modelClasses.add(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        MODEL_CLASSES = Collections.unmodifiableSet(modelClasses);
    }

    @Override
    public RealmObjectSchema createRealmObjectSchema(Class<? extends RealmModel> clazz, RealmSchema realmSchema) {
        checkClass(clazz);

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return io.realm.SubjectRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return io.realm.StudentRealmProxy.createRealmObjectSchema(realmSchema);
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return io.realm.SimpleClassRealmProxy.createRealmObjectSchema(realmSchema);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public ColumnInfo validateTable(Class<? extends RealmModel> clazz, SharedRealm sharedRealm, boolean allowExtraColumns) {
        checkClass(clazz);

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return io.realm.SubjectRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return io.realm.StudentRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return io.realm.SimpleClassRealmProxy.validateTable(sharedRealm, allowExtraColumns);
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public List<String> getFieldNames(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return io.realm.SubjectRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return io.realm.StudentRealmProxy.getFieldNames();
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return io.realm.SimpleClassRealmProxy.getFieldNames();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public String getTableName(Class<? extends RealmModel> clazz) {
        checkClass(clazz);

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return io.realm.SubjectRealmProxy.getTableName();
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return io.realm.StudentRealmProxy.getTableName();
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return io.realm.SimpleClassRealmProxy.getTableName();
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E newInstance(Class<E> clazz, Object baseRealm, Row row, ColumnInfo columnInfo, boolean acceptDefaultValue, List<String> excludeFields) {
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        try {
            objectContext.set((BaseRealm) baseRealm, row, columnInfo, acceptDefaultValue, excludeFields);
            checkClass(clazz);

            if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
                return clazz.cast(new io.realm.SubjectRealmProxy());
            }
            if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
                return clazz.cast(new io.realm.StudentRealmProxy());
            }
            if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
                return clazz.cast(new io.realm.SimpleClassRealmProxy());
            }
            throw getMissingProxyClassException(clazz);
        } finally {
            objectContext.clear();
        }
    }

    @Override
    public Set<Class<? extends RealmModel>> getModelClasses() {
        return MODEL_CLASSES;
    }

    @Override
    public <E extends RealmModel> E copyOrUpdate(Realm realm, E obj, boolean update, Map<RealmModel, RealmObjectProxy> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return clazz.cast(io.realm.SubjectRealmProxy.copyOrUpdate(realm, (com.ybyb.zzq.myrealm.data.Subject) obj, update, cache));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return clazz.cast(io.realm.StudentRealmProxy.copyOrUpdate(realm, (com.ybyb.zzq.myrealm.data.Student) obj, update, cache));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return clazz.cast(io.realm.SimpleClassRealmProxy.copyOrUpdate(realm, (com.ybyb.zzq.myrealm.data.SimpleClass) obj, update, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public void insert(Realm realm, RealmModel object, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            io.realm.SubjectRealmProxy.insert(realm, (com.ybyb.zzq.myrealm.data.Subject) object, cache);
        } else if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            io.realm.StudentRealmProxy.insert(realm, (com.ybyb.zzq.myrealm.data.Student) object, cache);
        } else if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            io.realm.SimpleClassRealmProxy.insert(realm, (com.ybyb.zzq.myrealm.data.SimpleClass) object, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insert(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
                io.realm.SubjectRealmProxy.insert(realm, (com.ybyb.zzq.myrealm.data.Subject) object, cache);
            } else if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
                io.realm.StudentRealmProxy.insert(realm, (com.ybyb.zzq.myrealm.data.Student) object, cache);
            } else if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
                io.realm.SimpleClassRealmProxy.insert(realm, (com.ybyb.zzq.myrealm.data.SimpleClass) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
                    io.realm.SubjectRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
                    io.realm.StudentRealmProxy.insert(realm, iterator, cache);
                } else if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
                    io.realm.SimpleClassRealmProxy.insert(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, RealmModel obj, Map<RealmModel, Long> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((obj instanceof RealmObjectProxy) ? obj.getClass().getSuperclass() : obj.getClass());

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            io.realm.SubjectRealmProxy.insertOrUpdate(realm, (com.ybyb.zzq.myrealm.data.Subject) obj, cache);
        } else if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            io.realm.StudentRealmProxy.insertOrUpdate(realm, (com.ybyb.zzq.myrealm.data.Student) obj, cache);
        } else if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            io.realm.SimpleClassRealmProxy.insertOrUpdate(realm, (com.ybyb.zzq.myrealm.data.SimpleClass) obj, cache);
        } else {
            throw getMissingProxyClassException(clazz);
        }
    }

    @Override
    public void insertOrUpdate(Realm realm, Collection<? extends RealmModel> objects) {
        Iterator<? extends RealmModel> iterator = objects.iterator();
        RealmModel object = null;
        Map<RealmModel, Long> cache = new HashMap<RealmModel, Long>(objects.size());
        if (iterator.hasNext()) {
            //  access the first element to figure out the clazz for the routing below
            object = iterator.next();
            // This cast is correct because obj is either
            // generated by RealmProxy or the original type extending directly from RealmObject
            @SuppressWarnings("unchecked") Class<RealmModel> clazz = (Class<RealmModel>) ((object instanceof RealmObjectProxy) ? object.getClass().getSuperclass() : object.getClass());

            if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
                io.realm.SubjectRealmProxy.insertOrUpdate(realm, (com.ybyb.zzq.myrealm.data.Subject) object, cache);
            } else if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
                io.realm.StudentRealmProxy.insertOrUpdate(realm, (com.ybyb.zzq.myrealm.data.Student) object, cache);
            } else if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
                io.realm.SimpleClassRealmProxy.insertOrUpdate(realm, (com.ybyb.zzq.myrealm.data.SimpleClass) object, cache);
            } else {
                throw getMissingProxyClassException(clazz);
            }
            if (iterator.hasNext()) {
                if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
                    io.realm.SubjectRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
                    io.realm.StudentRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
                    io.realm.SimpleClassRealmProxy.insertOrUpdate(realm, iterator, cache);
                } else {
                    throw getMissingProxyClassException(clazz);
                }
            }
        }
    }

    @Override
    public <E extends RealmModel> E createOrUpdateUsingJsonObject(Class<E> clazz, Realm realm, JSONObject json, boolean update)
        throws JSONException {
        checkClass(clazz);

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return clazz.cast(io.realm.SubjectRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return clazz.cast(io.realm.StudentRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return clazz.cast(io.realm.SimpleClassRealmProxy.createOrUpdateUsingJsonObject(realm, json, update));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createUsingJsonStream(Class<E> clazz, Realm realm, JsonReader reader)
        throws IOException {
        checkClass(clazz);

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return clazz.cast(io.realm.SubjectRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return clazz.cast(io.realm.StudentRealmProxy.createUsingJsonStream(realm, reader));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return clazz.cast(io.realm.SimpleClassRealmProxy.createUsingJsonStream(realm, reader));
        }
        throw getMissingProxyClassException(clazz);
    }

    @Override
    public <E extends RealmModel> E createDetachedCopy(E realmObject, int maxDepth, Map<RealmModel, RealmObjectProxy.CacheData<RealmModel>> cache) {
        // This cast is correct because obj is either
        // generated by RealmProxy or the original type extending directly from RealmObject
        @SuppressWarnings("unchecked") Class<E> clazz = (Class<E>) realmObject.getClass().getSuperclass();

        if (clazz.equals(com.ybyb.zzq.myrealm.data.Subject.class)) {
            return clazz.cast(io.realm.SubjectRealmProxy.createDetachedCopy((com.ybyb.zzq.myrealm.data.Subject) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.Student.class)) {
            return clazz.cast(io.realm.StudentRealmProxy.createDetachedCopy((com.ybyb.zzq.myrealm.data.Student) realmObject, 0, maxDepth, cache));
        }
        if (clazz.equals(com.ybyb.zzq.myrealm.data.SimpleClass.class)) {
            return clazz.cast(io.realm.SimpleClassRealmProxy.createDetachedCopy((com.ybyb.zzq.myrealm.data.SimpleClass) realmObject, 0, maxDepth, cache));
        }
        throw getMissingProxyClassException(clazz);
    }

}
