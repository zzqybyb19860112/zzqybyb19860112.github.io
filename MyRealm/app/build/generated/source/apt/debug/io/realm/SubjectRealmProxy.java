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

public class SubjectRealmProxy extends com.ybyb.zzq.myrealm.data.Subject
    implements RealmObjectProxy, SubjectRealmProxyInterface {

    static final class SubjectColumnInfo extends ColumnInfo {
        long nameIndex;
        long scoreIndex;
        long fullScoreIndex;

        SubjectColumnInfo(SharedRealm realm, Table table) {
            super(3);
            this.nameIndex = addColumnDetails(table, "name", RealmFieldType.STRING);
            this.scoreIndex = addColumnDetails(table, "score", RealmFieldType.DOUBLE);
            this.fullScoreIndex = addColumnDetails(table, "fullScore", RealmFieldType.INTEGER);
        }

        SubjectColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new SubjectColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final SubjectColumnInfo src = (SubjectColumnInfo) rawSrc;
            final SubjectColumnInfo dst = (SubjectColumnInfo) rawDst;
            dst.nameIndex = src.nameIndex;
            dst.scoreIndex = src.scoreIndex;
            dst.fullScoreIndex = src.fullScoreIndex;
        }
    }

    private SubjectColumnInfo columnInfo;
    private ProxyState<com.ybyb.zzq.myrealm.data.Subject> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("name");
        fieldNames.add("score");
        fieldNames.add("fullScore");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    SubjectRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (SubjectColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.ybyb.zzq.myrealm.data.Subject>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$name() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.nameIndex);
    }

    @Override
    public void realmSet$name(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.nameIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.nameIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.nameIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.nameIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$score() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.scoreIndex);
    }

    @Override
    public void realmSet$score(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.scoreIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.scoreIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$fullScore() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.fullScoreIndex);
    }

    @Override
    public void realmSet$fullScore(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.fullScoreIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.fullScoreIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Subject")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Subject");
            realmObjectSchema.add("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("score", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("fullScore", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("Subject");
    }

    public static SubjectColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Subject")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Subject' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Subject");
        final long columnCount = table.getColumnCount();
        if (columnCount != 3) {
            if (columnCount < 3) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 3 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 3 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 3 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final SubjectColumnInfo columnInfo = new SubjectColumnInfo(sharedRealm, table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("name")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'name' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("name") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'name' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.nameIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'name' is required. Either set @Required to field 'name' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("score")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'score' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("score") != RealmFieldType.DOUBLE) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'double' for field 'score' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.scoreIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'score' does support null values in the existing Realm file. Use corresponding boxed type for field 'score' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("fullScore")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'fullScore' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("fullScore") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'fullScore' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.fullScoreIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'fullScore' does support null values in the existing Realm file. Use corresponding boxed type for field 'fullScore' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Subject";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.ybyb.zzq.myrealm.data.Subject createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.ybyb.zzq.myrealm.data.Subject obj = realm.createObjectInternal(com.ybyb.zzq.myrealm.data.Subject.class, true, excludeFields);
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((SubjectRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((SubjectRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("score")) {
            if (json.isNull("score")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'score' to null.");
            } else {
                ((SubjectRealmProxyInterface) obj).realmSet$score((double) json.getDouble("score"));
            }
        }
        if (json.has("fullScore")) {
            if (json.isNull("fullScore")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'fullScore' to null.");
            } else {
                ((SubjectRealmProxyInterface) obj).realmSet$fullScore((int) json.getInt("fullScore"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.ybyb.zzq.myrealm.data.Subject createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.ybyb.zzq.myrealm.data.Subject obj = new com.ybyb.zzq.myrealm.data.Subject();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((SubjectRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((SubjectRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("score")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'score' to null.");
                } else {
                    ((SubjectRealmProxyInterface) obj).realmSet$score((double) reader.nextDouble());
                }
            } else if (name.equals("fullScore")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'fullScore' to null.");
                } else {
                    ((SubjectRealmProxyInterface) obj).realmSet$fullScore((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.ybyb.zzq.myrealm.data.Subject copyOrUpdate(Realm realm, com.ybyb.zzq.myrealm.data.Subject object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.myrealm.data.Subject) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.ybyb.zzq.myrealm.data.Subject copy(Realm realm, com.ybyb.zzq.myrealm.data.Subject newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.myrealm.data.Subject) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.ybyb.zzq.myrealm.data.Subject realmObject = realm.createObjectInternal(com.ybyb.zzq.myrealm.data.Subject.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((SubjectRealmProxyInterface) realmObject).realmSet$name(((SubjectRealmProxyInterface) newObject).realmGet$name());
            ((SubjectRealmProxyInterface) realmObject).realmSet$score(((SubjectRealmProxyInterface) newObject).realmGet$score());
            ((SubjectRealmProxyInterface) realmObject).realmSet$fullScore(((SubjectRealmProxyInterface) newObject).realmGet$fullScore());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.ybyb.zzq.myrealm.data.Subject object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Subject.class);
        long tableNativePtr = table.getNativePtr();
        SubjectColumnInfo columnInfo = (SubjectColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Subject.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$name = ((SubjectRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.scoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$score(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.fullScoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$fullScore(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Subject.class);
        long tableNativePtr = table.getNativePtr();
        SubjectColumnInfo columnInfo = (SubjectColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Subject.class);
        com.ybyb.zzq.myrealm.data.Subject object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.myrealm.data.Subject) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$name = ((SubjectRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                Table.nativeSetDouble(tableNativePtr, columnInfo.scoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$score(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.fullScoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$fullScore(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.ybyb.zzq.myrealm.data.Subject object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Subject.class);
        long tableNativePtr = table.getNativePtr();
        SubjectColumnInfo columnInfo = (SubjectColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Subject.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$name = ((SubjectRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetDouble(tableNativePtr, columnInfo.scoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$score(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.fullScoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$fullScore(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Subject.class);
        long tableNativePtr = table.getNativePtr();
        SubjectColumnInfo columnInfo = (SubjectColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Subject.class);
        com.ybyb.zzq.myrealm.data.Subject object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.myrealm.data.Subject) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$name = ((SubjectRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                Table.nativeSetDouble(tableNativePtr, columnInfo.scoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$score(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.fullScoreIndex, rowIndex, ((SubjectRealmProxyInterface)object).realmGet$fullScore(), false);
            }
        }
    }

    public static com.ybyb.zzq.myrealm.data.Subject createDetachedCopy(com.ybyb.zzq.myrealm.data.Subject realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.ybyb.zzq.myrealm.data.Subject unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.ybyb.zzq.myrealm.data.Subject)cachedObject.object;
            } else {
                unmanagedObject = (com.ybyb.zzq.myrealm.data.Subject)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.ybyb.zzq.myrealm.data.Subject();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((SubjectRealmProxyInterface) unmanagedObject).realmSet$name(((SubjectRealmProxyInterface) realmObject).realmGet$name());
        ((SubjectRealmProxyInterface) unmanagedObject).realmSet$score(((SubjectRealmProxyInterface) realmObject).realmGet$score());
        ((SubjectRealmProxyInterface) unmanagedObject).realmSet$fullScore(((SubjectRealmProxyInterface) realmObject).realmGet$fullScore());
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Subject = proxy[");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{score:");
        stringBuilder.append(realmGet$score());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{fullScore:");
        stringBuilder.append(realmGet$fullScore());
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
        SubjectRealmProxy aSubject = (SubjectRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aSubject.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aSubject.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aSubject.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
