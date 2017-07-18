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

public class TypeRealmProxy extends com.ybyb.zzq.h_know.data.local.Type
    implements RealmObjectProxy, TypeRealmProxyInterface {

    static final class TypeColumnInfo extends ColumnInfo {
        long idIndex;
        long nameIndex;
        long descriptionIndex;

        TypeColumnInfo(SharedRealm realm, Table table) {
            super(3);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.nameIndex = addColumnDetails(table, "name", RealmFieldType.STRING);
            this.descriptionIndex = addColumnDetails(table, "description", RealmFieldType.STRING);
        }

        TypeColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new TypeColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final TypeColumnInfo src = (TypeColumnInfo) rawSrc;
            final TypeColumnInfo dst = (TypeColumnInfo) rawDst;
            dst.idIndex = src.idIndex;
            dst.nameIndex = src.nameIndex;
            dst.descriptionIndex = src.descriptionIndex;
        }
    }

    private TypeColumnInfo columnInfo;
    private ProxyState<com.ybyb.zzq.h_know.data.local.Type> proxyState;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("id");
        fieldNames.add("name");
        fieldNames.add("description");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    TypeRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (TypeColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.ybyb.zzq.h_know.data.local.Type>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.idIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.idIndex, value);
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
    public String realmGet$description() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.descriptionIndex);
    }

    @Override
    public void realmSet$description(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.descriptionIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.descriptionIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.descriptionIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.descriptionIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Type")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Type");
            realmObjectSchema.add("id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("description", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("Type");
    }

    public static TypeColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Type")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Type' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Type");
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

        final TypeColumnInfo columnInfo = new TypeColumnInfo(sharedRealm, table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("id") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'id' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.idIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using RealmObjectSchema.setNullable().");
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
        if (!columnTypes.containsKey("description")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'description' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("description") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'description' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.descriptionIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'description' is required. Either set @Required to field 'description' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Type";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.ybyb.zzq.h_know.data.local.Type createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.ybyb.zzq.h_know.data.local.Type obj = realm.createObjectInternal(com.ybyb.zzq.h_know.data.local.Type.class, true, excludeFields);
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                ((TypeRealmProxyInterface) obj).realmSet$id((int) json.getInt("id"));
            }
        }
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((TypeRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((TypeRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("description")) {
            if (json.isNull("description")) {
                ((TypeRealmProxyInterface) obj).realmSet$description(null);
            } else {
                ((TypeRealmProxyInterface) obj).realmSet$description((String) json.getString("description"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.ybyb.zzq.h_know.data.local.Type createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.ybyb.zzq.h_know.data.local.Type obj = new com.ybyb.zzq.h_know.data.local.Type();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((TypeRealmProxyInterface) obj).realmSet$id((int) reader.nextInt());
                }
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TypeRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((TypeRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("description")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((TypeRealmProxyInterface) obj).realmSet$description(null);
                } else {
                    ((TypeRealmProxyInterface) obj).realmSet$description((String) reader.nextString());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.ybyb.zzq.h_know.data.local.Type copyOrUpdate(Realm realm, com.ybyb.zzq.h_know.data.local.Type object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.h_know.data.local.Type) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.ybyb.zzq.h_know.data.local.Type copy(Realm realm, com.ybyb.zzq.h_know.data.local.Type newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.h_know.data.local.Type) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.ybyb.zzq.h_know.data.local.Type realmObject = realm.createObjectInternal(com.ybyb.zzq.h_know.data.local.Type.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((TypeRealmProxyInterface) realmObject).realmSet$id(((TypeRealmProxyInterface) newObject).realmGet$id());
            ((TypeRealmProxyInterface) realmObject).realmSet$name(((TypeRealmProxyInterface) newObject).realmGet$name());
            ((TypeRealmProxyInterface) realmObject).realmSet$description(((TypeRealmProxyInterface) newObject).realmGet$description());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.ybyb.zzq.h_know.data.local.Type object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.Type.class);
        long tableNativePtr = table.getNativePtr();
        TypeColumnInfo columnInfo = (TypeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.Type.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((TypeRealmProxyInterface)object).realmGet$id(), false);
        String realmGet$name = ((TypeRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        String realmGet$description = ((TypeRealmProxyInterface)object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.Type.class);
        long tableNativePtr = table.getNativePtr();
        TypeColumnInfo columnInfo = (TypeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.Type.class);
        com.ybyb.zzq.h_know.data.local.Type object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.h_know.data.local.Type) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((TypeRealmProxyInterface)object).realmGet$id(), false);
                String realmGet$name = ((TypeRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                String realmGet$description = ((TypeRealmProxyInterface)object).realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                }
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.ybyb.zzq.h_know.data.local.Type object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.Type.class);
        long tableNativePtr = table.getNativePtr();
        TypeColumnInfo columnInfo = (TypeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.Type.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((TypeRealmProxyInterface)object).realmGet$id(), false);
        String realmGet$name = ((TypeRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        String realmGet$description = ((TypeRealmProxyInterface)object).realmGet$description();
        if (realmGet$description != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.Type.class);
        long tableNativePtr = table.getNativePtr();
        TypeColumnInfo columnInfo = (TypeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.Type.class);
        com.ybyb.zzq.h_know.data.local.Type object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.h_know.data.local.Type) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((TypeRealmProxyInterface)object).realmGet$id(), false);
                String realmGet$name = ((TypeRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                String realmGet$description = ((TypeRealmProxyInterface)object).realmGet$description();
                if (realmGet$description != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.descriptionIndex, rowIndex, realmGet$description, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.descriptionIndex, rowIndex, false);
                }
            }
        }
    }

    public static com.ybyb.zzq.h_know.data.local.Type createDetachedCopy(com.ybyb.zzq.h_know.data.local.Type realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.ybyb.zzq.h_know.data.local.Type unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.ybyb.zzq.h_know.data.local.Type)cachedObject.object;
            } else {
                unmanagedObject = (com.ybyb.zzq.h_know.data.local.Type)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.ybyb.zzq.h_know.data.local.Type();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((TypeRealmProxyInterface) unmanagedObject).realmSet$id(((TypeRealmProxyInterface) realmObject).realmGet$id());
        ((TypeRealmProxyInterface) unmanagedObject).realmSet$name(((TypeRealmProxyInterface) realmObject).realmGet$name());
        ((TypeRealmProxyInterface) unmanagedObject).realmSet$description(((TypeRealmProxyInterface) realmObject).realmGet$description());
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Type = proxy[");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{description:");
        stringBuilder.append(realmGet$description() != null ? realmGet$description() : "null");
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
        TypeRealmProxy aType = (TypeRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aType.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aType.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aType.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
