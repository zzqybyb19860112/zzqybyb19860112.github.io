package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.LinkView;
import io.realm.internal.OsObject;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.SharedRealm;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SimpleClassRealmProxy extends com.ybyb.zzq.myrealm.data.SimpleClass
    implements RealmObjectProxy, SimpleClassRealmProxyInterface {

    static final class SimpleClassColumnInfo extends ColumnInfo {
        long studentsIndex;
        long classNameIndex;

        SimpleClassColumnInfo(SharedRealm realm, Table table) {
            super(2);
            this.studentsIndex = addColumnDetails(table, "students", RealmFieldType.LIST);
            this.classNameIndex = addColumnDetails(table, "className", RealmFieldType.STRING);
        }

        SimpleClassColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SimpleClassColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SimpleClassColumnInfo src = (SimpleClassColumnInfo) rawSrc;
            final SimpleClassColumnInfo dst = (SimpleClassColumnInfo) rawDst;
            dst.studentsIndex = src.studentsIndex;
            dst.classNameIndex = src.classNameIndex;
        }
    }

    private SimpleClassColumnInfo columnInfo;
    private ProxyState<com.ybyb.zzq.myrealm.data.SimpleClass> proxyState;
    private RealmList<com.ybyb.zzq.myrealm.data.Student> studentsRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("students");
        fieldNames.add("className");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    SimpleClassRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SimpleClassColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.ybyb.zzq.myrealm.data.SimpleClass>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    public RealmList<com.ybyb.zzq.myrealm.data.Student> realmGet$students() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (studentsRealmList != null) {
            return studentsRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.studentsIndex);
            studentsRealmList = new RealmList<com.ybyb.zzq.myrealm.data.Student>(com.ybyb.zzq.myrealm.data.Student.class, linkView, proxyState.getRealm$realm());
            return studentsRealmList;
        }
    }

    @Override
    public void realmSet$students(RealmList<com.ybyb.zzq.myrealm.data.Student> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("students")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.ybyb.zzq.myrealm.data.Student> original = value;
                value = new RealmList<com.ybyb.zzq.myrealm.data.Student>();
                for (com.ybyb.zzq.myrealm.data.Student item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.studentsIndex);
        links.clear();
        if (value == null) {
            return;
        }
        for (RealmModel linkedObject : (RealmList<? extends RealmModel>) value) {
            if (!(RealmObject.isManaged(linkedObject) && RealmObject.isValid(linkedObject))) {
                throw new IllegalArgumentException("Each element of 'value' must be a valid managed object.");
            }
            if (((RealmObjectProxy)linkedObject).realmGet$proxyState().getRealm$realm() != proxyState.getRealm$realm()) {
                throw new IllegalArgumentException("Each element of 'value' must belong to the same Realm.");
            }
            links.add(((RealmObjectProxy)linkedObject).realmGet$proxyState().getRow$realm().getIndex());
        }
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$className() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.classNameIndex);
    }

    @Override
    public void realmSet$className(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.classNameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.classNameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.classNameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.classNameIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("SimpleClass")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("SimpleClass");
            if (!realmSchema.contains("Student")) {
                StudentRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("students", RealmFieldType.LIST, realmSchema.get("Student"));
            realmObjectSchema.add("className", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("SimpleClass");
    }

    public static SimpleClassColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_SimpleClass")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'SimpleClass' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_SimpleClass");
        final long columnCount = table.getColumnCount();
        if (columnCount != 2) {
            if (columnCount < 2) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 2 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 2 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 2 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final SimpleClassColumnInfo columnInfo = new SimpleClassColumnInfo(sharedRealm, table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("students")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'students'");
        }
        if (columnTypes.get("students") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Student' for field 'students'");
        }
        if (!sharedRealm.hasTable("class_Student")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Student' for field 'students'");
        }
        Table table_0 = sharedRealm.getTable("class_Student");
        if (!table.getLinkTarget(columnInfo.studentsIndex).hasSameSchema(table_0)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'students': '" + table.getLinkTarget(columnInfo.studentsIndex).getName() + "' expected - was '" + table_0.getName() + "'");
        }
        if (!columnTypes.containsKey("className")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'className' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("className") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'className' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.classNameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'className' is required. Either set @Required to field 'className' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_SimpleClass";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.ybyb.zzq.myrealm.data.SimpleClass createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("students")) {
            excludeFields.add("students");
        }
        com.ybyb.zzq.myrealm.data.SimpleClass obj = realm.createObjectInternal(com.ybyb.zzq.myrealm.data.SimpleClass.class, true, excludeFields);
        if (json.has("students")) {
            if (json.isNull("students")) {
                ((SimpleClassRealmProxyInterface) obj).realmSet$students(null);
            } else {
                ((SimpleClassRealmProxyInterface) obj).realmGet$students().clear();
                JSONArray array = json.getJSONArray("students");
                for (int i = 0; i < array.length(); i++) {
                    com.ybyb.zzq.myrealm.data.Student item = StudentRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((SimpleClassRealmProxyInterface) obj).realmGet$students().add(item);
                }
            }
        }
        if (json.has("className")) {
            if (json.isNull("className")) {
                ((SimpleClassRealmProxyInterface) obj).realmSet$className(null);
            } else {
                ((SimpleClassRealmProxyInterface) obj).realmSet$className((String) json.getString("className"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.ybyb.zzq.myrealm.data.SimpleClass createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.ybyb.zzq.myrealm.data.SimpleClass obj = new com.ybyb.zzq.myrealm.data.SimpleClass();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("students")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((SimpleClassRealmProxyInterface) obj).realmSet$students(null);
                } else {
                    ((SimpleClassRealmProxyInterface) obj).realmSet$students(new RealmList<com.ybyb.zzq.myrealm.data.Student>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ybyb.zzq.myrealm.data.Student item = StudentRealmProxy.createUsingJsonStream(realm, reader);
                        ((SimpleClassRealmProxyInterface) obj).realmGet$students().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("className")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((SimpleClassRealmProxyInterface) obj).realmSet$className(null);
                } else {
                    ((SimpleClassRealmProxyInterface) obj).realmSet$className((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.ybyb.zzq.myrealm.data.SimpleClass copyOrUpdate(Realm realm, com.ybyb.zzq.myrealm.data.SimpleClass object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.myrealm.data.SimpleClass) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.ybyb.zzq.myrealm.data.SimpleClass copy(Realm realm, com.ybyb.zzq.myrealm.data.SimpleClass newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.myrealm.data.SimpleClass) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.ybyb.zzq.myrealm.data.SimpleClass realmObject = realm.createObjectInternal(com.ybyb.zzq.myrealm.data.SimpleClass.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);

            RealmList<com.ybyb.zzq.myrealm.data.Student> studentsList = ((SimpleClassRealmProxyInterface) newObject).realmGet$students();
            if (studentsList != null) {
                RealmList<com.ybyb.zzq.myrealm.data.Student> studentsRealmList = ((SimpleClassRealmProxyInterface) realmObject).realmGet$students();
                for (int i = 0; i < studentsList.size(); i++) {
                    com.ybyb.zzq.myrealm.data.Student studentsItem = studentsList.get(i);
                    com.ybyb.zzq.myrealm.data.Student cachestudents = (com.ybyb.zzq.myrealm.data.Student) cache.get(studentsItem);
                    if (cachestudents != null) {
                        studentsRealmList.add(cachestudents);
                    } else {
                        studentsRealmList.add(StudentRealmProxy.copyOrUpdate(realm, studentsList.get(i), update, cache));
                    }
                }
            }

            ((SimpleClassRealmProxyInterface) realmObject).realmSet$className(((SimpleClassRealmProxyInterface) newObject).realmGet$className());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.ybyb.zzq.myrealm.data.SimpleClass object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        long tableNativePtr = table.getNativePtr();
        SimpleClassColumnInfo columnInfo = (SimpleClassColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);

        RealmList<com.ybyb.zzq.myrealm.data.Student> studentsList = ((SimpleClassRealmProxyInterface) object).realmGet$students();
        if (studentsList != null) {
            long studentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.studentsIndex, rowIndex);
            for (com.ybyb.zzq.myrealm.data.Student studentsItem : studentsList) {
                Long cacheItemIndexstudents = cache.get(studentsItem);
                if (cacheItemIndexstudents == null) {
                    cacheItemIndexstudents = StudentRealmProxy.insert(realm, studentsItem, cache);
                }
                LinkView.nativeAdd(studentsNativeLinkViewPtr, cacheItemIndexstudents);
            }
        }

        String realmGet$className = ((SimpleClassRealmProxyInterface)object).realmGet$className();
        if (realmGet$className != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.classNameIndex, rowIndex, realmGet$className, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        long tableNativePtr = table.getNativePtr();
        SimpleClassColumnInfo columnInfo = (SimpleClassColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        com.ybyb.zzq.myrealm.data.SimpleClass object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.myrealm.data.SimpleClass) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);

                RealmList<com.ybyb.zzq.myrealm.data.Student> studentsList = ((SimpleClassRealmProxyInterface) object).realmGet$students();
                if (studentsList != null) {
                    long studentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.studentsIndex, rowIndex);
                    for (com.ybyb.zzq.myrealm.data.Student studentsItem : studentsList) {
                        Long cacheItemIndexstudents = cache.get(studentsItem);
                        if (cacheItemIndexstudents == null) {
                            cacheItemIndexstudents = StudentRealmProxy.insert(realm, studentsItem, cache);
                        }
                        LinkView.nativeAdd(studentsNativeLinkViewPtr, cacheItemIndexstudents);
                    }
                }

                String realmGet$className = ((SimpleClassRealmProxyInterface)object).realmGet$className();
                if (realmGet$className != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.classNameIndex, rowIndex, realmGet$className, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.ybyb.zzq.myrealm.data.SimpleClass object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        long tableNativePtr = table.getNativePtr();
        SimpleClassColumnInfo columnInfo = (SimpleClassColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);

        long studentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.studentsIndex, rowIndex);
        LinkView.nativeClear(studentsNativeLinkViewPtr);
        RealmList<com.ybyb.zzq.myrealm.data.Student> studentsList = ((SimpleClassRealmProxyInterface) object).realmGet$students();
        if (studentsList != null) {
            for (com.ybyb.zzq.myrealm.data.Student studentsItem : studentsList) {
                Long cacheItemIndexstudents = cache.get(studentsItem);
                if (cacheItemIndexstudents == null) {
                    cacheItemIndexstudents = StudentRealmProxy.insertOrUpdate(realm, studentsItem, cache);
                }
                LinkView.nativeAdd(studentsNativeLinkViewPtr, cacheItemIndexstudents);
            }
        }

        String realmGet$className = ((SimpleClassRealmProxyInterface)object).realmGet$className();
        if (realmGet$className != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.classNameIndex, rowIndex, realmGet$className, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.classNameIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        long tableNativePtr = table.getNativePtr();
        SimpleClassColumnInfo columnInfo = (SimpleClassColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.SimpleClass.class);
        com.ybyb.zzq.myrealm.data.SimpleClass object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.myrealm.data.SimpleClass) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);

                long studentsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.studentsIndex, rowIndex);
                LinkView.nativeClear(studentsNativeLinkViewPtr);
                RealmList<com.ybyb.zzq.myrealm.data.Student> studentsList = ((SimpleClassRealmProxyInterface) object).realmGet$students();
                if (studentsList != null) {
                    for (com.ybyb.zzq.myrealm.data.Student studentsItem : studentsList) {
                        Long cacheItemIndexstudents = cache.get(studentsItem);
                        if (cacheItemIndexstudents == null) {
                            cacheItemIndexstudents = StudentRealmProxy.insertOrUpdate(realm, studentsItem, cache);
                        }
                        LinkView.nativeAdd(studentsNativeLinkViewPtr, cacheItemIndexstudents);
                    }
                }

                String realmGet$className = ((SimpleClassRealmProxyInterface)object).realmGet$className();
                if (realmGet$className != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.classNameIndex, rowIndex, realmGet$className, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.classNameIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.ybyb.zzq.myrealm.data.SimpleClass createDetachedCopy(com.ybyb.zzq.myrealm.data.SimpleClass realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.ybyb.zzq.myrealm.data.SimpleClass unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.ybyb.zzq.myrealm.data.SimpleClass)cachedObject.object;
            } else {
                unmanagedObject = (com.ybyb.zzq.myrealm.data.SimpleClass)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.ybyb.zzq.myrealm.data.SimpleClass();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }

        // Deep copy of students
        if (currentDepth == maxDepth) {
            ((SimpleClassRealmProxyInterface) unmanagedObject).realmSet$students(null);
        } else {
            RealmList<com.ybyb.zzq.myrealm.data.Student> managedstudentsList = ((SimpleClassRealmProxyInterface) realmObject).realmGet$students();
            RealmList<com.ybyb.zzq.myrealm.data.Student> unmanagedstudentsList = new RealmList<com.ybyb.zzq.myrealm.data.Student>();
            ((SimpleClassRealmProxyInterface) unmanagedObject).realmSet$students(unmanagedstudentsList);
            int nextDepth = currentDepth + 1;
            int size = managedstudentsList.size();
            for (int i = 0; i < size; i++) {
                com.ybyb.zzq.myrealm.data.Student item = StudentRealmProxy.createDetachedCopy(managedstudentsList.get(i), nextDepth, maxDepth, cache);
                unmanagedstudentsList.add(item);
            }
        }
        ((SimpleClassRealmProxyInterface) unmanagedObject).realmSet$className(((SimpleClassRealmProxyInterface) realmObject).realmGet$className());
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("SimpleClass = proxy[");
        stringBuilder.append("{students:");
        stringBuilder.append("RealmList<Student>[").append(realmGet$students().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{className:");
        stringBuilder.append(realmGet$className() != null ? realmGet$className() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleClassRealmProxy aSimpleClass = (SimpleClassRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSimpleClass.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSimpleClass.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSimpleClass.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
