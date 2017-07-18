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

public class HKnowledgeRealmProxy extends com.ybyb.zzq.h_know.data.local.HKnowledge
    implements RealmObjectProxy, HKnowledgeRealmProxyInterface {

    static final class HKnowledgeColumnInfo extends ColumnInfo {
        long datasIndex;

        HKnowledgeColumnInfo(SharedRealm realm, Table table) {
            super(1);
            this.datasIndex = addColumnDetails(table, "datas", RealmFieldType.LIST);
        }

        HKnowledgeColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new HKnowledgeColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final HKnowledgeColumnInfo src = (HKnowledgeColumnInfo) rawSrc;
            final HKnowledgeColumnInfo dst = (HKnowledgeColumnInfo) rawDst;
            dst.datasIndex = src.datasIndex;
        }
    }

    private HKnowledgeColumnInfo columnInfo;
    private ProxyState<com.ybyb.zzq.h_know.data.local.HKnowledge> proxyState;
    private RealmList<com.ybyb.zzq.h_know.data.local.Type> datasRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("datas");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    HKnowledgeRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (HKnowledgeColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.ybyb.zzq.h_know.data.local.HKnowledge>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    public RealmList<com.ybyb.zzq.h_know.data.local.Type> realmGet$datas() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (datasRealmList != null) {
            return datasRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.datasIndex);
            datasRealmList = new RealmList<com.ybyb.zzq.h_know.data.local.Type>(com.ybyb.zzq.h_know.data.local.Type.class, linkView, proxyState.getRealm$realm());
            return datasRealmList;
        }
    }

    @Override
    public void realmSet$datas(RealmList<com.ybyb.zzq.h_know.data.local.Type> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("datas")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.ybyb.zzq.h_know.data.local.Type> original = value;
                value = new RealmList<com.ybyb.zzq.h_know.data.local.Type>();
                for (com.ybyb.zzq.h_know.data.local.Type item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.datasIndex);
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

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("HKnowledge")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("HKnowledge");
            if (!realmSchema.contains("Type")) {
                TypeRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("datas", RealmFieldType.LIST, realmSchema.get("Type"));
            return realmObjectSchema;
        }
        return realmSchema.get("HKnowledge");
    }

    public static HKnowledgeColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_HKnowledge")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'HKnowledge' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_HKnowledge");
        final long columnCount = table.getColumnCount();
        if (columnCount != 1) {
            if (columnCount < 1) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 1 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 1 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 1 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final HKnowledgeColumnInfo columnInfo = new HKnowledgeColumnInfo(sharedRealm, table);

        if (table.hasPrimaryKey()) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Primary Key defined for field " + table.getColumnName(table.getPrimaryKey()) + " was removed.");
        }

        if (!columnTypes.containsKey("datas")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'datas'");
        }
        if (columnTypes.get("datas") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Type' for field 'datas'");
        }
        if (!sharedRealm.hasTable("class_Type")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Type' for field 'datas'");
        }
        Table table_0 = sharedRealm.getTable("class_Type");
        if (!table.getLinkTarget(columnInfo.datasIndex).hasSameSchema(table_0)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'datas': '" + table.getLinkTarget(columnInfo.datasIndex).getName() + "' expected - was '" + table_0.getName() + "'");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_HKnowledge";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.ybyb.zzq.h_know.data.local.HKnowledge createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("datas")) {
            excludeFields.add("datas");
        }
        com.ybyb.zzq.h_know.data.local.HKnowledge obj = realm.createObjectInternal(com.ybyb.zzq.h_know.data.local.HKnowledge.class, true, excludeFields);
        if (json.has("datas")) {
            if (json.isNull("datas")) {
                ((HKnowledgeRealmProxyInterface) obj).realmSet$datas(null);
            } else {
                ((HKnowledgeRealmProxyInterface) obj).realmGet$datas().clear();
                JSONArray array = json.getJSONArray("datas");
                for (int i = 0; i < array.length(); i++) {
                    com.ybyb.zzq.h_know.data.local.Type item = TypeRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((HKnowledgeRealmProxyInterface) obj).realmGet$datas().add(item);
                }
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.ybyb.zzq.h_know.data.local.HKnowledge createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.ybyb.zzq.h_know.data.local.HKnowledge obj = new com.ybyb.zzq.h_know.data.local.HKnowledge();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("datas")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((HKnowledgeRealmProxyInterface) obj).realmSet$datas(null);
                } else {
                    ((HKnowledgeRealmProxyInterface) obj).realmSet$datas(new RealmList<com.ybyb.zzq.h_know.data.local.Type>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ybyb.zzq.h_know.data.local.Type item = TypeRealmProxy.createUsingJsonStream(realm, reader);
                        ((HKnowledgeRealmProxyInterface) obj).realmGet$datas().add(item);
                    }
                    reader.endArray();
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.ybyb.zzq.h_know.data.local.HKnowledge copyOrUpdate(Realm realm, com.ybyb.zzq.h_know.data.local.HKnowledge object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.h_know.data.local.HKnowledge) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.ybyb.zzq.h_know.data.local.HKnowledge copy(Realm realm, com.ybyb.zzq.h_know.data.local.HKnowledge newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.h_know.data.local.HKnowledge) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.ybyb.zzq.h_know.data.local.HKnowledge realmObject = realm.createObjectInternal(com.ybyb.zzq.h_know.data.local.HKnowledge.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);

            RealmList<com.ybyb.zzq.h_know.data.local.Type> datasList = ((HKnowledgeRealmProxyInterface) newObject).realmGet$datas();
            if (datasList != null) {
                RealmList<com.ybyb.zzq.h_know.data.local.Type> datasRealmList = ((HKnowledgeRealmProxyInterface) realmObject).realmGet$datas();
                for (int i = 0; i < datasList.size(); i++) {
                    com.ybyb.zzq.h_know.data.local.Type datasItem = datasList.get(i);
                    com.ybyb.zzq.h_know.data.local.Type cachedatas = (com.ybyb.zzq.h_know.data.local.Type) cache.get(datasItem);
                    if (cachedatas != null) {
                        datasRealmList.add(cachedatas);
                    } else {
                        datasRealmList.add(TypeRealmProxy.copyOrUpdate(realm, datasList.get(i), update, cache));
                    }
                }
            }

            return realmObject;
        }
    }

    public static long insert(Realm realm, com.ybyb.zzq.h_know.data.local.HKnowledge object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        long tableNativePtr = table.getNativePtr();
        HKnowledgeColumnInfo columnInfo = (HKnowledgeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);

        RealmList<com.ybyb.zzq.h_know.data.local.Type> datasList = ((HKnowledgeRealmProxyInterface) object).realmGet$datas();
        if (datasList != null) {
            long datasNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.datasIndex, rowIndex);
            for (com.ybyb.zzq.h_know.data.local.Type datasItem : datasList) {
                Long cacheItemIndexdatas = cache.get(datasItem);
                if (cacheItemIndexdatas == null) {
                    cacheItemIndexdatas = TypeRealmProxy.insert(realm, datasItem, cache);
                }
                LinkView.nativeAdd(datasNativeLinkViewPtr, cacheItemIndexdatas);
            }
        }

        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        long tableNativePtr = table.getNativePtr();
        HKnowledgeColumnInfo columnInfo = (HKnowledgeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        com.ybyb.zzq.h_know.data.local.HKnowledge object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.h_know.data.local.HKnowledge) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);

                RealmList<com.ybyb.zzq.h_know.data.local.Type> datasList = ((HKnowledgeRealmProxyInterface) object).realmGet$datas();
                if (datasList != null) {
                    long datasNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.datasIndex, rowIndex);
                    for (com.ybyb.zzq.h_know.data.local.Type datasItem : datasList) {
                        Long cacheItemIndexdatas = cache.get(datasItem);
                        if (cacheItemIndexdatas == null) {
                            cacheItemIndexdatas = TypeRealmProxy.insert(realm, datasItem, cache);
                        }
                        LinkView.nativeAdd(datasNativeLinkViewPtr, cacheItemIndexdatas);
                    }
                }

            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.ybyb.zzq.h_know.data.local.HKnowledge object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        long tableNativePtr = table.getNativePtr();
        HKnowledgeColumnInfo columnInfo = (HKnowledgeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);

        long datasNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.datasIndex, rowIndex);
        LinkView.nativeClear(datasNativeLinkViewPtr);
        RealmList<com.ybyb.zzq.h_know.data.local.Type> datasList = ((HKnowledgeRealmProxyInterface) object).realmGet$datas();
        if (datasList != null) {
            for (com.ybyb.zzq.h_know.data.local.Type datasItem : datasList) {
                Long cacheItemIndexdatas = cache.get(datasItem);
                if (cacheItemIndexdatas == null) {
                    cacheItemIndexdatas = TypeRealmProxy.insertOrUpdate(realm, datasItem, cache);
                }
                LinkView.nativeAdd(datasNativeLinkViewPtr, cacheItemIndexdatas);
            }
        }

        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        long tableNativePtr = table.getNativePtr();
        HKnowledgeColumnInfo columnInfo = (HKnowledgeColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.h_know.data.local.HKnowledge.class);
        com.ybyb.zzq.h_know.data.local.HKnowledge object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.h_know.data.local.HKnowledge) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);

                long datasNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.datasIndex, rowIndex);
                LinkView.nativeClear(datasNativeLinkViewPtr);
                RealmList<com.ybyb.zzq.h_know.data.local.Type> datasList = ((HKnowledgeRealmProxyInterface) object).realmGet$datas();
                if (datasList != null) {
                    for (com.ybyb.zzq.h_know.data.local.Type datasItem : datasList) {
                        Long cacheItemIndexdatas = cache.get(datasItem);
                        if (cacheItemIndexdatas == null) {
                            cacheItemIndexdatas = TypeRealmProxy.insertOrUpdate(realm, datasItem, cache);
                        }
                        LinkView.nativeAdd(datasNativeLinkViewPtr, cacheItemIndexdatas);
                    }
                }

            }
        }
    }

    public static com.ybyb.zzq.h_know.data.local.HKnowledge createDetachedCopy(com.ybyb.zzq.h_know.data.local.HKnowledge realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.ybyb.zzq.h_know.data.local.HKnowledge unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.ybyb.zzq.h_know.data.local.HKnowledge)cachedObject.object;
            } else {
                unmanagedObject = (com.ybyb.zzq.h_know.data.local.HKnowledge)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.ybyb.zzq.h_know.data.local.HKnowledge();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }

        // Deep copy of datas
        if (currentDepth == maxDepth) {
            ((HKnowledgeRealmProxyInterface) unmanagedObject).realmSet$datas(null);
        } else {
            RealmList<com.ybyb.zzq.h_know.data.local.Type> manageddatasList = ((HKnowledgeRealmProxyInterface) realmObject).realmGet$datas();
            RealmList<com.ybyb.zzq.h_know.data.local.Type> unmanageddatasList = new RealmList<com.ybyb.zzq.h_know.data.local.Type>();
            ((HKnowledgeRealmProxyInterface) unmanagedObject).realmSet$datas(unmanageddatasList);
            int nextDepth = currentDepth + 1;
            int size = manageddatasList.size();
            for (int i = 0; i < size; i++) {
                com.ybyb.zzq.h_know.data.local.Type item = TypeRealmProxy.createDetachedCopy(manageddatasList.get(i), nextDepth, maxDepth, cache);
                unmanageddatasList.add(item);
            }
        }
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("HKnowledge = proxy[");
        stringBuilder.append("{datas:");
        stringBuilder.append("RealmList<Type>[").append(realmGet$datas().size()).append("]");
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
        HKnowledgeRealmProxy aHKnowledge = (HKnowledgeRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aHKnowledge.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aHKnowledge.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aHKnowledge.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
