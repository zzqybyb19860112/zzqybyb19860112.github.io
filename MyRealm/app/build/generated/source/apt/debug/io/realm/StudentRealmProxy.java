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

public class StudentRealmProxy extends com.ybyb.zzq.myrealm.data.Student
    implements RealmObjectProxy, StudentRealmProxyInterface {

    static final class StudentColumnInfo extends ColumnInfo {
        long nameIndex;
        long idIndex;
        long ageIndex;
        long genderIndex;
        long subjectsIndex;
        long totalScoreIndex;
        long averageScoreIndex;
        long orderScoreIndex;

        StudentColumnInfo(SharedRealm realm, Table table) {
            super(8);
            this.nameIndex = addColumnDetails(table, "name", RealmFieldType.STRING);
            this.idIndex = addColumnDetails(table, "id", RealmFieldType.INTEGER);
            this.ageIndex = addColumnDetails(table, "age", RealmFieldType.INTEGER);
            this.genderIndex = addColumnDetails(table, "gender", RealmFieldType.STRING);
            this.subjectsIndex = addColumnDetails(table, "subjects", RealmFieldType.LIST);
            this.totalScoreIndex = addColumnDetails(table, "totalScore", RealmFieldType.DOUBLE);
            this.averageScoreIndex = addColumnDetails(table, "averageScore", RealmFieldType.DOUBLE);
            this.orderScoreIndex = addColumnDetails(table, "orderScore", RealmFieldType.INTEGER);
        }

        StudentColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new StudentColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final StudentColumnInfo src = (StudentColumnInfo) rawSrc;
            final StudentColumnInfo dst = (StudentColumnInfo) rawDst;
            dst.nameIndex = src.nameIndex;
            dst.idIndex = src.idIndex;
            dst.ageIndex = src.ageIndex;
            dst.genderIndex = src.genderIndex;
            dst.subjectsIndex = src.subjectsIndex;
            dst.totalScoreIndex = src.totalScoreIndex;
            dst.averageScoreIndex = src.averageScoreIndex;
            dst.orderScoreIndex = src.orderScoreIndex;
        }
    }

    private StudentColumnInfo columnInfo;
    private ProxyState<com.ybyb.zzq.myrealm.data.Student> proxyState;
    private RealmList<com.ybyb.zzq.myrealm.data.Subject> subjectsRealmList;
    private static final List<String> FIELD_NAMES;
    static {
        List<String> fieldNames = new ArrayList<String>();
        fieldNames.add("name");
        fieldNames.add("id");
        fieldNames.add("age");
        fieldNames.add("gender");
        fieldNames.add("subjects");
        fieldNames.add("totalScore");
        fieldNames.add("averageScore");
        fieldNames.add("orderScore");
        FIELD_NAMES = Collections.unmodifiableList(fieldNames);
    }

    StudentRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (StudentColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.ybyb.zzq.myrealm.data.Student>(this);
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
    public long realmGet$id() {
        proxyState.getRealm$realm().checkIfValid();
        return (long) proxyState.getRow$realm().getLong(columnInfo.idIndex);
    }

    @Override
    public void realmSet$id(long value) {
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
    public int realmGet$age() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.ageIndex);
    }

    @Override
    public void realmSet$age(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.ageIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.ageIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$gender() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.genderIndex);
    }

    @Override
    public void realmSet$gender(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.genderIndex, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.genderIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.genderIndex);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.genderIndex, value);
    }

    @Override
    public RealmList<com.ybyb.zzq.myrealm.data.Subject> realmGet$subjects() {
        proxyState.getRealm$realm().checkIfValid();
        // use the cached value if available
        if (subjectsRealmList != null) {
            return subjectsRealmList;
        } else {
            LinkView linkView = proxyState.getRow$realm().getLinkList(columnInfo.subjectsIndex);
            subjectsRealmList = new RealmList<com.ybyb.zzq.myrealm.data.Subject>(com.ybyb.zzq.myrealm.data.Subject.class, linkView, proxyState.getRealm$realm());
            return subjectsRealmList;
        }
    }

    @Override
    public void realmSet$subjects(RealmList<com.ybyb.zzq.myrealm.data.Subject> value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            if (proxyState.getExcludeFields$realm().contains("subjects")) {
                return;
            }
            if (value != null && !value.isManaged()) {
                final Realm realm = (Realm) proxyState.getRealm$realm();
                final RealmList<com.ybyb.zzq.myrealm.data.Subject> original = value;
                value = new RealmList<com.ybyb.zzq.myrealm.data.Subject>();
                for (com.ybyb.zzq.myrealm.data.Subject item : original) {
                    if (item == null || RealmObject.isManaged(item)) {
                        value.add(item);
                    } else {
                        value.add(realm.copyToRealm(item));
                    }
                }
            }
        }

        proxyState.getRealm$realm().checkIfValid();
        LinkView links = proxyState.getRow$realm().getLinkList(columnInfo.subjectsIndex);
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
    public double realmGet$totalScore() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.totalScoreIndex);
    }

    @Override
    public void realmSet$totalScore(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.totalScoreIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.totalScoreIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public double realmGet$averageScore() {
        proxyState.getRealm$realm().checkIfValid();
        return (double) proxyState.getRow$realm().getDouble(columnInfo.averageScoreIndex);
    }

    @Override
    public void realmSet$averageScore(double value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setDouble(columnInfo.averageScoreIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setDouble(columnInfo.averageScoreIndex, value);
    }

    @Override
    @SuppressWarnings("cast")
    public int realmGet$orderScore() {
        proxyState.getRealm$realm().checkIfValid();
        return (int) proxyState.getRow$realm().getLong(columnInfo.orderScoreIndex);
    }

    @Override
    public void realmSet$orderScore(int value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            row.getTable().setLong(columnInfo.orderScoreIndex, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        proxyState.getRow$realm().setLong(columnInfo.orderScoreIndex, value);
    }

    public static RealmObjectSchema createRealmObjectSchema(RealmSchema realmSchema) {
        if (!realmSchema.contains("Student")) {
            RealmObjectSchema realmObjectSchema = realmSchema.create("Student");
            realmObjectSchema.add("name", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            realmObjectSchema.add("id", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("age", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("gender", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
            if (!realmSchema.contains("Subject")) {
                SubjectRealmProxy.createRealmObjectSchema(realmSchema);
            }
            realmObjectSchema.add("subjects", RealmFieldType.LIST, realmSchema.get("Subject"));
            realmObjectSchema.add("totalScore", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("averageScore", RealmFieldType.DOUBLE, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            realmObjectSchema.add("orderScore", RealmFieldType.INTEGER, !Property.PRIMARY_KEY, !Property.INDEXED, Property.REQUIRED);
            return realmObjectSchema;
        }
        return realmSchema.get("Student");
    }

    public static StudentColumnInfo validateTable(SharedRealm sharedRealm, boolean allowExtraColumns) {
        if (!sharedRealm.hasTable("class_Student")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "The 'Student' class is missing from the schema for this Realm.");
        }
        Table table = sharedRealm.getTable("class_Student");
        final long columnCount = table.getColumnCount();
        if (columnCount != 8) {
            if (columnCount < 8) {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is less than expected - expected 8 but was " + columnCount);
            }
            if (allowExtraColumns) {
                RealmLog.debug("Field count is more than expected - expected 8 but was %1$d", columnCount);
            } else {
                throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field count is more than expected - expected 8 but was " + columnCount);
            }
        }
        Map<String, RealmFieldType> columnTypes = new HashMap<String, RealmFieldType>();
        for (long i = 0; i < columnCount; i++) {
            columnTypes.put(table.getColumnName(i), table.getColumnType(i));
        }

        final StudentColumnInfo columnInfo = new StudentColumnInfo(sharedRealm, table);

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
        if (!columnTypes.containsKey("id")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'id' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("id") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'long' for field 'id' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.idIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'id' does support null values in the existing Realm file. Use corresponding boxed type for field 'id' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("age")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'age' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("age") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'age' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.ageIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'age' does support null values in the existing Realm file. Use corresponding boxed type for field 'age' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("gender")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'gender' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("gender") != RealmFieldType.STRING) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'String' for field 'gender' in existing Realm file.");
        }
        if (!table.isColumnNullable(columnInfo.genderIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'gender' is required. Either set @Required to field 'gender' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("subjects")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'subjects'");
        }
        if (columnTypes.get("subjects") != RealmFieldType.LIST) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'Subject' for field 'subjects'");
        }
        if (!sharedRealm.hasTable("class_Subject")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing class 'class_Subject' for field 'subjects'");
        }
        Table table_4 = sharedRealm.getTable("class_Subject");
        if (!table.getLinkTarget(columnInfo.subjectsIndex).hasSameSchema(table_4)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid RealmList type for field 'subjects': '" + table.getLinkTarget(columnInfo.subjectsIndex).getName() + "' expected - was '" + table_4.getName() + "'");
        }
        if (!columnTypes.containsKey("totalScore")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'totalScore' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("totalScore") != RealmFieldType.DOUBLE) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'double' for field 'totalScore' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.totalScoreIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'totalScore' does support null values in the existing Realm file. Use corresponding boxed type for field 'totalScore' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("averageScore")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'averageScore' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("averageScore") != RealmFieldType.DOUBLE) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'double' for field 'averageScore' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.averageScoreIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'averageScore' does support null values in the existing Realm file. Use corresponding boxed type for field 'averageScore' or migrate using RealmObjectSchema.setNullable().");
        }
        if (!columnTypes.containsKey("orderScore")) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Missing field 'orderScore' in existing Realm file. Either remove field or migrate using io.realm.internal.Table.addColumn().");
        }
        if (columnTypes.get("orderScore") != RealmFieldType.INTEGER) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Invalid type 'int' for field 'orderScore' in existing Realm file.");
        }
        if (table.isColumnNullable(columnInfo.orderScoreIndex)) {
            throw new RealmMigrationNeededException(sharedRealm.getPath(), "Field 'orderScore' does support null values in the existing Realm file. Use corresponding boxed type for field 'orderScore' or migrate using RealmObjectSchema.setNullable().");
        }

        return columnInfo;
    }

    public static String getTableName() {
        return "class_Student";
    }

    public static List<String> getFieldNames() {
        return FIELD_NAMES;
    }

    @SuppressWarnings("cast")
    public static com.ybyb.zzq.myrealm.data.Student createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = new ArrayList<String>(1);
        if (json.has("subjects")) {
            excludeFields.add("subjects");
        }
        com.ybyb.zzq.myrealm.data.Student obj = realm.createObjectInternal(com.ybyb.zzq.myrealm.data.Student.class, true, excludeFields);
        if (json.has("name")) {
            if (json.isNull("name")) {
                ((StudentRealmProxyInterface) obj).realmSet$name(null);
            } else {
                ((StudentRealmProxyInterface) obj).realmSet$name((String) json.getString("name"));
            }
        }
        if (json.has("id")) {
            if (json.isNull("id")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
            } else {
                ((StudentRealmProxyInterface) obj).realmSet$id((long) json.getLong("id"));
            }
        }
        if (json.has("age")) {
            if (json.isNull("age")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'age' to null.");
            } else {
                ((StudentRealmProxyInterface) obj).realmSet$age((int) json.getInt("age"));
            }
        }
        if (json.has("gender")) {
            if (json.isNull("gender")) {
                ((StudentRealmProxyInterface) obj).realmSet$gender(null);
            } else {
                ((StudentRealmProxyInterface) obj).realmSet$gender((String) json.getString("gender"));
            }
        }
        if (json.has("subjects")) {
            if (json.isNull("subjects")) {
                ((StudentRealmProxyInterface) obj).realmSet$subjects(null);
            } else {
                ((StudentRealmProxyInterface) obj).realmGet$subjects().clear();
                JSONArray array = json.getJSONArray("subjects");
                for (int i = 0; i < array.length(); i++) {
                    com.ybyb.zzq.myrealm.data.Subject item = SubjectRealmProxy.createOrUpdateUsingJsonObject(realm, array.getJSONObject(i), update);
                    ((StudentRealmProxyInterface) obj).realmGet$subjects().add(item);
                }
            }
        }
        if (json.has("totalScore")) {
            if (json.isNull("totalScore")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'totalScore' to null.");
            } else {
                ((StudentRealmProxyInterface) obj).realmSet$totalScore((double) json.getDouble("totalScore"));
            }
        }
        if (json.has("averageScore")) {
            if (json.isNull("averageScore")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'averageScore' to null.");
            } else {
                ((StudentRealmProxyInterface) obj).realmSet$averageScore((double) json.getDouble("averageScore"));
            }
        }
        if (json.has("orderScore")) {
            if (json.isNull("orderScore")) {
                throw new IllegalArgumentException("Trying to set non-nullable field 'orderScore' to null.");
            } else {
                ((StudentRealmProxyInterface) obj).realmSet$orderScore((int) json.getInt("orderScore"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.ybyb.zzq.myrealm.data.Student createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        com.ybyb.zzq.myrealm.data.Student obj = new com.ybyb.zzq.myrealm.data.Student();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("name")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((StudentRealmProxyInterface) obj).realmSet$name(null);
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$name((String) reader.nextString());
                }
            } else if (name.equals("id")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'id' to null.");
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$id((long) reader.nextLong());
                }
            } else if (name.equals("age")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'age' to null.");
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$age((int) reader.nextInt());
                }
            } else if (name.equals("gender")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((StudentRealmProxyInterface) obj).realmSet$gender(null);
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$gender((String) reader.nextString());
                }
            } else if (name.equals("subjects")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    ((StudentRealmProxyInterface) obj).realmSet$subjects(null);
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$subjects(new RealmList<com.ybyb.zzq.myrealm.data.Subject>());
                    reader.beginArray();
                    while (reader.hasNext()) {
                        com.ybyb.zzq.myrealm.data.Subject item = SubjectRealmProxy.createUsingJsonStream(realm, reader);
                        ((StudentRealmProxyInterface) obj).realmGet$subjects().add(item);
                    }
                    reader.endArray();
                }
            } else if (name.equals("totalScore")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'totalScore' to null.");
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$totalScore((double) reader.nextDouble());
                }
            } else if (name.equals("averageScore")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'averageScore' to null.");
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$averageScore((double) reader.nextDouble());
                }
            } else if (name.equals("orderScore")) {
                if (reader.peek() == JsonToken.NULL) {
                    reader.skipValue();
                    throw new IllegalArgumentException("Trying to set non-nullable field 'orderScore' to null.");
                } else {
                    ((StudentRealmProxyInterface) obj).realmSet$orderScore((int) reader.nextInt());
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        obj = realm.copyToRealm(obj);
        return obj;
    }

    public static com.ybyb.zzq.myrealm.data.Student copyOrUpdate(Realm realm, com.ybyb.zzq.myrealm.data.Student object, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().threadId != realm.threadId) {
            throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
        }
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return object;
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.myrealm.data.Student) cachedRealmObject;
        } else {
            return copy(realm, object, update, cache);
        }
    }

    public static com.ybyb.zzq.myrealm.data.Student copy(Realm realm, com.ybyb.zzq.myrealm.data.Student newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.ybyb.zzq.myrealm.data.Student) cachedRealmObject;
        } else {
            // rejecting default values to avoid creating unexpected objects from RealmModel/RealmList fields.
            com.ybyb.zzq.myrealm.data.Student realmObject = realm.createObjectInternal(com.ybyb.zzq.myrealm.data.Student.class, false, Collections.<String>emptyList());
            cache.put(newObject, (RealmObjectProxy) realmObject);
            ((StudentRealmProxyInterface) realmObject).realmSet$name(((StudentRealmProxyInterface) newObject).realmGet$name());
            ((StudentRealmProxyInterface) realmObject).realmSet$id(((StudentRealmProxyInterface) newObject).realmGet$id());
            ((StudentRealmProxyInterface) realmObject).realmSet$age(((StudentRealmProxyInterface) newObject).realmGet$age());
            ((StudentRealmProxyInterface) realmObject).realmSet$gender(((StudentRealmProxyInterface) newObject).realmGet$gender());

            RealmList<com.ybyb.zzq.myrealm.data.Subject> subjectsList = ((StudentRealmProxyInterface) newObject).realmGet$subjects();
            if (subjectsList != null) {
                RealmList<com.ybyb.zzq.myrealm.data.Subject> subjectsRealmList = ((StudentRealmProxyInterface) realmObject).realmGet$subjects();
                for (int i = 0; i < subjectsList.size(); i++) {
                    com.ybyb.zzq.myrealm.data.Subject subjectsItem = subjectsList.get(i);
                    com.ybyb.zzq.myrealm.data.Subject cachesubjects = (com.ybyb.zzq.myrealm.data.Subject) cache.get(subjectsItem);
                    if (cachesubjects != null) {
                        subjectsRealmList.add(cachesubjects);
                    } else {
                        subjectsRealmList.add(SubjectRealmProxy.copyOrUpdate(realm, subjectsList.get(i), update, cache));
                    }
                }
            }

            ((StudentRealmProxyInterface) realmObject).realmSet$totalScore(((StudentRealmProxyInterface) newObject).realmGet$totalScore());
            ((StudentRealmProxyInterface) realmObject).realmSet$averageScore(((StudentRealmProxyInterface) newObject).realmGet$averageScore());
            ((StudentRealmProxyInterface) realmObject).realmSet$orderScore(((StudentRealmProxyInterface) newObject).realmGet$orderScore());
            return realmObject;
        }
    }

    public static long insert(Realm realm, com.ybyb.zzq.myrealm.data.Student object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Student.class);
        long tableNativePtr = table.getNativePtr();
        StudentColumnInfo columnInfo = (StudentColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Student.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$name = ((StudentRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$age(), false);
        String realmGet$gender = ((StudentRealmProxyInterface)object).realmGet$gender();
        if (realmGet$gender != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
        }

        RealmList<com.ybyb.zzq.myrealm.data.Subject> subjectsList = ((StudentRealmProxyInterface) object).realmGet$subjects();
        if (subjectsList != null) {
            long subjectsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.subjectsIndex, rowIndex);
            for (com.ybyb.zzq.myrealm.data.Subject subjectsItem : subjectsList) {
                Long cacheItemIndexsubjects = cache.get(subjectsItem);
                if (cacheItemIndexsubjects == null) {
                    cacheItemIndexsubjects = SubjectRealmProxy.insert(realm, subjectsItem, cache);
                }
                LinkView.nativeAdd(subjectsNativeLinkViewPtr, cacheItemIndexsubjects);
            }
        }

        Table.nativeSetDouble(tableNativePtr, columnInfo.totalScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$totalScore(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.averageScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$averageScore(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.orderScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$orderScore(), false);
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Student.class);
        long tableNativePtr = table.getNativePtr();
        StudentColumnInfo columnInfo = (StudentColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Student.class);
        com.ybyb.zzq.myrealm.data.Student object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.myrealm.data.Student) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$name = ((StudentRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$id(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$age(), false);
                String realmGet$gender = ((StudentRealmProxyInterface)object).realmGet$gender();
                if (realmGet$gender != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
                }

                RealmList<com.ybyb.zzq.myrealm.data.Subject> subjectsList = ((StudentRealmProxyInterface) object).realmGet$subjects();
                if (subjectsList != null) {
                    long subjectsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.subjectsIndex, rowIndex);
                    for (com.ybyb.zzq.myrealm.data.Subject subjectsItem : subjectsList) {
                        Long cacheItemIndexsubjects = cache.get(subjectsItem);
                        if (cacheItemIndexsubjects == null) {
                            cacheItemIndexsubjects = SubjectRealmProxy.insert(realm, subjectsItem, cache);
                        }
                        LinkView.nativeAdd(subjectsNativeLinkViewPtr, cacheItemIndexsubjects);
                    }
                }

                Table.nativeSetDouble(tableNativePtr, columnInfo.totalScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$totalScore(), false);
                Table.nativeSetDouble(tableNativePtr, columnInfo.averageScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$averageScore(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.orderScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$orderScore(), false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.ybyb.zzq.myrealm.data.Student object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Student.class);
        long tableNativePtr = table.getNativePtr();
        StudentColumnInfo columnInfo = (StudentColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Student.class);
        long rowIndex = OsObject.createRow(realm.sharedRealm, table);
        cache.put(object, rowIndex);
        String realmGet$name = ((StudentRealmProxyInterface)object).realmGet$name();
        if (realmGet$name != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
        }
        Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$id(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$age(), false);
        String realmGet$gender = ((StudentRealmProxyInterface)object).realmGet$gender();
        if (realmGet$gender != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.genderIndex, rowIndex, false);
        }

        long subjectsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.subjectsIndex, rowIndex);
        LinkView.nativeClear(subjectsNativeLinkViewPtr);
        RealmList<com.ybyb.zzq.myrealm.data.Subject> subjectsList = ((StudentRealmProxyInterface) object).realmGet$subjects();
        if (subjectsList != null) {
            for (com.ybyb.zzq.myrealm.data.Subject subjectsItem : subjectsList) {
                Long cacheItemIndexsubjects = cache.get(subjectsItem);
                if (cacheItemIndexsubjects == null) {
                    cacheItemIndexsubjects = SubjectRealmProxy.insertOrUpdate(realm, subjectsItem, cache);
                }
                LinkView.nativeAdd(subjectsNativeLinkViewPtr, cacheItemIndexsubjects);
            }
        }

        Table.nativeSetDouble(tableNativePtr, columnInfo.totalScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$totalScore(), false);
        Table.nativeSetDouble(tableNativePtr, columnInfo.averageScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$averageScore(), false);
        Table.nativeSetLong(tableNativePtr, columnInfo.orderScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$orderScore(), false);
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.ybyb.zzq.myrealm.data.Student.class);
        long tableNativePtr = table.getNativePtr();
        StudentColumnInfo columnInfo = (StudentColumnInfo) realm.schema.getColumnInfo(com.ybyb.zzq.myrealm.data.Student.class);
        com.ybyb.zzq.myrealm.data.Student object = null;
        while (objects.hasNext()) {
            object = (com.ybyb.zzq.myrealm.data.Student) objects.next();
            if(!cache.containsKey(object)) {
                if (object instanceof RealmObjectProxy && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy)object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                    cache.put(object, ((RealmObjectProxy)object).realmGet$proxyState().getRow$realm().getIndex());
                    continue;
                }
                long rowIndex = OsObject.createRow(realm.sharedRealm, table);
                cache.put(object, rowIndex);
                String realmGet$name = ((StudentRealmProxyInterface)object).realmGet$name();
                if (realmGet$name != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.nameIndex, rowIndex, realmGet$name, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.nameIndex, rowIndex, false);
                }
                Table.nativeSetLong(tableNativePtr, columnInfo.idIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$id(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.ageIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$age(), false);
                String realmGet$gender = ((StudentRealmProxyInterface)object).realmGet$gender();
                if (realmGet$gender != null) {
                    Table.nativeSetString(tableNativePtr, columnInfo.genderIndex, rowIndex, realmGet$gender, false);
                } else {
                    Table.nativeSetNull(tableNativePtr, columnInfo.genderIndex, rowIndex, false);
                }

                long subjectsNativeLinkViewPtr = Table.nativeGetLinkView(tableNativePtr, columnInfo.subjectsIndex, rowIndex);
                LinkView.nativeClear(subjectsNativeLinkViewPtr);
                RealmList<com.ybyb.zzq.myrealm.data.Subject> subjectsList = ((StudentRealmProxyInterface) object).realmGet$subjects();
                if (subjectsList != null) {
                    for (com.ybyb.zzq.myrealm.data.Subject subjectsItem : subjectsList) {
                        Long cacheItemIndexsubjects = cache.get(subjectsItem);
                        if (cacheItemIndexsubjects == null) {
                            cacheItemIndexsubjects = SubjectRealmProxy.insertOrUpdate(realm, subjectsItem, cache);
                        }
                        LinkView.nativeAdd(subjectsNativeLinkViewPtr, cacheItemIndexsubjects);
                    }
                }

                Table.nativeSetDouble(tableNativePtr, columnInfo.totalScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$totalScore(), false);
                Table.nativeSetDouble(tableNativePtr, columnInfo.averageScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$averageScore(), false);
                Table.nativeSetLong(tableNativePtr, columnInfo.orderScoreIndex, rowIndex, ((StudentRealmProxyInterface)object).realmGet$orderScore(), false);
            }
        }
    }

    public static com.ybyb.zzq.myrealm.data.Student createDetachedCopy(com.ybyb.zzq.myrealm.data.Student realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.ybyb.zzq.myrealm.data.Student unmanagedObject;
        if (cachedObject != null) {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.ybyb.zzq.myrealm.data.Student)cachedObject.object;
            } else {
                unmanagedObject = (com.ybyb.zzq.myrealm.data.Student)cachedObject.object;
                cachedObject.minDepth = currentDepth;
            }
        } else {
            unmanagedObject = new com.ybyb.zzq.myrealm.data.Student();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        }
        ((StudentRealmProxyInterface) unmanagedObject).realmSet$name(((StudentRealmProxyInterface) realmObject).realmGet$name());
        ((StudentRealmProxyInterface) unmanagedObject).realmSet$id(((StudentRealmProxyInterface) realmObject).realmGet$id());
        ((StudentRealmProxyInterface) unmanagedObject).realmSet$age(((StudentRealmProxyInterface) realmObject).realmGet$age());
        ((StudentRealmProxyInterface) unmanagedObject).realmSet$gender(((StudentRealmProxyInterface) realmObject).realmGet$gender());

        // Deep copy of subjects
        if (currentDepth == maxDepth) {
            ((StudentRealmProxyInterface) unmanagedObject).realmSet$subjects(null);
        } else {
            RealmList<com.ybyb.zzq.myrealm.data.Subject> managedsubjectsList = ((StudentRealmProxyInterface) realmObject).realmGet$subjects();
            RealmList<com.ybyb.zzq.myrealm.data.Subject> unmanagedsubjectsList = new RealmList<com.ybyb.zzq.myrealm.data.Subject>();
            ((StudentRealmProxyInterface) unmanagedObject).realmSet$subjects(unmanagedsubjectsList);
            int nextDepth = currentDepth + 1;
            int size = managedsubjectsList.size();
            for (int i = 0; i < size; i++) {
                com.ybyb.zzq.myrealm.data.Subject item = SubjectRealmProxy.createDetachedCopy(managedsubjectsList.get(i), nextDepth, maxDepth, cache);
                unmanagedsubjectsList.add(item);
            }
        }
        ((StudentRealmProxyInterface) unmanagedObject).realmSet$totalScore(((StudentRealmProxyInterface) realmObject).realmGet$totalScore());
        ((StudentRealmProxyInterface) unmanagedObject).realmSet$averageScore(((StudentRealmProxyInterface) realmObject).realmGet$averageScore());
        ((StudentRealmProxyInterface) unmanagedObject).realmSet$orderScore(((StudentRealmProxyInterface) realmObject).realmGet$orderScore());
        return unmanagedObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Student = proxy[");
        stringBuilder.append("{name:");
        stringBuilder.append(realmGet$name() != null ? realmGet$name() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{id:");
        stringBuilder.append(realmGet$id());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{age:");
        stringBuilder.append(realmGet$age());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{gender:");
        stringBuilder.append(realmGet$gender() != null ? realmGet$gender() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{subjects:");
        stringBuilder.append("RealmList<Subject>[").append(realmGet$subjects().size()).append("]");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{totalScore:");
        stringBuilder.append(realmGet$totalScore());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{averageScore:");
        stringBuilder.append(realmGet$averageScore());
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{orderScore:");
        stringBuilder.append(realmGet$orderScore());
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
        StudentRealmProxy aStudent = (StudentRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aStudent.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aStudent.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aStudent.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }

}
